package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="empleados")
public class Empleado {

    @Id
    @Column(name = "num_id")
    private Integer num_id;

    private Integer cargo;

    public Empleado(Integer num_id, Integer cargo) {
        this.num_id = num_id;
        this.cargo = cargo;
    }

    public Empleado(){}

    public Integer getCargo() {
        return cargo;
    }

    public void setCargo(Integer cargo) {
        this.cargo = cargo;
    }

    public Integer getNum_id() {
        return num_id;
    }

    public void setNum_id(Integer num_id) {
        this.num_id = num_id;
    }

    
    
}