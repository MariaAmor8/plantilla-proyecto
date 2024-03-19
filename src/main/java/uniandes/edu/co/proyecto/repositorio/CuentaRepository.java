package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import uniandes.edu.co.proyecto.modelo.Cuenta;
//import uniandes.edu.co.proyecto.modelo.Operacion_cuenta;
//import uniandes.edu.co.proyecto.modelo.Transaccion;

import java.sql.Date;


public interface CuentaRepository extends JpaRepository<Cuenta, Integer>{

    /*TODAS LAS CUENTAS */

    @Query(value = "SELECT * FROM cuentas WHERE id = :id", nativeQuery = true)
    Cuenta darCuenta(@Param("id") Integer id);

    @Query(value = "SELECT * FROM cuentas ", nativeQuery = true)
    Collection<Cuenta> darCuentas();

    @Query(value = "SELECT * FROM cuentas WHERE tipo = :tipo", nativeQuery = true)
    Collection<Cuenta> darCuentasPorTipoCuenta(@Param("tipo") String tipo);

    @Query(value = "SELECT * FROM cuentas WHERE saldo BETWEEN :min_saldo AND :max_saldo", nativeQuery = true)
    Collection<Cuenta> darCuentasPorRangoSaldos(@Param("min_saldo") Float min_saldo, @Param("max_saldo") Float max_saldo);

    /*tambien se utiliza darCuentasPorCliente() */

    @Query(value = "SELECT c.**\\r\\n" + //"
    "FROM cuentas c*\\r\\n" + //"
    "JOIN (*\\r\\n" + //"
        "SELECT id_cuenta AS id, MAX(fecha_operacion) AS fecha_max*\\r\\n" + //"
        "FROM (*\\r\\n" + //"
            "SELECT id_cuenta_origen AS id_cuenta, fecha_operacion*\\r\\n" + //"
            "FROM Transacciones*\\r\\n" + //"
            "UNION ALL*\\r\\n" + //"
            "SELECT id_cuenta_destino AS id_cuenta, fecha_operacion*\\r\\n" + //"
            "FROM Transacciones*\\r\\n" + //"
            "UNION ALL*\\r\\n" + //"
            "SELECT id AS id_cuenta, fecha_operacion*\\r\\n" + //"
            "FROM operaciones_cuenta)*\\r\\n" + //"
        "GROUP BY id_cuenta*\\r\\n" + //"
    ") t ON c.Id = t.id*\\r\\n" + //"
    "WHERE t.fecha_max = :fecha_dada;", nativeQuery = true)
    Collection<Cuenta> darCuentasPorFechaUltimoMovimiento(@Param("fecha_dada") Date fecha_dada);


    /*CUENTAS POR ID OFICINA */

    @Query(value = "SELECT * FROM cuentas WHERE id = :id, id_oficina = :id_oficina", nativeQuery = true)
    Cuenta darCuentaPorIDoficina(@Param("id") Integer id, @Param("id_oficina") Integer id_oficina);

    @Query(value = "SELECT * FROM cuentas WHERE id_oficina = :id_oficina", nativeQuery = true)
    Collection<Cuenta> darCuentasPorIDoficina(@Param("id_oficina") Integer id_oficina);

    @Query(value = "SELECT * FROM cuentas WHERE tipo = :tipo AND id_oficina = :id_oficina", nativeQuery = true)
    Collection<Cuenta> darCuentasPorTipoCuentayIDoficina(@Param("tipo") String tipo, @Param("id_oficina") Integer id_oficina);

    @Query(value = "SELECT * FROM cuentas WHERE saldo BETWEEN :min_saldo AND :max_saldo AND id_oficina = :id_oficina", nativeQuery = true)
    Collection<Cuenta> darCuentasPorRangoSaldosyIDoficina(@Param("min_saldo") Float min_saldo, @Param("max_saldo") Float max_saldo, @Param("id_oficina") Integer id_oficina);

