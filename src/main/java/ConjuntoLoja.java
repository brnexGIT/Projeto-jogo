package main.java;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

// Classe associativa que guarda as coisas necessárias para cada aba da loja
public class ConjuntoLoja {
    private final String nome;
    private final ArrayList<Item> tem;
    private final Item[] loja;
    private JScrollPane aba;
    private JPanel panel;
    private JButton[] botoes;

    public ConjuntoLoja(String nome, ArrayList<Item> tem, Item[] loja, JScrollPane aba, JPanel panel, JButton[] botoes) {
        this.nome = nome;
        this.tem = tem;
        this.loja = loja;
        this.aba = aba;
        this.panel = panel;
        this.botoes = botoes;
    }

    public ConjuntoLoja(String nome, ArrayList<Item> tem, Item[] loja) {
        this.nome = nome;
        this.tem = tem;
        this.loja = loja;
    }

    public void setAba(JScrollPane aba) {
        this.aba = aba;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public void setBotoes(JButton[] botoes) {
        this.botoes = botoes;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Item> getTem() {
        return tem;
    }

    public Item[] getLoja() {
        return loja;
    }

    public JScrollPane getAba() {
        return aba;
    }

    public JPanel getPanel() {
        return panel;
    }

    public JButton[] getBotoes() {
        return botoes;
    }

    
    
    
}
