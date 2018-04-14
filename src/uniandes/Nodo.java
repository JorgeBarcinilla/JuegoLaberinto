/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniandes;


/**
 *
 * @author Jorge
 */
public class Nodo {
    
    private final Object[] valor;
    private Nodo siguiente;
    private Nodo anterior;
    
    public Nodo (Object[] valor)
    {
        this.valor=valor;
        this.siguiente=null;
        this.anterior=null;
    }
    
    public void enlazarSiguiente(Nodo sig)
    {
        this.siguiente=sig;
    }
    
    public void enlazarAnterior(Nodo ant)
    {
        this.anterior=ant;
    }   
    
    public Nodo ObtenerSiguiente()
    {
        return siguiente;
    }
    
    public Nodo ObtenerAnterior()
    {
        return anterior;
    }
    
    public Object[] ObtenerValor()
    {
        return valor;
    }
    
    public void ActualizarVisita(int visita)
    {
        this.valor[1]=visita;
    }
    
}
