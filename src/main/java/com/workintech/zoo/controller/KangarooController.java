package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.validations.ZooKangarooValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {
    private Map<Integer, Kangaroo> kangaroos;


    @GetMapping
    public List<Kangaroo> find(){
        return this.kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo find(@PathVariable("id") Integer id) {
        ZooKangarooValidation.isIdValid(id);
        ZooKangarooValidation.checkKangarooExistence(kangaroos, id, true);
        return kangaroos.get(id);

    }

    @PostConstruct
    public void init(){
        kangaroos = new HashMap<>();
    }

    @PostMapping
    public Kangaroo save(@RequestBody Kangaroo kangaroo) {
        ZooKangarooValidation.checkKangarooExistence(kangaroos, kangaroo.getId(), false);
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroos.get(kangaroo.getId());
    }

    @PutMapping("/{id}")
    public Kangaroo update(@PathVariable int id, @RequestBody Kangaroo kangaroo) {
       ZooKangarooValidation.isIdValid(id);
       kangaroo.setId(id);
       if(kangaroos.containsKey(id)){
           kangaroos.put(id, kangaroo);
           return kangaroos.get(id);
       }else{
           return save(kangaroo);
       }
    }

    @DeleteMapping("/{id}")
    public Kangaroo delete(@PathVariable Integer id) {
        ZooKangarooValidation.isIdValid(id);
        ZooKangarooValidation.checkKangarooExistence(kangaroos, id, true);
        return kangaroos.remove(id);
    }
}
