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
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nmro_celular;
    private String nmro_telefone;
    private String email;
    private String telefone_recado;
    private String nome_contato_recado;

    public Contato() {
    }

    public Contato(String nmro_celular, String nmro_telefone, String email, String telefone_recado, String nome_contato_recado) {
        this.nmro_celular = nmro_celular;
        this.nmro_telefone = nmro_telefone;
        this.email = email;
        this.telefone_recado = telefone_recado;
        this.nome_contato_recado = nome_contato_recado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmro_celular() {
        return nmro_celular;
    }

    public void setNmro_celular(String nmro_celular) {
        this.nmro_celular = nmro_celular;
    }

    public String getNmro_telefone() {
        return nmro_telefone;
    }

    public void setNmro_telefone(String nmro_telefone) {
        this.nmro_telefone = nmro_telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone_recado() {
        return telefone_recado;
    }

    public void setTelefone_recado(String telefone_recado) {
        this.telefone_recado = telefone_recado;
    }

    public String getNome_contato_recado() {
        return nome_contato_recado;
    }

    public void setNome_contato_recado(String nome_contato_recado) {
        this.nome_contato_recado = nome_contato_recado;
    }
    
    
}
