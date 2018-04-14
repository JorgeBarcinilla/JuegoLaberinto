/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniandes;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Jorge
 */
public class Cargar implements ActionListener {

    JPanel escenario;
    Matriz matriz;
    Jugador jugador;
    JLabel cantCasilla;
    JLabel cantEspecial;
    JPanel escenarioDatos;
    Matriz matriz2;
    JLabel cantAmarilla;
    JLabel cantAzul;
    JLabel cantRoja;
    JButton[] botones;
    JFrame ventana;
    File url;
    JPanel[] paneles;
    Matriz matriz3;
    JLabel interuptor;

    public Cargar(JFrame ventana,JPanel escenario, JPanel escenarioDatos, JButton[] botones,Matriz matriz,Matriz matriz2,Matriz matriz3,Jugador jugador,JLabel cantEspecial,JLabel cantCasilla,JLabel cantAmarilla,JLabel cantAzul,JLabel cantRoja,JPanel[] paneles,JLabel interuptor) {
        this.escenario = escenario;
        this.botones=botones;
        this.matriz=matriz;
        this.jugador=jugador;
        this.cantEspecial=cantEspecial;
        this.cantCasilla=cantCasilla;
        this.escenarioDatos=escenarioDatos;
        this.matriz2=matriz2;
        this.cantRoja=cantRoja;
        this.cantAzul=cantAzul;
        this.cantAmarilla=cantAmarilla;
        this.ventana=ventana;
        this.paneles=paneles;
        this.matriz3=matriz3;
        this.interuptor=interuptor;
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        JFileChooser buscar=new JFileChooser("./data");
        buscar.setDialogTitle("Cargar nivel");
        buscar.setMultiSelectionEnabled(false);
        
        int resultado=buscar.showOpenDialog(this.escenario);
        
            if (resultado == JFileChooser.APPROVE_OPTION) 
            {
                this.escenario.removeAll();
                this.escenarioDatos.removeAll();
                this.matriz.EliminarTodo();
                this.matriz2.EliminarTodo();
                this.matriz3.EliminarTodo();
                this.jugador.Reiniciar();
                this.paneles[6].removeAll();
                this.paneles[8].removeAll();
                this.cantCasilla.setText("");
                this.cantEspecial.setText("");
                this.cantAmarilla.setText("");
                this.cantAzul.setText("");
                this.cantRoja.setText("");
                
                url = buscar.getSelectedFile();

                if (url.getName().contains(".cp2")) 
                {
                    Cargar();
                } 
                else 
                {
                    
                    JLabel advertencia = new JLabel("Archivo no valido");
                    advertencia.setSize(200, 200);
                    advertencia.setFont(new Font("Rockwell Extra Bold",1,16));
                    advertencia.setHorizontalAlignment(JLabel.CENTER);
                    advertencia.setLocation((int) ((this.paneles[6].getSize().getWidth() / 2) - (advertencia.getSize().getWidth() / 2)), (int) ((this.paneles[6].getSize().getHeight() / 2) - (advertencia.getSize().getHeight() / 2)));
                    this.paneles[6].add(advertencia);
                    this.paneles[6].updateUI();
                    this.paneles[8].removeAll();
                }
                
            }
        
        
    }
        
