package main.java;

public class ConstrutorPecasPadrao {
    static Item Item(String nome, int custo, int poder, String imagem, javax.swing.JButton botao){
        Item novoItem = new Item(nome, custo, poder, imagem);
        setBotao(botao, novoItem);
        return novoItem;
    }
    static Item Item(String nome, int custo, int poder, String imagem){
        Item novoItem = new Item(nome, custo, poder, imagem);
        return novoItem;
    }
    
    static void setBotao(javax.swing.JButton botao, Item peca) {
        botao.setText(peca.getNome() + " - R$" + peca.getCusto());
    }
    
    static Item Processador(String nome, int custo, int poder, javax.swing.JButton botao){
        return Item(nome, custo, poder, "imagemPadrao", botao);
    }
    
    static Item PlacaDeVideo(String nome, int custo, int poder, javax.swing.JButton botao){
        return Item(nome, custo, poder, "imagemPadrao", botao);
    }
    
    static Item PlacaMae(String nome, int custo, int poder, javax.swing.JButton botao){
        return Item(nome, custo, poder, "imagemPadrao", botao);
    }
    
    static Item RAM(String nome, int custo, int poder, javax.swing.JButton botao){
        return Item(nome, custo, poder, "ram/antigo", botao);
    }
    
    
    
}
