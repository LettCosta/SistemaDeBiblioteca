package usuarios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author letic
 */
public abstract class Usuario {
    private String email;
    private String nome;
    private String senha;
    private String tipo;
    private static final String CSV_FILE = System.getProperty("user.dir")
    + "/src/main/java/bancoDeDados/usuarios.csv";

    public Usuario(String nome, String email, String senha, String tipo) {
        this.nome=nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
        public static Usuario validarLogin(String email, String senha) {
        String linha;
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            
            br.readLine();

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                String emailCSV = dados[1];
                String nomeCSV = dados[0];
                String senhaCSV = dados[2];
                String tipoUsuario = dados[3]; 

                System.out.println(dados[1]);
                System.out.println(dados[2]);
                
                if (emailCSV.equals(email) && senhaCSV.equals(senha)) {
                    if (tipoUsuario.equals("Cliente")) {
                        return new Cliente(1,nomeCSV, emailCSV, senhaCSV); 
                    } else if (tipoUsuario.equals("Admin")) {
                        return new Admin(1,nomeCSV, emailCSV, senhaCSV);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  
        }
        
        return null;  
    }
}