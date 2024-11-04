
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        System.out.println("Se bienvenido/a al Coversor de Moneda");
        Scanner scanner = new Scanner(System.in);
        ConsultaMoneda miMoneda = new ConsultaMoneda();
        int selecionarMenu=0;
        while (true){
        ConsultaMoneda.mostrarMenu();
        System.out.println("Selecciona una opcion valida");
        selecionarMenu = Integer.parseInt(scanner.nextLine());
            if (selecionarMenu == 7) {
                System.out.println("¡Gracias por usar nuestro conversor!");
                break;
            }
        System.out.println("Ingrese la cantidad que desea convertir");
        double cantidad = Double.parseDouble(scanner.nextLine());
            System.out.println(miMoneda.calcularMoneda(selecionarMenu,cantidad));

        }
    }
}
