package com.yayao.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class TXTtest {
	 public static void main(String[] args) {
	        try {
	            //Scanner in = new Scanner(new File("D:/FTP/aaa.txt"));
	            FileReader fis=new FileReader(new File("D:/FTP/新建文本文档.txt"));
	            BufferedReader br=new BufferedReader(fis);
	            BufferedWriter bw=new BufferedWriter(new FileWriter(new File("D:/FTP/aaa.txt")));
	            String tempString = null;
	            while (( tempString = br.readLine())!=null) {
	            	//br.readLine();
	               //String str = br.readLine();
	            	System.out.println(tempString);
	            	
	            	//bw.write(br.readLine()+"\r\n");
	               //bw.write(br.readLine()+"\r\n");
	               //BufferedWriter bw=new BufferedWriter(str);
	                //splitt(str);
	            	bw.append(tempString+"\r\n");
	            	
	            }
	            br.close();
	            fis.close();
	            bw.close();
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	     
	    public static String[] splitt(String str){
	        String strr = str.trim();
	        String[] abc = strr.split("[\\p{Space}]+");
	        String str1 = abc[0];
	        String str2 = abc[1];
	        System.out.println(str1);
	        System.out.println(str2);
	        return abc;
	    }
}
