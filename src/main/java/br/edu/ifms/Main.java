// src/main/java/br/edu/ifms/Main.java
package br.edu.ifms;

import javax.swing.SwingUtilities;

import br.edu.ifms.view.frames.TelaInicial;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        new TelaInicial().setVisible(true);
        });
    }
}