/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniandes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

/**
 *
 * @author Jorge
 */
public class Mover implements ActionListener {

    JButton movimiento;
    Matriz matriz;
    Matriz matriz2;
    Jugador jugador;
    JLabel cantAmarilla;
    JLabel cantAzul;
    JLabel cantRoja;
    JLabel cantEspecial;
    JLabel cantCasilla;
    JButton[] botones1;
    JPanel escenario;
    JPanel estado;
    JLabel interuptor;
    JPanel escenario2;
    Cargar load;

    public Mover(JButton movimiento, Matriz matriz, Matriz matriz2, Jugador jugador, JLabel cantAmarilla, JLabel cantAzul, JLabel cantRoja, JLabel cantEspecial, JLabel cantCasilla, JButton[] botones1, JPanel escenario, JPanel estado, JLabel interuptor, JPanel escenario2, Cargar load) {
        this.movimiento = movimiento;
        this.matriz = matriz;
        this.matriz2 = matriz2;
        this.jugador = jugador;
        this.cantAmarilla = cantAmarilla;
        this.cantAzul = cantAzul;
        this.cantRoja = cantRoja;
        this.cantEspecial = cantEspecial;
        this.cantCasilla = cantCasilla;
        this.botones1 = botones1;
        this.escenario = escenario;
        this.estado = estado;
        this.interuptor = interuptor;
        this.escenario2 = escenario2;
        this.load = load;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        boolean est = true;
        this.escenario.removeAll();
        this.escenario.updateUI();

        for (int i = 0; i < this.matriz.tamañoFilas(); i++) {
            for (int j = 0; j < this.matriz.tamañoColumnas(); j++) {
                if (this.matriz.ObtenerDato(i, j).getName().equals("jugador")) {
                    if (this.movimiento.getName().equals("arriba")) {
                        if ((i - 1) >= 0) {
                            this.movimiento((i - 1), i, j, "vertical");
                        }
                    }

                    if (this.movimiento.getName().equals("abajo")) {
                        if ((i + 1) >= 0 & (i + 1) < this.matriz.tamañoFilas()) {
                            this.movimiento((i + 1), i, j, "vertical");
                        }
                    }

                    if (this.movimiento.getName().equals("derecha")) {
                        if ((j + 1) >= 0 & (j + 1) < this.matriz.tamañoColumnas()) {
                            this.movimiento((j + 1), i, j, "horizontal");
                        }

                    }
                    if (this.movimiento.getName().equals("izquierda")) {
                        if ((j - 1) >= 0) {
                            this.movimiento((j - 1), i, j, "horizontal");
                        }
                    }

                    est = false;
                    if (!est) {
                        break;
                    }
                }

            }
            if (!est) {
                break;
            }
        }

        this.cantAmarilla.setText("" + this.jugador.getCantLlavesAmarillas());
        this.cantAzul.setText("" + this.jugador.getCantLlavesAzules());
        this.cantRoja.setText("" + this.jugador.getCantLlavesRojas());
        this.cantEspecial.setText("" + (this.jugador.getLlavesEspeciales() - this.jugador.getCantLlavesEspeciales()));

    }

