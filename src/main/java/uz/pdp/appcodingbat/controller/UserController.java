package uz.pdp.appcodingbat.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.constants.FrontUrls;
import uz.pdp.appcodingbat.entity.User;
import uz.pdp.appcodingbat.payload.Response;
import uz.pdp.appcodingbat.service.UserService;

import java.util.List;

@RestController
@RequestMapping(FrontUrls.USER)
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public HttpEntity<Response> save(@RequestBody User user) {
        Response response = userService.save(user);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.OK : HttpStatus.CONFLICT).body(response);
    }

    @GetMapping
    public HttpEntity<?> findAll(){
        List<User> userList = userService.findAll();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> findOneById(@PathVariable Integer id){
        User user = userService.finOneById(id);
        if(user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("User id not found", false));
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public HttpEntity<Response> edit(@RequestBody User user, @PathVariable Integer id) {
        Response response = userService.edit(user, id);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<Response> delete(@PathVariable Integer id) {
        Response response = userService.delete(id);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND).body(response);
    }
}
