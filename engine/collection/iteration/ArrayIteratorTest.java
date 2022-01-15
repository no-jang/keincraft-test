package engine.collection.iteration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ArrayIteratorTest {
    private Object object1;
    private Object object2;
    private Iterator<Object> iterator;

    @BeforeEach
    public void beforeEach() {
        object1 = new Object();
        object2 = new Object();

        iterator = new ArrayIterator<>(new Object[]{object1, object2}, 2);
    }

    @Test
    public void givenIteratorHasNext_whenNext_thenReturnNext() {
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
        assertThat(iterator.next()).isEqualTo(object1);

        iterator.reset();

        assertThat(iterator.next()).isEqualTo(object1);
        assertThat(iterator.next()).isEqualTo(object2);
    }
}
