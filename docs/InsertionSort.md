# Insertion Sort (ordenamiento por inserción)

## Historia
Es el método que usa una persona al ordenar cartas en la mano: toma una carta y la inserta en su lugar entre las que ya están ordenadas. Es uno de los métodos de ordenamiento más simples y antiguos, y Donald Knuth lo analiza en *The Art of Computer Programming*. Shell Sort (1959) nació como una mejora suya.

## Cómo funciona
La parte izquierda del arreglo siempre está ordenada. En cada vuelta se toma el siguiente elemento (la clave) y se corren hacia la derecha los mayores que ella, hasta dejarla en su posición.

## Seudocódigo
```
InsertionSort(A, n)
  para i desde 1 hasta n-1 hacer
    clave <- A[i]
    j <- i - 1
    mientras j >= 0 y A[j] > clave hacer
      A[j+1] <- A[j]
      j <- j - 1
    A[j+1] <- clave
```

## Características
- Estable: no cambia el orden de elementos iguales.
- Se ordena en el mismo arreglo: memoria extra O(1).
- Se adapta: es muy rápido si los datos ya vienen casi ordenados.
- Eficiente en listas pequeñas; por eso lo usan algoritmos más grandes como parte final.

## Rendimiento
| Caso | Comparaciones | Orden |
|------|---------------|-------|
| Mejor (ya ordenado) | n − 1 | O(n) |
| Promedio (aleatorio) | cerca de n²/4 | O(n²) |
| Peor (inverso) | n(n − 1)/2 | O(n²) |

Los desplazamientos de elementos son, como máximo, n(n − 1)/2.

## Verificación
Con n = 2000 el programa midió 1 999 comparaciones con datos ordenados (n − 1), 999 913 con datos aleatorios (≈ n²/4) y 1 999 000 con datos inversos (n(n − 1)/2), tal como predice la teoría.
