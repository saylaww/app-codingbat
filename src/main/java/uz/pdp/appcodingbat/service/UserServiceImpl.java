package uz.pdp.appcodingbat.service;

import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entity.User;
import uz.pdp.appcodingbat.payload.Response;
import uz.pdp.appcodingbat.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Response save(User user) {
        if(userRepository.existsByUsername(user.getUsername()))
            return new Response("Username is already exists!", false);
        userRepository.save(user);
        return new Response("User saved!", true);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User finOneById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    @Override
    public Response edit(User user, Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            optionalUser.get().setUsername(user.getUsername());
            optionalUser.get().setFirstname(user.getFirstname());
            optionalUser.get().setLastname(user.getLastname());
            optionalUser.get().setEmail(user.getEmail());

            userRepository.save(optionalUser.get());
            return new Response("User data updated!", true);
        }
        return new Response("User id not found!",false);
    }

    @Override
    public Response delete(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            userRepository.deleteById(id);
            return new Response("User deleted!", true);
        }
        return new Response("User id not found!",false);
    }
}
