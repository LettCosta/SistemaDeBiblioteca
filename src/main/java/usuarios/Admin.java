package usuarios;

import auxiliares.Emprestimo;
import auxiliares.Livro;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author letic
 */
public class Admin extends Usuario {
    private String nome;
    private int id;
    private List<Emprestimo> emprestimos;
    private List<Livro> livros;

    public Admin(int id, String nome, String email, String senha) {
        super(nome, email, senha, "admin");
        this.id = id;
        this.emprestimos = new ArrayList<>();
        this.livros = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
        System.out.println("Livro " + livro.getTitulo() + " adicionado ao catálogo.");
    }

    public void removerLivro(Livro livro) {
        if (livros.contains(livro)) {
            livros.remove(livro);
            System.out.println("Livro " + livro.getTitulo() + " removido do catálogo.");
        } else {
            System.out.println("Livro não encontrado no catálogo.");
        }
    }

    public void gerarEmprestimo(Cliente cliente, Livro livro, Date dataEmprestimo) {
        Emprestimo emprestimo = new Emprestimo(cliente, livro, dataEmprestimo);
        emprestimos.add(emprestimo);
        cliente.pegarLivroEmprestado(livro, dataEmprestimo);
        System.out.println("Empréstimo gerado para o cliente " + cliente.getNome() + " com o livro " + livro.getTitulo());
    }

    public void listarLivros() {
        if (livros.isEmpty()) {
            System.out.println("Não há livros no catálogo.");
        } else {
            System.out.println("Livros no catálogo:");
            for (Livro livro : livros) {
                System.out.println(livro);
            }
        }
    }

    public void listarEmprestimos() {
        if (emprestimos.isEmpty()) {
            System.out.println("Não há empréstimos registrados.");
        } else {
            System.out.println("Empréstimos realizados:");
            for (Emprestimo emprestimo : emprestimos) {
                System.out.println(emprestimo);
            }
        }
    }

    @Override
    public String toString() {
        return "Admin{" + "id=" + id + ", nome=" + nome + '}';
    }

}
