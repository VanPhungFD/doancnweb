package com.cdweb.service;

import javax.servlet.http.Cookie;

public interface ICookieService {
	Cookie createCookie(String name, String value, int day);

//	Cookie read(String name);
	void deleteCookie(String name);
	String getCookieValue(String name, String defauValue);

}
