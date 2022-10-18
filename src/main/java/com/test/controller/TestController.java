package com.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@RestController
@RequestMapping
public class TestController {
	private static String url = "https://ipinfo.io/country";
	private static String url1 = "https://ipinfo.io/timezone";

	@GetMapping("/hello")
	public static String getGreeting() {
		return "Hello World";
	}

	@GetMapping("/country")
	public String getCountry() throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create(url))
			.build();
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(httpResponse.body());
		return "Hello from " + httpResponse.body();
	}
	@GetMapping("/timeZone")
	public String gettimeZone() throws IOException, InterruptedException{
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create(url1))
			.build();
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(httpResponse.body());
		return "You are located in " + httpResponse.body();
	}

}