/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

import java.time.LocalDateTime;


public class FranquiaDAO {

    Franquia[] franquias = new Franquia[15];

    public FranquiaDAO() {
        //LEMBRAR DE TROOOCAAARR ESSAS PESSOAS SÃO NOVAS E TEM QUE SER UMA PESSOA CADASTRADA
        Pessoa pMF1 = new Pessoa();
        pMF1.setNome("MFjosephina");
        pMF1.setEndereco("MFjose");
        pMF1.setCpf("MFjose");
        pMF1.setTelefone("MFjosephina");
        pMF1.setLogin("MFjose");
        pMF1.setSenha("MFjose");

        Pessoa pMF2 = new Pessoa();
        pMF2.setNome("MFjaspion");
        pMF2.setEndereco("MFjas");
        pMF2.setCpf("MFjas");
        pMF2.setTelefone("MFjaspion");
        pMF2.setLogin("MFjas");
        pMF2.setSenha("MFjas");

        Pessoa pMF3 = new Pessoa();
        pMF3.setNome("MFjiraia");
        pMF3.setEndereco("MFir");
        pMF3.setCpf("MFjir");
        pMF3.setTelefone("MFjiraia");
        pMF3.setLogin("MFjir");
        pMF3.setSenha("MFjir");
        
        Pessoa pMF4 = new Pessoa();
        pMF4.setNome("FRjosephina");
        pMF4.setEndereco("FRjose");
        pMF4.setCpf("FRjose");
        pMF4.setTelefone("FRjosephina");
        pMF4.setLogin("FRjose");
        pMF4.setSenha("FRjose");

        Pessoa pMF5 = new Pessoa();
        pMF5.setNome("FRjaspion");
        pMF5.setEndereco("FRjas");
        pMF5.setCpf("FRjas");
        pMF5.setTelefone("FRjaspion");
        pMF5.setLogin("FRjas");
        pMF5.setSenha("FRjas");

        Pessoa pMF6 = new Pessoa();
        pMF6.setNome("FRjiraia");
        pMF6.setEndereco("FRir");
        pMF6.setCpf("FRjir");
        pMF6.setTelefone("FRjiraia");
        pMF6.setLogin("FRjir");
        pMF6.setSenha("FRjir");

        MatrizFranquia m1 = new MatrizFranquia(pMF1);
        m1.setNome("Unidade A");
        m1.setCnpj("111.456.789.-0001");
        m1.setEndereco("123oftalmo");
        m1.setCidade("Araxa");

        MatrizFranquia m2 = new MatrizFranquia(pMF2);
        m2.setNome("Unidade B");
        m2.setCnpj("222.456.789.-0001");
        m2.setEndereco("123oftalmo");
        m2.setCidade("Araxa");

        MatrizFranquia m3 = new MatrizFranquia(pMF3);
        m3.setNome("Unidade A");
        m3.setCnpj("333.456.789.-0001");
        m3.setEndereco("123oftalmo");
        m3.setCidade("Araxa");
        
        
        Franquia f1 = new Franquia(pMF4, m1);
        f1.setEndereco("123oftalmo");
        f1.setCidade("Araxa");
        adiciona(f1);

        Franquia f2 = new Franquia(pMF5, m2);
        f2.setEndereco("123oftalmo");
        f2.setCidade("Araxa");
        adiciona(f2);

        Franquia f3 = new Franquia(pMF6, m3);
        f3.setEndereco("123oftalmo");
        f3.setCidade("Araxa");
        adiciona(f3);

    }

    private int proximaPosicaoLivre() {//só serve aqui dentro
        for (int i = 0; i < franquias.length; i++) {
            Franquia f = franquias[i];
            if (f == null) {
                return i;
            }
        }
        return -1;//sinonimo que nao tem posição livre
    }

    public boolean adiciona(Franquia f) {
        int x = proximaPosicaoLivre();
        if (x != -1) {
            franquias[x] = f;
            return true;
        } else {
            return false;
        }
    }

    public void mostraTodos() {
        for (Franquia f : franquias) {
            if (f != null) {
                System.out.println(f);
            }
        }
    }
    
    /**A Franquia verifica registro de acordo com o nome da Matriz
     * @param nome
     * @return */
    public Franquia verificaRegistro(String nome) {
        for (Franquia f : franquias) {
            if (f.getFranquia().getNome().equals(nome)) {
                return f;
            }
        }
        return null;
    }

    public boolean remove(String nome) {
        for (int i = 0; i < franquias.length; i++) {
            if (franquias[i] != null && franquias[i].getFranquia().getNome().equals(nome)) {
                franquias[i] = null;
                return true;
            }
        }
        return false;
    }

    /**
     * Todas os updates precisam de modify data
     */ 
    public boolean alterarEndereco(String endereco, String novoEndereco) {
        for (Franquia f : franquias) {
            if (f != null && f.getEndereco().equals(endereco)) {
                f.setEndereco(novoEndereco);
                f.setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarCidade(String cidade, String novoCidade) {
        for (Franquia f : franquias) {
            if (f != null && f.getCidade().equals(cidade)) {
                f.setCidade(novoCidade);
                f.setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarLogin(String login, String novoLogin) {
        for (Franquia f : franquias) {
            if (f != null && f.getResponsavel().getLogin().equals(login)) {
                f.getResponsavel().setLogin(novoLogin);
                f.getResponsavel().setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarSenha(String senha, String novaSenha) {
        for (Franquia f : franquias) {
            if (f != null
                    && f.getResponsavel().getSenha().equals(senha)) {
                f.getResponsavel().setSenha(novaSenha);
                f.getResponsavel().setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alteraResponsavelNome(String novoNome) {
        for (Franquia f : franquias) {
            if (f != null) {
                f.getResponsavel().setNome(novoNome);
                f.getResponsavel().setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarResponsavelEndereco(String endereco, String novaEndereco) {
        for (Franquia f : franquias) {
            if (f != null
                    && f.getResponsavel().getEndereco().equals(endereco)) {
                f.getResponsavel().setEndereco(novaEndereco);
                f.getResponsavel().setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarResponsavelCpf(String cpf, String novoCpf) {
        for (Franquia f : franquias) {
            if (f != null
                    && f.getResponsavel().getCpf().equals(cpf)) {
                f.getResponsavel().setCpf(novoCpf);
                f.getResponsavel().setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarResponsavelTelefone(String telefone, String novoTelefone) {
        for (Franquia f : franquias) {
            if (f != null
                    && f.getResponsavel().getTelefone().equals(telefone)) {
                f.getResponsavel().setTelefone(novoTelefone);
                f.getResponsavel().setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

}
