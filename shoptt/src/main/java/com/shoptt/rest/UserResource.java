package com.shoptt.rest;

import com.shoptt.dto.CustomUserDetails;
import com.shoptt.dto.UserDto;
import com.shoptt.entity.AddressUser;
import com.shoptt.entity.User;
import com.shoptt.jwt.JwtTokenProvider;
import com.shoptt.repository.AddressUserRepository;
import com.shoptt.repository.UserRepository;
import com.shoptt.service.MailService;
import com.shoptt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
@Validated
public class UserResource {
    private final UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final MailService mailService;

    private final AddressUserRepository addressUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResource(UserRepository userRepository, JwtTokenProvider jwtTokenProvider, UserService userService, MailService mailService, AddressUserRepository addressUserRepository) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.mailService = mailService;
        this.addressUserRepository = addressUserRepository;
    }
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody User user) throws URISyntaxException {
        Optional<User> users = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        System.out.println(users);
        if(users.isPresent() == false){
            return ResponseEntity.status(401)
                    .body(null);
        }
        CustomUserDetails customUserDetails = new CustomUserDetails(users.get());
        String token = jwtTokenProvider.generateToken(customUserDetails);
        return ResponseEntity
                .created(new URI("/api/authen/" ))
                .body(token);
    }

    @PostMapping("/register")
    public ResponseEntity<Integer> save(@Validated @RequestBody User user) throws URISyntaxException {
        if(userService.checkEmailexist(user.getEmail())){
            HttpHeaders headers = new HttpHeaders();
            headers.add("email already exist ", user.getEmail());
            return ResponseEntity.status(300).headers(headers)
                    .body(1);
        }
        else if(userRepository.findByUsername(user.getUsername()).isPresent() == true){
            HttpHeaders headers = new HttpHeaders();
            return ResponseEntity.status(500).headers(headers)
                    .body(2);
        }
        User result = userService.save(user);
        AddressUser addressUser = new AddressUser();
        addressUser.setVillage(result.getVillage());
        addressUser.setTenDuong(result.getTenDuong());
        addressUser.setUser(result);
        addressUserRepository.save(addressUser);

        System.out.println(result);
        mailService.sendEmail(user.getEmail(), "Kích hoạt tài khoản website","truy cập vào link sau để xác thực tài khoản: http://localhost:8080/keyactive?key="+result.getActivation_key(),false, false);
        return ResponseEntity
                .created(new URI("/api/save/" + result.getId()))
                .body(0);
    }

    @GetMapping("")
    public void finishRegistration(){

    }


    @PostMapping("/updateUser")
    public ResponseEntity<Integer> update(@RequestBody User user) throws URISyntaxException {
        User u = userService.getUserWithAuthority();
        if(u.getId() != user.getId()){
            return ResponseEntity.status(500).body(0);
        }
        u.setEmail(user.getEmail());
        u.setPhone(user.getPhone());
        u.setAvatar(user.getAvatar());
        User result = userRepository.save(u);
        System.out.println(result);
        return ResponseEntity.status(200).body(1);
    }


    @PostMapping("/userlogged")
    public UserDto getUserLogged(){
        return new UserDto(userService.getUserWithAuthority());
    }

    @PostMapping("/user/updateinfor")
    public void updateInfor(@RequestBody UserDto userDto){
        User user = userService.getUserWithAuthority();
        user.setFullname(userDto.getFullname());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        userRepository.save(user);
    }

    @PostMapping("/user/changePassword")
    public void changePassword(@RequestParam("old") String oldPass, @RequestParam("new") String newPass) throws Exception {
        User user = userService.getUserWithAuthority();
        if(passwordEncoder.matches(oldPass, user.getPassword())){
            user.setPassword(passwordEncoder.encode(newPass));
        }
        else{
            throw new Exception("password khong dung");
        }
        userRepository.save(user);
    }

    @PostMapping("/confirm-regis")
    public void confirmRegis(@RequestParam("key") String key) throws Exception {
        Optional<User> user = userRepository.getUserByActivationKey(key);
        if(user.isPresent() == false){
            throw new Exception("No user was found for this activation key");
        }
        else{
            user.get().setActivation_key(null);
            user.get().setActived(1);
            userService.update(user.get());
        }
    }

    @PostMapping("/resetpass-init")
    public ResponseEntity<String> resetPassword(@RequestBody String email) throws URISyntaxException {
        Optional<User> user = userRepository.getUserByEmail(email);
        if(user.isPresent() == false){
            return ResponseEntity.status(500)
                    .body("this email not exist");
        }
        else{
            String remember_key = userService.randomKey();
            User users = user.get();
            users.setRemember_key(remember_key);
            userService.update(users);
            mailService.sendEmail(email,"resetmail","truy cập vào link sau để đặt lại mật khẩu: http://localhost:8080/keyforget, sử dụng key sau để đặt lại mật khẩu của bạn:"+remember_key,false, false);
        }
        return ResponseEntity.status(200)
                .body("check your email");
    }

