package mybatis.dao;

import mybatis.domain.Student;

import java.util.List;

public interface StudentDao {
    public List<Student> selectStudents();
}
