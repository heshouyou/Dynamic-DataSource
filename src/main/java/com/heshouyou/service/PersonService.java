package com.heshouyou.service;

import com.heshouyou.entity.Person;

import java.util.List;

/**
 * @Author heshouyou
 * @Description
 * @Date 2018-01-16 15:43.
 */
public interface PersonService {

    List<Person> queryByNameLike(String name);

    List<Person> queryByAgeEq(Integer age);

    List<Person> queryBySexEq(String sex);
}
