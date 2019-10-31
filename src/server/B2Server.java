package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class B2Server {
    public static void main(String[] args) throws SocketException, IOException {
        DatagramSocket datagramSocket = new DatagramSocket(1108);
        byte[] receive1 = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(receive1,receive1.length);
        datagramSocket.receive(datagramPacket);
        receive1 = datagramPacket.getData();
        String s = new String(receive1);
        System.out.println(s);
        //end receive student code 
        
        InetAddress inetAddress = datagramPacket.getAddress();
        int port = datagramPacket.getPort();
        byte[] dataBytes = new byte[1024];       
        String dataStr = "dungBerlin;a2!@AN";
        dataBytes = dataStr.getBytes();
        DatagramPacket sendDataPacket = new DatagramPacket(dataBytes, dataBytes.length, inetAddress, port);
        datagramSocket.send(sendDataPacket);
    }
}
