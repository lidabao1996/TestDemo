package IOC;

import IOC.human.HumanWithCar;

public class Zhangsan extends HumanWithCar {
    public Zhangsan(Car car) {
        super(car);
    }

    @Override
    public void goHome() {
        car.straight();
        car.stop();
    }
}
