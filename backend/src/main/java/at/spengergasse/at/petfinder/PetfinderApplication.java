package at.spengergasse.at.petfinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class PetfinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetfinderApplication.class, args);
	}

	@Configuration
	@EnableJpaRepositories(basePackages = "at.spengergasse.at.petfinder.JPA.persistence")
	public class JpaConfig {

	}

	@Configuration
	@EnableMongoRepositories(basePackages = "at.spengergasse.at.petfinder.Mongo.persistence")
	public class MongoConfig {

	}

	@Configuration
	@EnableMongoRepositories(basePackages = "at.spengergasse.at.petfinder.mongoReferencing.persistence")
	public class MongoReferencingConfig {

	}
}
