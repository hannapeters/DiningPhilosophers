import java.util.ArrayList;
import java.util.List;

public class MainB {

	/*
	 * Philosopher class takes in left and right chopstick, each thread
	 * (representing a philosopher)
	 * first syncronizes on the first input argument (which we call firstChopstick),
	 * representing
	 * the chopstick that is picked up first, and then does the same for the second
	 * chopstick
	 */
	public static class Philosopher implements Runnable {
		Object firstChopstick;
		Object secondChopstick;

		public Philosopher(Object firstChopstick, Object secondChopstick) {
			this.firstChopstick = firstChopstick;
			this.secondChopstick = secondChopstick;
		}

		public synchronized void run() {
			while (true) {
				synchronized (firstChopstick) {
					System.out.println(Thread.currentThread().getName() + " picked up first chopstick");
					synchronized (secondChopstick) {
						System.out.println(Thread.currentThread().getName() + " picked up second chopstick");
						System.out.println(Thread.currentThread().getName() + " started eating");
					}
					System.out.println(Thread.currentThread().getName() + " put down second chopstick");
				}
				System.out.println(Thread.currentThread().getName() + " put down first chopstick");
				System.out.println(Thread.currentThread().getName() + " started thinking");
			}
		}
	}

	/*
	 * DiningPhilosophers class creates numberOfPhilosophers number of philosophers
	 * and
	 * delegates a left and right chopstick to them depending on the algorithm used
	 * For the A assignment, each philosopher tries to "pick up" the chopstick to
	 * its left followed
	 * by the chopstick to its right resulting in a deadlock
	 * For the B assingment, each philosopher except for the last tries to pick the
	 * chopstick to its left
	 * the last tries to pick up the chopstick to its right instead
	 */
	public static void main(String[] args) {
		int numberOfPhilosophers = 10;
		List<Object> chopsticks = new ArrayList<Object>();
		for (int i = 0; i < numberOfPhilosophers; i++) {
			chopsticks.add(new Object());
		}

		for (int i = 0; i < numberOfPhilosophers; i++) {
			Philosopher newPhilosopher;
			if (i == numberOfPhilosophers - 1) {
				// Code for 6B, last philosopers first picks up the right chopstick, then the
				// left
				newPhilosopher = new Philosopher(chopsticks.get(0), chopsticks.get(i));
			} else {
				// the algorithm for remaining philosopers are the same for 6A and 6B
				// they each first pick up the left, then the right chopstick
				newPhilosopher = new Philosopher(chopsticks.get(i), chopsticks.get(i + 1));
			}
			Thread philosopherThread = new Thread(newPhilosopher);
			philosopherThread.start();
		}
	}
}
