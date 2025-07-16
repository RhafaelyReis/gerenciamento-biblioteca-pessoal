package br.edu.ifms.controller;

import br.edu.ifms.model.Ebook;
import br.edu.ifms.model.Genero;
import br.edu.ifms.view.panels.PainelEbook;

import java.util.List;

public class EbookController {

    private PainelEbook view;
    private List<Ebook> ebooks;

    public EbookController(PainelEbook view, List<Ebook> ebooks) {
        this.view = view;
        this.ebooks = ebooks;
    }

    public void initController() {
        view.getBtnAdicionar().addActionListener(e -> adicionarEbook());
        view.getBtnRemover().addActionListener(e -> removerEbook());
        view.getBtnAtualizar().addActionListener(e -> salvarEbook());
        view.getTabelaEbooks().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                preencherFormularioComEbookSelecionado();
            }
        });
    }

    private void adicionarEbook() {
        try {
            String titulo = view.getTxtTitulo().getText();
            String autor = view.getTxtAutor().getText();
            int ano = Integer.parseInt(view.getTxtAno().getText());
            Genero genero = (Genero) view.getComboGenero().getSelectedItem();
            String descricao = view.getTxtDescricao().getText();
            String dispositivo = view.getTxtDispositivo().getText();

            if (titulo.trim().isEmpty() || autor.trim().isEmpty()) {
                view.displayWarning("Título e Autor são obrigatórios.");
                return;
            }

            Ebook novo = new Ebook(titulo, autor, ano, genero, descricao, dispositivo);
            ebooks.add(novo);

            view.getAtualizarTabelaCallback().accept("ebook");
            view.limparCampos();

        } catch (NumberFormatException ex) {
            view.displayError("Erro de formato numérico. Verifique o Ano.");
        } catch (Exception ex) {
            view.displayError("Erro ao adicionar ebook: " + ex.getMessage());
        }
    }

    private void removerEbook() {
        int linhaSelecionada = view.getTabelaEbooks().getSelectedRow();
        if (linhaSelecionada >= 0) {
            int id = (Integer) view.getModeloEbooks().getValueAt(linhaSelecionada, 0);
            ebooks.removeIf(ebook -> ebook.getId() == id);
            view.getAtualizarTabelaCallback().accept("ebook");
            view.limparCampos();
        } else {
            view.displayWarning("Selecione um ebook para remover.");
        }
    }

    private void salvarEbook() {
        int linhaSelecionada = view.getTabelaEbooks().getSelectedRow();
        if (linhaSelecionada >= 0) {
            try {
                int id = (Integer) view.getModeloEbooks().getValueAt(linhaSelecionada, 0);
                for (Ebook ebook : ebooks) {
                    if (ebook.getId() == id) {
                        ebook.setTitulo(view.getTxtTitulo().getText());
                        ebook.setAutor(view.getTxtAutor().getText());
                        ebook.setAnoPublicacao(Integer.parseInt(view.getTxtAno().getText()));
                        ebook.setGenero((Genero) view.getComboGenero().getSelectedItem());
                        ebook.setDescricao(view.getTxtDescricao().getText());
                        ebook.setDispositivo(view.getTxtDispositivo().getText());
                        break;
                    }
                }
                view.getAtualizarTabelaCallback().accept("ebook");
            } catch (NumberFormatException ex) {
                view.displayError("Erro de formato numérico. Verifique o Ano.");
            } catch (Exception ex) {
                view.displayError("Erro ao atualizar ebook: " + ex.getMessage());
            }
        } else {
            view.displayWarning("Selecione um ebook para atualizar.");
        }
    }

    private void preencherFormularioComEbookSelecionado() {
        int selectedRow = view.getTabelaEbooks().getSelectedRow();
        if (selectedRow != -1) {
            int idSelecionado = (Integer) view.getModeloEbooks().getValueAt(selectedRow, 0);
            Ebook ebookSelecionado = ebooks.stream()
                    .filter(eb -> eb.getId() == idSelecionado)
                    .findFirst()
                    .orElse(null);

            if (ebookSelecionado != null) {
                view.getTxtTitulo().setText(ebookSelecionado.getTitulo());
                view.getTxtAutor().setText(ebookSelecionado.getAutor());
                view.getTxtAno().setText(String.valueOf(ebookSelecionado.getAnoPublicacao()));
                view.getComboGenero().setSelectedItem(ebookSelecionado.getGenero());
                view.getTxtDescricao().setText(ebookSelecionado.getDescricao());
                view.getTxtDispositivo().setText(ebookSelecionado.getDispositivo());
            }
        }
    }
}