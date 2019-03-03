package sk.learning.rest.webservices.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserDaoService daoService;
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return daoService.findAll();
	}
	
	@GetMapping("/user/{id}")
	public User retrieveUser(@PathVariable int id) {
		User user = daoService.findOne(id);
		
		if(user == null)
			throw new UserNotFoundException("id - " + id);	
		return user;
	}
	
	@PostMapping("/user")
	public User createUser(@RequestBody User user) {
		return daoService.save(user);
	}
	
	@DeleteMapping("/user/{id}")
	public User DeleteUser(@PathVariable int id) {
		User user = daoService.deleteById(id);
		
		if(user == null) {
			throw new UserNotFoundException("id - " + id);
		}
		return user;
	}
}
