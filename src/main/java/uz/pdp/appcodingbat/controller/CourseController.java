package uz.pdp.appcodingbat.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.constants.FrontUrls;
import uz.pdp.appcodingbat.entity.Course;
import uz.pdp.appcodingbat.payload.Response;
import uz.pdp.appcodingbat.service.CourseService;

import java.util.List;

@RestController
@RequestMapping(FrontUrls.COURSE)
public class CourseController {

    final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public HttpEntity<Response> save(@RequestBody Course course) {
        Response response = courseService.save(course);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.OK : HttpStatus.CONFLICT).body(response);
    }

    @GetMapping
    public HttpEntity<?> findAll(){
        List<Course> courseList = courseService.findAll();
        return ResponseEntity.ok(courseList);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> findOneById(@PathVariable Integer id){
        Course course = courseService.findOneById(id);
        if(course == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Course id not found", false));
        return ResponseEntity.ok(course);
    }

    @PutMapping("/{id}")
    public HttpEntity<Response> edit(@RequestBody Course course, @PathVariable Integer id) {
        Response response = courseService.edit(course, id);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<Response> delete(@PathVariable Integer id) {
        Response response = courseService.delete(id);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND).body(response);
    }
}
