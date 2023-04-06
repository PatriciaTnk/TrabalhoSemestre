/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

import java.time.LocalDateTime;

public class MedicoDAO {
//adicionar, mostrar, conferir se tem registro, deletar e atualizar.
    //para já ter dados cadastrados, popular dados no construtor

    //preciso de um array para popular
    Medico[] medicos = new Medico[30];

    public MedicoDAO(PessoaDAO pessoadao) {
        //LEMBRAR DE TROOOCAAARR ESSAS PESSOAS SÃO NOVAS E TEM QUE SER UMA PESSOA CADASTRADA
        Pessoa p1 = new Pessoa();
        p1.setNome("Mjosephina");
        p1.setEndereco("Mjose");
        p1.setCpf("Mjose");
        p1.setTelefone("Mjosephina");
        p1.setLogin("Mjose");
        p1.setSenha("Mjose");
        pessoadao.adiciona(p1);

        Pessoa p2 = new Pessoa();
        p2.setNome("Mjaspion");
        p2.setEndereco("Mjas");
        p2.setCpf("Mjas");
        p2.setTelefone("Mjaspion");
        p2.setLogin("Mjas");
        p2.setSenha("Mjas");
        pessoadao.adiciona(p2);


        Pessoa p3 = new Pessoa();
        p3.setNome("Mjiraia");
        p3.setEndereco("Mir");
        p3.setCpf("Mjir");
        p3.setTelefone("Mjiraia");
        p3.setLogin("Mjir");
        p3.setSenha("Mjir");
        pessoadao.adiciona(p3);

        
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

    public Medico verificaRegistro(long id) {
        for (Medico medico : medicos) {          
            if (medico.getId() == id) {            
                return medico;
            }
        }
        return null;
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
    public boolean alterarNome(long idMedico, String novoNome) {
        for (Medico medico : medicos) {
            if (medico.getId() == idMedico) {
                medico.getPessoa().setNome(novoNome);
                medico.getPessoa().setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarEndereco(long idMedico, String novoEndereco) {
        for (Medico medico : medicos) {
            if (medico.getId() == idMedico) {
                medico.getPessoa().setEndereco(novoEndereco);
                medico.getPessoa().setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarCpf(long idMedico, String novoCpf) {
        for (Medico medico : medicos) {
            if (medico.getId() == idMedico) {
                medico.getPessoa().setCpf(novoCpf);
                medico.getPessoa().setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarTelefone(long idMedico, String novoTelefone) {
        for (Medico medico : medicos) {
            if (medico.getId() == idMedico) {
                medico.getPessoa().setTelefone(novoTelefone);
                medico.getPessoa().setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarLogin(long idMedico, String novoLogin) {
        for (Medico medico : medicos) {
            if (medico.getId() == idMedico) {
                medico.getPessoa().setLogin(novoLogin);
                medico.getPessoa().setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarSenha(long idMedico, String novaSenha) {
        for (Medico medico : medicos) {
            if (medico.getId() == idMedico) {
                medico.getPessoa().setSenha(novaSenha);
                medico.getPessoa().setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarEspecialidade(long idMedico, String novaEspecialidade) {
        for (Medico medico : medicos) {
            if (medico.getId() == idMedico) {
                medico.setEspecialidade(novaEspecialidade);
                medico.setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarCrm(long idMedico, String novoCrm) {
        for (Medico medico : medicos) {
            if (medico.getId() == idMedico) {
                medico.setCrm(novoCrm);
                medico.setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

}