    @Query(value = "SELECT * FROM cuentas WHERE num_doc_cliente = :num_doc_cliente AND id_oficina = :id_oficina", nativeQuery = true)
    Collection<Cuenta> darCuentasPorClienteyIDOficina(@Param("num_doc_cliente") Integer num_doc_cliente, @Param("id_oficina") Integer id_oficina);

    @Query(value = "SELECT c.**\\r\\n" + //"
    "FROM cuentas c*\\r\\n" + //"
    "JOIN (*\\r\\n" + //"
        "SELECT id_cuenta AS id, MAX(fecha_operacion) AS fecha_max*\\r\\n" + //"
        "FROM (*\\r\\n" + //"
            "SELECT id_cuenta_origen AS id_cuenta, fecha_operacion*\\r\\n" + //"
            "FROM Transacciones*\\r\\n" + //"
            "UNION ALL*\\r\\n" + //"
            "SELECT id_cuenta_destino AS id_cuenta, fecha_operacion*\\r\\n" + //"
            "FROM Transacciones*\\r\\n" + //"
            "UNION ALL*\\r\\n" + //"
            "SELECT id AS id_cuenta, fecha_operacion*\\r\\n" + //"
            "FROM operaciones_cuentas)*\\r\\n" + //"
        "GROUP BY id_cuenta*\\r\\n" + //"
    ") t ON c.id = t.id*\\r\\n" + //"
    "WHERE t.fecha_max = :fecha_dada AND c.id_oficina = :id_oficina;", nativeQuery = true)
    Collection<Cuenta> darCuentasPorFechaUltimoMovimientoyIDoficina(@Param("fecha_dada") Date fecha_dada, @Param("id_oficina") Integer id_oficina);


    /*CUENTAS POR CLIENTE */

    @Query(value = "SELECT * FROM cuentas WHERE id = :id AND num_doc_cliente = :num_doc_cliente", nativeQuery = true)
    Collection<Cuenta> darCuentaPorCliente(@Param("num_doc_cliente") Integer num_doc_cliente, @Param("id") Integer id);

    @Query(value = "SELECT * FROM cuentas WHERE num_doc_cliente = :num_doc_cliente", nativeQuery = true)
    Collection<Cuenta> darCuentasPorCliente(@Param("num_doc_cliente") Integer num_doc_cliente);

    @Query(value = "SELECT * FROM cuentas WHERE tipo = :tipo AND num_doc_cliente = :num_doc_cliente", nativeQuery = true)
    Collection<Cuenta> darCuentasPorTipoCuentayCliente(@Param("num_doc_cliente") Integer num_doc_cliente, @Param("tipo") String tipo);

    @Query(value = "SELECT * FROM cuentas WHERE saldo BETWEEN :min_saldo AND :max_saldo AND num_doc_cliente = :num_doc_cliente", nativeQuery = true)
    Collection<Cuenta> darCuentasPorRangoSaldosyCliente(@Param("min_saldo") Float min_saldo, @Param("max_saldo") Float max_saldo, @Param("num_doc_cliente") Integer num_doc_cliente);

    @Query(value = "SELECT c.**\\r\\n" + //"
    "FROM cuentas c*\\r\\n" + //"
    "JOIN (*\\r\\n" + //"
        "SELECT id_cuenta AS id, MAX(fecha_operacion) AS fecha_max*\\r\\n" + //"
        "FROM (*\\r\\n" + //"
            "SELECT id_cuenta_origen AS id_cuenta, fecha_operacion*\\r\\n" + //"
            "FROM Transacciones*\\r\\n" + //"
            "UNION ALL*\\r\\n" + //"
            "SELECT id_cuenta_destino AS id_cuenta, fecha_operacion*\\r\\n" + //"
            "FROM Transacciones*\\r\\n" + //"
            "UNION ALL*\\r\\n" + //"
            "SELECT id AS id_cuenta, fecha_operacion*\\r\\n" + //"
            "FROM operaciones_cuentas)*\\r\\n" + //"
        "GROUP BY id_cuenta*\\r\\n" + //"
    ") t ON c.id = t.id*\\r\\n" + //"
    "WHERE t.fecha_max = :fecha_dada AND c.num_doc_cliente = :num_doc_cliente;", nativeQuery = true)
    Collection<Cuenta> darCuentasPorFechaUltimoMovimientoyCliente(@Param("fecha_dada") Date fecha_dada, @Param("num_doc_cliente") Integer num_doc_cliente);
    

