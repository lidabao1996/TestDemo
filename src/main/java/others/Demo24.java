package others;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Demo24 {
    public static void main(String[] args) {
        Dog[] dogs = new Dog[]{
                new Dog("老旺财", 10),
                new Dog("小旺财", 3),
                new Dog("二旺财", 5),
        };

        Arrays.sort(dogs);
        for (Dog d : dogs) {
            System.out.println(d.getName() + "：" + d.getAge());
        }
    }

    static class Dog implements Comparable<Dog> {
        private String name;
        private int age;



        public Dog(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public int compareTo(Dog o) {
            return age - o.age;
        }
    }
}
