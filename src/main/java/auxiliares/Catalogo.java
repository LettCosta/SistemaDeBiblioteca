package auxiliares;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import usuarios.Cliente;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author letic
 */
public class Catalogo {
    private static final String CSV_FILE = "livros.csv";
    
    public List<Livro> listarLivros(){
        List<Livro> livros = new ArrayList<>();
        try(CSVReader reader = new CSVReader(new FileReader(CSV_FILE))){
            String[] linha;
            reader.readNext();
            while ((linha = reader.readNext()) != null) {
                int id = Integer.parseInt(linha[0]);
                String titulo = linha[1];
                String autor = linha[2];
                String categoria = linha[3];
                int ano = Integer.parseInt(linha[4]);
                livros.add(new Livro(id, titulo, autor, categoria, ano));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return livros;
    }
    
    public void emprestarLivro(Cliente cliente, Livro livro) {
        if (!livro.isEmprestado()) {
            cliente.pegarLivroEmprestado(livro, new java.util.Date());
            livro.setEmprestado(true);
            System.out.println("Livro " + livro.getTitulo() + " emprestado com sucesso.");
        } else {
            System.out.println("O livro " + livro.getTitulo() + " já foi emprestado.");
        }
    }
}
