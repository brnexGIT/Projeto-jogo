/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author monar
 */
public class Jogo {
    public int dinheiro = 50000000;
    public Computador computador;
    public HashMap<String, ArrayList<Item>> itens;
    public HashMap<String, Boolean> jaVisto;
    public Habilidade habilidadeAtiva;
    public Habilidade habilidadePassiva;

    public Jogo(String caminhoItens) {
        // Cria o invetário das peças
        itens = new HashMap<>();
        itens.put("placaDeVideo", new ArrayList<>());
        itens.put("processador", new ArrayList<>());
        itens.put("placaMae", new ArrayList<>());
        itens.put("ram", new ArrayList<>());
        itens.put("gabinete", new ArrayList<>());
        itens.put("monitor", new ArrayList<>());
        itens.put("teclado", new ArrayList<>());
        itens.put("mouse", new ArrayList<>());
        carregarItensCSV(itens, caminhoItens);
        
        
        // Cria o computador e dá as peças iniciais
        computador = new Computador(
                itens.get("processador").get(0), 
                itens.get("placaDeVideo").get(0), 
                itens.get("placaMae").get(0), 
                itens.get("ram").get(0), 
                itens.get("gabinete").get(0), 
                itens.get("monitor").get(0), 
                itens.get("teclado").get(0), 
                itens.get("mouse").get(0));
        
        // Inicia ja visto
        jaVisto = new HashMap<>();
        String[] nomesDicas = {"Trabalho", "Loja", "PC"};
        for (String nome : nomesDicas) {
            jaVisto.put(nome, false);
        }
        
        // Inicia as skills
        habilidadeAtiva = new Habilidade(100, 10, 10);
        habilidadePassiva = new Habilidade(100, 10, 30);
        
        
    }
    
    private void carregarItensCSV(HashMap<String, ArrayList<Item>> itens, String caminho) {
        // Tenta criar o leitor de arquivos
        URL arquivo = getClass().getResource("/main/resources/csvs/" + caminho + ".csv");
        if (arquivo == null) {
            return;
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(arquivo.openStream()))) {
            // Continua enquanto tiver uma próxima linha
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    String[] partes = line.split(";");
                    // Joga um erro caso o não tenha 4 elementos
                    if (partes.length != 7) {
                        throw new ArrayIndexOutOfBoundsException("Tamanho invalido");
                    }
                    // Pega os valores e formata eles
                    int id = Integer.parseInt(partes[0]);
                    String tipo = partes[1];
                    String nome = partes[2];
                    int custo = Integer.parseInt(partes[3]);
                    int poder = Integer.parseInt(partes[4]);
                    String caminhoImagem = partes[5];
                    boolean possuido = Boolean.parseBoolean(partes[6]);
                    // caso um dos valores não esteja formatado corretamente irá jogar um erro
                    itens.get(tipo).add(new Item(id, nome, custo, poder, caminhoImagem, possuido));
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
                    Logger.getLogger(Interface.class.getName()).log(Level.WARNING, "Item invalido", ex);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, "Arquivo " + caminho + " não encontrado", ex);
        } catch (IOException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, "Não foi possível abrir o arquivo " + caminho, ex);
        }
    }
}
