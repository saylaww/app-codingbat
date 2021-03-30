package uz.pdp.appcodingbat.service;

import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entity.Course;
import uz.pdp.appcodingbat.payload.Response;
import uz.pdp.appcodingbat.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Response save(Course course) {
        if(courseRepository.existsByName(course.getName()))
            return new Response("This course name is already exists!", false);

        courseRepository.save(course);
        return new Response("Course saved!", true);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findOneById(Integer id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        return optionalCourse.orElse(null);
    }

    @Override
    public Response edit(Course course, Integer id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if(optionalCourse.isPresent()){
            optionalCourse.get().setName(course.getName());
            optionalCourse.get().setDescription(course.getDescription());

            courseRepository.save(optionalCourse.get());

            return new Response("Course updated!", true);
        }
        return new Response("Course id not found!", false);
    }

    @Override
    public Response delete(Integer id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if(optionalCourse.isPresent()){
            courseRepository.deleteById(id);
            return new Response("Course deleted!", true);
        }
        return new Response("Course id not found!", false);
    }
}
