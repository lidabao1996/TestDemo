package spring.db.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.db.Bean;
import spring.db.Bean2;

public class DemoTest {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Bean bean = (Bean) context.getBean("bean");
        System.out.println("bean" + bean);

        //使用工厂模式创建bean2
        //Bean2 bean2 = Bean2Factory.getBean2();
        Bean2 bean2 = (Bean2) context.getBean("bean2");
        System.out.println("bean2 = " + bean2);
    }

}
