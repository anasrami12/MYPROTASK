package com.app.myprotask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.myprotask.model.Role;
import com.app.myprotask.model.User;
import com.app.myprotask.model.dao.DAOService;

/**
 */
@RestController
@RequestMapping(value = "api/role")
public class RoleController {

	@Autowired
	DAOService daoS;

	/**
	 * Used in Users [ User ]
	 *
	 * @param idUser
	 * @return objet manager or member of a user
	 */
	@GetMapping(value = "/displayRoleUserProjectByIdUser")
	public ResponseEntity<?> displayRoleUserProjectByIdUser(@RequestParam("idUser") Long idUser) {
		try {
			User user = daoS.displayUserById(idUser);
			if (user != null) {
				Role role;
				if (user.getRole().getName().equals("admin")) {
					role = daoS.getRoleByName("admin");
				} else {
					if (daoS.displayRoleUserProjectByIdUser(idUser) >= 1) {
						role = daoS.getRoleByName("manager");
					} else {
						role = daoS.getRoleByName("employee");
					}
				}
				return ResponseEntity.status(HttpStatus.OK).body(role);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while fetching the role: " + e.getMessage());
		}
	}

}