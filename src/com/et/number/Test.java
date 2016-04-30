package com.et.number;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author： The One
 * @time：2014年3月27日 下午5:44:03
 * @version：1.0
 */
public class Test {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArithUtils arithUtils = new ArithUtils();
		int key = 0;
		while (true) {
			try {
				System.out.println("    <计算器1.0>\n");
				System.out.println("请选择：\n1.加    2.减    3.乘    4.除    5.结束 ");
				key = sc.nextInt();
				switch (key) {
				case 1:
					System.out.println("请输入第一个操作数字：");
					double d1 = sc.nextDouble();
					System.out.println("请输入第二个操作数字：");
					double d2 = sc.nextDouble();
					System.out.println("计算结果：" + arithUtils.add(d1, d2));
					break;
				case 2:
					System.out.println("请输入第一个操作数字：");
					d1 = sc.nextDouble();
					System.out.println("请输入第二个操作数字：");
					d2 = sc.nextDouble();
					System.out.println("计算结果：" + arithUtils.sub(d1, d2));
					break;
				case 3:
					System.out.println("请输入第一个操作数字：");
					d1 = sc.nextDouble();
					System.out.println("请输入第二个操作数字：");
					d2 = sc.nextDouble();
					System.out.println("计算结果：" + arithUtils.mul(d1, d2));
					break;
				case 4:
					System.out.println("请输入第一个操作数字：");
					d1 = sc.nextDouble();
					System.out.println("请输入第二个操作数字：");
					d2 = sc.nextDouble();
					System.out.println("计算结果：" + arithUtils.div(d1, d2));
					break;
				case 5:
					System.out.println("程序已结束，欢饮下次使用！");
					return;
				default:
					System.out.println("选择错误！");
					break;
				}
			} catch (ArithmeticException | InputMismatchException e) {
				System.out.println("错误提示：计算错误或者输入错误！");
				sc.nextLine();
			}
		}
	}
}
