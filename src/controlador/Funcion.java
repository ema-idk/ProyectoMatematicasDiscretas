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
        prestamos = new ArrayList<Prestamo>();
    }
    
    private boolean existeNoPrestamo(int noPrestamo){
        boolean ban = false;
        for(int i = 0; i < prestamos.size(); i++){
            if(prestamos.get(i).getNoPrestamo() == noPrestamo)
                ban = true;
        }
        return ban;
    }
    /*
    public void registrarArticulo(){
        Scanner leerString = new Scanner(System.in);
        Scanner leerInt = new Scanner(System.in);
        Scanner leerDouble = new Scanner(System.in);
        int codigo, cantidad;
        String descripcion;
        double precio;
        do  {
            System.out.print("Codigo del articulo: ");
            codigo = leerInt.nextInt();
        }   while(Funcion.this.existeCodigo(codigo));
        System.out.print("Descripcion del articulo: ");
        descripcion = leerString.nextLine();
        System.out.print("Cantidad en inventario: ");
        cantidad = leerInt.nextInt();
        System.out.print("Precio: ");
        precio = leerDouble.nextDouble();
        Funcion.this.registrarArticulo(codigo, descripcion, cantidad, precio);
        System.out.println("Articulo registrado!");
    }
    
    public void registrarArticulo(int codigo, String descripcion, int cantidad, double precio){
        articulos.add(new Articulo(codigo, descripcion, cantidad, precio));
        Object []arreglo = articulos.toArray();
        ordenacionMerge(arreglo);
        articulos = new ArrayList(Arrays.asList(arreglo));
    }
    
    private Object[] ordenacionMerge(Object []arreglo){
        if(arreglo.length > 1) {
            Object[] first = new Articulo[arreglo.length / 2];
            Object[] second = new Articulo[arreglo.length - first.length];

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
          if ( ((Articulo)first[iFirst]).getCodigo() < ((Articulo)second[iSecond]).getCodigo()) {
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
    
    public void mostrarArticulos(){
        for(Articulo a : articulos){
            System.out.println(a);
        }
    }
    
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
