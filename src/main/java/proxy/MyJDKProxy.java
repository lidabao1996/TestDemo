package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyJDKProxy implements InvocationHandler {
    private UserDao userDao;

    public MyJDKProxy(UserDao userDao) {
        this.userDao = userDao;
    }


    public UserDao createProxy() {
        UserDao userDapProxy = (UserDao) Proxy.newProxyInstance(userDao.getClass().getClassLoader(), userDao.getClass().getInterfaces(), this);
        return userDapProxy;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("save".equals(method.getName())){
            System.out.println("需要加事务.....");
        }

        return method.invoke(userDao,args);
    }
}
