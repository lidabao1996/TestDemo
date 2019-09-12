package spring_version2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring_version2.service.MessageService;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        MessageService service = context.getBean(MessageService.class);
        System.out.println(service.getMessage());
    }
}
