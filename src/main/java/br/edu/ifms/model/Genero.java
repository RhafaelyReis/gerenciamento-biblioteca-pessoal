// src/main/java/br/edu/ifms/model/Genero.java
package br.edu.ifms.model;

/**
 * Enumeração para os gêneros de um item da biblioteca.
 * Padroniza a classificação e evita erros de digitação.
 */
public enum Genero {
    FICCAO,
    NAO_FICCAO,
    ROMANCE,
    SUSPENSE,
    FANTASIA,
    BIOGRAFIAS,
    AUTOAJUDA,
    TECNICO,
    FICCAO_CIENTIFICA,
    TERROR,
    DRAMA,
    COMEDIA,
    AVENTURA,
    HISTORIA,
    BIOGRAFIA,
    POESIA,
    INFANTIL,
    EDUCACIONAL,
    TECNOLOGIA,
    OUTROS;

    /**
     * Retorna o nome do gênero formatado de forma legível (ex: "Ficção Científica").
     * @return O nome formatado.
     */
    @Override
    public String toString() {
        String name = name().toLowerCase().replace('_', ' ');
        StringBuilder formattedName = new StringBuilder();
        boolean capitalizeNext = true;
        for (char c : name.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                capitalizeNext = true;
                formattedName.append(c);
            } else if (capitalizeNext) {
                formattedName.append(Character.toUpperCase(c));
                capitalizeNext = false;
            } else {
                formattedName.append(c);
            }
        }
        return formattedName.toString();
    }

    /** @return A descrição do gênero (sinônimo de toString()). */
    public String getDescricao() {
        return this.toString();
    }
}