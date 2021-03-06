package com.socket.cliente;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.socket.entidad.Supervisor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class frmCliente extends JFrame implements ActionListener {

  private JPanel contentPane;
  private JButton btnEnviar;
  private JButton btnCierre;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          frmCliente frame = new frmCliente();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   */
  public frmCliente() {
    setTitle("Cliente");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //setDefaultCloseOperation(0);
    setBounds(100, 100, 558, 231);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    btnEnviar = new JButton("");
    btnEnviar.setIcon(new ImageIcon(frmCliente.class.getResource("/img/mensajeCliente.png")));
    btnEnviar.addActionListener(this);
    btnEnviar.setBounds(124, 73, 89, 73);
    contentPane.add(btnEnviar);

    btnCierre = new JButton("");
    btnCierre.setIcon(new ImageIcon(frmCliente.class.getResource("/img/cerrarCliente.png")));
    btnCierre.addActionListener(this);
    btnCierre.setBounds(376, 73, 89, 73);
    contentPane.add(btnCierre);
  }

  public void actionPerformed(final ActionEvent arg0) {
    if (arg0.getSource() == btnCierre) {
      actionPerformedBtnCierre(arg0);
    }
    if (arg0.getSource() == btnEnviar) {
      actionPerformedBtnEnviar(arg0);
    }
  }

  protected void actionPerformedBtnEnviar(final ActionEvent arg0) {
    try {
      Socket enviar = new Socket("localhost", 1026);
      List<Supervisor> lista = new ArrayList<>();
      lista.add(new Supervisor(1, 25000, true));
      lista.add(new Supervisor(2, 35000, false));
      lista.add(new Supervisor(3, 45000, true));
      lista.add(new Supervisor(4, 55000, false));
      lista.add(new Supervisor(5, 75000, false));
      ObjectOutputStream stream = new ObjectOutputStream(enviar.getOutputStream());
      stream.writeObject(lista);
      enviar.close();


    } catch (Exception e) {
      e.printStackTrace();
    }


  }

  protected void actionPerformedBtnCierre(final ActionEvent arg0) {
    System.exit(0);
  }


}
