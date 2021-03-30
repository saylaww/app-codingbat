package uz.pdp.appcodingbat.service;

import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entity.Problem;
import uz.pdp.appcodingbat.entity.ProblemTest;
import uz.pdp.appcodingbat.payload.ProblemTestDTO;
import uz.pdp.appcodingbat.payload.Response;
import uz.pdp.appcodingbat.repository.ProblemRepository;
import uz.pdp.appcodingbat.repository.ProblemTestRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemTestServiceImpl implements ProblemTestService {

    final ProblemTestRepository problemTestRepository;
    final ProblemRepository problemRepository;

    public ProblemTestServiceImpl(ProblemTestRepository problemTestRepository,
                                  ProblemRepository problemRepository) {
        this.problemTestRepository = problemTestRepository;
        this.problemRepository = problemRepository;
    }

    @Override
    public Response save(ProblemTestDTO problemTestDTO) {
        ProblemTest problemTest = new ProblemTest();
        problemTest.setArguments(problemTestDTO.getArguments());
        problemTest.setResult(problemTestDTO.getResult());

        Optional<Problem> optionalProblem = problemRepository.findById(problemTestDTO.getProblemId());
        if (!optionalProblem.isPresent())
            return new Response("Problem id not found!", false);
        problemTest.setProblem(optionalProblem.get());

        problemTestRepository.save(problemTest);
        return new Response("Problem Test saved!", true);
    }

    @Override
    public List<ProblemTest> findAll() {
        return problemTestRepository.findAll();
    }

    @Override
    public ProblemTest finOneById(Integer id) {
        Optional<ProblemTest> optionalProblemTest = problemTestRepository.findById(id);

        return optionalProblemTest.orElse(null);
    }

    @Override
    public Response edit(ProblemTestDTO problemTestDTO, Integer id) {
        Optional<ProblemTest> optionalProblemTest = problemTestRepository.findById(id);
        if(optionalProblemTest.isPresent()){
            optionalProblemTest.get().setArguments(problemTestDTO.getArguments());
            optionalProblemTest.get().setResult(problemTestDTO.getResult());

            Optional<Problem> optionalProblem = problemRepository.findById(problemTestDTO.getProblemId());
            if (!optionalProblem.isPresent())
                return new Response("Problem id not found!", false);
            optionalProblemTest.get().setProblem(optionalProblem.get());

            problemTestRepository.save(optionalProblemTest.get());
            return new Response("Problem Test updated!", true);
        }
        return new Response("Problem Test id not found!", false);
    }

    @Override
    public Response delete(Integer id) {
        Optional<ProblemTest> optionalProblemTest = problemTestRepository.findById(id);
        if(optionalProblemTest.isPresent()){
            problemTestRepository.deleteById(id);

        }
        return null;
    }
}
