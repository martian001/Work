package com.et.bothwayLikedList;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆            @author： The One                  ☆★
★☆            @time：2014年4月8日 下午7:40:18        ☆★
★☆            @version：1.0                      ☆★
★☆            @lastMotifyTime：                                                      ☆★
★☆            @ClassAnnotation：                                                   ☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
 */
public class MyLinked<T> extends AbstractList<T> implements List<T> {

   private class Node {
      private Node last;//上一个节点
      private Node next;//下一个节点
      private T value;//当前节点

      public Node(T value) {
         this.value = value;
      }
      
   }

   private int size;//集合大小
   private Node tail;//最后节点
   private Node root;//开始节点

   /**
    * 根据索引插入进行添加
    * 三种情况：
    * 1.index=0，添加到链表的根节点
    * 2.index=size，添加到链表的尾节点
    * 3.index>0 and index<sieze，添加到链表的中间节点
    */
   @Override
   public void add(int index, T element) {
      Node newNode = new Node(element);
      if (index == 0) {
         newNode.next = root;
         root.last = newNode;
         root = newNode;
         size++;
         return;
      }
      if (index == size) {
         add(element);
         return;
      }
      Node firstNode = getNode(index).last;
      Node lastNode = firstNode.next;
      firstNode.next = newNode;
      newNode.last = firstNode;
      newNode.next = lastNode;
      lastNode.last = newNode;
      size++;
   }

   @Override
   public boolean add(T element) {
      Node newNode = new Node(element);
      //第一次添加:根节点和尾节点都是添加的对象
      if (root == null) {
         root = newNode;
         tail = newNode;
         size++;
         return true;
      }
      //第二次及以后：尾节点的下一个=添加的对象，
      tail.next = newNode;
      newNode.last = tail;
      tail = newNode;
      size++;
      return true;
   }

   @Override
   public boolean addAll(Collection<? extends T> conllection) {
      for (T element : conllection) {
         add(element);
      }
      return true;
   }

   @Override
   public boolean addAll(int index, Collection<? extends T> collection) {
      checkIndex(index);
      for (T c : collection) {
         add(index++, c);
      }
      return true;
   }

   private void checkIndex(int index) {
      if ((index > (size - 1)) || (index < 0) || ((size == 0) && (index == 0))) {
         throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
      }
   }

   @Override
   public void clear() {
      size = 0;
      root = null;
      tail = null;
   }

   @Override
   public boolean contains(Object value) {
      Node nextNode = root;
      while (nextNode != null) {
         if (value.equals(nextNode.value)) {
            return true;
         }
         nextNode = nextNode.next;
      }
      return false;
   }

   @Override
   public boolean containsAll(Collection<?> collection) {
      for (Object object : collection) {
         if (!contains(object)) {
            return false;
         }
      }
      return true;
   }

   @Override
   public T get(int index) {
      checkIndex(index);
      T value = getNode(index).value;
      return value;
   }

   private Node getForward(int index) {
      Node tempNext = root;
      int i = 0;
      do {
         if (i == index) {
            return tempNext;
         }
         i++;
         tempNext = tempNext.next;
      } while (tempNext != null);
      return null;
   }

   public Node getNode(int index) {
      checkIndex(index);
      if (index >= (size / 2)) {
         return getReverse(index);
      } else {
         return getForward(index);
      }
   }

   private Node getReverse(int index) {
      Node tempLast = tail;
      int i = size - 1;
      do {
         if (i == index) {
            return tempLast;
         }
         i--;
         tempLast = tempLast.last;
      } while (tempLast != null);
      return null;

   }

   @Override
   public int indexOf(Object value) {
      Node nextNode = root;
      int i = 0;
      do {
         if (value.equals(nextNode.value)) {
            return i;
         }
         i++;
         nextNode = nextNode.next;
      } while (nextNode != null);
      return -1;
   }

   @Override
   public boolean isEmpty() {

      return size == 0;
   }

   @Override
   public int lastIndexOf(Object value) {
      Node lastNode = tail;
      int lastIndex = size - 1;
      do {
         if (value.equals(lastNode.value)) {
            return lastIndex;
         }
         lastIndex--;
         lastNode = lastNode.last;
      } while (lastNode != null);
      return -1;
   }

   @Override
   public T remove(int index) {
      checkIndex(index);
      Node remove = getNode(index);// 当前删除的Node
      Node father = remove.last;// 删除元素的上一个Node
      Node last = remove.next;// 删除元素的下一个Node

      if (root.equals(remove)) {// 删除的是头1：里面有两个以上元素，则头为下一个Node，并把上一个last赋为null。
         // 2：如果只存在一个元素，又要删除该元素，也是把头为下一个Node（此时下一个Node必然为null），因为删除的是该容器的最后一个元素，所以也把tail赋为null
         root = last;
         if (root != null) {
            root.last = null;
         }
         if ((size == 1) && (index == 0)) {
            tail = null;
         }
         size--;
         return remove.value;
      }

      if (tail.equals(remove)) {
         tail = father;
         tail.next = null;
         size--;
         return remove.value;
      }

      father.next = last;
      last.last = father;
      size--;
      return remove.value;
   }

   @Override
   public boolean remove(Object value) {
      Node nextNode = root;
      while (nextNode != null) {
         if (value.equals(nextNode.value)) {
            Node father = nextNode.last;
            Node last = nextNode.next;

            if (root.equals(nextNode)) {
               root = last;
               if (root != null) {
                  root.last = null;
               }
               if ((size == 1) && value.equals(root.value)) {
                  tail = null;
               }
               size--;
               return true;
            }

            if (tail.equals(nextNode)) {
               tail = father;
               tail.next = null;
               size--;
               return true;
            }

            father.next = last;
            last.last = father;
            size--;
            return true;
         }
         nextNode = nextNode.next;
      }
      return false;
   }

   @Override
   public boolean removeAll(Collection<?> collection) {
      for (Object object : collection) {
         remove(object);
      }
      return true;
   }

   @Override
   public T set(int index, T element) {
      Node setNode = getNode(index);
      T setValue = setNode.value;
      setNode.value = element;
      return setValue;
   }

   @Override
   public int size() {
      return size;
   }

   @Override
   public List<T> subList(int fromIndex, int toIndex) {
      if ((fromIndex < 0) || (fromIndex >= size) || (toIndex <= 0) || (toIndex > size)) {
         throw new IndexOutOfBoundsException();
      }
      Node fromNode = getNode(fromIndex);
      List<T> list = new ArrayList<>();

      while (fromNode != null) {
         if (fromIndex == toIndex) {
            break;
         }
         list.add(fromNode.value);
         fromNode = fromNode.next;
         fromIndex++;
      }
      return list;
   }

   @Override
   public Object[] toArray() {
      Object[] objects = subList(0, size).toArray();
      return objects;
   }

   public static void main(String[] args) {
      MyLinked<String> myLinked = new MyLinked<>();
      myLinked.add("1");
      myLinked.add("2");
      myLinked.add("3");
      myLinked.add("1");
      myLinked.remove("1");
      myLinked.add(-1, "a");
      System.out.println(myLinked);
      LinkedList<Object> linkedList = new LinkedList<>();
      linkedList.add("1");
      linkedList.add("2");
      linkedList.add("3");
      linkedList.add("1");
      linkedList.remove("1");
//      linkedList.add(-1, "a");
      System.out.println(linkedList);
   }
}
