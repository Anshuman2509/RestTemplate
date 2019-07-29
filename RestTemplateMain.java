package com.test.restTemplate;

import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestTemplateMain {
	private static String url="http://localhost:3000/cars";
	
//	Note that RestTemplate uses MessageConverter and we need to set this property in the RestTemplate bean. 
//	In our example we are using MappingJacksonHttpMessageConverter for fetching data from JSON format.
	public static RestTemplate getRestTemplate() {
		RestTemplate restTemplate=new RestTemplate();
		MappingJackson2HttpMessageConverter converter=new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(new ObjectMapper());
		restTemplate.getMessageConverters().add(converter);
		return restTemplate;
	}

	public static void main(String[] args) {
		RestTemplate restTemplate=getRestTemplate();

//      GET ALL CARS 
		ResponseEntity<Car[]> responseEntity=restTemplate.getForEntity(url, Car[].class);
		System.out.println(Arrays.asList(responseEntity.getBody()));
		
//     GET CARS BY ID
	   ResponseEntity<Car> responseEntity2=restTemplate.getForEntity(url+"/1001", Car.class);
	   System.out.println(responseEntity2.getBody());
		
//     ADD A CAR
	   Car car=new Car();
	   car.setId(1005);
	   car.setName("Test Car 5");
	   car.setColor("Black");
	   // Here in the third parameter pass the response of the post API. in this case it returs all cars. In most
	   //other cases the Api just returns Http Status codes.
	   ResponseEntity<Car[]> responseEntity3=restTemplate.postForEntity(url, car, Car[].class);
	   System.out.println("After addition "+Arrays.asList(responseEntity3.getBody()));
	   
//     UPDATE A CAR
	   Car car2=new Car();
	   car2.setId(1001);
	   car2.setName("Updated Car");
	   car2.setColor("Brown");
	   restTemplate.put(url+"/1001", car2);
	   ResponseEntity<Car[]> responseEntity5=restTemplate.getForEntity(url, Car[].class);
	   System.out.println("After the update "+Arrays.asList(responseEntity5.getBody()));
	   
//     DELETE A CAR
	   restTemplate.delete(url+"/1001");
	   ResponseEntity<Car[]> responseEntity6=restTemplate.getForEntity(url, Car[].class);
	   System.out.println("After the delete "+Arrays.asList(responseEntity6.getBody()));
	   
	   
	}

}
