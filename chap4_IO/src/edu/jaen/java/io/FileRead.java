package edu.jaen.java.io;
import java.io.*;
class FileRead {
   public static void main(String arg[]) throws Exception{
	   /*  File f=new File("Data.txt");
         FileInputStream fis = new FileInputStream(f); 
         InputStreamReader isr = new InputStreamReader(fis); 
		 BufferedReader br=new BufferedReader(isr);
		*/
		BufferedReader br=new BufferedReader(
								new InputStreamReader(
								new FileInputStream(
								new File("Data.txt"))));


         String ch;
         while((ch=br.readLine()) != null )
             System.out.println(ch);                  
         br.close();
		// isr.close();
        // fis.close();              
   }
}

//�ǽ� ����
//���α׷� ������ out ������ �����ϰ�, 
//Data.txt�� �о out�������� Data2.txt�� ����ϴ� ���α׷��� �ۼ��Ͽ� ������.