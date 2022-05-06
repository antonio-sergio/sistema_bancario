/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import javax.swing.JOptionPane;

/**
 *
 * @author antonio
 */
@Entity
public class ContaPoupanca extends Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  double limiteSaldo = 5000;

    public ContaPoupanca() {
    }

    public ContaPoupanca(int numeroDaConta, Cliente cliente, String senha) {
        super(numeroDaConta, cliente, senha);
        super.setTipo("Poupança");
        super.setSaldo(0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public  double getLimiteSaldo() {
        return limiteSaldo;
    }

    public  void setLimiteSaldo(double limiteSaldo) {
        this.limiteSaldo = limiteSaldo;
    }

    public void sacar(double valor) {

        if (valor <= super.getSaldo()) {
            super.sacar(valor);

        } else {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente! Seu saldo é: " + super.getSaldo());
        }
    }

    public void depositar(double valor){
        if(valor + super.getSaldo() <= limiteSaldo){
            super.depositar(valor);

        }else
            JOptionPane.showMessageDialog(null, "Operação não pôde ser realizada pois ultrapassa o limite de saldo da conta! O limite deve ser de até " + this.limiteSaldo);
        
        
    }
    
    public void transferir(int nmroContaDestino, double valor){
        super.transferir(nmroContaDestino, valor);
    }
    
    
}
