/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

import java.time.LocalDateTime;

/**
 *
 * @author miyuk
 */
public class MedicoDAO {
//adicionar, mostrar, conferir se tem registro, deletar e atualizar.
    //para já ter dados cadastrados, popular dados no construtor

    //preciso de um array para popular
    Medico[] medicos = new Medico[15];

    public MedicoDAO() {
        Pessoa p1 = new Pessoa();
        p1.setNome("josephina");
        p1.setEndereco("jose");
        p1.setCpf("jose");
        p1.setTelefone("josephina");
        p1.setLogin("jose");
        p1.setSenha("jose");


        Pessoa p2 = new Pessoa();
        p2.setNome("jaspion");
        p2.setEndereco("jas");
        p2.setCpf("jas");
        p2.setTelefone("jaspion");
        p2.setLogin("jas");
        p2.setSenha("jas");


        Pessoa p3 = new Pessoa();
        p3.setNome("jiraia");
        p3.setEndereco("jir");
        p3.setCpf("jir");
        p3.setTelefone("jiraia");
        p3.setLogin("jir");
        p3.setSenha("jir");

        
        Medico m1 = new Medico(p1);
        m1.setEspecialidade("oftalmologista");
        m1.setCrm("123oftalmo");
        adiciona(m1);

        Medico m2 = new Medico(p2);
        m2.setEspecialidade("oncologista");
        m2.setCrm("123onco");
        adiciona(m2);

        Medico m3 = new Medico(p3);
        m3.setEspecialidade("cardiologista");
        m3.setCrm("123cardio");
        adiciona(m3);

    }

    private int proximaPosicaoLivre() {//só serve aqui dentro
        for (int i = 0; i < medicos.length; i++) {
            Medico m = medicos[i];
            if (m == null) {
                return i;
            }
        }
        return -1;//sinonimo que nao tem posição livre
    }

    public boolean adiciona(Medico m) {
        int x = proximaPosicaoLivre();
        if (x != -1) {
            medicos[x] = m;
            return true;
        } else {
            return false;
        }
    }

    public void mostraTodos() {
        for (Medico m : medicos) {
            if (m != null) {
                System.out.println(m);
            }
        }
    }

    public boolean temRegistro(String nome) {
        for (Medico medico : medicos) {          
            if (medico.getPessoa().getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    public boolean remove(String nome) {
        for (int i = 0; i < medicos.length; i++) {
            if (medicos[i] != null && medicos[i].getPessoa().getNome().equals(nome)) {
                medicos[i] = null;
                return true;
            }
        }
        return false;
    }

    /**
     * Todas os updates precisam de modify data
     */
    public boolean alterarNome(String nome, String novoNome) {
        for (Medico medico : medicos) {
            if (medico != null && medico.getPessoa().getNome().equals(nome)) {
                medico.getPessoa().setNome(novoNome);
                medico.setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarEndereco(String endereco, String novoEndereco) {
        for (Medico medico : medicos) {
            if (medico != null && medico.getPessoa().getEndereco().equals(endereco)) {
                medico.getPessoa().setEndereco(novoEndereco);
                medico.setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarCpf(String cpf, String novoCpf) {
        for (Medico medico : medicos) {
            if (medico != null && medico.getPessoa().getCpf().equals(cpf)) {
                medico.getPessoa().setCpf(novoCpf);
                medico.setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarTelefone(String telefone, String novoTelefone) {
        for (Medico medico : medicos) {
            if (medico != null && medico.getPessoa().getTelefone().equals(telefone)) {
                medico.getPessoa().setTelefone(novoTelefone);
                medico.setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarLogin(String login, String novoLogin) {
        for (Medico medico : medicos) {
            if (medico != null && medico.getPessoa().getLogin().equals(login)) {
                medico.getPessoa().setLogin(novoLogin);
                medico.setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarSenha(String senha, String novaSenha) {
        for (Medico medico : medicos) {
            if (medico != null && medico.getPessoa().getSenha().equals(senha)) {
                medico.getPessoa().setSenha(novaSenha);
                medico.setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarEspecialidade(String especialidade, String novaEspecialidade) {
        for (Medico medico : medicos) {
            if (medico != null && medico.getEspecialidade().equals(especialidade)) {
                medico.setEspecialidade(novaEspecialidade);
                medico.setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarCrm(String crm, String novoCrm) {
        for (Medico medico : medicos) {
            if (medico != null && medico.getCrm().equals(crm)) {
                medico.setCrm(novoCrm);
                medico.setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

}
