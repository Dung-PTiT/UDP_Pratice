package client;

import UDP.Student;
import com.sun.corba.se.pept.encoding.OutputObject;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class B3Client {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException, ClassNotFoundException {
        DatagramSocket datagramSocket = new DatagramSocket();
        InetAddress inetAddress = InetAddress.getByName("localhost");
        int port = 1109;
        Student student = new Student("B16DCCN090");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(student);      
        byte[] objectBytes = new byte[1024];
        objectBytes = baos.toByteArray();
        
        
        DatagramPacket datagramPacket = new DatagramPacket(objectBytes,objectBytes.length,inetAddress,port);
        datagramSocket.send(datagramPacket);
        
        byte[] receiveBytes = new byte[1024];        
        DatagramPacket datagramPacket1 = new DatagramPacket(receiveBytes,receiveBytes.length);
        datagramSocket.receive(datagramPacket1);       
        byte[] receiveObjectBytes = new byte[1024];
        receiveObjectBytes = datagramPacket1.getData();      
        ByteArrayInputStream bais = new ByteArrayInputStream(receiveObjectBytes);
        ObjectInputStream ois = new ObjectInputStream(bais);      
        Student student1 = (Student) ois.readObject();
        System.out.println(student1.getId()+" "+student1.getCode()+" "+student1.getGpa());
        
        
        float gpa = student1.getGpa();
        if(gpa>=0 && gpa <1){
            student1.setGpaLetter("F");
        }else if(gpa>=1 && gpa <2){
            student1.setGpaLetter("D");
        }else if(gpa>=2 && gpa <3){
            student1.setGpaLetter("C");
        }else if(gpa>=3 && gpa <3.7){
            student1.setGpaLetter("B");
        }else if(gpa>=3.7 && gpa <4){
            student1.setGpaLetter("A");
        }       
        System.out.println(student1.getGpaLetter());
        
        //send student update
        
        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        ObjectOutputStream oos2 = new ObjectOutputStream(baos2);
        oos2.writeObject(student1);      
        byte[] objectBytes2 = new byte[1024];
        objectBytes2 = baos2.toByteArray();
        
        
        DatagramPacket datagramPacket2 = new DatagramPacket(objectBytes2,objectBytes2.length,inetAddress,port);
        datagramSocket.send(datagramPacket2);
        
    }
}
