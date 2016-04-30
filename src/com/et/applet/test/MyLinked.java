package com.et.applet.test;

/**
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆            @author： The One                  ☆★
★☆            @time：2014年4月14日 下午9:44:31        ☆★
★☆            @version：1.0                      ☆★
★☆            @lastMotifyTime：                                                      ☆★
★☆            @ClassAnnotation：                                                   ☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
 */
public class MyLinked {
	public static void main(String[] args) {
		MyLinked linked = new MyLinked();
		//		linked.add("第一个");
		linked.delete(0);
		//		linked.add("第二个");
		//		linked.add("第三个");
		//		System.out.println(linked.get(0));
	}

	private class Node {
		private Node next;
		private Object value;

		public Node(Object value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "value=" + value;
		}
	}

	private Node root;
	private Node tail;
	private int size;

	public void add(Object object) {
		Node newNode = new Node(object);
		if (root == null) {
			root = newNode;
			tail = newNode;
			size++;
			return;
		}
		tail.next = newNode;
		tail = newNode;
		size++;
		return;
	}

	public boolean delete(int index) {
		if ((size == 0) || (index < 0) || (index > size)) {
			return false;
		}
		if (index == 0) {
			root = root.next;
			size--;
			return true;
		}
		if (index == (size - 1)) {
			tail = getNode(index - 1);
			tail.next = null;
			size--;
			return true;
		}
		Node fatherNode = getNode(index - 1);
		fatherNode.next = fatherNode.next.next;
		size--;
		return true;

	}

	public boolean contains(Object value) {
		if (size == 0) {
			return false;
		}
		Node tempNode = root;
		do {
			if (tempNode.value.equals(value)) {
				return true;
			}
			tempNode = tempNode.next;
		} while (tempNode != null);
		return false;
	}

	public int indexOf(Object value) {
		if (size == 0) {
			return -1;
		}
		int i = 0;
		Node tempNode = root;

		do {
			if (tempNode.equals(value)) {
				return i;
			}
			i++;
			tempNode = tempNode.next;
		} while (tempNode != null);
		return -1;
	}

	public Object get(int index) {
		Object value = getNode(index).value;
		return value;
	}

	public void set(int index, Object value) {
		if ((size == 0) || (index < 0) || (index > size)) {
			return;
		}
		getNode(index).value = value;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Node getNode(int index) {
		if ((size == 0) || (index < 0) || (index > (size - 1))) {
			return null;
		}
		int i = 0;
		Node tempNode = root;

		do {
			if (i == index) {
				return tempNode;
			}
			i++;
			tempNode = tempNode.next;
		} while (tempNode != null);

		return null;
	}
}
