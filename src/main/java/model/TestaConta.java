/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import model.Cliente;
import view.Login;
/**
 *
 * @author antonio
 */
public class TestaConta {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String senha = "admin";

       MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
       byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

       StringBuilder hexString = new StringBuilder();
       for (byte b : messageDigest) {
         hexString.append(String.format("%02X", 0xFF & b));
       }
       String senhahex = hexString.toString();

       System.out.println(senhahex);
       
       
        for (int i = 1; i <= 99; i++) {
            System.out.println(i);
        }
    }
}