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
        Pessoa p1 = new Pessoa();
        p1.setNome("MFjosephina");
        p1.setEndereco("MFjose");
        p1.setCpf("MFjose");
        p1.setTelefone("MFjosephina");
        p1.setLogin("MFjose");
        p1.setSenha("MFjose");

        Pessoa p2 = new Pessoa();
        p2.setNome("MFjaspion");
        p2.setEndereco("MFjas");
        p2.setCpf("MFjas");
        p2.setTelefone("MFjaspion");
        p2.setLogin("MFjas");
        p2.setSenha("MFjas");

        Pessoa p3 = new Pessoa();
        p3.setNome("MFjiraia");
        p3.setEndereco("MFir");
        p3.setCpf("MFjir");
        p3.setTelefone("MFjiraia");
        p3.setLogin("MFjir");
        p3.setSenha("MFjir");
        
        Pessoa p4 = new Pessoa();
        p4.setNome("FRjosephina");
        p4.setEndereco("FRjose");
        p4.setCpf("FRjose");
        p4.setTelefone("FRjosephina");
        p4.setLogin("FRjose");
        p4.setSenha("FRjose");

        Pessoa p5 = new Pessoa();
        p5.setNome("FRjaspion");
        p5.setEndereco("FRjas");
        p5.setCpf("FRjas");
        p5.setTelefone("FRjaspion");
        p5.setLogin("FRjas");
        p5.setSenha("FRjas");

        Pessoa p6 = new Pessoa();
        p6.setNome("FRjiraia");
        p6.setEndereco("FRir");
        p6.setCpf("FRjir");
        p6.setTelefone("FRjiraia");
        p6.setLogin("FRjir");
        p6.setSenha("FRjir");

        MatrizFranquia m1 = new MatrizFranquia(p1);
        m1.setNome("Unidade A");
        m1.setCnpj("111.456.789.-0001");
        m1.setEndereco("123oftalmo");
        m1.setCidade("Araxa");

        MatrizFranquia m2 = new MatrizFranquia(p2);
        m2.setNome("Unidade B");
        m2.setCnpj("222.456.789.-0001");
        m2.setEndereco("123oftalmo");
        m2.setCidade("Araxa");

        MatrizFranquia m3 = new MatrizFranquia(p3);
        m3.setNome("Unidade A");
        m3.setCnpj("333.456.789.-0001");
        m3.setEndereco("123oftalmo");
        m3.setCidade("Araxa");
        
        
        Franquia f1 = new Franquia(p4, m1);
        f1.setEndereco("123oftalmo");
        f1.setCidade("Araxa");
        adiciona(f1);

        Franquia f2 = new Franquia(p5, m2);
        f2.setEndereco("123oftalmo");
        f2.setCidade("Araxa");
        adiciona(f2);

        Franquia f3 = new Franquia(p6, m3);
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
    
    /**A Franquia tbm verifica registro de acordo com o nome da Matriz
    Além disso com o nome do responsave
     * @param nome
     * @param nomeResponsavel
     * @return */
    public Franquia verificaRegistro(String nome, String nomeResponsavel) {
        for (Franquia f : franquias) {
            if (f.getFranquia().getNome().equals(nome)
                    && f.encontraUmResponsavel(nomeResponsavel) != null) {
                return f;
            }
        }
        return null;
    }

    public boolean remove(String nome, String nomeResponsavel) {
        for (int i = 0; i < franquias.length; i++) {
            if (franquias[i] != null && franquias[i].getFranquia().getNome().equals(nome)
                    && franquias[i].encontraUmResponsavel(nomeResponsavel)!= null) {
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

    public boolean alterarLogin(String nome, String login, String novoLogin) {
        for (Franquia f : franquias) {
            if (f != null && f.encontraUmResponsavel(nome) != null
                    && f.encontraUmResponsavel(nome).getLogin().equals(login)) {
                f.encontraUmResponsavel(nome).setLogin(novoLogin);
                f.encontraUmResponsavel(nome).setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarSenha(String nome, String senha, String novaSenha) {
        for (Franquia f : franquias) {
            if (f != null && f.encontraUmResponsavel(nome) != null
                    && f.encontraUmResponsavel(nome).getSenha().equals(senha)) {
                f.encontraUmResponsavel(nome).setSenha(novaSenha);
                f.encontraUmResponsavel(nome).setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alteraResponsavelNome(String nome, String novoNome) {
        for (Franquia f : franquias) {
            if (f != null && f.encontraUmResponsavel(nome) != null) {
                f.encontraUmResponsavel(nome).setNome(novoNome);
                f.encontraUmResponsavel(nome).setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarResponsavelEndereco(String nome, String endereco, String novaEndereco) {
        for (Franquia f : franquias) {
            if (f != null && f.encontraUmResponsavel(nome) != null
                    && f.encontraUmResponsavel(nome).getEndereco().equals(endereco)) {
                f.encontraUmResponsavel(nome).setEndereco(novaEndereco);
                f.encontraUmResponsavel(nome).setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarResponsavelCpf(String nome, String cpf, String novoCpf) {
        for (Franquia f : franquias) {
            if (f != null && f.encontraUmResponsavel(nome) != null
                    && f.encontraUmResponsavel(nome).getCpf().equals(cpf)) {
                f.encontraUmResponsavel(nome).setCpf(novoCpf);
                f.encontraUmResponsavel(nome).setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarResponsavelTelefone(String nome, String telefone, String novoTelefone) {
        for (Franquia f : franquias) {
            if (f != null && f.encontraUmResponsavel(nome) != null
                    && f.encontraUmResponsavel(nome).getTelefone().equals(telefone)) {
                f.encontraUmResponsavel(nome).setTelefone(novoTelefone);
                f.encontraUmResponsavel(nome).setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

}
