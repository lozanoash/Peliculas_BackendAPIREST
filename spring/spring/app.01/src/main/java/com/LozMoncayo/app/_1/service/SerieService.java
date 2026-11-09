package com.LozMoncayo.app._1.service;

import com.LozMoncayo.app._1.models.Serie;
import com.LozMoncayo.app._1.repository.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SerieService {
    private SerieRepository serieRepository;

    public SerieService(SerieRepository serieRepository){
        this.serieRepository = serieRepository;


    }
    //metodo trae todos las Series
    public List<Serie> listarTodas(){
        return serieRepository.findAll();
    }
    //leer un unico elemento o traer una sola serie
    public Optional <Serie> buscarPorId(Long id){
        return serieRepository.findById(id);
    }
    //GUARDAR SERIE
    public Serie guardar(Serie serie){
        return serieRepository.save(serie);

    }
    //editar serie
    public Optional <Serie> actualizar(Long id, Serie datos){
        return serieRepository.findById(id).map(serieExiste ->{
            serieExiste.setTitulo(datos.getTitulo());
            serieExiste.setPlataforma(datos.getPlataforma());
            serieExiste.setCalificacion(datos.getCalificacion());
            return serieRepository.save(serieExiste);
        } );


    }
    //eliminar serie
    public boolean eliminar(Long id){
        if (serieRepository.existsById(id)) {
            serieRepository.deleteById(id);
            return  true;
        }
        return false;
    }
}
