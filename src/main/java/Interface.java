package main.java;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;


@SuppressWarnings("FieldMayBeFinal")
public class Interface extends JFrame {

    private Jogo jogo;
    private long ultimoFrame = System.currentTimeMillis(); 
    private final Runnable gameCycle;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    
    private AbaLoja[] lojas;
    private Font fonte;
    private JTabbedPane gerenciadorAbasPrincipais;
        private JPanel abaTrabalho;
            private JPanel panelDinheiro;
                private JLabel txtDinheiro;
            private JPanel panelGeracoes;
                private JLabel labelGeracaoClique;
                private JLabel labelGeracaoPassiva;
            private JPanel panelProgramar;
                private JButton botaoDinheiro;
            private JPanel panelHabilidades;
                private JPanel panelHabilidadeAtiva;
                    private JLabel labelHabilidadeAtiva;
                    private JProgressBar barraHabilidadeAtiva;
                    private JButton btHabilidadeAtiva;
                private JPanel panelHabilidadePassiva;
                    private JLabel labelHabilidadePassiva;
                    private JProgressBar barraHabilidadePassiva;
                    private JButton btHabilidadePassiva;
            private JPanel panelMissoes;
            private JPanel abaPC;
                private JLabel labelMonitor;
                private JLabel labelTeclado;
                private JLabel labelMouse;
                private JLabel labelGabinete;
        private JPanel AbaLoja;
            private JLabel txtDinheiroLoja;
            private JTabbedPane gerenciadorAbasPecas;
    
            
    public Interface() {
        // Inicia o jogo
        jogo = new Jogo("itens");
        
        // Carrega a fonte que vai ser usada para tudo
        fonte = carregarFonte("Roboto-ExtraBold.ttf");
        
        // Inicia os componentes gráfico
        initComponents();
        atualizarImagens();
        atualizarPoderHabilidades();
        
        // Define o ciclo do jogo
        ultimoFrame = System.currentTimeMillis();
        this.gameCycle = () -> {
            atualizarDinheiro();
            atualizarHabilidades(ultimoFrame);
            
            // Calcula deltaT
            long currentTime = System.currentTimeMillis();
            long deltaT = currentTime - ultimoFrame;
            long deltaTSegundos = (long) (deltaT / 1000.0);
            int milisegundosSobrando = (int) (deltaT % 1000);
            
            
            long geracao = (long) (jogo.computador.getGeracao() * jogo.habilidadePassiva.getPoder() * deltaTSegundos);
            
            jogo.dinheiro += geracao;
            
            ultimoFrame = System.currentTimeMillis() - milisegundosSobrando;
        };
        scheduler.scheduleAtFixedRate(gameCycle, 0, 16, TimeUnit.MILLISECONDS);
    }

    
    private void initComponents() {
        // Declaração dos componentes gráficos
        gerenciadorAbasPrincipais = new JTabbedPane();
            abaTrabalho = new JPanel();
                panelDinheiro = new JPanel();
                    txtDinheiro = new JLabel();
                panelGeracoes = new JPanel();
                    labelGeracaoClique = new JLabel();
                    labelGeracaoPassiva = new JLabel();
                panelProgramar = new JPanel();
                    botaoDinheiro = new JButton();
                panelHabilidades = new JPanel();
                    panelHabilidadeAtiva = new JPanel();
                        labelHabilidadeAtiva = new JLabel();
                        barraHabilidadeAtiva = new JProgressBar();
                        btHabilidadeAtiva = new JButton();
                    panelHabilidadePassiva = new JPanel();
                        labelHabilidadePassiva = new JLabel();
                        barraHabilidadePassiva = new JProgressBar();
                        btHabilidadePassiva = new JButton();
                panelMissoes = new JPanel();
            AbaLoja = new JPanel();
                txtDinheiroLoja = new JLabel();
                gerenciadorAbasPecas = new JTabbedPane();
            abaPC = new JPanel();
                labelMonitor = new JLabel();
                labelTeclado = new JLabel();
                labelMouse = new JLabel();
                labelGabinete = new JLabel();

        // Define o que acontece quando usuário coloca para fechar
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        // Coloca o jframe como GridLayout com uma linha e uma coluna
        // Dessa forma o conteúdo fica do mesmo tamanho da janela
        getContentPane().setLayout(new java.awt.GridLayout(1, 1));

        // Fica de olho quando o usuário troca de aba
        gerenciadorAbasPrincipais.addChangeListener((javax.swing.event.ChangeEvent evt) -> {
            AbasPrincipaisStateChanged();
        });

        // Aba principal "Área de trabalho"
        abaTrabalho.setLayout(new java.awt.GridLayout(0, 1));

          // Contador de dinheiro
        txtDinheiro.setFont(fonte.deriveFont(0, 18));
        txtDinheiro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDinheiro.setText("Dinheiro: 0");
        panelDinheiro.add(txtDinheiro);
        abaTrabalho.add(panelDinheiro);

          // Geração
        panelGeracoes.setLayout(new java.awt.GridLayout(1, 0));
        labelGeracaoClique.setFont(fonte);
        labelGeracaoPassiva.setFont(fonte);
        labelGeracaoClique.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelGeracaoPassiva.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelGeracoes.add(labelGeracaoClique);
        panelGeracoes.add(labelGeracaoPassiva);
        abaTrabalho.add(panelGeracoes);

          // Botão de dinheiro
        botaoDinheiro.setBackground(new java.awt.Color(153, 255, 51));
        botaoDinheiro.setFont(fonte);
        botaoDinheiro.setText("Programar");
        botaoDinheiro.addActionListener((java.awt.event.ActionEvent evt) -> {
            botaoProgramarClique();
        });
        panelProgramar.add(botaoDinheiro);
        abaTrabalho.add(panelProgramar);

          // Habilidades
        panelHabilidades.setLayout(new java.awt.GridLayout(1, 0));
        panelHabilidadeAtiva.setLayout(new GridBagLayout());
        panelHabilidadePassiva.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
            // Barras
        panelHabilidadeAtiva.add(barraHabilidadeAtiva, c);
        panelHabilidadePassiva.add(barraHabilidadePassiva, c);
            // Labels
        c.gridy = 1;
        c.gridheight = 3;
        labelHabilidadeAtiva.setFont(fonte);
        labelHabilidadeAtiva.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHabilidadeAtiva.setPreferredSize(new Dimension(250, 80));
        panelHabilidadeAtiva.add(labelHabilidadeAtiva, c);
        labelHabilidadePassiva.setFont(fonte);
        labelHabilidadePassiva.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHabilidadePassiva.setPreferredSize(new Dimension(250, 80));
        panelHabilidadePassiva.add(labelHabilidadePassiva, c);
            // Botões
        c.gridheight = 1;
        c.gridy = 4;
        btHabilidadeAtiva.setFont(fonte);
        btHabilidadeAtiva.setText("Ativar");
        btHabilidadeAtiva.addActionListener((java.awt.event.ActionEvent evt) -> {
            jogo.habilidadeAtiva.usar(ultimoFrame);
        });
        btHabilidadePassiva.setFont(fonte);
        btHabilidadePassiva.setText("Ativar");
        btHabilidadePassiva.addActionListener((java.awt.event.ActionEvent evt) -> {
            jogo.habilidadePassiva.usar(ultimoFrame);
        });
        
        panelHabilidadeAtiva.add(btHabilidadeAtiva, c);
        panelHabilidadePassiva.add(btHabilidadePassiva, c);
        panelHabilidades.add(panelHabilidadeAtiva);
        panelHabilidades.add(panelHabilidadePassiva);
        abaTrabalho.add(panelHabilidades);
        
          // Mostra as missões disponíveis
        abaTrabalho.add(panelMissoes);

          // Adiciona a aba "Trabalho" no gerenciador de abas "AbasPrincipais"
        gerenciadorAbasPrincipais.addTab("TRABALHO", abaTrabalho);

        
        // Aba da Loja
        AbaLoja.setLayout(new BoxLayout(AbaLoja, BoxLayout.Y_AXIS));

        txtDinheiroLoja.setFont(fonte); 
        txtDinheiroLoja.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDinheiroLoja.setText("Dinheiro: 0");
        AbaLoja.add(txtDinheiroLoja);

          // Cria as abas da loja
        lojas = new AbaLoja[8];
        String[] nomes = new String[] {"Processadores", "Placas de vídeo", "Placas mãe", "RAMs", "Gabinetes", "Monitores", "Teclados", "Mouses"};
        String[] nomesIds = new String[] {"processador", "placaDeVideo", "placaMae", "ram", "gabinete", "monitor", "teclado", "mouse"};
        for (int i = 0; i < 8; i++){
            lojas[i] = new AbaLoja(jogo, nomes[i], nomesIds[i], fonte);
            gerenciadorAbasPecas.addTab(lojas[i].getNome(), null, lojas[i].getAba(), "");
        }
        AbaLoja.add(gerenciadorAbasPecas);
        gerenciadorAbasPrincipais.addTab("LOJA", AbaLoja);
        
        
        // Aba do computador
        abaPC.setLayout(new GridBagLayout());
        abaPC.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                atualizarImagens();
            }
        });
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        
          // Monitor
            // Adiciona função trocar peça
        labelMonitor.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                abrirTrocarPeca("monitor");
            }
        });
          // Propriedades do grid em que reside
        c.gridwidth = 2;
        c.gridheight = 2;
        abaPC.add(labelMonitor, c);
        
        
          // Gabinete
        labelGabinete.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Botão direito
                if(evt.getButton() == 3){
                    abrirGabinete();
                }
                // Botão esquerdo
                if(evt.getButton() == 1){
                    abrirTrocarPeca("gabinete");
                }
            }
        });
            // Propriedades do grid em que reside
        c.gridwidth = 1;
        c.gridx = 2;
        abaPC.add(labelGabinete, c);

        // Teclado
        labelTeclado.setIcon(new ImageIcon(getClass().getResource("/main/resources/images/teclado/antigo.png"))); // NOI18N
        labelTeclado.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                abrirTrocarPeca("teclado");
            }
        });
            // Propriedades do grid em que reside
        c.gridwidth = 2;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 2;
        abaPC.add(labelTeclado, c);

        // Mouse
        labelMouse.setIcon(new ImageIcon(getClass().getResource("/main/resources/images/mouse/antigo.png"))); // NOI18N
        labelMouse.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                abrirTrocarPeca("mouse");
            }
        });
            // Propriedades do grid em que reside
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 2;
        c.gridy = 2;
        abaPC.add(labelMouse, c);

        gerenciadorAbasPrincipais.addTab("PC", abaPC);
        getContentPane().add(gerenciadorAbasPrincipais);

        pack();
    }

    private void botaoProgramarClique() {
        jogo.dinheiro += jogo.computador.getClique() * jogo.habilidadeAtiva.getPoder();
        atualizarDinheiro();
    }
    
    private void abrirTrocarPeca(String nome) {
        new TrocarPeca(this, jogo, nome).setVisible(true);
    }
    
    private void abrirGabinete(){
        new Gabinete(jogo).setVisible(true);
    }

    private void AbasPrincipaisStateChanged() {
        if(gerenciadorAbasPrincipais.getSelectedIndex() == 1 ){
            if (!jogo.jaVisto.get("Loja")){
                JOptionPane.showMessageDialog(rootPane, "Esta é a loja\nAqui você pode comprar itens para melhorar seu Setup!");
                jogo.jaVisto.put("Loja", true);
            }
        } else if(gerenciadorAbasPrincipais.getSelectedIndex() == 2){
            if(!jogo.jaVisto.get("PC")){
                JOptionPane.showMessageDialog(rootPane, """
                                                        Aqui é seu PC
                                                        Você deve equipar as peças compradas para utilizá-las!
                                                         Botão esquerdo = Altera a peça clicada
                                                        Botão direito no 'gabinete' = Abre o computador para trocar-se os hardwares""");
                jogo.jaVisto.put("PC", true);
            }
        }
    }

    public void atualizarDinheiro(){
        String labelText = String.format("Dinheiro: R$%d", jogo.dinheiro);
        txtDinheiro.setText(labelText);
        txtDinheiroLoja.setText(labelText);
        labelGeracaoClique.setText("Dinheiro p/Clique: " + jogo.computador.getClique()*jogo.habilidadeAtiva.getPoder());
        labelGeracaoPassiva.setText("Dinheiro p/s: " + jogo.computador.getGeracao()*jogo.habilidadePassiva.getPoder());
    }
    
    public final void atualizarPoderHabilidades(){
        int poderAtiva = jogo.computador.get("monitor").getPoder();
        int poderPassiva = jogo.computador.get("gabinete").getPoder();
        jogo.habilidadeAtiva.setPoder(poderAtiva);
        jogo.habilidadePassiva.setPoder(poderPassiva);
        labelHabilidadeAtiva.setText("<html>X" + ((float) poderAtiva / 100) + " mult para o clique" +
                               "<br/>Duração: " + jogo.habilidadeAtiva.getDuracao()/1000 + "s" + 
                               "<br/>Cooldown: " + jogo.habilidadeAtiva.getCooldown()/1000 + "s</html>");
        labelHabilidadePassiva.setText("<html>X" + ((float) poderPassiva / 100) + " mult para a geração"+
                               "<br/>Duração: " + jogo.habilidadePassiva.getDuracao()/1000 + "s" + 
                               "<br/>Cooldown: " + jogo.habilidadePassiva.getCooldown()/1000 + "s</html>");
    }
    
    public void atualizarHabilidades(long momentoAtual){
        // Ativa
        long duracaoRestante = jogo.habilidadeAtiva.tempoQueResta(momentoAtual);
        long cooldown = jogo.habilidadeAtiva.tempoCooldown(momentoAtual);
        if (duracaoRestante > 0) {
            barraHabilidadeAtiva.setValue((int) duracaoRestante);
            barraHabilidadeAtiva.setMaximum(jogo.habilidadeAtiva.getDuracao());
        } else {
            barraHabilidadeAtiva.setValue((int)(jogo.habilidadeAtiva.getCooldown() - cooldown));
            barraHabilidadeAtiva.setMaximum(jogo.habilidadeAtiva.getCooldown());
        }
        // Passiva
        duracaoRestante = jogo.habilidadePassiva.tempoQueResta(momentoAtual);
        cooldown = jogo.habilidadePassiva.tempoCooldown(momentoAtual);
        if (duracaoRestante > 0) {
            barraHabilidadePassiva.setValue((int) duracaoRestante);
            barraHabilidadePassiva.setMaximum(jogo.habilidadePassiva.getDuracao());
        } else {
            barraHabilidadePassiva.setValue((int)(jogo.habilidadePassiva.getCooldown() - cooldown));
            barraHabilidadePassiva.setMaximum(jogo.habilidadePassiva.getCooldown());
        }
    }
    
    public final void atualizarImagens(){
        int tamanho = abaPC.getWidth() < abaPC.getHeight() ? abaPC.getWidth() : abaPC.getHeight();
        // Pequeno e grande
        int p = tamanho / 3;
        int g = p * 2;
        labelGabinete.setIcon(jogo.computador.get("gabinete").getIcon(p, g));
        labelMonitor.setIcon(jogo.computador.get("monitor").getIcon(g, g));
        labelTeclado.setIcon(jogo.computador.get("teclado").getIcon(g, p));
        labelMouse.setIcon(jogo.computador.get("mouse").getIcon(p, p));
    }
    
    public Icon carregarImagem(String caminho, int largura, int altura) throws IOException{
        return new ImageIcon(ImageIO.read(getClass().getResource("/main/resources/images/" + caminho + ".png")));
    }

    
    private Font carregarFonte(String caminho){
        GraphicsEnvironment ge =  GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font fonteCarregada;
        try {
            fonteCarregada = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/main/resources/fonts/" + caminho).openStream());
            ge.registerFont(fonteCarregada);
        } catch (IOException | FontFormatException ex) {
            fonteCarregada = new Font("DejaVu Serif Condensed", 0, 12);
            Logger.getLogger(Interface.class.getName()).log(Level.WARNING, null, ex);
        }
        return fonteCarregada.deriveFont(0, 14);
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(() -> {
            new Interface().setVisible(true);
        });
    }
}
