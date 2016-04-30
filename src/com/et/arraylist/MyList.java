package com.et.arraylist;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆            @author： The One                  ☆★
★☆            @time：2014年4月8日 下午3:35:21        ☆★
★☆            @version：1.0                      ☆★
★☆            @lastMotifyTime：                                                      ☆★
★☆            @ClassAnnotation：                                                   ☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
 */
public class MyList<T> extends AbstractList<T> implements List<T> {
	public static void main(String[] args) {
		MyList<String> myList = new MyList<String>();
		myList.add("第一个");
		myList.add("第一个");
		myList.add("第三个");
		myList.add("第四个");
		myList.add("第四个");
		ArrayList<String> list = new ArrayList<>();
		list.add("第一个");
		//				list.add("第一个");
		//				list.add("第一个");
		//				list.add("第二个");
		//				list.add("第一个");
		//				list.add("第一个");
		//				list.add("第四个");
		//				list.add("第四个");
		myList.retainAll(list);
		//		myList.remove("第一个");
		//		myList.remove("第四个");
		for (String string : myList) {
			System.out.println(string);
		}

	}

	/**
	 * 储存数据数组
	 */
	private Object[] objects;

	/**
	 * 数组所储存数据的数量
	 */
	private int size = 0;

	/**
	 * 无参构造器，调用时，初始化数组，长度10
	 */
	public MyList() {
		objects = new Object[10];
	}

	/**
	 * 有参构造器，调用时，初始化数组，长度自定义
	 */
	public MyList(int length) {
		if (length <= 0) {
			throw new IndexOutOfBoundsException();
		}
		objects = new Object[length];
	}

	/**
	 * 根据索引，从指定位置添加数据
	 */
	@Override
	public void add(int index, T element) {
		if ((index < 0) || (index > size) || ((size == 0) && (index == 0))) {//防止容器只有0个元素，又往0位置插入
			throw new IndexOutOfBoundsException();
		}
		trendsLength();
		for (int i = size; i >= index; i--) {
			objects[i] = objects[i - 1];
		}
		objects[index] = element;
		size++;
	}

	/**
	 * 从尾部添加数据
	 */
	@Override
	public boolean add(T value) {
		trendsLength();
		objects[size++] = value;
		return true;
	}

	/**
	 * 在容器中添加一组数据
	 */
	@Override
	public boolean addAll(Collection<? extends T> collection) {
		if ((collection == null) || (collection.size() == 0)) {
			return false;
		}
		for (T value : collection) {
			add(value);
		}
		return true;
	}

	/**
	 * 从指定索引添加一组数据
	 */
	@Override
	public boolean addAll(int index, Collection<? extends T> collection) {
		if ((collection == null) || (collection.size() == 0) || ((size == 0) && (index == 0))) {//防止容器只有0个元素，又往0位置插入
			return false;
		}
		for (T value : collection) {
			add(index++, value);
		}
		return true;
	}

	/**
	 * 清除容器的数据
	 */
	@Override
	public void clear() {
		for (int i = 0; i < size; i++) {
			objects[i] = null;
		}
		objects = null;
		objects = new Object[10];
		size = 0;
	}

	/**
	 * 判断容器是否存在该数据
	 */
	@Override
	public boolean contains(Object value) {
		for (Object element : objects) {
			if (value.equals(element)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断该容器是否包含传入的集合全部元素
	 */
	@Override
	public boolean containsAll(Collection<?> colenction) {
		for (Object object : colenction) {
			if (!contains(object)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 根据索引获取数据
	 */
	@Override
	@SuppressWarnings("unchecked")
	public T get(int index) {
		if ((index > (size - 1)) || (index < 0)) {
			throw new IndexOutOfBoundsException();
		}
		return (T) objects[index];
	}

	/**
	 * 根据对象，返回该对象所在容器第一次出现的下标，不存在返回-1
	 */
	@Override
	public int indexOf(Object value) {
		for (int i = 0; i < size; i++) {
			if (objects[i].equals(value)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 判断容器是否为空
	 */
	@Override
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 根据对象，返回该对象所在容器最后一次出现的下标，不存在返回-1
	 */
	@Override
	public int lastIndexOf(Object value) {
		for (int i = size - 1; i > 0; i--) {
			if (objects[i].equals(value)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 根据索引删除指定数据
	 */
	@Override
	public T remove(int index) {
		T removeValue = get(index);
		if (removeValue == null) {
			return null;
		}
		remove(removeValue);
		return removeValue;
	}

	/**
	 * 删除该数据,删除顺数第一个，而不是全部相同的元素
	 */
	@Override
	public boolean remove(Object value) {
		if (!contains(value)) {
			return false;
		}
		boolean isSearch = false;
		Object[] temp = new Object[objects.length];
		int tempIndex = 0;
		for (int i = 0; i < size; i++) {
			// 移除删除的那个元素
			if (value.equals(objects[i]) && !isSearch) {
				isSearch = true;
			} else {
				temp[tempIndex++] = objects[i];
			}
		}
		objects = temp;
		size--;
		return true;
	}

	/**
	 * 删除一组数据
	 */
	@Override
	public boolean removeAll(Collection<?> conllections) {
		if ((conllections.size() == 0) || (conllections == null)) {
			return false;
		}
		for (Object object : conllections) {
			remove(object);
		}
		return true;
	}

	/**
	 * 根据索引设置数据
	 */
	@Override
	public T set(int index, T element) {
		T setValue = get(index);
		T tempSetValue = setValue;
		if (setValue == null) {
			return null;
		}
		setValue = element;
		return tempSetValue;
	}

	/**
	 * 获取所储存数据的数量
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * 根据指定索引，返回截取的数据
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> subList(int fromIndex, int toIndex) {
		if ((fromIndex < 0) || (fromIndex >= size) || (toIndex <= 0) || (toIndex > size)) {
			throw new IndexOutOfBoundsException();
		}
		ArrayList<T> arrayList = new ArrayList<>();
		for (int i = fromIndex; i < toIndex; i++) {
			arrayList.add((T) objects[i]);
		}
		return arrayList;
	}

	/**
	 * 返回该容器全部数据的数组
	 */
	@Override
	public Object[] toArray() {

		return objects;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean retainAll(Collection<?> c) {
		MyList<T> tempList = new MyList<>();
		for (Object object : c) {
			if (contains(object)) {
				tempList.add((T) object);
				remove(object);
			}
		}
		objects = tempList.toArray();
		size = tempList.size();
		return true;
	}

	/**
	 * 动态长度数组
	 */
	private void trendsLength() {
		int length = objects.length;
		if (size >= (length * 0.8)) {
			Object[] newObjects = new Object[length * 2];
			System.arraycopy(objects, 0, newObjects, 0, length);
			objects = newObjects;
		}
	}
}
