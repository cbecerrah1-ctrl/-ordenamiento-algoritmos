package ordenamiento;

import java.util.Arrays;

/**
 * Selection Sort (ordenamiento por seleccion).
 *
 * HISTORIA: no tiene un inventor unico; es un metodo tan natural que aparece
 * en los primeros estudios de ordenamiento en computadores (decadas de 1950 y
 * 1960). Knuth lo describe dentro de los metodos de ordenamiento por seleccion
 * en "The Art of Computer Programming". Heap Sort puede verse como su mejora.
 *
 * IDEA: en cada vuelta se busca el menor de la parte sin ordenar y se coloca
 * en su posicion final.
 *
 * RENDIMIENTO:
 *   - Comparaciones: siempre n(n-1)/2  -> O(n^2) en mejor, promedio y peor caso.
 *   - Intercambios: como maximo n-1    -> O(n).
 *   - Memoria extra: O(1) (se ordena en el mismo arreglo).
 *   - No es estable.
 */
public class SelectionSort implements Ordenamiento {

    private long comparaciones;
    private long intercambios;

    @Override
    public String nombre() {
        return "Selection Sort";
    }

    @Override
    public String complejidad() {
        return "O(n^2)";
    }

    @Override
    public void ordenar(int[] a) {
        ordenar(a, false);
    }

    /** Igual que ordenar(a), pero si mostrarPasos es true imprime cada vuelta. */
    public void ordenar(int[] a, boolean mostrarPasos) {
        comparaciones = 0;
        intercambios = 0;
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;                       // supongo que el menor esta en i
            for (int j = i + 1; j < n; j++) {
                comparaciones++;
                if (a[j] < a[min]) {
                    min = j;                   // encontre uno menor
                }
            }
            if (min != i) {                    // intercambio con una variable temporal
                int temporal = a[i];
                a[i] = a[min];
                a[min] = temporal;
                intercambios++;
            }
            if (mostrarPasos) {
                System.out.println("Paso " + (i + 1) + " (posicion " + i + ", minimo " + a[i] + "): "
                        + Arrays.toString(a));
            }
        }
    }

    @Override
    public long getComparaciones() {
        return comparaciones;
    }

    @Override
    public long getIntercambios() {
        return intercambios;
    }
}
