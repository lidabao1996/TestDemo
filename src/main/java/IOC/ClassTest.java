package IOC;

import IOC.human.Human;
import org.junit.Before;
import org.junit.Test;

public class ClassTest {
    IocContainer iocContainer = new IocContainer();
    @Before
    public void before(){
        iocContainer.setBean(Audi.class,"audi");
        iocContainer.setBean(Buick.class,"buick");
        iocContainer.setBean(Zhangsan.class,"zhangsan","audi");
    }

    @Test
    public void test(){
        Human zhangsan = (Human) iocContainer.getBean("zhangsan");
        zhangsan.goHome();
    }

}
