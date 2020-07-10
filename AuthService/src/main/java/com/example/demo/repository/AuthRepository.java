package com.example.demo.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Login;
public interface AuthRepository extends JpaRepository<Login,Integer>
{

	Optional<Login> findByPassword(String password);
	// Login findByPassword(String password);
	@Query(value="select 1 from Login l where l.company_email=:companyEmail and l.password=:password",nativeQuery=true)
    Optional<?> findByEmailAndPassword(String companyEmail,String password);
    
	@Query(value="select 1 from Login l where l.company_email=:companyEmail ",nativeQuery=true)
    Object findByEmail(String companyEmail);
 
}
