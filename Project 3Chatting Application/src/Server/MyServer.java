/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

/**
 *
 * @author Dell
 */
public class MyServer
{
   public static void main(String[] args)
   {
      Server server = new Server();
      server.watingForClient();
      server.setIoStream();
   }        
}
