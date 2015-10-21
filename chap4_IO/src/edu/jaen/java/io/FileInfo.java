package edu.jaen.java.io;
/*
** File Name 정보 
String getName() : file 이름 가져오기 
String getPath() : path 이름 가져오기 
String getAbsolutePath() : 절대 path 가져오기 
String getParent() : parent directoy 이름 가져오기 
boolean renameTo(File newName) : 다른 이름으로 바꾸기 
 
** File Testing 
boolean exists() : 존재하는지 확인 
boolean canWrite() : 쓸 수 있는지 확인 
boolean canRead() : 읽을 수 있는지 확인 
boolean isFIle() : file 인지 확인 
boolean isDirectory() : directory 인지 확인 
 
** Directory 정보 
boolean mkdir() : sub directory 생성 
String[] list() : 현재 diretory에 있는 전체 file list를 return 

** 그외의 메소드 
long lastModified() : 마지막 수정된 날짜 
long length() : 크기 알려주기 
boolean delete() : 삭제하기 */
 
import java.io.*;
import java.util.Scanner;

class FileInfo {
   public static void main(String arg[])throws Exception {
/*      if (arg.length != 1)    {
         System.out.println("Usage: java FileInfo filename");
         System.exit(0);
      }
*/
	   Scanner sc = new Scanner(System.in);
	   System.out.println("Input file name want to get information:");
	   String fname = sc.next();
	  File file = new File(fname);
      if (file.exists()) {
        System.out.println("파일 이름 : " + file.getName());
		System.out.println("상대 패스 : " + file.getPath());
		System.out.println("절대 패스 : " + file.getAbsolutePath());

		System.out.println("쓰기 가능 : " + file.canWrite());
		System.out.println("읽기 가능 : " + file.canRead());
		System.out.println("파일 길이 : " + file.length() + " 바이트");
		Scanner sc2 = new Scanner(file);
		while(sc2.hasNextLine()) {
			System.out.println(sc2.nextLine());
		}
      }else {
         System.out.println(fname + "not found");
      }          
   }
}
 