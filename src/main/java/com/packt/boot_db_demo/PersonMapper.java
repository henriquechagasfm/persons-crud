package com.packt.boot_db_demo;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
/**
 * This is where we map the model class attributes
 * model class = boot_db_demo.person
 */
@Mapper
public interface PersonMapper {

    @Select("SELECT * FROM person")
        public List<Person> getPersons();
    
    @Select("SELECT * FROM person WHERE id = #{id}")
        public Person getPersonById(Integer id);

    @Insert("INSERT INTO person(first_name, last_name, place) " + " VALUES (#{firstName}, #{lastName}, #{place})")
        @Options(useGeneratedKeys = true)
        public void insert(Person person);

    @Update("UPDATE person SET first_name = #{firstName}, last_name = #{lastName}, place = #{place} WHERE id = #{id}")
         public void save(Person person);

    @Delete("DELETE FROM person WHERE id = #{id}")
        public void delete(Integer id);

    

}
