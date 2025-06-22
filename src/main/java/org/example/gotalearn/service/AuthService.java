package org.example.gotalearn.service;

import lombok.RequiredArgsConstructor;
import org.example.gotalearn.dao.InstructorDao;
import org.example.gotalearn.dao.RoleDao;
import org.example.gotalearn.dao.StudentDao;
import org.example.gotalearn.entity.Education;
import org.example.gotalearn.entity.Instructor;
import org.example.gotalearn.entity.Role;
import org.example.gotalearn.entity.Student;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final InstructorDao instructorDao;
    private final StudentDao studentDao;
    private final RoleDao roleDao;

    public String login(String username, String password) {
        Authentication authentication=authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    username,password
            )
        );
        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        StringBuilder sb=new StringBuilder();
        for(var role:authentication.getAuthorities()){
            sb.append(role.getAuthority());
        }
        return sb.toString();
    }
    @Transactional
    public String register(String username,
                           String password,
                           String email,
                           String address,
                           String education,
                           String experties,
                           String userType) {
       String response= switch (userType) {
            case "instructor" -> {
                Role instructorRole=roleDao
                        .findByRoleName("ROLE_INSTRUCTOR").orElseThrow();
                Instructor instructor=new Instructor();
                instructor.setUsername(username);
                instructor.setPassword(passwordEncoder.encode(password));
                instructor.setEmail(email);
                instructor.setEducation(education);
                instructor.addExperties(experties);
                instructor.setNetWorth(new BigDecimal(0));
                instructor.addRole(instructorRole);
                instructorDao.save(instructor);
                yield  "Instructor registered successfully!";
            }
            case "student" -> {
                Role studentRole= roleDao.findByRoleName("ROLE_STUDENT")
                        .orElseThrow();
                Student student=new Student();
                student.setUsername(username);
                student.setPassword(passwordEncoder.encode(password));
                student.setEmail(email);
                student.setAddress(address);
                student.setEducation(Education.valueOf(education));
                student.addRole(studentRole);
                studentDao.save(student);
                yield  "Student registered successfully!";
            }
           default -> {
               yield  "Invalid user type!";
           }
        };
       return response;
    }
}
