package mybatis.utils;

import mybatis.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisUtils {
    //官网：SqlSessionFactory 一旦被创建就应该在应用的运行期间一直存在，没有任何理由丢弃它或重新创建另一个实例。
    private static SqlSessionFactory sqlSessionFactory = null;

    static {
        String config = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(config);
        } catch (IOException e) {
        }
        //官网：一旦创建了 SqlSessionFactory，就不再需要它了
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static SqlSession getSqlSession() {
        SqlSession sqlSession = null;
        if (sqlSessionFactory != null) {
            sqlSession = sqlSessionFactory.openSession();
        }
        return sqlSession;
    }
}
