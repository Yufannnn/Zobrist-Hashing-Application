import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    void testFindBalancedIntervalsCase1() {
        int[] a = new int[]{1, 1, 4, 5, 1, 4, 1, 9, 1, 9, 8, 1, 0};
        assertEquals(2, Main.findBalancedIntervals(a));
    }

    @Test
    void testFindBalancedIntervalsCase2() {
        int[] b = new int[]{1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1};
        assertEquals(0, Main.findBalancedIntervals(b));
    }

    @Test
    void testFindBalancedIntervalsCase3() {
        int[] c = new int[]{2, 2, 2, 2, 2, 2, 2};
        assertEquals(12, Main.findBalancedIntervals(c));
    }

    @Test
    void testFindBalancedIntervalsCase4() {
        int[] d = new int[]{3, 3, 1, 2, 2, 1};
        assertEquals(4, Main.findBalancedIntervals(d));
    }
}
