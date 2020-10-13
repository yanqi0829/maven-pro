package chapter03;

/**
 * 动态链接
 */
public class DynamicLinkingTest {
    int num = 1;

    public void methodA() {

    }

    public void methodB() {
        methodA();
        num++;
    }
}
