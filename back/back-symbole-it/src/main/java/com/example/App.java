package com.example;


import com.example.services.firebase.FirebaseConnexion;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")
public class App 
{
    public static void main( String[] args ) throws IOException {
        SpringApplication.run(App.class, args);

        FirebaseConnexion.init();

        System.out.println("Serveur lancé !");
    }
}
