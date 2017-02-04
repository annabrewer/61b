/** Performs some basic linked list tests. */
public class ArrayDequeTest {
	
	/* Utility method for printing out empty checks. */
	public static boolean checkEmpty(boolean expected, boolean actual) {
		if (expected != actual) {
			System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Utility method for printing out empty checks. */
	public static boolean checkSize(int expected, int actual) {
		if (expected != actual) {
			System.out.println("size() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Prints a nice message based on whether a test passed. 
	 * The \n means newline. */
	public static void printTestStatus(boolean passed) {
		if (passed) {
			System.out.println("Test passed!\n");
		} else {
			System.out.println("Test failed!\n");
		}
	}

	/** Adds a few things to the list, checking isEmpty() and size() are correct, 
	  * finally printing the results. 
	  *
	  * && is the "and" operation. */
	public static void addIsEmptySizeTest() {
		System.out.println("Running add/isEmpty/Size test.");
		//System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
		
		ArrayDeque<String> lld1 = new ArrayDeque<String>();

		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst("front");
		
		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
		passed = checkSize(1, lld1.size()) && passed;
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		int counter = 2;
		while (counter < 9) {
			lld1.addLast("#" + counter);
			passed = checkSize(counter, lld1.size()) && passed;
			counter += 1;
		}

		lld1.addLast("back");
		passed = checkSize(counter, lld1.size()) && passed;



		System.out.println("size = " + counter);
		System.out.println("Printing out deque: ");
		//lld1.printDeque();

		printTestStatus(passed);
	}

	/** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
	public static void addRemoveTest() {

		System.out.println("Running add/remove test.");

		//System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
		
		ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
		// should be empty 
		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addLast(0);
        System.out.println("one:" + lld1.removeLast());
        lld1.addLast(2);
        lld1.addFirst(3);
        System.out.println("two:" + lld1.removeFirst());
        lld1.addFirst(5);
        System.out.println("three:" + lld1.removeFirst());
        System.out.println("four:" + lld1.removeLast());

        lld1.printDeque();
		
		// should not be empty 
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.removeFirst();
		lld1.removeLast();
		// should be empty 
		passed = checkEmpty(true, lld1.isEmpty()) && passed;

		printTestStatus(passed);
	}

	public static void integerTest() {
		System.out.println("Running int tests.\n");
		ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
		int counter = 0;
		while (counter < 10) {
			lld1.addLast(counter);
			counter += 1;
		}
		System.out.println(lld1.get(0));
	}

	public static void main(String[] args) {
		System.out.println("Running tests.\n");
		/*addIsEmptySizeTest();
		integerTest();
		addRemoveTest();
		storageTest();*/
		//testing123();
		//testingremoval();
		testAgain();

	}

	public static void testAgain() {
		ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
		lld1.addFirst(0);
		lld1.printDeque();
		System.out.println("----------");

        lld1.removeFirst();
        lld1.printDeque();
        System.out.println("----------");

        lld1.addLast(2);
        lld1.printDeque();
        System.out.println("----------");

        lld1.addLast(3);
        lld1.printDeque();
        System.out.println("----------");
        
        lld1.get(1);
        lld1.printDeque();
        System.out.println("----------");
	}

	public static void testing123() {
		ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
		int counter = 0;
		while (counter < 30) {
			lld1.addFirst(counter);
			counter += 1;
		}
		lld1.printDeque();
	}

	public static void testingremoval() {
		ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
		int counter = 0;
		lld1.addFirst(0);
		lld1.addFirst(1);
		lld1.addFirst(2);
		lld1.addFirst(3);
		lld1.removeFirst();
		lld1.printDeque();
	}

	public static void storageTest() {
		ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
		int counter = 0;
		while (counter < 30) {
			lld1.addFirst(counter);
			counter += 1;
		}
		while (counter > 3) {
			lld1.removeLast();
			counter -= 1;
		}
		//lld1.printDeque();
	}
}