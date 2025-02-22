/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java;

import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author monar
 */
public class ItemLoja {
    private final Jogo jogo;
    private final ArrayList<Item> itens;
    private final Item item;
    private final Font fonteBotoes;
    private JButton botao;

    public ItemLoja(Jogo jogo, ArrayList<Item> itens, Item item, Font fonteBotoes) {
        this.jogo = jogo;
        this.itens = itens;
        this.item = item;
        this.fonteBotoes = fonteBotoes;
        initComponents();
    }
    
    private void initComponents() {
        botao = new JButton();
        botao.setFont(fonteBotoes);
        botao.setText(item.getNome() + " - R$" + item.getCusto());
        botao.addActionListener((java.awt.event.ActionEvent evt) -> {
            comprar();
        });
    }
    
    private void comprar() {
        if (jogo.dinheiro >= item.getCusto()){
            jogo.dinheiro -= item.getCusto();
            botao.setVisible(false);
            itens.add(item);
        }
    }
    
    public Component get() {
        return botao;
    }
}
