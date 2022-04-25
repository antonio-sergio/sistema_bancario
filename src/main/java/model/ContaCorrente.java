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


/**
 *
 * @author antonio
 */
@Entity
public class ContaCorrente extends Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public ContaCorrente() {

    }

    public ContaCorrente(int numeroDaConta, Cliente cliente, String senha) {
        super(numeroDaConta, cliente, senha);
        super.setTipo("Corrente");
        super.setLimiteRotativo(500.);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void sacar(double valor) {
        if(valor <= super.getSaldo()){
            super.sacar(valor);
        }else if(super.getSaldo() > 0 && valor <= (super.getSaldo() + super.getLimiteRotativo())){
            double diferenca = valor - super.getSaldo();
            super.sacar(valor);
            super.sacarRotativo(diferenca);
        }else if(super.getSaldo() <= 0 && valor <= super.getLimiteRotativo()){
            super.sacarRotativo(valor);
            super.sacar(valor);
        }
    }

    @Override
    public void depositar(double valor) {
        if(super.getSaldo() >= 0){
            super.depositar(valor);
        }else{
            if(super.getSaldo() + valor <= 0){
                super.depositar(valor);
                super.depositarRotativo(valor);
            }else{
                super.depositar(valor);
                double diferenca = valor - super.getSaldo();
                super.depositarRotativo(diferenca);
            }
        }
    }

}
