package engine.collection.stack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ArrayStackTest {
    @Test
    public void Push_Should_ThrowIllegalArgumentException_When_ElementIsNull() {
        ArrayStackMock stack = new ArrayStackMock(0);
        assertThatThrownBy(() -> stack.push(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void Push_Should_PushElementOnStack_When_ElementNonNull() {
        ArrayStackMock stack = new ArrayStackMock(1);
        Object object = new Object();
        Object object2 = new Object();

        stack.push(object);
        stack.push(object2);

        assertThat(stack.size()).isEqualTo(2);
        assertThat(stack).containsExactly(object, object2);
    }

    @Test
    public void PopOrNull_Should_ReturnNull_When_StackIsEmpty() {
        ArrayStackMock stack = new ArrayStackMock(0);
        assertThat(stack.popOrNull()).isEqualTo(null);
    }

    @Test
    public void PopOrNull_Should_PopElementFromStack() {
        Object object = new Object();
        Object object2 = new Object();
        ArrayStackMock stack = new ArrayStackMock(new Object[]{object, object2}, 2);

        assertThat(stack.popOrNull()).isEqualTo(object2);
        assertThat(stack.popOrNull()).isEqualTo(object);
    }

    @Test
    public void headOrNull_Should_ReturnNull_When_StackIsEmpty() {
        ArrayStackMock stack = new ArrayStackMock(0);
        assertThat(stack.headOrNull()).isEqualTo(null);
    }

    @Test
    public void headOrNull_Should_ReturnHead() {
        Object object = new Object();
        Object object2 = new Object();
        ArrayStackMock stack = new ArrayStackMock(new Object[]{object, object2}, 2);

        assertThat(stack.headOrNull()).isEqualTo(object2);
    }

    @Test
    public void clear_Should_ClearStack() {
        Object object = new Object();
        Object object2 = new Object();
        ArrayStackMock stack = new ArrayStackMock(new Object[]{object, object2}, 2);

        stack.clear();

        assertThat(stack.getArray()).isEmpty();
    }

    public static class ArrayStackMock extends ArrayStack<Object> {
        public ArrayStackMock() {
            this(0);
        }

        public ArrayStackMock(int defaultCapacity) {
            super(defaultCapacity);
        }

        public ArrayStackMock(Object[] array, int size) {
            this.array = array;
            this.size = size;
        }

        public Object[] getArray() {
            return array;
        }
    }
}
