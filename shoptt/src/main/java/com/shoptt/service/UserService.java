package com.shoptt.service;

import com.shoptt.config.SecurityUtils;
import com.shoptt.dto.CustomUserDetails;
import com.shoptt.entity.User;
import com.shoptt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.get() == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user.get());
    }

    public User getUserWithAuthority(){
        Long id =Long.valueOf(SecurityUtils.getCurrentUserLogin().get());
        return userRepository.findById(id).get();
    }

    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActived(0);
        user.setActivation_key(randomKey());
        user.setCreated_date(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);
        return user;
    }

    public void update(User user){
        userRepository.save(user);
    }

    public Optional<User> findByUsernameAndPassword(String username, String password){
        String passwordHash = passwordEncoder.encode(password);
        Optional<User> user = userRepository.findByUsername(username);

        if(user.isPresent()){
            if(user.get().getActived() == 0){
                return null;
            }
            if(passwordEncoder.matches(password, user.get().getPassword())){
                return user;
            }
        }
        return null;
    }

    public String randomPass(){
        String str = "qwert1yui2op3as4dfg5hj6klzx7cvb8nmQ9WE0RTYUIOPASDFGHJKLZXCVBNM";
        Integer length = str.length()-1;
        StringBuilder stringBuilder = new StringBuilder("");
        for(int i=0; i<7; i++){
            Integer ran = (int)(Math.random()*length);
            stringBuilder.append(str.charAt(ran));
        }
        return String.valueOf(stringBuilder);
    }

    public String randomKey(){
        String str = "qwert1yui2op3as4dfg5hj6klzx7cvb8nmQ9WE0RTYUIOPASDFGHJKLZXCVBNM";
        Integer length = str.length()-1;
        StringBuilder stringBuilder = new StringBuilder("");
        for(int i=0; i<10; i++){
            Integer ran = (int)(Math.random()*length);
            stringBuilder.append(str.charAt(ran));
        }
        return String.valueOf(stringBuilder);
    }

    public boolean checkEmailexist(String email){
        if(userRepository.countUserByEmail(email)>0){
            return true;
        }
        return false;
    }

}

