package com.fatty.revise;

import java.util.Scanner;

public class Two {
	public static Three[] array = new Three[2];
	public static int size;
	public static Scanner sc = new Scanner(System.in);

	private Three[] arrays(Three[] array) {
		Three[] newArray = new Three[array.length * 2];
		int count = 0;
		for (int i = 0; i < array.length; i++) {
			newArray[count++] = array[i];
		}
		return newArray;
	}

	public void add() {
		System.out.println("请输入Id:");
		int id = CheckUtils.getId();
		if (CheckUtils.hisId(id)) {
			System.out.println("该Id已存在请重新确认！");
			return;
		}
		System.out.println("请输入姓名:");
		String name = sc.next();
		Three three = new Three();
		three.setId(id);
		three.setName(name);
		if (size == array.length) {
			array = arrays(array);
		}
		array[size++] = three;
		System.out.println("恭喜添加成功！");
	}

	public void delete() {
		System.out.println("请输入你要删除的Id:");
		int id = CheckUtils.getId();
		if (!CheckUtils.hisId(id)) {
			System.out.println("你所删除的Id不存在！");
			return;
		}
		Three[] newArray = new Three[array.length * 2];
		int count = 0;
		for (int i = 0; i < size; i++) {
			if (array[i].getId() != id) {
				newArray[count++] = array[i];
			}
		}
		array = newArray;
		size--;
		System.out.println("恭喜删除成功！");
	}

	public void revise() {
		System.out.println("请输入你要修改的Id:");
		int id = CheckUtils.getId();
		if (!CheckUtils.hisId(id)) {
			System.out.println("你所修改的Id不存在！");
			return;
		}
		System.out.println("请输入新的姓名:");
		String name = sc.next();
		int temp = 0;
		for (int i = 0; i < size; i++) {
			if (array[i].getId() == id) {
				temp = i;
			}
		}
		array[temp].setName(name);
		System.out.println("恭喜修改成功！");
	}

	public void querySingle() {
		System.out.println("请输入你要查询的Id:");
		int id = CheckUtils.getId();
		if (!CheckUtils.hisId(id)) {
			System.out.println("你所查询的Id不存在！");
			return;
		}
		for (int i = 0; i < size; i++) {
			if (array[i].getId() == id) {
				System.out.println(array[i]);
			}
		}
	}

	public static void main(String[] args) {
		Two two = new Two();
		while (true) {
			System.out.println("1.添加 2.删除 3.修改 4.查询单个 5.退出");
			int mark = CheckUtils.getId();
			if (mark == 1) {
				two.add();
			} else if (mark == 2) {
				two.delete();
			} else if (mark == 3) {
				two.revise();
			} else if (mark == 4) {
				two.querySingle();
			} else if (mark == 5) {
				break;
			} else if (mark == 6) {
				for (Three string : two.array) {
					System.out.println(string);
					if (string == null) {
						break;
					}
				}
			} else {
				System.out.println("请根据提示进行操作！");
			}
		}
	}

}

class Three {
	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "id: " + this.id + " " + "姓名: " + this.name;
	}

}
