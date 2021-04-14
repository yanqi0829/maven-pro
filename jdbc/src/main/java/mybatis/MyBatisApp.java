package mybatis;

import mybatis.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisApp {
    public static void main(String[] args) throws IOException {
//        1.定义mybatis主配置文件名称，从类路径根开始（target/classes）
        String config = "mybatis-config.xml";
        //2.读取文件
        InputStream inputStream = Resources.getResourceAsStream(config);
        //3.创建 SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //4.获取 sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //5.指定要执行的sql语句的标识，namespace+"."+ 标签id
        String sqlId = "mybatis.dao.StudentDao" + "." + "selectStudents";
        //6.执行
        List<Student> list = sqlSession.selectList(sqlId);
        list.forEach(stu -> System.out.println(stu));
        //7.关闭sqlSession
        sqlSession.close();
    }

}
