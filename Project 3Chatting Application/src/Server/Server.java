/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Dell
 */
public class Server 
{
    private JFrame serverframe;
    private JTextArea textarea;
    private JScrollPane scrollpane;
    private JTextField textfield;
    
    private ServerSocket serversocket;
    private InetAddress inet_address;
    
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    
    String ip_address ,ipaddress;
    //--------------------------Thread created-------------------
       Thread thread = new Thread()
       {
           public void run()
           {
             while(true)
             {
               readMessage();
             }    
           }        
       };
    //--------------------thread compieted-----------------------
  Server()
  {
     serverframe= new JFrame("server");
     serverframe.setSize(500,500);
     serverframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
     textarea = new JTextArea();
     textarea.setEditable(false);
     Font f = new Font("Arial",1,16);
     textarea.setFont(f);
     scrollpane = new JScrollPane(textarea);
     serverframe.add(scrollpane);
     
     textfield = new JTextField();
     textfield.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e)
         {
               sendMessage(textfield.getText());
               textarea.append(textfield.getText()+"\n");
               textfield.setText("");
         }
     });
     textfield.setEditable(false);
     
     serverframe.add(textfield,BorderLayout.SOUTH);
     
     serverframe.setVisible(true);
  }
  public void watingForClient()
  {
     try
     {
       ipaddress = getIpAddress();
       serversocket = new ServerSocket(6666);
       textarea.setText("To connect with server please provide Ip Address:- " + ipaddress);
       socket =serversocket.accept();
       textarea.setText("client found\n");
       textarea.append("------------------------\n");
       
       textfield.setEditable(true);
     }
     catch(Exception e)
     {
       System.out.println(e);
     }    
  }
  public String getIpAddress()
  {
     ip_address = "";
    try
    {
      inet_address = InetAddress.getLocalHost();
      ip_address = inet_address.getHostAddress();
      
    }
    catch(Exception e)
    {
       System.out.println(e);
    }    
    return ip_address;
  } 
  void setIoStream()
  {
    try
    {    
      dis = new DataInputStream(socket.getInputStream());
      dos = new DataOutputStream(socket.getOutputStream());
    }
    catch(Exception e)
    {
      System.out.println(e);
    } 
    thread.start();
  } 
  public void sendMessage(String message)
  {
    try
    {
      dos.writeUTF(message);
      dos.flush();
    }
    catch(Exception e)
    {
       System.out.println(e);
    } 
    
  } 
  public void readMessage()
  {
    try
    {    
      String message= dis.readUTF();
      showMessage("client :-"+message);
    } 
    catch(Exception e)
    {
      System.out.println(e);
    }    
  }        
  private void showMessage(String message)
  {
     textarea.append(message+"\n");
  }        
}
