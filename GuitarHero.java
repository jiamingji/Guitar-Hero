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

public class GuitarHero {
	
	// fields and constants
	public static final int NOTES = 37;
	final static double TEXT_POS_X = .5;
	final static double TEXT_POS_Y = .5;

	// main method that plays the guitar
	public static void main(String[] args) {
		String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
		GuitarString[] guitarString = new GuitarString[NOTES];
		for(int i = 0; i < NOTES; i++) {
			double freq =  440 * Math.pow(1.05956, i - 24);
			guitarString[i] = new GuitarString(freq);	
		}
		StdDraw.text(TEXT_POS_X, TEXT_POS_Y, "Type " + keyboard + " to play a note!");
		play(keyboard, guitarString);
	}

	// method that plays the guitar by calculating the superposition 
	private static void play(String keyboard, GuitarString[] guitarString) {
		while (true) {
			if (StdDraw.hasNextKeyTyped()) {
				char key = StdDraw.nextKeyTyped();
				if(keyboard.indexOf(key) != -1) {
					int index = keyboard.indexOf(key);
					guitarString[index].pluck();
				}
			}
			double sample = 0.0;
			for(int i = 0; i < NOTES; i++) {
				sample += guitarString[i].sample();
			}
			StdAudio.play(sample);
			// advance the simulation of each guitar string by one step
			for(int i = 0; i < NOTES; i++) {
				guitarString[i].tic();
			}
		}
	}
}
