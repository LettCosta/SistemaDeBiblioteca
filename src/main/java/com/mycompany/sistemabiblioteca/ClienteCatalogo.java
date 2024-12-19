package com.mycompany.sistemabiblioteca;

import auxiliares.Livro;
import auxiliares.LivroCSV;
import usuarios.Cliente;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Date;

public class ClienteCatalogo extends javax.swing.JFrame {

    private LivroCSV livroDAO;
    private Cliente cliente;

    public ClienteCatalogo(Cliente cliente) throws IOException {
        this.setLocationRelativeTo(null);
        initComponents();
        this.cliente = cliente;
        livroDAO = new LivroCSV();
        carregarLivros();
    }

    private void carregarLivros() throws IOException {
        List<Livro> livros = LivroCSV.lerLivrosDoCSV();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        for (Livro livro : livros) {
            model.addRow(new Object[]{
                livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getAno(), !livro.isEmprestado()
            });
        }
    }

    private void emprestarLivro() throws IOException {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecione um livro para emprestar.");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int livroId = (int) model.getValueAt(selectedRow, 0);

        List<Livro> livros = LivroCSV.lerLivrosDoCSV();
        for (Livro livro : livros) {
            if (livro.getId() == livroId && !livro.isEmprestado()) {
                livro.setEmprestado(true);
                cliente.pegarLivroEmprestado(livro, new Date());
                LivroCSV.salvarLivrosNoCSV(livros);
                carregarLivros();
                javax.swing.JOptionPane.showMessageDialog(this, "Livro emprestado com sucesso!");
                return;
            }
        }

        javax.swing.JOptionPane.showMessageDialog(this, "Não foi possível emprestar o livro selecionado.");
    }

    private void jButtonEmprestarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            emprestarLivro();
        } catch (IOException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro ao emprestar livro: " + e.getMessage());
        }
    }

    private void jButtonLogoutActionPerformed(java.awt.event.ActionEvent evt) {
        Login login = new Login();
        login.setVisible(true);
        this.dispose();
    }

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
        new ClienteDashboard(cliente).setVisible(true);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButtonLogout = new javax.swing.JButton();
        jButtonVoltar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButtonEmprestar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(240, 248, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel1.setForeground(new java.awt.Color(90, 100, 115));
        jLabel1.setText("Catálogo de Livros");

        jButtonLogout.setBackground(new java.awt.Color(0, 58, 118));
        jButtonLogout.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jButtonLogout.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLogout.setText("Logout");
        jButtonLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogoutActionPerformed(evt);
            }
        });

        jButtonVoltar.setBackground(new java.awt.Color(0, 58, 118));
        jButtonVoltar.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jButtonVoltar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonVoltar.setText("Voltar");
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "ID", "Título", "Autor", "Ano", "Disponível"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButtonEmprestar.setBackground(new java.awt.Color(0, 128, 0));
        jButtonEmprestar.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jButtonEmprestar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonEmprestar.setText("Emprestar Livro");
        jButtonEmprestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEmprestarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jButtonLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonEmprestar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButtonLogout)
                    .addComponent(jButtonVoltar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonEmprestar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private javax.swing.JButton jButtonEmprestar;
    private javax.swing.JButton jButtonLogout;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
}
