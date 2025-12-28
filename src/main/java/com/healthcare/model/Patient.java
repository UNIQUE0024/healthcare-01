package com.healthcare.model;
import java.io.Serializable;
import java.util.Date;
public class Patient implements Serializable {
private static final long serialVersionUID = 1L;
private int id;
private String firstName;
private String lastName;
private String email;
private String phone;
private Date dateOfBirth;
private String gender;
private String address;
private String bloodGroup;
private Date registrationDate;
public Patient() {
}
public Patient(String firstName, String lastName, String email, String phone, 
Date dateOfBirth, String gender, String address, String bloodGroup) {
this.firstName = firstName;
this.lastName = lastName;
this.email = email;
this.phone = phone;
this.dateOfBirth = dateOfBirth;
this.gender = gender;
this.address = address;
this.bloodGroup = bloodGroup;
this.registrationDate = new Date();
}
public int getId() { return id; }
public void setId(int id) { this.id = id; }
public String getFirstName() { return firstName; }
public void setFirstName(String firstName) { this.firstName = firstName; }
public String getLastName() { return lastName; }
public void setLastName(String lastName) { this.lastName = lastName; }
public String getEmail() { return email; }
public void setEmail(String email) { this.email = email; }
public String getPhone() { return phone; }
public void setPhone(String phone) { this.phone = phone; }
public Date getDateOfBirth() { return dateOfBirth; }
public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }
public String getGender() { return gender; }
public void setGender(String gender) { this.gender = gender; }
public String getAddress() { return address; }
public void setAddress(String address) { this.address = address; }
public String getBloodGroup() { return bloodGroup; }
public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }
public Date getRegistrationDate() { return registrationDate; }
public void setRegistrationDate(Date registrationDate) { this.registrationDate = registrationDate; }
@Override
public String toString() {
return "Patient{" +
"id=" + id +
", firstName='" + firstName + '\'' +
", lastName='" + lastName + '\'' +
", email='" + email + '\'' +
'}';
}
}
