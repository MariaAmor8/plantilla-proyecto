package uniandes.edu.co.proyecto.modelo;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table (name="usuarios")
public class Usuario {
    
    @Id
    @Column(name = "num_id")
    private Integer num_id;

    private String nombre;
    private String email;
    private String nacionalidad;
    private int telefono;
    private int tipoUsuario;

    @JoinColumn(name = "id")
    @OneToOne
    private Lugar lugar;


    public Usuario(Integer num_id, String nombre, String email, String nacionalidad, int telefono, int tipoUsuario,
            Lugar lugar) {
                
        this.num_id = num_id;
        this.nombre = nombre;
        this.email = email;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.tipoUsuario = tipoUsuario;
        this.lugar = lugar;
    }

    public Usuario(){;}

 

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    public Integer getNum_id() {
        return num_id;
    }

    public void setNum_id(Integer num_id) {
        this.num_id = num_id;
    }


}   
