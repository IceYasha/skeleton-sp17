import static org.junit.Assert.*;
import org.junit.Test;


public class TestArrayDeque1B {

    /**  Modify StudentDequeLauncher.java. */
    @Test
    public void testAdd() {
        ArrayDeque<Integer> sad1 = new ArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();

        OperationSequence fs = new OperationSequence();

        for (Integer i = 0; i < 20; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                sad1.addFirst(i); ads1.addFirst(i);
                DequeOperation dequeOp1 = new DequeOperation("addFirst", i);
                fs.addOperation(dequeOp1);

                Integer a = sad1.get(i);
                DequeOperation dequeOp2 = new DequeOperation("get", i);
                fs.addOperation(dequeOp2);

                assertEquals(fs.toString(), ads1.get(i), a);
                assertEquals(ads1.size(), sad1.size());
            } else {
                sad1.addLast(i); ads1.addLast(i);
                DequeOperation dequeOp1 = new DequeOperation("addLast", i);
                fs.addOperation(dequeOp1);

                Integer a = sad1.get(i);
                DequeOperation dequeOp2 = new DequeOperation("get", i);
                fs.addOperation(dequeOp2);

                assertEquals(fs.toString(), ads1.get(i), a);
                assertEquals(ads1.size(), sad1.size());
            }

            assertEquals(ads1.size(), sad1.size());
            for(int j = 0; j < ads1.size(); j++) {
                Integer a = sad1.get(j); Integer b = ads1.get(j);

                DequeOperation dequeOp3 = new DequeOperation("get", j);
                fs.addOperation(dequeOp3);

                assertEquals(fs.toString(), b, a);
            }
        }
    }

    @Test
    public void testRemove() {
        ArrayDeque<Integer> sad1 = new ArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();

        OperationSequence fs = new OperationSequence();

        for (Integer i = 0; i < 100; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                sad1.addFirst(i);
                ads1.addFirst(i);
                DequeOperation dequeOp = new DequeOperation("addFirst", i);
                fs.addOperation(dequeOp);
            } else {
                sad1.addLast(i);
                ads1.addLast(i);
                DequeOperation dequeOp = new DequeOperation("addLast", i);
                fs.addOperation(dequeOp);
            }
        }

        for (Integer i = 0; i < 30; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                Integer a = sad1.removeFirst();
                DequeOperation dequeOp = new DequeOperation("removeFirst");
                fs.addOperation(dequeOp);

                assertEquals(fs.toString(), ads1.removeFirst(), a);
                assertEquals(ads1.size(), sad1.size());
            } else {
                Integer a = sad1.removeLast();
                DequeOperation dequeOp = new DequeOperation("removeLast");
                fs.addOperation(dequeOp);

                assertEquals(fs.toString(), ads1.removeLast(), a);
                assertEquals(ads1.size(), sad1.size());
            }

            assertEquals(ads1.size(), sad1.size());
            for(int j = 0; j < ads1.size(); j++) {
                assertEquals(ads1.get(i), sad1.get(i));
            }
        }
    }

    @Test
    public void testGet() {
        ArrayDeque<Integer> sad1 = new ArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();

        OperationSequence fs = new OperationSequence();

        for (Integer i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                sad1.addFirst(i);
                ads1.addFirst(i);
            } else {
                sad1.addLast(i);
                ads1.addLast(i);
            }

            for(int j = 0; j < ads1.size(); j++) {
                assertEquals(ads1.getFirst(), sad1.getFirst());
                assertEquals(ads1.getLast(), sad1.getLast());
            }
        }
    }


    public static void main(String... args) {
        jh61b.junit.TestRunner.runTests("all", TestArrayDeque1B.class);
    }
}
