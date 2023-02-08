/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Dell
 */
public class Client 
{
   private JFrame clientframe;
   private JTextArea textarea;
   private JTextField textfield;
   private JScrollPane scrollpane;
     
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
   
   String ipaddress;
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
   
   Client()
   {
     ipaddress = JOptionPane.showInputDialog("Entre the ip address");
   
     if(!ipaddress.equals("null"))
     {
       if(!ipaddress.equals(""))
       {
        
       connectToServer();
           
       clientframe= new JFrame("Client");
       clientframe.setSize(500,500);
       clientframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
       textarea = new JTextArea();
       textarea.setEditable(false);
       Font f = new Font("Arial",1,16);
       textarea.setFont(f);
       scrollpane = new JScrollPane(textarea);
       clientframe.add(scrollpane);
     
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
        
      clientframe.add(textfield,BorderLayout.SOUTH);
     
       clientframe.setVisible(true);
       }
       
     }    
   }
   void connectToServer()
   {
     try
     {
       socket = new Socket(ipaddress,6666);
     }
     catch(Exception e)
     {
       System.out.println(e);
     }        
   
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
       showMessage(message);
     } 
     catch(Exception e)
     {
       System.out.println(e);
     }    
   }    
    private void showMessage(String message)
    {
       textarea.append("server syas :- "+message+"\n");
    }   
   
   
}
