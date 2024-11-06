package com.rumana.casamusical.principal;

import com.rumana.casamusical.modelo.Artista;
import com.rumana.casamusical.modelo.Cancion;
import com.rumana.casamusical.modelo.TipoArtista;
import com.rumana.casamusical.repository.ArtistaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private final ArtistaRepository repositorio;

    private Scanner teclado = new Scanner(System.in);

    public Principal(ArtistaRepository repositorio){
        this.repositorio = repositorio;
    }

    public void muestraElMenu() {

        var opcion = -1;

        while(opcion!= 9) {
            var menu = """
                    *** Screen Sound Musicas ****
                    
                    1- Registrar artistas
                    2- Registras canciones
                    3- Listar canciones
                    4- Buscar canciones por artistas
                    5- Buscar los datos sobre un artista
                    
                    9- Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){
                case 1:
                    registrarArtista();
                    break;
                case 2:
                    registrarCanciones();
                    break;
                case 3:
                    listarCanciones();
                    break;
                case 4:
                    buscarCancionesPorArtista();
                case 5:
                    //buscarDatosDelArtista();
                    break;
                case 9:
                    System.out.println(("Cerrando la aplicación ..."));
                    break;
                default:
                    System.out.println("Opcion inválida");
            }
        }
    }
//  private void buscarDatosDelArtista(){
//        System.out.println("¿DESEA BUSCAR DATOS SOBRE CUÁL ARTISTA?");
//        var nombre = teclado.nextLine();
//        var respuesta = ConsultaChatGPT.obtenerInformacion(nombre);
//        System.out.println(respuesta.trim());
//   }

    private void buscarCancionesPorArtista(){
        System.out.println("¿DESEA BUSCAR CANCIONES DE QUÉ ARTISTA? ");
        var nombre = teclado.nextLine();
        List<Cancion> canciones = repositorio.buscaCancionesPorArtista(nombre);
        canciones.forEach(System.out::println);
    }

    private void listarCanciones(){
        List<Artista> artistas = repositorio.findAll();
        artistas.forEach(a-> a.getCanciones().forEach(System.out::println));
    }

    private void registrarCanciones(){
        System.out.println("DESEA REGISTRAR LA CANCIÓN DE QUÉ ARTISTA?");
        var nombre = teclado.nextLine();

        Optional<Artista> artista = repositorio.findByNombreContainingIgnoreCase(nombre);
        if (artista.isPresent()) {

            System.out.println("INDIQUE EL TÍTULO DE LA CANCIÓN: ");
            var nombreCancion = teclado.nextLine();

            Cancion cancion = new Cancion(nombreCancion);
            cancion.setArtista(artista.get());
            artista.get().getCanciones().add(cancion);
            repositorio.save(artista.get());

        }else {
            System.out.println("Artista no encontrado");
        }
    }

    private void registrarArtista(){
        var registrarNuevo = "S";

        while(registrarNuevo.equalsIgnoreCase("s")){

            System.out.println("ESCRIBA EL NOMBRE DEL ARTISTA: ");
            var nombre = teclado.nextLine();

            System.out.println("REGISTRE EL TIPO DE ESTE ARTISTA: (solo,duo o banda)");
            var tipo = teclado.nextLine();

            TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());
            Artista artista = new Artista(nombre, tipoArtista);
            repositorio.save(artista);
            System.out.println("¿REGISTRAR NUEVO ARTISTA? (S/N))");
            registrarNuevo = teclado.nextLine();
        }

    }
}
