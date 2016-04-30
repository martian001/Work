package com.et.applet;

/**
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆            @author： The One                  ☆★
★☆            @time：2014年4月11日 下午8:12:40        ☆★
★☆            @version：1.0                      ☆★
★☆            @lastMotifyTime：                                                      ☆★
★☆            @ClassAnnotation：先进后出                                                   ☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
 */
public class Stack {
	public static void main(String[] args) {
		Stack stack = new Stack();

	}

	int top = 0;
	Object[] objects = new Object[10];

	/**
	 * 将数据放在堆栈顶部
	 * @param object
	 */
	public void push(Object object) {
		objects[top++] = object;
	}

	/**
	 * 从顶部移除，并返回
	 */
	public Object pop() {
		if (top == 0) {
			return null;
		}
		Object object = objects[--top];
		objects[top] = null;
		return object;
	}

	public Object peek() {
		if (top == 0) {
			return null;
		}
		Object object = objects[top - 1];
		return object;
	}
}
