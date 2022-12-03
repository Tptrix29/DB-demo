package cn.edu.tongji.sse.db.student;

import java.util.List;

public interface StudentRepository {
    List<Student> findBySid(Integer sid);
    Student updateOne(Student student);
}
