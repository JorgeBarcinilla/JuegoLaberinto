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
class Matriz {

    private Lista cabeza;
    private Lista cola;
    private int filas;
    private int columnas;
    
    public Matriz()
    {
        this.cabeza=null;
        this.cola=null;
        this.filas=0;
        this.columnas=0;
    }
    
    public void insertar(Object[] botones) {
        
        Lista nuevo = new Lista(botones);
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
        this.filas++;
        this.columnas=botones.length;
    }

    public JLabel ObtenerDato(int x, int y) {
       int contador=0;
       Lista aux = this.cabeza;
       while(contador<x)
       {
           aux=aux.ObtenerSiguiente();
           contador ++;
       }
       JLabel dato=(JLabel)aux.ObtenerDato(y)[0];
       return dato;
    }
    
    public int ObtenerVisita(int x, int y) {
       int contador=0;
       Lista aux = this.cabeza;
       while(contador<x)
       {
           aux=aux.ObtenerSiguiente();
           contador ++;
       }
       int visita=(int)aux.ObtenerDato(y)[1];
       return visita;
    }
    
    public void ActualizarVisita(int x,int y,int visita)
    {
        int contador = 0;
        Lista aux = this.cabeza;
        while (contador < x) 
        {
            aux = aux.ObtenerSiguiente();
            contador++;
        }
        aux.Actualizar(y,visita);
    }
    
    public int VisitaFila(int x)
    {
        
        int visita = 0;
        
        for(int i=0;i<this.tamañoFilas();i++)
        {
            visita=visita+this.ObtenerVisita(x, i);
        }
        
        return visita;
    }
    
    public int VisitaColumna(int x)
    {
        
        int visita = 0;
        
        for(int i=0;i<this.tamañoFilas();i++)
        {
            visita=visita+this.ObtenerVisita(i, x);
        }
        
        return visita;
    }
    
    public int tamañoFilas()
    {
        return filas;
    }
    
    public int tamañoColumnas()
    {
        return columnas;
    }
    public void EliminarTodo()
    {
        this.cabeza=null;
        this.cola=null;
        this.filas=0;
        this.columnas=0;
    }
}
