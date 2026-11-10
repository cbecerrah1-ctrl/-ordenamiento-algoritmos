package ordenamiento;

import java.util.Arrays;
import java.util.Scanner;

/** Demo interactiva: escribe numeros y mira Selection Sort paso a paso. */
public class DemoSelectionSort {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Escribe numeros enteros separados por comas o espacios: ");
        String entrada = scanner.nextLine().trim();
        if (entrada.isEmpty()) {
            System.out.println("Debes escribir al menos un numero.");
            return;
        }
        String[] partes = entrada.split("[,\\s]+");
        int[] datos = new int[partes.length];
        try {
            for (int i = 0; i < partes.length; i++) {
                datos[i] = Integer.parseInt(partes[i]);
            }
        } catch (NumberFormatException error) {
            System.out.println("Usa solo numeros enteros. Ejemplo: 8, 3, 10, 1");
            return;
        }

        SelectionSort algoritmo = new SelectionSort();
        System.out.println("Ingresados: " + Arrays.toString(datos));
        System.out.println("\nFuncionamiento paso a paso:");
        algoritmo.ordenar(datos, true);
        System.out.println("\nOrdenados: " + Arrays.toString(datos));
        long n = datos.length;
        System.out.println("Comparaciones: " + algoritmo.getComparaciones()
                + " (teorico n(n-1)/2 = " + n * (n - 1) / 2 + ")");
        System.out.println("Intercambios: " + algoritmo.getIntercambios() + " (maximo n-1 = " + (n - 1) + ")");
    }
}
