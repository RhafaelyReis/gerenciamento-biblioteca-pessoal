// src/main/java/br/edu/ifms/Main.java
package br.edu.ifms;

import br.edu.ifms.view.JanelaPrincipal;

public class Main {
    public static void main(String[] args) {
        JanelaPrincipal janelaPrincipal = new JanelaPrincipal();
    }
}





































// // Importe as classes do pacote correto
// import br.edu.ifms.model.Audiobook;
// import br.edu.ifms.model.Ebook;
// import br.edu.ifms.model.Genero;
// import br.edu.ifms.model.Livro;
// import br.edu.ifms.model.Nota;

// public class Main {
//     public static void main(String[] args) {
//         System.out.println("--- Iniciando Testes Manuais das Classes de Modelo ---");

//         // --- Testando a classe Livro ---
//         System.out.println("\n*** Testando Livro ***");
//         Livro livro1 = new Livro(
//             "O Senhor dos Anéis: A Sociedade do Anel",
//             "J.R.R. Tolkien",
//             1954,
//             Genero.FANTASIA,
//             "Primeiro volume da trilogia épica.",
//             480,
//             "978-0618053267"
//         );
//         System.out.println("Livro 1 (inicial): " + livro1);

//         // Testando setters
//         livro1.setLido(true);
//         livro1.setNota(Nota.CINCO_ESTRELAS); // Usando setNota
//         livro1.setNumPaginas(500);
//         System.out.println("Livro 1 (após setLido e setNota): " + livro1);
//         System.out.println("Gênero do Livro 1: " + livro1.getGenero().getDescricao());
//         System.out.println("Avaliação do Livro 1: " + livro1.getNota().getEstrelas()); // Usando getNota

//         Livro livro2 = new Livro(
//             "Pequeno Príncipe",
//             "Antoine de Saint-Exupéry",
//             1943,
//             Genero.INFANTIL,
//             "Uma fábula filosófica e poética.",
//             96,
//             "978-8578270634"
//         );
//         System.out.println("Livro 2: " + livro2);
//         livro2.setLido(true);
//         livro2.setNota(Nota.QUATRO_ESTRELAS);

//         System.out.println("\nComparando Livro 1 e Livro 2 (IDs diferentes):");
//         System.out.println("Livro 1 equals Livro 2? " + livro1.equals(livro2));
//         System.out.println("Hash code Livro 1: " + livro1.hashCode());
//         System.out.println("Hash code Livro 2: " + livro2.hashCode());


//         // --- Testando a classe Ebook ---
//         System.out.println("\n*** Testando Ebook ***");
//         Ebook ebook1 = new Ebook(
//             "Clean Code",
//             "Robert C. Martin",
//             2008,
//             Genero.TECNOLOGIA,
//             "Um guia para escrever código limpo.",
//             "Computador" // Dispositivo
//         );
//         System.out.println("Ebook 1 (inicial): " + ebook1);
//         ebook1.setLido(true);
//         ebook1.setNota(Nota.CINCO_ESTRELAS);
//         System.out.println("Ebook 1 (após setLido e setNota): " + ebook1);
//         System.out.println("Dispositivo do Ebook 1: " + ebook1.getDispositivo());


//         // --- Testando a classe Audiobook ---
//         System.out.println("\n*** Testando Audiobook ***");
//         Audiobook audiobook1 = new Audiobook(
//             "Sapiens: Uma Breve História da Humanidade",
//             "Yuval Noah Harari",
//             2011,
//             Genero.HISTORIA,
//             "Explora a história da humanidade desde o surgimento do Homo sapiens.",
//             1500, // Duração em minutos (25 horas)
//             "Pedro Drummond"
//         );
//         System.out.println("Audiobook 1 (inicial): " + audiobook1);
//         audiobook1.setLido(true);
//         audiobook1.setNota(Nota.QUATRO_ESTRELAS);
//         System.out.println("Audiobook 1 (após setLido e setNota): " + audiobook1);
//         System.out.println("Narrador do Audiobook 1: " + audiobook1.getNarrador());
//         System.out.println("Duração do Audiobook 1: " + audiobook1.getDuracaoMinutos() + " minutos");


//         // --- Testando regras de Nota e Lido ---
//         System.out.println("\n*** Testando Regras de Nota e Lido ***");
//         Livro livroTeste = new Livro(
//             "Teste Avaliação", "Autor Teste", 2020, Genero.OUTROS, "Livro para testar regras de avaliação.", 100, "123"
//         );
//         System.out.println("Livro Teste (inicial): " + livroTeste);

//         livroTeste.setNota(Nota.DUAS_ESTRELAS); // O modelo aceita a avaliação, mas a camada de serviço que validará
//         System.out.println("Livro Teste (após tentar avaliar sem ler): " + livroTeste);

//         livroTeste.setLido(true); // Marca como lido
//         livroTeste.setNota(Nota.TRES_ESTRELAS); // Agora sim, avalia
//         System.out.println("Livro Teste (após ler e avaliar): " + livroTeste);

//         livroTeste.setLido(false); // Marca como não lido novamente
//         System.out.println("Livro Teste (após desmarcar lido - avaliação deve resetar): " + livroTeste);

//         System.out.println("\n--- Fim dos Testes Manuais ---");
//     }
// }