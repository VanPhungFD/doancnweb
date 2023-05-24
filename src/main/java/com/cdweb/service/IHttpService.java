package com.cdweb.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface IHttpService {
	// lấy ra địa chỉ của url hiện tại trang web đang gọi tới
	String getCurrentUrl();

	// lấy ra địa chỉ của uri
	String getCurrentUri();

	// setSession
	void setSession(String name, Object value);

	void removeSession(String name);

	<T> T getSession(String name);

	String getUrlEncode(String text);

	String encode(String text);
	String decode(String text);

	File saveCustomerPhoto(MultipartFile photo);
	File saveProductPhoto(MultipartFile photo);

}
