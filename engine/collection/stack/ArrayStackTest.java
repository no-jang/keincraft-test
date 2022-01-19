package engine.collection.stack;

import engine.collection.iteration.Iterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ArrayStackTest {
    private Object object1;
    private Object object2;
    private Stack<Object> stack;

    @BeforeEach
    public void beforeEach() {
        object1 = new Object();
        object2 = new Object();

        stack = new ArrayStack.Builder<>(2)
                .add(object1)
                .add(object2)
                .build();
    }

    @Test
    public void givenNonEmptyArrayStack_whenGetElements_thenReturnHeadOfStack() {
        assertThat(stack.size()).isEqualTo(2);
        assertThat(stack.get(0)).isEqualTo(object1);
        assertThat(stack.get(1)).isEqualTo(object2);
        assertThat(stack.getTail()).isEqualTo(object1);
        assertThat(stack.getHead()).isEqualTo(object2);
    }

    @Test
    public void givenEmptyArrayStack_whenGetElements_thenThrowNoSuchElementException() {
        ArrayStack<Object> stack = new ArrayStack<>();

        assertThatThrownBy(stack::getHead)
                .isInstanceOf(NoSuchElementException.class);

        assertThatThrownBy(stack::getTail)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void givenNegativeIndex_whenGetOrNull_thenThrowIllegalArgumentException() {
        assertThatThrownBy(() -> stack.getOrNull(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void givenIndexHigherThanSize_whenGetOrNull_thenThrowIllegalArgumentException() {
        assertThat(stack.getOrNull(1)).isEqualTo(object2);

        assertThatThrownBy(() -> stack.getOrNull(2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void givenElementInStack_whenIndexOf_thenReturnElementIndex() {
        assertThat(stack.indexOf(object1)).isEqualTo(0);
        assertThat(stack.indexOf(object2)).isEqualTo(1);
    }

    @Test
    public void givenNoElementInStack_whenIndexOf_thenReturnMinusOne() {
        assertThat(stack.indexOf(new Object())).isEqualTo(-1);
    }

    @Test
    public void whenIterator_thenIterateCorrectly() {
        Iterator<Object> iterator = stack.iterator();

        assertThat(iterator.next()).isEqualTo(object1);
        assertThat(iterator.next()).isEqualTo(object2);
    }

    @Test
    public void whenReverseIterator_thenIterateCorrectly() {
        Iterator<Object> iterator = stack.reverseIterator();

        assertThat(iterator.next()).isEqualTo(object2);
        assertThat(iterator.next()).isEqualTo(object1);
    }

    @Test
    public void whenToMutable_thenCreateNewMutableArrayStack() {
        assertThat(stack.toMutable()).isNotEqualTo(stack);
    }
}
