package com.easytop.stack;

/**
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆            @author： The One                  ☆★
★☆            @time：2014年6月15日 下午9:30:23      ☆★
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
		lincked.delete(3);
		lincked.add("第5个");
		lincked.findAll();
	}

	class Node {
		Node last;
		Node next;
		Object value;

		public Node() {
		}

		public Node(Object value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value.toString();
		}

	}

	int size;
	Node root;
	Node tail;

	public void add(Object object) {
		Node newNode = new Node(object);
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

	public Node getNode(int index) {
		if (root == null) {
			return null;
		}
		Node temp = root;
		int i = 0;
		do {
			if (index == i) {
				return temp;
			}
			i++;
			temp = temp.next;
		} while (temp != null);
		return null;
	}

	public Object get(int index) {
		if ((index < 0) || (index > (size - 1))) {
			throw new IndexOutOfBoundsException();
		}
		if (root == null) {
			throw new NullPointerException();
		}
		return getNode(index).value;
	}

	public void set(int index, Object newValue) {
		if ((index < 0) || (index > (size - 1))) {
			throw new IndexOutOfBoundsException();
		}
		if (root == null) {
			throw new NullPointerException();
		}
		getNode(index).value = newValue;
	}

	public void delete(int index) {
		if ((index < 0) || (index > (size - 1))) {
			throw new IndexOutOfBoundsException();
		}
		if (root == null) {
			throw new NullPointerException();
		}
		Node del = getNode(index);
		Node delNext = del.next;
		Node delLast = del.last;
		if (index == 0) {
			root = null;
			root = delNext;
			root.last = null;
			if (size == 1) {
				tail = null;
			}
			size--;
			return;
		}
		if (index == (size - 1)) {
			tail = tail.last;
			tail.next = null;
			size--;
			return;
		}
		delLast.next = delNext;
		delNext.last = delLast;
		size--;
	}

	public void findAll() {
		if (root == null) {
			return;
		}
		Node temp = root;
		do {
			System.out.println(temp);
			temp = temp.next;
		} while (temp != null);
	}
}
