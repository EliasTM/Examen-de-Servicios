package com.socket.servidor;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.socket.entidad.Supervisor;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

public class frmServidor extends JFrame implements ActionListener, Runnable {

  private JPanel contentPane;
  private JTextArea txtS;
  private JScrollPane scrollPane;
  private JButton btnCierre;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          frmServidor frame = new frmServidor();
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
  public frmServidor() {
    setTitle("Servidor");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //setDefaultCloseOperation(0);
    setBounds(100, 100, 558, 477);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    scrollPane = new JScrollPane();
    scrollPane.setBounds(10, 11, 522, 354);
    contentPane.add(scrollPane);

    txtS = new JTextArea();
    txtS.setEditable(false);
    scrollPane.setViewportView(txtS);

    btnCierre = new JButton("");
    btnCierre.setIcon(new ImageIcon(frmServidor.class.getResource("/img/cerrarCliente.png")));
    btnCierre.addActionListener(this);
    btnCierre.setBounds(477, 376, 55, 51);
    contentPane.add(btnCierre);
    //Hilo
    Thread hilo = new Thread(this);
    hilo.start();


  }

  public void actionPerformed(final ActionEvent arg0) {
    if (arg0.getSource() == btnCierre) {
      actionPerformedBtnCierre(arg0);
    }
  }

  protected void actionPerformedBtnCierre(final ActionEvent arg0) {
    System.exit(0);
  }

  @Override
  public void run() {
    try {
      ServerSocket server = new ServerSocket(1026);
      Socket cliente;
      while (true) {
        cliente = server.accept();
        ObjectInputStream stream = new ObjectInputStream(cliente.getInputStream());

        List<Supervisor> lista = (List<Supervisor>) stream.readObject();
        listado(lista);
        stream.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  void listado(List<Supervisor> data) {
    double x = 0;
    int cantidad = 0;

    for (Supervisor bean : data) {
      x += bean.getSueldo();

      if (!bean.isCondicion()) {
        cantidad++;
      }

      txtS.append("Codigo : " + bean.getCodigo() + "\n");
      txtS.append("Sueldo : " + bean.getSueldo() + "\n");
      txtS.append("Condicion: " + bean.validacion() + "\n");
      txtS.append("--------------------\n");
    }
    txtS.append("Cantidad de Supervisores campa??a: " + cantidad + "\n");
    txtS.append("Total de los Sueldos : " + x + "\n");

  }

}
