package proxy;

public class ProxyByJdkDynamic implements Service {
    private Service target;


    public ProxyByJdkDynamic(Service target) {
        this.target = target;
    }


    @Override
    public void doNeedTx() {
        System.out.println("在代理中创建事务");
        target.doNeedTx();
        System.out.println("在代理中提交事务");
    }

    @Override
    public void doNotneedTx() {

        try {
            target.doNotneedTx();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
