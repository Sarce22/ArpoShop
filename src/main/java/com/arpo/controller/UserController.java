package com.arpo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arpo.models.Rol;
import com.arpo.models.User;
import com.arpo.models.Usuario;
import com.arpo.service.RolService;
import com.arpo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	 @Autowired
	 private UserService userService;
	 
	 @Autowired
	 private RolService rolService;
	    
	 @GetMapping("/registro")
	    public String mostrarFormularioRegistro(Model model) {
	        List<Rol> roles = rolService.getRoleList();
	        model.addAttribute("roles", roles);
	        model.addAttribute("usuario", new User());
	        return "add-user";
	    }
	    
	    @GetMapping("/listado-usuarios")
	    public String listaUsuarios(Model model) {
	        model.addAttribute("ListaDeUsuarios", userService.listUser());
	        return "listar-usuarios";
	    }

	    @PostMapping("/guardarUsuarios")
	    public String guardarUsuario(@ModelAttribute Usuario usuario, Model model) {
	    	boolean usuarioId = userService.existeUsuarioConid(usuario.getIdUsuario());
	    	if(usuarioId) {
	    		model.addAttribute("errorIdDuplicado", true);
	    		return "redirect:/user/registro";
	    	}
	        int idRol = usuario.getIdRol().getId_Rol(); 
	        Rol rolSeleccionado = rolService.obtenerRolPorId(idRol);
	        usuario.setIdRol(rolSeleccionado);
	        userService.guardar(usuario);
	        System.out.print(usuario);
	        return "redirect:/user/listado-usuarios";
	    }
	    
}
