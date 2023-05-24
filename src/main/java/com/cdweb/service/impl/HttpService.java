package com.cdweb.service.impl;

import java.io.File;
import java.util.Base64;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cdweb.service.IHttpService;

@Service
public class HttpService implements IHttpService {
	@Autowired
	private HttpServletRequest req;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private HttpSession session;
	
	@Autowired
	ServletContext application;

	@Override
	public String getCurrentUrl() {
		return req.getRequestURL().toString();
	}

	@Override
	public String getCurrentUri() {

		return req.getRequestURL().toString();
	}

	@Override
	public void setSession(String name, Object value) {
		session.setAttribute(name, value);
	}

	@Override
	public void removeSession(String name) {
		session.removeAttribute(name);

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getSession(String name) {
		return (T) session.getAttribute(name);
	}

	@Override
	public String getUrlEncode(String text) {
		return response.encodeURL(text);
	}

	@Override
	public File saveCustomerPhoto(MultipartFile photo) {
		return this.save(photo, "/static/images/customers/");

	}
	
	public File save(MultipartFile uploadfile, String folder) {
		try {
			if(!uploadfile.isEmpty()) {
				String fname = uploadfile.getOriginalFilename(); // lấy tên file từ file upload
				//fname = UUID.randomUUID().toString() + fname.substring(fname.lastIndexOf("."));
				File file = new File(application.getRealPath(folder), fname); 
				uploadfile.transferTo(file);
				return file;
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Mã hóa chuỗi dạng Base64
	 * @param text là chuỗi cần mã hóa
	 * @return chuỗi đã mã hóa
	 */
	@Override
	public String encode(String text) {
		return Base64.getEncoder().encodeToString(text.getBytes());
	}

	/**
	 * Giải mã chuỗi được mã hóa dạng Base64
	 * @param encodedText là chuỗi mã hóa
	 * @return chuỗi đã giải mã
	 */
	@Override
	public String decode(String encodedText) {
		return new String(Base64.getDecoder().decode(encodedText));
	}

	@Override
	public File saveProductPhoto(MultipartFile photo) {		
		return this.save(photo, "/static/images/products/");
	}

}
