package main.java;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;


@SuppressWarnings("FieldMayBeFinal")
public class Interface extends JFrame {

    int dinheiro = 50000000;
    
    public Computador computador;
    private HashMap<String, Boolean> jaVisto;
    private long ultimoFrame = System.currentTimeMillis(); 
    private long[] tempoHabilidades = new long[2];
    private final Runnable gameCycle;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    
    
    private ArrayList<Item> processadorTem;
    private ArrayList<Item> placaVideoTem;
    private ArrayList<Item> placaMaeTem;
    private ArrayList<Item> ramTem;
    private ArrayList<Item> gabineteTem;
    private ArrayList<Item> monitorTem;
    private ArrayList<Item> tecladoTem;
    private ArrayList<Item> mouseTem;
    
    
    private final Item[] processadorLoja;
    private final Item[] placaVideoLoja;
    private final Item[] placaMaeLoja;
    private final Item[] ramLoja;
    private final Item[] gabineteLoja;
    private final Item[] monitorLoja;
    private final Item[] tecladoLoja;
    private final Item[] mouseLoja;
    
    private ConjuntoLoja[] lojas;
    
    private JTabbedPane gerenciadorAbasPrincipais;
        private JPanel abaTrabalho;
            private JPanel panelDinheiro;
                private JLabel txtDinheiro;
            private JPanel panelGeracoes;
                private JLabel labelClique;
                private JLabel labelGeracao;
            private JPanel panelProgramar;
                private JButton botaoDinheiro;
            private JPanel panelHabilidades;
                private JButton btHabilidadeGabinete;
                private JButton btHabilidadeMonitor;
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
        
        // Cria o invetário das peças
        processadorTem = new ArrayList<>();
        placaVideoTem = new ArrayList<>();
        placaMaeTem = new ArrayList<>();
        ramTem = new ArrayList<>();
        gabineteTem = new ArrayList<>();
        monitorTem = new ArrayList<>();
        tecladoTem = new ArrayList<>();
        mouseTem = new ArrayList<>();
        
        
        // Cria o computador e dá as peças iniciais
        processadorTem.add(ConstrutorPecasPadrao.Item("Processador ancestral", 0, 0, "processador/antigo"));
        placaVideoTem.add(ConstrutorPecasPadrao.Item("Placa de imagem", 0, 0, "placadevideo/antigo"));
        placaMaeTem.add(ConstrutorPecasPadrao.Item("Placa avó", 0, 0, "PlacaMae/antigo"));
        ramTem.add(ConstrutorPecasPadrao.Item("Ram sem memória", 0, 0, "ram/antigo"));
        gabineteTem.add(ConstrutorPecasPadrao.Item("Gabinete do lixo", 0, 0, "gabinete/antigo"));
        monitorTem.add(ConstrutorPecasPadrao.Item("Monitor pré-histórico", 0, 0, "monitor/antigo"));
        tecladoTem.add(ConstrutorPecasPadrao.Item("Teclado peba", 0, 0, "teclado/antigo"));
        mouseTem.add(ConstrutorPecasPadrao.Item("Mouse arcaíco", 0, 1, "mouse/antigo"));
        
        computador = new Computador(
                processadorTem.get(0), 
                placaVideoTem.get(0), 
                placaMaeTem.get(0), 
                ramTem.get(0), 
                gabineteTem.get(0), 
                monitorTem.get(0), 
                tecladoTem.get(0), 
                mouseTem.get(0));
        
        
        // Define os itens da loja
        processadorLoja = new Item[0];
        placaVideoLoja = new Item[0];
        placaMaeLoja = new Item[0];
        ramLoja = new Item[2];
        ramLoja[0] = ConstrutorPecasPadrao.RAM("DDR3 2GB", 10, 1);
        ramLoja[1] = ConstrutorPecasPadrao.RAM("DDR3 4GB", 20, 2);
        ramLoja[2] = ConstrutorPecasPadrao.RAM("DDR3 8GB", 50, 4);
        gabineteLoja = new Item[0];
        monitorLoja = new Item[0];
        tecladoLoja = new Item[0];
        mouseLoja = new Item[0];
        
        // Cria os conjuntos das lojas
        // só vai ser populado com as partes gráficas durante o "initComponents()"
        lojas = new ConjuntoLoja[8];
        lojas[0] = new ConjuntoLoja("Processadores", processadorTem, processadorLoja);
        lojas[1] = new ConjuntoLoja("Placas de vídeo", placaVideoTem, placaVideoLoja);
        lojas[2] = new ConjuntoLoja("Placas mãe", placaMaeTem, placaMaeLoja);
        lojas[3] = new ConjuntoLoja("RAMs", ramTem, ramLoja);
        lojas[4] = new ConjuntoLoja("Gabinetes", gabineteTem, gabineteLoja);
        lojas[5] = new ConjuntoLoja("Monitores", monitorTem, monitorLoja);
        lojas[6] = new ConjuntoLoja("Teclados", tecladoTem, tecladoLoja);
        lojas[7] = new ConjuntoLoja("Mouses", mouseTem, mouseLoja);
        

