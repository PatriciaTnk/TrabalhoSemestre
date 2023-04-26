/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package franquiamedica;

public class ProcedimentoDAO {
    
    Procedimento[] proceds = new Procedimento[50];
    
    public ProcedimentoDAO (ConsultaDAO consultadao){
        Procedimento novo = new Procedimento(consultadao.verificaRegistro(Long.parseLong("0")));
        novo.setLaudo("Identificado manchas brancas no pulmao");
        adiciona(novo);
                
        Procedimento novo1 = new Procedimento(consultadao.verificaRegistro(Long.parseLong("1")));
        novo1.setLaudo("Encontrado mais plaquetas que o ideal");
        adiciona(novo1);
        
        Procedimento novo2 = new Procedimento(consultadao.verificaRegistro(Long.parseLong("2")));
        novo2.setLaudo("Paciente ainda nao realizou exame");
        adiciona(novo2);

    }    
    
    private int proximaPosicaoLivre() {//só serve aqui dentro
        for (int i = 0; i < proceds.length; i++) {
            Procedimento p = proceds[i];
            if (p == null) {
                return i;
            }
        }
        return -1;//sinonimo que nao tem posição livre
    }

    public boolean adiciona(Procedimento p) {
        int x = proximaPosicaoLivre();
        if (x != -1) {
            proceds[x] = p;
            return true;
        } else {
            return false;
        }
    }

    public void mostraTodos() {
        for (Procedimento p : proceds) {
            if (p != null) {
                System.out.println(p);
            }
        }
    }

    /**
     * A Procedimento verifica registro de acordo com o nome da Matriz
     *
     * @param idProcedimento
     * @return
     */
    public Procedimento verificaRegistro(long idProcedimento) {
        for (Procedimento p : proceds) {
            if (p.getId() == idProcedimento) {
                return p;
            }
        }
        return null;
    }
}

