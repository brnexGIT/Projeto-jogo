package main.java;

public class ConstrutorPecasPadrao {
    static Item Item(String nome, int custo, int poder, String imagem){
        return new Item(nome, custo, poder, imagem);
    }
    
    static Item Processador(String nome, int custo, int poder){
        return Item(nome, custo, poder, "imagemPadrao");
    }
    
    static Item PlacaDeVideo(String nome, int custo, int poder){
        return Item(nome, custo, poder, "imagemPadrao");
    }
    
    static Item PlacaMae(String nome, int custo, int poder){
        return Item(nome, custo, poder, "imagemPadrao");
    }
    
    static Item RAM(String nome, int custo, int poder){
        return Item(nome, custo, poder, "ram/antigo");
    }
    
    
    
}
