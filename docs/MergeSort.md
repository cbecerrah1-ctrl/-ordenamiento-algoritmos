# Merge Sort (ordenamiento por mezcla)

## Historia
Lo propuso John von Neumann en 1945 y es uno de los primeros algoritmos de ordenamiento pensados para computadores. Es el ejemplo clásico de la técnica «divide y vencerás»: se parte el problema en partes más pequeñas, se resuelven y se combinan.

## Cómo funciona
1. Se divide el arreglo en dos mitades.
2. Se ordena cada mitad con el mismo método (recursión), hasta llegar a mitades de un solo elemento, que ya están ordenadas.
3. Se mezclan las dos mitades ordenadas tomando siempre el menor de los dos primeros elementos.

## Seudocódigo
```
MergeSort(A, inicio, fin)
  si inicio < fin entonces
    medio <- (inicio + fin) / 2
    MergeSort(A, inicio, medio)
    MergeSort(A, medio+1, fin)
    Mezclar(A, inicio, medio, fin)

Mezclar(A, inicio, medio, fin)
  copiar A[inicio..fin] en el arreglo auxiliar AUX
  i <- inicio ; j <- medio+1
  para k desde inicio hasta fin hacer
    si i > medio entonces        A[k] <- AUX[j]; j <- j+1
    sino si j > fin entonces     A[k] <- AUX[i]; i <- i+1
    sino si AUX[j] < AUX[i] entonces  A[k] <- AUX[j]; j <- j+1
    sino                         A[k] <- AUX[i]; i <- i+1
```

## Características
- Estable.
- Necesita memoria extra O(n) (el arreglo auxiliar).
- No se adapta mucho: hace casi el mismo trabajo con cualquier orden inicial.
- Rendimiento predecible, muy bueno con muchos datos.

## Rendimiento: O(n log n)
El arreglo se divide en log₂ n niveles y en cada nivel se mezclan n elementos, así que el trabajo total es n · log₂ n. En el peor caso las comparaciones son n⌈log₂ n⌉ − 2^⌈log₂ n⌉ + 1.

## Verificación
Con n = 2000 el programa midió 19 363 comparaciones con datos aleatorios, por debajo del peor caso teórico de 19 953. Selection e Insertion Sort hacen del orden de un millón o más.
