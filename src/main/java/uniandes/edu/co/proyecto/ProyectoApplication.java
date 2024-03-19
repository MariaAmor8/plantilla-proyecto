package uniandes.edu.co.proyecto;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uniandes.edu.co.proyecto.modelo.Login;
import uniandes.edu.co.proyecto.repositorio.LoginRepository;

@SpringBootApplication
public class ProyectoApplication implements CommandLineRunner{
	@Autowired
	LoginRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(ProyectoApplication.class, args);
	}

	@Override
	public void run(String... args)   {
		Collection<Login> lista = repository.darLogins();

		for(Login i: lista){
			System.out.println(i.getLogin());
		}
		
	}

}
