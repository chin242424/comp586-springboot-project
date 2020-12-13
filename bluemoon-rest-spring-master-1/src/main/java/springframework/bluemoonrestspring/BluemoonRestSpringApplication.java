package springframework.bluemoonrestspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication(scanBasePackages={"springframework.bluemoonrestspring"})
@SpringBootApplication
public class BluemoonRestSpringApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BluemoonRestSpringApplication.class, args);
    }

}
