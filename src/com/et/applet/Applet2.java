package com.et.applet;

import java.util.ArrayList;
import java.util.Random;

/**
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆            @author： The One                  ☆★
★☆            @time：2014年4月11日 上午9:41:55       ☆★
★☆            @version：1.0                      ☆★
★☆            @lastMotifyTime：                                                      ☆★
★☆            @ClassAnnotation：                                                   ☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
 */
public class Applet2 {
	public static void main(String[] args) {
		random();
	}

	private static void random() {
		String[] names = { "梁衍君", "唐冬明", "苏鸿杰", "邓志雄", "罗佳盼" };
		ArrayList<Integer> arrayList = new ArrayList<>();
		Random rm = new Random();
		while (arrayList.size() < 5) {
			int a = rm.nextInt(5);
			if (!arrayList.contains(a)) {
				arrayList.add(a);
			}
		}
		for (int j = 0; j < 5; j++) {
			if (names[j].equals(names[arrayList.get(j)])) {
				System.out.println(names[j] + "==" + names[arrayList.get(j)]);
				System.out.println("------------以上是重复的");
				random();
			}
			System.out.println(names[j] + "==" + names[arrayList.get(j)]);
		}
		System.exit(0);
	}
}
