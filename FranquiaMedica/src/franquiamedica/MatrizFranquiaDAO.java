/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

import java.time.LocalDateTime;


public class MatrizFranquiaDAO {

    MatrizFranquia[] matrizes = new MatrizFranquia[20];

    public MatrizFranquiaDAO(PessoaDAO pessoadao) {
        //LEMBRAR DE TROOOCAAARR ESSAS PESSOAS SÃO NOVAS E TEM QUE SER UMA PESSOA CADASTRADA
        Pessoa p1 = new Pessoa();
        p1.setNome("MFjosephina");
        p1.setEndereco("MFjose");
        p1.setCpf("MFjose");
        p1.setTelefone("MFjosephina");
        p1.setLogin("MFjose");
        p1.setSenha("MFjose");
        pessoadao.adiciona(p1);

        Pessoa p2 = new Pessoa();
        p2.setNome("MFjaspion");
        p2.setEndereco("MFjas");
        p2.setCpf("MFjas");
        p2.setTelefone("MFjaspion");
        p2.setLogin("MFjas");
        p2.setSenha("MFjas");
        pessoadao.adiciona(p2);

        Pessoa p3 = new Pessoa();
        p3.setNome("MFjiraia");
        p3.setEndereco("MFir");
        p3.setCpf("MFjir");
        p3.setTelefone("MFjiraia");
        p3.setLogin("MFjir");
        p3.setSenha("MFjir");
        pessoadao.adiciona(p3);

        MatrizFranquia m1 = new MatrizFranquia(p1);
        m1.setNome("UnidadeA");
        m1.setCnpj("111.456.789.-0001");
        m1.setEndereco("123oftalmo");
        m1.setCidade("Araxa");
        adiciona(m1);

        MatrizFranquia m2 = new MatrizFranquia(p2);
        m2.setNome("UnidadeB");
        m2.setCnpj("222.456.789.-0001");
        m2.setEndereco("123oftalmo");
        m2.setCidade("Araxa");
        adiciona(m2);

        MatrizFranquia m3 = new MatrizFranquia(p3);
        m3.setNome("UnidadeC");
        m3.setCnpj("333.456.789.-0001");
        m3.setEndereco("123oftalmo");
        m3.setCidade("Araxa");
        adiciona(m3);

    }

    private int proximaPosicaoLivre() {//só serve aqui dentro
        for (int i = 0; i < matrizes.length; i++) {
            MatrizFranquia m = matrizes[i];
            if (m == null) {
                return i;
            }
        }
        return -1;//sinonimo que nao tem posição livre
    }

    public boolean adiciona(MatrizFranquia m) {
        int x = proximaPosicaoLivre();
        if (x != -1) {
            matrizes[x] = m;
            return true;
        } else {
            return false;
        }
    }

    public void mostraTodos() {
        for (MatrizFranquia m : matrizes) {
            if (m != null) {
                System.out.println(m);
            }
        }
    }

    public MatrizFranquia verificaRegistro(long id) {
        for (MatrizFranquia matriz : matrizes) {
            if (matriz.getId() == id) {
                return matriz;
            }
        }
        return null;
    }

    public boolean remove(String nome) {
        for (int i = 0; i < matrizes.length; i++) {
            if (matrizes[i] != null && matrizes[i].getNome().equals(nome)) {
                matrizes[i] = null;
                return true;
            }
        }
        return false;
    }

    /**
     * Todas os updates precisam de modify data
     */
    public boolean alterarNome(long idMatriz, String novoNome) {
        for (MatrizFranquia matriz : matrizes) {
            if (matriz.getId() == idMatriz) {
                matriz.setNome(novoNome);
                matriz.setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarCnpj(long idMatriz, String novoCnpj) {
        for (MatrizFranquia matriz : matrizes) {
            if (matriz.getId() == idMatriz) {
                matriz.setCnpj(novoCnpj);
                matriz.setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarEndereco(long idMatriz, String novoEndereco) {
        for (MatrizFranquia matriz : matrizes) {
            if (matriz.getId() == idMatriz) {
                matriz.setEndereco(novoEndereco);
                matriz.setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarCidade(long idMatriz, String novoCidade) {
        for (MatrizFranquia matriz : matrizes) {
            if (matriz.getId() == idMatriz) {
                matriz.setCidade(novoCidade);
                matriz.setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarLogin(long idMatriz, String novoLogin) {
        for (MatrizFranquia matriz : matrizes) {
            if (matriz.getId() == idMatriz) {
                matriz.getDono().setLogin(novoLogin);
                matriz.getDono().setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarSenha(long idMatriz, String novaSenha) {
        for (MatrizFranquia matriz : matrizes) {
            if (matriz.getId() == idMatriz) {
                matriz.getDono().setSenha(novaSenha);
                matriz.getDono().setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alteraDonoNome(long idMatriz, String novoNome) {
        for (MatrizFranquia matriz : matrizes) {
            if (matriz.getId() == idMatriz) {
                matriz.getDono().setNome(novoNome);
                matriz.getDono().setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarDonoEndereco(long idMatriz, String novaEndereco) {
        for (MatrizFranquia matriz : matrizes) {
            if (matriz.getId() == idMatriz) {
                matriz.getDono().setEndereco(novaEndereco);
                matriz.getDono().setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarDonoCpf(long idMatriz, String novoCpf) {
        for (MatrizFranquia matriz : matrizes) {
            if (matriz.getId() == idMatriz) {
                matriz.getDono().setCpf(novoCpf);
                matriz.getDono().setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarDonoTelefone(long idMatriz, String novoTelefone) {
        for (MatrizFranquia matriz : matrizes) {
            if (matriz.getId() == idMatriz) {
                matriz.getDono().setTelefone(novoTelefone);
                matriz.getDono().setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

}
