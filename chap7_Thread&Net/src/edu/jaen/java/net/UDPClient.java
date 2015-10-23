package edu.jaen.java.net;
import java.net.*;
import java.io.*;

public class UDPClient {
  public final static int port = 3000;
  public static void main(String[] args) {
    String hostname;
    if (args.length > 0) {
      hostname = args[0];
    } else {
      hostname = "192.168.10.57";   //원격 접속시 수정하세요^^
    }
    try {
      String strInput;
      DatagramPacket outPacket;
      	InetAddress server = InetAddress.getByName(hostname);
      	BufferedReader userInput= new BufferedReader(new InputStreamReader(System.in));
      	DatagramSocket dSock = new DatagramSocket();      	
		System.out.println("서버로 전송 문자열을 입력하세요. 마지막엔 'q'");
      while (true) {
		System.out.print("\n전송 문자열: ");
        strInput = userInput.readLine(); 	
        if (strInput.equals("q")) break;
        byte[] data = strInput.getBytes();
        outPacket = new DatagramPacket(data, data.length, server, port);
        dSock.send(outPacket);
        UDPClient.viewInfo(outPacket);
      }  
    } catch (UnknownHostException e) {
      System.err.println(e);
    } catch (SocketException se) {
      System.err.println(se);
    } catch (IOException e) {
      System.err.println(e);
    }
  }  // end main
	public static void viewInfo(DatagramPacket packet){
		System.out.println("패킷 IP : " + packet.getAddress());
		System.out.println("패킷 PORT : " + packet.getPort());
		System.out.println("패킷 SIZE : " + packet.getLength());		
	}
}