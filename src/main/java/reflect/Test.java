package reflect;

import java.lang.annotation.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) throws Exception {
        //通过反射获取类的Class对象
        Class c1 = Class.forName("reflect.User");
        Class c2 = Class.forName("reflect.User");
        System.out.println(c1);
        //一个类在内存中只有一个Class对象
        System.out.println(c1 == c2);
//TODO
        //class类的创建方式
        User user = new User();
        //getClass
        Class<? extends User> aClass = user.getClass();
        //.class
        Class<User> userClass = User.class;
        //Class.forName
        Class<?> aClass1 = Class.forName("reflect.User");
        //TODO
        System.out.println(c1.getName());
        System.out.println(c1.getSimpleName());

        Field[] fields = c1.getFields();  //public属性
        System.out.println(Arrays.toString(fields));
        Field[] declaredFields = c1.getDeclaredFields();
        System.out.println(Arrays.toString(declaredFields));

        Field name = c1.getDeclaredField("name");
        Method[] methods = c1.getMethods();//获得本类及父类public方法
        Method[] declaredMethods = c1.getDeclaredMethods();//获得本类的所有方法

        Method getName = c1.getMethod("getName", null);
        Method settName = c1.getMethod("setName", String.class);

        Constructor[] constructors = c1.getConstructors();
        Constructor[] declaredConstructors = c1.getDeclaredConstructors();
        Constructor declaredConstructor = c1.getDeclaredConstructor(String.class, String.class, Integer.class);

        //创建对象
        User o = (User) c1.newInstance();
        //构造器创建对象
        User o1 = (User) declaredConstructor.newInstance("213", "21da", 12);
        //通过反射获取一个方案
        settName.invoke(o1, "改了");
        System.out.println(o1);

        //修改私有属性
        name.setAccessible(true);
        name.set(o1, "haha");

        //获取字段上的注解
        ITestAnnotation name1 = c1.getDeclaredField("name").getAnnotation(ITestAnnotation.class);
        System.out.println(name1.value());
    }
}

class User {
    @ITestAnnotation("奥巴马")
    private String name;
    private String age;
    private Integer id;

    public User() {
    }

    public User(String name, String age, Integer id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", id=" + id +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {return age;}

    public void setAge(String age) {
        this.age = age;
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {
        this.id = id;
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface ITestAnnotation {
    String value();
}
