package com.redis.exercise;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.ReactiveRedisTemplate;

@SpringBootApplication
public class ExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExerciseApplication.class, args);
	}

	@Bean
	ApplicationRunner list(ReactiveRedisTemplate <String,String> template){
		return args -> {

			var listTemplate = template.opsForList();
			var listName = "spring-team";

			var push = listTemplate.leftPushAll(listName,"Amy","trompi","inay","chechi");
			push
					.thenMany(listTemplate.leftPop(listName))
			        .doOnNext(System.out::println)
					.thenMany(listTemplate.leftPop(listName))
					.doOnNext(System.out::println)
					.subscribe();

		};
	}

}