        // Inicia jaVisto
        jaVisto = new HashMap<>();
        String[] nomesDicas = {"Trabalho", "Loja", "PC"};
        for (String nome : nomesDicas) {
            jaVisto.put(nome, false);
        }
        
        // Inicia os componentes gráfico
        initComponents();
        atualizarImagens();
        
        // Define o ciclo do jogo
        ultimoFrame = System.currentTimeMillis();
        this.gameCycle = () -> {
            atualizarDinheiro();
            
            // Calcula deltaT
            long currentTime = System.currentTimeMillis();
            long deltaT = currentTime - ultimoFrame;
            long deltaTSegundos = (long) (deltaT / 1000.0);
            int milisegundosSobrando = (int) (deltaT % 1000);
            
            
            long geracao = (long) computador.getGeracao() * deltaTSegundos;
            
            dinheiro += geracao;
            
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
                    labelClique = new JLabel();
                    labelGeracao = new JLabel();
                panelProgramar = new JPanel();
                    botaoDinheiro = new JButton();
                panelHabilidades = new JPanel();
                    btHabilidadeMonitor = new JButton();
                    btHabilidadeGabinete = new JButton();
                panelMissoes = new JPanel();
            AbaLoja = new JPanel();
                txtDinheiroLoja = new JLabel();
                gerenciadorAbasPecas = new JTabbedPane();
                    for (ConjuntoLoja loja : lojas) {
                        loja.setAba(new JScrollPane());
                        loja.setPanel(new JPanel());
                        // Cria os botões;
                        var botoes = new JButton[loja.getLoja().length];
                        for (int i = 0; i < botoes.length; i++) {
                            botoes[i] = new JButton();
                        }
                        loja.setBotoes(botoes);
                    }
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
            AbasPrincipaisStateChanged(evt);
        });

        // Aba principal "Área de trabalho"
        abaTrabalho.setLayout(new java.awt.GridLayout(0, 1));

          // Contador de dinheiro
        txtDinheiro.setFont(new java.awt.Font("DejaVu Serif Condensed", 0, 14)); // NOI18N
        txtDinheiro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDinheiro.setText("Dinheiro: 0");
        panelDinheiro.add(txtDinheiro);
        abaTrabalho.add(panelDinheiro);

          // Geração
        labelClique.setText("Dinheiro p/clique");
        labelGeracao.setText("Geração p/s");
        panelGeracoes.add(labelClique);
        panelGeracoes.add(labelGeracao);
        abaTrabalho.add(panelGeracoes);

          // Botão de dinheiro
        botaoDinheiro.setBackground(new java.awt.Color(153, 255, 51));
        botaoDinheiro.setText("Programar");
        botaoDinheiro.addActionListener((java.awt.event.ActionEvent evt) -> {
            botaoDinheiroActionPerformed(evt);
        });
          // Segurar um botão fica spamando cliques, útil para testes
            // TODO - Remover
        botaoDinheiro.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                botaoDinheiroKeyPressed(evt);
            }
        });
        panelProgramar.add(botaoDinheiro);
        abaTrabalho.add(panelProgramar);

          // Botões das habilidades
        btHabilidadeMonitor.setVisible(false);
        btHabilidadeGabinete.setVisible(false);
        panelHabilidades.add(btHabilidadeMonitor);
        panelHabilidades.add(btHabilidadeGabinete);
        abaTrabalho.add(panelHabilidades);
        
          // Mostra as missões disponíveis
        abaTrabalho.add(panelMissoes);

          // Adiciona a aba "Trabalho" no gerenciador de abas "AbasPrincipais"
        gerenciadorAbasPrincipais.addTab("TRABALHO", abaTrabalho);

        
        // Aba da Loja
        AbaLoja.setLayout(new BoxLayout(AbaLoja, BoxLayout.Y_AXIS));

        txtDinheiroLoja.setFont(new java.awt.Font("DejaVu Serif Condensed", 0, 14)); // NOI18N
        txtDinheiroLoja.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDinheiroLoja.setText("Dinheiro: 0");
        AbaLoja.add(txtDinheiroLoja);

          // Loopa por todas as abas da loja criando ela e seus botões
        for (var loja : lojas) {
            ArrayList<Item> tem = loja.getTem();
            Item[] lojaInventario = loja.getLoja();
            JScrollPane aba = loja.getAba();
            JPanel panel = loja.getPanel();
            JButton[] bts = loja.getBotoes();
            
            aba.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            panel.setLayout(new WrapLayout());
            // Cria botões e coloca função neles
            for (int i = 0; i < lojaInventario.length; i++) {
                final int iFinal = i;
                bts[i] = new JButton();
                bts[i].setText(lojaInventario[i].getNome() + " - R$" + lojaInventario[i].getCusto());
                bts[i].addActionListener((java.awt.event.ActionEvent evt) -> {
                    comprar(lojaInventario[iFinal], tem, bts[iFinal]);
                });
                panel.add(bts[i]);
            }
            aba.setViewportView(panel);
            gerenciadorAbasPecas.addTab(loja.getNome(), null, aba, "");
        }
        AbaLoja.add(gerenciadorAbasPecas);
        gerenciadorAbasPrincipais.addTab("LOJA", AbaLoja);
        
        
        
        // Aba do computador
        abaPC.setLayout(new GridBagLayout());
        abaPC.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                //formComponentResized(evt);
            }
        });
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        
          // Monitor
            // Adiciona função trocar peça
        labelMonitor.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                abrirTrocarPeca(monitorTem, "monitor");
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
                    abrirTrocarPeca(gabineteTem, "gabinete");
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
                abrirTrocarPeca(tecladoTem, "teclado");
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
                abrirTrocarPeca(mouseTem, "mouse");
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

    private void botaoDinheiroKeyPressed(java.awt.event.KeyEvent evt) {
        dinheiro += computador.getClique();
    }

    private void botaoDinheiroActionPerformed(java.awt.event.ActionEvent evt) {
        dinheiro += computador.getClique();
        atualizarDinheiro();
        if(!jaVisto.get("Trabalho")){
            JOptionPane.showMessageDialog(rootPane, "Aqui é sua área de trabalho\nAo clicar dindin se ganha dinheiro!");
            jaVisto.put("Trabalho", true);
        }
    }
    
    private void abrirTrocarPeca(ArrayList<Item> inventario, String nome) {
        new TrocarPeca(this, inventario, computador, nome).setVisible(true);
    }
    
    private void abrirGabinete(){
        new Gabinete(this, computador, processadorTem, placaVideoTem, placaMaeTem, ramTem).setVisible(true);
    }

    private void AbasPrincipaisStateChanged(javax.swing.event.ChangeEvent evt) {
        if(gerenciadorAbasPrincipais.getSelectedIndex() == 1 ){
            if (!jaVisto.get("Loja")){
                JOptionPane.showMessageDialog(rootPane, "Esta é a loja\nAqui você pode comprar itens para melhorar seu Setup!");
                jaVisto.put("Loja", true);
            }
        } else if(gerenciadorAbasPrincipais.getSelectedIndex() == 2){
            if(!jaVisto.get("PC")){
                JOptionPane.showMessageDialog(rootPane, """
                                                        Aqui é seu PC
                                                        Você deve equipar as peças compradas para utilizá-las!
                                                         Botão esquerdo = Altera a peça clicada
                                                        Botão direito no 'gabinete' = Abre o computador para trocar-se os hardwares""");
                jaVisto.put("PC", true);
            }
        }
    }

    public void atualizarDinheiro(){
        String labelText = String.format("Dinheiro: R$%d", dinheiro);
        txtDinheiro.setText(labelText);
        txtDinheiroLoja.setText(labelText);
        labelClique.setText("Dinheiro p/Clique: " + computador.getClique());
        labelGeracao.setText("Dinheiro p/s: " + computador.getGeracao());
    }
    
    public final void atualizarImagens(){
        try {
            labelGabinete.setIcon(carregarImagem(computador.get("gabinete").getCaminhoImagem(), 128, 256));
            labelMonitor.setIcon(carregarImagem(computador.get("monitor").getCaminhoImagem(), 256, 256));
            labelTeclado.setIcon(carregarImagem(computador.get("teclado").getCaminhoImagem(), 280, 140));
            labelMouse.setIcon(carregarImagem(computador.get("mouse").getCaminhoImagem(), 140, 140));
        } catch (IOException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.WARNING, "Erro ao carregar imagem", ex);
        }
    }
    private void atualizarTamanhoImagens() {
        
    }
    
    public Icon carregarImagem(String caminho, int largura, int altura) throws IOException{
        return new ImageIcon(ImageIO.read(getClass().getResource("/main/resources/images/" + caminho + ".png")).getScaledInstance(largura, altura, Image.SCALE_DEFAULT));
    }
    
    private void comprar(Item item, ArrayList lista, JButton botao) {
        if (dinheiro >= item.getCusto()){
            dinheiro -= item.getCusto();
            botao.setVisible(false);
            lista.add(item);
        }
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
