package engine.collection.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ArrayListTest {
    private MutableList<Object> list;

    @BeforeEach
    public void beforeEach() {
        list = new ArrayList<>();
    }

    @Test
    public void given_nonEmptyList_when_size_then_returnCorrectSize() {
        Object object = new Object();
        list.add(new Object());
        list.add(object);

        assertThat(list.size()).isEqualTo(2);

        list.remove(object);
        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    public void given_nonEmptyList_when_getOrNull_then_getElementAtIndex() {
        Object object = new Object();
        Object object2 = new Object();
        list.add(object);
        list.add(object2);

        assertThat(list.get(0)).isEqualTo(object);
        assertThat(list.get(1)).isEqualTo(object2);
    }

    @Test
    public void given_negativeIndex_when_getOrNull_then_throwIllegalArgumentException() {
        assertThatThrownBy(() -> list.getOrNull(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void given_indexOutOfRange_when_getOrNull_then_ReturnNull() {
        MutableList<Object> list = new ArrayList<>();
        assertThat(list.getOrNull(0)).isEqualTo(null);

        list.add(new Object());
        assertThat(list.getOrNull(0)).isNotEqualTo(null);
        assertThat(list.getOrNull(1)).isEqualTo(null);
    }

    @Test
    public void given_nonNullElement_when_add_thenAddElement() {
        Object object = new Object();
        Object object2 = new Object();
        Object object3 = new Object();

        list.add(object);
        assertThat(list.getOrNull(0)).isEqualTo(object);

        list.add(object2);
        assertThat(list.getOrNull(1)).isEqualTo(object2);

        list.remove(object2);
        list.add(object3);
        assertThat(list.getOrNull(1)).isEqualTo(object3);
    }

    @Test
    public void given_null_when_add_then_throwIllegalArgumentException() {
        assertThatThrownBy(() -> list.add(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void given_nonNullElement_when_set_then_setElementAndReturnOld() {
        Object object = new Object();
        Object object2 = new Object();

        list.add(object);
        assertThat(list.set(0, object2)).isEqualTo(object);
        assertThat(list.getOrNull(0)).isEqualTo(object2);
    }

    @Test
    public void given_indexEqualsSize_when_set_then_setElementWithNoGaps() {
        list.add(new Object());

        Object object = new Object();
        list.set(1, object);

        assertThat(list.getOrNull(1)).isEqualTo(object);
    }

    @Test
    public void given_negativeIndex_when_set_then_throwIllegalArgumentException() {
        assertThatThrownBy(() -> list.set(-1, new Object()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void given_nullElement_when_set_then_throwIllegalArgumentException() {
        assertThatThrownBy(() -> list.set(0, null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void given_indexHigherThanSize_when_set_then_throwIllegalArgumentException() {
        assertThatThrownBy(() -> list.set(1, new Object()));
    }

    @Test
    public void given_nonEmptyList_when_remove_thenRemoveElementAndReturnIt() {
        Object object = new Object();
        Object object2 = new Object();
        list.add(object);
        list.add(object2);

        assertThat(list.removeOrNull(0)).isEqualTo(object);

        // Test that the element was moved
        assertThat(list.getOrNull(0)).isEqualTo(object2);
    }

    @Test
    public void given_emptyList_when_remove_thenReturnNull() {
        assertThat(list.removeOrNull(0)).isEqualTo(null);
    }

    @Test
    public void given_negativeIndex_when_removeOrNull_thenThrowIllegalArgumentException() {
        assertThatThrownBy(() -> list.removeOrNull(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void given_nonEmptyList_when_iterator_then_iterateCorrectly() {
        int i = 0;
        Object object = new Object();
        Object object2 = new Object();
        Object object3 = new Object();
        list.add(object);
        list.add(object2);
        list.add(object3);

        for (Object element : list) {
            Object expectedElement = null;

            switch (i) {
                case 0:
                    expectedElement = object;
                    break;
                case 1:
                    expectedElement = object2;
                    break;
                case 2:
                    expectedElement = object3;
                    break;
            }

            assertThat(element).isEqualTo(expectedElement);

            i++;
        }
    }
}
