import java.util.NoSuchElementException;

// import java.util.*;

/**
 * CS312 Assignment 12.
 *
 * On MY honor, <JIAMING JI>, this programming assignment is MY own work
 * and I have not provided this code to any other student.
 *
 * Student name: jiaming ji
 * UTEID: jj35553
 * email address: jiamingji988@gmail.com
 * Grader name: OMER
 * Number of slip days used on this assignment: 0 
 * 
 */

public class RingBuffer {

	// fields and constants goes here
	private int size;
	private double[] ringBuffer;
	private int maxCapacity;
	private int last;
	private int first;

	// create a constructor for the RingBuffer class
	public RingBuffer(int capacity) {
		size = 0;
		first = 0;
		last = first;
		maxCapacity = capacity;
		ringBuffer = new double[capacity];
	}

	// return number of items currently in the buffer
	public int size() {
		return size;
	}

	// is the buffer empty (size equals zero)?
	public boolean isEmpty() {
		return size == 0;
	}

	// is the buffer full  (size equals capacity)?
	public boolean isFull() {
		return size == maxCapacity;
	}

	// add item x to the end (as long as the buffer is not full)
	public void enqueue(double x) {
		if (isFull()) {
			throw new IllegalStateException();
		}
		ringBuffer[last] = x;
		size++;
		last++;
		if(last > maxCapacity-1) {
			last = 0;
		}
	}

	// delete and return item from the front (as long as the buffer is not empty)
	public double dequeue() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		double x = ringBuffer[first];
		first ++;
		if(first == maxCapacity) {
			first = 0;
		}

		size --;
		return x;
	}

	// return (but do not delete) item from the front of the buffer
	public double peek() {
		if(isEmpty())
			throw new NoSuchElementException();
		return ringBuffer[first];
	}

	// override toString. Return a String of the form [front, next, next, last] 
	public String toString() {
		if(isEmpty()) {
			return "[]";
		}
		String st = "[" + ringBuffer[first];
		int index = first + 1;
		for(int i = 1; i < size; i ++) {
			if(index > maxCapacity - 1) {
				index = 0;
			}
			st += ", " + ringBuffer[index];
			index++;
		}
		return st + "]";
	}
}
