package engine.collection.list;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ArrayListTest {

    @Test
    public void indexOf_Should_ReturnMinusOne_When_ElementIsNull() {
        ArrayListMock list = new ArrayListMock();

        assertThat(list.indexOf(null)).isEqualTo(-1);
    }

    @Test
    public void indexOf_Should_ReturnMinusOne_When_ElementNotFound() {
        ArrayListMock list = new ArrayListMock(new Object[]{
                new Object(),
                new Object()
        }, 2);

        assertThat(list.indexOf(new Object())).isEqualTo(-1);
    }

    @Test
    public void indexOf_Should_ReturnElement() {
        Object object = new Object();
        ArrayListMock list = new ArrayListMock(new Object[]{
                new Object(),
                object
        }, 2);

        assertThat(list.indexOf(object)).isEqualTo(1);
    }

    @Test
    public void getOrNull_Should_ThrowIllegalArgumentException_When_IndexIsNegative() {
        ArrayListMock list = new ArrayListMock();

        assertThatThrownBy(() -> list.getOrNull(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void getOrNull_Should_ReturnNull_When_IsEmpty() {
        ArrayListMock list = new ArrayListMock();

        assertThat(list.getOrNull(1)).isEqualTo(null);
    }

    @Test
    public void getOrNull_Should_ReturnNull_When_IndexIsOutOfRange() {
        ArrayListMock list = new ArrayListMock(new Object[]{new Object()}, 1);

        assertThat(list.getOrNull(1)).isEqualTo(null);
    }

    @Test
    public void getOrNull_Should_ReturnElementAtIndex() {
        Object object = new Object();
        Object object2 = new Object();
        ArrayListMock list = new ArrayListMock(new Object[]{
                object,
                object2
        }, 2);

        assertThat(list.getOrNull(0)).isEqualTo(object);
        assertThat(list.getOrNull(1)).isEqualTo(object2);
    }

    @Test
    public void tailOrNull_Should_ReturnNull_When_IsEmpty() {
        ArrayListMock list = new ArrayListMock();

        assertThat(list.tailOrNull()).isEqualTo(null);
    }

    @Test
    public void tailOrNull_Should_ReturnHead() {
        Object object = new Object();
        ArrayListMock list = new ArrayListMock(new Object[]{object}, 1);

        assertThat(list.tailOrNull()).isEqualTo(object);
    }

    @Test
    public void set_Should_ThrowIllegalArgumentException_When_IndexIsNegative() {
        ArrayListMock list = new ArrayListMock();

        assertThatThrownBy(() -> list.set(-1, new Object()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void set_Should_ThrowIllegalArgumentException_When_ElementIsNull() {
        ArrayListMock list = new ArrayListMock();

        assertThatThrownBy(() -> list.set(0, null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void set_Should_ThrowIllegalArgumentException_When_IndexIsMoreThanSize() {
        ArrayListMock list = new ArrayListMock(new Object[]{new Object()}, 1);

        list.set(1, new Object());
        assertThatThrownBy(() -> list.set(3, new Object()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void set_Should_SetElement() {
        Object object = new Object();
        Object object2 = new Object();
        Object object3 = new Object();
        ArrayListMock list = new ArrayListMock(1);

        assertThat(list.set(0, object)).isEqualTo(null);
        assertThat(list.set(0, object2)).isEqualTo(object);
        assertThat(list.set(1, object3)).isEqualTo(null);

        assertThat(list.getArray()).containsExactly(object2, object3);
    }

    @Test
    public void removeOrNull_Should_ReturnNull_When_ElementDoesNotExist() {
        ArrayListMock list = new ArrayListMock(new Object[]{new Object()}, 1);

        assertThat(list.removeOrNull(1)).isEqualTo(null);
    }

    @Test
    public void removeOrNull_Should_ReturnNull_When_ListIsEmpty() {
        ArrayListMock list = new ArrayListMock();

        assertThat(list.removeOrNull(1)).isEqualTo(null);
    }

    @Test
    public void removeOrNull_Should_RemoveElement() {
        Object object = new Object();
        Object object2 = new Object();
        Object object3 = new Object();
        ArrayListMock list = new ArrayListMock(new Object[]{object, object2, object3}, 3);

        assertThat(list.removeOrNull(0)).isEqualTo(object);
        assertThat(list.getArray()).containsSequence(object2, object3);
        assertThat(list.removeOrNull(1)).isEqualTo(object3);
        assertThat(list.getArray()).containsSequence(object2);
    }

    public static class ArrayListMock extends ArrayList<Object> {
        public ArrayListMock() {
            this(0);
        }

        public ArrayListMock(int defaultCapacity) {
            super(defaultCapacity);
        }

        public ArrayListMock(Object[] array, int size) {
            this.array = array;
            this.size = size;
        }

        public Object[] getArray() {
            return array;
        }
    }
}
