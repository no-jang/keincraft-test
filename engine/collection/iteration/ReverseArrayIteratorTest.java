package engine.collection.iteration;

import engine.collection.iteration.reverse.ReverseArrayIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ReverseArrayIteratorTest {
    private Object object1;
    private Object object2;
    private Iterator<Object> iterator;

    @BeforeEach
    public void beforeEach() {
        object1 = new Object();
        object2 = new Object();

        iterator = new ReverseArrayIterator<>(new Object[]{object1, object2}, 2);
    }

    @Test
    public void givenIteratorHasNext_whenNext_thenReturnNext() {
        assertThat(iterator.next()).isEqualTo(object2);
        assertThat(iterator.next()).isEqualTo(object1);
    }

    @Test
    public void givenIteratorHasNotNext_whenNext_thenThrowNoSuchElementException() {
        ReverseArrayIterator<Object> iterator = new ReverseArrayIterator<>(new Object[0], 0);

        assertThatThrownBy(iterator::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void givenIteratorHasNext_whenReset_thenBeginAtArraySize() {
        assertThat(iterator.next()).isEqualTo(object2);

        iterator.reset();

        assertThat(iterator.next()).isEqualTo(object2);
        assertThat(iterator.next()).isEqualTo(object1);
    }
}
