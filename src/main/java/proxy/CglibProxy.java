package proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 2019-09-04
 * cglibProxy
 */
public class CglibProxy implements MethodInterceptor {
    public static void main(String[] args) {
        
    }

    public UserDao userDao;

    public CglibProxy(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 生成代理
     *
     * @return
     */
    public UserDao createProxy() {
        //核心类
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserDao.class);
        //设置回调
        enhancer.setCallback(this);
        UserDao userDaoProxy = (UserDao) enhancer.create();
        return userDaoProxy;
    }


    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if ("save".equals(method.getName())) {
            Object o = methodProxy.invokeSuper(proxy, args);
            System.out.println("");
        }

        return methodProxy.invokeSuper(proxy,args);
    }
}
