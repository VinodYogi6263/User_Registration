package com.ss.repository;


import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ss.entity.User_Entity;


@Repository
public interface User_Repository extends JpaRepository<User_Entity,Long>
{

	@Query("select new User_Entity(u.email,u.first_name,u.last_name,u.password,u.role) from User_Entity u where u.email=?1")
	public User_Entity findByUserName(String email);
	
	@Query("SELECT u FROM User_Entity u WHERE u.role LIKE CONCAT('%',:role,'%')Or u.email LIKE CONCAT('%',:email,'%')Or u.first_name LIKE CONCAT('%',:first_name,'%')Or u.last_name LIKE CONCAT('%',:last_name,'%')")
	public Page<User_Entity> search (String role, String email,String first_name,String last_name, Pageable p);


}
