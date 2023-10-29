package com.arpo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.arpo.service.ProductService;
import com.arpo.utils.PDF;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;

public class PDFController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/export/pdf")
	public void exportToPDF (HttpServletResponse response ) throws DocumentException, IOException{
		response.setContentType("application/pdf");


		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=factura.pdf";
		response.setHeader(headerKey, headerValue);

		PDF exporter = new PDF();
		exporter.export(response);

	}
	
	//Para hacer merger
}
