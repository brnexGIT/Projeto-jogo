package main.java;

import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class AbaLoja {
    private final Jogo jogo;
    private final String nome;
    private final ArrayList<Item> itens;
    private int quantItensLoja;
    private Font fonteBotoes;
    private JScrollPane aba;
    private JPanel panel;
    private ItemLoja[] ItensLoja;

    public AbaLoja(Jogo jogo, String nome, String nomeId, Font fonteBotoes) {
        this.jogo = jogo;
        this.nome = nome;
        this.itens = jogo.itens.get(nomeId);
        quantItensLoja = 0;
        for (Item item : itens) {
            if (!item.isPossuido()) {
                quantItensLoja++;
            }
        }
        this.fonteBotoes = fonteBotoes;
        
        initComponents();
    }
    
    private void initComponents() {
        aba = new JScrollPane();
        panel = new JPanel();
        ItensLoja = new ItemLoja[quantItensLoja];
        int i = 0;
        for (Item item : itens) {
            if (!item.isPossuido()){
                ItensLoja[i] = new ItemLoja(jogo, itens, item, fonteBotoes);
                panel.add(ItensLoja[i].get());
                i++;
            }
        }
        
        aba.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panel.setLayout(new WrapLayout());
        aba.setViewportView(panel);
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Item> getItens() {
        return itens;
    }

    public JScrollPane getAba() {
        return aba;
    }

    public JPanel getPanel() {
        return panel;
    }
}
