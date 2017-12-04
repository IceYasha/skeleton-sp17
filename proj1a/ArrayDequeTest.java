
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
//		System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<String> ald1 = new ArrayDeque<>();

        boolean passed = checkEmpty(true, ald1.isEmpty());

        ald1.addFirst("a");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, ald1.size()) && passed;
        passed = checkEmpty(false, ald1.isEmpty()) && passed;

        ald1.addLast("b");
        passed = checkSize(2, ald1.size()) && passed;

        ald1.addLast("c");
        passed = checkSize(3, ald1.size()) && passed;

        ald1.addLast("d");
        passed = checkSize(4, ald1.size()) && passed;
        ald1.addLast("e");
        passed = checkSize(5, ald1.size()) && passed;
        ald1.addLast("f");
        passed = checkSize(6, ald1.size()) && passed;
        ald1.addLast("g");
        passed = checkSize(7, ald1.size()) && passed;

        ald1.addFirst("h");
        passed = checkSize(8, ald1.size()) && passed;

        ald1.addFirst("i");
        passed = checkSize(9, ald1.size()) && passed;

        System.out.println("Printing out deque: ");
        ald1.printDeque();

        printTestStatus(passed);
    }

    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public static void addRemoveTest() {

        System.out.println("Running add/remove test.");

        boolean passed = true;

        ArrayDeque<Integer> ald1 = new ArrayDeque<>();

        passed = checkEmpty(true, ald1.isEmpty());

        ald1.addFirst(1);
        passed = checkSize(1, ald1.size()) && passed;

        passed = (1 == ald1.removeFirst()) && passed;
        passed = checkSize(0, ald1.size()) && passed;

        ald1.addLast(2);
        passed = checkSize(1, ald1.size()) && passed;

        passed = (2 == ald1.removeLast()) && passed;
        passed = checkSize(0, ald1.size()) && passed;


        ald1.addLast(1);
        ald1.addLast(2);
        passed = (1 == ald1.removeFirst()) && passed;
        passed = (2 == ald1.removeFirst()) && passed;

        ald1.addLast(1);
        ald1.addLast(2);
        passed = (2 == ald1.removeLast()) && passed;
        passed = (1 == ald1.removeFirst()) && passed;


        ArrayDeque<Integer> ald2 = new ArrayDeque<>();

        ald2.addLast(1);
        ald2.addLast(2);
        ald2.addLast(3);
        ald2.addLast(4);
        ald2.addLast(5);
        ald2.addLast(6);
        ald2.addLast(7);
        ald2.addLast(8);
        ald2.addLast(9);
        ald2.addLast(10);
        ald2.addLast(11);
        ald2.addLast(12);

        passed = (1 == ald2.removeFirst()) && passed;
        passed = (12 == ald2.removeLast()) && passed;
        passed = (2 == ald2.removeFirst()) && passed;
        passed = (11 == ald2.removeLast()) && passed;
        passed = (3 == ald2.removeFirst()) && passed;
        passed = (10 == ald2.removeLast()) && passed;
        passed = (4 == ald2.removeFirst()) && passed;
        passed = (9 == ald2.removeLast()) && passed;
        passed = (5 == ald2.removeFirst()) && passed;

        ArrayDeque<Integer> ald3 = new ArrayDeque<>();

        ald3.addLast(1);
        ald3.addLast(2);
        ald3.addLast(3);
        ald3.addLast(4);
        ald3.addLast(5);
        ald3.addLast(6);
        ald3.addLast(7);
        ald3.addLast(8);
        ald3.addLast(9);
        ald3.addLast(10);
        ald3.addLast(11);
        ald3.addLast(12);

        passed = (12 == ald3.removeLast()) && passed;
        passed = (11 == ald3.removeLast()) && passed;
        passed = (10 == ald3.removeLast()) && passed;
        passed = (9 == ald3.removeLast()) && passed;
        passed = (8 == ald3.removeLast()) && passed;
        passed = (7 == ald3.removeLast()) && passed;
        passed = (6 == ald3.removeLast()) && passed;
        passed = (5 == ald3.removeLast()) && passed;
        passed = (4 == ald3.removeLast()) && passed;

//        lld1.addFirst(9);
//        lld1.addFirst(8);
//        lld1.addFirst(7);
//        passed = checkSize(3, lld1.size()) && passed;
//
//        passed = (9 == lld1.removeLast()) && passed;
//        passed = checkSize(2, lld1.size()) && passed;
//
//        passed = (8 == lld1.removeLast()) && passed;
//        lld1.removeLast();
//        passed = checkEmpty(true, lld1.isEmpty()) && passed;

        System.out.println("Printing out deque: ");
//        ald1.printDeque();

//        ald2.printDeque();

        ald3.printDeque();

        printTestStatus(passed);

    }

    public static void addGetTest() {
        System.out.println("Running add/remove test.");

        ArrayDeque<Integer> ald1 = new ArrayDeque<>();

        boolean passed = checkEmpty(true, ald1.isEmpty());

        ald1.addFirst(1);

        passed = (1 == ald1.get(4)) && passed;

        ald1.addFirst(10);
        ald1.addFirst(9);
        ald1.addFirst(8);
        ald1.addFirst(7);

        passed = (7 == ald1.get(0)) && passed;

        System.out.println("Printing out deque: ");
        ald1.printDeque();

        printTestStatus(passed);
    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        addIsEmptySizeTest();
        addRemoveTest();
        addGetTest();
    }
}
