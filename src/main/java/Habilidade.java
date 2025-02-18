/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java;

/**
 *
 * @author monar
 */
public class Habilidade {
    private Long ultimoUso;
    private double poder;
    private final int duracao;
    private final int cooldown;
    private boolean ativa;

    public Habilidade(int poder, int duracao, int cooldown) {
        this.ultimoUso = (long)0;
        this.poder = poder / 100;
        this.duracao = duracao * 1000;
        this.cooldown = cooldown * 1000;
    }
    
    
    public void setPoder(int poder) {
        this.poder = (double) poder / 100;
    }
    
    public long tempoQueResta(long momentoAtual){
        long tempo = ultimoUso + duracao - momentoAtual;
        ativa = tempo > 0;
        return tempo;
    }
    
    public long tempoCooldown(long momentoAtual){
        return ultimoUso + cooldown + duracao - momentoAtual;
    }
    
    public boolean usar(long momentoAtual) {
        // Skill em cooldown
        if (tempoCooldown(momentoAtual) > 0){
            return false;
        }
        // Usa a skill
        ultimoUso = momentoAtual;
        return true;
    }
    
    public double getPoder(){
        return ativa ? poder : 1.0;
    }
    
    public int getDuracao(){
        return duracao;
    }
    
    public int getCooldown(){
        return cooldown;
    }
}
