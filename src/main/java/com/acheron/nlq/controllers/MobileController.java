package com.acheron.nlq.controllers;

import com.acheron.nlq.models.Mobile;
import com.acheron.nlq.repositories.MobileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * @author Madhu Shree Mangi
 * @date : 16-08-2022
 * @project : nlq-solr-opennlp
 */
@RestController
@RequestMapping("nlq")
@CrossOrigin("http://localhost:4200")
public class MobileController {

    private MobileRepository mobileRepository;
    @Autowired
    public void setMobileRepository(MobileRepository mobileRepository) {
        this.mobileRepository = mobileRepository;
    }

    //this method indexes mobile document(s) in solr repository by using solrcrudrepository's save method
    //this method will be executed when the application will opt if we use @PostConstruct
    @PostConstruct
    public void add(){
//        List<Mobile> mobiles=new ArrayList<>();
//        mobiles.add(new Mobile("1","Galaxy S4","Samsung","Black","15000"));
//        mobiles.add(new Mobile("2","Galaxy M15","Samsung","Black","20000"));
//        mobiles.add(new Mobile("3","Reno 3","Oppo","Black","18000"));
//        mobiles.add(new Mobile("4","IqOO Legend","Vivo","Blue","35000"));
//        mobiles.add(new Mobile("5","Iphone 12","Apple","Black","75000"));
//        mobiles.add(new Mobile("6","K50i","Redmi","Black","17000"));
//        mobiles.add(new Mobile("7","V7","Vivo","Grey","12000"));
//        mobiles.add(new Mobile("8","Note 11T","Redmi","Blue","11000"));
//        mobiles.add(new Mobile("9","Iphone 13","Apple","White","135000"));
//        mobiles.add(new Mobile("10","K10","Oppo","Blue","25000"));
//        mobileRepository.saveAll(mobiles);
//        Mobile mobile =new Mobile(15,"Apple","White",85000);
//        mobileRepository.save(mobile);
    }

    // to add data of new mobile
    @PostMapping("/addMobile")
    ResponseEntity<Void> addMobile(@RequestBody Mobile mobile){
        mobileRepository.save(mobile);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //to retrieve and update a document
    @PutMapping("/updateDocument")
    ResponseEntity<Void> updateDocument(@RequestBody Mobile mobile){
        Mobile retrievedMobile=mobileRepository.findById(parseInt(mobile.getId())).get();
        System.out.println(retrievedMobile);
        retrievedMobile.setBrand(mobile.getBrand());
        retrievedMobile.setColor(mobile.getColor());
        retrievedMobile.setPrice(mobile.getPrice());
        mobileRepository.save(mobile);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    //to delete a document
    @DeleteMapping("/deleteDocument/{id}")
    ResponseEntity<Void> deleteDocument(@PathVariable("id") String id){
        HttpHeaders headers=new HttpHeaders();
        headers.add("desc","deleting document by id");
        Mobile retrievedMobile=mobileRepository.findById(parseInt(id)).get();
        mobileRepository.delete(retrievedMobile);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
    }

    // to get all the mobiles available
    @GetMapping("/mobiles")
    public List<Mobile> getMobiles(){
        Iterable<Mobile> result=mobileRepository.findAll();
        List<Mobile> mobiles=new ArrayList<>();
        for (Mobile key:result) {
            mobiles.add(key);
        }
        return mobiles;
    }

    //method name query generation
    //to get a mobiles by its name
    @GetMapping("/name/{name}")
    public List<Mobile> getMobileByName(@PathVariable("name")String mobileName){
        Iterable<Mobile> result=mobileRepository.findByName(mobileName);
        List<Mobile> mobiles=new ArrayList<>();
        for (Mobile key:result) {
            mobiles.add(key);
        }
        return mobiles;
    }

    //query with @Query annotation
    //custom search
    @GetMapping("/search/{searchItem}")
    public List<Mobile> getByCustomQuery(@PathVariable("searchItem")String searchItem){
        Page<Mobile> result=mobileRepository.findByCustomQuery(searchItem, PageRequest.of(0,10));
        List<Mobile> mobiles=new ArrayList<>();
        for(Mobile key:result){
            mobiles.add(key);
        }
        return mobiles;
    }
    @GetMapping("/named/{feature}")
    public List<Mobile> getByNamedQuery(@PathVariable("feature")String feature){
        Page<Mobile> result=mobileRepository.findByNamedQuery(feature,PageRequest.of(0,10));
        List<Mobile> mobiles=new ArrayList<>();
        for(Mobile key:result){
            mobiles.add(key);
        }
        return mobiles;
    }
}
