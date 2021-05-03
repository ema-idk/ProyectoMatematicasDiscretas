/*
 * Este código lo saque de un proyecto pasado xD, luego lo iremos modificando
 * 
 * 
 */
package controlador;
import modelo.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
/**
 *
 * @author Canela
 */
public class Funcion {
    private ArrayList<Prestamo> prestamos;
    
    public Funcion(){
        //Declaramos un objeto de tipo ArrayList
        prestamos = new ArrayList<Prestamo>();
    }
    
    private boolean existeNoPrestamo(int noPrestamo){
        boolean ban = false;
        //Realizamos una busqueda por todo el arreglo hasta que encuentre el noPrestamo
        for(int i = 0; i < prestamos.size(); i++){
            //En caso de encontrarlo, lo marcara como verdadero
            if(prestamos.get(i).getNoPrestamo() == noPrestamo)
                ban = true;
        }
        //Retorna al valor que tenga ban, si es falso es que no existe
        return ban;
    }
    
    public void realizarPrestamo(){
        //Declaro los y objetos y las variables
        //Para leer cadenas 
        Scanner leerString = new Scanner(System.in);
        //Para leer enteros
        Scanner leerInt = new Scanner(System.in);
        //Para leer decimales
        Scanner leerDouble = new Scanner(System.in);
        int noPrestamo;
        String nombre, direccion, noTelefono;
        int importe;
        int ciclo;
        do {
            //Hacemos una iteracion hasta validar que noPrestamo no este registrado 
            do  {
                System.out.print("Número de préstamo: ");
                noPrestamo = leerInt.nextInt();
            }   while(existeNoPrestamo(noPrestamo));
            //Introducimos los datos restantes
            System.out.print("Nombre del cliente: ");
            nombre = leerString.nextLine();
            System.out.print("Dirección: ");
            direccion = leerString.nextLine();
            System.out.print("Teléfono: ");
            noTelefono = leerString.nextLine();
            System.out.print("Importe solicitado: ");
            importe = leerInt.nextInt();
            //Mandamos llamar la funcion que agrega al ArrayList el prestamo
            realizarPrestamo(noPrestamo, nombre, direccion, noTelefono, importe);
            System.out.println("Préstamo registrado exitosamente!!");
            System.out.println("Quieres agregar otro registro? \n[1-Si / 2-No]: ");
            ciclo = leerInt.nextInt();
        } while(ciclo == 1);
    }
    
    private void realizarPrestamo(int noPrestamo, String nombre, String direccion, String noTelefono, int importe){
        //Agregamos los valores introducidos al ArrayList como un prestamo 
        prestamos.add(new Prestamo(noPrestamo, nombre, direccion, noTelefono, importe)); 
        //Casteamos el ArrayList como un objeto de tipo Array para realizar la ordenacion 
        Object []arreglo = prestamos.toArray();
        //Realizamos un ordenamiento del arreglo con el algoritmo merge sort
        ordenacionMerge(arreglo);
        //Introducimos al ArrayList el arreglo con los datos ya ordenados
        prestamos = new ArrayList(Arrays.asList(arreglo));
       
    }
    
    
    private Object[] ordenacionMerge(Object []arreglo){
        //Si el arreglo es mayor a 1, entonces realizamos el ordenamiento
        if(arreglo.length > 1) {
            //El arreglo se divide en 2 mitades de similar longitud
            Object[] first = new Prestamo[arreglo.length / 2];
            Object[] second = new Prestamo[arreglo.length - first.length];
            //Se traslada el valor del un elemento del arreglo a otra posicion dentro de otro arreglo
            System.arraycopy(arreglo, 0, first, 0, first.length);
            System.arraycopy(arreglo, first.length, second, 0, second.length);
            //De manera recursiva el arreglo se ira ordenando, aplicandolo a cada mitad
            ordenacionMerge(first);
            ordenacionMerge(second);
            //Juntamos los resultados y ordenamos
            merge(first, second, arreglo);
        }
        //Regresamos el arreglo ya ordenado
        return arreglo;
    }
    
