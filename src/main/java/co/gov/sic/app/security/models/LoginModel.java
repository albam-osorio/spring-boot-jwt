package co.gov.sic.app.security.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginModel {

    private String username;

    private String password;
}
