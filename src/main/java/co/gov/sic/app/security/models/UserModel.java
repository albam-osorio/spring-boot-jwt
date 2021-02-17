package co.gov.sic.app.security.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private String username;

    @JsonIgnore
	private String password;
    
    private int attempts;
    
    private boolean locked;
}
