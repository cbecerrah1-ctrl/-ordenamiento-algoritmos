/*
 * Analisis de Algoritmos - Universidad Central
 * Autor: Cesar Camilo Becerra Herrera
 * Profesor: Giovanny Alexander Riveros
 * Tema: Selection Sort - comparativo de algoritmos de ordenamiento
 */
package ordenamiento;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

/**
 * Comparador de algoritmos de ordenamiento.
 *
 * Para cada algoritmo, por separado:
 *   1. VERIFICA que ordena bien (compara contra Arrays.sort).
 *   2. Mide comparaciones, intercambios y tiempo con 3 casos: aleatorio, ordenado e inverso.
 *   3. Genera sus graficas y las compara con la formula teorica.
 * Al final genera graficas que COMPARAN todos los algoritmos entre si.
 *
 * Uso: java ordenamiento.Comparador [carpetaSalida] [nMaximo] [paso]
 */
public class Comparador {

    /** Para agregar otro algoritmo: new MiAlgoritmo() en esta lista. */
    static final List<Ordenamiento> ALGORITMOS = List.of(
            new SelectionSort(),
            new InsertionSort(),
            new MergeSort()
            // , new MiAlgoritmo() ...
    );

    static final String[] CASOS = {"Aleatorio", "Ordenado", "Inverso"};

