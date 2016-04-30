package com.et.applet;

/**
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆            @author： The One                  ☆★
★☆            @time：2014年4月10日 下午3:31:47       ☆★
★☆            @version：1.0                      ☆★
★☆            @lastMotifyTime：                                                      ☆★
★☆            @ClassAnnotation:先进先出                                    ☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
 */
public class Queue {
	public static void main(String[] args) {
		Queue queue = new Queue();
		queue.offer("第一个");
		queue.offer("第二个");
		queue.offer("第三个");
		System.out.println(queue.peek());
		System.out.println(queue.peek());
	}

	private Object[] objects = new Object[10];
	private int top;

	public void offer(Object value) {
		for (int i = top; i > 0; i--) {
			objects[i] = objects[i - 1];
		}
		objects[0] = value;
		top++;
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
		Object object = objects[top - 1];
		return object;
	}
}
