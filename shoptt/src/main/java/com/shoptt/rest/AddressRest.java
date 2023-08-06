package com.shoptt.rest;

import com.shoptt.entity.Province;
import com.shoptt.entity.Town;
import com.shoptt.entity.Village;
import com.shoptt.repository.ProvinceRepository;
import com.shoptt.repository.TownRepository;
import com.shoptt.repository.VillageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AddressRest {

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private TownRepository townRepository;

    @Autowired
    private VillageRepository villageRepository;

    @GetMapping("/public/province")
    public List<Province> findAllProvince(){
        return provinceRepository.findAlls();
    }

    @GetMapping("/public/town")
    public List<Town> getTownByProvinceId(@RequestParam("id") Long id){
        return townRepository.findByProvinceId(id);
    }

    @GetMapping("/public/village")
    public List<Village> getVillageByTownId(@RequestParam("id") Long id){
        return villageRepository.findByTownId(id);
    }
}
