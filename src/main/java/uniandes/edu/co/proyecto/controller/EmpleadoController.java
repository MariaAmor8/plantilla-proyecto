package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import uniandes.edu.co.proyecto.modelo.Empleado;
import uniandes.edu.co.proyecto.repositorio.EmpleadoRepository;

public class EmpleadoController {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping("/empleados")
    public String empleados(Model model){
        model.addAttribute("empleados", empleadoRepository.darEmpleados());
        return "empleados";
    }
    
    @GetMapping("/empleados/new")
    public String clienteForm(Model model){
        model.addAttribute("empleado", new Empleado());
        return "empleadoNuevo";
    }

    @GetMapping("/empleados/new/save")
    public String empleadosave(@ModelAttribute Empleado empleado){
        empleadoRepository.insertarEmpleado(empleado.getNum_id(), empleado.getCargo());
        return "empleadoNuevo";
    }
}
