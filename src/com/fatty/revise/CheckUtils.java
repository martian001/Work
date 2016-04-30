package com.fatty.revise;

import java.util.Scanner;

public class CheckUtils {
	public static int getId(){
		Scanner sc=new Scanner(System.in);
		while(true){
			try {
				return sc.nextInt();
			} catch (Exception e) {
				System.out.println("输入错误请重新输入：");
				sc.next();
			}
		}
	}
	public static boolean hisId(int id){
		for(int i=0;i<Two.size;i++){
			if(Two.array[i].getId()==id){
				return true;
			}
		}
		return false;
	}
}
