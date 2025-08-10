package com.msvc.usuario.service.Impl;

import com.msvc.usuario.entities.Calificacion;
import com.msvc.usuario.entities.Hotel;
import com.msvc.usuario.entities.Usuario;
import com.msvc.usuario.exceptions.ResourceNotFoundException;
import com.msvc.usuario.external.services.HotelService;
import com.msvc.usuario.repository.Usuariorepository;
import com.msvc.usuario.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private Usuariorepository usuariorepository;

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        String randomUsuario = UUID.randomUUID().toString();
        usuario.setUsuarioId(randomUsuario);
        return usuariorepository.save(usuario);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuariorepository.findAll();
    }

    @Override
    public Usuario getUsuario(String usuarioId) {
        Usuario usuario = usuariorepository.findById(usuarioId).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el ID :" + usuarioId));
        /*
        Calificacion[] calificacionesDelUsuario = restTemplate.getForObject("http://localhost:8084/calificaciones/" + usuarioId, Calificacion[].class);
        List<Calificacion> calificaciones= Arrays.stream(calificacionesDelUsuario).collect(Collectors.toList());

        List<Calificacion> listaCalificaciones=calificaciones.stream().map(calificacion -> {
            System.out.println(calificacion.getHotelId());
            ResponseEntity<Hotel> forEntiy=restTemplate.getForEntity("http://localhost:8095/hoteles/" + calificacion.getHotelId(), Hotel.class);
            Hotel hotel=forEntiy.getBody();
            log.info("Respuesta con codigo de estado : {}",forEntiy.getStatusCode());
            calificacion.setHotel(hotel);
            return calificacion;
        });

         */
        // Obtener las calificaciones (manejo de null)
        Calificacion[] array = restTemplate.getForObject("http://CALIFICACION-SERVICE/calificaciones/usuarios/" + usuarioId, Calificacion[].class);
        if (array == null) array = new Calificacion[0];

        // Transformar a lista y asignar hoteles
        List<Calificacion> listaCalificaciones = Arrays.stream(array)
                // .parallelStream() // Descomentar si quieres hacerlo en paralelo (ten precaución)
                .map(calificacion -> {

                        log.info("Obteniendo hotelId: {}", calificacion.getHotelId());
                       // ResponseEntity<Hotel> respuesta = restTemplate.getForEntity(
                       //         "http://HOTEL-SERVICE/hoteles/" + calificacion.getHotelId(),
                        //        Hotel.class
                        ResponseEntity<Hotel> respuesta = hotelService.getHotel(calificacion.getHotelId());
                        //);
                        log.info("Respuesta con codigo de estado : {}", respuesta.getStatusCode());
                        Hotel hotel = respuesta.getBody();
                        calificacion.setHotel(hotel);

                    return calificacion;
                })
                .collect(Collectors.toList());


        log.info("{}", listaCalificaciones);
        usuario.setCalificacion(listaCalificaciones);

        return usuario;
    }
}
