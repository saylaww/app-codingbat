package uz.pdp.appcodingbat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPractise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Problem problem;

    @NotNull(message = "Solution can not be empty")
    private String userSolution;

    @NotNull(message = "Score can not be empty")
    private Integer score;

    @NotNull(message = "Date can not be empty")
    private Date date;

}
