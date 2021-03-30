package uz.pdp.appcodingbat.service;

import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entity.Course;
import uz.pdp.appcodingbat.payload.Response;

import java.util.List;

@Service
public interface CourseService {

    Response save(Course course);                      // CREATE

    List<Course> findAll();                                // READ

    Course findOneById(Integer id);

    Response edit(Course course, Integer id);           // UPDATE

    Response delete(Integer id);                  // DELETE

}
