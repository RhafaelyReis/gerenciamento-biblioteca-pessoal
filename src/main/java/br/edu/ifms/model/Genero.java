// src/main/java/br/edu/ifms/model/Genero.java
package br.edu.ifms.model;


// Enumeração para representar os possíveis genêros de um item na biblioteca, facilitando a padronização e evitando erros de digitação.
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
     * Retorna uma representação legível do gênero, substituindo underscores por espaços.
     * Ex: FICCAO_CIENTIFICA se torna "Ficção Científica".
     *
     * @return O nome do gênero formatado.
     */
    @Override
    public String toString() {
        // Converte para minúsculas, substitui '_' por ' ' e capitaliza a primeira letra de cada palavra
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

    public String getDescricao() {
    return this.toString();
    }

}
