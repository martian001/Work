package com.et.applet;

/**
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆            @author： The One                  ☆★
★☆            @time：2014年4月15日 下午9:20:22        ☆★
★☆            @version：1.0                      ☆★
★☆            @lastMotifyTime：                                                      ☆★
★☆            @ClassAnnotation：                                                   ☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
 */
public class MyLinked {
	public static void main(String[] args) {
		MyLinked linked = new MyLinked();
		linked.add("1");
		linked.delete(0);
		System.out.println(linked.get(0));
	}

	private class Node {
		private Node next;
		private Node first;
		private Object value;

		public Node(Object value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "Node [value=" + value + "]";
		}

	}

	private Node root;
	private Node tail;
	private int size;

	public boolean add(Object value) {
		Node newNode = new Node(value);
		if (size == 0) {
			root = newNode;
			tail = newNode;
			size++;
			return true;
		}
		tail.next = newNode;
		newNode.first = tail;
		tail = newNode;
		size++;
		return true;
	}

	private void checkIndex(int index) {
		if ((index > (size - 1)) || (index < 0) || ((size == 0) && (index == 0))) {
			throw new IndexOutOfBoundsException();
		}
	}

	public boolean delete(int index) {
		checkIndex(index);
		Node node = getNode(index);
		if (root.equals(node)) {
			root = root.next;
			if (root != null) {
				root.first = null;
			}
			size--;
			return true;
		}
		if (tail.equals(node)) {
			tail = tail.first;
			tail.next = null;
			size--;
			return true;
		}
		Node fatherNode = node.first;
		Node nextNode = node.next;
		fatherNode.next = nextNode;
		nextNode.first = fatherNode;
		size--;
		return true;
	}

	// 这里不检查index是因为，你懂得getNode有检查
	public Object get(int index) {
		return getNode(index).value;
	}

	// 这里不检查index是因为，调用该方法的地方会检查，所以不必重复检查二浪费性能
	public Node getForward(int index) {
		Node tempNode = root;
		int i = 0;
		do {
			if (i == index) {
				return tempNode;
			}
			i++;
			tempNode = tempNode.next;
		} while (tempNode != null);
		return null;
	}

	private Node getNode(int index) {
		if ((size == 0) || (index > (size - 1)) || (index < 0)) {
			throw new IndexOutOfBoundsException();
		}
		if (index > (size / 2)) {
			return getReverse(index);
		} else {
			return getForward(index);
		}
	}

	// 这里不检查index是因为，调用该方法的地方会检查，所以不必重复检查二浪费性能
	public Node getReverse(int index) {
		Node tempNode = tail;
		int i = size - 1;
		do {
			if (i == index) {
				return tempNode;
			}
			i--;
			tempNode = tempNode.first;
		} while (tempNode != null);

		return null;
	}

	// 这里不检查index是因为，你懂得getNode有检查
	public void set(int index, Object value) {
		Node setNode = getNode(index);
		setNode.value = value;
	}

	public int size() {
		return size;
	}
}
