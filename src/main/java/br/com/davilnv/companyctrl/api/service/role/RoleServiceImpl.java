package br.com.davilnv.companyctrl.api.service.role;

import br.com.davilnv.companyctrl.api.exception.RolesNotFoundException;
import br.com.davilnv.companyctrl.api.models.roles.RoleModel;
import br.com.davilnv.companyctrl.api.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository repository;

    @Autowired
    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }


    @Override
    public void saveRole(RoleModel role) {
        repository.save(role);
    }

    @Override
    public Optional<RoleModel> findByName(String name) throws RolesNotFoundException {
        Optional<RoleModel> role = repository.findByName(name);
        if (role.isEmpty()) {
            throw new RolesNotFoundException("Não foi encontrado nenhuma permissão com o nome '"+name+"'");
        }
        return role;
    }

    @Override
    public List<RoleModel> findAll() {
        return repository.findAll();
    }
}
