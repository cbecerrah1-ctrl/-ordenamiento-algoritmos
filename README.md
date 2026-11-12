# Análisis de Algoritmos: comparativo de ordenamiento

Producto didáctico y comparativo de ordenamiento para Análisis de Algoritmos (Universidad Central). Tres métodos; cada uno tiene historia, características con seudocódigo, rendimiento O(), verificación por separado y gráficas, y todos se comparan entre sí.

| Algoritmo | Complejidad | Responsable | Ficha |
|-----------|-------------|-------------|-------|
| Selection Sort | O(n²) | César Camilo Becerra Herrera | [docs/SelectionSort.md](docs/SelectionSort.md) |
| Insertion Sort | O(n²) | César Camilo Becerra Herrera | [docs/InsertionSort.md](docs/InsertionSort.md) |
| Merge Sort | O(n log n) | César Camilo Becerra Herrera | [docs/MergeSort.md](docs/MergeSort.md) |

Comparativo: [docs/Comparativo.md](docs/Comparativo.md)

## Estructura
```
src/ordenamiento/
  Ordenamiento.java       interfaz que implementa cada algoritmo
  SelectionSort.java      Selection Sort
  InsertionSort.java      Insertion Sort
  MergeSort.java          Merge Sort
  DemoSelectionSort.java  demo paso a paso (escribes los números)
  Comparador.java         verifica, mide y genera las gráficas
  Grafica.java            dibuja los PNG
docs/                     una ficha por algoritmo
resultados/               gráficas PNG y resultados.csv
```

## Cómo ejecutar
**NetBeans:** crear un proyecto Java, copiar la carpeta `src/ordenamiento` dentro de *Source Packages* y ejecutar `Comparador.java` (Run File) o `DemoSelectionSort.java`.

**Terminal:**
```bash
javac -d out src/ordenamiento/*.java
java -cp out ordenamiento.Comparador resultados      # verificación + gráficas
java -cp out ordenamiento.DemoSelectionSort          # demo paso a paso
```
Parámetros opcionales de `Comparador`: carpeta de salida, n máximo (2000) y paso (100).

## Cómo agregar otro algoritmo
1. Crear `MiAlgoritmo.java` en `src/ordenamiento/` con `implements Ordenamiento`. Debe reiniciar `comparaciones` e `intercambios` en cada `ordenar`.
2. Agregar `new MiAlgoritmo()` a la lista `ALGORITMOS` de `Comparador.java`.
3. Ejecutar `Comparador`: se genera su verificación, sus gráficas y aparece en `comparacion_tiempo.png` y `comparacion_comparaciones.png`.

## Qué verifica el Comparador
- Que el arreglo quede bien ordenado (contra `Arrays.sort`).
- Comparaciones, intercambios y tiempo con datos aleatorios, ordenados e inversos.
- Que ninguna medición supere el peor caso teórico de cada algoritmo (comparaciones e intercambios).

Los tiempos varían entre ejecuciones y equipos; las comparaciones e intercambios no.
