package com.bodoque007.mappings;

import com.bodoque007.mappings.dao.AppDAO;
import com.bodoque007.mappings.entity.Instructor;
import com.bodoque007.mappings.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MappingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MappingsApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			System.out.println("Working!");
			deleteInstructor(appDAO, 2);
		};
	}

	private void deleteInstructor(AppDAO appDAO, int id) {
		appDAO.deleteInstructorById(id);
	}

	public void createInstructor(AppDAO appDAO) {
		Instructor instructor = new Instructor("Gohan", "Son", "gohan@email.com");

		InstructorDetail instructorDetail = new InstructorDetail("youtybe.com/peakGohan", "Studying");
		instructor.setInstructorDetail(instructorDetail);
		appDAO.save(instructor);
	}
}
