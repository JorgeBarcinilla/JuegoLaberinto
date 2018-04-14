/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniandes;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import static javax.swing.BorderFactory.createLineBorder;
import static javax.swing.BorderFactory.createTitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Jorge
 */
public class Interfaz extends JFrame{

    public Interfaz() {
        
        setSize(1059, 709);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
                
        Container c = getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(255,255,255));
        
                
        Matriz matriz=new Matriz();
        Matriz matriz2=new Matriz();
        Matriz matriz3=new Matriz();
        Jugador jugador=new Jugador();
        
        JPanel banner=new JPanel();
        banner.setBounds(0, 0, 1053, 160);
        banner.setBackground(Color.BLACK);
        
        JLabel imgBanner=new JLabel(new ImageIcon(getClass().getResource("/Imagenes/banner.png")));
        imgBanner.setBounds(0, 0, 1053, 160);
        imgBanner.setOpaque(false);
        imgBanner.setHorizontalAlignment(SwingConstants.CENTER);
        
        banner.add(imgBanner);
        
        //_______________________________________OPCIONES_________________________________________________________________________  

        JPanel opciones=new JPanel();
        opciones.setBounds(0, 160, 180, 520);
        opciones.setLayout(new GridLayout(7, 2,0,20));
        opciones.setBorder(createTitledBorder(createLineBorder(new Color(0, 0, 0)), "OPCIONES", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION,new Font("Tahoma", 1, 10)));
        
