package com.et.applet.test2;

/**
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆            @author： The One                  ☆★
★☆            @time：2014年6月8日 下午9:31:47      ☆★
★☆            @version：1.0                      ☆★
★☆            @lastMotifyTime：                                                      ☆★
★☆            @ClassAnnotation：                                                   ☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
 */
public class Lincked {
	public static void main(String[] args) {
		Lincked lincked = new Lincked();
		lincked.add("第一个");
		lincked.add("第二个");
		lincked.add("第三个");
		lincked.add("第四个");
		lincked.delete(1);
		lincked.findAll();
		//		System.out.println(lincked.get(4));
	}

	Node root;
	Node tail;
	int size;

	public void add(Object obj) {
		Node newNode = new Node(obj);
		if (root == null) {
			root = newNode;
			tail = newNode;
			size++;
			return;
		}
		tail.next = newNode;
		newNode.last = tail;
		tail = newNode;
		size++;
	}

	public void findAll() {
		if (size == 0) {
			return;
		}
		Node temp = root;
		while (temp != null) {
			System.out.println(temp.value);
			temp = temp.next;
		}
	}

	private Node getNode(int index) {
		if (size == 0) {
			return null;
		}
		if ((index > (size - 1)) || (index < 0)) {
			return null;
		}
		Node temp = root;
		int i = 0;
		while (temp != null) {
			if (i == index) {
				return temp;
			}
			temp = temp.next;
			i++;
		}
		return null;
	}

	public Object get(int index) {
		if (size == 0) {
			return null;
		}
		if ((index > (size - 1)) || (index < 0)) {
			return null;
		}
		return getNode(index).value;
	}

	public void modify(int index, Object value) {
		if (size == 0) {
			return;
		}
		if ((index > (size - 1)) || (index < 0)) {
			return;
		}
		getNode(index).value = value;
	}

	public void delete(int index) {
		if (size == 0) {
			return;
		}
		if ((index > (size - 1)) || (index < 0)) {
			return;
		}
		Node remove = getNode(index);
		Node father = remove.next;
		Node last = remove.last;

		if (root.equals(remove)) {
			root = last;
			if (root != null) {
				root.next = null;
			}
			size--;
		}

		if (tail.equals(remove)) {
			tail = father;
			tail.last = null;
			size--;
		}

		father.last = last;
		last.next = father;
		size--;
	}

	class Node {
		Object value;
		Node next;
		Node last;

		public Node(Object value) {
			this.value = value;
		}

		public Node() {

		}

		@Override
		public String toString() {
			return "[value=" + value + "]";
		}

	}

}
