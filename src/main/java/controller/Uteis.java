/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.ContaCorrente;
import model.ContaPoupanca;

/**
 *
 * @author antonio
 */
public class Uteis {
    public static ContaPoupanca instanciaPoupanca(int nmroConta, String senha){
        ContaPoupanca conta = new ContaPoupanca();
        UsuarioDAO ud = new UsuarioDAO();
        conta = ud.getUsuarioPoupanca(nmroConta, senha);
        ud.fecharConexao();
        return conta;
    }
    
    public static ContaCorrente instanciaCorrente(int nmroConta, String senha){
        ContaCorrente conta = new ContaCorrente();
        UsuarioDAO ud = new UsuarioDAO();
        conta = ud.getUsuarioCorrente(nmroConta, senha);
        ud.fecharConexao();
        return conta;
    }
    
    
    
}
