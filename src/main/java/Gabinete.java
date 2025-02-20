package main.java;

import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class Gabinete extends javax.swing.JFrame {
    
    private final ConjuntoPeca[] conjuntosPecas;
    private final Computador computador;
    
    private javax.swing.JPanel PanelPrincipal;
        private javax.swing.JTabbedPane GerenciadorAbas;
        private javax.swing.JPanel panelMontar;
            private javax.swing.JButton btMontar;

    public Gabinete(Computador computador,
            ArrayList<Item> Processadores, ArrayList<Item> PlacasDeVideo, ArrayList<Item> PlacasMae, ArrayList<Item> RAMS) {
        
        this.computador = computador;
        
        conjuntosPecas = new ConjuntoPeca[4];
        conjuntosPecas[0] = new ConjuntoPeca("Processador", "processador", Processadores);
        conjuntosPecas[1] = new ConjuntoPeca("Placa de video", "placaDeVideo", PlacasDeVideo);
        conjuntosPecas[2] = new ConjuntoPeca("Placa Mãe", "placaMae", PlacasMae);
        conjuntosPecas[3] = new ConjuntoPeca("RAM", "ram", RAMS);
        
        initComponents();
    }
    
   
    @SuppressWarnings("unchecked")
    private void initComponents() {
        PanelPrincipal = new javax.swing.JPanel();
            GerenciadorAbas = new javax.swing.JTabbedPane();
            panelMontar = new JPanel();
                btMontar = new javax.swing.JButton();

        java.awt.GridBagConstraints c;
            
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 1));

        // Abas das peças
        PanelPrincipal.setLayout(new java.awt.GridBagLayout());
        for (ConjuntoPeca conjunto : conjuntosPecas) {
            JPanel panel = conjunto.getPanel();
            JLabel label = conjunto.getLabel();
            JComboBox cb = conjunto.getComboBox();
            
            // ComboBox
            panel.setLayout(new java.awt.GridBagLayout());
            c = new java.awt.GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            panel.add(cb, c);
            registrarItems(conjunto, computador.get(conjunto.getChave()));
            cb.addActionListener((java.awt.event.ActionEvent evt) -> {
                atualizarImagemLabel(conjunto, 200, 200);
            });
            
            // Label com a imagem
            c.gridx = 0;
            c.gridy = 1;
            c.fill = java.awt.GridBagConstraints.HORIZONTAL;
            label.setPreferredSize(new java.awt.Dimension(200, 200));
            panel.add(label, c);
            atualizarImagemLabel(conjunto, 200, 200);

            // Adiciona a aba
            GerenciadorAbas.addTab(conjunto.getNome(), panel);
        }
        
        // Adiciona o gerenciador de abas
        c = new java.awt.GridBagConstraints();
        c.fill = java.awt.GridBagConstraints.BOTH;
        c.weighty = 1.0;
        PanelPrincipal.add(GerenciadorAbas, c);

        // Botão montar
        btMontar.setText("MONTAR");
        btMontar.addActionListener((java.awt.event.ActionEvent evt) -> {
            montarPc();
        });
        panelMontar.add(btMontar);

        c = new java.awt.GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.fill = java.awt.GridBagConstraints.HORIZONTAL;
        PanelPrincipal.add(panelMontar, c);

        
        getContentPane().add(PanelPrincipal);
        pack();
    }
    
    private void montarPc() {
        for (ConjuntoPeca conjunto : conjuntosPecas) {
            computador.set(conjunto.getChave(), getItem(conjunto));
        }
        this.dispose();
    }

    public static Item getItem(ConjuntoPeca conjunto){
        if(conjunto.getComboBox().getItemCount() > 0){
            return conjunto.getInventario().get(conjunto.getComboBox().getSelectedIndex());
        }
        return null;
    }
    public static String getImage(ConjuntoPeca conjunto) {
        
        JComboBox cb = conjunto.getComboBox();
        if(cb.getItemCount() > 0){
            return "/main/resources/images/" + conjunto.getInventario().get(cb.getSelectedIndex()).getCaminhoImagem() + ".png";
        }
        return "/main/resources/images/Sem imagem.png";
    }
    
    private static void registrarItems(ConjuntoPeca conjunto, Item itemAtual){
        ArrayList<Item> lista = conjunto.getInventario();
        JComboBox comboBox = conjunto.getComboBox();
        if(!lista.isEmpty()){ // Só registra se existe itens para serem registrados
            for(Item item:lista){ // Repete para cada peca da lista
                comboBox.addItem(item.getNome()); // Adiciona a peca no comboBox
            }
        }
        
        if (itemAtual == null) {
            return;
        }
        
        for (int i = 0; i < comboBox.getItemCount(); i++){
            if (itemAtual.getNome().equals(comboBox.getItemAt(i))){
                comboBox.setSelectedIndex(i);
                break;
            }
        }
    }
    
    private void atualizarImagemLabel(ConjuntoPeca conjunto, int largura, int altura){
        URL caminho = getClass().getResource(getImage(conjunto));
        Image imagem = new javax.swing.ImageIcon(caminho).getImage().getScaledInstance(largura, altura, Image.SCALE_DEFAULT);
        conjunto.getLabel().setIcon(new javax.swing.ImageIcon(imagem));
    }
}
