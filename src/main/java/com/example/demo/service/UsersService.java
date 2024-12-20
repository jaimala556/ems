package com.example.demo.service;

import com.example.demo.helper.UsersHelper;
import com.example.demo.model.Users;
import com.example.demo.repository.UsersRepo;
import lombok.*;
import org.apache.catalina.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class UsersService {
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private RandomPasswordGenerator randomPasswordGenerator;
    @Autowired
    private EmailSendService emailSendService;
    @Autowired
    private OtpGenerator otpGenerator;


    public void addUsers(String firstName, String email, ObjectId empId) {
        String password = randomPasswordGenerator.generateRandomPassword();

        //        to set email subject and body
        String subject = " Your Account Has Been Created Successfully.";
        String body = "Dear " + firstName + ",  \n" +
                "\n" +
                "We are pleased to inform you that your account on EMS has been created successfully.  \n" +
                "\n" +
                "Here are your account details:  \n" +
                "\n" +
                "*Email:" + email + "  \n" +
                "*Password:" + password + "  \n" +
                "\n" +
                "You can now log in to access your account and explore the features we provide.  \n" +
                "\n" +
                "If you have any questions or require assistance, feel free to contact us at prajapatijaimala556@gmail.com.  \n" +
                "\n" +
                "Thank you for choosing [Your App/Company Name]. We look forward to serving you.  \n" +
                "\n" +
                "Best regards,  \n" +
                "Jai mala\n";
        emailSendService.sendEmail(email, subject, body);
        Users users = new Users();
        users.setFirstName(firstName);
        users.setEmail(email);
        users.setId(empId);
        users.setPassword(randomPasswordGenerator.hashPassword(password));
        users.setLastUpdate(LocalDateTime.now());
        users.setActive(true);
        usersRepo.save(users);

    }

    public String login(UsersHelper usersHelper) {
        Users users = usersRepo.findByEmail(usersHelper.getEmail()).orElse(null);
        if (users==null) {
            return "user not found";
        }
        if (users.getPassword().equals(randomPasswordGenerator.hashPassword(usersHelper.getPassword()))) {
            if (users.isActive()) return randomPasswordGenerator.generateRandomPassword();
            return "Unable to login, contact your senior";
        }
        return "password not matched";
    }

    public String forget(String email) {
        Users users = usersRepo.findByEmail(email).orElse(null);
        if (users == null) {
            return "user not found";
        }
        int otp = otpGenerator.generateOtp();
        String subject = "";
        String body = ""+otp;
        emailSendService.sendEmail(email, subject, body);

        users.setOtp(otp);
        users.setPassword("");
        users.setLastUpdate(LocalDateTime.now());
        usersRepo.save(users);
        return "you received otp on your email ";
    }
    public String resetPassword(ResetPassword resetPassword) {

        if (!(resetPassword.getPassword().length() < 8) && (resetPassword.getPassword().length() > 40)) {
            return "Invalid password, password must be 8-40 characters";
        }
        Users users = usersRepo.findByEmail(resetPassword.getEmail()).orElse(null);
        if (users == null) return "Invalid email";
        if (!(users.getOtp() == resetPassword.getOtp())) return "Incorrect otp";
        users.setPassword(randomPasswordGenerator.hashPassword(resetPassword.getPassword()));
        users.setOtp(0);
        users.setLastUpdate(LocalDateTime.now());
        usersRepo.save(users);
        return "Your password has been changed";
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter

    public static class ResetPassword {
        int otp;
        String email, password;
}





}
