package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Login;
import com.example.demo.repository.AuthRepository;

@RestController
@RequestMapping
public class AuthController {
 
	@Autowired
    AuthRepository auth;
	@Autowired
    RestTemplate restTemplate;
	
	
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping(path="/login/{username}/{pwd}")
	//public void loginValidation(@RequestBody Login login)
	public String loginValidation(@PathVariable String username,@PathVariable String pwd )
	{
		
	//query whether login table has the sent username and password
    //return an exception if username or password are invalid
		//auth.findByEmailAndPassword(login.companyEmail,login.password).orElseThrow(()-> new UserNotFoundException("Your Username or Passwod is incorrect"));
		auth.findByEmailAndPassword(username,pwd).orElseThrow(()-> new UserNotFoundException("Your Username or Passwod is incorrect"));
		  return "Logged in!";
	}
	
	//Method to be called from EmpInfoService when password field is changed
	@PutMapping(path="/login/updateByPassword/{curPwd}/{newPwd}")
	 public String updateByPassword(@PathVariable String curPwd,@PathVariable String newPwd)
	{
		//return an exception if current password passed is not valid
		Login login = auth.findByPassword(curPwd).orElseThrow(()-> new UserNotFoundException("Current password does not exist"));
		//Login login = auth.findByPassword(curPwd);
		login.setPassword(newPwd);
		auth.save(login);
		return "Password Updated";
		
	}
	/*@PostMapping(path="/login",consumes= {"application/json"})
	public String addUser(@RequestBody Login login)
	{
		Object num=auth.findByEmail(login.companyEmail);
		if(num==null)
		{
			auth.save(login);
			 HttpEntity<Login> entity = new HttpEntity<Login>(login);
			 return restTemplate.exchange(
			   "http://localhost:8080/activity/emp/add/"+login.getPassword(), HttpMethod.POST, entity, String.class).getBody();
		}
		else
			return "User with same username exists";
	}*/

}
