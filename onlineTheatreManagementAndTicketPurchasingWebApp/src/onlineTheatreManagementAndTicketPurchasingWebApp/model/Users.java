package onlineTheatreManagementAndTicketPurchasingWebApp.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import onlineTheatreManagementAndTicketPurchasingWebApp.DB.UsersDB;

public class Users {
	
	private String userName;
	private String hashedAndSaltedPassword;
	private String saltValue;
	private String firstName;
	private String lastName;
	private String street;
	private String city;
	private String state;
	private String zipCode;
	private String emailAddress;
	private String phoneNumber;
	
	public Users(){
		super();
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getHashedAndSaltedPassword() {
		return hashedAndSaltedPassword;
	}

	public void setHashedAndSaltedPassword(String hashedAndSaltedPassword) {
		this.hashedAndSaltedPassword = hashedAndSaltedPassword;
	}
	public String getSaltValue(){
		return saltValue;
	}
	public void setSaltValue(String saltValue) {
		this.saltValue = saltValue;
	}
	public Users(String firstName, String lastName, String userName, String hashedAndSaltedPassword, String saltValue, String street, String city, String state, String zipCode, String emailAddress, String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.hashedAndSaltedPassword = hashedAndSaltedPassword;
		this.saltValue = saltValue;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
	}
	public Users(String userName, String hashedAndSaltedPassword) {
		super();
		this.userName = userName;
		this.hashedAndSaltedPassword = hashedAndSaltedPassword;
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
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}	
	
	public boolean isValidPhone(String phoneNumber){
		boolean isValid = true;
		String regexStr = "^(1\\-)?[0-9]{3}\\-?[0-9]{3}\\-?[0-9]{4}$";
		if(!phoneNumber.matches(regexStr)){
			isValid = false;
		}
		return isValid;
	}
	
	public boolean isValidEmail(String emailAddress){
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
		Pattern pat = Pattern.compile(emailRegex);
		if(emailAddress == null){
			return false;
		}
		
		return pat.matcher(emailAddress).matches();
	}
	
	public boolean isValidZip(String zipCode){
		boolean isValid = true;
		String zipRegex = "^\\d{5}(?:[-\\s]\\d{4})?$";
		if(!zipCode.matches(zipRegex)){
			isValid = false;
		}
		return isValid;
	}
	
	public static String hashPassword(String password) 
			throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		
		/*
		 * Convert the password string into an array of bytes (getBytes method)
		 * Then, specify the array of bytes that you want to hash.
		 */
		md.update(password.getBytes());
		
		/*
		 * Call the digest method of the MessageDigest class 
		 * to hash the input and return a fixed-length array 
		 * of bytes for the hashed input.
		 */
		
		byte[] mdArray = md.digest();
		
		//Uses Base64 class to convert bytes into String
		String st = Base64.encode(mdArray);
		return st;
		
	}
	
	public static String generateSalt() {		 
		Random r = new SecureRandom();
		byte[] saltBytes = new byte[32];
		r.nextBytes(saltBytes);
		return Base64.encode(saltBytes);
	}
	
	public static String hashAndSaltPassword(String password, String saltValue) 
			throws NoSuchAlgorithmException {
		return hashPassword(password + saltValue);
	}
	
}
