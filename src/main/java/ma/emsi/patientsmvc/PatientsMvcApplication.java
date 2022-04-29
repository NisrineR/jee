package ma.emsi.patientsmvc;

import ma.emsi.patientsmvc.entities.Patient;
import ma.emsi.patientsmvc.repositories.PatientRepository;
import ma.emsi.patientsmvc.sec.services.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientsMvcApplication.class, args);

    }
    @Bean
    PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }
    //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
           patientRepository.save(new Patient(null,"Nisrine",new Date(),false,15));
           patientRepository.save(new Patient(null,"leila",new Date(),true,155));
           patientRepository.save(new Patient(null,"khadija",new Date(),false,13));
           patientRepository.save(new Patient(null,"amine",new Date(),true,11));
           patientRepository.findAll().forEach(p->{
               System.out.println(p.getNom());
           });
        };
    }
    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("nisrine","1234","1234");
            securityService.saveNewUser("raihane","1234","1234");
            securityService.saveNewRole("USER","");
            securityService.saveNewRole("ADMIN","admin");

            securityService.addRoleToUser("nisrine","ADMIN");
            securityService.addRoleToUser("nisrine","USER");
            securityService.addRoleToUser("raihane","USER");

        };
    }

}
