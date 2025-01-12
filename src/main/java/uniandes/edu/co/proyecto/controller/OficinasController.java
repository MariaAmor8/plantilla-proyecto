package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Oficina;
import uniandes.edu.co.proyecto.repositorio.OficinaRepository;

@Controller
public class OficinasController {

    @Autowired
    OficinaRepository oficinaRepository;

    @GetMapping("/oficinas")
    public String puntosAtencion(Model model) {
        model.addAttribute("oficinas", oficinaRepository.darOficinas());
        return "/oficinas";
    }

    @GetMapping("/oficinas/new")
    public String oficinasForm(Model model) {
        model.addAttribute("oficinas", new Oficina());
        return "/oficinas";
    }

    @PostMapping("oficinas/new/save")
    public String oficinaGuardar(@ModelAttribute Oficina oficina) {
       String cargo = oficinaRepository.comprobarGerenteOficina(oficina.getNum_doc_gerente_oficina().getNum_id());
        if(cargo == "gerente de oficina"){

        oficinaRepository.insertarOficina(oficina.getNombre(), oficina.getDepartamento(), oficina.getCiudad(),
                oficina.getDireccion(), oficina.getNumeroPuntosAt(), oficina.getHoraApertura(), oficina.getHoraCierre(),
                oficina.getNum_doc_gerente_oficina().getNum_id());
        return "redirect:/oficinas";
        }
        else{
            //TODO mirar a donde redirecciona si hay error
            return "redirect:/oficinas";
        }
    }

    @GetMapping("oficinas/{id}/edit")
    public String oficinaEditarForm(@PathVariable("id") int id, Model model) {
        Oficina oficina = oficinaRepository.darOficina(id);
        if (oficina != null) {
            model.addAttribute("oficina", oficina);
            return "oficinaEditar";
        } else {
            return "redirect:/oficinas";
        }

    }

    @GetMapping("oficinas/{id}/edit/save")
    public String oficinaEditarGuardar(@PathVariable("id") int id, @ModelAttribute Oficina oficina) {
        oficinaRepository.actualizarOficina(id, oficina.getNombre(), oficina.getDepartamento(), oficina.getCiudad(),
        oficina.getDireccion(), oficina.getNumeroPuntosAt(), oficina.getHoraApertura(), oficina.getHoraCierre(),
        oficina.getNum_doc_gerente_oficina().getNum_id());
        return "redirect:/oficinas";
    }

    @GetMapping("oficinas/{id}/delete")
    public String oficinaEliminar(@PathVariable("id") int id){
        oficinaRepository.eliminarOficina(id);
        return "redirect:/oficinas";
    }

}
