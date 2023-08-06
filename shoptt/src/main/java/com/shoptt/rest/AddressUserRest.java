package com.shoptt.rest;

import com.shoptt.entity.AddressUser;
import com.shoptt.entity.User;
import com.shoptt.repository.AddressUserRepository;
import com.shoptt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AddressUserRest {

    @Autowired
    private AddressUserRepository addressUserRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/user/addressUser")
    public List<AddressUser> findByUser(){
        User user = userService.getUserWithAuthority();
        return addressUserRepository.findByUserId(user.getId());
    }

    @GetMapping("/user/addressUserById")
    public AddressUser findById(@RequestParam("id") Long id){
        AddressUser addressUser = addressUserRepository.findById(id).get();
        return addressUser;
    }

    @PostMapping("/user/addAddressUser")
    public void save(@RequestBody AddressUser addressUser){
        User user = userService.getUserWithAuthority();
        addressUser.setUser(user);
        addressUserRepository.save(addressUser);
    }

    @DeleteMapping("/user/deleteAdressUser")
    public void delete(@RequestParam("id") Long id) throws Exception {
        User user = userService.getUserWithAuthority();
        AddressUser addressUser = addressUserRepository.findById(id).get();
        if(user.getId() != addressUser.getUser().getId()){
            throw new Exception("khong du quyen");
        }
        addressUserRepository.deleteById(id);
    }
}
