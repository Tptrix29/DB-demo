package cn.edu.tongji.sse.db.student;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document("students")
public class Student {
    @MongoId
    private String id;
    private Integer sid;
    private String name;
    private Integer year;
    private float score;
}
