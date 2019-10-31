package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class B2Client {

    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket datagramSocket = new DatagramSocket();
        byte[] sendData1 = new byte[1024];
        String s = ";B16DCCN090;101";
        sendData1 = s.getBytes();
        InetAddress inetAddress = InetAddress.getByName("localhost");
        int port = 1108;
        DatagramPacket datagramPacket = new DatagramPacket(sendData1, sendData1.length, inetAddress, port);
        datagramSocket.send(datagramPacket);
        // end send student code        

        //receive data
        byte[] dataBytes = new byte[1024];
        DatagramPacket receiveData = new DatagramPacket(dataBytes, dataBytes.length);
        datagramSocket.receive(receiveData);
        dataBytes = receiveData.getData();
        String receiveStr = new String(dataBytes).trim();
        System.out.println(receiveStr);

        //Spilit string
        String[] dataStrArr = receiveStr.split(";");
        String data1 = dataStrArr[0];
        System.out.println(dataStrArr[0]);
        String data2 = dataStrArr[1];
        System.out.println(dataStrArr[1]);

        StringBuilder regularCh = new StringBuilder();
        StringBuilder specialCh = new StringBuilder();

           
        for (int i = 0; i < data2.length(); i++) {
            if (checkChar(data2.charAt(i)) == 1) {
                regularCh.append(data2.charAt(i));
            } else {
                specialCh.append(data2.charAt(i));
            }
        }
            System.out.println("Regular string: " + regularCh);
        System.out.println("Special string: " + specialCh);
    }

    public static int checkChar(char c) {
        if ((c >= 48 && c <= 57) || (c >= 65 && c <= 90) || (c >= 97 && c <= 122)) {
            return 1;
        }
        return 0;
    }
}
