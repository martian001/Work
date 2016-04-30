package com.et.applet;

import java.util.Scanner;

/**
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆            @author： The One                  ☆★
★☆            @time：2014年4月23日 下午9:30:06        ☆★
★☆            @version：1.0                      ☆★
★☆            @lastMotifyTime：                                                      ☆★
★☆            @ClassAnnotation：                                                   ☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
 */
public class ArrayWork {
	public static void main(String[] args) {
		ArrayWork maneger = new ArrayWork();
		while (true) {
			System.out.println("1.添加    2.查询     3.查询所有    4.删除    5.修改");
			int key = sc.nextInt();
			switch (key) {
			case 1:
				maneger.add();
				break;
			case 2:
				System.out.println("请输入id：");
				maneger.query(sc.nextInt());
				break;
			case 3:
				maneger.queryAll();
				break;
			case 4:
				System.out.println("请输入id：");
				maneger.delete(sc.nextInt());
				break;
			case 5:
				System.out.println("请输入id：");
				maneger.modify(sc.nextInt());
				break;
			default:
				break;
			}
		}
	}

	User[] user = new User[100];
	static Scanner sc = new Scanner(System.in);
	static int index = 0;

	public void add() {
		System.out.println("请输入id");
		int id = sc.nextInt();
		if (getIndex(id) != -1) {
			System.out.println("id已存在");
			return;
		}
		System.out.println("请输入姓名");
		String name = sc.next();
		user[index++] = new User(id, name);
		System.out.println("添加成功");
	}

	public void query(int id) {
		int userIndex = getIndex(id);
		if (userIndex == -1) {
			System.out.println("id不存在");
			return;
		}
		System.out.println(user[userIndex]);
	}

	public void queryAll() {
		if (index == 0) {
			System.out.println("暂无用户");
			return;
		}
		for (int i = 0; i < index; i++) {
			System.out.println(user[i]);
		}
	}

	public void delete(int id) {
		if (getIndex(id) == -1) {
			System.out.println("id不存在");
			return;
		}
		User[] tempUsers = new User[user.length];
		int tempIndex = 0;
		for (int i = 0; i < index; i++) {
			if (user[i].getId() != id) {
				tempUsers[tempIndex++] = user[i];
			}
		}
		user = tempUsers;
		index--;
		System.out.println("删除成功");
	}

	public void modify(int id) {
		int userIndex = getIndex(id);
		if (userIndex == -1) {
			System.out.println("id不存在");
			return;
		}
		System.out.println("请输入姓名");
		String name = sc.next();
		user[userIndex].setName(name);
		System.out.println("修改成功！");
	}

	public int getIndex(int id) {
		for (int i = 0; i < index; i++) {
			if (user[i].getId() == id) {
				return i;
			}
		}
		return -1;

	}
}

class User {
	private int id;
	private String name;

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}

	public User() {
	}

	public User(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + id;
		result = (prime * result) + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		User other = (User) obj;
		if (id != other.id) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

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

}