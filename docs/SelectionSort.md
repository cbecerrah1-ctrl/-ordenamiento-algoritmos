# Selection Sort (ordenamiento por selección)

## Historia
No tiene un inventor único: es una técnica tan natural que apareció de forma independiente en los primeros estudios de ordenamiento en computadores (décadas de 1950 y 1960). Donald Knuth la describe entre los métodos de ordenamiento por selección en *The Art of Computer Programming*. Resultó atractiva porque hace muy pocos intercambios (máximo n − 1), lo que importaba cuando escribir en memoria era costoso. Heap Sort puede verse como una mejora suya: también selecciona el mayor o el menor repetidamente, pero con un montículo (heap) que evita recorrer todo el arreglo.

## Cómo funciona
El arreglo se divide en una parte ordenada (izquierda) y otra sin ordenar. En cada vuelta se busca el menor de la parte sin ordenar y se intercambia con la primera posición de esa parte.

Ejemplo con `8, 3, 10, 1, 5, 12, 2, 7`:

| Paso | Arreglo |
|------|---------|
| 1 | 1, 3, 10, 8, 5, 12, 2, 7 |
| 2 | 1, 2, 10, 8, 5, 12, 3, 7 |
| 3 | 1, 2, 3, 8, 5, 12, 10, 7 |
| 4 | 1, 2, 3, 5, 8, 12, 10, 7 |
| 5 | 1, 2, 3, 5, 7, 12, 10, 8 |
| 6 | 1, 2, 3, 5, 7, 8, 10, 12 |
| 7 | 1, 2, 3, 5, 7, 8, 10, 12 |

## Seudocódigo
```
SelectionSort(A, n)
  para i desde 0 hasta n-2 hacer
    min <- i
    para j desde i+1 hasta n-1 hacer
      si A[j] < A[min] entonces
        min <- j
    si min != i entonces
      intercambiar A[i] con A[min]
```

## Características
- Se ordena en el mismo arreglo: memoria extra O(1).
- No es estable (el intercambio puede saltar elementos iguales).
- No se adapta: tarda lo mismo si los datos ya vienen ordenados.
- Hace como máximo n − 1 intercambios.
- Útil en listas pequeñas, o cuando escribir es costoso.

## Rendimiento: O(n²)
El ciclo externo corre n − 1 veces y el interno revisa los elementos que quedan:

(n − 1) + (n − 2) + … + 1 = **n(n − 1)/2** comparaciones

Esa cifra no depende del orden de los datos, por eso es O(n²) en el mejor, el promedio y el peor caso.

| Caso | Comparaciones | Intercambios |
|------|---------------|--------------|
| Mejor (ya ordenado) | n(n − 1)/2 | 0 |
| Promedio (aleatorio) | n(n − 1)/2 | cercano a n − 1 |
| Inverso | n(n − 1)/2 | cerca de n/2 |
| Máximo posible | n(n − 1)/2 | n − 1 |

## Verificación con las gráficas
`Comparador` ejecuta el algoritmo con 100, 200… 2000 datos y genera en `resultados/`:

- `selection_sort_comparaciones.png`: las comparaciones reales (3 casos) caen exactamente sobre la curva n(n − 1)/2.
- `selection_sort_intercambios.png`: los intercambios son lineales y nunca pasan de n − 1.
- `selection_sort_tiempo.png`: el tiempo crece como una parábola; los tres casos quedan cercanos porque hacen las mismas comparaciones. Las diferencias pequeñas vienen del computador y de la JVM, no del algoritmo.
