package com.et.applet;

/**
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆            @author： The One                  ☆★
★☆            @time：2014年4月10日 上午10:09:18      ☆★
★☆            @version：1.0                      ☆★
★☆            @lastMotifyTime：                                                      ☆★
★☆            @ClassAnnotation：                                                   ☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
 */
public class Array1 {
	public static void main(String[] args) {
		Array1 array1 = new Array1();
		array1.add(1);
		array1.add(2);
		array1.add(3);
		array1.add(4);
		array1.delete(1);
		array1.add(8);
		for (int i = 0; i < array1.index; i++) {
			System.out.println(array1.intege[i]);
		}
	}

	private int index;
	private int[] intege = new int[4];

	public void add(int id) {
		intege[index++] = id;
	}

	public void delete(int id) {
		if (getIndex(id) == -1) {
			return;
		}
		int tempIndex = 0;
		for (int i : intege) {
			if (i != id) {
				intege[tempIndex++] = i;
			}
		}
		index--;
	}

	public void modify(int id) {
		int index2 = getIndex(id);
		if (index2 == -1) {
			return;
		}
		System.out.println("请输入值");
		intege[index2] = id;
	}

	public int getIndex(int id) {
		for (int i = 0; i < intege.length; i++) {
			if (intege[i] == id) {
				return i;
			}
		}
		return -1;
	}
}
