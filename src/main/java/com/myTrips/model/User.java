package com.myTrips.model;

import com.myTrips.service.PasswordConstraintValidator;
import com.myTrips.service.ValidPassword;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity //This tells Hibernate to make a table out of this class
@Table(name="user", schema="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="userId",unique=true)
	private Long userId;
	
	@NotBlank(message="Username is mandatory")
	@Size(min=5, max=20,message ="Username must have between 5 and 20 characters")
	@Column(name="username", unique=true)
	private String username;
	
	@Column(name="firstName")
	@Size(min=3, max=20,message ="First name must at list 3 characters")
	private String firstName;
	@Size(min=3, max=20,message ="Last name must at list 3 characters")
	@Column(name="lastName")
	private String lastName;
	
	
	@ValidPassword
	private String password;
	
	@Column(name="city")
	private String city;
	
	@Column(name="address")
	private String address;
	
	@Column(name="phone")
	@Pattern(regexp = "(\\+61|0)[0-9]{9}",message = "Phone number must be 10 digits long and start with 0")
	private String phone;
	
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL )
	private Set<Trip> trips;
	
	public User() {
	
	}
	
	public Set<Trip> getTrips() {
		return trips;
	}
	
	public Long getId() {
		return userId;
	}
	
	public void setId(Long id) {
		this.userId = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		
		this.password = password;
	}
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	@Override
	public String toString() {
		return "User{" +
				"username='" + username + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", password='" + password + '\'' +
				", city='" + city + '\'' +
				", address='" + address + '\'' +
				", phone='" + phone + '\'' +
				'}';
	}
	
}
