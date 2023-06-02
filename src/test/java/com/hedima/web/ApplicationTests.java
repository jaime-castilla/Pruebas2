package com.hedima.web;

import com.hedima.web.modelo.Pelicula;
import com.hedima.web.repositorio.IPeliculaRepo;
import com.hedima.web.servicio.PeliculaServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ApplicationTests {

	@Mock
	private IPeliculaRepo repo;
	@InjectMocks
	private PeliculaServicio servicio;

	@Test
	public void consultarConDatos() {
		Pelicula salida = new Pelicula(1 ,"Fast and Furius", "Pelicula de carreras de coches","Accion","https://www.google.com/search?q=imagen+fast+and+furious+10&rlz=1C1GCEA_enES1050ES1050&sxsrf=APwXEdfTF5VFXDaLFNmYTHNCEboPG9Dsuw:1686042626729&source=lnms&tbm=isch&sa=X&ved=2ahUKEwiqmd6epq7_AhUkVaQEHQSeA6UQ_AUoAXoECAEQAw&biw=1280&bih=609&dpr=1.5");
		Mockito.when(repo.findById(Mockito.anyInt())).thenReturn(Optional.of(salida));
		Pelicula resultado = servicio.mostrarUno(1);
		Assertions.assertEquals(salida, resultado);
	}

	@Test
	public void consultarSinDatos() {
		Pelicula salida = new Pelicula();
		Mockito.when(repo.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		Pelicula resultado = servicio.mostrarUno(8);
		Assertions.assertEquals(salida, resultado);
	}

	@Test
	public void fabricar() {
		Pelicula entrada = new Pelicula("El señor de los anillos", "Buscar un anillo", "Aventuras", "https://www.google.com/search?q=el+se%C3%B1or+de+los+anillos&rlz=1C1GCEA_enES1050ES1050&sxsrf=APwXEdc6AY_SKiLeX7aty2BfHOnQ9NarWQ:1686052167280&source=lnms&tbm=isch&sa=X&ved=2ahUKEwiDm4Pkya7_AhVmU6QEHZFuA0UQ_AUoAXoECAEQAw&biw=1280&bih=609&dpr=1.5");
		Pelicula salida = new Pelicula(2,"El señor de los anillos", "Buscar un anillo", "Aventuras", "https://www.google.com/search?q=el+se%C3%B1or+de+los+anillos&rlz=1C1GCEA_enES1050ES1050&sxsrf=APwXEdc6AY_SKiLeX7aty2BfHOnQ9NarWQ:1686052167280&source=lnms&tbm=isch&sa=X&ved=2ahUKEwiDm4Pkya7_AhVmU6QEHZFuA0UQ_AUoAXoECAEQAw&biw=1280&bih=609&dpr=1.5");
		Mockito.when(repo.save(entrada)).thenReturn(salida);
		Pelicula resultado = servicio.crear(entrada);
		Assertions.assertEquals(salida, resultado);
	}


}
