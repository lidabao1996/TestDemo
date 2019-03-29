package IOC;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IocContainer {
    // bean 有id和所属类型
    Map<String, Object> beans = new ConcurrentHashMap<>();


    //得到bean
    public Object getBean(String beanId) {
        return beans.get(beanId);
    }


    /**
     * ioc容器创建一个bean
     *
     * @param clazz         需要创建的bean来源于哪个类
     * @param beanId        创建beanId
     * @param parentBeanIds 要创建的bean的class的构造方法所依赖的beanId们
     */
    public void setBean(Class<?> clazz, String beanId, String... parentBeanIds) {
        Object[] paramValues = new Object[parentBeanIds.length];
        //1:组装构造方法所需要的参数值

        for (int i = 0; i < parentBeanIds.length; i++) {
            paramValues[i] = beans.get(parentBeanIds[i]);
        }

        //2:调用构造方法实例化bean
        Object bean = null;

        for (Constructor<?> constructor : clazz.getConstructors()) {
            try {
                bean = constructor.newInstance(paramValues);
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }

            if (bean == null) {
                throw new RuntimeException("找不到构造方法去实例化bean");
            }
        }
        //3:将实例化的bean放入beans
        beans.put(beanId, bean);
    }


}
