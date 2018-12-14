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
public class GuitarString{

	// fields and constants
	private static int samplingRate = 44100;
	private static double energyDecayFac = 0.994;
	private RingBuffer guitarString;
	private int size;
	private int time;

	// creates a RingBuffer of the desired capacity N
	public GuitarString(double frequency) {
		int capacity = (int)Math.round(samplingRate / frequency);
		guitarString = new RingBuffer(capacity);
		size = capacity;
		for(int i = 0; i < size; i++) {
			guitarString.enqueue(0.0);
		}
	}

	// creates a RingBuffer of capacity equal to the size of the array, 
	// and initializes the contents of the buffer to the values in the array.
	public GuitarString(double[] init) {
		size = init.length;
		guitarString = new RingBuffer(size);
		for(int index = 0; index < size; index++) {
			guitarString.enqueue(init[index]);
		}
	}

	// set the buffer to white noise
	// Replace the N items in the ring buffer with N random values between -0.5 and +0.5. 
	public void pluck() {
		for(int i = 0; i < size; i++) {
			double x = Math.random() - 0.5;
			guitarString.dequeue();
			guitarString.enqueue(x);
		}
	}

	// delete the sample at the front of the ring buffer and add to the end of the ring buffer 
	// the average of the first two samples, multiplied by the energy decay factor. 
	public void tic() {
		double first = guitarString.dequeue();
		double second = guitarString.peek();
		double newDouble = energyDecayFac * 0.5 * (first + second);
		guitarString.enqueue(newDouble);
		time++;
	}

	// Return the value of the item at the front of the ring buffer. 
	public double sample() {
		return guitarString.peek();
	}

	// Return the total number of times tic() was called.
	public int time() {
		return time;
	}
}
