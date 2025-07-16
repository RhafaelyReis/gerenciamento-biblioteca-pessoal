package br.edu.ifms.controller;

import br.edu.ifms.model.Genero;
import br.edu.ifms.model.Livro;
import br.edu.ifms.view.panels.PainelLivro;

import java.util.List;

public class LivroController {

    private PainelLivro view;
    private List<Livro> livros;

    public LivroController(PainelLivro view, List<Livro> livros) {
        this.view = view;
        this.livros = livros;
    }

    public void initController() {
        view.getBtnNovo().addActionListener(e -> view.limparCampos());
        view.getBtnCancelar().addActionListener(e -> view.limparCampos());
        view.getBtnRemover().addActionListener(e -> removerLivro());
        view.getBtnAtualizar().addActionListener(e -> salvarLivro());
        view.getTabelaLivros().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                preencherFormularioComLivroSelecionado();
            }
        });
    }

    private void removerLivro() {
        int linhaSelecionada = view.getTabelaLivros().getSelectedRow();
        if (linhaSelecionada >= 0) {
            int id = (Integer) view.getModeloLivros().getValueAt(linhaSelecionada, 0);
            livros.removeIf(livro -> livro.getId() == id);
            view.getAtualizarTabelaCallback().accept("livro");
            view.limparCampos();
        } else {
            view.displayWarning("Selecione um livro para remover.");
        }
    }

    private void salvarLivro() {
        int linhaSelecionada = view.getTabelaLivros().getSelectedRow();
        try {
            String titulo = view.getTxtTitulo().getText();
            String autor = view.getTxtAutor().getText();
            
            if (titulo.trim().isEmpty() || autor.trim().isEmpty()) {
                view.displayWarning("Título e Autor são obrigatórios.");
                return;
            }
            
            int ano = Integer.parseInt(view.getTxtAno().getText());
            Genero genero = (Genero) view.getComboGenero().getSelectedItem();
            String descricao = view.getTxtDescricao().getText();
            int paginas = Integer.parseInt(view.getTxtPaginas().getText());
            String isbn = view.getTxtIsbn().getText();

            if (linhaSelecionada >= 0) {
                // Atualiza livro existente
                int id = (Integer) view.getModeloLivros().getValueAt(linhaSelecionada, 0);
                for (Livro livro : livros) {
                    if (livro.getId() == id) {
                        livro.setTitulo(titulo);
                        livro.setAutor(autor);
                        livro.setAnoPublicacao(ano);
                        livro.setGenero(genero);
                        livro.setDescricao(descricao);
                        livro.setNumPaginas(paginas);
                        livro.setIsbn(isbn);
                        break;
                    }
                }
            } else {
                // Adiciona novo livro
                Livro novo = new Livro(titulo, autor, ano, genero, descricao, paginas, isbn);
                livros.add(novo);
            }
            view.getAtualizarTabelaCallback().accept("livro");
            view.limparCampos();
        } catch (NumberFormatException ex) {
            view.displayError("Erro de formato numérico. Verifique Ano e Páginas.");
        } catch (Exception ex) {
            view.displayError("Erro ao salvar livro: " + ex.getMessage());
        }
    }

    private void preencherFormularioComLivroSelecionado() {
        int selectedRow = view.getTabelaLivros().getSelectedRow();
        if (selectedRow != -1) {
            int idSelecionado = (Integer) view.getModeloLivros().getValueAt(selectedRow, 0);
            Livro livroSelecionado = livros.stream()
                                          .filter(l -> l.getId() == idSelecionado)
                                          .findFirst()
                                          .orElse(null);

            if (livroSelecionado != null) {
                view.getTxtTitulo().setText(livroSelecionado.getTitulo());
                view.getTxtAutor().setText(livroSelecionado.getAutor());
                view.getTxtAno().setText(String.valueOf(livroSelecionado.getAnoPublicacao()));
                view.getComboGenero().setSelectedItem(livroSelecionado.getGenero());
                view.getTxtDescricao().setText(livroSelecionado.getDescricao());
                view.getTxtPaginas().setText(String.valueOf(livroSelecionado.getNumPaginas()));
                view.getTxtIsbn().setText(livroSelecionado.getIsbn());
            }
        }
    }
}