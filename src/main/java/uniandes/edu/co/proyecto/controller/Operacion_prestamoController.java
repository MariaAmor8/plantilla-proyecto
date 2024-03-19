package uniandes.edu.co.proyecto.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import uniandes.edu.co.proyecto.modelo.Operacion_prestamo;
import uniandes.edu.co.proyecto.repositorio.PrestamoRepository;
import uniandes.edu.co.proyecto.repositorio.Operacion_prestamoRepository;

@Controller
public class Operacion_prestamoController {
    @Autowired
    private Operacion_prestamoRepository operacion_prestamoRepository;

    @Autowired
    private PrestamoRepository prestamoRepository;

    @GetMapping("/operaciones_prestamos")
    public String operaciones_prestamos(Model model){
        model.addAttribute("operaciones_prestamos", operacion_prestamoRepository.darOperaciones());
        return "operaciones_prestamos";
    }

    @GetMapping("/operaciones_prestamos/new")
    public String operacion_prestamoForm(Model model) {
        model.addAttribute("operacion_prestamo", new Operacion_prestamo());
        return "operacion_prestamoNueva";
    }

    @PostMapping("/operaciones_prestamos/new/save")
    public String operacion_prestamoGuardar(@ModelAttribute Operacion_prestamo operacion_prestamo) {
        operacion_prestamoRepository.insertarOperacion_prestamo(operacion_prestamo.getTipo_operacion(), operacion_prestamo.getFecha_operacion(), operacion_prestamo.getId_prestamo().getId(), operacion_prestamo.getMonto_pago(), 
        operacion_prestamo.getPunto_atencion().getId());

        prestamoRepository.reducirSaldoPrestamo(operacion_prestamo.getId_prestamo().getId(), operacion_prestamo.getMonto_pago());
        return "redirect:/operaciones_prestamos";
    }


}
