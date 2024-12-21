package jogo;

import java.util.ArrayList;

public class Gabinete extends javax.swing.JFrame {
    Clicar jogo;
    ArrayList<Item> Processadores;
    ArrayList<Item> PlacasDeVideo;
    ArrayList<Item> PlacasMae;
    ArrayList<Item> RAMS;

    public Gabinete(Clicar jogo, ArrayList<Item> Processadores, ArrayList<Item> PlacasDeVideo, ArrayList<Item> PlacasMae, ArrayList<Item> RAMS) {
        initComponents();
        this.jogo = jogo;
        
        this.Processadores = Processadores;
        this.PlacasDeVideo = PlacasDeVideo;
        this.PlacasMae = PlacasMae;
        this.RAMS = RAMS;
        
        registrarItems(Processadores, cbProcessador);
        registrarItems(PlacasDeVideo, cbPlacaDeVideo);
        registrarItems(PlacasMae, cbPlacaMae);
        registrarItems(RAMS, cbRAM);
    }
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        panelProcessador = new javax.swing.JPanel();
        imgProcessador = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbProcessador = new javax.swing.JComboBox<>();
        panelRam = new javax.swing.JPanel();
        imgRAM = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbRAM = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        imgPlacaDeVideo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbPlacaDeVideo = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        imgPlacaMae = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbPlacaMae = new javax.swing.JComboBox<>();
        btMontar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelProcessador.setBackground(new java.awt.Color(102, 102, 102));
        panelProcessador.setForeground(new java.awt.Color(102, 255, 102));

        imgProcessador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgProcessador.setText("IMAGEM FODA");

        jLabel1.setFont(new java.awt.Font("Microsoft YaHei Light", 3, 24)); // NOI18N
        jLabel1.setText("Processador");

        cbProcessador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProcessadorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelProcessadorLayout = new javax.swing.GroupLayout(panelProcessador);
        panelProcessador.setLayout(panelProcessadorLayout);
        panelProcessadorLayout.setHorizontalGroup(
            panelProcessadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProcessadorLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(panelProcessadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbProcessador, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgProcessador, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelProcessadorLayout.setVerticalGroup(
            panelProcessadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProcessadorLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(imgProcessador, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbProcessador, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelRam.setBackground(new java.awt.Color(102, 102, 102));
        panelRam.setForeground(new java.awt.Color(102, 255, 102));

        imgRAM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgRAM.setText("IMAGEM FODA");

        jLabel2.setFont(new java.awt.Font("Microsoft YaHei Light", 3, 24)); // NOI18N
        jLabel2.setText("RAM");

        cbRAM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRAMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRamLayout = new javax.swing.GroupLayout(panelRam);
        panelRam.setLayout(panelRamLayout);
        panelRamLayout.setHorizontalGroup(
            panelRamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRamLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(panelRamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbRAM, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgRAM, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        panelRamLayout.setVerticalGroup(
            panelRamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRamLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(imgRAM, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbRAM, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));
        jPanel4.setForeground(new java.awt.Color(102, 255, 102));

        imgPlacaDeVideo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgPlacaDeVideo.setText("IMAGEM FODA");

        jLabel3.setFont(new java.awt.Font("Microsoft YaHei Light", 3, 24)); // NOI18N
        jLabel3.setText("Placa de Vídeo");

        cbPlacaDeVideo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPlacaDeVideoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbPlacaDeVideo, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgPlacaDeVideo, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(imgPlacaDeVideo, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbPlacaDeVideo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(102, 102, 102));
        jPanel5.setForeground(new java.awt.Color(102, 255, 102));

        imgPlacaMae.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgPlacaMae.setText("IMAGEM FODA");

        jLabel4.setFont(new java.awt.Font("Microsoft YaHei Light", 3, 24)); // NOI18N
        jLabel4.setText("PLACA MÃE");

        cbPlacaMae.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPlacaMaeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbPlacaMae, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgPlacaMae, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(imgPlacaMae, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbPlacaMae, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        btMontar.setText("MONTAR");
        btMontar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMontarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelProcessador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelRam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(btMontar, javax.swing.GroupLayout.PREFERRED_SIZE, 799, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelProcessador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addComponent(btMontar, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btMontarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMontarActionPerformed
        int geracao = 0;
        int multiplicador = 100; // 100%
        
        multiplicador += getPoder(Processadores, cbProcessador);
        multiplicador += getPoder(PlacasDeVideo, cbPlacaDeVideo);
        multiplicador += getPoder(PlacasMae, cbPlacaMae);
        geracao += getPoder(RAMS, cbRAM);
        
        
        jogo.setGeracaoS((int)(geracao * multiplicador / 100));
        this.dispose();
    }//GEN-LAST:event_btMontarActionPerformed

    private void cbProcessadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProcessadorActionPerformed
        imgProcessador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jogo/imagens/" + getImage(Processadores, cbProcessador))));
    }//GEN-LAST:event_cbProcessadorActionPerformed

    private void cbRAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRAMActionPerformed
        imgRAM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jogo/imagens/" + getImage(RAMS, cbRAM))));
    }//GEN-LAST:event_cbRAMActionPerformed

    private void cbPlacaMaeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPlacaMaeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbPlacaMaeActionPerformed

    private void cbPlacaDeVideoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPlacaDeVideoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbPlacaDeVideoActionPerformed

    public static int getPoder(ArrayList<Item> lista, javax.swing.JComboBox<String> comboBox){
        if(comboBox.getItemCount() > 0){
            return lista.get(comboBox.getSelectedIndex()).getPoder();
        }
        return 0;
    }
    public static String getImage(ArrayList<Item> lista, javax.swing.JComboBox<String> comboBox) {
        if(comboBox.getItemCount() > 0){
            return lista.get(comboBox.getSelectedIndex()).getCaminhoImagem();
        }
        return "sem imagem.png";
    }
    
    private static void registrarItems(ArrayList<Item> lista, javax.swing.JComboBox<String> comboBox){
        if(!lista.isEmpty()){ // Só registra se existe itens para serem registrados
            for(Item item:lista){ // Repete para cada peca da lista
                comboBox.addItem(item.getNome()); // Adiciona a peca no comboBox
            }
        }
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btMontar;
    private javax.swing.JComboBox<String> cbPlacaDeVideo;
    private javax.swing.JComboBox<String> cbPlacaMae;
    private javax.swing.JComboBox<String> cbProcessador;
    private javax.swing.JComboBox<String> cbRAM;
    private javax.swing.JLabel imgPlacaDeVideo;
    private javax.swing.JLabel imgPlacaMae;
    private javax.swing.JLabel imgProcessador;
    private javax.swing.JLabel imgRAM;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelProcessador;
    private javax.swing.JPanel panelRam;
    // End of variables declaration//GEN-END:variables
}
