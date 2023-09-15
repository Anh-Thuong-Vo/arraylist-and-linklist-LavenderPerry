import java.lang.AssertionError;


public class MyLinkedList <T> {
	class Node {
    T data;
    Node next;
		
		Node(T data) {
      this.data = data;
		}
	}

  private Node head;
  private Node tail;
  private int size = 0;
	/**
	 * Construct an MyLinkedList.
	 */
	public MyLinkedList() {
	}

	/**
	 * Return the number of elements in the MyLinkedList.
	 *
	 * @return The number of elements in the MyLinkedList.
	 */
	public int size() {
		return size;
	}

	/**
	 * Add an element to the end of the MyLinkedList.
	 *
	 * @param element The element to add.
	 */
	public void add(T element) {
    size++;

		if (tail == null) {
      tail = new Node(element);
      head = tail;
      return;
    }

    tail.next = new Node(element);
    tail = tail.next;
	}

	/**
	 * Get the element at the specified index.
	 *
	 * This function assumes that the index argument is within range of the MyLinkedList.
	 *
	 * @param index The index to get.
	 * @return The element at the specified index.
	 */
	public T get(int index) {
    return getNode(index).data;
	}

	/**
	 * Remove the element at the specified index.
	 *
	 * This function assumes that the index argument is within range of the MyLinkedList.
	 *
	 * @param index The index to remove.
	 */
	public void remove(int index) {
    if (size == 0) {
      return;
    }

    size--;

    if (index == 0) {
      if (size == 0) {
        head = null;
        tail = null;
        return;
      }

      head = head.next;
      return;
    }

    Node prev = getNode(index - 1);

    if (index == size) {
      prev.next = null;
      tail = prev;
      return;
    }

    prev.next = getNode(index + 1);
	}

	/**
	 * Create a String representation of the MyLinkedList.
	 *
	 * @return A String representation of the MyLinkedList.
	 */
	public String toString() {
		String result = "{";
		if (this.size() > 0) {
			result += this.get(0);
		}
		for (int i = 1; i < this.size; i++) {
			result += ", " + this.get(i);
		}
		result += "}";
		return result;
	}

	/**
	 * Check that an MyLinkedList contains the same elements as an int array.
	 *
	 * If the list and the array are not the same, throw an AssertionError.
	 *
	 * @param list The MyLinkedList to check.
	 * @param answer The expected answer, in the form of an int array.
	 */
	public static void assertArraysEqual(MyLinkedList list, int[] answer) {
		if (list.size() != answer.length) {
			throw new AssertionError("Expected list of length " + answer.length + " but got " + list.size());
		}
		for (int i = 0; i < answer.length; i++) {
			if ((Integer)list.get(i) != answer[i]) {
				throw new AssertionError("Expected " + answer[i] + " but got " + list.get(i) + " at index " + i);
			}
		}
	}

	/*
	 * Test that the empty arraylist has size 0.
	 */
	public static void test1() {
		MyLinkedList<Integer> list = new MyLinkedList();
		int[] answer = new int[0];
		assertArraysEqual(list, answer);
	}

	/*
	 * Test insertion into an arraylist (without resizing).
	 */
	public static void test2() {
		MyLinkedList list = new MyLinkedList();
		for (int i = 0; i < 3; i++) {
			list.add(i * i);
		}
		int[] answer = {0, 1, 4};
		assertArraysEqual(list, answer);
	}

	/*
	 * Test deletion from an arraylist without emptying it.
	 */
	public static void test3() {
		MyLinkedList <Integer> list = new MyLinkedList();
		for (int i = 0; i < 5; i++) {
			list.add(i * i);
		}
		list.remove(1);
		list.remove(2);
		int[] answer = {0, 4, 16};
		MyLinkedList.assertArraysEqual(list, answer);
	}

	/*
	 * Test deletion from an arraylist and emptying it.
	 */
	public static void test4() {
		MyLinkedList <Integer> list = new MyLinkedList();
		for (int i = 0; i < 5; i++) {
			list.add(i * i);
		}

		list.remove(1);
		list.remove(2);

		// delete the final remaining numbers
		list.remove(0);
		list.remove(0);
		list.remove(0);
		int[] answer1 = {};
		MyLinkedList.assertArraysEqual(list, answer1);

		// check that there are no last-element issues
		for (int i = 0; i < 5; i++) {
			list.add(i * i);
		}
		list.remove(4);
		list.add(-1);
		int[] answer2 = {0, 1, 4, 9, -1};
		MyLinkedList.assertArraysEqual(list, answer2);
	}

	/*
	 * Test insertion into an arraylist (with resizing).
	 */
	public static void test5() {
		MyLinkedList <Integer> list = new MyLinkedList();
		for (int i = 0; i < 12; i++) {
			list.add(i * i);
		}
		int[] answer = {0, 1, 4, 9, 16, 25, 36, 49, 64, 81, 100, 121};
		MyLinkedList.assertArraysEqual(list, answer);
	}

	/**
	 * Put the MyLinkedList through some simple tests.
	 *
	 * @param args Ignored command line arguments.
	 */
	public static void main(String[] args) {
		test1();
		test2();
		test3();
		test4();
		test5();

		System.out.println("pass");
	}

  private Node getNode(int index) {
    Node currentNode = head;
    for (int i = 0; i < index; i++) {
      currentNode = currentNode.next;
    }
    return currentNode;
  }
}
