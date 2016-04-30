package com.et.applet;

/**
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆            @author： The One                  ☆★
★☆            @time：2014年4月9日 下午5:44:13        ☆★
★☆            @version：1.0                      ☆★
★☆            @lastMotifyTime：                                                      ☆★
★☆            @ClassAnnotation：                                                   ☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
 */
public class Applet {
	public static void main(String[] args) {
		sort(null, 0);
		multiplicationTable();
		diamond();
		array2Sort();
		diamond();
	}

	private static void array2Sort() {
		int[][] number = { { 2, 1 }, { 7, 3 }, { 9, 5 } };
		for (int i = 0; i < number.length; i++) {
			for (int j = 0; j < number[i].length; j++) {
				for (int a = 0; a < number.length; a++) {
					for (int b = 0; b < number[i].length; b++) {
						if (number[i][j] > number[a][b]) {
							int temp = number[i][j];
							number[i][j] = number[a][b];
							number[a][b] = temp;
						}
					}
				}
			}
		}
		System.out.println("==========================");
		for (int i = 0; i < number.length; i++) {
			for (int j = 0; j < number[i].length; j++) {
				System.out.println(number[i][j]);
			}
		}
	}

	private static void sort(int[] number, int count) {
		for (int i = 0; i < (number.length - 1); i++) {
			System.out.println("==" + count++);
			for (int j = i + 1; j < number.length; j++) {
				if (number[i] > number[j]) {
					int temp = number[i];
					number[i] = number[j];
					number[j] = temp;
				}
			}
		}
		for (int i = 0; i < number.length; i++) {
			System.out.println(number[i]);
		}
	}

	private static void multiplicationTable() {
		for (int i = 0; i <= 9; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print(j + "*" + i + "=" + (i * j) + "  ");
			}
			System.out.println();
		}
	}

	private static void diamond() {
		for (int i = 0; i < 6; i++) {
			for (int j = 6; j > i; j--) {
				System.out.print(" ");
			}
			for (int j = 0; j < ((i * 2) - 1); j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		for (int i = 6; i > 0; i--) {
			for (int j = 6; j > i; j--) {
				System.out.print(" ");
			}
			for (int j = 0; j < ((i * 2) - 1); j++) {
				System.out.print("*");
			}
			System.out.println();

		}
	}
}
