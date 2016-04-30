package com.et.applet;

/**
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆            @author： The One                  ☆★
★☆            @time：2014年4月10日 下午2:42:05       ☆★
★☆            @version：1.0                      ☆★
★☆            @lastMotifyTime：                                                      ☆★
★☆            @ClassAnnotation：二维数组增删改查                   ☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
 */
public class Array2 {
	public static void main(String[] args) {
		Array2 array = new Array2();
		array.add(1, 23);
		array.add(2, 31);
		array.add(3, 24);
		array.add(4, 13);
		array.modify(0, 10, 100);
		array.findAll();
	}

	private int[][] infos = new int[9][2];
	private int index;

	public void add(int id, int age) {
		if (getIndex(id) != -1) {
			System.out.println("id已存在！");
			return;
		}
		infos[index][0] = id;
		infos[index++][1] = age;
	}

	public void delete(int id) {
		int index2 = getIndex(id);
		if (index2 == -1) {
			System.out.println("id不存在");
			return;
		}
		int tempIndex = 0;
		for (int i = 0; i < index; i++) {
			if (infos[i][0] != id) {
				infos[tempIndex][0] = infos[i][0];
				infos[tempIndex++][1] = infos[i][1];
			}
		}
		index--;
	}

	public void modify(int index, int id, int age) {
		infos[index][0] = id;
		infos[index][1] = age;
	}

	public int getIndex(int id) {
		for (int i = 0; i < index; i++) {
			if (infos[i][0] == id) {
				return i;
			}
		}
		return -1;
	}

	public void findAll() {
		for (int i = 0; i < index; i++) {
			for (int j = 0; j < infos[i].length; j++) {
				System.out.print(infos[i][j] + ",");
			}
			System.out.println();
		}
	}
}
