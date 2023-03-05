package br.com.davilnv.companyctrl.api.repository;

import br.com.davilnv.companyctrl.api.models.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    public Optional<UserModel> findByUsername(String username);
    public Boolean existsByUsername(String username);

}
