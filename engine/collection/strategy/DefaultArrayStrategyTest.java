package engine.collection.strategy;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultArrayStrategyTest {

    @Test
    public void growArray_Should_NotGrowArray_When_CapacityIsEnough() {
        ArrayStrategy<Object> strategy = new DefaultArrayStrategy<>();
        Object[] array = new Object[1];

        array = strategy.growArray(array, 0, 1);

        assertThat(array).hasSize(1);
    }

    @Test
    public void growArray_Should_GrowArrayWithMinSize_When_CapacityIncreaseIsUnderMinGrowSize() {
        ArrayStrategy<Object> strategy = new DefaultArrayStrategy<>();
        Object[] array = new Object[5];

        array = strategy.growArray(array, 5, 1);

        assertThat(array).hasSize(15);
    }

    @Test
    public void growArray_Should_GrowArrayWithIncreaseCapacity_When_IncreaseCapacityIsMoreThanMinGrowSize() {
        ArrayStrategy<Object> strategy = new DefaultArrayStrategy<>();
        Object[] array = new Object[10];

        array = strategy.growArray(array, 0, 20);

        assertThat(array).hasSize(20);
    }

    @Test
    public void shrinkArray_Should_NotShrinkArray_When_EmptySpaceIsSmallerThanShrinkThreshold() {
        ArrayStrategy<Object> strategy = new DefaultArrayStrategy<>();
        Object[] array = new Object[10];

        array = strategy.shrinkArray(array, 0, 1);

        assertThat(array).hasSize(0);
    }

    @Test
    public void shrinkArray_Should_ShrinkArray() {
        ArrayStrategy<Object> strategy = new DefaultArrayStrategy<>();
        Object[] array = new Object[49];

        array = strategy.shrinkArray(array, 25, 20);

        assertThat(array).hasSize(29);
    }
}
