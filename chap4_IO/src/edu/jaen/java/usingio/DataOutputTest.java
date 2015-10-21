package edu.jaen.java.usingio;
import java.io.*;
class DataOutputTest {
   public static void main(String arg[]) {
      try {
         FileOutputStream fos = 
				new FileOutputStream("TestFile.txt");
         BufferedOutputStream bos = 
				new BufferedOutputStream(fos);
         DataOutputStream dos = 
				new DataOutputStream(bos);
		 dos.writeInt(4);
         dos.writeDouble(3.0);
		 dos.writeBoolean(true);
		 dos.writeUTF("this is");
         dos.close();
         bos.close();
         fos.close();
		System.out.println("���������� ����Ǿ����ϴ�");
	  }catch(IOException e) {
         System.out.println("ERROR "+ e.toString()); 
      } 
   }
}