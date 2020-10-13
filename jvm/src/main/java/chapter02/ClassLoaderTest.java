package chapter02;

import sun.misc.Launcher;

import java.net.URL;
import java.security.Provider;

public class ClassLoaderTest {
    public static void main(String[] args) {
      //获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);//sun.misc.Launcher$AppClassLoader@18b4aac2
        //获取其上层： 扩展类加载器
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader); //sun.misc.Launcher$ExtClassLoader@1540e19d
        //获取其上层： 获取不到引导类加载器
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println(bootstrapClassLoader);//null

        //对于用户自定义类,默认使用系统类加载器
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);//sun.misc.Launcher$AppClassLoader@18b4aac2

        //String 类使用引导类加载进行加载的 ====》Java的核心类库都是使用引导类加载器进行加载的
        ClassLoader classLoader1 = String.class.getClassLoader();
        System.out.println(classLoader1);//null

        //============================================================================
        //获取BootstrapClassLoader能够加载的api的路径
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL ele:
             urLs) {
            System.out.println(ele.toExternalForm());
        }
        //从上面的路径任意选择一个类，查看器类加载器
        System.out.println(Provider.class.getClassLoader());//null
        //获取扩展类加载器能够加载的路径
        String property = System.getProperty("java.ext.dirs");
        System.out.println(property);
    }
}
