package usuarios;


import java.util.List;

import auxiliares.Catalogo;
import auxiliares.Emprestimo;
import auxiliares.Livro;

import java.util.ArrayList;
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author letic
 */
public class Cliente extends Usuario {
    private List<Emprestimo> emprestimos;
    private int id;
 
    public Cliente(int id, String nome, String email, String senha) {
        super(nome, email, senha, "cliente");
        this.id=id;
        this.emprestimos = new ArrayList<>();
    }
    
    public void consultarCatalogo(Catalogo catalogo){
        for (Livro livro : catalogo.listarLivros()) {
            System.out.println(livro);
        }
    }
    
    public void pegarLivroEmprestado(Livro livro, Date dataEmprestimo) {
        Emprestimo emprestimo = new Emprestimo(this, livro, dataEmprestimo);
        emprestimos.add(emprestimo);
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }


    public int getId() {
        return id;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nome=" + getNome() + '}';
    }
}
