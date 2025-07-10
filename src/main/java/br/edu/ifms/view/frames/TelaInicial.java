package br.edu.ifms.view.frames;

import br.edu.ifms.view.styles.ButtonStyles;
import br.edu.ifms.view.styles.StyleConstants;
import javax.swing.*;
import java.awt.*;

public class TelaInicial extends JFrame {
    
    public TelaInicial() {
        initComponents();
    }
    
    private void initComponents() {
        // Configuração da janela
        setTitle("Biblioteca Pessoal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(StyleConstants.SECONDARY_COLOR);
        
        // Layout principal
        setLayout(new BorderLayout());
        
        // Título
        JLabel titulo = new JLabel("Sistema de Biblioteca Pessoal");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setForeground(StyleConstants.PRIMARY_COLOR);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        add(titulo, BorderLayout.NORTH);
        
        // Painel dos botões
        JPanel painelBotoes = new JPanel(new GridBagLayout());
        painelBotoes.setBackground(StyleConstants.SECONDARY_COLOR);
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Criação e configuração inicial dos botões
        JButton btnGerenciarItens = criarBotao("Gerenciar Itens", false);
        JButton btnMetricas = criarBotao("Métricas", false);
        JButton btnMeusItens = criarBotao("Meus Itens", false);
        JButton btnSair = criarBotao("Sair", true);
        
        // Adicionando os botões ao painel e configurando a posição
        gbc.gridy = 0;
        painelBotoes.add(btnGerenciarItens, gbc);
        
        gbc.gridy = 1;
        painelBotoes.add(btnMetricas, gbc);
        
        gbc.gridy = 2;
        painelBotoes.add(btnMeusItens, gbc);
        
        gbc.gridy = 3;
        gbc.insets = new Insets(20, 0, 10, 0);
        painelBotoes.add(btnSair, gbc);
        
        add(painelBotoes, BorderLayout.CENTER);
        
        // Rodapé
        JLabel rodape = new JLabel("Gerencie sua biblioteca pessoal");
        rodape.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        rodape.setForeground(StyleConstants.PRIMARY_COLOR);
        rodape.setHorizontalAlignment(SwingConstants.CENTER);
        rodape.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        add(rodape, BorderLayout.SOUTH);
        
        // Configuração dos eventos
        configurarEventos(btnGerenciarItens, btnMetricas, btnMeusItens, btnSair);
    }
    
    private JButton criarBotao(String texto, boolean isDanger) {
        JButton botao = new JButton(texto);
        botao.setPreferredSize(new Dimension(200, 40));
        
        if (isDanger) {
            ButtonStyles.applyDangerStyle(botao);
        } else {
            ButtonStyles.applyDefaultStyle(botao);
        }
        
        return botao;
    }
    
    private void configurarEventos(JButton btnGerenciarItens, JButton btnMetricas, 
                                  JButton btnMeusItens, JButton btnSair) {
        
        btnGerenciarItens.addActionListener(e -> abrirGerenciarItens());
        btnMetricas.addActionListener(e -> abrirMetricas());
        btnMeusItens.addActionListener(e -> abrirMeusItens());
        btnSair.addActionListener(e -> sairAplicacao());
    }
    
    private void abrirGerenciarItens() {
        
    }
    
    private void abrirMetricas() {
        
    }
    
    private void abrirMeusItens() {
        
    }
    
    private void sairAplicacao() {
        int resposta = JOptionPane.showConfirmDialog(this,
            "Tem certeza que deseja sair?",
            "Confirmar Saída",
            JOptionPane.YES_NO_OPTION);
        
        if (resposta == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaInicial().setVisible(true);
        });
    }
}