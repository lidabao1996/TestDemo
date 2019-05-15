package base;

public class TestMain {
    public static void main(String[] args) {
       /* Person Harry = new Person();
        System.out.println("Harry's age is " + Harry.GetAge(12));*/


        City city = new City();
        city.value();
    }
}


class Country {
    String name;

    void value() {
        name = "China";
    }
}


class City extends Country {
    String name;

    void value() {
        name = "shanghai";
        super.value();
        System.out.println(name);
        System.out.println(super.name);
    }
}
