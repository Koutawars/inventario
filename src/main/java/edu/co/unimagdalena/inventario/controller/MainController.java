package edu.co.unimagdalena.inventario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.co.unimagdalena.inventario.entity.Compra;
import edu.co.unimagdalena.inventario.service.CompraService;

@Controller
public class MainController {
	@Autowired
	CompraService service;

	@RequestMapping("/")
	String getIndex() {
		return "index";
	}
	
	@RequestMapping("/inventario")
	String getInventarioView() {
		return "inventario";
	}
	
	@RequestMapping("/compra")
	String getCompraView() {
		return "compra";
	}
	
	@RequestMapping("/factura/{id}")
	String getFacturaView(@PathVariable Long id, Model model) {
		Compra compra = service.getCompra(id);
		model.addAttribute("compra", compra);
		return "factura";
	}
	
}
