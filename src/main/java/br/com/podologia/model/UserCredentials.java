package br.com.podologia.model;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_credentials")
@Builder
@Data
public class UserCredentials {

    @Id
    private String id;
    private String email;
    private String password;
    private Integer validationCode;
}
