package uz.pdp.appcodingbat.service;

import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entity.Course;
import uz.pdp.appcodingbat.entity.Problem;
import uz.pdp.appcodingbat.entity.User;
import uz.pdp.appcodingbat.entity.UserPractise;
import uz.pdp.appcodingbat.payload.Response;
import uz.pdp.appcodingbat.payload.UserPractiseDTO;
import uz.pdp.appcodingbat.repository.ProblemRepository;
import uz.pdp.appcodingbat.repository.UserPractiseRepository;
import uz.pdp.appcodingbat.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserPractiseServiceImpl implements UserPractiseService {

    final UserPractiseRepository userPractiseRepository;
    final UserRepository userRepository;
    final ProblemRepository problemRepository;

    public UserPractiseServiceImpl(UserPractiseRepository userPractiseRepository,
                                   UserRepository userRepository, ProblemRepository problemRepository) {
        this.userPractiseRepository = userPractiseRepository;
        this.userRepository = userRepository;
        this.problemRepository = problemRepository;
    }

    @Override
    public Response save(UserPractiseDTO userPractiseDTO) {
        UserPractise userPractise = new UserPractise();
        userPractise.setUserSolution(userPractiseDTO.getUserSolution());
        userPractise.setScore(userPractiseDTO.getScore());
        userPractise.setDate(userPractiseDTO.getDate());

        Optional<User> optionalUser = userRepository.findById(userPractiseDTO.getUserId());
        if(!optionalUser.isPresent())
            return new Response("User id not found!", false);
        userPractise.setUser(optionalUser.get());

        Optional<Problem> optionalProblem = problemRepository.findById(userPractiseDTO.getProblemId());
        if(!optionalProblem.isPresent())
            return new Response("Problem id not found!", false);
        userPractise.setProblem(optionalProblem.get());

        userPractiseRepository.save(userPractise);

        return new Response("User practise saved!", true);
    }

    @Override
    public List<UserPractise> findAll() {
        return userPractiseRepository.findAll();
    }

    @Override
    public UserPractise finOneById(Integer id) {
        Optional<UserPractise> optionalUserPractise = userPractiseRepository.findById(id);
        return optionalUserPractise.orElse(null);
    }

    @Override
    public Response edit(UserPractiseDTO userPractiseDTO, Integer id) {
        Optional<UserPractise> optionalUserPractise = userPractiseRepository.findById(id);
        if(optionalUserPractise.isPresent()){
            optionalUserPractise.get().setUserSolution(userPractiseDTO.getUserSolution());
            optionalUserPractise.get().setScore(userPractiseDTO.getScore());
            optionalUserPractise.get().setDate(userPractiseDTO.getDate());

            Optional<User> optionalUser = userRepository.findById(userPractiseDTO.getUserId());
            if(!optionalUser.isPresent())
                return new Response("User id not found!", false);


            Optional<Problem> optionalProblem = problemRepository.findById(userPractiseDTO.getProblemId());
            if(!optionalProblem.isPresent())
                return new Response("Problem id not found!", false);


            userPractiseRepository.save(optionalUserPractise.get());

            return new Response("User practise updated!", true);
        }
        return new Response("User practise id not found!",false);
    }

    @Override
    public Response delete(Integer id) {
        Optional<UserPractise> optionalUserPractise = userPractiseRepository.findById(id);
        if(optionalUserPractise.isPresent()){
            userPractiseRepository.deleteById(id);

            return new Response("User practise deleted!", true);
        }
        return new Response("User practise id not found!",false);
    }
}
