package engine.collection.util;

import org.junit.jupiter.api.Test;

public class ArraysTest {

    @Test
    public void testEnsureCapacityAtIndex() {
        Object[] array = new Object[]{
                new Object(),
                new Object()
        };

        array = Arrays.ensureCapacityAtIndex(array, 2, 2);
        array[2] = new Object();
    }
}
