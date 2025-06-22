package org.example.gotalearn;

import lombok.RequiredArgsConstructor;
import org.example.gotalearn.dao.*;
import org.example.gotalearn.entity.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@SpringBootApplication
@RequiredArgsConstructor
public class GotaLearnApplication {
    private final StudentDao studentDao;
    private final AdminDao adminDao;
    private final InstructorDao instructorDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;
    @Bean @Transactional @Profile("dev")
    public ApplicationRunner runner(){
        return args -> {
            Role studentRole=new Role();
            studentRole.setRoleName("ROLE_STUDENT");

            Role instructorRole=new Role();
            instructorRole.setRoleName("ROLE_INSTRUCTOR");

            Role adminRole=new Role();
            adminRole.setRoleName("ROLE_ADMIN");

            Instructor instructor=new Instructor();
            instructor.setUsername("john");
            instructor.setPassword(passwordEncoder
                    .encode("12345"));
            instructor.setEducation("MASTERATE");
            instructor.setEmail("john@gmail.com");
            instructor.addExperties("Java");
            instructor.addExperties("Python");
            instructor.setNetWorth(new BigDecimal(0));
            instructor.addRole(instructorRole);

            Student student=new Student();
            student.setUsername("mary");
            student.setPassword(passwordEncoder.encode("12345"));
            student.setEmail("mary@gmail.com");
            student.setAddress("79 Park Avenue.Yangon");
            student.setEducation(Education.UNDERGRADUATE);
            student.addRole(studentRole);


            Admin admin=new Admin();
            admin.setUsername("richard");
            admin.setPassword(passwordEncoder.encode("12345"));
            admin.setPlatformShare(new BigDecimal(0));
            admin.addRole(adminRole);

            roleDao.save(adminRole);
            roleDao.save(studentRole);
            roleDao.save(instructorRole);

            adminDao.save(admin);
            studentDao.save(student);
            instructorDao.save(instructor);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(GotaLearnApplication.class, args);
    }

}
