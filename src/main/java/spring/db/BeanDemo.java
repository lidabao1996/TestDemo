package spring.db;

public class BeanDemo {
    private String name;
    private AnthorBean anthorBean;

    public BeanDemo(String name, AnthorBean anthorBean) {
        this.name = name;
        this.anthorBean = anthorBean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnthorBean getAnthorBean() {
        return anthorBean;
    }

    public void setAnthorBean(AnthorBean anthorBean) {
        this.anthorBean = anthorBean;
    }
}
