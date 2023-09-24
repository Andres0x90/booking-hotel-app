package co.edu.tdea;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Getter
@Setter
public class ApplicationMain {

    public static void main(String args[]){
        SpringApplication.run(ApplicationMain.class, args);
    }
}