    public static void main(String[] args) throws IOException {
        System.setProperty("java.awt.headless", "true");
        File salida = new File(args.length > 0 ? args[0] : "resultados");
        int nMax = args.length > 1 ? Integer.parseInt(args[1]) : 2000;
        int paso = args.length > 2 ? Integer.parseInt(args[2]) : 100;
        salida.mkdirs();

        int puntos = nMax / paso;
        double[] xs = new double[puntos];
        for (int i = 0; i < puntos; i++) xs[i] = (i + 1) * paso;

        // calentamiento de la JVM
        for (Ordenamiento alg : ALGORITMOS) {
            for (int r = 0; r < 20; r++) alg.ordenar(generar(2000, "Aleatorio", 1));
        }

        LinkedHashMap<String, double[]> tiempoAleatorio = new LinkedHashMap<>();
        LinkedHashMap<String, double[]> compAleatorio = new LinkedHashMap<>();
        LinkedHashMap<String, double[]> interAleatorio = new LinkedHashMap<>();
        List<String> csv = new ArrayList<>();
        csv.add("algoritmo,caso,n,comparaciones,intercambios,microsegundos");

        for (Ordenamiento alg : ALGORITMOS) {
            System.out.println("\n=== " + alg.nombre() + "  " + alg.complejidad() + " ===");
            LinkedHashMap<String, double[]> comp = new LinkedHashMap<>();
            LinkedHashMap<String, double[]> inter = new LinkedHashMap<>();
            LinkedHashMap<String, double[]> tiempo = new LinkedHashMap<>();
            double[] teoricoComp = new double[puntos];
            double[] teoricoInter = new double[puntos];
            for (int i = 0; i < puntos; i++) {
                teoricoComp[i] = alg.teoricoComparaciones((int) xs[i]);
                teoricoInter[i] = alg.teoricoIntercambios((int) xs[i]);
            }
            comp.put("Peor caso teorico", teoricoComp);
            inter.put("Maximo teorico", teoricoInter);

            boolean todoOk = true;
            for (String caso : CASOS) {
                double[] c = new double[puntos], s = new double[puntos], t = new double[puntos];
                for (int i = 0; i < puntos; i++) {
                    int n = (int) xs[i];
                    int[] datos = generar(n, caso, 42);
                    int[] esperado = datos.clone();
                    Arrays.sort(esperado);

                    int[] copia = datos.clone();
                    alg.ordenar(copia);
                    if (!Arrays.equals(copia, esperado)) {
                        todoOk = false;
                        System.out.println("ERROR: mal ordenado con n=" + n + " caso " + caso);
                    }
                    c[i] = alg.getComparaciones();
                    s[i] = alg.getIntercambios();
                    if (c[i] > teoricoComp[i] + 1e-9 || s[i] > teoricoInter[i] + 1e-9) {
                        todoOk = false;
                        System.out.println("ERROR: supera el peor caso teorico con n=" + n + " caso " + caso);
                    }
                    t[i] = medirMicrosegundos(alg, datos);
                    csv.add(alg.nombre() + "," + caso + "," + n + "," + (long) c[i] + "," + (long) s[i]
                            + "," + String.format("%.2f", t[i]).replace(',', '.'));
                }
                comp.put(caso, c);
                inter.put(caso, s);
                tiempo.put(caso, t);
                System.out.printf("%-10s n=%d: comparaciones=%d intercambios=%d tiempo=%.1f us%n",
                        caso, (int) xs[puntos - 1], (long) c[puntos - 1], (long) s[puntos - 1], t[puntos - 1]);
            }
            System.out.println("Verificacion (ordena bien y no supera el peor caso teorico): "
                    + (todoOk ? "CORRECTO" : "CON ERRORES"));
            System.out.printf("Comparaciones: mejor caso %d, aleatorio %d, peor caso teorico %d (n=%d)%n",
                    (long) comp.get("Ordenado")[puntos - 1], (long) comp.get("Aleatorio")[puntos - 1],
                    (long) teoricoComp[puntos - 1], (int) xs[puntos - 1]);

            String base = alg.nombre().toLowerCase().replace(' ', '_');
            Grafica.guardar(alg.nombre() + " " + alg.complejidad() + ": comparaciones reales vs peor caso teorico", "Cantidad de datos (n)",
                    "Comparaciones", xs, comp, new File(salida, base + "_comparaciones.png"));
            Grafica.guardar(alg.nombre() + ": intercambios o movimientos vs maximo teorico", "Cantidad de datos (n)",
                    "Intercambios / movimientos", xs, inter, new File(salida, base + "_intercambios.png"));
            Grafica.guardar(alg.nombre() + ": tiempo de ejecucion", "Cantidad de datos (n)",
                    "Tiempo (microsegundos)", xs, tiempo, new File(salida, base + "_tiempo.png"));

            tiempoAleatorio.put(alg.nombre() + " " + alg.complejidad(), tiempo.get("Aleatorio"));
            compAleatorio.put(alg.nombre() + " " + alg.complejidad(), comp.get("Aleatorio"));
            interAleatorio.put(alg.nombre() + " " + alg.complejidad(), inter.get("Aleatorio"));
        }

        Grafica.guardar("Comparacion de algoritmos: tiempo (datos aleatorios)", "Cantidad de datos (n)",
                "Tiempo (microsegundos)", xs, tiempoAleatorio, new File(salida, "comparacion_tiempo.png"));
        Grafica.guardar("Comparacion de algoritmos: comparaciones (datos aleatorios)", "Cantidad de datos (n)",
                "Comparaciones", xs, compAleatorio, new File(salida, "comparacion_comparaciones.png"));

        Grafica.guardar("Comparacion de algoritmos: intercambios / movimientos (datos aleatorios)",
                "Cantidad de datos (n)", "Intercambios / movimientos", xs, interAleatorio,
                new File(salida, "comparacion_intercambios.png"));

        try (PrintWriter pw = new PrintWriter(new File(salida, "resultados.csv"), "UTF-8")) {
            csv.forEach(pw::println);
        }
        System.out.println("\nGraficas y resultados.csv guardados en: " + salida.getAbsolutePath());
        System.out.println("El tiempo puede variar entre ejecuciones y equipos.");
    }

    static int[] generar(int n, String caso, long semilla) {
        int[] a = new int[n];
        Random azar = new Random(semilla);
        for (int i = 0; i < n; i++) a[i] = azar.nextInt(100000);
        if (caso.equals("Ordenado")) Arrays.sort(a);
        if (caso.equals("Inverso")) {
            Arrays.sort(a);
            for (int i = 0; i < n / 2; i++) {
                int t = a[i];
                a[i] = a[n - 1 - i];
                a[n - 1 - i] = t;
            }
        }
        return a;
    }

    /** Mediana de varias repeticiones, en microsegundos. */
    static double medirMicrosegundos(Ordenamiento alg, int[] datos) {
        int reps = 7;
        long[] tiempos = new long[reps];
        for (int r = 0; r < reps; r++) {
            int[] copia = datos.clone();
            long inicio = System.nanoTime();
            alg.ordenar(copia);
            tiempos[r] = System.nanoTime() - inicio;
        }
        Arrays.sort(tiempos);
        return tiempos[reps / 2] / 1000.0;
    }
}
