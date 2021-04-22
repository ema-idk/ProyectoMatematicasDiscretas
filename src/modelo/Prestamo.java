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
    private double importe;

    public Prestamo(int noPrestamo, String nombre, String direccion, String noTelefono, double importe) {
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

    public double getImporte() {
        return importe;
    }
}
