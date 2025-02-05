package com.workintech.zoo.validations;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class ZooKangarooValidation {
    public static void isIdValid(Integer id) {
        if (id == null || id < 0) {
            throw new ZooException("Id is not valid: " + id, HttpStatus.BAD_REQUEST);
        }

    }

    public static void checkKangarooExistence(Map<Integer, Kangaroo> kangaroos, Integer id, boolean existence) {
        if (existence) {
            if (!kangaroos.containsKey(id)) {
                throw new ZooException("id is not exist" + id, HttpStatus.NOT_FOUND);
            } else {
                if (kangaroos.containsKey(id)) {
                    throw new ZooException("id is not already exist" + id, HttpStatus.BAD_REQUEST);
                }
            }
        }
    }
}
