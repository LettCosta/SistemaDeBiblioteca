package usuarios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author letic
 */
public class UsuarioCSV {
       private String caminhoArquivo;

    public UsuarioCSV(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public boolean autenticar(String email, String senha) {
        List<Usuario> usuarios = listar(); 
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    public void salvar(Usuario usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            writer.write(usuario.toString());  
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                String nome= dados[0];
                String email = dados[1];
                String senha = dados[2];
                String tipo = dados[3];

                if (tipo.equals("Cliente")) {
                    usuarios.add(new Cliente(1, tipo, email, senha));
                } else if (tipo.equals("Admin")) {
                    usuarios.add(new Admin(1, nome,email, senha)); 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public Usuario buscarPorEmail(String email) {
        List<Usuario> usuarios = listar();
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                return usuario;
            }
        }
        return null; 
    }
}