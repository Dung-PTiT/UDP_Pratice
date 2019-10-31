package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDPClient {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            byte[] sendData =  new byte[1024];
            String s = ";B16DCCN090;100";
            sendData = s.getBytes();
            try {
                InetAddress ipAddress = InetAddress.getByName("localhost");
                DatagramPacket packet  = new DatagramPacket(sendData, sendData.length, ipAddress, 1107);
                try {
                    socket.send(packet);
                } catch (IOException ex) {
                    Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (UnknownHostException ex) {
                Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
            try {
                socket.receive(receivePacket);
                byte[] data = receivePacket.getData();
                String dataStr = new String(data).trim();
                System.out.println(dataStr);
                
                String[] code = dataStr.split(";");
                String[] numArr = code[1].split(",");
                int a = Integer.parseInt(numArr[0]);
                int b = Integer.parseInt(numArr[1]);
                System.out.println(a);
                System.out.println(b);
                int uc = ucln(a,b);
                int bc = (a*b)/uc;
                System.out.println(uc);
                System.out.println(bc);
                String s1 = Integer.toString(uc);
                String s2 = Integer.toString(bc);
                
                String result = s1 + "," + s2;
                
                byte[] resultByte = new byte[1024];
                resultByte = result.getBytes();
                  InetAddress ipAddress1 = InetAddress.getByName("localhost");
                DatagramPacket datagramPacket = new DatagramPacket(resultByte,resultByte.length,ipAddress1,1107);
                socket.send(datagramPacket);
                    
            } catch (IOException ex) {
                Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
            }
                    
                    
        } catch (SocketException ex) {
            Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static int ucln(int a, int b){
        if(b == 0) return a;
        return ucln(b,a%b);
    }
}
