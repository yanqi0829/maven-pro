import mybatis.dao.StudentDao;
import mybatis.domain.Student;
import mybatis.utils.MyBatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    @Test   //insert
    public void insert() throws IOException {
//        1.定义mybatis主配置文件名称，从类路径根开始（target/classes）
        String config = "mybatis-config.xml";
        //2.读取文件
        InputStream inputStream = Resources.getResourceAsStream(config);
        //3.创建 SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //4.获取 sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //5.指定要执行的sql语句的标识，namespace+"."+ 标签id
        String sqlId = "mybatis.dao.StudentDao" + "." + "insertStudent";
        //6.执行
        int num = sqlSession.insert(sqlId, new Student(33, "asd", "@", 34));
        System.out.println(num);
        //mybatis默认不自动提交事务， insert update  delete 之后要commit
        sqlSession.commit();
        //7.关闭sqlSession
        sqlSession.close();
    }

    @Test  //代理
    public void daili() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        //studentDao=com.sun.proxy.$Proxy2    jdk的动态代理
        System.out.println("studentDao=" + studentDao.getClass().getName());
        //Mybatis 会根据sql信息 选择去调用sqlSession的哪个方法
        List<Student> students = studentDao.selectStudents();
        students.forEach(stu -> System.out.println(stu));
        Student student = studentDao.selectById(12);
        Student as = studentDao.selectMulitParam(12, "as");
    }
}
