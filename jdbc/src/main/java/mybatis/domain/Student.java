package mybatis.domain;

public class Student {
    private Integer id;
    private String name;
    private String email;
    private Integer age;

    public Student() {
    }

    public Student(Integer id, String name, String eamil, Integer age) {
        this.id = id;
        this.name = name;
        this.email = eamil;
        this.age = age;
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
    }

    public String getEamil() {return email;}

    public void setEamil(String eamil) {
        this.email = eamil;
    }

    public Integer getAge() {return age;}

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
