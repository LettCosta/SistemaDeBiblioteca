/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auxiliares;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author letic
 */
public class LivroCSV {
    private static final String CSV_FILE = System.getProperty("user.dir")
    + "/src/main/java/bancoDeDados/livros.csv";
    
    
    public int contarTotalLivros() {
        int total = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                total++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return total;
    }
    
    public int contarEmprestimos() {
        int emprestimos = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(",");
                if (campos.length > 5 && campos[5].trim().equalsIgnoreCase("true")) {
                    emprestimos++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return emprestimos;
    }
  
    public static List<Livro> lerLivrosDoCSV() throws IOException {
        List<Livro> livros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(",");
                Livro livro = new Livro(
                    Integer.parseInt(campos[0]), 
                    campos[1], 
                    campos[2], 
                    campos[3], 
                    Integer.parseInt(campos[4])
                );
                livro.setEmprestado(Boolean.parseBoolean(campos[5]));
                livros.add(livro);
            }
        }
        return livros;
    }

    public static void salvarLivrosNoCSV(List<Livro> livros) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE))) {
            for (Livro livro : livros) {
                bw.write(String.format("%d,%s,%s,%s,%d,%b",
                    livro.getId(), livro.getTitulo(), livro.getAutor(),
                    livro.getCategoria(), livro.getAno(), livro.isEmprestado()
                ));
                bw.newLine();
            }
        }
    }
}
