/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author LN710Q
 */
public class filtro {
    private int id;
    private String codigo;
    private double precio;
    private String nombre;
    private int cantidad;
    private String tipo;
    private boolean disponibilidad;
    
    
    
    public filtro(){
    }
    public filtro(int id, String nombre, String codigo,String tipo,int cantidad, double precio, boolean dispo){
    this.id = id;
    this.nombre = nombre;
    this.codigo = codigo;
    this.tipo = tipo;
    this.cantidad = cantidad;
    this.precio = precio;
    this.disponibilidad = dispo;
    }
    public filtro(String codigo, String tipo, double precio,int cantidad, boolean dispo) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.precio = precio;
        this.cantidad= cantidad;
        this.disponibilidad = dispo;
    }
    public filtro(String tipo, int cantidad, boolean dispo){
    this.tipo = tipo;
    this.cantidad= cantidad;
    this.disponibilidad = dispo;
    }
    
    
    
    public int getId(){
        return this.id;
    }
    public String getCodigo(){
        return this.codigo;
    }
    public String getNombre(){
        return this.nombre;
    }
    public int getCantidad(){
        return this.cantidad;
    }
    public String getTipo(){
        return this.tipo;
    }
    public boolean getDispo(){
        return this.disponibilidad;
    }
    public double getPrecio(){
        return this.precio;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    public void setTipo(String tipo){
        this.tipo= tipo;
    }
    public void setDisponibilidad(boolean dispo){
        this.disponibilidad = dispo;
    }
    public void setPrecio(double precio){
        this.precio= precio;
    }
    
}
