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

## Representación temporal f(n)
Lo que cuenta cada algoritmo, con n datos:

| Algoritmo | f(n) en comparaciones | Orden |
|-----------|-----------------------|-------|
| Selection Sort | n(n − 1)/2 en todos los casos | O(n²) |
| Insertion Sort | n − 1 (mejor) · ≈ n²/4 (promedio) · n(n − 1)/2 (peor) | O(n²), mejor caso O(n) |
| Merge Sort | ≤ n⌈log₂ n⌉ − 2^⌈log₂ n⌉ + 1 | O(n log n) |

## Según cómo lleguen los datos (n = 2000, comparaciones medidas)
| Datos | Selection Sort | Insertion Sort | Merge Sort | Menos comparaciones |
|-------|----------------|----------------|------------|---------------------|
| Aleatorios | 1 999 000 | 999 913 | 19 363 | Merge Sort |
| Ya ordenados | 1 999 000 | 1 999 | 11 088 | Insertion Sort (mejor caso O(n)) |
| Invertidos | 1 999 000 | 1 999 000 | 10 878 | Merge Sort |

Ningún algoritmo es el mejor en todos los casos: Insertion Sort gana con datos ya ordenados, y Merge Sort en los demás.

## Con más datos (n hasta 5 000)
La carpeta `resultados_5000/` repite todas las gráficas con hasta 5 000 datos (`java -cp out ordenamiento.Comparador resultados_5000 5000 250`). Allí la curva cuadrática de Selection e Insertion Sort se ve mucho más marcada frente a Merge Sort: con n = 5 000, 12 497 500 comparaciones (Selection), 6 203 865 (Insertion) y 55 080 (Merge).

## Referencias
- Knuth, D. E. (1998). *The Art of Computer Programming, Vol. 3: Sorting and Searching* (2.ª ed.). Addison-Wesley.
- Cormen, T. H., Leiserson, C. E., Rivest, R. L., & Stein, C. (2022). *Introduction to Algorithms* (4.ª ed.). MIT Press.
- Sedgewick, R., & Wayne, K. (2011). *Algorithms* (4.ª ed.). Addison-Wesley.
