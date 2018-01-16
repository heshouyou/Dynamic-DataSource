package com.heshouyou.service;

import com.heshouyou.core.DynamicDataSourceContextHolder;
import com.heshouyou.core.TargetDataSource;
import com.heshouyou.entity.Person;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author heshouyou
 * @Description
 * @Date 2018-01-16 15:43.
 */
@Service
public class PersonServiceImpl implements PersonService {

    private Logger logger = Logger.getLogger(PersonServiceImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Person> queryByNameLike(String name) {
        logger.info("通过名字模糊查询数据，使用默认数据源");
        String sql = "select id,name,age,sex,address from person where name LIKE '%"+name+"%'";
        return (List<Person>) jdbcTemplate.query(sql, new RowMapper<Person>() {

            @Override
            public Person mapRow(ResultSet resultSet, int i) throws SQLException {
                Person person = new Person(resultSet.getInt("id")
                        ,resultSet.getString("name")
                        ,resultSet.getInt("age")
                        ,resultSet.getString("sex")
                        ,resultSet.getString("address"));
                return person;
            }
        });
    }

    @Override
    @TargetDataSource(name = "ds1")
    public List<Person> queryByAgeEq(Integer age) {
        logger.info("通过年龄查询数据，ds1数据源");
        String sql = "select id,name,age,sex,address from person WHERE age = "+age;
        return (List<Person>) jdbcTemplate.query(sql, new RowMapper<Person>() {

            @Override
            public Person mapRow(ResultSet resultSet, int i) throws SQLException {
                Person person = new Person(resultSet.getInt("id")
                        ,resultSet.getString("name")
                        ,resultSet.getInt("age")
                        ,resultSet.getString("sex")
                        ,resultSet.getString("address"));
                return person;
            }
        });
    }

    @Override
    @TargetDataSource(name = "ds2")
    public List<Person> queryBySexEq(String sex) {
        logger.info("通过性别查询数据，ds2数据源");
        String sql = "select id,name,age,sex,address from person WHERE sex = '"+sex+"'";
        return (List<Person>) jdbcTemplate.query(sql, new RowMapper<Person>() {

            @Override
            public Person mapRow(ResultSet resultSet, int i) throws SQLException {
                Person person = new Person(resultSet.getInt("id")
                        ,resultSet.getString("name")
                        ,resultSet.getInt("age")
                        ,resultSet.getString("sex")
                        ,resultSet.getString("address"));
                return person;
            }
        });
    }
}
