package br.edu.ifms.controller;

import br.edu.ifms.model.*;
import br.edu.ifms.view.frames.TelaGerenciarItens;
import javax.swing.*;
import java.util.List;

public class GerenciarItensController {

    private TelaGerenciarItens view;
    private List<Livro> livros;
    private List<Ebook> ebooks;
    private List<Audiobook> audiobooks;

    public GerenciarItensController(TelaGerenciarItens view, List<Livro> livros, List<Ebook> ebooks, List<Audiobook> audiobooks) {
        this.view = view;
        this.livros = livros;
        this.ebooks = ebooks;
        this.audiobooks = audiobooks;
    }

    public void initController() {
        view.getBtnVoltar().addActionListener(e -> view.getTelaPrincipal().voltarParaMenu());

        // Ação do botão foi centralizada em um único método mais inteligente
        view.getBtnMarcarLidoLivro().addActionListener(e -> gerenciarStatusLeitura("livro"));
        view.getBtnMarcarLidoEbook().addActionListener(e -> gerenciarStatusLeitura("ebook"));
        view.getBtnMarcarLidoAudiobook().addActionListener(e -> gerenciarStatusLeitura("audiobook"));

        // ===== NOVA LÓGICA =====
        // Adiciona listeners para atualizar o texto do botão em tempo real
        addTableSelectionListeners();

        // Inicializa os controllers dos painéis de formulário
        new LivroController(view.getPainelLivro(), livros).initController();
        new EbookController(view.getPainelEbook(), ebooks).initController();
        new AudiobookController(view.getPainelAudiobook(), audiobooks).initController();
        
        // Garante que o estado inicial dos botões esteja correto
        atualizarBotaoLido("livro");
        atualizarBotaoLido("ebook");
        atualizarBotaoLido("audiobook");
    }

    /**
     * Adiciona listeners às tabelas para atualizar dinamicamente o botão de "Marcar Lido".
     */
    private void addTableSelectionListeners() {
        view.getTabelaLivros().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                atualizarBotaoLido("livro");
            }
        });
        view.getTabelaEbooks().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                atualizarBotaoLido("ebook");
            }
        });
        view.getTabelaAudiobooks().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                atualizarBotaoLido("audiobook");
            }
        });
    }

    /**
     * Atualiza o texto do botão com base no status do item selecionado.
     * @param tipo O tipo de item (livro, ebook, audiobook).
     */
    private void atualizarBotaoLido(String tipo) {
        JTable tabela = getTabelaPorTipo(tipo);
        JButton botao = getBotaoPorTipo(tipo);
        List<? extends Item> lista = getListaPorTipo(tipo);

        if (tabela == null || botao == null) return;

        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada == -1) {
            botao.setText("Marcar como Lido");
            return;
        }

        int id = (Integer) tabela.getValueAt(linhaSelecionada, 0);
        Item itemSelecionado = lista.stream().filter(item -> item.getId() == id).findFirst().orElse(null);

        if (itemSelecionado != null) {
            if (itemSelecionado.isLido()) {
                botao.setText("Alterar Status/Avaliação");
            } else {
                botao.setText("Marcar como Lido");
            }
        }
    }
    
    /**
     * Gerencia o status de leitura de um item. Permite marcar como lido,
     * marcar como não lido e alterar a avaliação de itens já lidos.
     * @param tipo O tipo de item (livro, ebook, audiobook).
     */
    private void gerenciarStatusLeitura(String tipo) {
        JTable tabela = getTabelaPorTipo(tipo);
        List<? extends Item> lista = getListaPorTipo(tipo);

        if (tabela == null || tabela.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(view, "Selecione um item para gerenciar.",
                                        "Nenhum item selecionado", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int linhaSelecionada = tabela.getSelectedRow();
        int id = (Integer) tabela.getValueAt(linhaSelecionada, 0);
        Item itemSelecionado = lista.stream().filter(item -> item.getId() == id).findFirst().orElse(null);

        if (itemSelecionado == null) return;

        // Se o item JÁ FOI LIDO, oferece novas opções
        if (itemSelecionado.isLido()) {
            Object[] opcoes = {"Marcar como Não Lido", "Alterar Avaliação", "Cancelar"};
            int escolha = JOptionPane.showOptionDialog(view,
                "O que você deseja fazer com '" + itemSelecionado.getTitulo() + "'?",
                "Gerenciar Item Lido",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

            switch (escolha) {
                case 0: // Marcar como Não Lido
                    itemSelecionado.setLido(false);
                    itemSelecionado.setNota(Nota.NAO_AVALIADO); // Remove a avaliação
                    JOptionPane.showMessageDialog(view, "O item foi marcado como 'Não Lido' e sua avaliação foi removida.");
                    break;
                case 1: // Alterar Avaliação
                    abrirTelaAvaliacao(itemSelecionado);
                    break;
                case 2: // Cancelar
                default:
                    return; // Nenhuma ação
            }
        } else { // Se o item NÃO FOI LIDO, executa a lógica original
            int confirmacao = JOptionPane.showConfirmDialog(view,
                "Deseja marcar '" + itemSelecionado.getTitulo() + "' como lido?",
                "Confirmar", JOptionPane.YES_NO_OPTION);

            if (confirmacao == JOptionPane.YES_OPTION) {
                itemSelecionado.setLido(true);
                abrirTelaAvaliacao(itemSelecionado);
            }
        }
        
        view.atualizarTabela(tipo); 
        // Força a atualização do botão após a ação
        SwingUtilities.invokeLater(() -> atualizarBotaoLido(tipo));
    }

    private void abrirTelaAvaliacao(Item item) {
        String[] opcoes = {"★", "★★", "★★★", "★★★★", "★★★★★"};
        // Pré-seleciona a avaliação atual, se houver
        String avaliacaoAtual = item.getNota().getSimbolo();
        int indiceInicial = -1;
        for (int i = 0; i < opcoes.length; i++) {
            if (opcoes[i].equals(avaliacaoAtual)) {
                indiceInicial = i;
                break;
            }
        }
        if (indiceInicial == -1) indiceInicial = 2; // Padrão 3 estrelas se não houver

        String avaliacao = (String) JOptionPane.showInputDialog(view,
            "Como você avalia '" + item.getTitulo() + "'?",
            "Avaliar Item",
            JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[indiceInicial]);

        if (avaliacao != null) {
            Nota nota = Nota.fromSimbolo(avaliacao);
            item.setNota(nota);
        }
    }

    // Métodos utilitários para evitar repetição de código
    private JTable getTabelaPorTipo(String tipo) {
        switch (tipo) {
            case "livro": return view.getTabelaLivros();
            case "ebook": return view.getTabelaEbooks();
            case "audiobook": return view.getTabelaAudiobooks();
            default: return null;
        }
    }

    private List<? extends Item> getListaPorTipo(String tipo) {
        switch (tipo) {
            case "livro": return livros;
            case "ebook": return ebooks;
            case "audiobook": return audiobooks;
            default: return null;
        }
    }

    private JButton getBotaoPorTipo(String tipo) {
        switch (tipo) {
            case "livro": return view.getBtnMarcarLidoLivro();
            case "ebook": return view.getBtnMarcarLidoEbook();
            case "audiobook": return view.getBtnMarcarLidoAudiobook();
            default: return null;
        }
    }
}