/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Controller;

public interface Autenticar {
    //recebe em 5 cruds para validar - Paciente, Medico, Responsavel, Dono e Func_Administraivo
    boolean autenticavel (String login, String senha);
}
