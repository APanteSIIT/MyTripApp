package com.myTrips.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="trip", schema="users")
public class Trip {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tripId",unique = true)
	private Long tripId;
	
	@NotBlank(message="Trip name is mandatory")
	@Column(unique=true, nullable=false,name ="tripname")
	private String tripname;
	
	@Column(name="startDate", nullable=true)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past
	private LocalDate startDate;
	
	@Column(name="endDate", nullable=true)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent
	private LocalDate endDate;
	
	@Column(length = 255,name="impressions")
	@Size(min=5, max=255,message = "Text is to long")
	private String impressions;
	
	@Column(name="photo1",nullable = true,length = 255)
	private String photo1;
	@Column(name="title1",nullable = true)
	private String title1;
	@Column(name="description1",nullable = true)
	private String description1;
	
	@Column(name="photo2",nullable = true,length = 255)
	private String photo2;
	@Column(name="title2",nullable = true)
	private String title2;
	@Column(name="description2",nullable = true)
	private String description2;
	
	
	@Column(name="location",nullable = true)
	private String location;
	
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name="userId",nullable=false)
	private User user;
	
	
	public Trip() {
	
	}
	
	public Trip(Long tripId, String tripname) {
		this.tripId = tripId;
		this.tripname = tripname;
	}
	
	public String getPhoto1() {
		return photo1;
	}
	
	public void setPhoto1(String photo1) {
		this.photo1 = photo1;
	}
	
	public String getPhoto2() {
		return photo2;
	}
	
	public void setPhoto2(String photo2) {
		this.photo2 = photo2;
	}
	
	public String getTitle1() {
		return title1;
	}
	
	public void setTitle1(String title1) {
		this.title1 = title1;
	}
	
	public String getDescription1() {
		return description1;
	}
	
	public void setDescription1(String description1) {
		this.description1 = description1;
	}
	
	public String getTitle2() {
		return title2;
	}
	
	public void setTitle2(String title2) {
		this.title2 = title2;
	}
	
	public String getDescription2() {
		return description2;
	}
	
	public void setDescription2(String description2) {
		this.description2 = description2;
	}
	
	public Long getTripId() {
		return tripId;
	}
	
	public void setTripId(Long tripId) {
		this.tripId = tripId;
	}
	
	public String getTripname() {
		return tripname;
	}
	
	public void setTripname(String tripname) {
		this.tripname = tripname;
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}
	
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}
	
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public String getImpressions() {
		return impressions;
	}
	
	public void setImpressions(String impressions) {
		this.impressions = impressions;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	@Override
	public String toString() {
		return "Trip{" +
				"tripId=" + tripId +'\'' +
				", tripname='" + tripname + '\'' +
				", startDate=" + startDate +'\''+
				", endDate=" + endDate +'\''+
				", impressions='" + impressions + '\'' +
				", photo1='" + photo1 + '\'' +
				", title1='" + title1 + '\'' +
				", description1='" + description1 + '\'' +
				", photo2='" + photo2 + '\'' +
				", title2='" + title2 + '\'' +
				", description2='" + description2 + '\'' +
				", location='" + location + '\'' +
				", user=" + user +
				'}' ;
	}
}
