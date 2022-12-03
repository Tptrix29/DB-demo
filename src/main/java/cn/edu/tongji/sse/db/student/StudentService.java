package cn.edu.tongji.sse.db.student;

import java.util.List;

public interface StudentService {
    List<Student> getStudentBySid(Integer sid);
    Student updateAStudent(Student student);
}
