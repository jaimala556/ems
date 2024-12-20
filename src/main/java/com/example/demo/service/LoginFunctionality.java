package com.example.demo.service;

import com.example.demo.model.Users;
import com.example.demo.repository.UsersRepo;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class LoginFunctionality {
    @Autowired
    private UsersRepo usersRepo;
    @Autowired private OtpGenerator otpGenerator;
    @Autowired private RandomPasswordGenerator randomPasswordGenerator;
    @Autowired private EmailSendService emailSendService;

    public String addUser(String firstName, String email, ObjectId id){
        String password=randomPasswordGenerator.generateRandomPassword();
        String subject="";
        String body="email : "+email+" \npassword : "+password;
        emailSendService.sendEmail(email,subject,body);

        Users users=new Users();
        users.setFirstName(firstName);
        users.setEmail(email);
        users.setPassword(password);
        users.setActive(users.isActive());
        users.setLastUpdate(LocalDateTime.now());
        usersRepo.save(users);
        return "User is add successfully";

    }
    public String Login(String email,String password){
        Users users=usersRepo.findByEmail(email).orElse(null);
        if(users==null){
            return "user not found....";
        }
        if(users.getPassword().equals(randomPasswordGenerator.hashPassword(password)))
        {
            if(users.isActive()) return randomPasswordGenerator.generateRandomPassword();
            return "unable to login..";
        }
        return "user password not match.. ";
    }

    public String forget(String email,String password){
        Users users=usersRepo.findByEmail(email).orElse(null);
        if(users==null){
            return "user not found....";
        }
        int otp=otpGenerator.generateOtp();
        String subject="";
        String body=""+otp;
        emailSendService.sendEmail(email,subject,body);

        users.setOtp(otp);
        users.setPassword(password);
        users.setActive(users.isActive());
        users.setLastUpdate(LocalDateTime.now());
        usersRepo.save(users);
        return "forget password successfully..";
    }

    public String resetPassword(ResetPassword resetPassword){
        if(!(resetPassword.getPassword().length()<8) && (resetPassword.getPassword().length()>40));
        Users users=usersRepo.findByEmail(resetPassword.email).orElse(null);
        if(users==null){
            return "user not found....";
        }
        if(!(users.getOtp()==resetPassword.otp)) return "otp not matched";

        users.setOtp(0);
        users.setPassword(randomPasswordGenerator.hashPassword(resetPassword.password));
        users.setLastUpdate(LocalDateTime.now());
        usersRepo.save(users);
        return "password is reset successfully...";
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class ResetPassword{
        int otp;
        String email,password;
    }
}
