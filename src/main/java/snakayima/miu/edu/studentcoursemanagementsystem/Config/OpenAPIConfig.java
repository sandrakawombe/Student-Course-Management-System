package snakayima.miu.edu.studentcoursemanagementsystem.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI studentCourseManagementOpenAPI() {
        // --- Local server definition ---
        Server localServer = new Server()
                .url("http://localhost:8080")
                .description("Local Development Server");

        // --- AWS Production server definition ---
        Server awsServer = new Server()
                .url("http://184.73.128.59:8080")
                .description("AWS EC2 Production Server");

        // --- API contact information ---
        Contact contact = new Contact()
                .name("Student Course Management Support")
                .email("support@miu.edu");

        // --- API general info ---
        Info info = new Info()
                .title("Student Course Management API")
                .version("1.0.0")
                .description("RESTful API for managing students, courses, and enrollments within the Student Course Management System.")
                .contact(contact);

        // --- Register everything into OpenAPI ---
        return new OpenAPI()
                .info(info)
                .servers(List.of(localServer, awsServer));
    }
}