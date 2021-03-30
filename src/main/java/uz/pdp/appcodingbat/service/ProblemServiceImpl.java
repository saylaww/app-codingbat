package uz.pdp.appcodingbat.service;

import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entity.Problem;
import uz.pdp.appcodingbat.entity.Section;
import uz.pdp.appcodingbat.payload.ProblemDTO;
import uz.pdp.appcodingbat.payload.Response;
import uz.pdp.appcodingbat.repository.ProblemRepository;
import uz.pdp.appcodingbat.repository.SectionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemServiceImpl implements ProblemService{

    final ProblemRepository problemRepository;
    final SectionRepository sectionRepository;

    public ProblemServiceImpl(ProblemRepository problemRepository,
                              SectionRepository sectionRepository) {
        this.problemRepository = problemRepository;
        this.sectionRepository = sectionRepository;
    }

    @Override
    public Response save(ProblemDTO problemDTO) {
        Problem problem = new Problem();
        problem.setTitle(problemDTO.getTitle());
        problem.setBody(problemDTO.getBody());
        problem.setProblem(problem.getProblem());
        problem.setSolution(problemDTO.getSolution());

        Optional<Section> optionalSection = sectionRepository.findById(problemDTO.getSectionId());
        if(!optionalSection.isPresent())
            return new Response("Section id not found!", false);
        problem.setSection(optionalSection.get());

        problemRepository.save(problem);
        return new Response("Problem saved!", true);
    }

    @Override
    public List<Problem> findAll() {
        return problemRepository.findAll();
    }

    @Override
    public Problem finOneById(Integer id) {
        Optional<Problem> optionalProblem = problemRepository.findById(id);
        return optionalProblem.orElse(null);
    }

    @Override
    public Response edit(ProblemDTO problemDTO, Integer id) {
        Optional<Problem> optionalProblem = problemRepository.findById(id);
        if(optionalProblem.isPresent()){
            optionalProblem.get().setTitle(problemDTO.getTitle());
            optionalProblem.get().setBody(problemDTO.getBody());
            optionalProblem.get().setProblem(problemDTO.getProblem());
            optionalProblem.get().setSolution(problemDTO.getSolution());

            Optional<Section> optionalSection = sectionRepository.findById(problemDTO.getSectionId());
            if(!optionalSection.isPresent())
                return new Response("Section id not found!", false);

            problemRepository.save(optionalProblem.get());
            return new Response("Problem updated!", true);
        }
        return new Response("Problem not found!", false);
    }

    @Override
    public Response delete(Integer id) {
        Optional<Problem> optionalProblem = problemRepository.findById(id);
        if(optionalProblem.isPresent()){
            problemRepository.deleteById(id);
            return new Response("Problem deleted!", true);
        }
        return new Response("Problem not found!", false);
    }
}
