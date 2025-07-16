package br.edu.ifms.view.styles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonStyles {

    /**
     * Aplica o estilo padrão do botão:
     * - Fundo verde, fonte rosa em negrito
     * - Bordas arredondadas (implícita pela falta de BorderPainted e opaque)
     * - Ao clicar: fundo branco, fonte verde em negrito
     */
    public static void applyDefaultStyle(JButton button) {
        setupBaseButton(button);

        // Cores padrão
        button.setBackground(StyleConstants.PRIMARY_COLOR);    // Verde
        button.setForeground(StyleConstants.SECONDARY_COLOR); // Rosa

        // Efeitos de interação
        addHoverAndClickEffect(button, StyleConstants.PRIMARY_COLOR,
                               StyleConstants.PRIMARY_HOVER, StyleConstants.SECONDARY_COLOR);
    }

    /**
     * Aplica o estilo de perigo (danger) ao botão:
     * - Fundo vermelho, fonte branca em negrito
     * - Bordas arredondadas (implícita pela falta de BorderPainted e opaque)
     * - Ao clicar: fundo branco, fonte vermelha em negrito
     */
    public static void applyDangerStyle(JButton button) {
        setupBaseButton(button);

        // Cores de perigo
        button.setBackground(StyleConstants.DANGER_COLOR);    // Vermelho
        button.setForeground(Color.WHITE); // Branco

        // Efeitos de interação
        addHoverAndClickEffect(button, StyleConstants.DANGER_COLOR,
                               StyleConstants.DANGER_HOVER, Color.WHITE);
    }

    /**
     * Configura propriedades básicas do botão
     */
    private static void setupBaseButton(JButton button) {
        button.setFont(StyleConstants.FONT_BOLD);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    /**
     * Adiciona efeitos de hover e clique
     */
    private static void addHoverAndClickEffect(JButton button, Color normalColor,
                                               Color hoverColor, Color textColor) {
        button.addMouseListener(new MouseAdapter() {
            private boolean isPressed = false;

            @Override
            public void mouseEntered(MouseEvent e) {
                if (button.isEnabled() && !isPressed) {
                    // Estado hover: cor mais escura
                    button.setBackground(hoverColor);
                    button.setForeground(textColor);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (button.isEnabled() && !isPressed) {
                    // Estado normal
                    button.setBackground(normalColor);
                    button.setForeground(textColor);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (button.isEnabled()) {
                    isPressed = true;
                    // Estado pressionado: fundo branco, fonte da cor original
                    button.setBackground(Color.WHITE);
                    button.setForeground(normalColor); // A cor da fonte ao pressionar será a normalColor do botão
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (button.isEnabled()) {
                    isPressed = false;
                    // Verifica se o mouse ainda está sobre o botão
                    if (button.contains(e.getPoint())) {
                        // Estado hover
                        button.setBackground(hoverColor);
                        button.setForeground(textColor);
                    } else {
                        // Estado normal
                        button.setBackground(normalColor);
                        button.setForeground(textColor);
                    }
                }
            }
        });
    }
}