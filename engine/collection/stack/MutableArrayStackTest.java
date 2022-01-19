package engine.collection.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MutableArrayStackTest {
    private MutableStack<Object> stack;

    @BeforeEach
    public void beforeEach() {
        stack = new MutableArrayStack.Builder<>(1)
                .build();
    }

    @Test
    public void whenPush_thenPushElementOnStack() {
        Object object1 = new Object();
        Object object2 = new Object();

        stack.push(object1);
        stack.push(object2);

        assertThat(stack.size()).isEqualTo(2);

        assertThat(stack.pop()).isEqualTo(object2);
        assertThat(stack.pop()).isEqualTo(object1);
    }

    @Test
    public void givenEmptyStack_whenPop_thenThrowNoSuchElementException() {
        assertThat(stack.isEmpty()).isTrue();
        assertThatThrownBy(() -> stack.pop())
                .isInstanceOf(NoSuchElementException.class);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void whenToMutable_thenReturnThis() {
        ArrayStack<Object> stack2 = (ArrayStack<Object>) stack;

        assertThat(stack2.toMutable()).isEqualTo(stack);
    }
}
