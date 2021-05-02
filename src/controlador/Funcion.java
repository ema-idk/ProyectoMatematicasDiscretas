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
        double importe;
        //Hacemos una iteracion hasta validar que noPrestamo no este registrado 
        do  {
            System.out.print("Número de préstamo: ");
            noPrestamo = leerInt.nextInt();
        }   while(Funcion.this.existeNoPrestamo(noPrestamo));
        //Introducimos los datos restantes
        System.out.print("Nombre del cliente: ");
        nombre = leerString.nextLine();
        System.out.print("Dirección: ");
        direccion = leerString.nextLine();
        System.out.print("Teléfono: ");
        noTelefono = leerString.nextLine();
        System.out.print("Importe solicitado: ");
        importe = leerDouble.nextDouble();
        //Mandamos llamar la funcion que agrega al ArrayList el prestamo
        Funcion.this.realizarPrestamo(noPrestamo, nombre, direccion, noTelefono, importe);
        System.out.println("Préstamo registrado exitosamente!!");
    }
    
    public void realizarPrestamo(int noPrestamo, String nombre, String direccion, String noTelefono, double importe){
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
        if(arreglo.length > 1) {
            Object[] first = new Prestamo[arreglo.length / 2];
            Object[] second = new Prestamo[arreglo.length - first.length];

            System.arraycopy(arreglo, 0, first, 0, first.length);
            System.arraycopy(arreglo, first.length, second, 0, second.length);

            ordenacionMerge(first);
            ordenacionMerge(second);
          
            merge(first, second, arreglo);
        }
        return arreglo;
    }
    
    private void merge(Object[] first, Object[] second, Object[] result){
        int iFirst = 0;
        int iSecond = 0;
        int iMerged = 0;
        
        while (iFirst < first.length  &&  iSecond < second.length) {
          if (((Prestamo)first[iFirst]).getNombre().compareTo(((Prestamo)second[iSecond]).getNombre()) < 0) {
               result[iMerged++] = first[iFirst];
               iFirst++;
          } else {
               result[iMerged++] = second[iSecond];
               iSecond++;
          }
        }
        
        System.arraycopy(first, iFirst, result, iMerged, first.length - iFirst);
        System.arraycopy(second, iSecond, result, iMerged, second.length - iSecond);
    }
    
    public void consultaGeneral(){
        for(Prestamo p : prestamos){
            System.out.println(p);
        }
    }
    
    /*
    private Articulo busquedaBinaria(int codigo, ArrayList <Articulo>articulos){
        int central;
        int bajo = 0;
        int alto = articulos.size() - 1;
        while (bajo <= alto){
            central = (bajo + alto) / 2;
            int valorCentral = articulos.get(central).getCodigo();
            if (codigo == valorCentral)
               return articulos.get(central);
            else if (codigo < valorCentral)
               alto = central -1;
            else
               bajo = central + 1;
        }
        return null;
    }
    
    public void eliminarArticulo(){
        Scanner leerInt = new Scanner(System.in);
        int codigo;
        System.out.println("Codigo del articulo a eliminar: ");
        System.out.print(">> ");
        codigo = leerInt.nextInt();
        if(articulos.contains(busquedaBinaria(codigo, articulos))){
            articulos.remove(busquedaBinaria(codigo, articulos));
            System.out.println("Articulo eliminado exitosamente!");
        }
        else
            System.out.println("Codigo no encontado!");
    }
    
    public void consultarArticulo(){
        Scanner leerInt = new Scanner(System.in);
        int codigo;
        System.out.println("Codigo del articulo a buscar: ");
        System.out.print(">> ");
        codigo = leerInt.nextInt();
        if(articulos.contains(busquedaBinaria(codigo, articulos))){
            System.out.println("");
            System.out.println(busquedaBinaria(codigo, articulos));
        }
        else
            System.out.println("Codigo no encontado!");        
    }
    
    public void modificarArticulo(){
        Scanner leerInt = new Scanner(System.in);
        Scanner leerString = new Scanner(System.in);
        Scanner leerDouble = new Scanner(System.in);
        int codigo;
        System.out.println("Codigo del articulo a modificar: ");
        System.out.print(">> ");
        codigo = leerInt.nextInt();
        if(articulos.contains(busquedaBinaria(codigo, articulos))){
            int op = 0;
            boolean salir = false;
            while(!salir){
                System.out.println("Introduzca la opcion a modificar");
                System.out.println("1.- Descripcion");
                System.out.println("2.- Cantidad en inventario");
                System.out.println("3.- Precio");
                System.out.println("4.- Regresar al menu");
                try{
                    System.out.print(">> ");
                    op = leerInt.nextInt();
                    switch(op){
                        case 1 :
                            System.out.println("Descripcion nueva: ");
                            System.out.print(">> ");
                            String descripcion = leerString.nextLine();
                            busquedaBinaria(codigo, articulos).setDescripcion(descripcion);
                            System.out.println("Descripcion actualizada!");
                            break;
                        case 2 :
                            System.out.println("Cantidad nueva: ");
                            System.out.print(">> ");
                            int cantidad = leerInt.nextInt();
                            busquedaBinaria(codigo, articulos).setCantidad(cantidad);
                            System.out.println("Cantidad en inventario actualizada!");
                            break;
                        case 3 :
                            System.out.println("Nuevo precio: ");
                            System.out.print(">> ");
                            double precio = leerDouble.nextDouble();
                            busquedaBinaria(codigo, articulos).setPrecio(precio);
                            System.out.println("Precio actualizado!");
                            break;
                        case 4 :
                            salir = true;
                            break;
                        default :
                            System.out.println("Opción no valida");
                    }
                }catch(InputMismatchException e){
                    System.out.println("debes insertar un numero");
                    leerInt.next();
                }
            }
        }
        else
            System.out.println("Codigo no encontado!");     
    }*/
}