    /*GENERAL */

    @Modifying
    @Transactional
    @Query(value = "UPDATE cuentas SET estado = :estado WHERE saldo = 0 AND estado = 'activa' AND id = :id", nativeQuery = true)
    void actualizarEstadoCuenta(@Param("id") Integer id, @Param("estado") String estado);

    @Modifying
    @Transactional
    @Query(value = "UPDATE cuentas SET estado = 'activa' WHERE saldo = 0 AND estado = 'desactivada' AND id = :id", nativeQuery = true)
    void activarCuenta(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE cuentas SET saldo = saldo - :monto_pago WHERE id = :id AND saldo >= :monto_pago AND estado NOT IN ('cerrada', 'desactivada')" , nativeQuery = true)
    void actualizarSaldoRetiro(@Param("id") Integer id, @Param("monto_pago") Float monto_pago);

    @Modifying
    @Transactional
    @Query(value = "UPDATE cuentas SET saldo = saldo + :monto_pago WHERE AND id = :id", nativeQuery = true)
    void actualizarSaldoConsignar(@Param("id") Integer id, @Param("monto_pago") Float monto_pago);

    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO cuentas (tipo, id, estado, saldo, ultima_transaccion, num_doc_cliente, id_oficina) VALUES (:tipo, general_seq.nextval, :estado, :saldo, :ultima_transaccion, :num_doc_cliente, :id_oficina)", nativeQuery = true)
    void insertarCuenta(@Param("tipo") String tipo, @Param("estado") String estado, @Param("saldo") Float saldo, @Param("ultima_transaccion") Date ultima_transaccion, @Param("num_doc_cliente") Integer num_doc_cliente, @Param("id_oficina") Integer id_oficina);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Cuentas c*\\r\\n" + //"
    "SET ultima_transaccion = (*\\r\\n" + //"
        "SELECT MAX(Fecha_operacion)*\\r\\n" + //"
        "FROM transacciones t*\\r\\n" + //"
        "WHERE t.id_cuenta_origen = c.Id)*\\r\\n" + //"
    "WHERE EXISTS (*\\r\\n" + //"
        "SELECT 1*\\r\\n" + //"
        "FROM transacciones t*\\r\\n" + //"
        "WHERE t.id_cuenta_origen = c.Id)", nativeQuery = true)
    void actualizarFechaUltimaTransaccion();
/* 
    @Modifying
    @Transactional
    @Query(value = "SELECT oc.**\\r\\n" + //"
    "FROM operaciones_cuentas oc*\\r\\n" + //"
    "WHERE oc.id_cuenta = :id AND EXTRACT(MONTH FROM oc.fecha_operacion) = :mes", nativeQuery = true)
    Collection<Operacion_cuenta> listaOperacionesCuentaPorMes(@Param("id") Integer id, @Param("mes") String mes);

    @Modifying
    @Transactional
    @Query(value = "SELECT t.**\\r\\n" + //"
    "FROM transacciones t*\\r\\n" + //"
    "WHERE t.id_cuenta_destino = :id AND EXTRACT(MONTH FROM t.fecha_operacion) = :mes", nativeQuery = true)
    Collection<Transaccion> listaTransaccionesCuentaPorMes(@Param("id") Integer id, @Param("mes") String mes);

    
*/

    
}