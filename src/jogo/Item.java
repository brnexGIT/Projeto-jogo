package jogo;

public class Item {
    private String nome;
    private int custo;
    private String caminhoImagem;
    private int poder;

    
    public Item(String nome, int custo, int poder, String caminhoImagem) {
        this.nome = nome;
        this.custo = custo;
        this.caminhoImagem = caminhoImagem;
        this.poder = poder;
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
}
