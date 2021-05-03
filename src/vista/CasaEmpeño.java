/*
 * 
 * 
 * 
 */
package vista;
import controlador.*;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author Canela
 */
public class CasaEmpeño {
        public static void main(String[] args) {
        Funcion metodo = new Funcion();
        Scanner numero = new Scanner(System.in);
        int op = 0;
        boolean salir = false;
        while(!salir){
            System.out.println("---CASA DE EMPEÑO FLASHCASH---");
            System.out.println("1. Registrar préstamo");
            System.out.println("2. Consulta general");
            System.out.println("3. Consultar préstamo por nombre");
            System.out.println("4. Consulta por número de préstamo");
            System.out.println("5. Importe a pagar");
            System.out.println("6. Salir");
            try{
                System.out.print(">> ");
                op = numero.nextInt();
                switch (op) {
                    case 1:
                        System.out.println("\nREGISTRO");
                        metodo.realizarPrestamo();
                        break;
                    case 2:
                        System.out.println("\nCONSULTA GENERAL");
                        metodo.consultaGeneral();
                        break;
                    case 3:
                        System.out.println("\nCONSULTA POR NOMBRE");
                        metodo.consultarNombre();
                        break;
                    case 4:
                        System.out.println("\nCONSULTA POR PRESTAMO");
                        metodo.consultarNoPrestamo();
                        break;
                    case 5:
                        System.out.println("\nIMPORTE A PAGAR");
                        //metodo.mostrarImporte();
                        break;
                    case 6:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opcion no valida");
                }
            } catch(InputMismatchException e){
                System.out.println("Debes insertar un numero");
                numero.next();
            }
        }       
    }
}
