/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Uifm;

// Criar o menu do login e os menus de cada um dos 5 perfis

import java.util.Scanner;
import franquiamedica.PessoaDAO;
import franquiamedica.MedicoDAO;


public class UiMenus {
    Scanner scanner = new Scanner(System.in);
    PessoaDAO p = new PessoaDAO();
    MedicoDAO m = new MedicoDAO();
    
    public UiMenus() {
            
        //tela de login, senha, cadastrar nova pessoa
        int opcaoUsuario = 4;

        while (opcaoUsuario != 9) {
            opcaoUsuario = this.opcaoUsuarioTelaInicial();
            switch (opcaoUsuario) {
                case 0:                  
                    break;

                case 1:
                    break;
                    
                case 2:
                    break;
                    
                case 3:
                    break;
                    
                case 4:
                    break;
                    
                case 5:
                    break;
                    
                case 6:
                    break;    
            }
        }
        System.out.println("Saí do menu");
    }

    public static void main(String[] args) {
        new UiMenus();
    }
    
    //1ª tela - Início
    private int opcaoUsuarioTelaInicial() {

        StringBuilder builderInicio = new StringBuilder("");

        builderInicio.append("TELA INICIAL\n\n");
        builderInicio.append("\n0 - Logar");
        builderInicio.append("\n1 - Cadastrar");
        builderInicio.append("\nQual sua opção ? R: ");
        
        System.out.print(builderInicio.toString());

        return Integer.parseInt(scanner.nextLine());
    }
    
    //2ª tela - Login
    private long opcaoUsuarioTelaLogin() {

        StringBuilder builderLogin = new StringBuilder("");

        builderLogin.append("TELA LOGIN\n\n");
        builderLogin.append("\nInsira o login: ");
        builderLogin.append("\nInsira a senha: ");
        builderLogin.append("\nQual seu perfil");
        
                scanner.nextLine();
        
        Long id = Long.parseLong(scanner.nextLine());
        
        System.out.print(builderLogin.toString());

        return Long.parseLong(scanner.nextLine());
    }

    private int recebeOpcaoUsuario() {

        StringBuilder builderAdm = new StringBuilder("");

        builderAdm.append("ADMNISTRADOR\n\n");
        builderAdm.append("\n0 - Para inserir pessoa");
        builderAdm.append("\n1 - Listar pessoas");
        builderAdm.append("\n2 - Excluir um pessoa");
        builderAdm.append("\n3 - Alterar um pessoa");
        builderAdm.append("\n4 - Buscar salários > 5000");
        builderAdm.append("\n9 - Para sair do programa\n");
        builderAdm.append("\nQual sua opção ? R: ");
        
        System.out.print(builderAdm.toString());

        return Integer.parseInt(scanner.nextLine());
    }

}
