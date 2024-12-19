package auxiliares;

import java.util.Date;

import usuarios.Usuario;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author letic
 */
public class Emprestimo {
    private Usuario usuario;
    private Livro livro;
    private Date dataEmprestimo;
    private boolean devolvido;
    
    public Emprestimo( Usuario usuario, Livro livro, Date dataEmprestimo) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.devolvido = false; 
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

  

    public boolean isDevolvido() {
        return devolvido;
    }
    
    
    
    @Override
    public String toString() {
        return "Emprestimo{" +
                "usuario=" + usuario.getNome() +
                ", livro=" + livro.getTitulo() +
                ", dataEmprestimo=" + dataEmprestimo +
                ", devolvido=" + devolvido +
                '}';
    }
}
