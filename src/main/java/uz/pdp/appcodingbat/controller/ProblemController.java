package uz.pdp.appcodingbat.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.constants.FrontUrls;
import uz.pdp.appcodingbat.entity.Course;
import uz.pdp.appcodingbat.entity.Problem;
import uz.pdp.appcodingbat.payload.ProblemDTO;
import uz.pdp.appcodingbat.payload.Response;
import uz.pdp.appcodingbat.service.CourseService;
import uz.pdp.appcodingbat.service.ProblemService;

import java.util.List;

@RestController
@RequestMapping(FrontUrls.PROBLEM)
public class ProblemController {

    final ProblemService problemService;

    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @PostMapping
    public HttpEntity<Response> save(@RequestBody ProblemDTO problemDTO) {
        Response response = problemService.save(problemDTO);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.OK : HttpStatus.CONFLICT).body(response);
    }

    @GetMapping
    public HttpEntity<?> findAll(){
        List<Problem> problemList = problemService.findAll();
        return ResponseEntity.ok(problemList);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> findOneById(@PathVariable Integer id){
        Problem problem = problemService.finOneById(id);
        if(problem == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Problem id not found", false));
        return ResponseEntity.ok(problem);
    }

    @PutMapping("/{id}")
    public HttpEntity<Response> edit(@RequestBody ProblemDTO problemDTO, @PathVariable Integer id) {
        Response response = problemService.edit(problemDTO, id);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<Response> delete(@PathVariable Integer id) {
        Response response = problemService.delete(id);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND).body(response);
    }
}
