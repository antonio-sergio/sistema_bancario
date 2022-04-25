/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

/**
 *
 * @author antonio
 */

@Entity
//@Table(uniqueConstraints={@UniqueConstraint(columnNames={"cpf"})})
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    
    
    private String cpf;
    
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Endereco endereco;
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Contato contato;

    public Cliente() {
    }

    public Cliente(String nome, String cpf, Endereco endereco, Contato contato) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.contato = contato;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    

    public String getCpf() {
        return cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Contato getContato() {
        return contato;
    }

    
    
  
    
}
