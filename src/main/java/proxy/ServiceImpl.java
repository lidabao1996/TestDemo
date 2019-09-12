package proxy;

import org.springframework.transaction.annotation.Transactional;

public class ServiceImpl implements Service{
    @Transactional
    @Override
    public void doNeedTx() {
        System.out.println("execute doNeedTx in ServiceImpl");
    }


    @Override
    public void doNotneedTx() {
        this.doNeedTx();
    }
}
