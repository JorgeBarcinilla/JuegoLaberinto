/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniandes;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jorge
 */
public class VerVisita implements MouseListener {

    JButton MasVisitada;
    Matriz matriz;
    Matriz matriz3;
    JPanel escenarioVista;
    JLabel interuptor;

    public VerVisita(JButton MasVisitada, Matriz matriz, Matriz matriz3, JPanel escenarioVista,JLabel interuptor) {
        this.matriz3=matriz3;
        this.escenarioVista=escenarioVista;
        this.MasVisitada=MasVisitada;
        this.matriz=matriz;
        this.interuptor=interuptor;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        if(this.MasVisitada.getName().equals("fila"))
        {
            int cantVisita=0;
            int fila=0;
            for(int i=0;i<this.matriz.tamañoFilas();i++)
            {
                
                if(this.matriz.VisitaFila(i)>cantVisita)
                {
                    fila=i;
                    cantVisita=this.matriz.VisitaFila(i);
                    
                }
            }
            for (int j = 0; j < this.matriz.tamañoColumnas(); j++) 
            {
                this.matriz3.ObtenerDato(fila, j).setVisible(true);
            }
            if(cantVisita>0 & this.interuptor.getText().equals("si"))
            {
                this.escenarioVista.setVisible(true);
            }
        }
        if(this.MasVisitada.getName().equals("columna"))
        {
            int cantVisita=0;
            int columna=0;
            for(int i=0;i<this.matriz.tamañoColumnas();i++)
            {
                
                if(this.matriz.VisitaColumna(i)>cantVisita)
                {
                    columna=i;
                    cantVisita=this.matriz.VisitaColumna(i);
                }
            }
            for (int j = 0; j < this.matriz.tamañoFilas(); j++) 
            {
                this.matriz3.ObtenerDato(j, columna).setVisible(true);
            }
            if(cantVisita>0 & this.interuptor.getText().equals("si"))
            {
                this.escenarioVista.setVisible(true);
            }
        }
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
        this.escenarioVista.setVisible(false);
        for(int i=0;i<this.matriz3.tamañoFilas();i++)
        {
            for(int j=0;j<this.matriz3.tamañoColumnas();j++)
            {
                this.matriz3.ObtenerDato(i, j).setVisible(false);
            }
        }
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
