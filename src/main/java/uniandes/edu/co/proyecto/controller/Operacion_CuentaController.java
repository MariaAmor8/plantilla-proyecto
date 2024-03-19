package uniandes.edu.co.proyecto.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Operacion_cuenta;
import uniandes.edu.co.proyecto.repositorio.CuentaRepository;
import uniandes.edu.co.proyecto.repositorio.Operacion_cuentaRepository;

@Controller
public class Operacion_CuentaController {
    @Autowired
    private Operacion_cuentaRepository operacion_cuentaRepository;

    @Autowired
    private CuentaRepository cuentaRepository;


    @GetMapping("/operaciones_cuentas/new")
    public String operacion_cuentaForm(Model model) {
        model.addAttribute("operacion_cuenta", new Operacion_cuenta());
        return "operacion_cuentaNueva";
    }

    @PostMapping("/operaciones_cuentas/new/save")
    public String operacion_cuentaGuardar(@ModelAttribute Operacion_cuenta operacion_cuenta) {
        operacion_cuentaRepository.insertarOperacion_cuenta(operacion_cuenta.getTipo_operacion(), operacion_cuenta.getFecha_operacion(), operacion_cuenta.getId_cuenta().getId(),
        operacion_cuenta.getMonto_pago(), operacion_cuenta.getPunto_atencion().getId());
        if(operacion_cuenta.getTipo_operacion().equals("consignar")){
            cuentaRepository.actualizarSaldoConsignar(operacion_cuenta.getId_cuenta().getId(), operacion_cuenta.getMonto_pago());
        }else{
            cuentaRepository.actualizarSaldoRetiro(operacion_cuenta.getId_cuenta().getId(), operacion_cuenta.getMonto_pago());
        }

        return "redirect:/operaciones_cuentas";
    }

}
