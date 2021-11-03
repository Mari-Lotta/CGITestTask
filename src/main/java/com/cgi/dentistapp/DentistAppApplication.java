package com.cgi.dentistapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DentistAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(DentistAppApplication.class, args);
    }



//    @Bean
//    CommandLineRunner runner(DentistVisitRepository repository){
//        return args -> {
//            repository.save(new DentistVisitEntity("Mati Kaal", new Date()));
//        };
//    }
}
