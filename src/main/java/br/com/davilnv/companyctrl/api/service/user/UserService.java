package br.com.davilnv.companyctrl.api.service.user;

import br.com.davilnv.companyctrl.api.exception.UserNotFoundException;
import br.com.davilnv.companyctrl.api.models.user.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface UserService {

    public void saveUser(UserModel user);
    public Optional<UserModel> getUserByUsername(String username) throws UserNotFoundException;

    public List<UserModel> findAll();

    public Boolean existsByUsername(String username);

}
