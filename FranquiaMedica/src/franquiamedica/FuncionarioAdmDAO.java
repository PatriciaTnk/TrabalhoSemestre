/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

public class FuncionarioAdmDAO {

    FuncionarioAdm[] func_adm = new FuncionarioAdm[25];

    public FuncionarioAdmDAO(FranquiaDAO franquiadao, PessoaDAO pessoadao) {
        FuncionarioAdm novo = new FuncionarioAdm(pessoadao.pessoas[2]);
        novo.setIdFranquia(franquiadao.franquias[0].getId());
        adiciona(novo);

        FuncionarioAdm novo1 = new FuncionarioAdm(pessoadao.pessoas[3]);
        novo1.setIdFranquia(franquiadao.franquias[1].getId());
        adiciona(novo1);

        FuncionarioAdm novo2 = new FuncionarioAdm(pessoadao.pessoas[4]);
        novo2.setIdFranquia(franquiadao.franquias[2].getId());
        adiciona(novo2);

    }

    private int proximaPosicaoLivre() {//só serve aqui dentro
        for (int i = 0; i < func_adm.length; i++) {
            FuncionarioAdm fa = func_adm[i];
            if (fa == null) {
                return i;
            }
        }
        return -1;//sinonimo que nao tem posição livre
    }

    public boolean adiciona(FuncionarioAdm fa) {
        int x = proximaPosicaoLivre();
        if (x != -1) {
            func_adm[x] = fa;
            return true;
        } else {
            return false;
        }
    }

    public void mostraTodos() {
        for (FuncionarioAdm fa : func_adm) {
            if (fa != null && fa.getVisible() == true) {
                System.out.println(fa);
            }
        }
    }

    public FuncionarioAdm verificaRegistro(long id) {
        for (FuncionarioAdm fa : func_adm) {
            if (fa.getId() == id && fa.getVisible()) {
                return fa;
            }
        }
        return null;
    }

    public boolean remove(long idRemover) {
        for (int i = 0; i < func_adm.length; i++) {
            if (func_adm[i] != null && func_adm[i].getId() == idRemover) {
                func_adm[i].notVisible(true);
                func_adm[i] = null;
                return true;
            }
        }
        return false;
    }

}
