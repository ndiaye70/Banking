package Reporting.AFA.Security.Services;

import Reporting.AFA.Entity.AppRole;
import Reporting.AFA.Entity.AppUser;
import Reporting.AFA.Security.repo.AppRoleRepository;
import Reporting.AFA.Security.repo.UserRepository;
import jakarta.persistence.GenerationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AppRoleRepository appRoleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public AppUser addNewUser(String username, String Prenom, String Nom, String email, String password, String confirmPassword) {
        AppUser user=userRepository.findByUsername(username);
        if(user!=null) throw new RuntimeException("L'utilisateur existe deja ");
        if(!password.equals(confirmPassword)) throw new RuntimeException("Les mots de passes doivent être similaires ");
        user=AppUser.builder()
                .id(UUID.randomUUID().toString())
                .username(username)
                .Prenom(Prenom)
                .Nom(Nom)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();
        AppUser savedUser=userRepository.save(user);

        return savedUser;
    }

    @Override
    public AppRole addNewRole(String role) {
        AppRole appRole1=appRoleRepository.findById(role).orElse(null);
        if(appRole1!=null)throw new RuntimeException("Ce role existe deja");
        appRole1=AppRole.builder()
                .role(role)
                .build();


        return appRoleRepository.save(appRole1);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        AppUser user=userRepository.findByUsername(username);
        AppRole appRole=appRoleRepository.findById(role).get();
        user.getRoles().add(appRole);

    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        AppUser user=userRepository.findByUsername(username);
        AppRole appRole=appRoleRepository.findById(role).get();
        user.getRoles().remove(appRole);


    }
    @Override
    public AppUser loadUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

}
