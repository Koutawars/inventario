package edu.co.unimagdalena.inventario.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

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
}
