/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.util.Scanner;

public class ControllerFuncionarioAdm {
    
    Scanner scanner = new Scanner(System.in);
    
    private int recebeOpcaoUsuario() {

        StringBuilder builderAdm = new StringBuilder("");

        builderAdm.append("Funcionario_Administrativo\n\n");
        builderAdm.append("\n1 - Alterar informações do Perfil");
        builderAdm.append("\n2 - Verificar Consultas");
        builderAdm.append("\n3 - Registro de Consultas");
        builderAdm.append("\n4 - Verificar Procedimentos");
        builderAdm.append("\n5 - Registro de Procedimentos");
        builderAdm.append("\n6 - Para voltar à tela inicial\n");
        builderAdm.append("\nQual sua opção ? R: ");

        System.out.print(builderAdm.toString());

        return Integer.parseInt(scanner.nextLine());
    }

    
}
