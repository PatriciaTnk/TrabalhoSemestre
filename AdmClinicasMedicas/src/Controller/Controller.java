/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

//Criar o menu do login e os menus de cada um dos 5 perfis
//Quando for mexer com os menus lembrar de trocar o equals hash code de TODAS as CLASSES sem DAO
import View.View;
import franquiamedica.ConnectionFactory;
import franquiamedica.Consulta;
import franquiamedica.ConsultaDAO;
import franquiamedica.FinanceiroAdm;
import franquiamedica.FinanceiroAdmDAO;
import franquiamedica.FinanceiroMedico;
import franquiamedica.FinanceiroMedicoDAO;
import franquiamedica.FranquiaDAO;
import franquiamedica.FuncionarioAdmDAO;
import franquiamedica.InfoConsulta;
import franquiamedica.MatrizFranquiaDAO;
import franquiamedica.Pessoa;
import franquiamedica.PessoaDAO;
import franquiamedica.MedicoDAO;
import franquiamedica.InfoConsultaDAO;
import franquiamedica.Procedimento;
import franquiamedica.ProcedimentoDAO;
import franquiamedica.Utilitario;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import franquiamedica.Franquia;
import franquiamedica.MatrizFranquia;
import java.io.BufferedReader;
import java.io.FileReader;

public class Controller extends SimpleFileVisitor <Object>{

