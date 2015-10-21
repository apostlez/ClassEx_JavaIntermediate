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

//실습 예제
//프로그램 내에서 out 폴더를 생성하고, 
//Data.txt를 읽어서 out폴더내에 Data2.txt를 출력하는 프로그램을 작성하여 보세요.