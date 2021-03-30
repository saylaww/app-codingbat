package uz.pdp.appcodingbat.payload;

import lombok.Data;

@Data
public class ProblemDTO {

    private String title;
    private String body;
    private String problem;
    private String solution;
    private Integer sectionId;

}
