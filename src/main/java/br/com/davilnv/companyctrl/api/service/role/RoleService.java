package br.com.davilnv.companyctrl.api.service.role;

import br.com.davilnv.companyctrl.api.exception.RolesNotFoundException;
import br.com.davilnv.companyctrl.api.models.roles.RoleModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RoleService {

    public void saveRole(RoleModel role);

    public Optional<RoleModel> findByName(String name) throws RolesNotFoundException;

    public List<RoleModel> findAll();

}
