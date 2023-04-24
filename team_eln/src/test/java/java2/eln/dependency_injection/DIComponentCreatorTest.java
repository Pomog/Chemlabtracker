package java2.eln.dependency_injection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DIComponentCreatorTest {

    @Mock
    private ApplicationContext applicationContext;

    private DIComponentCreator diComponentCreator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        diComponentCreator = new DIComponentCreator();
    }

    @Test
    void testCreate() {
        // given
        List<Class> diComponents = Arrays.asList(TestComponent1.class, TestComponent2.class);

        // when
        diComponentCreator.create(applicationContext, diComponents);

        // then
        verify(applicationContext, times(2)).addBean(any(Class.class), any());
    }


    @Test
    void testBeanAddition() {
        // create a new ApplicationContext object and add a TestComponent1 instance to it
        ApplicationContext applicationContext2 = new ApplicationContext();
        TestComponent1 testComponent1 = new TestComponent1();
        applicationContext2.addBean(TestComponent1.class, testComponent1);

        // verify that the TestComponent1 instance was added to the ApplicationContext object
        assertEquals(testComponent1, applicationContext2.getBean(TestComponent1.class));
    }

    static class TestComponent1 {
        public TestComponent1() {}
    }

    static class TestComponent2 {
        public TestComponent2() {}
    }

}