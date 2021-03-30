package uz.pdp.appcodingbat.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.constants.FrontUrls;
import uz.pdp.appcodingbat.entity.Section;
import uz.pdp.appcodingbat.payload.Response;
import uz.pdp.appcodingbat.payload.SectionDTO;
import uz.pdp.appcodingbat.service.SectionService;

import java.util.List;

@RestController
@RequestMapping(FrontUrls.SECTION)
public class SectionController {

    final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @PostMapping
    public HttpEntity<Response> save(@RequestBody SectionDTO sectionDTO) {
        Response response = sectionService.save(sectionDTO);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.OK : HttpStatus.CONFLICT).body(response);
    }

    @GetMapping
    public HttpEntity<?> findAll(){
        List<Section> sectionList = sectionService.findAll();
        return ResponseEntity.ok(sectionList);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> findOneById(@PathVariable Integer id){
        Section section = sectionService.finOneById(id);
        if(section == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Section id not found", false));
        return ResponseEntity.ok(section);
    }

    @PutMapping("/{id}")
    public HttpEntity<Response> edit(@RequestBody SectionDTO sectionDTO, @PathVariable Integer id) {
        Response response = sectionService.edit(sectionDTO, id);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<Response> delete(@PathVariable Integer id) {
        Response response = sectionService.delete(id);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND).body(response);
    }
}
