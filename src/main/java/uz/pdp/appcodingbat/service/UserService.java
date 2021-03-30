package uz.pdp.appcodingbat.service;

import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entity.Course;
import uz.pdp.appcodingbat.entity.User;
import uz.pdp.appcodingbat.payload.Response;

import java.util.List;

@Service
public interface UserService {

    Response save(User user);                      // CREATE

    List<User> findAll();                                // READ

    User finOneById(Integer id);

    Response edit(User user, Integer id);           // UPDATE

    Response delete(Integer id);                  // DELETE

}