    public void Cargar()
    {
        this.escenario.removeAll();
        this.escenarioDatos.removeAll();
        this.matriz.EliminarTodo();
        this.matriz2.EliminarTodo();
        this.matriz3.EliminarTodo();
        this.jugador.Reiniciar();
        this.paneles[6].removeAll();
        this.paneles[8].removeAll();
        this.cantCasilla.setText("");
        this.cantEspecial.setText("");
        this.cantAmarilla.setText("");
        this.cantAzul.setText("");
        this.cantRoja.setText("");
        
        try (FileInputStream fis = new FileInputStream(url))
            {
                
                Properties mostrar = new Properties();
                mostrar.load(fis);
                
                String[]claves1={"filas","columnas","posX","posY","chips","cantidadMaximaVisitas"};
                int contador=0;
                for(String clave:claves1)
                {
                    for(int i=0;i<mostrar.getProperty(clave).length();i++)
                    {
                        char letra=mostrar.getProperty(clave).charAt(i);
                        if(!Character.isDigit(letra))
                        {
                            contador=contador+1;
                        }
                    }
                }
                
                if(contador==0)
                {
                    int filas = (int) Double.parseDouble(mostrar.getProperty("filas"));
                    int columnas = (int) Double.parseDouble(mostrar.getProperty("columnas"));
                    int x = (int) Double.parseDouble(mostrar.getProperty("posX"));
                    int y = (int) Double.parseDouble(mostrar.getProperty("posY"));
                    
                    String[] claves2=new String[filas];
                    for(int i=0;i<filas;i++)
                    {
                        claves2[i]="fila"+i;
                    }
                    
                    int contador2 = 0;
                    for (String clave : claves2) 
                    {
                        boolean estado=true;
                        String[] digitos=mostrar.getProperty(clave).split("-");
                        for (String digito : digitos) 
                        {
                            for (int i = 0; i < digito.length(); i++) 
                            {
                                char letra = digito.charAt(i);
                                if (!Character.isDigit(letra))
                                {
                                    contador2 = contador2 + 1;
                                    estado = false;
                                    break;
                                }
                            }
                            if (estado)
                            {
                                if ((int) Double.parseDouble(digito) > 10) 
                                {
                                    contador2 = contador2 + 1;
                                    break;
                                }
                            }
                            else 
                            {
                                break;
                            }
                        }
                        if(!estado)
                        {
                            break;
                        }
                        
                    }
                    
                    if(contador2==0)
                    {
                        
                        this.jugador.setLlavesEspeciales((int) Double.parseDouble(mostrar.getProperty("chips")));
                        this.jugador.setVisitasCasillas((int) Double.parseDouble(mostrar.getProperty("cantidadMaximaVisitas")));
                        this.cantCasilla.setText(mostrar.getProperty("cantidadMaximaVisitas"));
                        this.cantEspecial.setText(mostrar.getProperty("chips"));
                        this.cantAmarilla.setText("0");
                        this.cantAzul.setText("0");
                        this.cantRoja.setText("0");
                        this.escenario.setSize((51 * columnas), (52 * filas));
                        this.escenarioDatos.setSize(this.escenario.getSize());
                        this.paneles[8].setSize(this.escenario.getSize());
                        this.paneles[6].setSize(this.escenario.getSize());
                        this.paneles[0].setSize((int) (this.escenario.getSize().getWidth() + this.paneles[1].getSize().getWidth() + this.paneles[5].getSize().getWidth()), (int) this.paneles[0].getSize().getHeight());
                        this.paneles[5].setSize((int) this.paneles[5].getSize().getWidth(), (int) this.escenario.getSize().getHeight());
                        this.paneles[1].setBounds((int) (this.paneles[5].getSize().getWidth() + this.escenario.getSize().getWidth()), (int) this.paneles[0].getSize().getHeight(), (int) this.paneles[1].getSize().getWidth(), (int) (this.escenario.getSize().getHeight() / 2));
                        this.paneles[7].setLocation(0, (int) ((this.paneles[1].getSize().getHeight() / 2) - (this.paneles[7].getSize().getHeight() / 2)));
                        this.paneles[4].setBounds((int) (this.paneles[5].getSize().getWidth() + this.escenario.getSize().getWidth()), (int) (this.paneles[0].getSize().getHeight() + this.paneles[1].getSize().getHeight()), (int) this.paneles[1].getSize().getWidth(), (int) (this.escenario.getSize().getHeight() / 2));
                        this.ventana.setSize((int) (this.paneles[0].getSize().getWidth() + 6), (int) ((this.paneles[0].getSize().getHeight() + this.escenario.getSize().getHeight()) + 30));
                        this.ventana.setLocationRelativeTo(null);
                        this.interuptor.setText("si");

                        for (int i = 0; i < filas; i++)
                        {
                            Object[] botones1 = new Object[columnas];
                            Object[] botones2 = new Object[columnas];
                            Object[] botones3 = new Object[columnas];
                            for (int j = 0; j < columnas; j++) 
                            {
                                JLabel casillas = new JLabel();
                                casillas.setSize(51, 52);
                                JLabel casillas2 = new JLabel();
                                casillas2.setSize(51, 52);
                                casillas2.setText("0");
                                casillas2.setVisible(false);
                                casillas2.setFont(new Font("Comic Sans MS", 1, 16));
                                JLabel casillas3 = new JLabel(new ImageIcon(getClass().getResource("/imagenes/marca.png")));
                                casillas3.setSize(51, 52);
                                casillas3.setVisible(false);

                                if (x == j & y == i) 
                                {
                                    casillas.setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/jugador.jpg")));
                                    casillas.setName("jugador");
                                    casillas2.setName("jugador");
                                } 
                                else 
                                {
                                    String[] claves = mostrar.getProperty("fila" + i).split("-");
                                    casillas.setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/" + claves[j] + ".jpg")));
                                    casillas.setName(claves[j]);
                                    casillas2.setName(claves[j]);
                                    if (claves[j].equals("2"))
                                    {
                                        this.jugador.setLlavesRojas(this.jugador.getLlavesRojas() + 1);
                                    } 
                                    else
                                    {
                                        if (claves[j].equals("3"))
                                        {
                                            this.jugador.setLlavesAzules(this.jugador.getLlavesAzules() + 1);
                                        } 
                                        else 
                                        {
                                            if (claves[j].equals("4"))
                                            {
                                                this.jugador.setLlavesAmarillas(this.jugador.getLlavesAmarillas() + 1);
                                            }
                                        }
                                    }
                                }
                                casillas.setHorizontalAlignment(SwingConstants.CENTER);
                                Object[] datos = {casillas, 0};
                                botones1[j] = datos;
                                this.escenario.setLayout(new GridLayout(filas, columnas));
                                this.escenario.add(casillas);
                                this.escenario.updateUI();

                                casillas2.setHorizontalAlignment(SwingConstants.CENTER);
                                Object[] datos2 = {casillas2, 0};
                                botones2[j] = datos2;
                                this.escenarioDatos.setLayout(new GridLayout(filas, columnas));
                                this.escenarioDatos.add(casillas2);
                                this.escenarioDatos.updateUI();

                                casillas3.setHorizontalAlignment(SwingConstants.CENTER);
                                Object[] datos3 = {casillas3, 0};
                                botones3[j] = datos3;
                                this.paneles[8].setLayout(new GridLayout(filas, columnas));
                                this.paneles[8].add(casillas3);
                                this.paneles[8].updateUI();
                            }
                            this.matriz.insertar(botones1);
                            this.matriz2.insertar(botones2);
                            this.matriz3.insertar(botones3);
                        }

                        for (JButton botone : this.botones)
                        {
                            botone.setEnabled(true);
                        }
                    }
                    else
                    {
                        
                        JLabel imgAdvertencia=new JLabel(new ImageIcon(getClass().getResource("/imagenes/dañado.gif")));
                        imgAdvertencia.setSize(200, 200);
                        imgAdvertencia.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (imgAdvertencia.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (imgAdvertencia.getSize().getHeight() / 2)));
                        JLabel label=new JLabel("Archivo dañado");
                        label.setHorizontalAlignment(JLabel.CENTER);
                        label.setFont(new Font("Rockwell Extra Bold",1,16));
                        label.setSize(200, 30);
                        label.setLocation((int)imgAdvertencia.getLocation().getX(), (int)(imgAdvertencia.getLocation().getY()+210));
                        this.paneles[6].add(imgAdvertencia);
                        this.paneles[6].add(label);
                        this.paneles[6].updateUI();
                        for (JButton botone : this.botones) 
                        {
                            botone.setEnabled(false);
                        }
                        
                    }
                }
                else
                {
                   
                    
                    JLabel imgAdvertencia = new JLabel(new ImageIcon(getClass().getResource("/imagenes/dañado.gif")));
                    imgAdvertencia.setSize(200, 200);
                    imgAdvertencia.setLocation((int) ((this.paneles[6].getSize().getWidth() / 2) - (imgAdvertencia.getSize().getWidth() / 2)), (int) ((this.paneles[6].getSize().getHeight() / 2) - (imgAdvertencia.getSize().getHeight() / 2)));
                    JLabel label = new JLabel("Archivo dañado");
                    label.setHorizontalAlignment(JLabel.CENTER);
                    label.setSize(200, 30);
                    label.setFont(new Font("Rockwell Extra Bold",1,16));
                    label.setLocation((int) imgAdvertencia.getLocation().getX(), (int) (imgAdvertencia.getLocation().getY() + 210));
                    this.paneles[6].add(imgAdvertencia);
                    this.paneles[6].add(label);
                    this.paneles[6].updateUI();
                    for (JButton botone : this.botones) 
                    {
                        botone.setEnabled(false);
                    }
                }
            }
            catch(IOException i)
            {
                
                JLabel advertencia=new JLabel("Archivo no valido");
                advertencia.setFont(new Font("Rockwell Extra Bold",1,16));
                advertencia.setSize(200,200);
                advertencia.setLocation((int) ((this.paneles[6].getSize().getWidth() / 2) - (advertencia.getSize().getWidth() / 2)), (int) ((this.paneles[6].getSize().getHeight() / 2) - (advertencia.getSize().getHeight() / 2)));
                this.paneles[6].add(advertencia);
                this.paneles[6].updateUI();
            for (JButton botone : this.botones) 
            {
                botone.setEnabled(false);
            }
            }
        
        }
    
        
    }
    

