package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UDPServer {
    public static void main(String[] args) {
        try {
            DatagramSocket server = new DatagramSocket(1107);
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
            try {
                server.receive(receivePacket);
                receiveData = receivePacket.getData();
                String s = new String(receiveData);
                String[] info = s.split(";");
                System.out.println(info[1]);
                System.out.println(info[2]);
                System.out.println(s);
                
                
                byte[] sendData = new byte[1024];
                InetAddress inetAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                
                String req = "1234;6ábsc,123)--++!10";
                sendData = req.getBytes();
                
                DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, inetAddress, port);
                server.send(sendPacket);
                      
                System.out.println("result-------------------");
                byte[] rs = new byte[1024];
                DatagramPacket rsPacket = new DatagramPacket(rs,rs.length);
                server.receive(rsPacket);
                rs = rsPacket.getData();
                String result = new String(rs);
                System.out.println(result);
                
                
            } catch (IOException ex) {
                Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SocketException ex) {
            Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
