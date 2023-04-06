/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

import java.time.LocalDateTime;

public class PessoaDAO {
    /**adicionar, mostrar, conferir se tem registro, deletar e atualizar.
    para já ter dados cadastrados, popular dados no construtor
    preciso de um array para popular
    OBS: Para atender
    Uma pessoa pode ser cadastrada com diferentes papéis. Cada login diferente irá diferenciar os usuários.
    Deverão ser realizados mais cadastros da mesma pessoa, pois para cada id de cadastro só pode ser atribuido um login e um papel*/
    Pessoa[] pessoas = new Pessoa[60];

    public PessoaDAO() {
        Pessoa p0 = new Pessoa();
        p0.setNome("patricia");
        p0.setEndereco("patri");
        p0.setCpf("patri");
        p0.setTelefone("patricia");
        p0.setLogin("patri");
        p0.setSenha("patri");
        adiciona(p0);
        
        Pessoa p1 = new Pessoa();
        p1.setNome("josephina");
        p1.setEndereco("jose");
        p1.setCpf("jose");
        p1.setTelefone("josephina");
        p1.setLogin("jose");
        p1.setSenha("jose");
        adiciona(p1);

        Pessoa p2 = new Pessoa();
        p2.setNome("jaspion");
        p2.setEndereco("jas");
        p2.setCpf("jas");
        p2.setTelefone("jaspion");
        p2.setLogin("jas");
        p2.setSenha("jas");
        adiciona(p2);

        Pessoa p3 = new Pessoa();
        p3.setNome("jiraia");
        p3.setEndereco("jir");
        p3.setCpf("jir");
        p3.setTelefone("jiraia");
        p3.setLogin("jir");
        p3.setSenha("jir");
        adiciona(p3);
        
        Pessoa p4 = new Pessoa();
        p4.setNome("marcelo");
        p4.setEndereco("marc");
        p4.setCpf("marc");
        p4.setTelefone("marcelo");
        p4.setLogin("marc");
        p4.setSenha("marc");
        adiciona(p4);

        Pessoa p5 = new Pessoa();
        p5.setNome("carlos");
        p5.setEndereco("carl");
        p5.setCpf("carl");
        p5.setTelefone("carlos");
        p5.setLogin("carl");
        p5.setSenha("carl");
        adiciona(p5);

        Pessoa p6 = new Pessoa();
        p6.setNome("amanda");
        p6.setEndereco("aman");
        p6.setCpf("aman");
        p6.setTelefone("amanda");
        p6.setLogin("aman");
        p6.setSenha("aman");
        adiciona(p6);

        Pessoa p7 = new Pessoa();
        p7.setNome("laiane");
        p7.setEndereco("laia");
        p7.setCpf("laia");
        p7.setTelefone("laiane");
        p7.setLogin("laia");
        p7.setSenha("laia");
        adiciona(p7);

        Pessoa p8 = new Pessoa();
        p8.setNome("manoela");
        p8.setEndereco("manoe");
        p8.setCpf("manoe");
        p8.setTelefone("manoela");
        p8.setLogin("manoe");
        p8.setSenha("manoe");
        adiciona(p8);


    }
    
    private int proximaPosicaoLivre() {//só serve aqui dentro
        for (int i = 0; i < pessoas.length; i++) {
            Pessoa p = pessoas[i];
            if (p == null) {
                return i;
            }
        }
        return -1;//sinonimo que nao tem posição livre
    }

    public boolean adiciona(Pessoa p) {
        int x = proximaPosicaoLivre();
        if (x != -1) {
            pessoas[x] = p;
            return true;
        } else {
            return false;
        }
    }
    
    public void mostraTodos() {
        for (Pessoa p : pessoas) {
            if (p != null) {
                System.out.println(p);
            }
        }
    }
    
    /**busca de registro por nome, podemos alterar o requisito de busca
        public boolean temRegistro(String nome, String login) {
        for (Pessoa pessoa : pessoasp) {
            if (pessoa.getNome().equals(nome)
                 && pessoa.getLogin().equals(login)){
                return true;
            }            
        }
        return false;
     * @param nome}
     * @param id
     * @return */
    
    public Pessoa verificaRegistro(long id) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getId()== id) {                
                return pessoa;
            }            
        }
        return null;
    }
    
    /** * a exclusao do registro se da pelo nome, mas podemos solicitar outra informacao
     * dai tem que mexer na UI para solicitar a outra informacao do parametro
         public boolean remove(String nome, String login) {
        for (int i = 0; i < pessoasp.length; i++) {
            if (pessoasp[i] != null && pessoasp[i].getNome().equals(nome)
                && && pessoasp[i].getLogin().equals(login)) {
                pessoasp[i] = null;
                return true;
            }
        }
        return false;
     * @param nome
     * @return }*/
    
    public boolean remove(long id) {
        for (int i = 0; i < pessoas.length; i++) {
            if (pessoas[i].getId() == id) {
                pessoas[i] = null;
                return true;
            }
        }
        return false;
    }

    /**
     * Todas os updates precisam de modify data
     * @param id
     * @param novoNome
     * @return 
     */
    public boolean alterarNome(long id, String novoNome) {
        for (Pessoa p : pessoas) {
            if (p.getId() == id) {
                p.setNome(novoNome);
                p.setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarEndereco(long id, String novoEndereco) {
        for (Pessoa p : pessoas) {
            if (p.getId() == id) {
                p.setEndereco(novoEndereco);
                p.setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarCpf(long id, String novoCpf) {
        for (Pessoa p : pessoas) {
            if (p.getId() == id) {
                p.setCpf(novoCpf);
                p.setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarTelefone(long id, String novoTelefone) {
        for (Pessoa p : pessoas) {
            if (p.getId() == id) {
                p.setTelefone(novoTelefone);
                p.setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarLogin(long id, String novoLogin) {
        for (Pessoa p : pessoas) {
            if (p.getId() == id) {
                p.setLogin(novoLogin);
                p.setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarSenha(long id, String novaSenha) {
        for (Pessoa p : pessoas) {
            if (p.getId() == id) {
                p.setSenha(novaSenha);
                p.setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }


}
