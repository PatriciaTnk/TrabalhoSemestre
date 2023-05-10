/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

import java.time.LocalDateTime;

public class FranquiaDAO {

    Franquia[] franquias = new Franquia[20];

    public FranquiaDAO(PessoaDAO pessoadao, MatrizFranquiaDAO matrizfranquiadao) {
        //LEMBRAR DE TROOOCAAARR ESSAS PESSOAS SÃO NOVAS E TEM QUE SER UMA PESSOA CADASTRADA
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

        MatrizFranquia m1 = new MatrizFranquia(pMF1);
        m1.setNome("Unidade A");
        m1.setCnpj("111.456.789.-0001");
        m1.setEndereco("123oftalmo");
        m1.setCidade("Araxa");
        matrizfranquiadao.adiciona(m1);

        MatrizFranquia m2 = new MatrizFranquia(pMF2);
        m2.setNome("Unidade B");
        m2.setCnpj("222.456.789.-0001");
        m2.setEndereco("123oftalmo");
        m2.setCidade("Araxa");
        matrizfranquiadao.adiciona(m2);

        MatrizFranquia m3 = new MatrizFranquia(pMF3);
        m3.setNome("Unidade C");
        m3.setCnpj("333.456.789.-0001");
        m3.setEndereco("123oftalmo");
        m3.setCidade("Araxa");
        matrizfranquiadao.adiciona(m3);

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
            if (f != null && f.getVisible() == true) {
                System.out.println(f);
            }
        }
    }

    /**
     * A Franquia verifica registro de acordo com o nome da Matriz
     *
     * @param id
     * @return
     */
    public Franquia verificaRegistro(long id) {
        for (Franquia f : franquias) {
            if (f != null && f.getId() == id && f.getVisible()) {
                return f;
            }
        }
        return null;
    }

    public boolean remove(long idFranquia) {
        for (Franquia f : franquias) {
            if (idFranquia == f.getId()) {
                f.notVisible(true);
                return true;
            }
        }
        return false;
    }

    /**
     * Todas os updates precisam de modify data
     *
     * @param idFranquia
     * @param novoEndereco
     * @return
     */
    public boolean alterarEndereco(long idFranquia, String novoEndereco) {
        for (Franquia f : franquias) {
            if (f.getId() == idFranquia) {
                f.setEndereco(novoEndereco);
                f.setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean alterarCidade(long idFranquia, String novoCidade) {
        for (Franquia f : franquias) {
            if (f.getId() == idFranquia) {
                f.setCidade(novoCidade);
                f.setDatamodificacao(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public Pessoa getResponsavel(long idFranquia) {
        if (verificaRegistro(idFranquia) != null) {
            return verificaRegistro(idFranquia).getResponsavel();
        } else {
            return null;
        }
    }

    public String getMatriz(long idFranquia) {
        if (verificaRegistro(idFranquia) == null){
            return "Matriz nao encontrada";
        }
            return verificaRegistro(idFranquia).getFranquia().toString();
    }
    
}
