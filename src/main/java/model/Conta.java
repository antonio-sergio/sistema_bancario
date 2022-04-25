/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.DAO;
import controller.UsuarioDAO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import javax.swing.JOptionPane;

/**
 *
 * @author antonio
 */
@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int agencia = 2022;
    @Column(unique=true)
    private int numeroDaConta;
    private String tipo;
    private String senha;
    private double saldo;
    private double limiteRotativo;
    private int parametroLimiteRotativo = 500;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Cliente cliente;

    public Conta() {
    }

    public Conta(int numeroDaConta, Cliente cliente, String senha) {
        this.numeroDaConta = numeroDaConta;
        this.cliente = cliente;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getNumeroDaConta() {
        return numeroDaConta;
    }

    public void setNumeroDaConta(int numeroDaConta) {
        this.numeroDaConta = numeroDaConta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getLimiteRotativo() {
        return limiteRotativo;
    }

    public void setLimiteRotativo(double limiteRotativo) {
        this.limiteRotativo = limiteRotativo;
    }

    public int getParametroLimiteRotativo() {
        return parametroLimiteRotativo;
    }

    public void sacar(double valor) {
        DAO<Conta> dao = new DAO<>(Conta.class);
        this.saldo -= valor;
        this.setSaldo(saldo);
        dao.atualizar(this);
        JOptionPane.showMessageDialog(null, "Saque efetuado no valor de: " + valor);

        return;
    }

    public void sacarRotativo(double valor) {
        DAO<Conta> dao = new DAO<>(Conta.class);
        this.limiteRotativo -= valor;
        this.setLimiteRotativo(limiteRotativo);
        dao.atualizar(this);
        JOptionPane.showMessageDialog(null, "Limite cheque especial utilizado no valor de: " + valor);

    }

    public void depositar(double valor) {
        DAO<Conta> dao = new DAO<>(Conta.class);
        this.saldo += valor;
        this.setSaldo(saldo);
        dao.atualizar(this);
        JOptionPane.showMessageDialog(null, "Deposito efetuado no valor de: " + valor);

    }

    public void depositarRotativo(double valor) {
        DAO<Conta> dao = new DAO<>(Conta.class);
        this.limiteRotativo += valor;
        this.setLimiteRotativo(limiteRotativo);
        dao.atualizar(this);
        JOptionPane.showMessageDialog(null, "Pagamento cheque especial efetuado no valor de: " + valor);

    }

    public void transferir(int nmroContaDestino, double valor) {
        DAO<Conta> dao = new DAO<>(Conta.class);
        UsuarioDAO ud = new UsuarioDAO();
        try {
            ContaPoupanca contaDestino = ud.getUsuarioPorContaPoupanca(nmroContaDestino);
            double saldoRemetente = this.saldo;
            double saldoDestinatario = contaDestino.getSaldo();
            if (this.getSaldo() >= valor) {
                if (contaDestino.getSaldo() + valor <= contaDestino.getLimiteSaldo()) {
                    this.setSaldo(saldoRemetente - valor);
                    contaDestino.setSaldo(saldoDestinatario + valor);
                    dao.atualizar(this);
                    JOptionPane.showMessageDialog(null, "Transferido " + valor + " para " + contaDestino.getCliente().getNome());
                } else {
                    JOptionPane.showMessageDialog(null, "Operação não realizada pois excede o limite da conta do destinatário");

                }
            } else {
                JOptionPane.showMessageDialog(null, "Saldo insuficiente! Seu saldo é " + this.saldo);
            }

        } catch (Exception e) {
        }
        try {
            ContaCorrente contaDestino = ud.getUsuarioPorContaCorrente(nmroContaDestino);
            double saldoRemetente = this.saldo;
            double saldoDestinatario = contaDestino.getSaldo();
            if (valor <= this.saldo) {
                this.setSaldo(saldoRemetente - valor);
                contaDestino.setSaldo(saldoDestinatario + valor);
                dao.atualizar(this);
                JOptionPane.showMessageDialog(null, "Transferido " + valor + " para " + contaDestino.getCliente().getNome());

            } else {
                JOptionPane.showMessageDialog(null, "Saldo insuficiente! Seu saldo é " + this.saldo);
            }

        } catch (Exception e) {
        }

    }


}
