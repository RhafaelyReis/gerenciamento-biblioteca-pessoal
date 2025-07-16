package br.edu.ifms.controller;

import br.edu.ifms.model.*;
import br.edu.ifms.view.frames.TelaGerenciarItens;
import br.edu.ifms.view.panels.PainelAudiobook;
import br.edu.ifms.view.panels.PainelEbook;
import br.edu.ifms.view.panels.PainelLivro;

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
        view.getBtnMarcarLidoLivro().addActionListener(e -> marcarComoLido("livro"));
        view.getBtnMarcarLidoEbook().addActionListener(e -> marcarComoLido("ebook"));
        view.getBtnMarcarLidoAudiobook().addActionListener(e -> marcarComoLido("audiobook"));

        PainelLivro painelLivro = view.getPainelLivro();
        LivroController livroController = new LivroController(painelLivro, livros);
        livroController.initController();

        PainelEbook painelEbook = view.getPainelEbook();
        EbookController ebookController = new EbookController(painelEbook, ebooks);
        ebookController.initController();

        PainelAudiobook painelAudiobook = view.getPainelAudiobook();
        AudiobookController audiobookController = new AudiobookController(painelAudiobook, audiobooks);
        audiobookController.initController();
    }
    
    private void marcarComoLido(String tipo) {
        JTable tabela = null;
        List<? extends Item> lista = null;

        switch (tipo) {
            case "livro":
                tabela = view.getTabelaLivros();
                lista = livros;
                break;
            case "ebook":
                tabela = view.getTabelaEbooks();
                lista = ebooks;
                break;
            case "audiobook":
                tabela = view.getTabelaAudiobooks();
                lista = audiobooks;
                break;
        }

        if (tabela == null || tabela.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(view, "Selecione um item para marcar como lido.",
                                        "Nenhum item selecionado", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int linhaSelecionada = tabela.getSelectedRow();
        int id = (Integer) tabela.getValueAt(linhaSelecionada, 0);

        Item itemSelecionado = lista.stream().filter(item -> item.getId() == id).findFirst().orElse(null);

        if (itemSelecionado == null) return;

        if (itemSelecionado.isLido()) {
            JOptionPane.showMessageDialog(view, "Este item já foi marcado como lido.",
                                        "Item já lido", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        int confirmacao = JOptionPane.showConfirmDialog(view,
            "Deseja marcar '" + itemSelecionado.getTitulo() + "' como lido?",
            "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            itemSelecionado.setLido(true);
            abrirTelaAvaliacao(itemSelecionado);
            view.atualizarTabela(tipo); 
        }
    }

    private void abrirTelaAvaliacao(Item item) {
        String[] opcoes = {"★", "★★", "★★★", "★★★★", "★★★★★"};
        String avaliacao = (String) JOptionPane.showInputDialog(view,
            "Como você avalia '" + item.getTitulo() + "'?",
            "Avaliar Item",
            JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[2]);

        if (avaliacao != null) {
            Nota nota = Nota.TRES_ESTRELAS; // Padrão
            switch (avaliacao) {
                case "★": nota = Nota.UMA_ESTRELA; break;
                case "★★": nota = Nota.DUAS_ESTRELAS; break;
                case "★★★": nota = Nota.TRES_ESTRELAS; break;
                case "★★★★": nota = Nota.QUATRO_ESTRELAS; break;
                case "★★★★★": nota = Nota.CINCO_ESTRELAS; break;
            }
            item.setNota(nota);
        }
    }
}