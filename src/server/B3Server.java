package server;

import UDP.Student;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class B3Server {
    public static void main(String[] args) throws SocketException, IOException, ClassNotFoundException {
        DatagramSocket datagramSocket = new DatagramSocket(1109);
        byte[] receiveBytes = new byte[1024];        
        DatagramPacket datagramPacket = new DatagramPacket(receiveBytes,receiveBytes.length);
        datagramSocket.receive(datagramPacket);       
        byte[] objectBytes = new byte[1024];
        objectBytes = datagramPacket.getData();      
        ByteArrayInputStream bais = new ByteArrayInputStream(objectBytes);
        ObjectInputStream ois = new ObjectInputStream(bais);      
        Student student = (Student) ois.readObject();
        
        System.out.println("Student code: "+student.getCode());
        student.setId(1);
        student.setGpa(3);    
        System.out.println(student.getId()+" "+student.getGpa());
        
        
        InetAddress inetAddress = datagramPacket.getAddress();
        int port = datagramPacket.getPort();       
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(student);       
        byte[] sendObjectBytes = new byte[9000];       
        sendObjectBytes = baos.toByteArray();               
        DatagramPacket datagramPacket1 = new DatagramPacket(sendObjectBytes,sendObjectBytes.length, inetAddress, port);
        datagramSocket.send(datagramPacket1);    
        
        //receivce student updates
        
        byte[] receiveBytes2 = new byte[1024];        
        DatagramPacket datagramPacket2 = new DatagramPacket(receiveBytes2,receiveBytes2.length);
        datagramSocket.receive(datagramPacket2);       
        byte[] objectBytes2 = new byte[1024];
        objectBytes2 = datagramPacket2.getData();      
        ByteArrayInputStream bais2 = new ByteArrayInputStream(objectBytes2);
        ObjectInputStream ois2 = new ObjectInputStream(bais2);      
        Student student2 = (Student) ois2.readObject();
       
        System.out.println(student2.getId()+" "+student2.getCode()+" "+student2.getGpa() + " "+ student2.getGpaLetter());

       
    }
}
