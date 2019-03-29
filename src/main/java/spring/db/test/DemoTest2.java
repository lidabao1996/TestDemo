package spring.db.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.db.BeanDemo;

public class DemoTest2 {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        BeanDemo beanDemo = (BeanDemo) context.getBean("beanDemo");
    }
}