        JButton cargar=new JButton("Cargar");
        cargar.setBounds(10, 40, 160, 50);
        cargar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null ,new Color(0, 0, 0), new Color(0, 0, 0), null));
        cargar.setBackground(Color.white);
        cargar.setFont(new Font ("Comic Sans MS", 1, 15));
        cargar.setCursor(new Cursor(Cursor.HAND_CURSOR));
                
        JButton reiniciar=new JButton("Reiniciar");
        reiniciar.setBounds(10, 105, 160, 50);
        reiniciar.setEnabled(false);
        reiniciar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null ,new Color(0, 0, 0), new Color(0, 0, 0), null));
        reiniciar.setBackground(Color.white);
        reiniciar.setFont(new Font ("Comic Sans MS", 1, 15));
        reiniciar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton fMasVisitada=new JButton("Fila mas visitada");
        fMasVisitada.setBounds(10, 170, 160, 50);
        fMasVisitada.setEnabled(false);
        fMasVisitada.setName("fila");
        fMasVisitada.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null ,new Color(0, 0, 0), new Color(0, 0, 0), null));
        fMasVisitada.setBackground(Color.white);
        fMasVisitada.setFont(new Font ("Comic Sans MS", 1, 15));
        fMasVisitada.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton cMasVisitada=new JButton("Columna mas visitada");
        cMasVisitada.setBounds(10, 235, 160, 50);
        cMasVisitada.setEnabled(false);
        cMasVisitada.setName("columna");
        cMasVisitada.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null ,new Color(0, 0, 0), new Color(0, 0, 0), null));
        cMasVisitada.setBackground(Color.white);
        cMasVisitada.setFont(new Font ("Comic Sans MS", 1, 15));
        cMasVisitada.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton visitasVecinos=new JButton("Visitas vecinos");
        visitasVecinos.setBounds(10, 300, 160, 50);
        visitasVecinos.setEnabled(false);
        visitasVecinos.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null ,new Color(0, 0, 0), new Color(0, 0, 0), null));
        visitasVecinos.setBackground(Color.white);
        visitasVecinos.setFont(new Font ("Comic Sans MS", 1, 15));
        visitasVecinos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton opcion1=new JButton("Opcion 1");
        opcion1.setBounds(10, 365, 160, 50);
        opcion1.setEnabled(false);
        opcion1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null ,new Color(0, 0, 0), new Color(0, 0, 0), null));
        opcion1.setBackground(Color.white);
        opcion1.setFont(new Font ("Comic Sans MS", 1, 15));
        opcion1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton opcion2=new JButton("Opcion 2");
        opcion2.setBounds(10, 430, 160, 50);
        opcion2.setEnabled(false);
        opcion2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null ,new Color(0, 0, 0), new Color(0, 0, 0), null));
        opcion2.setBackground(Color.white);
        opcion2.setFont(new Font ("Comic Sans MS", 1, 15));
        opcion2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
        opciones.add(cargar);
        opciones.add(reiniciar);
        opciones.add(fMasVisitada);
        opciones.add(cMasVisitada);
        opciones.add(visitasVecinos);
        opciones.add(opcion1);
        opciones.add(opcion2);
        
        //_______________________________________ESCENARIO_________________________________________________________________________
        
        JPanel escenario=new JPanel();
        escenario.setBounds(180, 160, 663, 520);
        escenario.setLayout(null);
        escenario.setBackground(new Color(255,255,255,255));
        
        JPanel escenarioNot=new JPanel();
        escenarioNot.setBounds(180, 160, 663, 520);
        escenarioNot.setLayout(null);
        escenarioNot.setOpaque(false);
        
        JPanel estado=new JPanel();
        estado.setSize(450, 250);
        estado.setLayout(null);
        estado.setOpaque(false);
        
        JPanel escenarioDatos=new JPanel();
        escenarioDatos.setBounds(180, 160, 663, 520);
        escenarioDatos.setOpaque(false);
        escenarioDatos.setVisible(false);
        
        JPanel escenarioVista=new JPanel();
        escenarioVista.setBounds(180, 160, 663, 520);
        escenarioVista.setOpaque(false);
        escenarioVista.setVisible(false);
        
        
        //_______________________________________CONTROLES_________________________________________________________________________
        
        JPanel controles=new JPanel();
        controles.setBounds(843, 160, 210, 234);
        controles.setLayout(null);
        controles.setBorder(createTitledBorder(createLineBorder(new Color(0, 0, 0)), "CONTROLES", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION,new Font("Tahoma", 1, 10)));
        
        JPanel contenedor=new JPanel();
        contenedor.setSize(210, 210);
        contenedor.setLayout(null);
        contenedor.setOpaque(false);
        contenedor.setLocation(0,(int) ((controles.getSize().getHeight()/2)-(contenedor.getSize().getHeight()/2)));
        
        JButton arriba=new JButton();
        arriba.setBounds(70, 7, 70, 70);
        arriba.setIcon(new ImageIcon(getClass().getResource("/Imagenes/normal/arriba.png")));
        arriba.setPressedIcon(new ImageIcon(getClass().getResource("/Imagenes/precionado/arriba.png")));
        arriba.setBorder(null);
        arriba.setContentAreaFilled(false);
        arriba.setName("arriba");
        arriba.setEnabled(false);
        arriba.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton abajo=new JButton();
        abajo.setBounds(70, 133, 70, 70);
        abajo.setIcon(new ImageIcon(getClass().getResource("/Imagenes/normal/abajo.png")));
        abajo.setPressedIcon(new ImageIcon(getClass().getResource("/Imagenes/precionado/abajo.png")));
        abajo.setBorder(null);
        abajo.setContentAreaFilled(false);
        abajo.setName("abajo");
        abajo.setEnabled(false);
        abajo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton derecha=new JButton();
        derecha.setBounds(133, 70, 70, 70);
        derecha.setIcon(new ImageIcon(getClass().getResource("/Imagenes/normal/derecha.png")));
        derecha.setPressedIcon(new ImageIcon(getClass().getResource("/Imagenes/precionado/derecha.png")));
        derecha.setBorder(null);
        derecha.setContentAreaFilled(false);
        derecha.setName("derecha");
        derecha.setEnabled(false);
        derecha.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton izquierda=new JButton();
        izquierda.setBounds(7, 70, 70, 70);
        izquierda.setIcon(new ImageIcon(getClass().getResource("/Imagenes/normal/izquierda.png")));
        izquierda.setPressedIcon(new ImageIcon(getClass().getResource("/Imagenes/precionado/izquierda.png")));
        izquierda.setBorder(null);
        izquierda.setContentAreaFilled(false);
        izquierda.setName("izquierda");
        izquierda.setEnabled(false);
        izquierda.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        contenedor.add(arriba);
        contenedor.add(abajo);
        contenedor.add(derecha);
        contenedor.add(izquierda);
        
        controles.add(contenedor);
        //_______________________________________INFORMACION_________________________________________________________________________
        
        JPanel informacion=new JPanel();
        informacion.setBounds(843, 394, 210, 286);
        informacion.setLayout(new GridLayout(5, 0));
        informacion.setBorder(createTitledBorder(createLineBorder(new Color(0, 0, 0)), "INFORMACION", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION,new Font("Tahoma", 1, 10)));
        
        JLabel img1=new JLabel(new ImageIcon(getClass().getResource("/Imagenes/informacion/llave especial.png")));
        img1.setBounds(80, 20, 30, 30);
        JLabel cantEspecial=new JLabel();
        cantEspecial.setBounds(120, 25, 20, 20);
        
        JLabel img2=new JLabel(new ImageIcon(getClass().getResource("/Imagenes/informacion/casilla.png")));
        img2.setBounds(80, 70, 30, 30);
        JLabel cantCasilla=new JLabel();
        cantCasilla.setBounds(120, 75, 20, 20);
        
        JLabel img3=new JLabel(new ImageIcon(getClass().getResource("/Imagenes/informacion/llave roja.png")));
        img3.setBounds(80, 120, 30, 30);
        JLabel cantRoja=new JLabel();
        cantRoja.setBounds(120, 125, 20, 20);
        
        JLabel img4=new JLabel(new ImageIcon(getClass().getResource("/Imagenes/informacion/llave amarilla.png")));
        img4.setBounds(80, 170, 30, 30);
        JLabel cantAmarilla=new JLabel();
        cantAmarilla.setBounds(120, 175, 20, 20);
        
        JLabel img5=new JLabel(new ImageIcon(getClass().getResource("/Imagenes/informacion/llave azul.png")));
        img5.setBounds(80, 220, 30, 30);
        JLabel cantAzul=new JLabel();
        cantAzul.setBounds(120, 225, 20, 20);
        
        informacion.add(img1);
        informacion.add(cantEspecial);
        informacion.add(img2);
        informacion.add(cantCasilla);
        informacion.add(img3);
        informacion.add(cantRoja);
        informacion.add(img4);
        informacion.add(cantAmarilla);
        informacion.add(img5);
        informacion.add(cantAzul);
        
        //_________________________________________________________________________________________________________________________
        
        
        add(banner);
        add(opciones);
        add(escenarioNot);
        add(escenarioVista);
        add(escenarioDatos);
        add(escenario);
        add(controles);
        add(informacion);
        
        
        //_______________________________________ACCIONES_________________________________________________________________________
        
        JButton[] botones={abajo,arriba,derecha,izquierda,cMasVisitada,fMasVisitada,opcion1,opcion2,reiniciar,visitasVecinos};
        JPanel[] paneles={banner,controles,escenario,escenarioDatos,informacion,opciones,escenarioNot,contenedor,escenarioVista,estado};
        JLabel interuptor=new JLabel("no");
        
        Cargar load=new Cargar(this,escenario,escenarioDatos,botones,matriz,matriz2,matriz3,jugador,cantEspecial,cantCasilla,cantAmarilla,cantAzul,cantRoja,paneles,interuptor);
        Mover arr=new Mover(arriba,matriz,matriz2,jugador,cantAmarilla,cantAzul,cantRoja,cantEspecial,cantCasilla,botones,escenarioNot,estado,interuptor,escenario,load);
        Mover abj=new Mover(abajo,matriz,matriz2,jugador,cantAmarilla,cantAzul,cantRoja,cantEspecial,cantCasilla,botones,escenarioNot,estado,interuptor,escenario,load);
        Mover izq=new Mover(izquierda,matriz,matriz2,jugador,cantAmarilla,cantAzul,cantRoja,cantEspecial,cantCasilla,botones,escenarioNot,estado,interuptor,escenario,load);
        Mover der=new Mover(derecha,matriz,matriz2,jugador,cantAmarilla,cantAzul,cantRoja,cantEspecial,cantCasilla,botones,escenarioNot,estado,interuptor,escenario,load);
        
        
        cargar.addActionListener(load);
        izquierda.addActionListener(izq);
        derecha.addActionListener(der);
        abajo.addActionListener(abj);
        arriba.addActionListener(arr);
        visitasVecinos.addMouseListener(new MostrarVisitas(escenarioDatos,matriz2,jugador));
        fMasVisitada.addMouseListener(new VerVisita(fMasVisitada,matriz,matriz3,escenarioVista,interuptor));
        cMasVisitada.addMouseListener(new VerVisita(cMasVisitada,matriz,matriz3,escenarioVista,interuptor));
        reiniciar.addActionListener(new Reiniciar(load));
        cargar.addKeyListener(new Mover2(arr,abj,izq,der,arriba,abajo,izquierda,derecha,interuptor));
        
        for (JButton botone : botones) {
            botone.addKeyListener(new Mover2(arr,abj,izq,der,arriba,abajo,izquierda,derecha,interuptor));
        }
    }
    
}
