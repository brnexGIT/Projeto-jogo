package main.java;

import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Item {
    private final String nome;
    private final int custo;
    private final int poder;
    private final String caminhoImagem;
    private Image imagem = null;
    private javax.swing.JButton botao;

    
    public Item(String nome, int custo, int poder, int poderSecundario, int poderTerciario, String caminhoImagem) {
        this.nome = nome;
        this.custo = custo;
        this.poder = poder;
        this.botao = null;
        this.caminhoImagem = caminhoImagem;
        
        try {
            imagem = ImageIO.read(getClass().getResource("/main/resources/images/" + caminhoImagem + ".png"));
        } catch (IOException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.WARNING, "Erro ao carregar imagem", ex);
        }
    
    }
    
    public Item(String nome, int custo, int poder, String caminhoImagem) {
        this.nome = nome;
        this.custo = custo;
        this.poder = poder;
        this.botao = null;
        this.caminhoImagem = caminhoImagem;
        
        try {
            imagem = ImageIO.read(getClass().getResource("/main/resources/images/" + caminhoImagem + ".png"));
        } catch (IOException ex) {
            Logger.getLogger(Item.class.getName()).log(Level.WARNING, "Erro ao carregar imagem", ex);
        }
    
    }
    
    public ImageIcon getIcon(int largura, int altura){
        return new ImageIcon(imagem.getScaledInstance(largura, altura, Image.SCALE_DEFAULT));
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

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }
    
    
}
