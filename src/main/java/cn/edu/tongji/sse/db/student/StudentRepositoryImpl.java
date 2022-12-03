package cn.edu.tongji.sse.db.student;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepository {
    private final MongoTemplate mongoTemplate;

    @Override
    public List<Student> findBySid(Integer sid) {
        Query query = new Query(Criteria.where("sid").is(sid));
        return mongoTemplate.find(
                query,
                Student.class);
    }

    @Override
    public Student updateOne(Student student) {
        return mongoTemplate.save(student);
    }
}
