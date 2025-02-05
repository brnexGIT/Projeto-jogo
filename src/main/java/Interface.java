package main.java;
import java.awt.Image;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Interface extends javax.swing.JFrame {

    int dinheiro = 50000000;
    
    Computador computador;
    
    private long ultimoFrame = System.currentTimeMillis(); 
    private final Runnable gameCycle;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    
    
    ArrayList<Item> processadorTem;
    ArrayList<Item> placaVideoTem;
    ArrayList<Item> placaMaeTem;
    ArrayList<Item> ramTem;
    ArrayList<Item> gabineteTem;
    ArrayList<Item> monitorTem;
    ArrayList<Item> tecladoTem;
    ArrayList<Item> mouseTem;
    
    Item[] processadorLoja;
    Item[] placaVideoLoja;
    Item[] placaMaeLoja;
    Item[] ramLoja;
    Item[] gabineteLoja;
    Item[] monitorLoja;
    Item[] tecladoLoja;
    Item[] mouseLoja;
    
    
    
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
        
        // Inicia os componentes gráficos
        initComponents();
        atualizarImagens();
        
        // Define os itens da loja
        ramLoja = new Item[3];
        ramLoja[0] = ConstrutorPecasPadrao.RAM("DDR3 2GB", 10, 1, btRAM1);
        ramLoja[1] = ConstrutorPecasPadrao.RAM("DDR3 4GB", 20, 2, btRAM2);
        ramLoja[2] = ConstrutorPecasPadrao.RAM("DDR3 8GB", 50, 4, btRAM3);
        
        monitorLoja = new Item[4];
        monitorLoja[0] = ConstrutorPecasPadrao.Item("Monitor 1", 100, 0, "monitor/positivo capitão", btMonitor1);
        monitorLoja[1] = ConstrutorPecasPadrao.Item("Monitor 2", 200, 0, "monitor/clt", btMonitor2);
        monitorLoja[2] = ConstrutorPecasPadrao.Item("Monitor 3", 300, 0, "monitor/LGbt", btMonitor3);
        monitorLoja[3] = ConstrutorPecasPadrao.Item("Monitor 4", 400, 0, "monitor/ultra whinderson", btMonitor4);
        
        tecladoLoja = new Item[4];
        tecladoLoja[0] = ConstrutorPecasPadrao.Item("Tecl...", 100, 20, "teclado/projeto de algo", btTeclado1);
        tecladoLoja[1] = ConstrutorPecasPadrao.Item("Teclado base", 300, 40, "teclado/escritorio clt", btTeclado2);
        tecladoLoja[2] = ConstrutorPecasPadrao.Item("Teclado gamer", 1500, 50, "teclado/gamer", btTeclado3);
        tecladoLoja[3] = ConstrutorPecasPadrao.Item("Teclado GAMER 2000", 10000, 100, "teclado/dragão ball xl", btTeclado4);
        
        mouseLoja = new Item[4];
        mouseLoja[0] = ConstrutorPecasPadrao.Item("Yellow jaqueta", 100, 5, "mouse/gamer", btMouse1);
        mouseLoja[1] = ConstrutorPecasPadrao.Item("Dragon ball", 5000, 30, "mouse/dragonball", btMouse2);
        mouseLoja[2] = ConstrutorPecasPadrao.Item("Hotwheels ataque do tuburão", 10000, 50, "mouse/shark attack", btMouse3);
        mouseLoja[3] = ConstrutorPecasPadrao.Item("Mouse Gamer", 100000, 100 , "mouse/clicador 2000", btMouse4);
        
        gabineteLoja = new Item[4];
        gabineteLoja[0] = ConstrutorPecasPadrao.Item("Antigo", 100, 5, "gabinete/astethics 1", btMouse1);
        gabineteLoja[1] = ConstrutorPecasPadrao.Item("Diferente dos demais", 100, 5, "gabinete/emo 2", btMouse1);
        gabineteLoja[2] = ConstrutorPecasPadrao.Item("Com LUZINHA", 100, 5, "mouse/vagalume 3", btMouse1);
        gabineteLoja[3] = ConstrutorPecasPadrao.Item("O optimus prime", 100, 5, "mouse/escoliose 4", btMouse1);
        
        
        // Define o ciclo do jogo
        ultimoFrame = System.currentTimeMillis();
        this.gameCycle = new Runnable() {
            @Override
            public void run() {
                atualizarDinheiro();
 
                // Calcula deltaT
                long currentTime = System.currentTimeMillis();
                long deltaT = currentTime - ultimoFrame;
                long deltaTSegundos = (long) (deltaT / 1000.0);
                int milisegundosSobrando = (int) (deltaT % 1000);
 
 
                long geracao = (long) computador.getGeracao() * deltaTSegundos;
 
                dinheiro += geracao;
 
                ultimoFrame = System.currentTimeMillis() - milisegundosSobrando;
            }
        };
        scheduler.scheduleAtFixedRate(gameCycle, 0, 16, TimeUnit.MILLISECONDS);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu4 = new javax.swing.JMenu();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        Trabalho = new javax.swing.JPanel();
        botaoDinheiro = new javax.swing.JButton();
        txtDinheiro = new javax.swing.JLabel();
        labelGeracao = new javax.swing.JLabel();
        labelClique = new javax.swing.JLabel();
        Loja = new javax.swing.JPanel();
        txtDinheiroLoja = new javax.swing.JLabel();
        AbasPecas = new javax.swing.JTabbedPane();
        AbaProcessador = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        btProcessadori5 = new javax.swing.JButton();
        AbaPlacaDeVideo = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        btPlacaDeVideo = new javax.swing.JButton();
        AbaPlacaMae = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        AbaRam = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        btRAM1 = new javax.swing.JButton();
        btRAM2 = new javax.swing.JButton();
        btRAM3 = new javax.swing.JButton();
        btRAM4 = new javax.swing.JButton();
        btRAM5 = new javax.swing.JButton();
        btRAM6 = new javax.swing.JButton();
        AbaMouse = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        btMouse1 = new javax.swing.JButton();
        btMouse2 = new javax.swing.JButton();
        btMouse4 = new javax.swing.JButton();
        btMouse3 = new javax.swing.JButton();
        AbaTeclado = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        btTeclado1 = new javax.swing.JButton();
        btTeclado2 = new javax.swing.JButton();
        btTeclado3 = new javax.swing.JButton();
        btTeclado4 = new javax.swing.JButton();
        AbaMonitor = new javax.swing.JScrollPane();
        jPanel8 = new javax.swing.JPanel();
        btMonitor1 = new javax.swing.JButton();
        btMonitor2 = new javax.swing.JButton();
        btMonitor3 = new javax.swing.JButton();
        btMonitor4 = new javax.swing.JButton();
        PC = new javax.swing.JPanel();
        labelMonitor = new javax.swing.JLabel();
        labelTeclado = new javax.swing.JLabel();
        labelMouse = new javax.swing.JLabel();
        labelGabinete = new javax.swing.JLabel();

        jMenu4.setText("jMenu4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        botaoDinheiro.setBackground(new java.awt.Color(153, 255, 51));
        botaoDinheiro.setText("DIN DIN");
        botaoDinheiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoDinheiroActionPerformed(evt);
            }
        });
        botaoDinheiro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                botaoDinheiroKeyPressed(evt);
            }
        });

        txtDinheiro.setFont(new java.awt.Font("DejaVu Serif Condensed", 0, 14)); // NOI18N
        txtDinheiro.setText("Dinheiro: 0");
        txtDinheiro.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtDinheiroPropertyChange(evt);
            }
        });

        labelGeracao.setText("Geração p/s");

        labelClique.setText("Dinheiro p/clique");

        javax.swing.GroupLayout TrabalhoLayout = new javax.swing.GroupLayout(Trabalho);
        Trabalho.setLayout(TrabalhoLayout);
        TrabalhoLayout.setHorizontalGroup(
            TrabalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TrabalhoLayout.createSequentialGroup()
                .addGap(276, 276, 276)
                .addGroup(TrabalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botaoDinheiro, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                    .addGroup(TrabalhoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtDinheiro)))
                .addGap(277, 277, 277))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TrabalhoLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(labelGeracao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelClique)
                .addGap(74, 74, 74))
        );
        TrabalhoLayout.setVerticalGroup(
            TrabalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TrabalhoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(txtDinheiro, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(TrabalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelClique)
                    .addComponent(labelGeracao))
                .addGap(144, 144, 144)
                .addComponent(botaoDinheiro, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addGap(78, 78, 78))
        );

        jTabbedPane1.addTab("TRABALHO", Trabalho);

        txtDinheiroLoja.setFont(new java.awt.Font("DejaVu Serif Condensed", 0, 14)); // NOI18N
        txtDinheiroLoja.setText("Dinheiro: 0");
        txtDinheiroLoja.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtDinheiroLojaPropertyChange(evt);
            }
        });

        btProcessadori5.setText("PROCESSADOR I5 R$200");
        btProcessadori5.setToolTipText("Aumenta o valor do Click em +1!");
        btProcessadori5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btProcessadori5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProcessadori5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btProcessadori5, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(366, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(btProcessadori5)
                .addContainerGap(244, Short.MAX_VALUE))
        );

        AbaProcessador.setViewportView(jPanel2);

        AbasPecas.addTab("PROCESSADOR", AbaProcessador);

        btPlacaDeVideo.setText("PLACA DE VIDEO R$10");
        btPlacaDeVideo.setToolTipText("Gera R$1 por segundo!");
        btPlacaDeVideo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPlacaDeVideoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btPlacaDeVideo, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(377, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(btPlacaDeVideo)
                .addContainerGap(221, Short.MAX_VALUE))
        );

        AbaPlacaDeVideo.setViewportView(jPanel1);

        AbasPecas.addTab("PLACA DE VIDEO", AbaPlacaDeVideo);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 612, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 292, Short.MAX_VALUE)
        );

        AbaPlacaMae.setViewportView(jPanel3);

        AbasPecas.addTab("PLACA MÃE", AbaPlacaMae);

        btRAM1.setText("1");
        btRAM1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRAM1ActionPerformed(evt);
            }
        });

        btRAM2.setText("2");
        btRAM2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRAM2ActionPerformed(evt);
            }
        });

        btRAM3.setText("3");
        btRAM3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRAM3ActionPerformed(evt);
            }
        });

        btRAM4.setText("4");
        btRAM4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRAM4ActionPerformed(evt);
            }
        });

        btRAM5.setText("5");
        btRAM5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRAM5ActionPerformed(evt);
            }
        });

        btRAM6.setText("6");
        btRAM6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRAM6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btRAM5, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btRAM1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btRAM3, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btRAM2, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btRAM4, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btRAM6, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btRAM1)
                    .addComponent(btRAM2))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btRAM3)
                    .addComponent(btRAM4))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btRAM5)
                    .addComponent(btRAM6))
                .addContainerGap(158, Short.MAX_VALUE))
        );

        AbaRam.setViewportView(jPanel4);

        AbasPecas.addTab("RAM", AbaRam);

        btMouse1.setText("MOUSE GAMER R$50");
        btMouse1.setToolTipText("Aumenta o valor do Click em +1!");
        btMouse1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMouse1ActionPerformed(evt);
            }
        });

        btMouse2.setText("MOUSE GAMER R$50");
        btMouse2.setToolTipText("Aumenta o valor do Click em +1!");
        btMouse2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMouse2ActionPerformed(evt);
            }
        });

        btMouse4.setText("MOUSE GAMER R$50");
        btMouse4.setToolTipText("Aumenta o valor do Click em +1!");
        btMouse4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMouse4ActionPerformed(evt);
            }
        });

        btMouse3.setText("MOUSE GAMER R$50");
        btMouse3.setToolTipText("Aumenta o valor do Click em +1!");
        btMouse3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMouse3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btMouse3, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                    .addComponent(btMouse1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btMouse4, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                    .addComponent(btMouse2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btMouse1)
                    .addComponent(btMouse2))
                .addGap(41, 41, 41)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btMouse3)
                    .addComponent(btMouse4))
                .addContainerGap(168, Short.MAX_VALUE))
        );

        AbaMouse.setViewportView(jPanel5);

        AbasPecas.addTab("MOUSE", AbaMouse);

        btTeclado1.setText("TECLADO R$100");
        btTeclado1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTeclado1ActionPerformed(evt);
            }
        });

        btTeclado2.setText("TECLADO R$100");
        btTeclado2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTeclado2ActionPerformed(evt);
            }
        });

        btTeclado3.setText("TECLADO R$100");
        btTeclado3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTeclado3ActionPerformed(evt);
            }
        });

        btTeclado4.setText("TECLADO R$100");
        btTeclado4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTeclado4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btTeclado1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addComponent(btTeclado2, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btTeclado3, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btTeclado4, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btTeclado1)
                    .addComponent(btTeclado2))
                .addGap(58, 58, 58)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btTeclado3)
                    .addComponent(btTeclado4))
                .addContainerGap(149, Short.MAX_VALUE))
        );

        AbaTeclado.setViewportView(jPanel6);

        AbasPecas.addTab("TECLADO", AbaTeclado);

        btMonitor1.setText("1");
        btMonitor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMonitor1ActionPerformed(evt);
            }
        });

        btMonitor2.setText("2");
        btMonitor2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMonitor2ActionPerformed(evt);
            }
        });

        btMonitor3.setText("3");
        btMonitor3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMonitor3ActionPerformed(evt);
            }
        });

        btMonitor4.setText("4");
        btMonitor4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMonitor4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btMonitor1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btMonitor3, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btMonitor2, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btMonitor4, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btMonitor1)
                    .addComponent(btMonitor2))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btMonitor3)
                    .addComponent(btMonitor4))
                .addContainerGap(199, Short.MAX_VALUE))
        );

        AbaMonitor.setViewportView(jPanel8);

        AbasPecas.addTab("MONITOR", AbaMonitor);

        javax.swing.GroupLayout LojaLayout = new javax.swing.GroupLayout(Loja);
        Loja.setLayout(LojaLayout);
        LojaLayout.setHorizontalGroup(
            LojaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LojaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AbasPecas)
                .addContainerGap())
            .addGroup(LojaLayout.createSequentialGroup()
                .addGap(272, 272, 272)
                .addComponent(txtDinheiroLoja)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        LojaLayout.setVerticalGroup(
            LojaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LojaLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(txtDinheiroLoja, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(AbasPecas, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("LOJA", Loja);

        labelMonitor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resources/images/monitor/antigo.png"))); // NOI18N
        labelMonitor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelMonitorMouseClicked(evt);
            }
        });

        labelTeclado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resources/images/teclado/antigo.png"))); // NOI18N
        labelTeclado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelTecladoMouseClicked(evt);
            }
        });

        labelMouse.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        labelMouse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMouse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resources/images/mouse/antigo.png"))); // NOI18N
        labelMouse.setMaximumSize(new java.awt.Dimension(300, 100));
        labelMouse.setMinimumSize(new java.awt.Dimension(300, 100));
        labelMouse.setPreferredSize(new java.awt.Dimension(300, 100));
        labelMouse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelMouseMouseClicked(evt);
            }
        });

        labelGabinete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resources/images/gabinete/antigo.png"))); // NOI18N
        labelGabinete.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                labelGabineteAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        labelGabinete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelGabineteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PCLayout = new javax.swing.GroupLayout(PC);
        PC.setLayout(PCLayout);
        PCLayout.setHorizontalGroup(
            PCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PCLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(PCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PCLayout.createSequentialGroup()
                        .addComponent(labelTeclado, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelMouse, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PCLayout.createSequentialGroup()
                        .addComponent(labelMonitor)
                        .addGap(39, 39, 39)
                        .addComponent(labelGabinete)))
                .addContainerGap(104, Short.MAX_VALUE))
        );
        PCLayout.setVerticalGroup(
            PCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PCLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(PCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelGabinete)
                    .addComponent(labelMonitor, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(PCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelMouse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelTeclado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32))
        );

        jTabbedPane1.addTab("PC", PC);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btTeclado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTeclado1ActionPerformed
        comprar(tecladoLoja[0], tecladoTem, btTeclado1);
    }//GEN-LAST:event_btTeclado1ActionPerformed

    private void btPlacaDeVideoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPlacaDeVideoActionPerformed
        
    }//GEN-LAST:event_btPlacaDeVideoActionPerformed

    private void txtDinheiroLojaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtDinheiroLojaPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDinheiroLojaPropertyChange

    private void btMouse1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMouse1ActionPerformed
        comprar(mouseLoja[0], mouseTem, btMouse1);
    }//GEN-LAST:event_btMouse1ActionPerformed

    private void txtDinheiroPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtDinheiroPropertyChange

    }//GEN-LAST:event_txtDinheiroPropertyChange

    private void botaoDinheiroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_botaoDinheiroKeyPressed
        // TODO add your handling code here:
        dinheiro += computador.getClique();
    }//GEN-LAST:event_botaoDinheiroKeyPressed

    private void botaoDinheiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoDinheiroActionPerformed
        dinheiro += computador.getClique();
        System.out.println(computador.getGeracao());
        atualizarDinheiro();
        System.out.println(evt.getModifiers());
    }//GEN-LAST:event_botaoDinheiroActionPerformed

    private void btProcessadori5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProcessadori5ActionPerformed
        // TODO add your handling code here:
        if (dinheiro >= 200){
            dinheiro = dinheiro - 200;
            JOptionPane.showMessageDialog(rootPane, "compra realizada");
            btProcessadori5.setVisible(false);
        }
    }//GEN-LAST:event_btProcessadori5ActionPerformed

    private void btRAM1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRAM1ActionPerformed
        comprar(ramLoja[0], ramTem, btRAM1);
    }//GEN-LAST:event_btRAM1ActionPerformed

    private void btRAM2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRAM2ActionPerformed
        comprar(ramLoja[1], ramTem, btRAM2);
    }//GEN-LAST:event_btRAM2ActionPerformed

    private void btRAM3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRAM3ActionPerformed
        comprar(ramLoja[2], ramTem, btRAM3);
    }//GEN-LAST:event_btRAM3ActionPerformed

    private void btRAM4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRAM4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btRAM4ActionPerformed

    private void btRAM5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRAM5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btRAM5ActionPerformed

    private void btRAM6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRAM6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btRAM6ActionPerformed

    private void labelTecladoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelTecladoMouseClicked
        new TrocarPeca(this, tecladoTem, computador, "teclado").setVisible(true);
    }//GEN-LAST:event_labelTecladoMouseClicked

    private void labelMouseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMouseMouseClicked
        new TrocarPeca(this, mouseTem, computador, "mouse").setVisible(true);
    }//GEN-LAST:event_labelMouseMouseClicked

    private void labelGabineteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelGabineteMouseClicked
        
        
        if(evt.getButton() == 3){
            new Gabinete(this, computador, processadorTem, placaVideoTem, placaMaeTem, ramTem).setVisible(true);  
            
        }
        if(evt.getButton() == 1){
            new TrocarPeca(this, gabineteTem, computador, "gabinete").setVisible(true);
            
        }
        
        
    }//GEN-LAST:event_labelGabineteMouseClicked

    private void labelMonitorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMonitorMouseClicked
        new TrocarPeca(this, monitorTem, computador, "monitor").setVisible(true);
    }//GEN-LAST:event_labelMonitorMouseClicked

    private void labelGabineteAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_labelGabineteAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_labelGabineteAncestorAdded

    private void btMonitor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMonitor1ActionPerformed
        // TODO add your handling code here:
        comprar(monitorLoja[0], monitorTem, btMonitor1);
    }//GEN-LAST:event_btMonitor1ActionPerformed

    private void btMonitor2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMonitor2ActionPerformed
        // TODO add your handling code here:
        comprar(monitorLoja[1], monitorTem, btMonitor2);
    }//GEN-LAST:event_btMonitor2ActionPerformed

    private void btMonitor3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMonitor3ActionPerformed
        // TODO add your handling code here:
        comprar(monitorLoja[2], monitorTem, btMonitor3);
    }//GEN-LAST:event_btMonitor3ActionPerformed

    private void btMonitor4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMonitor4ActionPerformed
        // TODO add your handling code here:
        comprar(monitorLoja[3], monitorTem, btMonitor4);
    }//GEN-LAST:event_btMonitor4ActionPerformed

    private void btMouse2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMouse2ActionPerformed
        // TODO add your handling code here:
        comprar(mouseLoja[1], mouseTem, btMouse2);
    }//GEN-LAST:event_btMouse2ActionPerformed

    private void btMouse4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMouse4ActionPerformed
        // TODO add your handling code here:
        comprar(mouseLoja[3], mouseTem, btMouse4);
    }//GEN-LAST:event_btMouse4ActionPerformed

    private void btMouse3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMouse3ActionPerformed
        // TODO add your handling code here:
        comprar(mouseLoja[2], mouseTem, btMouse3);
    }//GEN-LAST:event_btMouse3ActionPerformed

    private void btTeclado2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTeclado2ActionPerformed
        // TODO add your handling code here:
        comprar(tecladoLoja[1], tecladoTem, btTeclado2);
    }//GEN-LAST:event_btTeclado2ActionPerformed

    private void btTeclado3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTeclado3ActionPerformed
        // TODO add your handling code here:
        comprar(tecladoLoja[2], tecladoTem, btTeclado3);
    }//GEN-LAST:event_btTeclado3ActionPerformed

    private void btTeclado4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTeclado4ActionPerformed
        // TODO add your handling code here:
        comprar(tecladoLoja[3], tecladoTem, btTeclado4);
    }//GEN-LAST:event_btTeclado4ActionPerformed

    public void atualizarDinheiro(){
        String labelText = String.format("Dinheiro: R$%d", dinheiro);
        txtDinheiro.setText(labelText);
        txtDinheiroLoja.setText(labelText);
        labelClique.setText("Dinheiro p/Clique: " + computador.getClique());
        labelGeracao.setText("Dinheiro p/s: " + computador.getGeracao());
    }
    
    public void atualizarImagens(){
        try {
            labelGabinete.setIcon(carregarImagem(computador.get("gabinete").getCaminhoImagem(), 225, 225));
            labelMonitor.setIcon(carregarImagem(computador.get("monitor").getCaminhoImagem(), 222, 227));
            labelTeclado.setIcon(carregarImagem(computador.get("teclado").getCaminhoImagem(), 280, 100));
            labelMouse.setIcon(carregarImagem(computador.get("mouse").getCaminhoImagem(), 230, 100));
        } catch (IOException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.WARNING, "Erro ao carregar imagem", ex);
        }
    }
    
    public Icon carregarImagem(String caminho, int largura, int altura) throws IOException{
        return new ImageIcon(ImageIO.read(getClass().getResource("/main/resources/images/" + caminho + ".png")).getScaledInstance(largura, altura, Image.SCALE_DEFAULT));
    }
    
    private void comprar(Item item, ArrayList lista, javax.swing.JButton botao) {
        if (dinheiro >= item.getCusto()){
            dinheiro -= item.getCusto();
            botao.setVisible(false);
            lista.add(item);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interface().setVisible(true);
            }
        });
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane AbaMonitor;
    private javax.swing.JScrollPane AbaMouse;
    private javax.swing.JScrollPane AbaPlacaDeVideo;
    private javax.swing.JScrollPane AbaPlacaMae;
    private javax.swing.JScrollPane AbaProcessador;
    private javax.swing.JScrollPane AbaRam;
    private javax.swing.JScrollPane AbaTeclado;
    private javax.swing.JTabbedPane AbasPecas;
    private javax.swing.JPanel Loja;
    private javax.swing.JPanel PC;
    private javax.swing.JPanel Trabalho;
    private javax.swing.JButton botaoDinheiro;
    private javax.swing.JButton btMonitor1;
    private javax.swing.JButton btMonitor2;
    private javax.swing.JButton btMonitor3;
    private javax.swing.JButton btMonitor4;
    private javax.swing.JButton btMouse1;
    private javax.swing.JButton btMouse2;
    private javax.swing.JButton btMouse3;
    private javax.swing.JButton btMouse4;
    private javax.swing.JButton btPlacaDeVideo;
    private javax.swing.JButton btProcessadori5;
    private javax.swing.JButton btRAM1;
    private javax.swing.JButton btRAM2;
    private javax.swing.JButton btRAM3;
    private javax.swing.JButton btRAM4;
    private javax.swing.JButton btRAM5;
    private javax.swing.JButton btRAM6;
    private javax.swing.JButton btTeclado1;
    private javax.swing.JButton btTeclado2;
    private javax.swing.JButton btTeclado3;
    private javax.swing.JButton btTeclado4;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelClique;
    private javax.swing.JLabel labelGabinete;
    private javax.swing.JLabel labelGeracao;
    private javax.swing.JLabel labelMonitor;
    private javax.swing.JLabel labelMouse;
    private javax.swing.JLabel labelTeclado;
    private javax.swing.JLabel txtDinheiro;
    private javax.swing.JLabel txtDinheiroLoja;
    // End of variables declaration//GEN-END:variables
}