    private void merge(Object[] first, Object[] second, Object[] result){
        int iFirst = 0;
        int iSecond = 0;
        int iMerged = 0;
        //Comparamos lexicograficamente el valor de una con otra de las mitades del arreglo
        while (iFirst < first.length  &&  iSecond < second.length) {
          if (((Prestamo)first[iFirst]).getNoPrestamo() < ((Prestamo)second[iSecond]).getNoPrestamo()) {
               result[iMerged++] = first[iFirst];
               iFirst++;
          } else {
               result[iMerged++] = second[iSecond];
               iSecond++;
          }
        }
        //Reacomodamos los valores dentro del arreglo para que esten ordenados
        System.arraycopy(first, iFirst, result, iMerged, first.length - iFirst);
        System.arraycopy(second, iSecond, result, iMerged, second.length - iSecond);
    }
    
    public void consultaGeneral(){
        ArrayList<Prestamo> prestamos2 = (ArrayList<Prestamo>) prestamos.clone();
        Collections.sort(prestamos2, new Comparator<Prestamo>() {
            @Override
            public int compare(Prestamo p1, Prestamo p2) {
                return p1.getNombre().compareTo(p2.getNombre() );
            }
        });
        for(Prestamo p : prestamos2){
            System.out.println(p);
        }
    }
    
    private Prestamo busquedaBinaria(int noPrestamo, ArrayList<Prestamo> prestamos) {
        int central;
        int bajo = 0;
        int alto = prestamos.size() - 1;
        while (bajo <= alto) {
            central = (bajo + alto) / 2;
            int valorCentral = prestamos.get(central).getNoPrestamo();
            if (noPrestamo == valorCentral) {
                return prestamos.get(central);
            } else if (noPrestamo < valorCentral) {
                alto = central - 1;
            } else {
                bajo = central + 1;
            }
        }
        return null;
    }
    
    public void consultarNoPrestamo() {
        Scanner leerInt = new Scanner(System.in);
        int noPrestamo;
        System.out.println("\nNúmero del préstamo a buscar: ");
        System.out.print(">> ");
        noPrestamo = leerInt.nextInt();
        if (prestamos.contains(busquedaBinaria(noPrestamo, prestamos))) {
            System.out.println(busquedaBinaria(noPrestamo, prestamos));
        } else {
            System.out.println("\nPrestamo no encontrado");
        }
    }
    
    private Prestamo busquedaSecuencial(String nombre, ArrayList<Prestamo> prestamos) {
        for (int n = 0; n < prestamos.size() ; n++) {
            if (prestamos.get(n).getNombre().equals(nombre)) {
                return prestamos.get(n);
            }
        }
        return null;
    }
    
        public void consultarNombre() {
        Scanner leerString = new Scanner(System.in);
        String nombre;
        System.out.println("\nNombre a buscar: ");
        System.out.print(">> ");
        nombre = leerString.nextLine();
        if (prestamos.contains(busquedaSecuencial(nombre, prestamos))) {
            System.out.println(busquedaSecuencial(nombre, prestamos));
        } else {
            System.out.println("\nPrestamo no encontrado");
        }
    }

    private double calcularImporte(int n_meses, double importe, double interes) {
        if(n_meses == 1) {
           importe = importe + (importe * interes);
           return importe;
        }
        else
           importe = importe + (importe * interes);
           return calcularImporte(n_meses - 1, importe, interes);
    }
        
    public void mostrarImporte() {
        Scanner leerInt = new Scanner(System.in);
        int noPrestamo;
        System.out.println("Número de préstamo: ");
        noPrestamo = leerInt.nextInt();
        if (prestamos.contains(busquedaBinaria(noPrestamo, prestamos))) {
            int importe = busquedaBinaria(noPrestamo, prestamos).getImporte();
            System.out.println("Cantidad de meses a pagar: ");
            int n_meses = leerInt.nextInt();
            double interes = 0.10;
            double importePagar = calcularImporte(n_meses, importe, interes);
            System.out.println("Importe a pagar: " + importePagar);
        } else {
            System.out.println("\nPréstamo no encontrado");
        }
    }
}
