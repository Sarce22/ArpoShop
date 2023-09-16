package com.arpo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.arpo.models.Rol;
import com.arpo.models.User;
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
	        return "usuario/add-user";
	    }
	    
	    @GetMapping("/listado-usuarios")
	    public String listaUsuarios(Model model) {
	        model.addAttribute("ListaDeUsuarios", userService.listUser());
	        return "usuario/listar-usuarios";
	    }

	    @PostMapping("/guardarUsuarios")
	    public String guardarUsuario(@ModelAttribute User usuario, Model model) {
	        boolean usuarioId = userService.alReadyExist(usuario.getIdUser());
	        if(usuarioId) {
	            model.addAttribute("errorIdDuplicado", true);
	        } else {
	            int idRol = usuario.getIdRol().getId_Rol();
	            Rol rolSeleccionado = rolService.getRolById(idRol);
	            usuario.setIdRol(rolSeleccionado);
	            userService.save(usuario);
	            return "redirect:/user/listado-usuarios";
	        }
	        // Si el usuario ya existe o hay otro error, muestra el formulario de registro nuevamente
	        List<Rol> roles = rolService.getRoleList();
	        model.addAttribute("roles", roles);
	        model.addAttribute("usuario", usuario);
	        return "usuario/add-user";
	    }

	    @GetMapping("/admin/editUser/{idUser}")
		public String showUpdateForm(@PathVariable("idUser") Long idUser, Model model) {
			User user = userService.getById(idUser);
			model.addAttribute("usuario", user);
			return "usuario/update-user";
	    }
		
	    
	    @PostMapping("/admin/updateUser/{idUser}")
		public String updateUser(@PathVariable("idUser") Long id, User user, BindingResult result, Model model) {
	    	if (result.hasErrors()) {
	            // Si hay errores de validación, muestra la vista de actualización nuevamente con mensajes de error.
	            return "usuario/update-user";
	    	}

	        // Busca el usuario existente por su id en la base de datos

	    	User alreadyExist = userService.getById(id);
	        if (alreadyExist != null) {
	            // Actualiza los datos del usuario existente con los datos del formulario
	        	alreadyExist.setName(user.getName());
	        	alreadyExist.setSurname(user.getSurname());
	        	alreadyExist.setAddress(user.getAddress());
	        	alreadyExist.setAge(user.getAge());
	        	alreadyExist.setEmail(user.getEmail());
	        	alreadyExist.setPhoneNumber(user.getPhoneNumber());
	        	alreadyExist.setPassword(user.getPassword());

	            // Guarda los cambios en la base de datos
	            userService.save(alreadyExist);
	            
	            // Redirige a la página de listado de usuarios con un mensaje de éxito
	            model.addAttribute("successMessage", "El usuario ha sido modificado con éxito.");
	            return "redirect:/user/listado-usuarios";
	        }
			return null;
		}
	    
	    
<<<<<<< HEAD
	    
}
=======
	    @GetMapping("/admin/deleteUser/{idUser}")
	    public String deleteEmpleado(@PathVariable("idUser") Long idUser, Model model) {
	 	        User user = userService.getById(idUser);
	 	        userService.delete(user.getIdUser());
	 	        model.addAttribute("usuario", userService.listUser());
	 	        return "redirect:/user/listado-usuarios";
	    }

   
}
>>>>>>> 0686c6515253f96261596d0a16044377b85527f4
