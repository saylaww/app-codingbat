package uz.pdp.appcodingbat.payload;

import lombok.Data;

@Data
public class ProblemTestDTO {

    private String arguments;
    private String result;
    private Integer problemId;
}
