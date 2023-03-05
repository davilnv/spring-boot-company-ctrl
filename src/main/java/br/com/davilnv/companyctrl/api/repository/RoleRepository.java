package br.com.davilnv.companyctrl.api.repository;

import br.com.davilnv.companyctrl.api.models.roles.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Long> {

    public Optional<RoleModel> findByName(String name);

}
