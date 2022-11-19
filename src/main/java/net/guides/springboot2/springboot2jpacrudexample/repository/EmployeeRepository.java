package net.guides.springboot2.springboot2jpacrudexample.repository;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import net.guides.springboot2.springboot2jpacrudexample.config.RedisConnectionFactory;
import net.guides.springboot2.springboot2jpacrudexample.model.Employee;

@Repository
public class EmployeeRepository{
   
private HashOperations<String, String, Employee> hashOperations;
    
    public EmployeeRepository(RedisTemplate<String, Employee> redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
    }
    
    public void create(Employee e) {
        hashOperations.put("EMPLOYEE", new Date().toString(), e);
    }
    
    public Employee get(String id) {
        return (Employee) hashOperations.get("EMPLOYEE", id);
    }

    public Map<String, Employee> getAll(){
        return hashOperations.entries("Employee");
    }
    
    public void update(Employee employee) {
        hashOperations.put("EMPLOYEE", employee.getId()+"",employee );
    }
    
    public void delete(String id) {
        hashOperations.delete("EMPLOYEE", id);
    }  
}
