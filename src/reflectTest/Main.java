package reflectTest;

import beans.Cat;
import beans.User;

public class Main {
    public static void main(String[] args) throws Exception {
        FileApplicationContext fac = new FileApplicationContext("src/applicationContext.xml");
        User user = (User) fac.getBean("user2");
        System.out.println(user.getName());
        System.out.println(user.getAge());

        Cat cat = (Cat)fac.getBean("cat1");
        System.out.println(cat.getColor());
        System.out.println(cat.getBrand());
    }
}
