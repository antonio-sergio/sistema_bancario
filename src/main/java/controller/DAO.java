/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import model.Cliente;

/**
 *
 * @author antonio
 */
public class DAO <E>{
    private static EntityManager em;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("banco");
    private Class<E> classe;
    

    public DAO(){
        this(null);
    }

    public DAO(Class<E> classe){
        this.classe = classe;
        em = emf.createEntityManager();
    }
    
    public void fecharConexao(){
        em.close();
    }
    
    public DAO<E> abrirTransacao(){
        em.getTransaction().begin();
        return this;
    }
    
    public DAO<E> fecharTransacao(){
        em.getTransaction().commit();
        return this;
    }
    
    public DAO<E> persistirDados(E entidade){
        em.persist(entidade);
        
        return this;
    }
    
    public DAO<E> atualizar(E entidade){
        this.abrirTransacao();
        em.merge(entidade);
        this.fecharTransacao();
        return this;
    }
    

    
    public DAO<E> persistirAtomico(E entidade){
        
        return this.abrirTransacao().persistirDados(entidade).fecharTransacao();
    }
    
    public  E obterPorId(Object id){
        return em.find(classe, id);
    }
    
    public E obterPorNmroConta(Object nmr_conta){
        return em.find(classe, nmr_conta);
    }
    
    public boolean temEsteCpf(String cpf){
        Query query = em.createQuery("SELECT u From Usuario AS u WHERE u.cpf = ?1 ", Cliente.class);
        query.setParameter(1, cpf);
        Cliente usuario = (Cliente)query.getSingleResult();
        if(usuario != null){
            return true;
        }
        return false;
    }
    
    
}
