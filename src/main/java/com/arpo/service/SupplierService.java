package com.arpo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpo.models.Supplier;
import com.arpo.repository.ISupplierRepository;

@Service
public class SupplierService {

	@Autowired
	private ISupplierRepository proveedorRepository;
	
	public List<Supplier> listarProveedores (){
		return proveedorRepository.findAll();
	}
	
	 public Supplier obtenerPorId(Long id) {
	        return proveedorRepository.findById(id).orElse(null);
	    }

	    public Supplier guardar(Supplier proveedor) {
	        return proveedorRepository.save(proveedor);
	    }

	    public void eliminar(Long id) {
	    	proveedorRepository.deleteById(id);
	    }
}
