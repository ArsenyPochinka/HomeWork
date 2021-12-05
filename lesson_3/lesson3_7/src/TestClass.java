import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.Annotation;
import java.util.Arrays;
import java.util.Optional;

public class TestClass {
    public static void start(Class testClass) {
        Method[] methods = TestClass.class.getDeclaredMethods();
        // start beforeSuite
        Arrays.stream(methods)
                .filter(m -> m.isAnnotationPresent(BeforeSuite.class))
                .findAny().ifPresent(m -> {
            try {
                m.invoke(testClass.newInstance());
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }
        });
        // start tests
        for (int i = 1; i <= 10; i++) {
            int priority = i;
            Optional.of(Arrays.stream(methods)
                    .filter(m -> m.isAnnotationPresent(Test.class) && m.getAnnotation(Test.class).priority() == priority))
                    .ifPresent(ms -> ms.forEach(m -> {
                try {
                    m.invoke(testClass.newInstance());
                } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                    e.printStackTrace();
                }
            }));
        }
        // start afterSuite
        Arrays.stream(methods)
                .filter(m -> m.isAnnotationPresent(AfterSuite.class))
                .findAny().ifPresent(m -> {
                    try {
                        m.invoke(testClass.newInstance());
                    } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                        e.printStackTrace();
                    }
                });

    }

    @BeforeSuite
    public static void beforeSuite() {
        System.out.println("start beforeSuite");
    }

    @Test(priority = 3)
    public static void test1() {
        System.out.println("start test1");
    }
    @Test(priority = 4)
    public static void test2() {
        System.out.println("start test2");
    }
    @Test(priority = 4)
    public static void test3() {
        System.out.println("start test3");
    }
    @Test(priority = 2)
    public static void test4() {
        System.out.println("start test4");
    }
    @Test(priority = 6)
    public static void test5() {
        System.out.println("start test5");
    }
    @Test(priority = 6)
    public static void test6() {
        System.out.println("start test6");
    }
    @Test(priority = 3)
    public static void test7() {
        System.out.println("start test7");
    }
    @Test(priority = 7)
    public static void test8() {
        System.out.println("start test8");
    }
    @Test(priority = 8)
    public static void test9() {
        System.out.println("start test9");
    }
    @Test(priority = 8)
    public static void test10() {
        System.out.println("start test10");
    }

    @AfterSuite
    public static void afterSuite() {
        System.out.println("start afterSuite");
    }
}
