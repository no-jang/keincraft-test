package engine.core.module.discover;

import engine.core.module.TestModule;
import engine.core.module.TestNonModule;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ModuleClassValidatorTest {
    @Test
    public void givenModuleClass_whenValidateModuleClass_thenPass() {
        ModuleClassValidator.validateModuleClass(TestModule.class);
    }

    @Test
    public void givenNonModuleClass_whenValidateModuleClass_thenThrowIllegalArgumentException() {
        assertThatThrownBy(() -> ModuleClassValidator.validateModuleClass(TestNonModule.class))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
