package uz.pdp.appcodingbat.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.constants.FrontUrls;
import uz.pdp.appcodingbat.entity.UserPractise;
import uz.pdp.appcodingbat.payload.Response;
import uz.pdp.appcodingbat.payload.UserPractiseDTO;
import uz.pdp.appcodingbat.service.UserPractiseService;

import java.util.List;

@RestController
@RequestMapping(FrontUrls.USER_PRACTISE)
public class UserPractiseController {

    final UserPractiseService userPractiseService;

    public UserPractiseController(UserPractiseService userPractiseService) {
        this.userPractiseService = userPractiseService;
    }

    @PostMapping
    public HttpEntity<Response> save(@RequestBody UserPractiseDTO userPractiseDTO) {
        Response response = userPractiseService.save(userPractiseDTO);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.OK : HttpStatus.CONFLICT).body(response);
    }

    @GetMapping
    public HttpEntity<?> findAll(){
        List<UserPractise> sectionList = userPractiseService.findAll();
        return ResponseEntity.ok(sectionList);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> findOneById(@PathVariable Integer id){
        UserPractise userPractise = userPractiseService.finOneById(id);
        if(userPractise == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("User Practise id not found", false));
        return ResponseEntity.ok(userPractise);
    }

    @PutMapping("/{id}")
    public HttpEntity<Response> edit(@RequestBody UserPractiseDTO userPractiseDTO, @PathVariable Integer id) {
        Response response = userPractiseService.edit(userPractiseDTO, id);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<Response> delete(@PathVariable Integer id) {
        Response response = userPractiseService.delete(id);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND).body(response);
    }
}