    public void relatorios() {
        {
            try {                
                Path parentDirectory = Utilitario.arquivoRelatorio.getParent();
                if (parentDirectory != null) {
                    Files.createDirectories(parentDirectory);
                }
                if (!Files.exists(Utilitario.arquivoRelatorio)) {
                    Files.createFile(Utilitario.arquivoRelatorio);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void escreverNoArquivo(String conteudo) {
        try {
            List<String> linhas = Arrays.asList(conteudo);
            Files.write(Utilitario.arquivoRelatorio, linhas, Charset.forName("UTF-8"),StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lerArquivoRelatorio() {
        try {
            List<String> linhas = Files.readAllLines(Utilitario.arquivoRelatorio);
            for (String linha : linhas) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void copyTxtToPdf(String txtFilename, String pdfFilename) {
    try {
        // Create a new PDF document
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        // step 2
        com.itextpdf.text.pdf.PdfWriter.getInstance(document, new java.io.FileOutputStream(Utilitario.result.toString()));

        // Open the document
        document.open();

        // Read the contents of the TXT file
        BufferedReader reader = new BufferedReader(new FileReader(Utilitario.arquivoRelatorio.toString()));
        String line;
        while ((line = reader.readLine()) != null) {
            // Add each line as a paragraph to the PDF document
            document.add(new Paragraph(line));
        }
        reader.close();

        // Close the document
        document.close();

        System.out.println("File copied successfully from " + txtFilename + " to " + pdfFilename);
    } catch (IOException | DocumentException e) {
        e.printStackTrace();
    }
}

    private View telaIni = null;

    public void setGUI(View tela) {
        this.telaIni = tela;
    }

    public View getTela() {
        return telaIni;
    }

    public PessoaDAO p = new PessoaDAO();
    public MedicoDAO m = new MedicoDAO();
    public MatrizFranquiaDAO mf = new MatrizFranquiaDAO();
    public FranquiaDAO f = new FranquiaDAO();
    public ConsultaDAO c = new ConsultaDAO();
    public InfoConsultaDAO ic = new InfoConsultaDAO();
    public ProcedimentoDAO proc = new ProcedimentoDAO();
    public FinanceiroAdmDAO fin = new FinanceiroAdmDAO();
    public FinanceiroMedicoDAO finMed = new FinanceiroMedicoDAO();
    public FuncionarioAdmDAO fa = new FuncionarioAdmDAO();

    public boolean validaLogin(String login, String senha) {
        if (p.buscaLoginSenha(login, senha) != -1) {
            System.out.println("\n\nLogin Realizado com sucesso");
            relatorios();
            chamaMenus();
            return true;
        } else {
            System.out.println("\n\nLogin não encontrado.\nTente novamente");
            return false;
        }
    }

    private void chamaMenus() {
        System.out.println(Utilitario.getPessoaLogada());
        if (Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("Paciente")) {
            telaIni.pacienteAll();
        } else if (Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("Medico")) {
            telaIni.medicoAll();
        } else if (Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("FuncAdministrativo")) {
            telaIni.funcionarioAdmAll();
        } else if (Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("Responsavel pela Franquia")) {
            telaIni.responsavelFranquiaAll();
        } else if (Utilitario.getPessoaLogada().getTipoUsuario().equalsIgnoreCase("Dono de Franquia")) {
            telaIni.donoFranquiaAll();
        }
    }

    public boolean novoCadastro(Pessoa novoRegistro) {
        //validacao do input de dados mas pode pular aqui
        return p.adiciona(novoRegistro);
    }

    public void primeiroCadastro() {
        String sql = "UPDATE pessoas SET tipoDeUsuario = ?, dataModificacao = ? WHERE id IN (?)";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, "Regente");
            stmt.setDate(2, java.sql.Date.valueOf(Utilitario.diaSistema));
            stmt.setInt(3, 1);
            stmt.executeUpdate();

            // Update for row with id = 2
            stmt.setString(1, "Dono de Franquia");
            stmt.setDate(2, java.sql.Date.valueOf(Utilitario.diaSistema));
            stmt.setInt(3, 2);
            stmt.executeUpdate();
            p.populaLista();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alterarDiaSistema(int parseInt) {
        Utilitario.diaSistema = Utilitario.diaSistema.plusDays(parseInt);
        System.out.println("O novo dia do sistema e: " + Utilitario.diaSistema);
    }

    /**
     * To give you an example, just loop over a list using for loop and try to
     * remove one element, you will get the ConcurrentModificatoinExcetpion?
     * Why? because you broke the rule of not modifying a Collection during
     * iteration.
     *
     * @param outroDia
     */
    public void alteraConsulta(LocalDate outroDia) {
        c.populaLista();
        while (outroDia.isBefore(Utilitario.diaSistema)) {
            for (int i = 0; i < c.consultas.size(); i++) {
                if (c.consultas.get(i).isVisible() && c.consultas.get(i).getDiaHorario().equals(outroDia)) {
                    c.alterarEstado(c.consultas.get(i).getId(), "Realizado");
                }
            }
            outroDia = outroDia.plusDays(1);
        }
    }

    public void alteraProcedimento(LocalDate outroDia) {
        proc.populaLista();
        while (outroDia.isBefore(Utilitario.diaSistema)) {
            for (int i = 0; i < proc.procedimentos.size(); i++) {
                if (proc.procedimentos.get(i).isVisible() && proc.procedimentos.get(i).getDiaHorario().equals(outroDia)) {
                    proc.alterarEstado(proc.procedimentos.get(i).getId(), "Realizado");
                }
            }
            outroDia = outroDia.plusDays(1);
        }
    }

    public void valorRecebidoConsulta(LocalDate outroDia) {
        c.populaLista();
        while (outroDia.isBefore(Utilitario.diaSistema)) {
            for (Consulta con : c.consultas) {
                if (con.isVisible() && con.getDiaHorario().equals(outroDia)) {
                    FinanceiroAdm recebe = new FinanceiroAdm();
                    recebe.setTipoDeMovim("Entrada");
                    recebe.setValor(con.getValor().multiply(new BigDecimal("0.30")).setScale(2, RoundingMode.HALF_DOWN));
                    recebe.setUnidade(con.getUnidade());
                    recebe.setDescritivo(recebe.getTipoDeMovim());
                    recebe.setDataCriacao(outroDia);
                    recebe.setDatamodificacao(outroDia);
                    recebe.setVisible(true);
                    fin.adiciona(recebe);

                    FinanceiroMedico paga = new FinanceiroMedico();
                    paga.setValor(con.getValor().multiply(new BigDecimal("0.70")).setScale(2, RoundingMode.HALF_DOWN));
                    paga.setMedico(con.getMedico());
                    paga.setEstado("A ser pago");
                    paga.setUnidade(con.getUnidade());
                    paga.setDataCriacao(outroDia);
                    paga.setDatamodificacao(outroDia);
                    paga.setVisible(true);
                    finMed.adiciona(paga);
                }
            }
            outroDia = outroDia.plusDays(1);
        }
    }

    public void valorRecebidoProcedimento(LocalDate outroDia) {
        proc.populaLista();
        while (outroDia.isBefore(Utilitario.diaSistema)) {

            for (Procedimento pro : proc.procedimentos) {
                if (pro.isVisible() && pro.getDiaHorario().equals(outroDia)) {
                    FinanceiroAdm recebeP = new FinanceiroAdm();
                    recebeP.setTipoDeMovim("Entrada");
                    recebeP.setValor(pro.getValorPro().multiply(new BigDecimal("0.50")).setScale(2, RoundingMode.HALF_DOWN));
                    recebeP.setUnidade(c.verificaRegistro(pro.getConsulta()).getUnidade());
                    recebeP.setDescritivo(recebeP.getTipoDeMovim());
                    recebeP.setDataCriacao(outroDia);
                    recebeP.setDatamodificacao(outroDia);
                    recebeP.setVisible(true);
                    fin.adiciona(recebeP);

                    FinanceiroMedico pagaP = new FinanceiroMedico();
                    pagaP.setValor(pro.getValorPro().multiply(new BigDecimal("0.50")).setScale(2, RoundingMode.HALF_DOWN));
                    pagaP.setMedico(c.verificaRegistro(pro.getConsulta()).getMedico());
                    pagaP.setEstado("A ser pago");
                    pagaP.setUnidade(c.verificaRegistro(pro.getConsulta()).getUnidade());
                    pagaP.setDataCriacao(outroDia);
                    pagaP.setDatamodificacao(outroDia);
                    pagaP.setVisible(true);
                    finMed.adiciona(pagaP);
                }

            }
            outroDia = outroDia.plusDays(1);
        }
    }

    public void relPagamentoUmMedico() {
        BigDecimal montante = new BigDecimal("0.00");
        finMed.populaLista();
        for (int i = 0; i < finMed.financas.size(); i++) {
            if (p.verificaRegistro(m.verificaRegistro(finMed.financas.get(i).getMedico()).getPessoa()).getId() == Utilitario.getPessoaLogada().getId()) {
                montante = montante.add(finMed.financas.get(i).getValor());
            }
        }
        System.out.println("Medico: " + Utilitario.getPessoaLogada().getNome());
        System.out.println("Valor total a receber: " + montante);

        // Escreve no arquivo
        String relatorio = "Medico: " + Utilitario.getPessoaLogada().getNome() + "\n"
                + "Valor total a receber: " + montante;
        escreverNoArquivo(relatorio);
    }

    public void pagamentoMedicos() {
        Map<String, BigDecimal> map = new HashMap<>();
        String nome;

        finMed.populaLista();
        for (int i = 0; i < finMed.financas.size(); i++) {
            nome = p.verificaRegistro(m.verificaRegistro(finMed.financas.get(i).getMedico()).getPessoa()).getNome();
            BigDecimal montante = map.get(nome);
            if (montante == null) {
                montante = finMed.financas.get(i).getValor();
            } else {
                montante = montante.add(finMed.financas.get(i).getValor());
            }
            map.put(nome, montante);
        }
        System.out.println(map);

        // Escreve no arquivo
        StringBuilder relatorioBuilder = new StringBuilder();
        for (Map.Entry<String, BigDecimal> entry : map.entrySet()) {
            relatorioBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        escreverNoArquivo(relatorioBuilder.toString());
    }

    public void relatConsulPaciente() {
        c.populaLista();
        for (Consulta con : c.consultas) {
            if (con.getPaciente() == Utilitario.getPessoaLogada().getId()) {
                System.out.println(con);
            }
        }
        // Escreve no arquivo
        StringBuilder relatorioBuilder = new StringBuilder();
        for (Consulta con : c.consultas) {
            if (con.getPaciente() == Utilitario.getPessoaLogada().getId()) {
                relatorioBuilder.append(con).append("\n");
            }
        }
        escreverNoArquivo(relatorioBuilder.toString());
    }

    public void relProcPaciente() {
        proc.populaLista();
        for (Procedimento pro : proc.procedimentos) {
            for (Consulta con : c.consultas) {
                if (pro.getConsulta() == con.getId()) {
                    if (con.getPaciente() == Utilitario.getPessoaLogada().getId()) {
                        System.out.println(pro);
                    }
                }
            }
        }

        // Escreve no arquivo
        StringBuilder relatorioBuilder = new StringBuilder();
        for (Procedimento pro : proc.procedimentos) {
            for (Consulta con : c.consultas) {
                if (pro.getConsulta() == con.getId()) {
                    if (con.getPaciente() == Utilitario.getPessoaLogada().getId()) {
                        relatorioBuilder.append(pro).append("\n");
                    }
                }
            }
        }
        escreverNoArquivo(relatorioBuilder.toString());
    }

    public void relConsulMedico() { //ok
        c.populaLista();
        for (Consulta con : c.consultas) {
            if (m.verificaRegistro(con.getMedico()).getPessoa() == Utilitario.getPessoaLogada().getId()) {
                System.out.println(con);
            }
        }

        // Escreve no arquivo
        StringBuilder relatorioBuilder = new StringBuilder();
        for (Consulta con : c.consultas) {
            if (m.verificaRegistro(con.getMedico()).getPessoa() == Utilitario.getPessoaLogada().getId()) {
                relatorioBuilder.append(con).append("\n");
            }
        }
        escreverNoArquivo(relatorioBuilder.toString());
    }

    public void addDescriConsul(long id, String descricao) { //ok
        InfoConsulta nova = new InfoConsulta();
        nova.setConsulta(id);
        nova.setDescricao(descricao);
        nova.setDataCriacao(c.verificaRegistro(id).getDiaHorario());
        nova.setDatamodificacao(c.verificaRegistro(id).getDiaHorario());

        ic.adiciona(nova);

        // Escreve no arquivo
        String relatorio = "Descrição adicionada para consulta com ID: " + id;
        escreverNoArquivo(relatorio);
    }

    public void relProcMedico() { //ok
        proc.populaLista();
        c.populaLista();
        for (Procedimento pro : proc.procedimentos) {
            if ((c.verificaRegistro(pro.getConsulta())).getMedico() == Utilitario.getPessoaLogada().getId()) {
                System.out.println(pro);
            }
        }

        // Escreve no arquivo
        StringBuilder relatorioBuilder = new StringBuilder();
        for (Procedimento pro : proc.procedimentos) {
            if ((c.verificaRegistro(pro.getConsulta())).getMedico() == Utilitario.getPessoaLogada().getId()) {
                relatorioBuilder.append(pro).append("\n");
            }
        }
        escreverNoArquivo(relatorioBuilder.toString());
    }

    public void addDescriProc(long idP, String laudo) {
        proc.alterarLaudo(idP, laudo);

        // Escreve no arquivo
        String relatorio = "Laudo adicionado para procedimento com ID: " + idP;
        escreverNoArquivo(relatorio);
    }

    public void relFinUmaFranquia(long idFranquiaEscolhida) {
        Map<Long, BigDecimal> map = new HashMap<>();

        fin.populaLista();
        for (int i = 0; i < fin.financas.size(); i++) {
            Long id = fin.financas.get(i).getUnidade();

            BigDecimal montante = map.getOrDefault(id, BigDecimal.ZERO);
            montante = montante.add(fin.financas.get(i).getValor());

            map.put(id, montante);
        }

        System.out.println(map);
        System.out.println("\n\nValor total recebido pela Franquia escolhida: " + map.get(idFranquiaEscolhida));

        // Escreve no arquivo
        StringBuilder relatorioBuilder = new StringBuilder();
        for (Map.Entry<Long, BigDecimal> entry : map.entrySet()) {
            relatorioBuilder.append("Franquia: ").append(entry.getKey()).append("\n");
            relatorioBuilder.append("Valor total recebido: ").append(entry.getValue()).append("\n");
        }
        relatorioBuilder.append("\n\nValor total recebido pela Franquia escolhida: ").append(map.get(idFranquiaEscolhida));
        escreverNoArquivo(relatorioBuilder.toString());
    }

    public void relFinFranquias() {
        Map<Long, BigDecimal> map = new HashMap<>();

        fin.populaLista();
        for (int i = 0; i < fin.financas.size(); i++) {
            Long id = fin.financas.get(i).getUnidade();

            BigDecimal montante = map.getOrDefault(id, BigDecimal.ZERO);
            montante = montante.add(fin.financas.get(i).getValor());

            map.put(id, montante);
        }

        System.out.println(map);

        // Escreve no arquivo
        StringBuilder relatorioBuilder = new StringBuilder();
        for (Map.Entry<Long, BigDecimal> entry : map.entrySet()) {
            relatorioBuilder.append("Franquia: ").append(entry.getKey()).append("\n");
            relatorioBuilder.append("Valor total recebido: ").append(entry.getValue()).append("\n");
        }
        escreverNoArquivo(relatorioBuilder.toString());
    }

    public void calcularFaturamentoTotal() {
        Map<Long, BigDecimal> map = new HashMap<>();

        fin.populaLista();
        for (int i = 0; i < fin.financas.size(); i++) {
            Long id = fin.financas.get(i).getUnidade();

            BigDecimal montante = map.getOrDefault(id, BigDecimal.ZERO);
            montante = montante.add(fin.financas.get(i).getValor());

            map.put(id, montante);
        }

        Collection<BigDecimal> values = map.values();
        BigDecimal ok = BigDecimal.ZERO;
        // Iterando sobre os valores do Map
        for (BigDecimal value : values) {
            ok = ok.add(value);
        }

        BigDecimal valorPagamento = new BigDecimal("1000.00").add(ok.multiply(new BigDecimal("0.05")));

        FinanceiroAdm pagamentoAdm = new FinanceiroAdm();
        pagamentoAdm.setTipoDeMovim("Saida");
        pagamentoAdm.setValor(valorPagamento);
        pagamentoAdm.setUnidade(0);
        pagamentoAdm.setDescritivo("Pagamento à Administradora");
        pagamentoAdm.setDataCriacao(Utilitario.diaSistema);
        pagamentoAdm.setDatamodificacao(Utilitario.diaSistema);
        pagamentoAdm.setVisible(true);

        fin.adiciona(pagamentoAdm);

        ok = ok.subtract(valorPagamento);
        System.out.println("O valor total de pagamento para a Matriz foi de: " + valorPagamento.setScale(2, RoundingMode.HALF_DOWN));
        System.out.println("Sendo que o lucro foi de:" + ok.setScale(2, RoundingMode.HALF_DOWN));

        // Escreve no arquivo
        String relatorio = "O valor total de pagamento para a Matriz foi de: " + valorPagamento.setScale(2, RoundingMode.HALF_DOWN) + "\n"
                + "Sendo que o lucro foi de:" + ok.setScale(2, RoundingMode.HALF_DOWN);
        escreverNoArquivo(relatorio);
    }

    public void incrementaMes(int qtdMeses) {
        LocalDate diaMaisMes = Utilitario.diaSistema;
        diaMaisMes = diaMaisMes.plusMonths(qtdMeses);
        System.out.println("Novo dia nessa funcao com meses incrementados: " + diaMaisMes);
    }
    
    public Long buscaIdMatriz (){
        for(MatrizFranquia mat : mf.matrizes){
            if(mat.getDono() == Utilitario.getPessoaLogada().getId()){
                return mat.getId();
            }
        }        
        return null;
    }
    
    public Long buscaIdFranquia (){
        for(Franquia franq : f.franquias){
            if(franq.getResponsavel()== Utilitario.getPessoaLogada().getId()){
                return franq.getId();
            }
        }  
        return null;
    }
    

}
