/*
 * Analisis de Algoritmos - Universidad Central
 * Autor: Cesar Camilo Becerra Herrera
 * Profesor: Giovanny Alexander Riveros
 * Tema: Insertion Sort - comparativo de algoritmos de ordenamiento
 */
package ordenamiento;

/**
 * Insertion Sort (ordenamiento por insercion).
 *
 * HISTORIA: es el metodo que usa la gente al ordenar cartas en la mano: toma
 * una carta y la inserta en su lugar entre las que ya estan ordenadas. Es de
 * los algoritmos mas antiguos y aparece en los primeros textos de
 * programacion; Knuth lo analiza en "The Art of Computer Programming". Shell
 * Sort (1959) nace como una mejora suya.
 *
 * IDEA: la parte izquierda siempre esta ordenada; cada elemento nuevo se
 * desplaza hacia la izquierda hasta encontrar su posicion.
 *
 * RENDIMIENTO:
 *   - Mejor caso (ya ordenado): n-1 comparaciones -> O(n).
 *   - Promedio: cerca de n^2/4 comparaciones     -> O(n^2).
 *   - Peor caso (inverso): n(n-1)/2              -> O(n^2).
 *   - Memoria extra O(1). Es estable.
 */
public class InsertionSort implements Ordenamiento {

    private long comparaciones;
    private long desplazamientos;

    @Override
    public String nombre() {
        return "Insertion Sort";
    }

    @Override
    public String complejidad() {
        return "O(n^2)";
    }

    @Override
    public void ordenar(int[] a) {
        comparaciones = 0;
        desplazamientos = 0;
        for (int i = 1; i < a.length; i++) {
            int clave = a[i];                  // elemento que voy a insertar
            int j = i - 1;
            while (j >= 0) {
                comparaciones++;
                if (a[j] > clave) {
                    a[j + 1] = a[j];           // corro el mayor una posicion a la derecha
                    desplazamientos++;
                    j--;
                } else {
                    break;
                }
            }
            a[j + 1] = clave;                  // lo dejo en su lugar
        }
    }

    @Override
    public long getComparaciones() {
        return comparaciones;
    }

    /** Aqui los "intercambios" son los desplazamientos de elementos. */
    @Override
    public long getIntercambios() {
        return desplazamientos;
    }

    @Override
    public double teoricoComparaciones(int n) {
        return n * (n - 1) / 2.0;
    }

    @Override
    public double teoricoIntercambios(int n) {
        return n * (n - 1) / 2.0;
    }
}
