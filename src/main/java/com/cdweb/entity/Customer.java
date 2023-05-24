package com.cdweb.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Customers")
public class Customer {
	@NotEmpty(message = "Vui lòng nhập tài khoản")
	@Id
	@Column(name = "id")
	String id;

	@NotEmpty(message = "Vui lòng nhập mật khẩu")
	@Column(name = "password")
	String password;

	@NotEmpty(message = "Vui lòng nhập tên khách hàng")
	@Column(name = "fullname")
	String fullname;

	@NotEmpty(message = "Vui lòng nhập email")
	@Email
	@Column(name = "email")
	String email;

	@Column(name = "photo")
	String photo = "user.png";

	@Column(name = "activated")
	boolean activated = false;

	@Column(name = "admin")
	boolean admin = false;

	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
	List<Order> orders;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Customer(@NotBlank String id) {
		super();
		this.id = id;
	}

	public Customer() {

	}

}
