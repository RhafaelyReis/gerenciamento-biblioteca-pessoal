package br.edu.ifms.controller;

import br.edu.ifms.exception.CampoInvalidoException;
import br.edu.ifms.exception.CampoObrigatorioException;
import br.edu.ifms.exception.FormatoInvalidoException;
import br.edu.ifms.exception.ItemNaoSelecionadoException;
import br.edu.ifms.exception.Validador;
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
        view.getBtnNovo().addActionListener(e -> view.limparCampos());
        view.getBtnCancelar().addActionListener(e -> view.limparCampos());
        view.getBtnRemover().addActionListener(e -> removerAudiobook());
        view.getBtnAtualizar().addActionListener(e -> salvarAudiobook());
        view.getTabelaAudiobooks().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                preencherFormularioComAudiobookSelecionado();
            }
        });
    }

    private void removerAudiobook() {
        try {
            Validador.validarItemSelecionado(view.getTabelaAudiobooks());

            int linhaSelecionada = view.getTabelaAudiobooks().getSelectedRow();
            int id = (Integer) view.getModeloAudiobooks().getValueAt(linhaSelecionada, 0);
            audiobooks.removeIf(audiobook -> audiobook.getId() == id);
            view.getAtualizarTabelaCallback().accept("audiobook");
            view.limparCampos();

        } catch (ItemNaoSelecionadoException e) {
            view.displayWarning(e.getMessage());
        }
    }

    private void salvarAudiobook() {
        try {
            String titulo = view.getTxtTitulo().getText();
            String autor = view.getTxtAutor().getText();
            String anoStr = view.getTxtAno().getText();
            String duracaoStr = view.getTxtDuracaoMinutos().getText();
            String narrador = view.getTxtNarrador().getText();
            
            Validador.validarCamposObrigatorios(titulo, autor, anoStr, duracaoStr, narrador);

            int ano = Validador.validarFormatoInteiro(anoStr);
            int duracao = Validador.validarFormatoInteiro(duracaoStr);
            
            Validador.validarAno(ano);
            Validador.validarDuracaoAudiobook(duracao);
            
            Genero genero = (Genero) view.getComboGenero().getSelectedItem();
            String descricao = view.getTxtDescricao().getText();
            int linhaSelecionada = view.getTabelaAudiobooks().getSelectedRow();
            
            if (linhaSelecionada >= 0) {
                int id = (Integer) view.getModeloAudiobooks().getValueAt(linhaSelecionada, 0);
                for (Audiobook audiobook : audiobooks) {
                    if (audiobook.getId() == id) {
                        audiobook.setTitulo(titulo);
                        audiobook.setAutor(autor);
                        audiobook.setAnoPublicacao(ano);
                        audiobook.setGenero(genero);
                        audiobook.setDescricao(descricao);
                        audiobook.setDuracaoMinutos(duracao);
                        audiobook.setNarrador(narrador);
                        break;
                    }
                }
            } else {
                Audiobook novo = new Audiobook(titulo, autor, ano, genero, descricao, duracao, narrador);
                audiobooks.add(novo);
            }
            view.getAtualizarTabelaCallback().accept("audiobook");
            view.limparCampos();

        } catch (CampoObrigatorioException | FormatoInvalidoException | CampoInvalidoException e) {
            view.displayError(e.getMessage());
        } catch (Exception e) {
            view.displayError("Erro ao salvar audiobook: " + e.getMessage());
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