package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "oficinas")
public class Oficina {
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nombre;
    private String departamento;
    private String ciudad;
    private String direccion;
    private Integer numeroPuntosAt;
    private String horaApertura;
    private String horaCierre;

    @ManyToOne
    @JoinColumn(name = "num_id", referencedColumnName = "num_id")
    private Empleado num_doc_gerente_oficina;


    public Oficina(String nombre, String departamento, String ciudad, String direccion, Integer numeroPuntosAt,String horaApertura,String horaCierre,Empleado num_doc_gerente_oficina){
        this.nombre = nombre;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.numeroPuntosAt = numeroPuntosAt;
        this.horaApertura = horaApertura;
        this.horaCierre = horaApertura;
        this.num_doc_gerente_oficina = num_doc_gerente_oficina;
    }

    public Oficina(){
        ;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getNumeroPuntosAt() {
        return numeroPuntosAt;
    }

    public void setNumeroPuntosAt(Integer numeroPuntosAtencion) {
        this.numeroPuntosAt = numeroPuntosAtencion;
    }

    public String getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(String horaApertura) {
        this.horaApertura = horaApertura;
    }

    public String getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(String horaCierre) {
        this.horaCierre = horaCierre;
    }

    public Empleado getNum_doc_gerente_oficina() {
        return num_doc_gerente_oficina;
    }

    public void setNum_doc_gerente_oficina(Empleado num_doc_gerente_oficina) {
        this.num_doc_gerente_oficina = num_doc_gerente_oficina;
    }



}
