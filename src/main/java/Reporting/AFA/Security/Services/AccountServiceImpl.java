package Reporting.AFA.Security.Services;

import Reporting.AFA.Entity.AppRole;
import Reporting.AFA.Entity.AppUser;
import Reporting.AFA.Security.repo.AppRoleRepository;
import Reporting.AFA.Security.repo.UserRepository;
import Reporting.AFA.dto.AppUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
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

    @Autowired
    public AccountServiceImpl(UserRepository userRepository, AppRoleRepository appRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.appRoleRepository = appRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }
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
    public AppUser NewUser(AppUserDto appUserDto) {
        // Vérifie si l'utilisateur existe déjà
        if (userRepository.findByUsername(appUserDto.getUsername()) != null) {
            throw new RuntimeException("L'utilisateur existe déjà");
        }
        // Vérifie si les mots de passe correspondent
        if (!appUserDto.getPassword().equals(appUserDto.getConfirmPassword())) {
            throw new RuntimeException("Les mots de passe doivent être identiques");
        }

        // Création de l'utilisateur à partir du DTO
        AppUser user = AppUser.builder()
                .id(UUID.randomUUID().toString())
                .username(appUserDto.getUsername())
                .Prenom(appUserDto.getPrenom())
                .Nom(appUserDto.getNom())
                .email(appUserDto.getEmail())
                .password(passwordEncoder.encode(appUserDto.getPassword()))
                .build();


        // Sauvegarde de l'utilisateur
        return userRepository.save(user);
    }

    @Override
    public AppUser save(AppUserDto appUserDto) {
        // Création de l'utilisateur à partir du DTO
        AppUser appUser = appUserDto.toEntity();

        // Vérification si l'utilisateur existe déjà
        AppUser existingUser = userRepository.findByUsername(appUser.getUsername());
        if (existingUser != null)
            throw new RuntimeException("L'utilisateur existe déjà");

        // Attribution automatique du rôle "USER" à l'utilisateur
        AppRole userRole = appRoleRepository.findById("USER")
                .orElseThrow(() -> new RuntimeException("Le rôle USER n'existe pas"));
        appUser.getRoles().add(userRole);

        // Encodage du mot de passe
        appUser.setPassword(passwordEncoder.encode(appUserDto.getPassword()));

        // Sauvegarde de l'utilisateur
        return userRepository.save(appUser);
    }


    @Override
    public void changeUserPassword(String userId, String newPassword) {
        // Recherche de l'utilisateur par ID
        AppUser user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            // Encode le nouveau mot de passe
            String encodedPassword = passwordEncoder.encode(newPassword);

            // Met à jour le mot de passe de l'utilisateur
            user.setPassword(encodedPassword);
            userRepository.save(user);
        } else {
            throw new RuntimeException("User not found");
        }
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

    @Override
    public List<AppUser> getAllUsers(){
        return userRepository.findAll();
    }

}
