package uz.pdp.appcodingbat.service;

import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entity.Course;
import uz.pdp.appcodingbat.entity.UserPractise;
import uz.pdp.appcodingbat.payload.Response;
import uz.pdp.appcodingbat.payload.UserPractiseDTO;

import java.util.List;

@Service
public interface UserPractiseService {

    Response save(UserPractiseDTO userPractiseDTO);                      // CREATE

    List<UserPractise> findAll();                                // READ

    UserPractise finOneById(Integer id);

    Response edit(UserPractiseDTO userPractiseDTO, Integer id);           // UPDATE

    Response delete(Integer id);                                          // DELETE

}
