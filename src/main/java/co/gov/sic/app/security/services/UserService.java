package co.gov.sic.app.security.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import co.gov.sic.app.security.models.UserModel;

@Transactional(readOnly = true)
public interface UserService {

    List<UserModel> findAll();

    UserModel findById(Long id);

    UserModel findOne(String username);

    @Transactional(readOnly = false)
    UserModel save(UserModel user);

    @Transactional(readOnly = false)
    void authenticationFailed(String username);

    @Transactional(readOnly = false)
	void delete(long id);
}
