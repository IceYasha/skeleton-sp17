import static org.junit.Assert.*;
import org.junit.Test;

public class FilkTest {

    @Test
    public void testHorribleSteve() {
        int i = 0;
        for (int j = 0; i < 500; ++i, ++j) {
            if (!Flik.isSameNumber(i, j)) {
                //System.out.println(i);
                //System.out.println(j);
                assertTrue(Flik.isSameNumber(i, j));
                break;
            }
            assertTrue(Flik.isSameNumber(i, j));
        }
    }

    public static void main(String... args) {
        jh61b.junit.TestRunner.runTests("all", FilkTest.class);
    }
}
