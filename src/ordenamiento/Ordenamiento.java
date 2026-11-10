package ordenamiento;

/**
 * Contrato comun para todos los algoritmos del trabajo.
 * Cada companero implementa esta interfaz con su algoritmo y lo agrega
 * a la lista de Comparador.ALGORITMOS: asi sus graficas salen en la comparacion.
 */
public interface Ordenamiento {

    /** Nombre para mostrar en tablas y graficas. Ej: "Selection Sort". */
    String nombre();

    /** Complejidad teorica. Ej: "O(n^2)". */
    String complejidad();

    /** Ordena el arreglo recibido (lo modifica). Debe reiniciar sus contadores. */
    void ordenar(int[] a);

    /** Comparaciones entre elementos hechas en el ultimo ordenar(). */
    long getComparaciones();

    /** Intercambios (o movimientos) hechos en el ultimo ordenar(). */
    long getIntercambios();
}
