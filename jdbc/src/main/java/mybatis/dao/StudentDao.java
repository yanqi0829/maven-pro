package mybatis.dao;

import mybatis.domain.ParamStu;
import mybatis.domain.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentDao {
    public List<Student> selectStudents();

    public int insertStudent(Student st);

    public Student selectById(Integer id);

    public Student selectMulitParam(@Param("myid") Integer id, @Param("myname") String name);

    public Student selectMulitObjetct(ParamStu st);

    public Student selectMulitMap(Map<String, Object> map);

    //动态sql使用对象做入参
    public List<Student> selectStudentIf(Student st);

    public List<Student> selectForeach(List<Integer> list);

}
