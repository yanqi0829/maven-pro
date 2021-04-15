package mybatis.dao;

import mybatis.domain.ParamStu;
import mybatis.domain.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDao {
    public List<Student> selectStudents();

    public int insertStudent(Student st);

    public Student selectById(Integer id);

    public Student selectMulitParam(@Param("myid") Integer id, @Param("myname") String name);

    public Student selectMulitObjetct(ParamStu st);

}
