/*
 * Analisis de Algoritmos - Universidad Central
 * Autor: Cesar Camilo Becerra Herrera
 * Profesor: Giovanny Alexander Riveros
 * Tema: Merge Sort - comparativo de algoritmos de ordenamiento
 */
package ordenamiento;

/**
 * Merge Sort (ordenamiento por mezcla).
 *
 * HISTORIA: lo propuso John von Neumann en 1945 y es uno de los primeros
 * algoritmos de ordenamiento pensados para computadores (EDVAC). Es el
 * ejemplo clasico de la tecnica "divide y venceras".
 *
 * IDEA: se parte el arreglo por la mitad, se ordena cada mitad (recursivo) y
 * luego se mezclan las dos mitades ya ordenadas.
 *
 * RENDIMIENTO:
 *   - Mejor, promedio y peor caso: O(n log n).
 *   - Peor caso en comparaciones: n*ceil(log2 n) - 2^ceil(log2 n) + 1.
 *   - Memoria extra O(n) (arreglo auxiliar). Es estable.
 */
public class MergeSort implements Ordenamiento {

    private long comparaciones;
    private long movimientos;
    private int[] aux;

    @Override
    public String nombre() {
        return "Merge Sort";
    }

    @Override
    public String complejidad() {
        return "O(n log n)";
    }

    @Override
    public void ordenar(int[] a) {
        comparaciones = 0;
        movimientos = 0;
        if (a.length < 2) {
            return;
        }
        aux = new int[a.length];
        dividir(a, 0, a.length - 1);
    }

    private void dividir(int[] a, int inicio, int fin) {
        if (inicio >= fin) {
            return;                            // un solo elemento ya esta ordenado
        }
        int medio = (inicio + fin) >>> 1;
        dividir(a, inicio, medio);
        dividir(a, medio + 1, fin);
        mezclar(a, inicio, medio, fin);
    }

    private void mezclar(int[] a, int inicio, int medio, int fin) {
        for (int k = inicio; k <= fin; k++) {
            aux[k] = a[k];
        }
        int i = inicio, j = medio + 1;
        for (int k = inicio; k <= fin; k++) {
            if (i > medio) {
                a[k] = aux[j++];               // se acabo la mitad izquierda
            } else if (j > fin) {
                a[k] = aux[i++];               // se acabo la mitad derecha
            } else {
                comparaciones++;
                if (aux[j] < aux[i]) {
                    a[k] = aux[j++];
                } else {
                    a[k] = aux[i++];
                }
            }
            movimientos++;
        }
    }

    @Override
    public long getComparaciones() {
        return comparaciones;
    }

    /** Aqui los "intercambios" son las escrituras al arreglo durante la mezcla. */
    @Override
    public long getIntercambios() {
        return movimientos;
    }

    private static int techoLog2(int n) {
        return n <= 1 ? 0 : 32 - Integer.numberOfLeadingZeros(n - 1);
    }

    @Override
    public double teoricoComparaciones(int n) {
        int k = techoLog2(n);
        return n <= 1 ? 0 : (double) n * k - (1L << k) + 1;
    }

    @Override
    public double teoricoIntercambios(int n) {
        return (double) n * techoLog2(n);
    }
}
