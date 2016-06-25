package com.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest/secure")
@RestController
public class SecureWebServices {

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greetings> getGreetings() {
		Greetings greetings = new Greetings("Hello, secure REST!");
		return new ResponseEntity<>(greetings, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity postGreetings(@RequestBody Greetings greetings) {
		System.out.println("Greetings have been SECURELY posted. They say: " + greetings.getGreetings());
		return new ResponseEntity(HttpStatus.OK);
	}
}
