package main.java;

import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Item {
    private int id;
    private final String nome;
    private final int custo;
    private final int poder;
    private final String caminhoImagem;
    private Image imagem = null;
    private boolean possuido;

    
    public Item(int id, String nome, int custo, int poder, String caminhoImagem, boolean possuido) {
        this.id = id;
        this.nome = nome;
        this.custo = custo;
        this.poder = poder;
        this.possuido = possuido;
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

    public boolean isPossuido() {
        return possuido;
    }

    public void setPossuido(boolean possuido) {
        this.possuido = possuido;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }
    
    
}
