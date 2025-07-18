package br.edu.ifms.view.frames;

import br.edu.ifms.model.*;
import br.edu.ifms.view.styles.ButtonStyles;
import br.edu.ifms.view.styles.StyleConstants;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Painel que exibe métricas e estatísticas da biblioteca.
 */
public class TelaMetricas extends JPanel {
    
    private TelaInicial telaPrincipal;
    private List<Livro> livros;
    private List<Ebook> ebooks;
    private List<Audiobook> audiobooks;
    
    // --- Componentes UI ---
    private JLabel lblTotalItens, lblTotalLidos, lblTotalLivros, lblTotalEbooks, lblTotalAudiobooks, lblMediaAvaliacoes;
    private JButton btnVoltar;
    
    /**
     * Construtor da tela de métricas.
     * @param telaPrincipal Referência à tela principal.
     * @param livros Lista de livros.
     * @param ebooks Lista de ebooks.
     * @param audiobooks Lista de audiobooks.
     */
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
    
    /** Cria e configura os componentes da interface. */
    private void initComponents() {
        setBackground(StyleConstants.SECONDARY_COLOR);
        setLayout(new BorderLayout());
        
        JLabel titulo = new JLabel("Métricas da Biblioteca");
        titulo.setFont(StyleConstants.TITLE);
        titulo.setForeground(StyleConstants.PRIMARY_COLOR);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        add(titulo, BorderLayout.NORTH);
        
        JPanel painelCentral = new JPanel(new GridBagLayout());
        painelCentral.setBackground(StyleConstants.SECONDARY_COLOR);
        painelCentral.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 20, 15, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JPanel cardTotalItens = criarCardMetrica("Total de livros na sua biblioteca", "0");
        lblTotalItens = (JLabel) ((JPanel) cardTotalItens.getComponent(1)).getComponent(0);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        painelCentral.add(cardTotalItens, gbc);
        
        JPanel cardTotalLidos = criarCardMetrica("Livros lidos", "0");
        lblTotalLidos = (JLabel) ((JPanel) cardTotalLidos.getComponent(1)).getComponent(0);
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        painelCentral.add(cardTotalLidos, gbc);
        
        JPanel cardMediaAvaliacoes = criarCardMetrica("Média de Avaliações", "0.0");
        lblMediaAvaliacoes = (JLabel) ((JPanel) cardMediaAvaliacoes.getComponent(1)).getComponent(0);
        gbc.gridx = 1; gbc.gridy = 1;
        painelCentral.add(cardMediaAvaliacoes, gbc);
        
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
        
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelInferior.setBackground(StyleConstants.SECONDARY_COLOR);
        painelInferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        btnVoltar = new JButton("Voltar ao Menu");
        ButtonStyles.applyDangerStyle(btnVoltar);
        painelInferior.add(btnVoltar);
        
        add(painelInferior, BorderLayout.SOUTH);
    }
    
    /**
     * Cria um card estilizado para exibir uma métrica.
     * @param titulo Título do card.
     * @param valor Valor inicial.
     * @return O painel do card.
     */
    private JPanel criarCardMetrica(String titulo, String valor) {
        //... Implementação do método (já concisa)
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
    
    /**
     * Cria um card menor para a distribuição de itens por tipo.
     * @param titulo Título do card.
     * @param valor Valor inicial.
     * @param tipo Descrição do tipo.
     * @return O painel do card.
     */
    private JPanel criarCardTipo(String titulo, String valor, String tipo) {
        //... Implementação do método (já concisa)
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
    
    /** Configura a ação do botão de voltar. */
    private void configurarEventos() {
        btnVoltar.addActionListener(e -> telaPrincipal.voltarParaMenu());
    }
    
    /** Calcula e exibe todas as métricas na tela. */
    private void atualizarMetricas() {
        int totalItens = livros.size() + ebooks.size() + audiobooks.size();
        lblTotalItens.setText(String.valueOf(totalItens));
        
        long totalLidos = livros.stream().filter(Item::isLido).count()
                        + ebooks.stream().filter(Item::isLido).count()
                        + audiobooks.stream().filter(Item::isLido).count();
        lblTotalLidos.setText(String.valueOf(totalLidos));
        
        lblTotalLivros.setText(String.valueOf(livros.size()));
        lblTotalEbooks.setText(String.valueOf(ebooks.size()));
        lblTotalAudiobooks.setText(String.valueOf(audiobooks.size()));
        
        double somaNotas = 0;
        int itensAvaliados = 0;
        
        List<Item> todosItens = new ArrayList<>();
        todosItens.addAll(livros);
        todosItens.addAll(ebooks);
        todosItens.addAll(audiobooks);
        
        for (Item item : todosItens) {
            if (item.isLido() && item.getNota() != Nota.NAO_AVALIADO) {
                somaNotas += obterValorNota(item.getNota());
                itensAvaliados++;
            }
        }
        
        if (itensAvaliados > 0) {
            double media = somaNotas / itensAvaliados;
            lblMediaAvaliacoes.setText(String.format("%.1f", media));
        } else {
            lblMediaAvaliacoes.setText("N/A");
        }
    }
    
    /**
     * Converte um enum Nota para seu valor numérico.
     * @param nota O enum Nota.
     * @return O valor inteiro da nota.
     */
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
    
    /** @return O container principal deste painel. */
    public Container getContentPane() {
        return this;
    }
}