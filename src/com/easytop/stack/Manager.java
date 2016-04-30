package com.easytop.stack;

import java.util.Scanner;

/**
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆            @author： The One                  ☆★
★☆            @time：2014年6月19日 下午9:30:58      ☆★
★☆            @version：1.0                      ☆★
★☆            @lastMotifyTime：                                                      ☆★
★☆            @ClassAnnotation：                                                   ☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
 */
public class Manager {
	public static void main(String[] args) {
		Manager manager = new Manager();
		while (true) {
			System.out.println("1.add   2.delete  3.modify   4.find   5.findAll");
			int key = sc.nextInt();
			switch (key) {
			case 1:
				manager.add();
				break;
			case 2:
				manager.delete();
				break;
			case 3:
				manager.modify();
				break;
			case 4:
				manager.find();
				break;
			case 5:
				manager.findAll();
				break;

			default:
				break;
			}
		}
	}

	static Scanner sc = new Scanner(System.in);
	public static Person[] persons = new Person[1];
	public static int size;

	public void add() {
		System.out.println("请输入id");
		int id = sc.nextInt();
		if (util.getIndex(id) != -1) {
			System.out.println("用户已存在");
			return;
		}
		addArray();
		System.out.println("请输入名字：");
		String name = sc.next();
		Person p = new Person(id, name);
		persons[size++] = p;
	}

	public void addArray() {
		if (size == persons.length) {
			Person[] temp = new Person[persons.length * 2];
			for (int i = 0; i < persons.length; i++) {
				temp[i] = persons[i];
			}
			persons = temp;
		}
	}

	public void delete() {

		System.out.println("请输入id");
		int id = sc.nextInt();
		if (util.getIndex(id) == -1) {
			System.out.println("用户不存在");
			return;
		}
		Person[] temp = new Person[persons.length];
		int tempIndex = 0;
		for (int i = 0; i < persons.length; i++) {
			if (persons[i].getId() != id) {
				temp[tempIndex++] = persons[i];
			}
		}
		persons = temp;
		size--;
	}

	public void modify() {
		System.out.println("请输入id");
		int id = sc.nextInt();
		int index = util.getIndex(id);
		if (index == -1) {
			System.out.println("用户不存在");
			return;
		}
		System.out.println("请输入名字：");
		String name = sc.next();
		persons[index].setName(name);
	}

	public void find() {
		System.out.println("请输入id");
		int id = sc.nextInt();
		int index = util.getIndex(id);
		if (index == -1) {
			System.out.println("用户不存在");
			return;
		}
		System.out.println(persons[index]);
	}

	public void findAll() {
		if ((persons == null) || (size == 0)) {
			System.out.println("没有用户");
			return;
		}
		for (int i = 0; i < size; i++) {
			System.out.println(persons[i]);
		}
	}
}

class util {
	public static int getIndex(int id) {
		for (int i = 0; i < Manager.size; i++) {
			if (Manager.persons[i].getId() == id) {
				return i;
			}
		}
		return -1;

	}
}

class Person {
	private int id;
	private String name;

	public Person(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Person() {
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

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
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
		Person other = (Person) obj;
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

}
