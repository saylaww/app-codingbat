package uz.pdp.appcodingbat.payload;

import lombok.Data;
import java.util.Date;

@Data
public class UserPractiseDTO {

    private Integer userId;
    private Integer problemId;
    private String userSolution;
    private Integer score;
    private Date date;

}
