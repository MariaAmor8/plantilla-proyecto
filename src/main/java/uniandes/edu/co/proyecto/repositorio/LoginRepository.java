package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Login;

public interface LoginRepository extends JpaRepository<Login, Integer> {
    
    @Query(value = "SELECT * FROM logins",nativeQuery = true)
    Collection<Login> darLogins();

    @Query(value = "SELECT * FROM logins WHERE login = :login AND palabraClave = :palabraClave",nativeQuery = true)
    Login darLogin(@Param("login") String login,@Param("palabraClave") String palabraClave);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO logins (login, palabraClave, tipoDoc, numId) VALUES ( :login, :palabraClave, :tipoDoc, :numId)", nativeQuery = true)
    void insertarLogin(@Param("login") String login,@Param("palabraClave") String palabraClave,@Param("tipoDoc") String tipoDoc,@Param("numId") int numId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE logins SET login=:login, palabraClave = :palabraClave, tipoDoc = :tipoDoc, numId = :numId WHERE login = :login", nativeQuery = true)
    void updateLogin(@Param("login") String login,@Param("palabraClave") String palabraClave,@Param("tipoDoc") String tipoDoc,@Param("numId") int numId);

    @Modifying
    @Transactional
    @Query(value =  "DELETE FROM logins WHERE login = :login AND palabraClave =:palabraClave", nativeQuery = true)
    void eliminarLogin(@Param("login") String login,@Param("palabraClave")String palabraClave);
}