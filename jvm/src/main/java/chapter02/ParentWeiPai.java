package chapter02;

public class ParentWeiPai {
    /**
     * 当前使用的String还是核心API中的  不是自定义的
     * @param args
     */
    public static void main(String[] args) {
        //系统加载器，委托扩展类加载器，进而委托引导类加载器，
        //引导类加载器  发现包名为java.lang开头，加载核心类 String
        java.lang.String str= new java.lang.String();
        System.out.println("hello");
        System.out.println(str.getClass().getClassLoader());
    }
}
