/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author antonio
 */
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import model.Conta;

import model.ContaCorrente;
import model.ContaPoupanca;

public class UsuarioDAO {

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("banco");
    private EntityManager em = factory.createEntityManager();

    public ContaPoupanca getUsuarioPoupanca(int numeroDaConta, String senha) {
    

        try {
            ContaPoupanca conta = (ContaPoupanca) em.createQuery("SELECT c from Conta c where c.numeroDaConta =:numeroDaConta and c.senha = :senha")
                    .setParameter("numeroDaConta", numeroDaConta)
                    .setParameter("senha", senha)
                    .getSingleResult();
            this.fecharConexao();
            return conta;
        } catch (NoResultException e) {
            System.out.println("Conta não encontrada" + e);

            return null;
        }
    }

    public ContaCorrente getUsuarioCorrente(int numeroDaConta, String senha) {

        try {
            ContaCorrente conta = (ContaCorrente) em.createQuery("SELECT c from Conta c where c.numeroDaConta =:numeroDaConta and c.senha = :senha")
                    .setParameter("numeroDaConta", numeroDaConta)
                    .setParameter("senha", senha)
                    .getSingleResult();
            this.fecharConexao();
            return conta;
        } catch (NoResultException e) {
            System.out.println("Conta não encontrada" + e);
            return null;
        }
    }
    
    public ContaPoupanca getUsuarioPorContaPoupanca(int numeroDaConta) {
    

        try {
            ContaPoupanca conta = (ContaPoupanca) em.createQuery("SELECT c from Conta c where c.numeroDaConta =:numeroDaConta")
                    .setParameter("numeroDaConta", numeroDaConta)
                    .getSingleResult();
            this.fecharConexao();
            return conta;
        } catch (NoResultException e) {
            System.out.println("Conta não encontrada" + e);

            return null;
        }
    }
    
    public ContaCorrente getUsuarioPorContaCorrente(int numeroDaConta) {

        try {
            ContaCorrente conta = (ContaCorrente) em.createQuery("SELECT c from Conta c where c.numeroDaConta =:numeroDaConta")
                    .setParameter("numeroDaConta", numeroDaConta)
                    .getSingleResult();
            this.fecharConexao();
            return conta;
        } catch (NoResultException e) {
            System.out.println("Conta não encontrada" + e);
            return null;
        }
    }
    
    public Conta getDestino(int numeroDaConta) {

        try {
            Conta conta = (Conta) em.createQuery("SELECT c from Conta c where c.numeroDaConta =:numeroDaConta")
                    .setParameter("numeroDaConta", numeroDaConta)
                    .getSingleResult();
            this.fecharConexao();
            return conta;
        } catch (NoResultException e) {
            System.out.println("Conta não encontrada" + e);
            return null;
        }
    }


    public void fecharConexao() {
        em.close();
    }
}
