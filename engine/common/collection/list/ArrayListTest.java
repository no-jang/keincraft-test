package engine.common.collection.list;

import engine.common.collection.util.Arrays;
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
        });

        assertThat(list.indexOf(new Object())).isEqualTo(-1);
    }

    @Test
    public void indexOf_Should_ReturnElement() {
        Object object = new Object();
        ArrayListMock list = new ArrayListMock(new Object[]{
                new Object(),
                object,
                object
        });

        assertThat(list.indexOf(object)).isEqualTo(1);
    }

    @Test
    public void lastIndexOf_Should_ReturnMinusOne_When_ElementIsNull() {
        ArrayListMock list = new ArrayListMock();

        assertThat(list.lastIndexOf(null)).isEqualTo(-1);
    }

    @Test
    public void lastIndexOf_Should_ReturnMinusOne_When_ElementNotFound() {
        ArrayListMock list = new ArrayListMock(new Object[]{
                new Object(),
                new Object()
        });

        assertThat(list.lastIndexOf(new Object())).isEqualTo(-1);
    }

    @Test
    public void lastIndexOf_Should_ReturnElement() {
        Object object = new Object();
        ArrayListMock list = new ArrayListMock(new Object[]{
                new Object(),
                object,
                object,
                new Object()
        });

        assertThat(list.lastIndexOf(object)).isEqualTo(2);
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
        ArrayListMock list = new ArrayListMock(new Object[]{new Object()});

        assertThat(list.getOrNull(1)).isEqualTo(null);
    }

    @Test
    public void getOrNull_Should_ReturnElementAtIndex() {
        Object object = new Object();
        Object object2 = new Object();
        ArrayListMock list = new ArrayListMock(new Object[]{
                object,
                object2
        });

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
        ArrayListMock list = new ArrayListMock(new Object[]{object});

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
        ArrayListMock list = new ArrayListMock(new Object[]{new Object()});

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

        assertThat(list.getArray()).containsSequence(object2, object3);
    }

    @Test
    public void removeOrNull_Should_ReturnNull_When_ElementDoesNotExist() {
        ArrayListMock list = new ArrayListMock(new Object[]{new Object()});

        assertThat(list.removeOrNull(1)).isEqualTo(null);
    }

    @Test
    public void removeOrNull_Should_ReturnNull_When_ListIsEmpty() {
        ArrayListMock list = new ArrayListMock();

        assertThat(list.removeOrNull(1)).isEqualTo(null);
    }

    @Test
    public void removeOrNull_Should_RemoveElement() {
        Object object1 = new Object();
        Object object2 = new Object();
        Object object3 = new Object();
        Object object4 = new Object();
        Object object5 = new Object();
        Object object6 = new Object();
        Object object7 = new Object();
        Object object8 = new Object();
        Object object9 = new Object();
        Object object10 = new Object();

        Object[] objects = new Object[]{object1, object2, object3, object4, object5, object6, object7, object8, object9, object10};

        ArrayListMock list = new ArrayListMock(objects);

        assertThat(list.removeOrNull(0)).isEqualTo(object1);
        assertThat(list.removeOrNull(0)).isEqualTo(object2);
        assertThat(list.removeOrNull(0)).isEqualTo(object3);
        assertThat(list.removeOrNull(0)).isEqualTo(object4);
        assertThat(list.removeOrNull(0)).isEqualTo(object5);
        assertThat(list.removeOrNull(0)).isEqualTo(object6);
        assertThat(list.removeOrNull(0)).isEqualTo(object7);
        assertThat(list.removeOrNull(0)).isEqualTo(object8);
        assertThat(list.removeOrNull(0)).isEqualTo(object9);
        assertThat(list.removeOrNull(0)).isEqualTo(object10);

        assertThat(list.getArray()).hasSize(0);
    }

    public static class ArrayListMock extends ArrayList<Object> {
        public ArrayListMock() {
            this(0);
        }

        public ArrayListMock(int defaultCapacity) {
            this.size = 0;
            this.array = Arrays.unsafeCastNewArray(defaultCapacity);
        }

        public ArrayListMock(Object[] array) {
            this(array, array.length);
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
