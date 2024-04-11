package com.example.demoapp;

import com.example.demoapp.model.Student;
import com.example.demoapp.model.Teacher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

// @Bean phải được định nghĩa trong class được chú thích bởi @Configuration
@SpringBootApplication
public class DemoappApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoappApplication.class, args);

		Student student = context.getBean(Student.class);
		System.out.println(student);

		Teacher teacher = context.getBean("teacher1", Teacher.class);
		System.out.println(teacher);
	}

	@Bean
	public Student student() {
		return new Student(1, "Nguyễn Văn An");
	}

	@Bean("teacher1")
	public Teacher teacher() {
		return new Teacher(1, "Nguyễn Thị Bình");
	}

	@Bean("teacher2")
	public Teacher teacher1() {
		return new Teacher(2, "Trần Văn Tuấn");
	}
}
