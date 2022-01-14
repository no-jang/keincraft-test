package engine.core.module.discover;

import engine.core.module.TestModule;
import engine.core.module.TestModule2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ListModuleSourceTest {
    private ListModuleSource source;

    @BeforeEach
    public void beforeEach() {
        source = new ListModuleSource();
    }

    @Test
    public void givenModuleClasses_whenDiscoverModuleClassCandidates_thenReturnClasses() {
        List<Class<?>> classes = List.of(TestModule.class, TestModule2.class);
        source.addClasses(classes);

        List<Class<?>> discoveredClasses = source.discoverModuleClassCandidates();

        assertThat(discoveredClasses).isEqualTo(classes);
    }
}
