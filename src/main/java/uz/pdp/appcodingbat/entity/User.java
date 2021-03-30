package uz.pdp.appcodingbat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Username can not be empty")
    private String username;

    @NotNull(message = "Firstname can not be empty")
    private String firstname;

    @NotNull(message = "Lastname can not be empty")
    private String lastname;

    @NotNull(message = "Email can not be empty")
    private String email;
}
