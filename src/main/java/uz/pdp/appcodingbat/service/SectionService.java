package uz.pdp.appcodingbat.service;

import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.payload.Response;
import uz.pdp.appcodingbat.entity.Section;
import uz.pdp.appcodingbat.payload.SectionDTO;

import java.util.List;

@Service
public interface SectionService {

    Response save(SectionDTO sectionDTO);                      // CREATE

    List<Section> findAll();                                // READ

    Section finOneById(Integer id);

    Response edit(SectionDTO sectionDTO, Integer id);           // UPDATE

    Response delete(Integer id);                  // DELETE

}
