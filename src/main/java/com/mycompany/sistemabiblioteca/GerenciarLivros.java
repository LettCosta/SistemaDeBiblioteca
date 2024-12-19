package com.mycompany.sistemabiblioteca;

import auxiliares.Livro;
import auxiliares.LivroCSV;
import com.mycompany.sistemabiblioteca.Login;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;


public class GerenciarLivros extends javax.swing.JFrame {
    private List<Livro> livros = new ArrayList<>();
    private DefaultTableModel tableModel;
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;

    public GerenciarLivros() {
        this.setLocationRelativeTo(null);
        initComponents();
        carregarLivros();
        configurarTabela();
    }

    private void carregarLivros() {
        try {
            livros = LivroCSV.lerLivrosDoCSV();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar livros do CSV: " + e.getMessage());
        }
    }

    private void configurarTabela() {
        String[] colunas = {"ID", "Título", "Autor", "Categoria", "Ano", "Emprestado"};
        tableModel = new DefaultTableModel(colunas, 0);
        jTable1.setModel(tableModel);
        atualizarTabela();
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0); 
        for (Livro livro : livros) {
            tableModel.addRow(new Object[] {
                livro.getId(), livro.getTitulo(), livro.getAutor(),
                livro.getCategoria(), livro.getAno(), livro.isEmprestado()
            });
        }
    }

    private void adicionarLivro() {
        AdicionarLivroDialog dialog = new AdicionarLivroDialog(this);
        dialog.setVisible(true);  

        Livro livroCriado = dialog.getNovoLivro();
        if (livroCriado != null) {
            livros.add(livroCriado);
            atualizarTabela();
            salvarLivros();
        }
    }

    private void editarLivro() {
        int linhaSelecionada = jTable1.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um livro para editar.");
            return;
        }

        Livro livroSelecionado = livros.get(linhaSelecionada);
        EditarLivroDialog dialog = new EditarLivroDialog(this, livroSelecionado);
        dialog.setVisible(true);

        Livro livroEditado = dialog.getLivroEditado();
        if (livroEditado != null) {
            atualizarTabela();
            salvarLivros();
        }
    }


    private void excluirLivro() {
        int linhaSelecionada = jTable1.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um livro para excluir.");
            return;
        }

        livros.remove(linhaSelecionada);
        atualizarTabela();
        salvarLivros();
    }

    private void salvarLivros() {
        try {
            LivroCSV.salvarLivrosNoCSV(livros);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar livros no CSV: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnAdicionar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel1.setText("Gerenciar Livros");

        jButton3.setText("Logout");
        jButton3.addActionListener(evt -> jButton3ActionPerformed(evt));

        jTable1.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID", "Título", "Autor", "Categoria", "Ano", "Emprestado"}
        ));
        jScrollPane1.setViewportView(jTable1);

        btnAdicionar.setText("Adicionar Livro");
        btnAdicionar.addActionListener(evt -> adicionarLivro());

        btnEditar.setText("Editar Livro");
        btnEditar.addActionListener(evt -> editarLivro());

        btnExcluir.setText("Excluir Livro");
        btnExcluir.addActionListener(evt -> excluirLivro());

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAdicionar)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionar)
                    .addComponent(btnEditar)
                    .addComponent(btnExcluir))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void jButton3ActionPerformed(ActionEvent evt) {
        Login login = new Login();
        login.setVisible(true);
        this.setVisible(false);
    }

    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;

    
    public static class EditarLivroDialog extends JDialog {
        private JTextField txtTitulo, txtAutor, txtCategoria, txtAno;
        private JButton btnSalvar, btnCancelar;
        private Livro livroEditado;

        public EditarLivroDialog(JFrame parent, Livro livro) {
            super(parent, "Editar Livro", true);
            setLayout(new GridLayout(5, 2, 10, 10));
            setSize(400, 300);
            setLocationRelativeTo(parent);

            add(new JLabel("Título:"));
            txtTitulo = new JTextField(livro.getTitulo());
            add(txtTitulo);

            add(new JLabel("Autor:"));
            txtAutor = new JTextField(livro.getAutor());
            add(txtAutor);

            add(new JLabel("Categoria:"));
            txtCategoria = new JTextField(livro.getCategoria());
            add(txtCategoria);

            add(new JLabel("Ano:"));
            txtAno = new JTextField(String.valueOf(livro.getAno()));
            add(txtAno);

            btnSalvar = new JButton("Salvar");
            btnSalvar.addActionListener(e -> salvarAlteracoes(livro));
            add(btnSalvar);

            btnCancelar = new JButton("Cancelar");
            btnCancelar.addActionListener(e -> dispose());
            add(btnCancelar);
        }

        private void salvarAlteracoes(Livro livro) {
            livro.setTitulo(txtTitulo.getText());
            livro.setAutor(txtAutor.getText());
            livro.setCategoria(txtCategoria.getText());
            livro.setAno(Integer.parseInt(txtAno.getText()));

            livroEditado = livro; 
            dispose();  
        }

        public Livro getLivroEditado() {
            return livroEditado;
        }
    }

    
    public static class AdicionarLivroDialog extends JDialog {
        private JTextField txtTitulo, txtAutor, txtCategoria, txtAno;
        private JButton btnSalvar, btnCancelar;
        private Livro novoLivro;

        public AdicionarLivroDialog(JFrame parent) {
            super(parent, "Adicionar Novo Livro", true);
            setLayout(new GridLayout(5, 2, 10, 10));
            setSize(400, 300);
            setLocationRelativeTo(parent);

            add(new JLabel("Título:"));
            txtTitulo = new JTextField();
            add(txtTitulo);

            add(new JLabel("Autor:"));
            txtAutor = new JTextField();
            add(txtAutor);

            add(new JLabel("Categoria:"));
            txtCategoria = new JTextField();
            add(txtCategoria);

            add(new JLabel("Ano:"));
            txtAno = new JTextField();
            add(txtAno);

            btnSalvar = new JButton("Salvar");
            btnSalvar.addActionListener(e -> salvarLivro());
            add(btnSalvar);

            btnCancelar = new JButton("Cancelar");
            btnCancelar.addActionListener(e -> dispose());
            add(btnCancelar);
        }

        private void salvarLivro() {
            String titulo = txtTitulo.getText();
            String autor = txtAutor.getText();
            String categoria = txtCategoria.getText();
            int ano = Integer.parseInt(txtAno.getText());

            novoLivro = new Livro(0, titulo, autor, categoria, ano); 
            dispose();  
        }

        public Livro getNovoLivro() {
            return novoLivro;
        }
    }
}
