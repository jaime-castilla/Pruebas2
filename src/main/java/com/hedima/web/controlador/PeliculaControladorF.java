package com.hedima.web.controlador;


import com.hedima.web.modelo.Pelicula;
import com.hedima.web.servicio.IPeliculaServicio;
import com.hedima.web.excepciones.ExcepcionNoEncontradoModelo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;



@RestController
@RequestMapping("/Peliculas")
public class PeliculaControladorF {
    @Autowired
    private IPeliculaServicio servicio;
   @GetMapping
    public ResponseEntity<List<Pelicula>> consultarTodos() {

       return new ResponseEntity<>(servicio.mostrarTodos(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> consultarUno(@PathVariable("id") int id) {
       Pelicula resultadoBBDD = servicio.mostrarUno(id);
       if (resultadoBBDD == null){
           throw new ExcepcionNoEncontradoModelo("ID no encontrado " + id);
       }
        return new ResponseEntity(servicio.mostrarUno(id),HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<Pelicula> crear(@RequestBody Pelicula Pelicula) {
//        System.out.println(Pelicula.toString());
//        return new ResponseEntity<>(servicio.crear(Pelicula), HttpStatus.CREATED);
//    }
    @PostMapping
    public ResponseEntity<Void> crear(@RequestBody Pelicula Pelicula) throws Exception{
        System.out.println(Pelicula.toString());
        Pelicula obj = servicio.crear(Pelicula);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getPeliculaId()).toUri();
        return  ResponseEntity.created(location).build();
    }
    @PutMapping
    public ResponseEntity<Pelicula> modificar(@RequestBody Pelicula Pelicula) {
        Pelicula resultadoBBDD = servicio.mostrarUno((Pelicula.getPeliculaId()));
        if (resultadoBBDD == null){
            throw new ExcepcionNoEncontradoModelo("ID no encontrado " + Pelicula.getClasificacion());
        }
        return new ResponseEntity(servicio.modificar(Pelicula),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") int id){
        Pelicula resultadoBBDD = servicio.mostrarUno(id);
        if (resultadoBBDD == null){
            throw new ExcepcionNoEncontradoModelo("ID no encontrado " + id);
        }
        servicio.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
