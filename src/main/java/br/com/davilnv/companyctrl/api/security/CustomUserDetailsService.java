package br.com.davilnv.companyctrl.api.security;

import br.com.davilnv.companyctrl.api.models.roles.RoleModel;
import br.com.davilnv.companyctrl.api.models.user.UserModel;
import br.com.davilnv.companyctrl.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public CustomUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<UserModel> user = Optional.ofNullable(repository.findByUsername(username).
//                orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado")));
        Optional<UserModel> userDB = repository.findByUsername(username);
        if (userDB.isEmpty()) {
            throw new UsernameNotFoundException("Usuário '"+username+"' não encontrado!");
        }
        UserModel user = userDB.get();

        return new User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<RoleModel> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
