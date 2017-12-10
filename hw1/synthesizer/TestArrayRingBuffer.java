package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        assertEquals(1, (int)arb.dequeue());
        arb.enqueue(4);
        arb.enqueue(5);
        arb.enqueue(6);
        arb.enqueue(7);
        arb.enqueue(8);
        arb.enqueue(9);
        arb.enqueue(10);
        arb.enqueue(11);
        assertEquals((Integer) 2, arb.peek());

        assertEquals((Integer) 2, arb.dequeue());
        assertEquals((Integer) 3, arb.dequeue());
        arb.enqueue(12);
        arb.enqueue(13);
        assertEquals((Integer) 4, arb.dequeue());
        assertEquals((Integer) 5, arb.dequeue());

        for (int x : arb) {
            System.out.println("x: " + x);
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
