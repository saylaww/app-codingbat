package uz.pdp.appcodingbat.service;

import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entity.Course;
import uz.pdp.appcodingbat.entity.ProblemTest;
import uz.pdp.appcodingbat.payload.ProblemTestDTO;
import uz.pdp.appcodingbat.payload.Response;

import java.util.List;

@Service
public interface ProblemTestService {

    Response save(ProblemTestDTO problemTestDTO);                      // CREATE

    List<ProblemTest> findAll();                                // READ

    ProblemTest finOneById(Integer id);

    Response edit(ProblemTestDTO problemTestDTO, Integer id);           // UPDATE

    Response delete(Integer id);                  // DELETE

}