//    @PostMapping("/resetpass-finish")
//    public ResponseEntity<String> finisRememberPassword(@RequestBody KeyAndPasswordDto keyAndPasswordDto){
//        Optional<User> user = userRepository.getUserByRememberKey(keyAndPasswordDto.getKey());
//        if(user.isPresent() == false){
//            return ResponseEntity.status(500)
//                    .body("key wrong! please check your email");
//        }
//        else{
//            user.get().setRemember_key(null);
//            user.get().setPassword(PasswordHash.passwordHash(keyAndPasswordDto.getNewPassword()));
//            userService.update(user.get());
//        }
//        return ResponseEntity.status(200)
//                .body("change password successful!");
//    }

    @GetMapping("/admin/getUserNotAdmin")
    public List<User> getUserNotAdmin() {
        return userRepository.findUserNotAdmin("ROLE_ADMIN");
    }

    @GetMapping("/admin/getUserNotAdminThisMonth")
    public List<User> getUserNotAdminThisMonth() {
        String date = new Date(System.currentTimeMillis()).toString();
        return userRepository.findUserNotAdminThisMonth("ROLE_ADMIN",Integer.valueOf(date.split("-")[1]), Integer.valueOf(date.split("-")[0]));
    }

    @GetMapping("/admin/getUserNotAdminByDate")
    public List<User> getUserNotAdminByDate(@RequestParam("d1") Date d1, @RequestParam("d2") Date d2) {
        System.out.println("d1: "+d1);
        System.out.println("d2: "+d2);
        return userRepository.findUserNotAdminAndDate("ROLE_ADMIN", d1, d2);
    }

    @GetMapping("/admin/getUserNotUser")
    public List<User> getUserNotUser() {
        return userRepository.findUserNotAdmin("ROLE_USER");
    }

    @PostMapping("/admin/activeUser")
    public void activeOrUnactiveUser(@RequestParam("id") Long id){
        User user = userRepository.findById(id).get();
        if(user.getActived() == 1){
            user.setActived(0);
        }
        else{
            user.setActived(1);
        }
        userRepository.save(user);
    }

    @GetMapping("/public/findUserById")
    public UserDto findById(@RequestParam("id") Long id) {
        return new UserDto(userRepository.findById(id).get());
    }

    @GetMapping("/public/findUserNotDtoById")
    public User findUserById(@RequestParam("id") Long id) {
        return userRepository.findById(id).get();
    }

    @PostMapping("/resetpass")
    public ResponseEntity<String> forgotpass(@RequestBody String email) throws URISyntaxException {
        Optional<User> user = userRepository.getUserByEmail(email);
        if(user.isPresent() == false){
            return ResponseEntity.status(500)
                    .body("this email not exist");
        }
        else{
            String newPass = userService.randomPass();
            User users = user.get();
            users.setPassword(passwordEncoder.encode(newPass));
            userRepository.save(users);
            mailService.sendEmail(email,"Đặt lại mật khẩu website shoptt","Mật khẩu mới của bạn là: "+newPass,false, false);
        }
        return ResponseEntity.status(200)
                .body("check your email");
    }

    @GetMapping("/admin/checkroleadmin")
    public void checkAdmin(){
        System.out.println("admin");
    }

    @GetMapping("/user/checkroleUser")
    public void checkroleUser(){
        System.out.println("user");
    }
}
