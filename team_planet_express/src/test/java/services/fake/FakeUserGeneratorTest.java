package services.fake;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FakeUserGeneratorTest {

    private final FakeUserGenerator userGenerator = new FakeUserGenerator();

    @Test
    void shouldCreateListOfFourUsers() {
        assertEquals(4, userGenerator.createUsers().size());
    }

}
