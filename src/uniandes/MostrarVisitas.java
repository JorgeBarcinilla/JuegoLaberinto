/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniandes;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author Jorge
 */
public class MostrarVisitas implements MouseListener {

    JPanel escenarioDatos;
    Matriz matriz2;
    Jugador jugador;
    
    public MostrarVisitas(JPanel escenarioDatos,Matriz matriz2,Jugador jugador) {
        this.escenarioDatos=escenarioDatos;
        this.matriz2=matriz2;
        this.jugador=jugador;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        Color verde=new Color(26,137,0);
        Color naranja=new Color(231,125,0);
        
        for(int i=0;i<this.matriz2.tamañoFilas();i++)
        {
            for(int j=0;j<this.matriz2.tamañoColumnas();j++)
            {
                
                if(this.matriz2.ObtenerDato(i, j).getName().equals("jugador"))
                {
                    if ((i + 1) < this.matriz2.tamañoFilas()) 
                    {
                        if (this.matriz2.ObtenerDato(i + 1, j).getName().equals("0"))
                        {
                            int visitas=(int)Double.parseDouble(this.matriz2.ObtenerDato(i+1, j).getText());
                            int limiteVisitas=this.jugador.getVisitasCasillas();
                            double resultado=(visitas*100)/limiteVisitas;
                            if(resultado<=50.0)
                            {
                                this.matriz2.ObtenerDato(i+1, j).setForeground(verde);
                            }
                            else
                            {
                                if(resultado<=75.0)
                                {
                                    this.matriz2.ObtenerDato(i+1, j).setForeground(naranja);
                                }
                                else
                                {
                                    this.matriz2.ObtenerDato(i+1, j).setForeground(Color.red);
                                }
                            }
                            this.matriz2.ObtenerDato(i + 1, j).setVisible(true);
                        }
                    }
                    if(this.matriz2.ObtenerDato(i-1, j).getName().equals("0") & (i-1)>=0)
                    {
                        int visitas = (int) Double.parseDouble(this.matriz2.ObtenerDato(i-1, j).getText());
                        int limiteVisitas = this.jugador.getVisitasCasillas();
                        double resultado=(visitas*100)/limiteVisitas;
                        if (resultado <= 50.0) 
                        {
                            this.matriz2.ObtenerDato(i-1, j).setForeground(verde);
                        } 
                        else 
                        {
                            if (resultado <= 75.0) 
                            {
                                this.matriz2.ObtenerDato(i-1, j).setForeground(naranja);
                            } 
                            else
                            {
                                this.matriz2.ObtenerDato(i-1, j).setForeground(Color.red);
                            }
                        }
                        this.matriz2.ObtenerDato(i-1, j).setVisible(true);
                    }
                    if ((j + 1) < this.matriz2.tamañoColumnas())
                    {
                        if (this.matriz2.ObtenerDato(i, j + 1).getName().equals("0")) 
                        {
                            int visitas=(int)Double.parseDouble(this.matriz2.ObtenerDato(i, j + 1).getText());
                            int limiteVisitas=this.jugador.getVisitasCasillas();
                            double resultado=(visitas*100)/limiteVisitas;
                            if(resultado<=50.0)
                            {
                                this.matriz2.ObtenerDato(i, j + 1).setForeground(verde);
                            }
                            else
                            {
                                if(resultado<=75.0)
                                {
                                    this.matriz2.ObtenerDato(i, j + 1).setForeground(naranja);
                                }
                                else
                                {
                                    this.matriz2.ObtenerDato(i, j + 1).setForeground(Color.red);
                                }
                            }
                            this.matriz2.ObtenerDato(i, j + 1).setVisible(true);
                        }
                    }
                    if(this.matriz2.ObtenerDato(i, j-1).getName().equals("0") & (j+1)>=0)
                    {
                        int visitas = (int) Double.parseDouble(this.matriz2.ObtenerDato(i, j-1).getText());
                        int limiteVisitas = this.jugador.getVisitasCasillas();
                        double resultado=(visitas*100)/limiteVisitas;
                        if (resultado <= 50.0)
                        {
                            this.matriz2.ObtenerDato(i, j-1).setForeground(verde);
                        }
                        else 
                        {
                            if (resultado <= 75.0)
                            {
                                this.matriz2.ObtenerDato(i, j-1).setForeground(naranja);
                            } 
                            else 
                            {
                                this.matriz2.ObtenerDato(i, j-1).setForeground(Color.red);
                            }
                        }
                        this.matriz2.ObtenerDato(i, j-1).setVisible(true);
                    }
                    
                }
            }
        }
        this.escenarioDatos.setVisible(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.escenarioDatos.setVisible(false);
        for(int i=0;i<this.matriz2.tamañoFilas();i++)
        {
            for(int j=0;j<this.matriz2.tamañoColumnas();j++)
            {
                this.matriz2.ObtenerDato(i, j).setVisible(false);
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