    private void movimiento(int var, int i, int j, String tipo) {
        if (tipo.equals("vertical")) {
            if (this.matriz.ObtenerDato(var, j).getName().equals("0") & this.matriz.ObtenerVisita(var, j) < this.jugador.getVisitasCasillas()) {
                this.matriz.ObtenerDato(var, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/jugador.jpg")));
                this.matriz.ObtenerDato(var, j).setName("jugador");
                this.matriz.ActualizarVisita(var, j, (this.matriz.ObtenerVisita(var, j) + 1));
                this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/0.jpg")));
                this.matriz.ObtenerDato(i, j).setName("0");
                this.matriz2.ObtenerDato(var, j).setName("jugador");
                this.matriz2.ObtenerDato(i, j).setName("0");
                this.matriz2.ObtenerDato(var, j).setText("" + this.matriz.ObtenerVisita(var, j));
                if (this.matriz.ObtenerVisita(i, j) >= this.jugador.getVisitasCasillas()) {
                    this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/1.jpg")));
                    this.matriz.ObtenerDato(i, j).setName("1");
                    this.matriz2.ObtenerDato(i, j).setName("1");

                    int contador = 0;

                    Object[] w = {var - 1, j, ""};
                    int wNum = (int) w[0];
                    if (wNum >= 0) {
                        w[2] = "si";
                        contador++;
                    }
                    Object[] x = {var + 1, j, ""};
                    int xNum = (int) x[0];
                    if (xNum < this.matriz.tamañoFilas()) {
                        x[2] = "si";
                        contador++;
                    }
                    Object[] y = {var, j - 1, ""};
                    int yNum = (int) y[1];
                    if (yNum >= 0) {
                        y[2] = "si";
                        contador++;
                    }
                    Object[] z = {var, j + 1, ""};
                    int zNum = (int) z[1];
                    if (zNum < this.matriz.tamañoColumnas()) {
                        z[2] = "si";
                        contador++;
                    }

                    Object[] datos = {w, x, y, z};
                    Object[] datos2 = new Object[contador];
                    int aviso = 0;
                    for (Object dato : datos) {
                        Object[] valor = (Object[]) dato;
                        if (valor[2].toString().equals("si")) {
                            datos2[aviso] = valor;
                            aviso++;
                        }
                    }

                    int contador2 = 0;

                    for (Object valor : datos2) {
                        Object[] valor2 = (Object[]) valor;
                        int a = (int) valor2[0];
                        int b = (int) valor2[1];
                        if (this.matriz.ObtenerDato(a, b).getName().equals("1")) {
                            contador2++;
                        }
                    }

                    if (contador == contador2) {
                        this.estado.removeAll();
                        this.escenario.removeAll();
                        JLabel imgEstado = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/sin movimientos.png")));
                        imgEstado.setBounds(10, 5, 426, 186);

                        JButton op2 = new JButton("volver a jugar");
                        op2.setName("reiniciar");
                        op2.setBounds(150, 200, 150, 30);
                        op2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, new Color(0, 0, 0), new Color(0, 0, 0), null));
                        op2.setBackground(Color.white);
                        op2.setFont(new Font("Comic Sans MS", 1, 15));
                        op2.addActionListener(new opcGanaste(op2, this.load, this.estado, this.escenario2));

                        this.estado.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (this.estado.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (this.estado.getSize().getHeight() / 2)));
                        this.estado.add(imgEstado);
                        this.estado.add(op2);
                        this.estado.updateUI();
                        this.escenario.add(estado);
                        this.estado.updateUI();
                        this.interuptor.setText("no");
                        for (JButton botones11 : this.botones1) {
                            botones11.setEnabled(false);
                        }
                    }

                }

            } else {
                if (this.matriz.ObtenerDato(var, j).getName().equals("5") & this.matriz.ObtenerVisita(var, j) < this.jugador.getVisitasCasillas()) {
                    if (this.jugador.getCantLlavesRojas() > 0) {
                        this.matriz.ObtenerDato(var, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/jugador.jpg")));
                        this.matriz.ObtenerDato(var, j).setName("jugador");
                        this.matriz.ActualizarVisita(var, j, (this.matriz.ObtenerVisita(var, j) + 1));
                        this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/0.jpg")));
                        this.matriz.ObtenerDato(i, j).setName("0");
                        this.jugador.setCantLlavesRojas(this.jugador.getCantLlavesRojas() - 1);
                        this.matriz2.ObtenerDato(var, j).setName("jugador");
                        this.matriz2.ObtenerDato(i, j).setName("0");
                        this.matriz2.ObtenerDato(var, j).setText("" + this.matriz.ObtenerVisita(var, j));
                        if (this.matriz.ObtenerVisita(i, j) >= this.jugador.getVisitasCasillas()) {
                            this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/1.jpg")));
                            this.matriz.ObtenerDato(i, j).setName("1");
                            this.matriz2.ObtenerDato(i, j).setName("1");

                            int contador = 0;

                            Object[] w = {var - 1, j, ""};
                            int wNum = (int) w[0];
                            if (wNum >= 0) {
                                w[2] = "si";
                                contador++;
                            }
                            Object[] x = {var + 1, j, ""};
                            int xNum = (int) x[0];
                            if (xNum < this.matriz.tamañoFilas()) {
                                x[2] = "si";
                                contador++;
                            }
                            Object[] y = {var, j - 1, ""};
                            int yNum = (int) y[1];
                            if (yNum >= 0) {
                                y[2] = "si";
                                contador++;
                            }
                            Object[] z = {var, j + 1, ""};
                            int zNum = (int) z[1];
                            if (zNum < this.matriz.tamañoColumnas()) {
                                z[2] = "si";
                                contador++;
                            }

                            Object[] datos = {w, x, y, z};
                            Object[] datos2 = new Object[contador];
                            int aviso = 0;
                            for (Object dato : datos) {
                                Object[] valor = (Object[]) dato;
                                if (valor[2].toString().equals("si")) {
                                    datos2[aviso] = valor;
                                    aviso++;
                                }
                            }

                            int contador2 = 0;

                            for (Object valor : datos2) {
                                Object[] valor2 = (Object[]) valor;
                                int a = (int) valor2[0];
                                int b = (int) valor2[1];
                                if (this.matriz.ObtenerDato(a, b).getName().equals("1")) {
                                    contador2++;
                                }
                            }

                            if (contador == contador2) {
                                this.estado.removeAll();
                                this.escenario.removeAll();
                                JLabel imgEstado = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/sin movimientos.png")));
                                imgEstado.setBounds(10, 5, 426, 186);

                                JButton op2 = new JButton("volver a jugar");
                                op2.setName("reiniciar");
                                op2.setBounds(150, 200, 150, 30);
                                op2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, new Color(0, 0, 0), new Color(0, 0, 0), null));
                                op2.setBackground(Color.white);
                                op2.setFont(new Font("Comic Sans MS", 1, 15));
                                op2.addActionListener(new opcGanaste(op2, this.load, this.estado, this.escenario2));

                                this.estado.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (this.estado.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (this.estado.getSize().getHeight() / 2)));
                                this.estado.add(imgEstado);
                                this.estado.add(op2);
                                this.estado.updateUI();
                                this.escenario.add(estado);
                                this.estado.updateUI();
                                this.interuptor.setText("no");
                                for (JButton botones11 : this.botones1) {
                                    botones11.setEnabled(false);
                                }
                            }

                        }
                    } else {
                        this.escenario.removeAll();
                        JLabel notificacion = new JLabel();
                        notificacion.setSize(200, 150);
                        notificacion.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (notificacion.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (notificacion.getSize().getHeight() / 2)));
                        notificacion.setIcon(new ImageIcon(getClass().getResource("/imagenes/no pasar roja.png")));
                        this.escenario.add(notificacion);
                        this.escenario.updateUI();

                    }

                } else {
                    if (this.matriz.ObtenerDato(var, j).getName().equals("6") & this.matriz.ObtenerVisita(var, j) < this.jugador.getVisitasCasillas()) {
                        if (this.jugador.getCantLlavesAzules() > 0) {
                            this.matriz.ObtenerDato(var, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/jugador.jpg")));
                            this.matriz.ObtenerDato(var, j).setName("jugador");
                            this.matriz.ActualizarVisita(var, j, (this.matriz.ObtenerVisita(var, j) + 1));
                            this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/0.jpg")));
                            this.matriz.ObtenerDato(i, j).setName("0");
                            this.jugador.setCantLlavesAzules(this.jugador.getCantLlavesAzules() - 1);
                            this.matriz2.ObtenerDato(var, j).setName("jugador");
                            this.matriz2.ObtenerDato(i, j).setName("0");
                            this.matriz2.ObtenerDato(var, j).setText("" + this.matriz.ObtenerVisita(var, j));
                            if (this.matriz.ObtenerVisita(i, j) >= this.jugador.getVisitasCasillas()) {
                                this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/1.jpg")));
                                this.matriz.ObtenerDato(i, j).setName("1");
                                this.matriz2.ObtenerDato(i, j).setName("1");

                                int contador = 0;

                                Object[] w = {var - 1, j, ""};
                                int wNum = (int) w[0];
                                if (wNum >= 0) {
                                    w[2] = "si";
                                    contador++;
                                }
                                Object[] x = {var + 1, j, ""};
                                int xNum = (int) x[0];
                                if (xNum < this.matriz.tamañoFilas()) {
                                    x[2] = "si";
                                    contador++;
                                }
                                Object[] y = {var, j - 1, ""};
                                int yNum = (int) y[1];
                                if (yNum >= 0) {
                                    y[2] = "si";
                                    contador++;
                                }
                                Object[] z = {var, j + 1, ""};
                                int zNum = (int) z[1];
                                if (zNum < this.matriz.tamañoColumnas()) {
                                    z[2] = "si";
                                    contador++;
                                }

                                Object[] datos = {w, x, y, z};
                                Object[] datos2 = new Object[contador];
                                int aviso = 0;
                                for (Object dato : datos) {
                                    Object[] valor = (Object[]) dato;
                                    if (valor[2].toString().equals("si")) {
                                        datos2[aviso] = valor;
                                        aviso++;
                                    }
                                }

                                int contador2 = 0;

                                for (Object valor : datos2) {
                                    Object[] valor2 = (Object[]) valor;
                                    int a = (int) valor2[0];
                                    int b = (int) valor2[1];
                                    if (this.matriz.ObtenerDato(a, b).getName().equals("1")) {
                                        contador2++;
                                    }
                                }

                                if (contador == contador2) {
                                    this.estado.removeAll();
                                    this.escenario.removeAll();
                                    JLabel imgEstado = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/sin movimientos.png")));
                                    imgEstado.setBounds(10, 5, 426, 186);

                                    JButton op2 = new JButton("volver a jugar");
                                    op2.setName("reiniciar");
                                    op2.setBounds(150, 200, 150, 30);
                                    op2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, new Color(0, 0, 0), new Color(0, 0, 0), null));
                                    op2.setBackground(Color.white);
                                    op2.setFont(new Font("Comic Sans MS", 1, 15));
                                    op2.addActionListener(new opcGanaste(op2, this.load, this.estado, this.escenario2));

                                    this.estado.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (this.estado.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (this.estado.getSize().getHeight() / 2)));
                                    this.estado.add(imgEstado);
                                    this.estado.add(op2);
                                    this.estado.updateUI();
                                    this.escenario.add(estado);
                                    this.estado.updateUI();
                                    this.interuptor.setText("no");
                                    for (JButton botones11 : this.botones1) {
                                        botones11.setEnabled(false);
                                    }
                                }

                            }
                        } else {
                            this.escenario.removeAll();
                            JLabel notificacion = new JLabel();
                            notificacion.setSize(200, 150);
                            notificacion.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (notificacion.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (notificacion.getSize().getHeight() / 2)));
                            notificacion.setIcon(new ImageIcon(getClass().getResource("/imagenes/no pasar azul.png")));
                            this.escenario.add(notificacion);
                            this.escenario.updateUI();

                        }

                    } else {
                        if (this.matriz.ObtenerDato(var, j).getName().equals("7") & this.matriz.ObtenerVisita(var, j) < this.jugador.getVisitasCasillas()) {
                            if (this.jugador.getCantLlavesAmarillas() > 0) {
                                this.matriz.ObtenerDato(var, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/jugador.jpg")));
                                this.matriz.ObtenerDato(var, j).setName("jugador");
                                this.matriz.ActualizarVisita(var, j, (this.matriz.ObtenerVisita(var, j) + 1));
                                this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/0.jpg")));
                                this.matriz.ObtenerDato(i, j).setName("0");
                                this.jugador.setCantLlavesAmarillas(this.jugador.getCantLlavesAmarillas() - 1);
                                this.matriz2.ObtenerDato(var, j).setName("jugador");
                                this.matriz2.ObtenerDato(i, j).setName("0");
                                this.matriz2.ObtenerDato(var, j).setText("" + this.matriz.ObtenerVisita(var, j));
                                if (this.matriz.ObtenerVisita(i, j) >= this.jugador.getVisitasCasillas()) {
                                    this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/1.jpg")));
                                    this.matriz.ObtenerDato(i, j).setName("1");
                                    this.matriz2.ObtenerDato(i, j).setName("1");

                                    int contador = 0;

                                    Object[] w = {var - 1, j, ""};
                                    int wNum = (int) w[0];
                                    if (wNum >= 0) {
                                        w[2] = "si";
                                        contador++;
                                    }
                                    Object[] x = {var + 1, j, ""};
                                    int xNum = (int) x[0];
                                    if (xNum < this.matriz.tamañoFilas()) {
                                        x[2] = "si";
                                        contador++;
                                    }
                                    Object[] y = {var, j - 1, ""};
                                    int yNum = (int) y[1];
                                    if (yNum >= 0) {
                                        y[2] = "si";
                                        contador++;
                                    }
                                    Object[] z = {var, j + 1, ""};
                                    int zNum = (int) z[1];
                                    if (zNum < this.matriz.tamañoColumnas()) {
                                        z[2] = "si";
                                        contador++;
                                    }

                                    Object[] datos = {w, x, y, z};
                                    Object[] datos2 = new Object[contador];
                                    int aviso = 0;
                                    for (Object dato : datos) {
                                        Object[] valor = (Object[]) dato;
                                        if (valor[2].toString().equals("si")) {
                                            datos2[aviso] = valor;
                                            aviso++;
                                        }
                                    }

                                    int contador2 = 0;

                                    for (Object valor : datos2) {
                                        Object[] valor2 = (Object[]) valor;
                                        int a = (int) valor2[0];
                                        int b = (int) valor2[1];
                                        if (this.matriz.ObtenerDato(a, b).getName().equals("1")) {
                                            contador2++;
                                        }
                                    }

                                    if (contador == contador2) {
                                        this.estado.removeAll();
                                        this.escenario.removeAll();
                                        JLabel imgEstado = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/sin movimientos.png")));
                                        imgEstado.setBounds(10, 5, 426, 186);

                                        JButton op2 = new JButton("volver a jugar");
                                        op2.setName("reiniciar");
                                        op2.setBounds(150, 200, 150, 30);
                                        op2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, new Color(0, 0, 0), new Color(0, 0, 0), null));
                                        op2.setBackground(Color.white);
                                        op2.setFont(new Font("Comic Sans MS", 1, 15));
                                        op2.addActionListener(new opcGanaste(op2, this.load, this.estado, this.escenario2));

                                        this.estado.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (this.estado.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (this.estado.getSize().getHeight() / 2)));
                                        this.estado.add(imgEstado);
                                        this.estado.add(op2);
                                        this.estado.updateUI();
                                        this.escenario.add(estado);
                                        this.estado.updateUI();
                                        this.interuptor.setText("no");
                                        for (JButton botones11 : this.botones1) {
                                            botones11.setEnabled(false);
                                        }
                                    }

                                }
                            } else {
                                this.escenario.removeAll();
                                JLabel notificacion = new JLabel();
                                notificacion.setSize(200, 150);
                                notificacion.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (notificacion.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (notificacion.getSize().getHeight() / 2)));
                                notificacion.setIcon(new ImageIcon(getClass().getResource("/imagenes/no pasar amarillo.png")));
                                this.escenario.add(notificacion);
                                this.escenario.updateUI();

                            }

                        } else {
                            if (this.matriz.ObtenerDato(var, j).getName().equals("2") & this.matriz.ObtenerVisita(var, j) < this.jugador.getVisitasCasillas()) {
                                this.matriz.ObtenerDato(var, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/jugador.jpg")));
                                this.matriz.ObtenerDato(var, j).setName("jugador");
                                this.matriz.ActualizarVisita(var, j, (this.matriz.ObtenerVisita(var, j) + 1));
                                this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/0.jpg")));
                                this.matriz.ObtenerDato(i, j).setName("0");
                                this.jugador.setCantLlavesRojas(this.jugador.getCantLlavesRojas() + 1);
                                this.matriz2.ObtenerDato(var, j).setName("jugador");
                                this.matriz2.ObtenerDato(i, j).setName("0");
                                this.matriz2.ObtenerDato(var, j).setText("" + this.matriz.ObtenerVisita(var, j));
                                this.escenario.removeAll();
                                JLabel notificacion = new JLabel();
                                notificacion.setSize(200, 150);
                                notificacion.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (notificacion.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (notificacion.getSize().getHeight() / 2)));
                                notificacion.setIcon(new ImageIcon(getClass().getResource("/imagenes/llave roja +.png")));
                                this.escenario.add(notificacion);
                                this.escenario.updateUI();
                                MoverPanel mover = new MoverPanel(notificacion, this.escenario);
                                ActionEvent e = null;
                                mover.actionPerformed(e);

                                if (this.matriz.ObtenerVisita(i, j) >= this.jugador.getVisitasCasillas()) {
                                    this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/1.jpg")));
                                    this.matriz.ObtenerDato(i, j).setName("1");
                                    this.matriz2.ObtenerDato(i, j).setName("1");

                                    int contador = 0;

                                    Object[] w = {var - 1, j, ""};
                                    int wNum = (int) w[0];
                                    if (wNum >= 0) {
                                        w[2] = "si";
                                        contador++;
                                    }
                                    Object[] x = {var + 1, j, ""};
                                    int xNum = (int) x[0];
                                    if (xNum < this.matriz.tamañoFilas()) {
                                        x[2] = "si";
                                        contador++;
                                    }
                                    Object[] y = {var, j - 1, ""};
                                    int yNum = (int) y[1];
                                    if (yNum >= 0) {
                                        y[2] = "si";
                                        contador++;
                                    }
                                    Object[] z = {var, j + 1, ""};
                                    int zNum = (int) z[1];
                                    if (zNum < this.matriz.tamañoColumnas()) {
                                        z[2] = "si";
                                        contador++;
                                    }

                                    Object[] datos = {w, x, y, z};
                                    Object[] datos2 = new Object[contador];
                                    int aviso = 0;
                                    for (Object dato : datos) {
                                        Object[] valor = (Object[]) dato;
                                        if (valor[2].toString().equals("si")) {
                                            datos2[aviso] = valor;
                                            aviso++;
                                        }
                                    }

                                    int contador2 = 0;

                                    for (Object valor : datos2) {
                                        Object[] valor2 = (Object[]) valor;
                                        int a = (int) valor2[0];
                                        int b = (int) valor2[1];
                                        if (this.matriz.ObtenerDato(a, b).getName().equals("1")) {
                                            contador2++;
                                        }
                                    }

                                    if (contador == contador2) {
                                        this.estado.removeAll();
                                        this.escenario.removeAll();
                                        JLabel imgEstado = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/sin movimientos.png")));
                                        imgEstado.setBounds(10, 5, 426, 186);

                                        JButton op2 = new JButton("volver a jugar");
                                        op2.setName("reiniciar");
                                        op2.setBounds(150, 200, 150, 30);
                                        op2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, new Color(0, 0, 0), new Color(0, 0, 0), null));
                                        op2.setBackground(Color.white);
                                        op2.setFont(new Font("Comic Sans MS", 1, 15));
                                        op2.addActionListener(new opcGanaste(op2, this.load, this.estado, this.escenario2));

                                        this.estado.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (this.estado.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (this.estado.getSize().getHeight() / 2)));
                                        this.estado.add(imgEstado);
                                        this.estado.add(op2);
                                        this.estado.updateUI();
                                        this.escenario.add(estado);
                                        this.estado.updateUI();
                                        this.interuptor.setText("no");
                                        for (JButton botones11 : this.botones1) {
                                            botones11.setEnabled(false);
                                        }
                                    }

                                }

                            } else {
                                if (this.matriz.ObtenerDato(var, j).getName().equals("3") & this.matriz.ObtenerVisita(var, j) < this.jugador.getVisitasCasillas()) {
                                    this.matriz.ObtenerDato(var, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/jugador.jpg")));
                                    this.matriz.ObtenerDato(var, j).setName("jugador");
                                    this.matriz.ActualizarVisita(var, j, (this.matriz.ObtenerVisita(var, j) + 1));
                                    this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/0.jpg")));
                                    this.matriz.ObtenerDato(i, j).setName("0");
                                    this.jugador.setCantLlavesAzules(this.jugador.getCantLlavesAzules() + 1);
                                    this.matriz2.ObtenerDato(var, j).setName("jugador");
                                    this.matriz2.ObtenerDato(i, j).setName("0");
                                    this.matriz2.ObtenerDato(var, j).setText("" + this.matriz.ObtenerVisita(var, j));
                                    this.escenario.removeAll();
                                    JLabel notificacion = new JLabel();
                                    notificacion.setSize(200, 150);
                                    notificacion.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (notificacion.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (notificacion.getSize().getHeight() / 2)));
                                    notificacion.setIcon(new ImageIcon(getClass().getResource("/imagenes/llave azul +.png")));
                                    this.escenario.add(notificacion);
                                    this.escenario.updateUI();
                                    MoverPanel mover = new MoverPanel(notificacion, this.escenario);
                                    ActionEvent e = null;
                                    mover.actionPerformed(e);
                                    if (this.matriz.ObtenerVisita(i, j) >= this.jugador.getVisitasCasillas()) {
                                        this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/1.jpg")));
                                        this.matriz.ObtenerDato(i, j).setName("1");
                                        this.matriz2.ObtenerDato(i, j).setName("1");

                                        int contador = 0;

                                        Object[] w = {var - 1, j, ""};
                                        int wNum = (int) w[0];
                                        if (wNum >= 0) {
                                            w[2] = "si";
                                            contador++;
                                        }
                                        Object[] x = {var + 1, j, ""};
                                        int xNum = (int) x[0];
                                        if (xNum < this.matriz.tamañoFilas()) {
                                            x[2] = "si";
                                            contador++;
                                        }
                                        Object[] y = {var, j - 1, ""};
                                        int yNum = (int) y[1];
                                        if (yNum >= 0) {
                                            y[2] = "si";
                                            contador++;
                                        }
                                        Object[] z = {var, j + 1, ""};
                                        int zNum = (int) z[1];
                                        if (zNum < this.matriz.tamañoColumnas()) {
                                            z[2] = "si";
                                            contador++;
                                        }

                                        Object[] datos = {w, x, y, z};
                                        Object[] datos2 = new Object[contador];
                                        int aviso = 0;
                                        for (Object dato : datos) {
                                            Object[] valor = (Object[]) dato;
                                            if (valor[2].toString().equals("si")) {
                                                datos2[aviso] = valor;
                                                aviso++;
                                            }
                                        }

                                        int contador2 = 0;

                                        for (Object valor : datos2) {
                                            Object[] valor2 = (Object[]) valor;
                                            int a = (int) valor2[0];
                                            int b = (int) valor2[1];
                                            if (this.matriz.ObtenerDato(a, b).getName().equals("1")) {
                                                contador2++;
                                            }
                                        }

                                        if (contador == contador2) {
                                            this.estado.removeAll();
                                            this.escenario.removeAll();
                                            JLabel imgEstado = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/sin movimientos.png")));
                                            imgEstado.setBounds(10, 5, 426, 186);

                                            JButton op2 = new JButton("volver a jugar");
                                            op2.setName("reiniciar");
                                            op2.setBounds(150, 200, 150, 30);
                                            op2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, new Color(0, 0, 0), new Color(0, 0, 0), null));
                                            op2.setBackground(Color.white);
                                            op2.setFont(new Font("Comic Sans MS", 1, 15));
                                            op2.addActionListener(new opcGanaste(op2, this.load, this.estado, this.escenario2));

                                            this.estado.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (this.estado.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (this.estado.getSize().getHeight() / 2)));
                                            this.estado.add(imgEstado);
                                            this.estado.add(op2);
                                            this.estado.updateUI();
                                            this.escenario.add(estado);
                                            this.estado.updateUI();
                                            this.interuptor.setText("no");
                                            for (JButton botones11 : this.botones1) {
                                                botones11.setEnabled(false);
                                            }
                                        }

                                    }
                                } else {
                                    if (this.matriz.ObtenerDato(var, j).getName().equals("4") & this.matriz.ObtenerVisita(var, j) < this.jugador.getVisitasCasillas()) {
                                        this.matriz.ObtenerDato(var, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/jugador.jpg")));
                                        this.matriz.ObtenerDato(var, j).setName("jugador");
                                        this.matriz.ActualizarVisita(var, j, (this.matriz.ObtenerVisita(var, j) + 1));
                                        this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/0.jpg")));
                                        this.matriz.ObtenerDato(i, j).setName("0");
                                        this.jugador.setCantLlavesAmarillas(this.jugador.getCantLlavesAmarillas() + 1);
                                        this.matriz2.ObtenerDato(var, j).setName("jugador");
                                        this.matriz2.ObtenerDato(i, j).setName("0");
                                        this.matriz2.ObtenerDato(var, j).setText("" + this.matriz.ObtenerVisita(var, j));
                                        this.escenario.removeAll();
                                        JLabel notificacion = new JLabel();
                                        notificacion.setSize(200, 150);
                                        notificacion.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (notificacion.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (notificacion.getSize().getHeight() / 2)));
                                        notificacion.setIcon(new ImageIcon(getClass().getResource("/imagenes/llave amarilla +.png")));
                                        this.escenario.add(notificacion);
                                        this.escenario.updateUI();
                                        MoverPanel mover = new MoverPanel(notificacion, this.escenario);
                                        ActionEvent e = null;
                                        mover.actionPerformed(e);
                                        if (this.matriz.ObtenerVisita(i, j) >= this.jugador.getVisitasCasillas()) {
                                            this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/1.jpg")));
                                            this.matriz.ObtenerDato(i, j).setName("1");
                                            this.matriz2.ObtenerDato(i, j).setName("1");

                                            int contador = 0;

                                            Object[] w = {var - 1, j, ""};
                                            int wNum = (int) w[0];
                                            if (wNum >= 0) {
                                                w[2] = "si";
                                                contador++;
                                            }
                                            Object[] x = {var + 1, j, ""};
                                            int xNum = (int) x[0];
                                            if (xNum < this.matriz.tamañoFilas()) {
                                                x[2] = "si";
                                                contador++;
                                            }
                                            Object[] y = {var, j - 1, ""};
                                            int yNum = (int) y[1];
                                            if (yNum >= 0) {
                                                y[2] = "si";
                                                contador++;
                                            }
                                            Object[] z = {var, j + 1, ""};
                                            int zNum = (int) z[1];
                                            if (zNum < this.matriz.tamañoColumnas()) {
                                                z[2] = "si";
                                                contador++;
                                            }

                                            Object[] datos = {w, x, y, z};
                                            Object[] datos2 = new Object[contador];
                                            int aviso = 0;
                                            for (Object dato : datos) {
                                                Object[] valor = (Object[]) dato;
                                                if (valor[2].toString().equals("si")) {
                                                    datos2[aviso] = valor;
                                                    aviso++;
                                                }
                                            }

                                            int contador2 = 0;

                                            for (Object valor : datos2) {
                                                Object[] valor2 = (Object[]) valor;
                                                int a = (int) valor2[0];
                                                int b = (int) valor2[1];
                                                if (this.matriz.ObtenerDato(a, b).getName().equals("1")) {
                                                    contador2++;
                                                }
                                            }

                                            if (contador == contador2) {
                                                this.estado.removeAll();
                                                this.escenario.removeAll();
                                                JLabel imgEstado = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/sin movimientos.png")));
                                                imgEstado.setBounds(10, 5, 426, 186);

                                                JButton op2 = new JButton("volver a jugar");
                                                op2.setName("reiniciar");
                                                op2.setBounds(150, 200, 150, 30);
                                                op2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, new Color(0, 0, 0), new Color(0, 0, 0), null));
                                                op2.setBackground(Color.white);
                                                op2.setFont(new Font("Comic Sans MS", 1, 15));
                                                op2.addActionListener(new opcGanaste(op2, this.load, this.estado, this.escenario2));

                                                this.estado.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (this.estado.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (this.estado.getSize().getHeight() / 2)));
                                                this.estado.add(imgEstado);
                                                this.estado.add(op2);
                                                this.estado.updateUI();
                                                this.escenario.add(estado);
                                                this.estado.updateUI();
                                                this.interuptor.setText("no");

                                                for (JButton botones11 : this.botones1) {
                                                    botones11.setEnabled(false);
                                                }
                                            }

                                        }
                                    } else {
                                        if (this.matriz.ObtenerDato(var, j).getName().equals("9") & this.matriz.ObtenerVisita(var, j) < this.jugador.getVisitasCasillas()) {
                                            this.matriz.ObtenerDato(var, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/jugador.jpg")));
                                            this.matriz.ObtenerDato(var, j).setName("jugador");
                                            this.matriz.ActualizarVisita(var, j, (this.matriz.ObtenerVisita(var, j) + 1));
                                            this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/0.jpg")));
                                            this.matriz.ObtenerDato(i, j).setName("0");
                                            this.jugador.setCantLlavesEspeciales(this.jugador.getCantLlavesEspeciales() + 1);
                                            this.matriz2.ObtenerDato(var, j).setName("jugador");
                                            this.matriz2.ObtenerDato(i, j).setName("0");
                                            this.matriz2.ObtenerDato(var, j).setText("" + this.matriz.ObtenerVisita(var, j));
                                            this.escenario.removeAll();
                                            JLabel notificacion = new JLabel();
                                            notificacion.setSize(200, 150);
                                            notificacion.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (notificacion.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (notificacion.getSize().getHeight() / 2)));
                                            notificacion.setIcon(new ImageIcon(getClass().getResource("/imagenes/llave especial +.png")));
                                            this.escenario.add(notificacion);
                                            this.escenario.updateUI();
                                            MoverPanel mover = new MoverPanel(notificacion, this.escenario);
                                            ActionEvent e = null;
                                            mover.actionPerformed(e);
                                            if (this.matriz.ObtenerVisita(i, j) >= this.jugador.getVisitasCasillas()) {
                                                this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/1.jpg")));
                                                this.matriz.ObtenerDato(i, j).setName("1");
                                                this.matriz2.ObtenerDato(i, j).setName("1");

                                                int contador = 0;

                                                Object[] w = {var - 1, j, ""};
                                                int wNum = (int) w[0];
                                                if (wNum >= 0) {
                                                    w[2] = "si";
                                                    contador++;
                                                }
                                                Object[] x = {var + 1, j, ""};
                                                int xNum = (int) x[0];
                                                if (xNum < this.matriz.tamañoFilas()) {
                                                    x[2] = "si";
                                                    contador++;
                                                }
                                                Object[] y = {var, j - 1, ""};
                                                int yNum = (int) y[1];
                                                if (yNum >= 0) {
                                                    y[2] = "si";
                                                    contador++;
                                                }
                                                Object[] z = {var, j + 1, ""};
                                                int zNum = (int) z[1];
                                                if (zNum < this.matriz.tamañoColumnas()) {
                                                    z[2] = "si";
                                                    contador++;
                                                }

                                                Object[] datos = {w, x, y, z};
                                                Object[] datos2 = new Object[contador];
                                                int aviso = 0;
                                                for (Object dato : datos) {
                                                    Object[] valor = (Object[]) dato;
                                                    if (valor[2].toString().equals("si")) {
                                                        datos2[aviso] = valor;
                                                        aviso++;
                                                    }
                                                }

                                                int contador2 = 0;

                                                for (Object valor : datos2) {
                                                    Object[] valor2 = (Object[]) valor;
                                                    int a = (int) valor2[0];
                                                    int b = (int) valor2[1];
                                                    if (this.matriz.ObtenerDato(a, b).getName().equals("1")) {
                                                        contador2++;
                                                    }
                                                }

                                                if (contador == contador2) {
                                                    this.estado.removeAll();
                                                    this.escenario.removeAll();
                                                    JLabel imgEstado = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/sin movimientos.png")));
                                                    imgEstado.setBounds(10, 5, 426, 186);

                                                    JButton op2 = new JButton("volver a jugar");
                                                    op2.setName("reiniciar");
                                                    op2.setBounds(150, 200, 150, 30);
                                                    op2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, new Color(0, 0, 0), new Color(0, 0, 0), null));
                                                    op2.setBackground(Color.white);
                                                    op2.setFont(new Font("Comic Sans MS", 1, 15));
                                                    op2.addActionListener(new opcGanaste(op2, this.load, this.estado, this.escenario2));

                                                    this.estado.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (this.estado.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (this.estado.getSize().getHeight() / 2)));
                                                    this.estado.add(imgEstado);
                                                    this.estado.add(op2);
                                                    this.estado.updateUI();
                                                    this.escenario.add(estado);
                                                    this.estado.updateUI();
                                                    this.interuptor.setText("no");
                                                    for (JButton botones11 : this.botones1) {
                                                        botones11.setEnabled(false);
                                                    }
                                                }

                                            }
                                        } else {
                                            if (this.matriz.ObtenerDato(var, j).getName().equals("8") & this.matriz.ObtenerVisita(var, j) < this.jugador.getVisitasCasillas()) {
                                                if (this.jugador.getCantLlavesEspeciales() == this.jugador.getLlavesEspeciales()) {
                                                    this.matriz.ObtenerDato(var, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/jugador.jpg")));
                                                    this.matriz.ObtenerDato(var, j).setName("jugador");
                                                    this.matriz.ActualizarVisita(var, j, (this.matriz.ObtenerVisita(var, j) + 1));
                                                    this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/0.jpg")));
                                                    this.matriz.ObtenerDato(i, j).setName("0");
                                                    this.matriz2.ObtenerDato(var, j).setName("jugador");
                                                    this.matriz2.ObtenerDato(i, j).setName("0");
                                                    this.matriz2.ObtenerDato(var, j).setText("" + this.matriz.ObtenerVisita(var, j));
                                                    if (this.matriz.ObtenerVisita(i, j) >= this.jugador.getVisitasCasillas()) {
                                                        this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/1.jpg")));
                                                        this.matriz.ObtenerDato(i, j).setName("1");
                                                        this.matriz2.ObtenerDato(i, j).setName("1");

                                                        int contador = 0;

                                                        Object[] w = {var - 1, j, ""};
                                                        int wNum = (int) w[0];
                                                        if (wNum >= 0) {
                                                            w[2] = "si";
                                                            contador++;
                                                        }
                                                        Object[] x = {var + 1, j, ""};
                                                        int xNum = (int) x[0];
                                                        if (xNum < this.matriz.tamañoFilas()) {
                                                            x[2] = "si";
                                                            contador++;
                                                        }
                                                        Object[] y = {var, j - 1, ""};
                                                        int yNum = (int) y[1];
                                                        if (yNum >= 0) {
                                                            y[2] = "si";
                                                            contador++;
                                                        }
                                                        Object[] z = {var, j + 1, ""};
                                                        int zNum = (int) z[1];
                                                        if (zNum < this.matriz.tamañoColumnas()) {
                                                            z[2] = "si";
                                                            contador++;
                                                        }

                                                        Object[] datos = {w, x, y, z};
                                                        Object[] datos2 = new Object[contador];
                                                        int aviso = 0;
                                                        for (Object dato : datos) {
                                                            Object[] valor = (Object[]) dato;
                                                            if (valor[2].toString().equals("si")) {
                                                                datos2[aviso] = valor;
                                                                aviso++;
                                                            }
                                                        }

                                                        int contador2 = 0;

                                                        for (Object valor : datos2) {
                                                            Object[] valor2 = (Object[]) valor;
                                                            int a = (int) valor2[0];
                                                            int b = (int) valor2[1];
                                                            if (this.matriz.ObtenerDato(a, b).getName().equals("1")) {
                                                                contador2++;
                                                            }
                                                        }

                                                        if (contador == contador2) {
                                                            this.estado.removeAll();
                                                            this.escenario.removeAll();
                                                            JLabel imgEstado = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/sin movimientos.png")));
                                                            imgEstado.setBounds(10, 5, 426, 186);

                                                            JButton op2 = new JButton("volver a jugar");
                                                            op2.setName("reiniciar");
                                                            op2.setBounds(150, 200, 150, 30);
                                                            op2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, new Color(0, 0, 0), new Color(0, 0, 0), null));
                                                            op2.setBackground(Color.white);
                                                            op2.setFont(new Font("Comic Sans MS", 1, 15));
                                                            op2.addActionListener(new opcGanaste(op2, this.load, this.estado, this.escenario2));

                                                            this.estado.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (this.estado.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (this.estado.getSize().getHeight() / 2)));
                                                            this.estado.add(imgEstado);
                                                            this.estado.add(op2);
                                                            this.estado.updateUI();
                                                            this.escenario.add(estado);
                                                            this.estado.updateUI();
                                                            this.interuptor.setText("no");
                                                            for (JButton botones11 : this.botones1) {
                                                                botones11.setEnabled(false);
                                                            }
                                                        }

                                                    }
                                                } else {
                                                    this.escenario.removeAll();
                                                    JLabel notificacion = new JLabel();
                                                    notificacion.setSize(200, 150);
                                                    notificacion.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (notificacion.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (notificacion.getSize().getHeight() / 2)));
                                                    notificacion.setIcon(new ImageIcon(getClass().getResource("/imagenes/no pasar especial.png")));
                                                    this.escenario.add(notificacion);
                                                    this.escenario.updateUI();

                                                }

                                            } else {
                                                if (this.matriz.ObtenerDato(var, j).getName().equals("10")) {
                                                    this.matriz.ObtenerDato(var, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/jugador.jpg")));
                                                    this.matriz.ObtenerDato(var, j).setName("jugador");
                                                    this.matriz.ActualizarVisita(var, j, (this.matriz.ObtenerVisita(var, j) + 1));
                                                    this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/0.jpg")));
                                                    this.matriz.ObtenerDato(i, j).setName("0");
                                                    this.matriz2.ObtenerDato(var, j).setName("jugador");
                                                    this.matriz2.ObtenerDato(i, j).setName("0");
                                                    this.matriz2.ObtenerDato(var, j).setText("" + this.matriz.ObtenerVisita(var, j));
                                                    this.interuptor.setText("no");
                                                    this.estado.removeAll();
                                                    this.escenario.removeAll();
                                                    JLabel imgEstado = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/ganaste.png")));
                                                    imgEstado.setBounds(10, 5, 426, 186);

                                                    JLabel fuegos = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/fuegos artificiales.gif")));
                                                    fuegos.setBounds(0, 0, 106, 105);

                                                    JLabel fuegos2 = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/fuegos artificiales.gif")));
                                                    fuegos2.setBounds(344, 0, 106, 105);

                                                    JLabel fuegos3 = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/fuegos artificiales 2.gif")));
                                                    fuegos3.setBounds(153, 110, 144, 108);

                                                    JButton op1 = new JButton("Cambiar nivel");
                                                    op1.setName("cargar");
                                                    op1.setBounds(50, 200, 150, 30);
                                                    op1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, new Color(0, 0, 0), new Color(0, 0, 0), null));
                                                    op1.setBackground(Color.white);
                                                    op1.setFont(new Font("Comic Sans MS", 1, 15));

                                                    JButton op2 = new JButton("volver a jugar");
                                                    op2.setName("reiniciar");
                                                    op2.setBounds(250, 200, 150, 30);
                                                    op2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, new Color(0, 0, 0), new Color(0, 0, 0), null));
                                                    op2.setBackground(Color.white);
                                                    op2.setFont(new Font("Comic Sans MS", 1, 15));

                                                    op1.addActionListener(new opcGanaste(op1, this.load, this.estado, this.escenario2));
                                                    op2.addActionListener(new opcGanaste(op2, this.load, this.estado, this.escenario2));

                                                    this.estado.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (this.estado.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (this.estado.getSize().getHeight() / 2)));
                                                    this.estado.add(imgEstado);
                                                    this.estado.add(fuegos);
                                                    this.estado.add(fuegos2);
                                                    this.estado.add(fuegos3);
                                                    this.estado.add(op1);
                                                    this.estado.add(op2);
                                                    this.estado.updateUI();
                                                    this.escenario.add(estado);
                                                    this.escenario.updateUI();
                                                    for (JButton botones11 : this.botones1) {
                                                        botones11.setEnabled(false);
                                                    }
                                                }

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (tipo.equals("horizontal")) {

            if (this.matriz.ObtenerDato(i, var).getName().equals("0") & this.matriz.ObtenerVisita(i, var) < this.jugador.getVisitasCasillas()) {
                this.matriz.ObtenerDato(i, var).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/jugador.jpg")));
                this.matriz.ObtenerDato(i, var).setName("jugador");
                this.matriz.ActualizarVisita(i, var, (this.matriz.ObtenerVisita(i, var) + 1));
                this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/0.jpg")));
                this.matriz.ObtenerDato(i, j).setName("0");
                this.matriz2.ObtenerDato(i, var).setName("jugador");
                this.matriz2.ObtenerDato(i, j).setName("0");
                this.matriz2.ObtenerDato(i, var).setText("" + this.matriz.ObtenerVisita(i, var));
                if (this.matriz.ObtenerVisita(i, j) >= this.jugador.getVisitasCasillas()) {
                    this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/1.jpg")));
                    this.matriz.ObtenerDato(i, j).setName("1");
                    this.matriz2.ObtenerDato(i, j).setName("1");
                    int contador = 0;
                    Object[] w = {i, var - 1, false};
                    int wNum = (int) w[1];
                    if (wNum >= 0) {
                        w[2] = true;
                        contador++;
                    }
                    Object[] x = {i, var + 1, false};
                    int xNum = (int) x[1];
                    if (xNum < this.matriz.tamañoColumnas()) {
                        x[2] = true;
                        contador++;
                    }
                    Object[] y = {i - 1, var, false};
                    int yNum = (int) y[0];
                    if (yNum >= 0) {
                        y[2] = true;
                        contador++;
                    }
                    Object[] z = {i + 1, var, false};
                    int zNum = (int) z[0];
                    if (zNum < this.matriz.tamañoFilas()) {
                        z[2] = true;
                        contador++;
                    }

                    Object[] datos = {w, x, y, z};
                    Object[] datos2 = new Object[contador];
                    int aviso = 0;
                    for (Object dato : datos) {
                        Object[] valor = (Object[]) dato;
                        boolean est = (boolean) valor[2];
                        if (est) {
                            datos2[aviso] = valor;
                            aviso++;
                        }
                    }

                    int contador2 = 0;

                    for (Object valor : datos2) {
                        Object[] valor2 = (Object[]) valor;
                        int a = (int) valor2[0];
                        int b = (int) valor2[1];
                        if (this.matriz.ObtenerDato(a, b).getName().equals("1")) {
                            contador2++;
                        }
                    }

                    if (contador == contador2) {
                        this.estado.removeAll();
                        this.escenario.removeAll();
                        JLabel imgEstado = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/sin movimientos.png")));
                        imgEstado.setBounds(10, 5, 426, 186);

                        JButton op2 = new JButton("volver a jugar");
                        op2.setName("reiniciar");
                        op2.setBounds(150, 200, 150, 30);
                        op2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, new Color(0, 0, 0), new Color(0, 0, 0), null));
                        op2.setBackground(Color.white);
                        op2.setFont(new Font("Comic Sans MS", 1, 15));
                        op2.addActionListener(new opcGanaste(op2, this.load, this.estado, this.escenario2));

                        this.estado.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (this.estado.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (this.estado.getSize().getHeight() / 2)));
                        this.estado.add(imgEstado);
                        this.estado.add(op2);
                        this.estado.updateUI();
                        this.escenario.add(estado);
                        this.estado.updateUI();
                        this.interuptor.setText("no");
                        for (JButton botones11 : this.botones1) {
                            botones11.setEnabled(false);
                        }
                    }
                }
            } else {
                if (this.matriz.ObtenerDato(i, var).getName().equals("5") & this.matriz.ObtenerVisita(i, var) < this.jugador.getVisitasCasillas()) {
                    if (this.jugador.getCantLlavesRojas() > 0) {
                        this.matriz.ObtenerDato(i, var).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/jugador.jpg")));
                        this.matriz.ObtenerDato(i, var).setName("jugador");
                        this.matriz.ActualizarVisita(i, var, (this.matriz.ObtenerVisita(i, var) + 1));
                        this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/0.jpg")));
                        this.matriz.ObtenerDato(i, j).setName("0");
                        this.jugador.setCantLlavesRojas(this.jugador.getCantLlavesRojas() - 1);
                        this.matriz2.ObtenerDato(i, var).setName("jugador");
                        this.matriz2.ObtenerDato(i, j).setName("0");
                        this.matriz2.ObtenerDato(i, var).setText("" + this.matriz.ObtenerVisita(i, var));
                        if (this.matriz.ObtenerVisita(i, j) >= this.jugador.getVisitasCasillas()) {
                            this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/1.jpg")));
                            this.matriz.ObtenerDato(i, j).setName("1");
                            this.matriz2.ObtenerDato(i, j).setName("1");
                            int contador = 0;
                            Object[] w = {i, var - 1, false};
                            int wNum = (int) w[1];
                            if (wNum >= 0) {
                                w[2] = true;
                                contador++;
                            }
                            Object[] x = {i, var + 1, false};
                            int xNum = (int) x[1];
                            if (xNum < this.matriz.tamañoColumnas()) {
                                x[2] = true;
                                contador++;
                            }
                            Object[] y = {i - 1, var, false};
                            int yNum = (int) y[0];
                            if (yNum >= 0) {
                                y[2] = true;
                                contador++;
                            }
                            Object[] z = {i + 1, var, false};
                            int zNum = (int) z[0];
                            if (zNum < this.matriz.tamañoFilas()) {
                                z[2] = true;
                                contador++;
                            }

                            Object[] datos = {w, x, y, z};
                            Object[] datos2 = new Object[contador];
                            int aviso = 0;
                            for (Object dato : datos) {
                                Object[] valor = (Object[]) dato;
                                boolean est = (boolean) valor[2];
                                if (est) {
                                    datos2[aviso] = valor;
                                    aviso++;
                                }
                            }

                            int contador2 = 0;

                            for (Object valor : datos2) {
                                Object[] valor2 = (Object[]) valor;
                                int a = (int) valor2[0];
                                int b = (int) valor2[1];
                                if (this.matriz.ObtenerDato(a, b).getName().equals("1")) {
                                    contador2++;
                                }
                            }

                            if (contador == contador2) {
                                this.estado.removeAll();
                                this.escenario.removeAll();
                                JLabel imgEstado = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/sin movimientos.png")));
                                imgEstado.setBounds(10, 5, 426, 186);

                                JButton op2 = new JButton("volver a jugar");
                                op2.setName("reiniciar");
                                op2.setBounds(150, 200, 150, 30);
                                op2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, new Color(0, 0, 0), new Color(0, 0, 0), null));
                                op2.setBackground(Color.white);
                                op2.setFont(new Font("Comic Sans MS", 1, 15));
                                op2.addActionListener(new opcGanaste(op2, this.load, this.estado, this.escenario2));

                                this.estado.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (this.estado.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (this.estado.getSize().getHeight() / 2)));
                                this.estado.add(imgEstado);
                                this.estado.add(op2);
                                this.estado.updateUI();
                                this.escenario.add(estado);
                                this.estado.updateUI();
                                this.interuptor.setText("no");
                                for (JButton botones11 : this.botones1) {
                                    botones11.setEnabled(false);
                                }
                            }
                        }
                    } else {
                        this.escenario.removeAll();
                        JLabel notificacion = new JLabel();
                        notificacion.setSize(200, 150);
                        notificacion.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (notificacion.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (notificacion.getSize().getHeight() / 2)));
                        notificacion.setIcon(new ImageIcon(getClass().getResource("/imagenes/no pasar roja.png")));
                        this.escenario.add(notificacion);
                        this.escenario.updateUI();

                    }

                } else {
                    if (this.matriz.ObtenerDato(i, var).getName().equals("6") & this.matriz.ObtenerVisita(i, var) < this.jugador.getVisitasCasillas()) {
                        if (this.jugador.getCantLlavesAzules() > 0) {
                            this.matriz.ObtenerDato(i, var).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/jugador.jpg")));
                            this.matriz.ObtenerDato(i, var).setName("jugador");
                            this.matriz.ActualizarVisita(i, var, (this.matriz.ObtenerVisita(i, var) + 1));
                            this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/0.jpg")));
                            this.matriz.ObtenerDato(i, j).setName("0");
                            this.jugador.setCantLlavesAzules(this.jugador.getCantLlavesAzules() - 1);
                            this.matriz2.ObtenerDato(i, var).setName("jugador");
                            this.matriz2.ObtenerDato(i, j).setName("0");
                            this.matriz2.ObtenerDato(i, var).setText("" + this.matriz.ObtenerVisita(i, var));
                            if (this.matriz.ObtenerVisita(i, j) >= this.jugador.getVisitasCasillas()) {
                                this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/1.jpg")));
                                this.matriz.ObtenerDato(i, j).setName("1");
                                this.matriz2.ObtenerDato(i, j).setName("1");
                                int contador = 0;
                                Object[] w = {i, var - 1, false};
                                int wNum = (int) w[1];
                                if (wNum >= 0) {
                                    w[2] = true;
                                    contador++;
                                }
                                Object[] x = {i, var + 1, false};
                                int xNum = (int) x[1];
                                if (xNum < this.matriz.tamañoColumnas()) {
                                    x[2] = true;
                                    contador++;
                                }
                                Object[] y = {i - 1, var, false};
                                int yNum = (int) y[0];
                                if (yNum >= 0) {
                                    y[2] = true;
                                    contador++;
                                }
                                Object[] z = {i + 1, var, false};
                                int zNum = (int) z[0];
                                if (zNum < this.matriz.tamañoFilas()) {
                                    z[2] = true;
                                    contador++;
                                }

                                Object[] datos = {w, x, y, z};
                                Object[] datos2 = new Object[contador];
                                int aviso = 0;
                                for (Object dato : datos) {
                                    Object[] valor = (Object[]) dato;
                                    boolean est = (boolean) valor[2];
                                    if (est) {
                                        datos2[aviso] = valor;
                                        aviso++;
                                    }
                                }

                                int contador2 = 0;

                                for (Object valor : datos2) {
                                    Object[] valor2 = (Object[]) valor;
                                    int a = (int) valor2[0];
                                    int b = (int) valor2[1];
                                    if (this.matriz.ObtenerDato(a, b).getName().equals("1")) {
                                        contador2++;
                                    }
                                }

                                if (contador == contador2) {
                                    this.estado.removeAll();
                                    this.escenario.removeAll();
                                    JLabel imgEstado = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/sin movimientos.png")));
                                    imgEstado.setBounds(10, 5, 426, 186);

                                    JButton op2 = new JButton("volver a jugar");
                                    op2.setName("reiniciar");
                                    op2.setBounds(150, 200, 150, 30);
                                    op2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, new Color(0, 0, 0), new Color(0, 0, 0), null));
                                    op2.setBackground(Color.white);
                                    op2.setFont(new Font("Comic Sans MS", 1, 15));

                                    op2.addActionListener(new opcGanaste(op2, this.load, this.estado, this.escenario2));

                                    this.estado.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (this.estado.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (this.estado.getSize().getHeight() / 2)));
                                    this.estado.add(imgEstado);
                                    this.estado.add(op2);
                                    this.estado.updateUI();
                                    this.escenario.add(estado);
                                    this.estado.updateUI();
                                    this.interuptor.setText("no");
                                    for (JButton botones11 : this.botones1) {
                                        botones11.setEnabled(false);
                                    }
                                }
                            }
                        } else {
                            this.escenario.removeAll();
                            JLabel notificacion = new JLabel();
                            notificacion.setSize(200, 150);
                            notificacion.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (notificacion.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (notificacion.getSize().getHeight() / 2)));
                            notificacion.setIcon(new ImageIcon(getClass().getResource("/imagenes/no pasar azul.png")));
                            this.escenario.add(notificacion);
                            this.escenario.updateUI();

                        }

                    } else {
                        if (this.matriz.ObtenerDato(i, var).getName().equals("7") & this.matriz.ObtenerVisita(i, var) < this.jugador.getVisitasCasillas()) {
                            if (this.jugador.getCantLlavesAmarillas() > 0) {
                                this.matriz.ObtenerDato(i, var).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/jugador.jpg")));
                                this.matriz.ObtenerDato(i, var).setName("jugador");
                                this.matriz.ActualizarVisita(i, var, (this.matriz.ObtenerVisita(i, var) + 1));
                                this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/0.jpg")));
                                this.matriz.ObtenerDato(i, j).setName("0");
                                this.jugador.setCantLlavesAmarillas(this.jugador.getCantLlavesAmarillas() - 1);
                                this.matriz2.ObtenerDato(i, var).setName("jugador");
                                this.matriz2.ObtenerDato(i, j).setName("0");
                                this.matriz2.ObtenerDato(i, var).setText("" + this.matriz.ObtenerVisita(i, var));
                                if (this.matriz.ObtenerVisita(i, j) >= this.jugador.getVisitasCasillas()) {
                                    this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/1.jpg")));
                                    this.matriz.ObtenerDato(i, j).setName("1");
                                    this.matriz2.ObtenerDato(i, j).setName("1");
                                    int contador = 0;
                                    Object[] w = {i, var - 1, false};
                                    int wNum = (int) w[1];
                                    if (wNum >= 0) {
                                        w[2] = true;
                                        contador++;
                                    }
                                    Object[] x = {i, var + 1, false};
                                    int xNum = (int) x[1];
                                    if (xNum < this.matriz.tamañoColumnas()) {
                                        x[2] = true;
                                        contador++;
                                    }
                                    Object[] y = {i - 1, var, false};
                                    int yNum = (int) y[0];
                                    if (yNum >= 0) {
                                        y[2] = true;
                                        contador++;
                                    }
                                    Object[] z = {i + 1, var, false};
                                    int zNum = (int) z[0];
                                    if (zNum < this.matriz.tamañoFilas()) {
                                        z[2] = true;
                                        contador++;
                                    }

                                    Object[] datos = {w, x, y, z};
                                    Object[] datos2 = new Object[contador];
                                    int aviso = 0;
                                    for (Object dato : datos) {
                                        Object[] valor = (Object[]) dato;
                                        boolean est = (boolean) valor[2];
                                        if (est) {
                                            datos2[aviso] = valor;
                                            aviso++;
                                        }
                                    }

                                    int contador2 = 0;

                                    for (Object valor : datos2) {
                                        Object[] valor2 = (Object[]) valor;
                                        int a = (int) valor2[0];
                                        int b = (int) valor2[1];
                                        if (this.matriz.ObtenerDato(a, b).getName().equals("1")) {
                                            contador2++;
                                        }
                                    }

                                    if (contador == contador2) {
                                        this.estado.removeAll();
                                        this.escenario.removeAll();
                                        JLabel imgEstado = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/sin movimientos.png")));
                                        imgEstado.setBounds(10, 5, 426, 186);

                                        JButton op2 = new JButton("volver a jugar");
                                        op2.setName("reiniciar");
                                        op2.setBounds(150, 200, 150, 30);
                                        op2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, new Color(0, 0, 0), new Color(0, 0, 0), null));
                                        op2.setBackground(Color.white);
                                        op2.setFont(new Font("Comic Sans MS", 1, 15));
                                        op2.addActionListener(new opcGanaste(op2, this.load, this.estado, this.escenario2));

                                        this.estado.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (this.estado.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (this.estado.getSize().getHeight() / 2)));
                                        this.estado.add(imgEstado);
                                        this.estado.add(op2);
                                        this.estado.updateUI();
                                        this.escenario.add(estado);
                                        this.estado.updateUI();
                                        this.interuptor.setText("no");
                                        for (JButton botones11 : this.botones1) {
                                            botones11.setEnabled(false);
                                        }
                                    }
                                }

                            } else {
                                this.escenario.removeAll();
                                JLabel notificacion = new JLabel();
                                notificacion.setSize(200, 150);
                                notificacion.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (notificacion.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (notificacion.getSize().getHeight() / 2)));
                                notificacion.setIcon(new ImageIcon(getClass().getResource("/imagenes/no pasar amarillo.png")));
                                this.escenario.add(notificacion);
                                this.escenario.updateUI();

                            }

                        } else {
                            if (this.matriz.ObtenerDato(i, var).getName().equals("2") & this.matriz.ObtenerVisita(i, var) < this.jugador.getVisitasCasillas()) {
                                this.matriz.ObtenerDato(i, var).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/jugador.jpg")));
                                this.matriz.ObtenerDato(i, var).setName("jugador");
                                this.matriz.ActualizarVisita(i, var, (this.matriz.ObtenerVisita(i, var) + 1));
                                this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/0.jpg")));
                                this.matriz.ObtenerDato(i, j).setName("0");
                                this.jugador.setCantLlavesRojas(this.jugador.getCantLlavesRojas() + 1);
                                this.matriz2.ObtenerDato(i, var).setName("jugador");
                                this.matriz2.ObtenerDato(i, j).setName("0");
                                this.matriz2.ObtenerDato(i, var).setText("" + this.matriz.ObtenerVisita(i, var));
                                this.escenario.removeAll();
                                JLabel notificacion = new JLabel();
                                notificacion.setSize(200, 150);
                                notificacion.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (notificacion.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (notificacion.getSize().getHeight() / 2)));
                                notificacion.setIcon(new ImageIcon(getClass().getResource("/imagenes/llave roja +.png")));
                                this.escenario.add(notificacion);
                                this.escenario.updateUI();
                                MoverPanel mover = new MoverPanel(notificacion, this.escenario);
                                ActionEvent e = null;
                                mover.actionPerformed(e);
                                if (this.matriz.ObtenerVisita(i, j) >= this.jugador.getVisitasCasillas()) {
                                    this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/1.jpg")));
                                    this.matriz.ObtenerDato(i, j).setName("1");
                                    this.matriz2.ObtenerDato(i, j).setName("1");
                                    int contador = 0;
                                    Object[] w = {i, var - 1, false};
                                    int wNum = (int) w[1];
                                    if (wNum >= 0) {
                                        w[2] = true;
                                        contador++;
                                    }
                                    Object[] x = {i, var + 1, false};
                                    int xNum = (int) x[1];
                                    if (xNum < this.matriz.tamañoColumnas()) {
                                        x[2] = true;
                                        contador++;
                                    }
                                    Object[] y = {i - 1, var, false};
                                    int yNum = (int) y[0];
                                    if (yNum >= 0) {
                                        y[2] = true;
                                        contador++;
                                    }
                                    Object[] z = {i + 1, var, false};
                                    int zNum = (int) z[0];
                                    if (zNum < this.matriz.tamañoFilas()) {
                                        z[2] = true;
                                        contador++;
                                    }

                                    Object[] datos = {w, x, y, z};
                                    Object[] datos2 = new Object[contador];
                                    int aviso = 0;
                                    for (Object dato : datos) {
                                        Object[] valor = (Object[]) dato;
                                        boolean est = (boolean) valor[2];
                                        if (est) {
                                            datos2[aviso] = valor;
                                            aviso++;
                                        }
                                    }

                                    int contador2 = 0;

                                    for (Object valor : datos2) {
                                        Object[] valor2 = (Object[]) valor;
                                        int a = (int) valor2[0];
                                        int b = (int) valor2[1];
                                        if (this.matriz.ObtenerDato(a, b).getName().equals("1")) {
                                            contador2++;
                                        }
                                    }

                                    if (contador == contador2) {
                                        this.estado.removeAll();
                                        this.escenario.removeAll();
                                        JLabel imgEstado = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/sin movimientos.png")));
                                        imgEstado.setBounds(10, 5, 426, 186);

                                        JButton op2 = new JButton("volver a jugar");
                                        op2.setName("reiniciar");
                                        op2.setBounds(150, 200, 150, 30);
                                        op2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, new Color(0, 0, 0), new Color(0, 0, 0), null));
                                        op2.setBackground(Color.white);
                                        op2.setFont(new Font("Comic Sans MS", 1, 15));
                                        op2.addActionListener(new opcGanaste(op2, this.load, this.estado, this.escenario2));

                                        this.estado.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (this.estado.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (this.estado.getSize().getHeight() / 2)));
                                        this.estado.add(imgEstado);
                                        this.estado.add(op2);
                                        this.estado.updateUI();
                                        this.escenario.add(estado);
                                        this.estado.updateUI();
                                        this.interuptor.setText("no");
                                        for (JButton botones11 : this.botones1) {
                                            botones11.setEnabled(false);
                                        }
                                    }
                                }
                            } else {
                                if (this.matriz.ObtenerDato(i, var).getName().equals("3") & this.matriz.ObtenerVisita(i, var) < this.jugador.getVisitasCasillas()) {
                                    this.matriz.ObtenerDato(i, var).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/jugador.jpg")));
                                    this.matriz.ObtenerDato(i, var).setName("jugador");
                                    this.matriz.ActualizarVisita(i, var, (this.matriz.ObtenerVisita(i, var) + 1));
                                    this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/0.jpg")));
                                    this.matriz.ObtenerDato(i, j).setName("0");
                                    this.jugador.setCantLlavesAzules(this.jugador.getCantLlavesAzules() + 1);
                                    this.matriz2.ObtenerDato(i, var).setName("jugador");
                                    this.matriz2.ObtenerDato(i, j).setName("0");
                                    this.matriz2.ObtenerDato(i, var).setText("" + this.matriz.ObtenerVisita(i, var));
                                    this.escenario.removeAll();
                                    JLabel notificacion = new JLabel();
                                    notificacion.setSize(200, 150);
                                    notificacion.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (notificacion.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (notificacion.getSize().getHeight() / 2)));
                                    notificacion.setIcon(new ImageIcon(getClass().getResource("/imagenes/llave azul +.png")));
                                    this.escenario.add(notificacion);
                                    this.escenario.updateUI();
                                    MoverPanel mover = new MoverPanel(notificacion, this.escenario);
                                    ActionEvent e = null;
                                    mover.actionPerformed(e);
                                    if (this.matriz.ObtenerVisita(i, j) >= this.jugador.getVisitasCasillas()) {
                                        this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/1.jpg")));
                                        this.matriz.ObtenerDato(i, j).setName("1");
                                        this.matriz2.ObtenerDato(i, j).setName("1");
                                        int contador = 0;
                                        Object[] w = {i, var - 1, false};
                                        int wNum = (int) w[1];
                                        if (wNum >= 0) {
                                            w[2] = true;
                                            contador++;
                                        }
                                        Object[] x = {i, var + 1, false};
                                        int xNum = (int) x[1];
                                        if (xNum < this.matriz.tamañoColumnas()) {
                                            x[2] = true;
                                            contador++;
                                        }
                                        Object[] y = {i - 1, var, false};
                                        int yNum = (int) y[0];
                                        if (yNum >= 0) {
                                            y[2] = true;
                                            contador++;
                                        }
                                        Object[] z = {i + 1, var, false};
                                        int zNum = (int) z[0];
                                        if (zNum < this.matriz.tamañoFilas()) {
                                            z[2] = true;
                                            contador++;
                                        }

                                        Object[] datos = {w, x, y, z};
                                        Object[] datos2 = new Object[contador];
                                        int aviso = 0;
                                        for (Object dato : datos) {
                                            Object[] valor = (Object[]) dato;
                                            boolean est = (boolean) valor[2];
                                            if (est) {
                                                datos2[aviso] = valor;
                                                aviso++;
                                            }
                                        }

                                        int contador2 = 0;

                                        for (Object valor : datos2) {
                                            Object[] valor2 = (Object[]) valor;
                                            int a = (int) valor2[0];
                                            int b = (int) valor2[1];
                                            if (this.matriz.ObtenerDato(a, b).getName().equals("1")) {
                                                contador2++;
                                            }
                                        }

                                        if (contador == contador2) {
                                            this.estado.removeAll();
                                            this.escenario.removeAll();
                                            JLabel imgEstado = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/sin movimientos.png")));
                                            imgEstado.setBounds(10, 5, 426, 186);

                                            JButton op2 = new JButton("volver a jugar");
                                            op2.setName("reiniciar");
                                            op2.setBounds(150, 200, 150, 30);
                                            op2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, new Color(0, 0, 0), new Color(0, 0, 0), null));
                                            op2.setBackground(Color.white);
                                            op2.setFont(new Font("Comic Sans MS", 1, 15));
                                            op2.addActionListener(new opcGanaste(op2, this.load, this.estado, this.escenario2));

                                            this.estado.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (this.estado.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (this.estado.getSize().getHeight() / 2)));
                                            this.estado.add(imgEstado);
                                            this.estado.add(op2);
                                            this.estado.updateUI();
                                            this.escenario.add(estado);
                                            this.estado.updateUI();
                                            this.interuptor.setText("no");
                                            for (JButton botones11 : this.botones1) {
                                                botones11.setEnabled(false);
                                            }
                                        }
                                    }
                                } else {
                                    if (this.matriz.ObtenerDato(i, var).getName().equals("4") & this.matriz.ObtenerVisita(i, var) < this.jugador.getVisitasCasillas()) {
                                        this.matriz.ObtenerDato(i, var).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/jugador.jpg")));
                                        this.matriz.ObtenerDato(i, var).setName("jugador");
                                        this.matriz.ActualizarVisita(i, var, (this.matriz.ObtenerVisita(i, var) + 1));
                                        this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/0.jpg")));
                                        this.matriz.ObtenerDato(i, j).setName("0");
                                        this.jugador.setCantLlavesAmarillas(this.jugador.getCantLlavesAmarillas() + 1);
                                        this.matriz2.ObtenerDato(i, var).setName("jugador");
                                        this.matriz2.ObtenerDato(i, j).setName("0");
                                        this.matriz2.ObtenerDato(i, var).setText("" + this.matriz.ObtenerVisita(i, var));
                                        this.escenario.removeAll();
                                        JLabel notificacion = new JLabel();
                                        notificacion.setSize(200, 150);
                                        notificacion.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (notificacion.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (notificacion.getSize().getHeight() / 2)));
                                        notificacion.setIcon(new ImageIcon(getClass().getResource("/imagenes/llave amarilla +.png")));
                                        this.escenario.add(notificacion);
                                        this.escenario.updateUI();
                                        MoverPanel mover = new MoverPanel(notificacion, this.escenario);
                                        ActionEvent e = null;
                                        mover.actionPerformed(e);
                                        if (this.matriz.ObtenerVisita(i, j) >= this.jugador.getVisitasCasillas()) {
                                            this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/1.jpg")));
                                            this.matriz.ObtenerDato(i, j).setName("1");
                                            this.matriz2.ObtenerDato(i, j).setName("1");
                                            int contador = 0;
                                            Object[] w = {i, var - 1, false};
                                            int wNum = (int) w[1];
                                            if (wNum >= 0) {
                                                w[2] = true;
                                                contador++;
                                            }
                                            Object[] x = {i, var + 1, false};
                                            int xNum = (int) x[1];
                                            if (xNum < this.matriz.tamañoColumnas()) {
                                                x[2] = true;
                                                contador++;
                                            }
                                            Object[] y = {i - 1, var, false};
                                            int yNum = (int) y[0];
                                            if (yNum >= 0) {
                                                y[2] = true;
                                                contador++;
                                            }
                                            Object[] z = {i + 1, var, false};
                                            int zNum = (int) z[0];
                                            if (zNum < this.matriz.tamañoFilas()) {
                                                z[2] = true;
                                                contador++;
                                            }

                                            Object[] datos = {w, x, y, z};
                                            Object[] datos2 = new Object[contador];
                                            int aviso = 0;
                                            for (Object dato : datos) {
                                                Object[] valor = (Object[]) dato;
                                                boolean est = (boolean) valor[2];
                                                if (est) {
                                                    datos2[aviso] = valor;
                                                    aviso++;
                                                }
                                            }

                                            int contador2 = 0;

                                            for (Object valor : datos2) {
                                                Object[] valor2 = (Object[]) valor;
                                                int a = (int) valor2[0];
                                                int b = (int) valor2[1];
                                                if (this.matriz.ObtenerDato(a, b).getName().equals("1")) {
                                                    contador2++;
                                                }
                                            }

                                            if (contador == contador2) {
                                                this.estado.removeAll();
                                                this.escenario.removeAll();
                                                JLabel imgEstado = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/sin movimientos.png")));
                                                imgEstado.setBounds(10, 5, 426, 186);

                                                JButton op2 = new JButton("volver a jugar");
                                                op2.setName("reiniciar");
                                                op2.setBounds(150, 200, 150, 30);
                                                op2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, new Color(0, 0, 0), new Color(0, 0, 0), null));
                                                op2.setBackground(Color.white);
                                                op2.setFont(new Font("Comic Sans MS", 1, 15));
                                                op2.addActionListener(new opcGanaste(op2, this.load, this.estado, this.escenario2));

                                                this.estado.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (this.estado.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (this.estado.getSize().getHeight() / 2)));
                                                this.estado.add(imgEstado);
                                                this.estado.add(op2);
                                                this.estado.updateUI();
                                                this.escenario.add(estado);
                                                this.estado.updateUI();
                                                this.interuptor.setText("no");
                                                for (JButton botones11 : this.botones1) {
                                                    botones11.setEnabled(false);
                                                }
                                            }
                                        }
                                    } else {
                                        if (this.matriz.ObtenerDato(i, var).getName().equals("9") & this.matriz.ObtenerVisita(i, var) < this.jugador.getVisitasCasillas()) {
                                            this.matriz.ObtenerDato(i, var).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/jugador.jpg")));
                                            this.matriz.ObtenerDato(i, var).setName("jugador");
                                            this.matriz.ActualizarVisita(i, var, (this.matriz.ObtenerVisita(i, var) + 1));
                                            this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/0.jpg")));
                                            this.matriz.ObtenerDato(i, j).setName("0");
                                            this.jugador.setCantLlavesEspeciales(this.jugador.getCantLlavesEspeciales() + 1);
                                            this.matriz2.ObtenerDato(i, var).setName("jugador");
                                            this.matriz2.ObtenerDato(i, j).setName("0");
                                            this.matriz2.ObtenerDato(i, var).setText("" + this.matriz.ObtenerVisita(i, var));
                                            this.escenario.removeAll();
                                            JLabel notificacion = new JLabel();
                                            notificacion.setSize(200, 150);
                                            notificacion.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (notificacion.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (notificacion.getSize().getHeight() / 2)));
                                            notificacion.setIcon(new ImageIcon(getClass().getResource("/imagenes/llave especial +.png")));
                                            this.escenario.add(notificacion);
                                            this.escenario.updateUI();
                                            MoverPanel mover = new MoverPanel(notificacion, this.escenario);
                                            ActionEvent e = null;
                                            mover.actionPerformed(e);

                                            if (this.matriz.ObtenerVisita(i, j) >= this.jugador.getVisitasCasillas()) {
                                                this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/1.jpg")));
                                                this.matriz.ObtenerDato(i, j).setName("1");
                                                this.matriz2.ObtenerDato(i, j).setName("1");
                                                int contador = 0;
                                                Object[] w = {i, var - 1, false};
                                                int wNum = (int) w[1];
                                                if (wNum >= 0) {
                                                    w[2] = true;
                                                    contador++;
                                                }
                                                Object[] x = {i, var + 1, false};
                                                int xNum = (int) x[1];
                                                if (xNum < this.matriz.tamañoColumnas()) {
                                                    x[2] = true;
                                                    contador++;
                                                }
                                                Object[] y = {i - 1, var, false};
                                                int yNum = (int) y[0];
                                                if (yNum >= 0) {
                                                    y[2] = true;
                                                    contador++;
                                                }
                                                Object[] z = {i + 1, var, false};
                                                int zNum = (int) z[0];
                                                if (zNum < this.matriz.tamañoFilas()) {
                                                    z[2] = true;
                                                    contador++;
                                                }

                                                Object[] datos = {w, x, y, z};
                                                Object[] datos2 = new Object[contador];
                                                int aviso = 0;
                                                for (Object dato : datos) {
                                                    Object[] valor = (Object[]) dato;
                                                    boolean est = (boolean) valor[2];
                                                    if (est) {
                                                        datos2[aviso] = valor;
                                                        aviso++;
                                                    }
                                                }

                                                int contador2 = 0;

                                                for (Object valor : datos2) {
                                                    Object[] valor2 = (Object[]) valor;
                                                    int a = (int) valor2[0];
                                                    int b = (int) valor2[1];
                                                    if (this.matriz.ObtenerDato(a, b).getName().equals("1")) {
                                                        contador2++;
                                                    }
                                                }

                                                if (contador == contador2) {
                                                    this.estado.removeAll();
                                                    this.escenario.removeAll();
                                                    JLabel imgEstado = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/sin movimientos.png")));
                                                    imgEstado.setBounds(10, 5, 426, 186);

                                                    JButton op2 = new JButton("volver a jugar");
                                                    op2.setName("reiniciar");
                                                    op2.setBounds(150, 200, 150, 30);
                                                    op2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, new Color(0, 0, 0), new Color(0, 0, 0), null));
                                                    op2.setBackground(Color.white);
                                                    op2.setFont(new Font("Comic Sans MS", 1, 15));
                                                    op2.addActionListener(new opcGanaste(op2, this.load, this.estado, this.escenario2));

                                                    this.estado.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (this.estado.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (this.estado.getSize().getHeight() / 2)));
                                                    this.estado.add(imgEstado);
                                                    this.estado.add(op2);
                                                    this.estado.updateUI();
                                                    this.escenario.add(estado);
                                                    this.estado.updateUI();
                                                    this.interuptor.setText("no");

                                                    for (JButton botones11 : this.botones1) {
                                                        botones11.setEnabled(false);
                                                    }
                                                }
                                            }
                                        } else {
                                            if (this.matriz.ObtenerDato(i, var).getName().equals("8") & this.matriz.ObtenerVisita(i, var) < this.jugador.getVisitasCasillas()) {
                                                if (this.jugador.getCantLlavesEspeciales() == this.jugador.getLlavesEspeciales()) {
                                                    this.matriz.ObtenerDato(i, var).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/jugador.jpg")));
                                                    this.matriz.ObtenerDato(i, var).setName("jugador");
                                                    this.matriz.ActualizarVisita(i, var, (this.matriz.ObtenerVisita(i, var) + 1));
                                                    this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/0.jpg")));
                                                    this.matriz.ObtenerDato(i, j).setName("0");
                                                    this.matriz2.ObtenerDato(i, var).setName("jugador");
                                                    this.matriz2.ObtenerDato(i, j).setName("0");
                                                    this.matriz2.ObtenerDato(i, var).setText("" + this.matriz.ObtenerVisita(i, var));
                                                    if (this.matriz.ObtenerVisita(i, j) >= this.jugador.getVisitasCasillas()) {
                                                        this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/1.jpg")));
                                                        this.matriz.ObtenerDato(i, j).setName("1");
                                                        this.matriz2.ObtenerDato(i, j).setName("1");
                                                        int contador = 0;
                                                        Object[] w = {i, var - 1, false};
                                                        int wNum = (int) w[1];
                                                        if (wNum >= 0) {
                                                            w[2] = true;
                                                            contador++;
                                                        }
                                                        Object[] x = {i, var + 1, false};
                                                        int xNum = (int) x[1];
                                                        if (xNum < this.matriz.tamañoColumnas()) {
                                                            x[2] = true;
                                                            contador++;
                                                        }
                                                        Object[] y = {i - 1, var, false};
                                                        int yNum = (int) y[0];
                                                        if (yNum >= 0) {
                                                            y[2] = true;
                                                            contador++;
                                                        }
                                                        Object[] z = {i + 1, var, false};
                                                        int zNum = (int) z[0];
                                                        if (zNum < this.matriz.tamañoFilas()) {
                                                            z[2] = true;
                                                            contador++;
                                                        }

                                                        Object[] datos = {w, x, y, z};
                                                        Object[] datos2 = new Object[contador];
                                                        int aviso = 0;
                                                        for (Object dato : datos) {
                                                            Object[] valor = (Object[]) dato;
                                                            boolean est = (boolean) valor[2];
                                                            if (est) {
                                                                datos2[aviso] = valor;
                                                                aviso++;
                                                            }
                                                        }

                                                        int contador2 = 0;

                                                        for (Object valor : datos2) {
                                                            Object[] valor2 = (Object[]) valor;
                                                            int a = (int) valor2[0];
                                                            int b = (int) valor2[1];
                                                            if (this.matriz.ObtenerDato(a, b).getName().equals("1")) {
                                                                contador2++;
                                                            }
                                                        }

                                                        if (contador == contador2) {
                                                            this.estado.removeAll();
                                                            this.escenario.removeAll();
                                                            JLabel imgEstado = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/sin movimientos.png")));
                                                            imgEstado.setBounds(10, 5, 426, 186);

                                                            JButton op2 = new JButton("volver a jugar");
                                                            op2.setName("reiniciar");
                                                            op2.setBounds(150, 200, 150, 30);
                                                            op2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, new Color(0, 0, 0), new Color(0, 0, 0), null));
                                                            op2.setBackground(Color.white);
                                                            op2.setFont(new Font("Comic Sans MS", 1, 15));
                                                            op2.addActionListener(new opcGanaste(op2, this.load, this.estado, this.escenario2));

                                                            this.estado.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (this.estado.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (this.estado.getSize().getHeight() / 2)));
                                                            this.estado.add(imgEstado);
                                                            this.estado.add(op2);
                                                            this.estado.updateUI();
                                                            this.escenario.add(estado);
                                                            this.estado.updateUI();
                                                            this.interuptor.setText("no");

                                                            for (JButton botones11 : this.botones1) {
                                                                botones11.setEnabled(false);
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    this.escenario.removeAll();
                                                    JLabel notificacion = new JLabel();
                                                    notificacion.setSize(200, 150);
                                                    notificacion.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (notificacion.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (notificacion.getSize().getHeight() / 2)));
                                                    notificacion.setIcon(new ImageIcon(getClass().getResource("/imagenes/no pasar especial.png")));
                                                    this.escenario.add(notificacion);
                                                    this.escenario.updateUI();

                                                }
                                            } else {
                                                if (this.matriz.ObtenerDato(i, var).getName().equals("10")) {
                                                    this.matriz.ObtenerDato(i, var).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/jugador.jpg")));
                                                    this.matriz.ObtenerDato(i, var).setName("jugador");
                                                    this.matriz.ActualizarVisita(i, var, (this.matriz.ObtenerVisita(i, var) + 1));
                                                    this.matriz.ObtenerDato(i, j).setIcon(new ImageIcon(getClass().getResource("/imagenes/casillas/0.jpg")));
                                                    this.matriz.ObtenerDato(i, j).setName("0");
                                                    this.matriz2.ObtenerDato(i, var).setName("jugador");
                                                    this.matriz2.ObtenerDato(i, j).setName("0");
                                                    this.matriz2.ObtenerDato(i, var).setText("" + this.matriz.ObtenerVisita(i, var));
                                                    this.interuptor.setText("no");
                                                    this.estado.removeAll();
                                                    this.escenario.removeAll();
                                                    JLabel imgEstado = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/ganaste.png")));
                                                    imgEstado.setBounds(10, 5, 426, 186);

                                                    JLabel fuegos = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/fuegos artificiales.gif")));
                                                    fuegos.setBounds(0, 0, 106, 105);

                                                    JLabel fuegos2 = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/fuegos artificiales.gif")));
                                                    fuegos2.setBounds(344, 0, 106, 105);

                                                    JLabel fuegos3 = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/fuegos artificiales 2.gif")));
                                                    fuegos3.setBounds(153, 110, 144, 108);

                                                    JButton op1 = new JButton("Cambiar nivel");
                                                    op1.setName("cargar");
                                                    op1.setBounds(50, 200, 150, 30);
                                                    op1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, new Color(0, 0, 0), new Color(0, 0, 0), null));
                                                    op1.setBackground(Color.white);
                                                    op1.setFont(new Font("Comic Sans MS", 1, 15));

                                                    JButton op2 = new JButton("volver a jugar");
                                                    op2.setName("reiniciar");
                                                    op2.setBounds(250, 200, 150, 30);
                                                    op2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, new Color(0, 0, 0), new Color(0, 0, 0), null));
                                                    op2.setBackground(Color.white);
                                                    op2.setFont(new Font("Comic Sans MS", 1, 15));

                                                    op1.addActionListener(new opcGanaste(op1, this.load, this.estado, this.escenario2));
                                                    op2.addActionListener(new opcGanaste(op2, this.load, this.estado, this.escenario2));

                                                    this.estado.setLocation((int) ((this.escenario.getSize().getWidth() / 2) - (this.estado.getSize().getWidth() / 2)), (int) ((this.escenario.getSize().getHeight() / 2) - (this.estado.getSize().getHeight() / 2)));
                                                    this.estado.add(imgEstado);
                                                    this.estado.add(fuegos);
                                                    this.estado.add(fuegos2);
                                                    this.estado.add(fuegos3);
                                                    this.estado.add(op1);
                                                    this.estado.add(op2);
                                                    this.estado.updateUI();
                                                    this.escenario.add(estado);
                                                    this.escenario.updateUI();
                                                    for (JButton botones11 : this.botones1) {
                                                        botones11.setEnabled(false);
                                                    }
                                                }

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
