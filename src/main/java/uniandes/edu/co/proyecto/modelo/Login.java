package uniandes.edu.co.proyecto.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="logins")
public class Login {

    @Id
    private int num_id;

    private String login;
    private String palabra_clave;
    private String tipo_doc;


    public Login(String login, String palabraClave, String tipoDoc, int numId){

        this.login = login;
        this.palabra_clave = palabraClave;
        this.tipo_doc = tipoDoc;
        this.num_id = numId;
    }

    public Login(){
        ;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPalabra_clave() {
        return palabra_clave;
    }

    public void setPalabra_clave(String palabra_clave) {
        this.palabra_clave = palabra_clave;
    }

    public String getTipo_doc() {
        return tipo_doc;
    }

    public void setTipo_doc(String tipo_doc) {
        this.tipo_doc = tipo_doc;
    }

    public int getNum_id() {
        return num_id;
    }

    public void setNum_id(int num_id) {
        this.num_id = num_id;
    }

    

}
