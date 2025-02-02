package main.java;

import java.util.HashMap;

public class Computador {
    private HashMap<String, Item> pecas;
    
    private double geracao;
    private double clique;

    public Computador(Item placaDeVideo, Item processador, Item placaMae, Item ram, Item gabinete, Item monitor, Item teclado, Item mouse) {
        pecas = new HashMap<String, Item>();
        
        pecas.put("placaDeVideo", placaDeVideo);
        pecas.put("processador", processador);
        pecas.put("placaMae", placaMae);
        pecas.put("ram", ram);
        pecas.put("gabinete", gabinete);
        pecas.put("monitor", monitor);
        pecas.put("teclado", teclado);
        pecas.put("mouse", mouse);
        
        atualizarGeracao();
        atualizarClique();
    }
    
    public void atualizarGeracao(){
        int novaGeracao = 0;
        int multiplicador = 100; // 100%
        
        if (pecas.get("placaDeVideo") != null) {multiplicador += pecas.get("placaDeVideo").getPoder();}
        if (pecas.get("processador") != null) {multiplicador += pecas.get("processador").getPoder();}
        if (pecas.get("placaMae") != null) {multiplicador += pecas.get("placaMae").getPoder();}
        if (pecas.get("ram") != null) {novaGeracao += pecas.get("ram").getPoder(); System.out.println(" - " + pecas.get("ram").getNome());}
        
        
        geracao = (int)(novaGeracao * multiplicador / 100);
    }
    
    public void atualizarClique(){
        clique = pecas.get("mouse").getPoder() * (100 + pecas.get("teclado").getPoder()) / 100;
    }
    
    public void set(String nome, Item peca){
        pecas.put(nome, peca);
    }
    
    public Item get(String nome){
        return pecas.get(nome);
    }
    
    public double getGeracao() {
        return geracao;
    }

    public double getClique() {
        return clique;
    }
}
