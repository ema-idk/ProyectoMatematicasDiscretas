/*
 * 
 * 
 * 
 */
package modelo;
/**
 *
 * @author Canela
 */
public class Prestamo {
    private int noPrestamo;
    private String nombre;
    private String direccion;
    private String noTelefono;
    private int importe;

    public Prestamo(int noPrestamo, String nombre, String direccion, String noTelefono, int importe) {
        this.noPrestamo = noPrestamo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.noTelefono = noTelefono;
        this.importe = importe;
    }

    public int getNoPrestamo() {
        return noPrestamo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNoTelefono() {
        return noTelefono;
    }

    public int getImporte() {
        return importe;
    }

    @Override
    public String toString() {
        return "Numero de prestamo: " + noPrestamo + "\nNombre del cliente: " + nombre + "\nDireccion: " + direccion + "\nTelefono: " + noTelefono + "\nImporte solicitado: " + importe + '\n';
    } 
}
