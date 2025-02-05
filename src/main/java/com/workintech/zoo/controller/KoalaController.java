package com.workintech.zoo.controller;


import com.workintech.zoo.entity.Koala;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {
    private Map<Integer, Koala> koalas;

    @PostConstruct
    public void init(){
        this.koalas = new HashMap<>();
    }
    @GetMapping
    public List<Koala> find(){
        return this.koalas.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Koala find(@PathVariable("id") Integer id){
        return koalas.get(id);
    }
    @PostMapping
    public Koala save(@RequestBody Koala koala){
       koalas.put(koala.getId(), koala);
       return koalas.get(koala.getId());
    }

    @PutMapping("/{id}")
    public Koala update(@PathVariable Integer id, @RequestBody Koala koala){
        if(koalas.containsKey(id)){
            koalas.put(id, koala);
            return koalas.get(koala.getId());

        }else {
            return save(koala);
        }
    }

    @DeleteMapping("/{id}")
    public Koala delete(@PathVariable Integer id){
        return  koalas.remove(id);
    }




}
