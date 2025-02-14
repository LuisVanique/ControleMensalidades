package br.com.luisvanique.controleDeMensalidades;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ControleDeMensalidadesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleDeMensalidadesApplication.class, args);
	}

}
