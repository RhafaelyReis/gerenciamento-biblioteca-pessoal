package br.edu.ifms.view.styles;
import java.awt.*;

public class StyleConstants {
    // === CORES PERSONALIZADAS ===
    public static final Color PRIMARY_COLOR = new Color(33, 64, 44);        // Verde escuro elegante
    public static final Color SECONDARY_COLOR = new Color(242, 206, 216);   // Rosa claro suave
    public static final Color SUCCESS_COLOR = new Color(39, 174, 96);       // Verde
    public static final Color WARNING_COLOR = new Color(241, 196, 15);      // Amarelo
    public static final Color DANGER_COLOR = new Color(163, 22, 8);        // Vermelho
    
    // Cores para hover (mais escuras/claras)
    public static final Color PRIMARY_HOVER = new Color(25, 48, 33);        // Verde mais escuro
    public static final Color SECONDARY_HOVER = new Color(230, 185, 200);   // Rosa mais escuro
    public static final Color SUCCESS_HOVER = new Color(34, 153, 84);
    public static final Color WARNING_HOVER = new Color(212, 172, 13);
    public static final Color DANGER_HOVER = new Color(192, 57, 43);
    
    // === FONTES ===
    public static final Font FONT = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FONT_BOLD = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font SMALL_FONT = new Font("Segoe UI", Font.PLAIN, 12);
    public static final Font LARGE_FONT = new Font("Segoe UI", Font.BOLD, 16);
    public static final Font TITLE = new Font("Segoe UI", Font.BOLD, 24);
    
    // === DIMENSÕES ===
    public static final Dimension BUTTON_SIZE = new Dimension(120, 35);
    public static final Dimension SMALL_BUTTON_SIZE = new Dimension(80, 30);
    public static final Dimension LARGE_BUTTON_SIZE = new Dimension(150, 45);
    public static final Dimension WIDE_BUTTON_SIZE = new Dimension(200, 35);
    
    // === CORES DE TEXTO ===
    public static final Color BLACK_TEXT = Color.BLACK;
    public static final Color GRAY_TEXT = new Color(80, 85, 89);
}
