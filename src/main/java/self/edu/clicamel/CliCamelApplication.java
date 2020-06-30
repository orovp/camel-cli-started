package self.edu.clicamel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CliCamelApplication {
    public static void main(String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(CliCamelApplication.class, args)));
    }
}
