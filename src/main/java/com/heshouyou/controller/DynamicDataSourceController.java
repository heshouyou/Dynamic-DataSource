package com.heshouyou.controller;

import com.heshouyou.entity.Person;
import com.heshouyou.service.PersonService;
import com.heshouyou.service.PersonServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author heshouyou
 * @Description
 * @Date 2018-01-16 15:45.
 */
@RestController
public class DynamicDataSourceController {

    private Logger logger = Logger.getLogger(DynamicDataSourceController.class);

    @Autowired
    private PersonService personService;

    @GetMapping("/default/{name}")
    public String useDefaultDataSource(@PathVariable("name") String name){
        logger.info("使用默认数据源");
        StringBuilder stringBuilder = new StringBuilder();
        List<Person> list = personService.queryByNameLike(name);
        for (Person p: list) {
            stringBuilder.append(p.toString()+"\n");
        }
        return stringBuilder.toString();
    }

    @GetMapping("/ds1/{age}")
    public String useDS1DataSource(@PathVariable("age") Integer age){
        logger.info("使用ds1数据源");
        StringBuilder stringBuilder = new StringBuilder();
        List<Person> list = personService.queryByAgeEq(age);
        for (Person p: list) {
            stringBuilder.append(p.toString()+"\n");
        }
        return stringBuilder.toString();
    }

    @GetMapping("/ds2/{sex}")
    public String useDS2DataSource(@PathVariable("sex") String sex){
        logger.info("使用ds2数据源");
        StringBuilder stringBuilder = new StringBuilder();
        List<Person> list = personService.queryBySexEq(sex);
        for (Person p: list) {
            stringBuilder.append(p.toString()+"\n");
        }
        return stringBuilder.toString();
    }
}
