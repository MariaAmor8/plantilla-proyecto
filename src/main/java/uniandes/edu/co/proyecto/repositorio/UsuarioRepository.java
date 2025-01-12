package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import uniandes.edu.co.proyecto.modelo.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

    @Query(value = "SELECT * FROM usuarios",nativeQuery = true)
    Collection<Usuario> darUsuarios();

    @Query(value = "SELECT * FROM usuarios WHERE numId = :numId",nativeQuery = true)
    Usuario darUsuario(@Param("numId") int numId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO usuarios (tipoDoc, numId, nombre, email, nacionalidad, telefono, tipoUsuario,lugar) VALUES (general_seq.nextval, :tipoDoc, :numId, :nombre, :email, :nacionalidad, :telefono, :tipoUsuario, :lugar", nativeQuery = true)
    void insertarUsuario(@Param("numId") int numId,@Param("nombre") String nombre,@Param("email") String email,
                        @Param("nacionalidad") String nacionalidad,@Param("telefono") int telefono,@Param("tipoUsuario") int tipoUsuario,@Param("lugar") int lugar);

    @Modifying
    @Transactional
    @Query(value = "UPDATE usuarios SET nombre = :nombre, email =:email, nacionalidad = :nacionalidad, telefono = :telefono, tipoUsuario = :tipoUsuario, lugar = :lugar WHERE numId = :numId", nativeQuery = true)
    void updateUsuario(@Param("numId") int numId,@Param("nombre") String nombre,@Param("email") String email,
                        @Param("nacionalidad") String nacionalidad,@Param("telefono") int telefono,@Param("tipoUsuario") int tipoUsuario,@Param("lugar") int lugar);

    @Modifying
    @Transactional
    @Query(value =  "DELETE FROM usuarios WHERE numId = :numId", nativeQuery = true)
    void eliminarUsuario(@Param("numId") int numId);
}
