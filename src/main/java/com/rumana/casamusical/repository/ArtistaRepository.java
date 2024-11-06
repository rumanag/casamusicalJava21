package com.rumana.casamusical.repository;

import com.rumana.casamusical.modelo.Artista;
import com.rumana.casamusical.modelo.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {

    Optional<Artista> findByNombreContainingIgnoreCase(String nome);

    @Query("SELECT m FROM Artista a JOIN a.canciones m WHERE a.nombre ILIKE %:nombre%")
    List<Cancion> buscaCancionesPorArtista(String nombre);
}
