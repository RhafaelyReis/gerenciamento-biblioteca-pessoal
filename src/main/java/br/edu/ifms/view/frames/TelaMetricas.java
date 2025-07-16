package br.edu.ifms.view.frames;

import br.edu.ifms.model.*;
import br.edu.ifms.view.styles.ButtonStyles;
import br.edu.ifms.view.styles.StyleConstants;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaMetricas extends JPanel {
    
    private TelaInicial telaPrincipal;
    private List<Livro> livros;
    private List<Ebook> ebooks;
    private List<Audiobook> audiobooks;
    
    // Componentes da interface
    private JLabel lblTotalItens;
    private JLabel lblTotalLidos;
    private JLabel lblTotalLivros;
    private JLabel lblTotalEbooks;
    private JLabel lblTotalAudiobooks;
    private JLabel lblMediaAvaliacoes;
    private JButton btnVoltar;
    private JButton btnAtualizar;
    
    public TelaMetricas(TelaInicial telaPrincipal, List<Livro> livros, 
                        List<Ebook> ebooks, List<Audiobook> audiobooks) {
        this.telaPrincipal = telaPrincipal;
        this.livros = livros;
        this.ebooks = ebooks;
        this.audiobooks = audiobooks;
        initComponents();
        configurarEventos();
        atualizarMetricas();
    }
    
    private void initComponents() {
        setBackground(StyleConstants.SECONDARY_COLOR);
        setLayout(new BorderLayout());
        
        // Título
        JLabel titulo = new JLabel("Métricas da Biblioteca");
        titulo.setFont(StyleConstants.TITLE);
        titulo.setForeground(StyleConstants.PRIMARY_COLOR);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        add(titulo, BorderLayout.NORTH);
        
        // Painel central com métricas
        JPanel painelCentral = new JPanel(new GridBagLayout());
        painelCentral.setBackground(StyleConstants.SECONDARY_COLOR);
        painelCentral.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 20, 15, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Card para Total de Itens
        JPanel cardTotalItens = criarCardMetrica("Total de livros na sua biblioteca", "0");
        lblTotalItens = (JLabel) ((JPanel) cardTotalItens.getComponent(1)).getComponent(0);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        painelCentral.add(cardTotalItens, gbc);
        
        // Card para Itens Lidos
        JPanel cardTotalLidos = criarCardMetrica("Livros lidos", "0");
        lblTotalLidos = (JLabel) ((JPanel) cardTotalLidos.getComponent(1)).getComponent(0);
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        painelCentral.add(cardTotalLidos, gbc);
        
        // Card para Média de Avaliações
        JPanel cardMediaAvaliacoes = criarCardMetrica("Média de Avaliações", "0.0");
        lblMediaAvaliacoes = (JLabel) ((JPanel) cardMediaAvaliacoes.getComponent(1)).getComponent(0);
        gbc.gridx = 1; gbc.gridy = 1;
        painelCentral.add(cardMediaAvaliacoes, gbc);
        
        // Seção de Distribuição por Tipo
        JPanel painelDistribuicao = new JPanel(new BorderLayout());
        painelDistribuicao.setBackground(StyleConstants.SECONDARY_HOVER);
        painelDistribuicao.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(StyleConstants.PRIMARY_COLOR, 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        JLabel tituloDistribuicao = new JLabel("Quantidade por tipo");
        tituloDistribuicao.setFont(StyleConstants.FONT_BOLD);
        tituloDistribuicao.setForeground(StyleConstants.PRIMARY_COLOR);
        tituloDistribuicao.setHorizontalAlignment(SwingConstants.CENTER);
        painelDistribuicao.add(tituloDistribuicao, BorderLayout.NORTH);
        
        JPanel painelTipos = new JPanel(new GridLayout(1, 3, 20, 0));
        painelTipos.setBackground(StyleConstants.SECONDARY_HOVER);
        painelTipos.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        // Cards para cada tipo
        JPanel cardLivros = criarCardTipo("Livros", "0", "LIVRO FÍSICO");
        lblTotalLivros = (JLabel) ((JPanel) cardLivros.getComponent(1)).getComponent(0);
        painelTipos.add(cardLivros);
        
        JPanel cardEbooks = criarCardTipo("Ebooks", "0", "EBOOK");
        lblTotalEbooks = (JLabel) ((JPanel) cardEbooks.getComponent(1)).getComponent(0);
        painelTipos.add(cardEbooks);
        
        JPanel cardAudiobooks = criarCardTipo("Audiobooks", "0", "AUDIOBOOK");
        lblTotalAudiobooks = (JLabel) ((JPanel) cardAudiobooks.getComponent(1)).getComponent(0);
        painelTipos.add(cardAudiobooks);
        
        painelDistribuicao.add(painelTipos, BorderLayout.CENTER);
        
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 20, 20, 20);
        painelCentral.add(painelDistribuicao, gbc);
        
        add(painelCentral, BorderLayout.CENTER);
        
        // Painel inferior com botões
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelInferior.setBackground(StyleConstants.SECONDARY_COLOR);
        painelInferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        btnAtualizar = new JButton("Atualizar Métricas");
        ButtonStyles.applyDefaultStyle(btnAtualizar);
        painelInferior.add(btnAtualizar);
        
        btnVoltar = new JButton("Voltar ao Menu");
        ButtonStyles.applyDangerStyle(btnVoltar);
        painelInferior.add(btnVoltar);
        
        add(painelInferior, BorderLayout.SOUTH);
    }
    
    private JPanel criarCardMetrica(String titulo, String valor) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(StyleConstants.SECONDARY_HOVER);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(StyleConstants.PRIMARY_COLOR, 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        card.setPreferredSize(new Dimension(300, 100));
        
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(StyleConstants.FONT_BOLD);
        lblTitulo.setForeground(StyleConstants.PRIMARY_COLOR);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(lblTitulo, BorderLayout.NORTH);
        
        JPanel painelValor = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelValor.setBackground(StyleConstants.SECONDARY_HOVER);
        
        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblValor.setForeground(StyleConstants.PRIMARY_COLOR);
        painelValor.add(lblValor);
        
        card.add(painelValor, BorderLayout.CENTER);
        
        return card;
    }
    
    private JPanel criarCardTipo(String titulo, String valor, String tipo) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(StyleConstants.SECONDARY_COLOR);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(StyleConstants.PRIMARY_COLOR, 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel lblTipo = new JLabel(tipo);
        lblTipo.setFont(StyleConstants.FONT_BOLD);
        lblTipo.setForeground(StyleConstants.PRIMARY_COLOR);
        lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(lblTipo, BorderLayout.NORTH);
        
        JPanel painelInfo = new JPanel(new BorderLayout());
        painelInfo.setBackground(StyleConstants.SECONDARY_COLOR);
        painelInfo.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblValor.setForeground(StyleConstants.PRIMARY_COLOR);
        lblValor.setHorizontalAlignment(SwingConstants.CENTER);
        painelInfo.add(lblValor, BorderLayout.CENTER);
        
        card.add(painelInfo, BorderLayout.CENTER);
        
        return card;
    }
    
    private void configurarEventos() {
        btnVoltar.addActionListener(e -> telaPrincipal.voltarParaMenu());
        btnAtualizar.addActionListener(e -> atualizarMetricas());
    }
    
    private void atualizarMetricas() {
        // Calcular total de itens
        int totalItens = livros.size() + ebooks.size() + audiobooks.size();
        lblTotalItens.setText(String.valueOf(totalItens));
        
        // Calcular total de itens lidos
        int totalLidos = 0;
        for (Livro livro : livros) {
            if (livro.isLido()) totalLidos++;
        }
        for (Ebook ebook : ebooks) {
            if (ebook.isLido()) totalLidos++;
        }
        for (Audiobook audiobook : audiobooks) {
            if (audiobook.isLido()) totalLidos++;
        }
        lblTotalLidos.setText(String.valueOf(totalLidos));
        
        // Atualizar distribuição por tipo
        lblTotalLivros.setText(String.valueOf(livros.size()));
        lblTotalEbooks.setText(String.valueOf(ebooks.size()));
        lblTotalAudiobooks.setText(String.valueOf(audiobooks.size()));
        
        // Calcular média de avaliações
        double somaNotas = 0;
        int itensAvaliados = 0;
        
        for (Livro livro : livros) {
            if (livro.isLido() && livro.getNota() != Nota.NAO_AVALIADO) {
                somaNotas += obterValorNota(livro.getNota());
                itensAvaliados++;
            }
        }
        for (Ebook ebook : ebooks) {
            if (ebook.isLido() && ebook.getNota() != Nota.NAO_AVALIADO) {
                somaNotas += obterValorNota(ebook.getNota());
                itensAvaliados++;
            }
        }
        for (Audiobook audiobook : audiobooks) {
            if (audiobook.isLido() && audiobook.getNota() != Nota.NAO_AVALIADO) {
                somaNotas += obterValorNota(audiobook.getNota());
                itensAvaliados++;
            }
        }
        
        if (itensAvaliados > 0) {
            double media = somaNotas / itensAvaliados;
            lblMediaAvaliacoes.setText(String.format("%.1f", media));
        } else {
            lblMediaAvaliacoes.setText("0.0");
        }
    }
    
    private int obterValorNota(Nota nota) {
        switch (nota) {
            case UMA_ESTRELA: return 1;
            case DUAS_ESTRELAS: return 2;
            case TRES_ESTRELAS: return 3;
            case QUATRO_ESTRELAS: return 4;
            case CINCO_ESTRELAS: return 5;
            default: return 0;
        }
    }
    
    public Container getContentPane() {
        return this;
    }
}