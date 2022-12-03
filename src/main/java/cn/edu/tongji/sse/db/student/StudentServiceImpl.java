package cn.edu.tongji.sse.db.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;

    @Override
    public List<Student> getStudentBySid(Integer sid) {
        return studentRepository.findBySid(sid);
    }

    @Override
    public Student updateAStudent(Student student) {
        return studentRepository.updateOne(student);
    }
}
