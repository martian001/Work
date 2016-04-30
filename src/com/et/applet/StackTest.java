package com.et.applet;

/**
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆            @author： The One                  ☆★
★☆            @time：2014年6月9日 下午9:24:40        ☆★
★☆            @version：1.0                      ☆★
★☆            @lastMotifyTime：                                                      ☆★
★☆            @ClassAnnotation：        栈数据结构，先进后出  ☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
 */
public class StackTest {
	Object[] objects;
	int top;

	public StackTest(int size) {
		objects = new Object[size];
	}

	public StackTest() {
		objects = new Object[10];
	}

	public void push(Object object) {
		if (top == objects.length) {
			Object[] temp = new Object[objects.length * 2];
			for (int i = 0; i < objects.length; i++) {
				temp[i] = objects[i];
			}
			objects = temp;
		}
		objects[top++] = object;
	}

	public Object poll() {
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
		return objects[top - 1];
	}

	public static void main(String[] args) {
		StackTest stackTest = new StackTest(0);
		stackTest.push("第一个");
		//		stackTest.push("第二个");
		//		stackTest.push("第三个");
		//		stackTest.push("第四个");
		//		System.out.println(stackTest.poll());
		//		System.out.println(stackTest.poll());
		//		System.out.println(stackTest.poll());
		//		System.out.println(stackTest.poll());
		//		System.out.println(stackTest.peek());
		//		System.out.println(stackTest.peek());
		//		System.out.println(stackTest.peek());
	}
}
