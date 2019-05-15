package base;

import java.io.*;

/**
 * 测试静态变量能否被
 */
public class TransientStaticTest {
    public static void main(String[] args) {

        try {
            User user = new User();
            user.username = "sophia";
            user.setId("li");
            System.out.println("\n序列化之前");

            System.out.println("username:" + user.getUsername());
            System.out.println("id = " + user.getId());

            OutputStream outputStream = new ObjectOutputStream(new FileOutputStream("E:/user/user.txt"));
            ((ObjectOutputStream) outputStream).writeObject(user);
            outputStream.flush();
            outputStream.close();


            System.out.println("\n序列化之后");

            ObjectInputStream  inputStream = new ObjectInputStream(new FileInputStream("E:/user/user.txt"));
            user = (User)inputStream.readObject();

            System.out.println("username : " + user.getUsername());
            System.out.println("id : " + user.getId());


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
