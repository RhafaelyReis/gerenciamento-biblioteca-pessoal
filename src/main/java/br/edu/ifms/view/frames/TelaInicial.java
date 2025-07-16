package br.edu.ifms.view.frames;

import br.edu.ifms.controller.GerenciarItensController;
import br.edu.ifms.view.styles.ButtonStyles;
import br.edu.ifms.view.styles.StyleConstants;
import javax.swing.*;
import java.awt.*;

public class TelaInicial extends JFrame {
    
    private CardLayout cardLayout;
    private JPanel painelPrincipal;
    private static final String TELA_MENU = "MENU";
    private static final String TELA_GERENCIAR = "GERENCIAR";
    private static final String TELA_METRICAS = "METRICAS";
    private static final String TELA_MEUS_ITENS = "MEUS_ITENS";
    
    private TelaGerenciarItens telaGerenciar;
    private TelaMetricas telaMetricas;
    private TelaMeusItens telaMeusItens;
    
    private GerenciarItensController gerenciarItensController;
    
    public TelaInicial() {
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Biblioteca Pessoal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        getContentPane().setBackground(StyleConstants.SECONDARY_COLOR);
        
        cardLayout = new CardLayout();
        painelPrincipal = new JPanel(cardLayout);
        
        JPanel painelMenu = criarPainelMenu();

        telaGerenciar = new TelaGerenciarItens(this);
        
        gerenciarItensController = new GerenciarItensController(
            telaGerenciar,
            telaGerenciar.getLivros(),
            telaGerenciar.getEbooks(),
            telaGerenciar.getAudiobooks()
        );
        gerenciarItensController.initController();
        
        painelPrincipal.add(painelMenu, TELA_MENU);
        painelPrincipal.add(telaGerenciar.getContentPane(), TELA_GERENCIAR);
        
        add(painelPrincipal);
        
        cardLayout.show(painelPrincipal, TELA_MENU);
    }
    
    private JPanel criarPainelMenu() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(StyleConstants.SECONDARY_COLOR);
        
        JLabel titulo = new JLabel("Sistema de Biblioteca Pessoal");
        titulo.setFont(StyleConstants.TITLE);
        titulo.setForeground(StyleConstants.PRIMARY_COLOR);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        painel.add(titulo, BorderLayout.NORTH);
        
        JPanel painelBotoes = new JPanel(new GridBagLayout());
        painelBotoes.setBackground(StyleConstants.SECONDARY_COLOR);
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JButton btnGerenciarItens = criarBotao("Gerenciar Itens", false);
        btnGerenciarItens.setToolTipText("Adicione, edite ou remova livros, ebooks e audiobooks.");
        JButton btnMetricas = criarBotao("Métricas", false);
        btnMetricas.setToolTipText("Visualize estatísticas sobre sua coleção.");
        JButton btnMeusItens = criarBotao("Meus Itens", false);
        btnMeusItens.setToolTipText("Veja todos os itens da sua biblioteca em um só lugar.");
        JButton btnSair = criarBotao("Sair", true);
        btnSair.setToolTipText("Encerrar o aplicativo.");
        
        gbc.gridy = 0; painelBotoes.add(btnGerenciarItens, gbc);
        gbc.gridy = 1; painelBotoes.add(btnMetricas, gbc);
        gbc.gridy = 2; painelBotoes.add(btnMeusItens, gbc);
        gbc.gridy = 3; gbc.insets = new Insets(20, 0, 10, 0); painelBotoes.add(btnSair, gbc);
        
        painel.add(painelBotoes, BorderLayout.CENTER);
        
        JLabel rodape = new JLabel("Gerencie sua biblioteca pessoal");
        rodape.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        rodape.setForeground(StyleConstants.PRIMARY_COLOR);
        rodape.setHorizontalAlignment(SwingConstants.CENTER);
        rodape.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        painel.add(rodape, BorderLayout.SOUTH);
        
        configurarEventos(btnGerenciarItens, btnMetricas, btnMeusItens, btnSair);
        
        return painel;
    }
    
    private JButton criarBotao(String texto, boolean isDanger) {
        JButton botao = new JButton(texto);
        botao.setPreferredSize(new Dimension(200, 40));
        if (isDanger) ButtonStyles.applyDangerStyle(botao); else ButtonStyles.applyDefaultStyle(botao);
        return botao;
    }
    
    private void configurarEventos(JButton btnGerenciarItens, JButton btnMetricas, JButton btnMeusItens, JButton btnSair) {
        btnGerenciarItens.addActionListener(e -> abrirGerenciarItens());
        btnMetricas.addActionListener(e -> abrirMetricas());
        btnMeusItens.addActionListener(e -> abrirMeusItens());
        btnSair.addActionListener(e -> sairAplicacao());
    }
    
    private void abrirGerenciarItens() {
        cardLayout.show(painelPrincipal, TELA_GERENCIAR);
    }
    
    private void abrirMetricas() {
        if (telaMetricas != null) {
            painelPrincipal.remove(telaMetricas.getContentPane());
        }
        telaMetricas = new TelaMetricas(this, telaGerenciar.getLivros(), telaGerenciar.getEbooks(), telaGerenciar.getAudiobooks());
        painelPrincipal.add(telaMetricas.getContentPane(), TELA_METRICAS);
        cardLayout.show(painelPrincipal, TELA_METRICAS);
    }
    
    private void abrirMeusItens() {
        if (telaMeusItens != null) {
            painelPrincipal.remove(telaMeusItens);
        }
        telaMeusItens = new TelaMeusItens(this, telaGerenciar.getLivros(), telaGerenciar.getEbooks(), telaGerenciar.getAudiobooks());
        painelPrincipal.add(telaMeusItens, TELA_MEUS_ITENS);
        cardLayout.show(painelPrincipal, TELA_MEUS_ITENS);
    }
    
    private void sairAplicacao() {
        int resposta = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja sair?", "Confirmar Saída", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) System.exit(0);
    }
    
    public void voltarParaMenu() {
        cardLayout.show(painelPrincipal, TELA_MENU);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaInicial().setVisible(true));
    }
}