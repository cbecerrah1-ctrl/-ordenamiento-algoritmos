/*
 * Analisis de Algoritmos - Universidad Central
 * Autor: Cesar Camilo Becerra Herrera
 * Profesor: Giovanny Alexander Riveros
 * Tema: Selection Sort - comparativo de algoritmos de ordenamiento
 */
package ordenamiento;

import javax.imageio.ImageIO;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/** Dibuja una grafica de lineas (varias series) y la guarda como PNG. */
public class Grafica {

    private static final Color[] COLORES = {
        new Color(25, 106, 190), new Color(225, 108, 27), new Color(40, 150, 80),
        new Color(160, 60, 170), new Color(200, 40, 60), new Color(90, 90, 90)
    };

    public static void guardar(String titulo, String ejeX, String ejeY, double[] xs,
                               LinkedHashMap<String, double[]> series, File archivo) throws IOException {
        int ancho = 900, alto = 560;
        int izq = 95, der = ancho - 30, arriba = 60, abajo = alto - 95;
        BufferedImage img = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, ancho, alto);

        double maxY = 1, maxX = xs[xs.length - 1];
        for (double[] s : series.values()) {
            for (double v : s) maxY = Math.max(maxY, v);
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.BOLD, 18));
        g.drawString(titulo, izq, 30);
        g.setFont(new Font("SansSerif", Font.PLAIN, 12));
        g.drawString(ejeY, 10, 50);

        // ejes y cuadricula
        for (int k = 0; k <= 4; k++) {
            int y = abajo - k * (abajo - arriba) / 4;
            g.setColor(new Color(228, 228, 228));
            g.drawLine(izq, y, der, y);
            g.setColor(Color.DARK_GRAY);
            g.drawString(formato(maxY * k / 4, maxY), 10, y + 4);
        }
        for (int k = 0; k <= 5; k++) {
            int x = izq + k * (der - izq) / 5;
            g.setColor(Color.DARK_GRAY);
            g.drawString(String.format("%.0f", maxX * k / 5), x - 12, abajo + 20);
        }
        g.drawLine(izq, arriba, izq, abajo);
        g.drawLine(izq, abajo, der, abajo);
        g.drawString(ejeX, izq + (der - izq) / 2 - 60, abajo + 42);

        // series
        int c = 0, leyendaX = izq;
        g.setStroke(new BasicStroke(2.2f));
        for (Map.Entry<String, double[]> e : series.entrySet()) {
            Color color = COLORES[c % COLORES.length];
            g.setColor(color);
            double[] v = e.getValue();
            for (int i = 0; i < v.length; i++) {
                int x = izq + (int) Math.round(xs[i] / maxX * (der - izq));
                int y = abajo - (int) Math.round(v[i] / maxY * (abajo - arriba));
                if (i > 0) {
                    int xa = izq + (int) Math.round(xs[i - 1] / maxX * (der - izq));
                    int ya = abajo - (int) Math.round(v[i - 1] / maxY * (abajo - arriba));
                    g.drawLine(xa, ya, x, y);
                }
                g.fillOval(x - 3, y - 3, 6, 6);
            }
            g.fillRect(leyendaX, alto - 28, 12, 12);
            g.setColor(Color.BLACK);
            g.drawString(e.getKey(), leyendaX + 17, alto - 17);
            leyendaX += 30 + e.getKey().length() * 7;
            c++;
        }
        g.dispose();
        ImageIO.write(img, "png", archivo);
    }

    /** Usa la misma unidad (k, M, G) en todas las marcas del eje, segun el maximo. */
    private static String formato(double v, double maximo) {
        double escala = 1;
        String sufijo = "";
        if (maximo >= 1e9) { escala = 1e9; sufijo = " G"; }
        else if (maximo >= 1e6) { escala = 1e6; sufijo = " M"; }
        else if (maximo >= 1e3) { escala = 1e3; sufijo = " k"; }
        double x = v / escala;
        return (x == Math.rint(x) ? String.format("%.0f", x) : String.format("%.1f", x)) + sufijo;
    }
}
