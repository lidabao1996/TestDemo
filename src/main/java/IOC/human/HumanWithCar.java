package IOC.human;

import IOC.Car;

public abstract class HumanWithCar implements Human {
    //所有被依赖的class都用构造方法来注入
    protected Car car;

    public HumanWithCar(Car car) {
        this.car = car;
    }

    public abstract void goHome();
}
