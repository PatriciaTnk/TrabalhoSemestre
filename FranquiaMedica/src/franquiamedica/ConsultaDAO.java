
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ConsultaDAO {

    Consulta[] consultas = new Consulta[50];
    
    public ConsultaDAO (PessoaDAO pessoadao, MedicoDAO medicodao, MatrizFranquiaDAO matrizfranquiadao, FranquiaDAO franquiadao){
        
        Pessoa p1 = new Pessoa();
        p1.setNome("josephina");
        p1.setEndereco("jose");
        p1.setCpf("jose");
        p1.setTelefone("josephina");
        p1.setLogin("jose");
        p1.setSenha("jose");
        pessoadao.adiciona(p1);

        Pessoa p2 = new Pessoa();
        p2.setNome("jaspion");
        p2.setEndereco("jas");
        p2.setCpf("jas");
        p2.setTelefone("jaspion");
        p2.setLogin("jas");
        p2.setSenha("jas");
        pessoadao.adiciona(p2);

        Pessoa p3 = new Pessoa();
        p3.setNome("jiraia");
        p3.setEndereco("jir");
        p3.setCpf("jir");
        p3.setTelefone("jiraia");
        p3.setLogin("jir");
        p3.setSenha("jir");
        pessoadao.adiciona(p3);
        
        Pessoa p4 = new Pessoa();
        p4.setNome("marcelo");
        p4.setEndereco("marc");
        p4.setCpf("marc");
        p4.setTelefone("marcelo");
        p4.setLogin("marc");
        p4.setSenha("marc");
        pessoadao.adiciona(p4);
        
        
        //dados Medico
        Pessoa pM1 = new Pessoa();
        pM1.setNome("Mjosephina");
        pM1.setEndereco("Mjose");
        pM1.setCpf("Mjose");
        pM1.setTelefone("Mjosephina");
        pM1.setLogin("Mjose");
        pM1.setSenha("Mjose");
        pessoadao.adiciona(pM1);

        Pessoa pM2 = new Pessoa();
        pM2.setNome("Mjaspion");
        pM2.setEndereco("Mjas");
        pM2.setCpf("Mjas");
        pM2.setTelefone("Mjaspion");
        pM2.setLogin("Mjas");
        pM2.setSenha("Mjas");
        pessoadao.adiciona(pM2);

        Pessoa pM3 = new Pessoa();
        pM3.setNome("Mjiraia");
        pM3.setEndereco("Mir");
        pM3.setCpf("Mjir");
        pM3.setTelefone("Mjiraia");
        pM3.setLogin("Mjir");
        pM3.setSenha("Mjir");
        pessoadao.adiciona(pM3);

        Medico m1 = new Medico(pM1);
        m1.setEspecialidade("oftalmologista");
        m1.setCrm("123oftalmo");
        medicodao.adiciona(m1);

        Medico m2 = new Medico(pM2);
        m2.setEspecialidade("oncologista");
        m2.setCrm("123onco");
        medicodao.adiciona(m2);

        Medico m3 = new Medico(pM3);
        m3.setEspecialidade("cardiologista");
        m3.setCrm("123cardio");
        medicodao.adiciona(m3);

        //Dados Matriz/Franquia
        Pessoa pMF1 = new Pessoa();
        pMF1.setNome("MFjosephina");
        pMF1.setEndereco("MFjose");
        pMF1.setCpf("MFjose");
        pMF1.setTelefone("MFjosephina");
        pMF1.setLogin("MFjose");
        pMF1.setSenha("MFjose");
        pessoadao.adiciona(pMF1);

        Pessoa pMF2 = new Pessoa();
        pMF2.setNome("MFjaspion");
        pMF2.setEndereco("MFjas");
        pMF2.setCpf("MFjas");
        pMF2.setTelefone("MFjaspion");
        pMF2.setLogin("MFjas");
        pMF2.setSenha("MFjas");
        pessoadao.adiciona(pMF2);

        Pessoa pMF3 = new Pessoa();
        pMF3.setNome("MFjiraia");
        pMF3.setEndereco("MFir");
        pMF3.setCpf("MFjir");
        pMF3.setTelefone("MFjiraia");
        pMF3.setLogin("MFjir");
        pMF3.setSenha("MFjir");
        pessoadao.adiciona(pMF3);
        
        Pessoa pMF4 = new Pessoa();
        pMF4.setNome("FRjosephina");
        pMF4.setEndereco("FRjose");
        pMF4.setCpf("FRjose");
        pMF4.setTelefone("FRjosephina");
        pMF4.setLogin("FRjose");
        pMF4.setSenha("FRjose");
        pessoadao.adiciona(pMF4);

        Pessoa pMF5 = new Pessoa();
        pMF5.setNome("FRjaspion");
        pMF5.setEndereco("FRjas");
        pMF5.setCpf("FRjas");
        pMF5.setTelefone("FRjaspion");
        pMF5.setLogin("FRjas");
        pMF5.setSenha("FRjas");
        pessoadao.adiciona(pMF5);

        Pessoa pMF6 = new Pessoa();
        pMF6.setNome("FRjiraia");
        pMF6.setEndereco("FRir");
        pMF6.setCpf("FRjir");
        pMF6.setTelefone("FRjiraia");
        pMF6.setLogin("FRjir");
        pMF6.setSenha("FRjir");
        pessoadao.adiciona(pMF6);

        MatrizFranquia mF1 = new MatrizFranquia(pMF1);
        mF1.setNome("Unidade A");
        mF1.setCnpj("111.456.789.-0001");
        mF1.setEndereco("123oftalmo");
        mF1.setCidade("Araxa");
        matrizfranquiadao.adiciona(mF1);

        MatrizFranquia mF2 = new MatrizFranquia(pMF2);
        mF2.setNome("Unidade B");
        mF2.setCnpj("222.456.789.-0001");
        mF2.setEndereco("123oftalmo");
        mF2.setCidade("Araxa");
        matrizfranquiadao.adiciona(mF2);

        MatrizFranquia mF3 = new MatrizFranquia(pMF3);
        mF3.setNome("Unidade C");
        mF3.setCnpj("333.456.789.-0001");
        mF3.setEndereco("123oftalmo");
        mF3.setCidade("Araxa");
        matrizfranquiadao.adiciona(mF3);
        
        Franquia f1 = new Franquia(pMF4, mF1);
        f1.setEndereco("123oftalmo");
        f1.setCidade("Araxa");
        franquiadao.adiciona(f1);

        Franquia f2 = new Franquia(pMF5, mF2);
        f2.setEndereco("123oftalmo");
        f2.setCidade("Araxa");
        franquiadao.adiciona(f1);

        Franquia f3 = new Franquia(pMF6, mF3);
        f3.setEndereco("123oftalmo");
        f3.setCidade("Araxa");
        franquiadao.adiciona(f1);

        Consulta c1 = new Consulta(p1, m1, f1);
        c1.setDiaHorario("05/05/2023", "10:30");
        c1.setValor(new BigDecimal(55.55));
        adiciona(c1);
        
        Consulta c2 = new Consulta(p2, m2, f2);
        c2.setDiaHorario("06/05/2023", "11:30");
        c2.setValor(new BigDecimal(55.55));
        adiciona(c2);
        
        Consulta c3 = new Consulta(p3, m3, f3);
        c3.setDiaHorario("07/05/2023", "12:30");
        c3.setValor(new BigDecimal(55.55));
        adiciona(c3);
    
}

    private int proximaPosicaoLivre() {//só serve aqui dentro
        for (int i = 0; i < consultas.length; i++) {
            Consulta c = consultas[i];
            if (c == null) {
                return i;
            }
        }
        return -1;//sinonimo que nao tem posição livre
    }

    public boolean adiciona(Consulta c) {
        int x = proximaPosicaoLivre();
        if (x != -1) {
            consultas[x] = c;
            return true;
        } else {
            return false;
        }
    }

    public void mostraTodos() {
        for (Consulta c : consultas) {
            if (c != null) {
                System.out.println(c);
            }
        }
    }

    /**
     * A Consulta verifica registro de acordo com o nome da Matriz
     *
     * @param idConsulta
     * @return
     */
    public Consulta verificaRegistro(long idConsulta) {
        for (Consulta c : consultas) {
            if (c.getId() == idConsulta) {
                return c;
            }
        }
        return null;
    }

    public boolean cancelaConsuta(String paciente, long idConsulta) {
        for (Consulta consulta : consultas) {
            if (consulta != null && consulta.getPaciente().getNome().equals(paciente)                    
                        && consulta.getId() == idConsulta){
                consulta.setEstado("cancelada");
                consulta.setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    //Precisa do nome do paciente e do medico para verificar a cosulta dai alterar valor
    public boolean alteraValor (long idConsulta, BigDecimal novoValor){
         for (Consulta consulta : consultas) {
            if (consulta.getId() == idConsulta) {
                consulta.setValor(novoValor);
                consulta.setDatamodificacao(LocalDateTime.now());
                return true;
            }        
         }
         return false;
    }

}
