package engine.core.container;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ContainerTest {
    private Container container;

    @BeforeEach
    public void beforeEach() {
        container = new DefaultContainer();
    }

    @Test
    public void testResolveFromInstance() {
        String instance = "test";

        container.bind(String.class).fromInstance(instance);

        String injected = container.resolve(String.class);
        assertThat(injected).isEqualTo(instance);
    }
}
