package hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HdfsTest {
    @Test
    public void testMkdirs() throws IOException, InterruptedException, URISyntaxException {
        // 1 获取文件系统
        Configuration configuration = new Configuration();
        // 配置在集群上运行
        // configuration.set("fs.defaultFS", "hdfs://hadoop002:9000");
        // FileSystem fs = FileSystem.get(configuration);
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop002:9000"), configuration, "root");
        // 2 创建目录
        fs.mkdirs(new Path("/test/hdfs/test"));
        // 3 关闭资源
        fs.close();
    }

    @Test
    public void testCopyFromLocalFile() throws IOException, InterruptedException, URISyntaxException {
        // 1 获取文件系统
        Configuration configuration = new Configuration();
        configuration.set("dfs.replication", "2");
        /**
         * 参数优先级排序：（1）客户端代码中设置的值 >（2）ClassPath下的用户自定义配置文件 >（3）然后是服务器的默认配置
         */
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop002:9000"), configuration, "root");
        // 2 上传文件
        fs.copyFromLocalFile(new Path("e:/1.txt"), new Path("/2.txt"));
        // 3 关闭资源
        fs.close();
        System.out.println("over");
    }

    @Test
    public void testCopyToLocalFile() throws IOException, InterruptedException, URISyntaxException {

        // 1 获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop002:9000"), configuration, "root");
        // 2 执行下载操作
        // boolean delSrc 指是否将原文件删除
        // Path src 指要下载的文件路径
        // Path dst 指将文件下载到的路径
        // boolean useRawLocalFileSystem 是否开启文件校验
        fs.copyToLocalFile(false, new Path("/2.txt"), new Path("e:/hdfstest.txt"), true);

        // 3 关闭资源
        fs.close();
    }

    @Test
    public void testDelete() throws IOException, InterruptedException, URISyntaxException {

        // 1 获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop002:9000"), configuration, "root");
        // 2 执行删除
        fs.delete(new Path("/1.txt"), true);
        // 3 关闭资源
        fs.close();
    }

    @Test
    public void testRename() throws IOException, InterruptedException, URISyntaxException {

        // 1 获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop002:9000"), configuration, "root");
        // 2 修改文件名称
        fs.rename(new Path("/2.txt"), new Path("/22222.txt"));
        // 3 关闭资源
        fs.close();
    }

    //查看文件名称、权限、长度、块信息
    @Test
    public void testListFiles() throws IOException, InterruptedException, URISyntaxException {

        // 1获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop002:9000"), configuration, "root");

        // 2 获取文件详情
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), false);

        while (listFiles.hasNext()) {
            LocatedFileStatus status = listFiles.next();
            // 输出详情
            // 文件名称
            System.out.println(status.getPath().getName());
            // 长度
            System.out.println(status.getLen());
            // 权限
            System.out.println(status.getPermission());
            // 分组
            System.out.println(status.getGroup());
            // 获取存储的块信息
            BlockLocation[] blockLocations = status.getBlockLocations();
            for (BlockLocation blockLocation : blockLocations) {
                // 获取块存储的主机节点
                String[] hosts = blockLocation.getHosts();
                for (String host : hosts) {
                    System.out.println(host);
                }
            }
            System.out.println("-----------分割线----------");
        }
// 3 关闭资源
        fs.close();
    }

    @Test
    public void testListStatus() throws IOException, InterruptedException, URISyntaxException {

        // 1 获取文件配置信息
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop002:9000"), configuration, "root");
        // 2 判断是文件还是文件夹
        FileStatus[] listStatus = fs.listStatus(new Path("/"));
        for (FileStatus fileStatus : listStatus) {
            // 如果是文件
            if (fileStatus.isFile()) {
                System.out.println("f:" + fileStatus.getPath().getName());
            } else {
                System.out.println("d:" + fileStatus.getPath().getName());
            }
        }
        // 3 关闭资源
        fs.close();
    }

    @Test
    public void putFileToHDFS() throws IOException, InterruptedException, URISyntaxException {

        // 1 获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop002:9000"), configuration, "root");
        // 2 创建输入流
        FileInputStream fis = new FileInputStream(new File("e:/1.txt"));
        // 3 获取输出流
        FSDataOutputStream fos = fs.create(new Path("/1test.txt"));
        // 4 流对拷
        IOUtils.copyBytes(fis, fos, configuration);
        // 5 关闭资源
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs.close();
    }

    @Test
    public void getFileFromHDFS() throws IOException, InterruptedException, URISyntaxException {

        // 1 获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop002:9000"), configuration, "root");
        // 2 获取输入流
        FSDataInputStream fis = fs.open(new Path("/1test.txt"));
        // 3 获取输出流
        FileOutputStream fos = new FileOutputStream(new File("e:/download.txt"));
        // 4 流的对拷
        IOUtils.copyBytes(fis, fos, configuration);
        // 5 关闭资源
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs.close();
    }


    @Test
    public void readFileSeek1() throws IOException, InterruptedException, URISyntaxException {

        // 1 获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop002:9000"), configuration, "root");
        // 2 获取输入流
        FSDataInputStream fis = fs.open(new Path("/hadoop-2.7.2.tar.gz"));
        // 3 创建输出流
        FileOutputStream fos = new FileOutputStream(new File("e:/hadoop-2.7.2.tar.gz.part1"));
        // 4 流的拷贝
        byte[] buf = new byte[1024];
        for (int i = 0; i < 1024 * 128; i++) {
            fis.read(buf);
            fos.write(buf);
        }
        // 5关闭资源
        IOUtils.closeStream(fis);
        IOUtils.closeStream(fos);
        fs.close();
    }

    //下载第二块
    @Test
    public void readFileSeek2() throws IOException, InterruptedException, URISyntaxException {

        // 1 获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop002:9000"), configuration, "root");
        // 2 打开输入流
        FSDataInputStream fis = fs.open(new Path("/hadoop-2.7.2.tar.gz"));
        // 3 定位输入数据位置
        fis.seek(1024 * 1024 * 128);
        // 4 创建输出流
        FileOutputStream fos = new FileOutputStream(new File("e:/hadoop-2.7.2.tar.gz.part2"));
        // 5 流的对拷
        IOUtils.copyBytes(fis, fos, configuration);
        // 6 关闭资源
        IOUtils.closeStream(fis);
        IOUtils.closeStream(fos);
        fs.close();
    }
@Test
    public  void test() throws Exception{

    System.setProperty("HADOOP_USER_NAME", "gzclhy");
    Configuration conf = new Configuration();
    conf.set("fs.defaultFS", "hdfs://gdHdfs");
    conf.set("dfs.nameservices", "gdHdfs");
    conf.set("dfs.ha.namenodes.gdHdfs", "nn1,nn2");
    conf.set("dfs.namenode.rpc-address.gdHdfs.nn1", "10.124.202.103:8020");
    conf.set("dfs.namenode.rpc-address.gdHdfs.nn2", "10.124.202.102:8020");
    conf.set("dfs.client.failover.proxy.provider.gdHdfs", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");

    FileSystem  fs = FileSystem.newInstance(conf);
    // 2 获取输入流
    FSDataInputStream fis = fs.open(new Path("/user/gzclhy/cust_file/20210223/success_KH202102231427108801.txt"));
    // 3 获取输出流
    FileOutputStream fos = new FileOutputStream(new File("e:/222.txt"));
    // 4 流的对拷
    IOUtils.copyBytes(fis, fos, conf);
    // 5 关闭资源
    IOUtils.closeStream(fos);
    IOUtils.closeStream(fis);
    fs.close();
}
}
