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
    private static SqlSessionFactory sqlSessionFactory = null;

    static {
        String config = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(config);
        } catch (IOException e) {
        }
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
