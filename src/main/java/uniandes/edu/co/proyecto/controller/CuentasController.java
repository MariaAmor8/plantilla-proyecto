package uniandes.edu.co.proyecto.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.proyecto.repositorio.CuentaRepository;
import uniandes.edu.co.proyecto.modelo.Cuenta;


import org.springframework.ui.Model;

@Controller
public class CuentasController {
    @Autowired
    private CuentaRepository cuentaRepository;

    @GetMapping("/cuentas")
    public String cuentas(Model model, String tipo, Float min_saldo, Float max_saldo, Date fecha_dada, Integer num_doc_cliente) {
        cuentaRepository.actualizarFechaUltimaTransaccion();
        if((tipo == null || tipo.equals("")) && (min_saldo == null || max_saldo == null) && (fecha_dada == null) && (num_doc_cliente == null)  ){
            model.addAttribute("cuentas", cuentaRepository.darCuentas());
        }else if(tipo != null && !tipo.equals("")){
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorTipoCuenta(tipo));
        }else if(min_saldo != null && max_saldo != null){
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorRangoSaldos(min_saldo, max_saldo));
        }else if((fecha_dada != null)){
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorFechaUltimoMovimiento(fecha_dada));
        }else if(num_doc_cliente != null){
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorCliente(num_doc_cliente));
        }
        return "cuentas";

    }

    @GetMapping("/oficina/cuentas")
    public String oficinaCuentas(Model model, String tipo, Float min_saldo, Float max_saldo, Date fecha_dada, Integer num_doc_cliente, Integer id_oficina) {
        cuentaRepository.actualizarFechaUltimaTransaccion();
        if((tipo == null || tipo.equals("")) && (min_saldo == null || max_saldo == null) && (fecha_dada == null) && ( num_doc_cliente == null)  ){
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorIDoficina(id_oficina));
        }else if(tipo != null && !tipo.equals("")){
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorTipoCuentayIDoficina(tipo, id_oficina));
        }else if(min_saldo != null && max_saldo != null){
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorRangoSaldosyIDoficina(min_saldo, max_saldo, id_oficina));
        }else if((fecha_dada != null)){
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorFechaUltimoMovimientoyIDoficina(fecha_dada, id_oficina));
        }else if( num_doc_cliente != null){
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorClienteyIDOficina(num_doc_cliente, id_oficina));
        }
        return "cuentas";

    }

    @GetMapping("/cliente/cuentas")
    public String clienteCuentas(Model model, String tipo, Float min_saldo, Float max_saldo, Date fecha_dada, Integer num_doc_cliente) {
        cuentaRepository.actualizarFechaUltimaTransaccion();
        if((tipo == null || tipo.equals("")) && (min_saldo == null || max_saldo == null) && (fecha_dada == null) && ( num_doc_cliente == null)  ){
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorCliente(num_doc_cliente));
        }else if(tipo != null && !tipo.equals("")){
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorTipoCuentayCliente(num_doc_cliente, tipo));
        }else if(min_saldo != null && max_saldo != null){
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorRangoSaldosyCliente(min_saldo, max_saldo, num_doc_cliente));
        }else if((fecha_dada != null)){
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorFechaUltimoMovimientoyCliente(fecha_dada, num_doc_cliente));
        }
        return "cuentas";

    }


    @GetMapping("/oficina/cuentas/new")
    public String cuentaForm(Model model) {
        model.addAttribute("cuenta", new Cuenta());
        return "cuentaNueva";
    }

    @PostMapping("oficina/cuentas/new/save")
    public String cuentaGuardar(@PathVariable("tipo") String tipo, @ModelAttribute Cuenta cuenta) {
        cuentaRepository.insertarCuenta(cuenta.getTipo(), cuenta.getEstado(), cuenta.getSaldo(), cuenta.getUltima_transaccion(), cuenta.getCliente().getNum_id(), cuenta.getId_oficina().getId());
        return "redirect:/cuentas";
    }

    @GetMapping("/oficina/cuentas/{tipo}/{id}/edit")
    public String cuentaEditarForm(@PathVariable("id") Integer id, Model model) {
        Cuenta cuenta = cuentaRepository.darCuenta(id);
        if (cuenta != null) {
            model.addAttribute("cuenta", cuenta);
            return "cuentaEditar";
        } else {
            return "redirect:/oficina/cuentas";
        }
    }

    @PostMapping("/oficina/cuentas/{tipo}/{id}/edit/save")
    public String cuentaEditarGuardar(@PathVariable("id") Integer id, @RequestParam("nuevoEstado") String nuevoEstado, @ModelAttribute Cuenta cuenta) {
        cuentaRepository.actualizarEstadoCuenta(id, nuevoEstado);
        return "redirect:/oficina/cuentas";
    }

    @GetMapping("/cliente/cuentas/{tipo}/{id}/edit")
    public String cuentaEditarFormCliente(@PathVariable("id") Integer id, Model model) {
        Cuenta cuenta = cuentaRepository.darCuenta(id);
        if (cuenta != null) {
            model.addAttribute("cuenta", cuenta);
            return "cuentaEditar";
        } else {
            return "redirect:/oficina/cuentas";
        }
    }

    @PostMapping("/cliente/cuentas/{tipo}/{id}/edit/save")
    public String cuentaEditarGuardarCliente(@PathVariable("id") Integer id, @RequestParam("nuevoEstado") String nuevoEstado, @ModelAttribute Cuenta cuenta) {
        cuentaRepository.actualizarEstadoCuenta(id, nuevoEstado);
        return "redirect:/oficina/cuentas";
    }

    @GetMapping("/oficina/cuentas/{tipo}/{id}/activar")
    public String cuentaActivarForm(@PathVariable("id") Integer id, Model model) {
        Cuenta cuenta = cuentaRepository.darCuenta(id);
        if (cuenta != null) {
            model.addAttribute("cuenta", cuenta);
            return "cuentaActivar";
        } else {
            return "redirect:/oficina/cuentas";
        }
    }

    @PostMapping("/oficina/cuentas/{tipo}/{id}/activar/save")
    public String cuentaActivarGuardar(@PathVariable("id") Integer id, @ModelAttribute Cuenta cuenta) {
        cuentaRepository.activarCuenta(id);
        return "redirect:/oficina/cuentas";
    }


    @GetMapping("/oficina/cuentas/{id}/extracto")
    public String extractoPorMesOficina(Model model, @PathVariable("id") Integer id_cuenta, @RequestParam("mes") String mes) {

        model.addAttribute("operaciones", cuentaRepository.listaOperacionesCuentaPorMes(id_cuenta, mes));
        model.addAttribute("transacciones", cuentaRepository.listaTransaccionesCuentaPorMes(id_cuenta, mes));
        return "operaciones_transacciones";
    }

    @GetMapping("/cuentas/{id}/extracto")
    public String extractoPorMesGerente(Model model, @PathVariable("id") Integer id_cuenta, @RequestParam("mes") String mes) {

    model.addAttribute("operaciones", cuentaRepository.listaOperacionesCuentaPorMes(id_cuenta, mes));
    model.addAttribute("transacciones", cuentaRepository.listaTransaccionesCuentaPorMes(id_cuenta, mes));
    return "operaciones_transacciones";
}






}
