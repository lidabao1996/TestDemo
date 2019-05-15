package proxy;

/**
 * 静态代理
 * 代理的优点，可以不修改别人代码的前提下，扩展业务代码
 * 代理缺点:因为dialing对象需要与目标对象实现一样的接口，所以很多代理类太多，同时，一旦增加业务方法，目标对象和代理对象都要跟着修改。
 */
public class ProxyMain {
    public static void main(String[] args) {
        /**
         * 静态代理
         */
       /* UserDao dao = new UserDao();
        UserDaoProxy proxy = new UserDaoProxy(dao);
        proxy.save();*/


        /**
         * 动态代理
         */
        IUserDao target = new UserDao();
        System.out.println(target.getClass());
        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
        System.out.println(proxy.getClass());
        proxy.save();
    }
}
