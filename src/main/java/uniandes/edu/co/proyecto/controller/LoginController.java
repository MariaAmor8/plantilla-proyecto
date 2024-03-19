package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Login;
import uniandes.edu.co.proyecto.repositorio.LoginRepository;

public class LoginController {

    @Autowired
    private LoginRepository loginRepository;

    @GetMapping("/logins/new")
    public String loginForm(Model model) {
        model.addAttribute("login", new Login());
        return "loginNuevo";
    }

    @PostMapping("/logins/new/save")
    public String loginGuardar(@ModelAttribute Login login) {
        loginRepository.insertarLogin(login.getLogin(), login.getPalabra_clave(), login.getTipo_doc(), login.getNum_id());
        return "redirect:/logins";
    }

    @GetMapping("/logins/{login}/{palabraClave}/edit")
    public String loginEditarForm(@PathVariable("login") String loginString,@PathVariable("palabraClave") String palabraClave, Model model) {
        Login login = loginRepository.darLogin(loginString,palabraClave);
        if (login != null) {
            model.addAttribute("login", login);
            return "loginEditar";
        } else {
            return "redirect:/logins";
        }
    }

    @PostMapping("/logins/{login}/{palabraClave}/edit/save")
    public String loginEditarGuardar(@PathVariable("login") String loginString,@PathVariable("palabraClave") String palabraClave, @PathVariable("numId") int numId, @ModelAttribute Login login) {

        loginRepository.updateLogin(loginString, palabraClave, palabraClave, numId);
        return "redirect:/logins";
    }

    @GetMapping("/logins/{login}/{palabraClave}/delete")
    public String loginBorrar(@PathVariable("login") String loginString,@PathVariable("palabraClave") String palabraClave) {
        loginRepository.eliminarLogin(loginString,palabraClave);
        return "redirect:/logins";
    }
}
