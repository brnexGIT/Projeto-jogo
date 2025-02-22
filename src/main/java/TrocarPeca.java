/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main.java;

import java.util.ArrayList;

public class TrocarPeca extends javax.swing.JFrame {
    Computador computador;
    ArrayList<Item> itens;
    String nomePeca;
    private final Interface interfacePrincipal;
    
    public TrocarPeca(Interface interfacePrincipal, Jogo jogo, String nomePeca) {
        this.computador = jogo.computador;
        this.itens = jogo.itens.get(nomePeca);
        this.nomePeca = nomePeca;
        this.interfacePrincipal = interfacePrincipal;
        
        initComponents();
        
        // Se possúi menos de dois items na lista fecha a janela imediatamente
        if(itens.size() < 2){
            dispose();
        }
        
        for(Item item:itens){ // Repete para cada peca da lista
            comboBox.addItem(item.getNome()); // Adiciona a peca no comboBox
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelImg = new javax.swing.JLabel();
        comboBox = new javax.swing.JComboBox<>();
        btConfirmar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(340, 283));
        setResizable(false);

        labelImg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        comboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxActionPerformed(evt);
            }
        });

        btConfirmar.setText("Confirmar");
        btConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConfirmarActionPerformed(evt);
            }
        });

        btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btConfirmar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
                        .addComponent(btCancelar)))
                .addContainerGap())
            .addComponent(labelImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(labelImg, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btConfirmar)
                    .addComponent(btCancelar))
                .addGap(0, 14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConfirmarActionPerformed
        // Tenta trocar a peça no computador
        try {
            Item peca = pecaSelecionada();
            if (peca == null) {
                throw new IndexOutOfBoundsException();
            }
            computador.set(nomePeca, peca);
        } catch (IndexOutOfBoundsException ex) {
            return;
        }
        computador.atualizarClique();
        computador.atualizarGeracao();
        interfacePrincipal.atualizarImagens();
        dispose();
        
    }//GEN-LAST:event_btConfirmarActionPerformed

    private Item pecaSelecionada() {
        String nome = comboBox.getSelectedItem().toString();
        System.out.println(nome);
        for (Item item : itens) {
            if (item.getNome().equals(nome)) {
                return item;
            }
        }
        return null;
    }
    
    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void comboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxActionPerformed
        // Sempre que o usuário interagir com a comboBox(como selecionar outro item)
        // Atualiza a imagem carregada
        String caminho = "/main/resources/images/Sem imagem";
        if(comboBox.getItemCount() > 0){
            caminho = pecaSelecionada().getCaminhoImagem();
        }
        labelImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resources/images/" + caminho + ".png")));
    }//GEN-LAST:event_comboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btConfirmar;
    private javax.swing.JComboBox<String> comboBox;
    private javax.swing.JLabel labelImg;
    // End of variables declaration//GEN-END:variables
}
