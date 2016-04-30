package com.et.applet;

/**
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆            @author： The One                  ☆★
★☆            @time：2014年4月16日 下午9:33:53        ☆★
★☆            @version：1.0                      ☆★
★☆            @lastMotifyTime：                                                      ☆★
★☆            @ClassAnnotation：                                                   ☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
 */
public class Queue2 {
	public static void main(String[] args) {
		Queue2 queue2 = new Queue2(10);
		queue2.offer("第一个");
		queue2.offer("第二个");
		queue2.offer("第三个");
		queue2.offer("第四个");
		System.out.println(queue2.pop());
		System.out.println(queue2.pop());
		System.out.println(queue2.pop());
		System.out.println(queue2.pop());
		System.out.println(queue2.pop());
	}

	public Queue2(int length) {
		objects = new Object[length];
	}

	private Object[] objects;
	private int top;

	public void offer(Object value) {
		for (int i = top; i > 0; i--) {
			objects[i] = objects[i - 1];
		}
		objects[0] = value;
		top++;
	}

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
		return objects[top - 1];
	}
}
