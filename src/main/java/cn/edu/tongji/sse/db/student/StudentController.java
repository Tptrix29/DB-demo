package cn.edu.tongji.sse.db.student;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "MongoDB测试")
@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @ApiOperation("根据sid获得学生信息")
    @GetMapping("/{sid}")
    List<Student> getInfo(@PathVariable("sid") Integer sid){
        return studentService.getStudentBySid(sid);
    }

    @ApiOperation("上传新的学生信息")
    @PostMapping("/add")
    Student addStudent(@RequestBody Student student){
        return studentService.updateAStudent(student);
    }

}
