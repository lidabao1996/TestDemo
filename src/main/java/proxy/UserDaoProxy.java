package proxy;

public class UserDaoProxy implements IUserDao {
    private IUserDao target;

    public UserDaoProxy(IUserDao target) {
        this.target = target;
    }

    @Override
    public void save() {
        System.out.println("start-trasaction");

        target.save();//执行目标对象的方法
        System.out.println("提交事务...");

    }
}
