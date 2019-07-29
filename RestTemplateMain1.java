package com.test.restTemplate;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class RestTemplateMain1 {
	private static String url="http://localhost:3000/cars";

	public static void main(String[] args) {
		RestTemplate restTemplate=new RestTemplate();
		
		//GET ALL CARS
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> httpEntity=new HttpEntity<String>(headers);
		Car[] cars=restTemplate.exchange(url,HttpMethod.GET,httpEntity,Car[].class).getBody();
//		System.out.println(Arrays.asList(cars));
		
		//GET CAR BY ID
		HttpHeaders headers2=new HttpHeaders();
		headers2.setAccept(Arrays.asList(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML));
		HttpEntity<String> httpEntity2=new HttpEntity<String>(headers2);
		Car car=restTemplate.exchange(url+"/1002", HttpMethod.GET,httpEntity2,Car.class).getBody();
//		System.out.println(car);
		
		//ADD A CAR
		HttpHeaders headers3=new HttpHeaders();
		headers3.setAccept(Arrays.asList(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML));
		Car car2=new Car();
		car2.setId(1005);
		car2.setName("New Car");
		car2.setColor("Purple");
		HttpEntity<Car> httpEntity3=new HttpEntity<Car>(car2,headers3);
		Car[] cars1=restTemplate.exchange(url, HttpMethod.POST,httpEntity3,Car[].class).getBody();
		System.out.println("Add Request "+Arrays.asList(cars1));
		
		//UPDATE A CAR
		HttpHeaders headers4=new HttpHeaders();
		headers4.setAccept(Arrays.asList(MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML));
		Car car3=new Car();
		car3.setId(1002);
		car3.setName("Updated Car");
		car3.setColor("Beige");
		HttpEntity<Car> httpEntity4=new HttpEntity<Car>(car3,headers4);
		String updateResponse=restTemplate.exchange(url+"/1002", HttpMethod.PUT,httpEntity4,String.class).getBody();
		System.out.println(updateResponse);
		
		//DELETE A CAR
		HttpHeaders headers5=new HttpHeaders();
		headers5.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Car> httpEntity5=new HttpEntity<Car>(headers5);
		String deleteResponse=restTemplate.exchange(url+"/1005",HttpMethod.DELETE,httpEntity5,String.class).getBody();
		System.out.println(deleteResponse);
		
		
		
		
	}

}
