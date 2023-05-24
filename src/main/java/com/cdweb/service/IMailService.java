package com.cdweb.service;

public interface IMailService {
	boolean send(String to, String subject, String body, String... others);

}
