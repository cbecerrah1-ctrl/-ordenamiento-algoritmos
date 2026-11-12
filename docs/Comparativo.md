# Comparativo de los tres algoritmos

Resultados reales con n = 2000 (ver `resultados/resultados.csv`). El tiempo cambia entre equipos; las comparaciones no.

| Algoritmo | Complejidad | Comparaciones (aleatorio) | Mejor caso | Peor caso | Memoria extra | Estable |
|-----------|-------------|---------------------------|------------|-----------|---------------|---------|
| Selection Sort | O(n²) | 1 999 000 | O(n²) | O(n²) | O(1) | No |
| Insertion Sort | O(n²) | 999 913 | O(n) | O(n²) | O(1) | Sí |
| Merge Sort | O(n log n) | 19 363 | O(n log n) | O(n log n) | O(n) | Sí |

## Qué muestran las gráficas
- `comparacion_comparaciones.png`: Selection e Insertion crecen como parábola; Merge Sort casi no se ve porque crece mucho más lento.
- `comparacion_tiempo.png`: el orden de los tiempos coincide con la teoría, con Merge Sort muy por debajo.
- `comparacion_intercambios.png`: Selection Sort hace muy pocos intercambios (máximo n − 1), Insertion Sort muchos desplazamientos (cerca de n²/4 en promedio) y Merge Sort escribe n log n datos.

## Cuándo usar cada uno
- **Selection Sort:** listas pequeñas, o cuando escribir en memoria es costoso.
- **Insertion Sort:** listas pequeñas o casi ordenadas.
- **Merge Sort:** muchos datos, cuando importa el rendimiento predecible y hay memoria extra.
