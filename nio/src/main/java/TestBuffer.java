import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

public class TestBuffer {
    @Test
    public void test1() {
        String str = "abcde";
        //分配1024字节大小的缓冲区
        System.out.println("------------allocate---------------");
        ByteBuffer buff = ByteBuffer.allocate(1024);
        System.out.println(buff.position()); //0
        System.out.println(buff.limit());//1024
        System.out.println(buff.capacity());//1024
        //存入数据到缓冲区中
        buff.put(str.getBytes());
        System.out.println("------------put---------------");
        System.out.println(buff.position()); //5
        System.out.println(buff.limit());//1024
        System.out.println(buff.capacity());//1024
        //切换到读数据模式
        System.out.println("------------flip---------------");
        buff.flip();
        System.out.println(buff.position()); //0
        System.out.println(buff.limit());//5
        System.out.println(buff.capacity());//1024
        //读取缓冲区中数据
        byte[] bytes = new byte[buff.limit()];
        buff.get(bytes);
        System.out.println("------------get---------------");
        System.out.println(new String(bytes, 0, bytes.length));
        System.out.println(buff.position()); //5
        System.out.println(buff.limit());//5
        System.out.println(buff.capacity());//1024
        //rewind重复读数据
        buff.rewind();
        System.out.println(buff.position()); //0
        System.out.println(buff.limit());//5
        System.out.println(buff.capacity());//1024
        //clear 清空缓冲区，但是缓冲区中的数据依然存在，属于被
        buff.clear();
        System.out.println(buff.position()); //0
        System.out.println(buff.limit());//1024
        System.out.println(buff.capacity());//1024
    }

}
