/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java;

import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author monar
 */
public class ConjuntoPeca {
    private final String nome;
    private final String chave;
    private final ArrayList<Item> inventario;
    private final javax.swing.JPanel panel;
    private final javax.swing.JLabel label;
    private final javax.swing.JComboBox<String> comboBox;

    public ConjuntoPeca(String nome, String chave, ArrayList<Item> inventario) {
        this.nome = nome;
        this.chave = chave;
        this.inventario = inventario;
        this.panel = new javax.swing.JPanel();
        this.label = new javax.swing.JLabel();
        this.comboBox = new javax.swing.JComboBox();
    }

    public String getNome() {
        return nome;
    }

    public String getChave() {
        return chave;
    }

    public ArrayList<Item> getInventario() {
        return inventario;
    }

    public JPanel getPanel() {
        return panel;
    }

    public JLabel getLabel() {
        return label;
    }

    public JComboBox getComboBox() {
        return comboBox;
    }
    
    
}
