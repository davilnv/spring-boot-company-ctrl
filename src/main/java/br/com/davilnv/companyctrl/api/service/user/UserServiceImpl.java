package br.com.davilnv.companyctrl.api.service.user;

import br.com.davilnv.companyctrl.api.repository.UserRepository;
import br.com.davilnv.companyctrl.api.models.user.UserModel;
import br.com.davilnv.companyctrl.api.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.repository =userRepository;
    }


    @Override
    public void saveUser(UserModel user) {
        repository.save(user);
    }

    @Override
    public Optional<UserModel> getUserByUsername(String username) throws UserNotFoundException {
        Optional<UserModel> user = repository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UserNotFoundException("Usuário inválido");
        }
        return user;
    }

    @Override
    public List<UserModel> findAll() {
        return repository.findAll();
    }

    @Override
    public Boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }
}
