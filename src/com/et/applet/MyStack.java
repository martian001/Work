package com.et.applet;

/**
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆            @author： The One                  ☆★
★☆            @time：2014年4月17日 下午9:32:09        ☆★
★☆            @version：1.0                      ☆★
★☆            @lastMotifyTime：                                                      ☆★
★☆            @ClassAnnotation：                                                   ☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
 */
public class MyStack {
	public static void main(String[] args) {
		MyStack myStack = new MyStack(-1);
		myStack.push("1");
		myStack.push("2");
		myStack.push("3");
		myStack.push("4");
	}

	private int top;
	private Object[] objects;

	public MyStack(int length) {
		objects = new Object[length];
	}

	private void checklength() {
		if (top == objects.length) {
			Object[] tempObjects = objects;
			objects = new Object[objects.length * 2];
			for (int i = 0; i < tempObjects.length; i++) {
				objects[i] = tempObjects[i];
			}
		}
	}

	public void push(Object object) {
		checklength();
		objects[top++] = object;
	}

	public Object pop() {
		if (top == 0) {
			return null;
		}
		Object value = objects[--top];
		objects[top] = null;
		return value;
	}

	public Object peek() {
		if (top == 0) {
			throw new NullPointerException();
		}
		return objects[top - 1];
	}
}
