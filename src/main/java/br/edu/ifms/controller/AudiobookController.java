package br.edu.ifms.controller;

import br.edu.ifms.model.Audiobook;
import br.edu.ifms.model.Genero;
import br.edu.ifms.view.panels.PainelAudiobook;

import java.util.List;

public class AudiobookController {

    private PainelAudiobook view;
    private List<Audiobook> audiobooks;

    public AudiobookController(PainelAudiobook view, List<Audiobook> audiobooks) {
        this.view = view;
        this.audiobooks = audiobooks;
    }

    public void initController() {
        view.getBtnAdicionar().addActionListener(e -> adicionarAudiobook());
        view.getBtnRemover().addActionListener(e -> removerAudiobook());
        view.getBtnAtualizar().addActionListener(e -> salvarAudiobook());
        view.getTabelaAudiobooks().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                preencherFormularioComAudiobookSelecionado();
            }
        });
    }

    private void adicionarAudiobook() {
        try {
            String titulo = view.getTxtTitulo().getText();
            String autor = view.getTxtAutor().getText();
            int ano = Integer.parseInt(view.getTxtAno().getText());
            Genero genero = (Genero) view.getComboGenero().getSelectedItem();
            String descricao = view.getTxtDescricao().getText();
            int duracao = Integer.parseInt(view.getTxtDuracaoMinutos().getText());
            String narrador = view.getTxtNarrador().getText();
            
            if (titulo.trim().isEmpty() || autor.trim().isEmpty()) {
                view.displayWarning("Título e Autor são obrigatórios.");
                return;
            }

            Audiobook novo = new Audiobook(titulo, autor, ano, genero, descricao, duracao, narrador);
            audiobooks.add(novo);

            view.getAtualizarTabelaCallback().accept("audiobook");
            view.limparCampos();

        } catch (NumberFormatException ex) {
            view.displayError("Erro de formato numérico. Verifique Ano e Duração.");
        } catch (Exception ex) {
            view.displayError("Erro ao adicionar audiobook: " + ex.getMessage());
        }
    }

    private void removerAudiobook() {
        int linhaSelecionada = view.getTabelaAudiobooks().getSelectedRow();
        if (linhaSelecionada >= 0) {
            int id = (Integer) view.getModeloAudiobooks().getValueAt(linhaSelecionada, 0);
            audiobooks.removeIf(audiobook -> audiobook.getId() == id);
            view.getAtualizarTabelaCallback().accept("audiobook");
            view.limparCampos();
        } else {
            view.displayWarning("Selecione um audiobook para remover.");
        }
    }

    private void salvarAudiobook() {
        int linhaSelecionada = view.getTabelaAudiobooks().getSelectedRow();
        if (linhaSelecionada >= 0) {
            try {
                int id = (Integer) view.getModeloAudiobooks().getValueAt(linhaSelecionada, 0);
                for (Audiobook audiobook : audiobooks) {
                    if (audiobook.getId() == id) {
                        audiobook.setTitulo(view.getTxtTitulo().getText());
                        audiobook.setAutor(view.getTxtAutor().getText());
                        audiobook.setAnoPublicacao(Integer.parseInt(view.getTxtAno().getText()));
                        audiobook.setGenero((Genero) view.getComboGenero().getSelectedItem());
                        audiobook.setDescricao(view.getTxtDescricao().getText());
                        audiobook.setDuracaoMinutos(Integer.parseInt(view.getTxtDuracaoMinutos().getText()));
                        audiobook.setNarrador(view.getTxtNarrador().getText());
                        break;
                    }
                }
                view.getAtualizarTabelaCallback().accept("audiobook");
            } catch (NumberFormatException ex) {
                view.displayError("Erro de formato numérico. Verifique Ano e Duração.");
            } catch (Exception ex) {
                view.displayError("Erro ao atualizar audiobook: " + ex.getMessage());
            }
        } else {
            view.displayWarning("Selecione um audiobook para atualizar.");
        }
    }

    private void preencherFormularioComAudiobookSelecionado() {
        int selectedRow = view.getTabelaAudiobooks().getSelectedRow();
        if (selectedRow != -1) {
            int idSelecionado = (Integer) view.getModeloAudiobooks().getValueAt(selectedRow, 0);
            Audiobook audiobookSelecionado = audiobooks.stream()
                    .filter(ab -> ab.getId() == idSelecionado)
                    .findFirst()
                    .orElse(null);

            if (audiobookSelecionado != null) {
                view.getTxtTitulo().setText(audiobookSelecionado.getTitulo());
                view.getTxtAutor().setText(audiobookSelecionado.getAutor());
                view.getTxtAno().setText(String.valueOf(audiobookSelecionado.getAnoPublicacao()));
                view.getComboGenero().setSelectedItem(audiobookSelecionado.getGenero());
                view.getTxtDescricao().setText(audiobookSelecionado.getDescricao());
                view.getTxtDuracaoMinutos().setText(String.valueOf(audiobookSelecionado.getDuracaoMinutos()));
                view.getTxtNarrador().setText(audiobookSelecionado.getNarrador());
            }
        }
    }
}