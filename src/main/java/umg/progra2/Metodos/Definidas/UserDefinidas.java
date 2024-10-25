package umg.progra2.Metodos.Definidas;

import java.util.Scanner;
import java.util.function.BiFunction;

public class UserDefinidas {

    Scanner scanner = new Scanner(System.in);

    public UserDefinidas() {
        try {
            LogicaDefinidas logica = new LogicaDefinidas();

            // Solicitar la función al usuario
            System.out.println("Introduce la función a integrar:");
            String funcionStr = scanner.nextLine();

            // Solicitar el eje de integración
            System.out.println("¿En qué eje deseas integrar? (x/y):");
            String eje = scanner.nextLine().toLowerCase();  // Convertir a minúsculas para evitar errores

            // Solicitar los límites de integración
            double a, b;
            if (eje.equals("x")) {
                // Límites en el eje x
                System.out.println("Introduce el límite inferior de integración en x:");
                String aStr = scanner.nextLine();
                a = LogicaDefinidas.parseInput(aStr);

                System.out.println("Introduce el límite superior de integración en x:");
                String bStr = scanner.nextLine();
                b = LogicaDefinidas.parseInput(bStr);
            } else if (eje.equals("y")) {
                // Límites en el eje y
                System.out.println("Introduce el límite inferior de integración en y:");
                String aStr = scanner.nextLine();
                a = LogicaDefinidas.parseInput(aStr);

                System.out.println("Introduce el límite superior de integración en y:");
                String bStr = scanner.nextLine();
                b = LogicaDefinidas.parseInput(bStr);
            } else {
                System.out.println("Error: Eje inválido. Debes ingresar 'x' o 'y'.");
                return;
            }

            // Convertir la cadena de la función a una BiFunction utilizando LogicaDefinidas
            BiFunction<Double, Double, Double> funcion = logica.convertirFuncion(funcionStr);

            double resultado;

            if (eje.equals("x")) {
                // Integrar respecto a x, y se mantiene constante
                resultado = logica.integrar((x, y) -> funcion.apply(x, 0.0), a, b, 0, 0, "x");
            } else {
                // Integrar respecto a y, x se mantiene constante
                resultado = logica.integrar((x, y) -> funcion.apply(0.0, y), 0, 0, a, b, "y");
            }

            // Redondear el resultado a 3 dígitos decimales
            System.out.printf("Resultado de la integral: %.3f%n", resultado);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
