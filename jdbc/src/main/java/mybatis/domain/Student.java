package mybatis.domain;

public class Student {
    private Integer id;
    private String name;
    private String eamil;
    private Integer age;

    public Integer getId() {return id;}

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
    }

    public String getEamil() {return eamil;}

    public void setEamil(String eamil) {
        this.eamil = eamil;
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
                ", eamil='" + eamil + '\'' +
                ", age=" + age +
                '}';
    }
}
