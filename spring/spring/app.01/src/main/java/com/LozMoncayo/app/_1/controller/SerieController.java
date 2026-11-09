package com.LozMoncayo.app._1.controller;

import com.LozMoncayo.app._1.models.Serie;
import com.LozMoncayo.app._1.repository.SerieRepository;
import com.LozMoncayo.app._1.service.SerieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/series")
public class SerieController {

    private final SerieService serieService;

    public SerieController(SerieService serieService){
        this.serieService = serieService;
    }
    // metodo ver todas las series
    @GetMapping
    public List <Serie> listar() {
        return  serieService.listarTodas();
    }

    //ver unico
    @GetMapping("/{id}")
    public  ResponseEntity <Serie> buscarPorId(@PathVariable Long id){
        return serieService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //guardar serie
    @PostMapping
    public ResponseEntity <Serie> guardar(@RequestBody Serie serie){
        Serie nuevoSerie = serieService.guardar(serie);
        return new ResponseEntity<>(nuevoSerie, HttpStatus.CREATED);
    }
    //editar estudiante
    @PutMapping("/{id}")
    public ResponseEntity <Serie> actualizar(@PathVariable Long id, @RequestBody Serie serie){
        return serieService.actualizar(id, serie)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> eliminar(@PathVariable Long id){
        if (serieService.eliminar(id)){
            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.notFound().build();
    }
}
