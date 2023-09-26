import java.util.ArrayList;
import java.util.List;

public class Main {

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
	 * each philosopher except for the last tries to pick the
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

				newPhilosopher = new Philosopher(chopsticks.get(0), chopsticks.get(i));
			} else {
				newPhilosopher = new Philosopher(chopsticks.get(i), chopsticks.get(i + 1));
			}
			Thread philosopherThread = new Thread(newPhilosopher);
			philosopherThread.start();
		}
	}
}
