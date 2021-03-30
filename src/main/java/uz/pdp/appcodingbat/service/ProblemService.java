package uz.pdp.appcodingbat.service;

import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entity.Course;
import uz.pdp.appcodingbat.entity.Problem;
import uz.pdp.appcodingbat.payload.ProblemDTO;
import uz.pdp.appcodingbat.payload.Response;

import java.util.List;

@Service
public interface ProblemService {

    Response save(ProblemDTO problemDTO);                      // CREATE

    List<Problem> findAll();                                // READ

    Problem finOneById(Integer id);

    Response edit(ProblemDTO problemDTO, Integer id);        // UPDATE

    Response delete(Integer id);                             // DELETE

}
