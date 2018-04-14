/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniandes;

import javax.swing.JLabel;

/**
 *
 * @author Jorge
 */
public class Lista {
    
    private Lista siguiente;
    private Lista anterior;
    private Nodo cabeza;
    private Nodo cola;
    private final Object[] datos;
    private int tamaño;
    
    public Lista(Object[] datos)
    {
        this.datos=datos;
        this.siguiente=null;
        this.anterior=null;
        this.tamaño=datos.length;
        this.insertarFinal();
    }

    public void Tamaño(int tamaño) {
        this.tamaño = tamaño;
    }
    
    public void enlazarSiguiente(Lista sig)
    {
        this.siguiente=sig;
    }
    
    public void enlazarAnterior(Lista ant)
    {
        this.anterior=ant;
    }   
    
    public Lista ObtenerSiguiente()
    {
        return siguiente;
    }
    
    public Lista ObtenerAnterior()
    {
        return anterior;
    }
    
    public int obtenerTamaño()
    {
        return this.tamaño;
    }
    
    public void Actualizar(int index,int visita)
    {
       Nodo aux = this.cabeza;
       for(int i=0;i<index;i++)
       {
           aux=aux.ObtenerSiguiente();
       }
       
       aux.ActualizarVisita(visita);
    }
    
    
    public void insertarInicio()
    {
        for(int i=0;i<tamaño;i++)
        {
            Object[] dato=(Object[])this.datos[i];
            Nodo nuevo = new Nodo(dato);
            if (this.cabeza == null) 
            {
                this.cabeza = nuevo;
                this.cola = nuevo;
            }
            else 
            {
                nuevo.enlazarSiguiente(this.cabeza);
                cabeza.enlazarAnterior(nuevo);
                this.cabeza = nuevo;
            }
        }
    }
    
    public void insertarFinal()
    {
        for(int i=0;i<tamaño;i++)
        {
            Object[] dato=(Object[])this.datos[i];
            Nodo nuevo = new Nodo(dato);
            if (this.cabeza == null) 
            {
                this.cabeza = nuevo;
                this.cola = nuevo;
            } 
            else 
            {
                this.cola.enlazarSiguiente(nuevo);
                nuevo.enlazarAnterior(this.cola);
                this.cola = nuevo;
            }
        
        }
    }
    
    public Object[] ObtenerDato(int indice)
   {
       int contador=0;
       Nodo aux = this.cabeza;
       while(contador<indice)
       {
           aux=aux.ObtenerSiguiente();
           contador ++;
       }
       
       return aux.ObtenerValor();
   }
    
    
    public void recorrerListaDobleAdelante()
    {
        Nodo aux = this.cabeza;
       for(int i=0;i<tamaño;i++)
       {
           Object[] dato=(Object[])aux.ObtenerValor();
           JLabel label=(JLabel)dato[0];
           System.out.println("dato del nodo "+(i+1)+" : "+label.getName());
           aux=aux.ObtenerSiguiente();
       }
    }
    
    
    
    
}
