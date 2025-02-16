package main.java;

import javax.swing.JButton;

public class Item {
    private final String nome;
    private final int custo;
    private final int poder;
    private final String caminhoImagem;
    private javax.swing.JButton botao;

    
    public Item(String nome, int custo, int poder, String caminhoImagem) {
        this.nome = nome;
        this.custo = custo;
        this.caminhoImagem = caminhoImagem;
        this.poder = poder;
        this.botao = null;
    }

  
    public String getNome() {
        return nome;
    }

    public int getCusto() {
        return custo;
    }
    
    public String getCaminhoImagem() {
        return caminhoImagem;
    }
    
    public int getPoder() {
        return poder;
    }

    public JButton getBotao() {
        return botao;
    }

    public void setBotao(JButton botao) {
        this.botao = botao;
    }
}
