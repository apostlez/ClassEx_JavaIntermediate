package edu.jaen.java.net;
import java.net.*;
import java.io.*;

public class SimpleClient {
  public static void main(String args[]) {
    try {
      Socket s1 = new Socket("192.168.10.57", 7000);  
      BufferedReader br = new BufferedReader(new InputStreamReader(s1.getInputStream()));
      System.out.println("Server에서 온 문자열 : "+br.readLine());
      br.close();
      s1.close();
    } catch (ConnectException connExc) {
      System.err.println("Could not connect to the server.");
    } catch (IOException e) {
      // ignore
    }
  }
}
