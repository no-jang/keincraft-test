package engine.collection.iteration;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ArrayIteratorTest {

    @Test
    public void givenIteratorHasNext_whenNext_thenReturnNext() {
        Object object1 = new Object();
        Object object2 = new Object();
        Object[] objects = new Object[]{object1, object2};

        ArrayIterator<Object> iterator = new ArrayIterator<>(objects, 2);

        assertThat(iterator.next()).isEqualTo(object1);
        assertThat(iterator.next()).isEqualTo(object2);
    }

    @Test
    public void givenIteratorHasNotNext_whenNext_thenThrowNoSuchElementException() {
        ArrayIterator<Object> iterator = new ArrayIterator<>(new Object[0], 0);

        assertThatThrownBy(iterator::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void givenIteratorHasNext_whenReset_thenBeginAtIndexZero() {
        Object object1 = new Object();
        Object object2 = new Object();
        Object[] objects = new Object[]{object1, object2};

        ArrayIterator<Object> iterator = new ArrayIterator<>(objects, 2);

        assertThat(iterator.next()).isEqualTo(object1);

        iterator.reset();

        assertThat(iterator.next()).isEqualTo(object1);
        assertThat(iterator.next()).isEqualTo(object2);
    }
}
