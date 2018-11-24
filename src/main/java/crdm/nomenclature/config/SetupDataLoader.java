package crdm.nomenclature.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import crdm.nomenclature.entity.Privilege;
import crdm.nomenclature.entity.Role;
import crdm.nomenclature.entity.User;
import crdm.nomenclature.service.PrivilegeService;
import crdm.nomenclature.service.RoleService;
import crdm.nomenclature.service.UserService;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PrivilegeService privilegeService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (alreadySetup) {
			return;
		}

		final Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
		final Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

		final List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
		createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
		createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

		Role adminRole = roleService.findByName("ROLE_ADMIN");
		User user = userService.findByEmail("admin@admin.com");
		if(user == null) {
			user = new User();
			user.setFirstName("admin");
			user.setLastName("admin");
			user.setPassword(passwordEncoder.encode("nomenCRDM"));
			user.setEmail("admin@admin.com");
			user.setEnabled(true);
		}

		user.setRoles(Arrays.asList(adminRole));
		userService.save(user);
		
		alreadySetup = true;
	}

	@Transactional
	private Privilege createPrivilegeIfNotFound(String name) {

		Privilege privilege = privilegeService.findByName(name);
		if (privilege == null) {
			privilege = new Privilege(name);
			privilegeService.save(privilege);
		}
		return privilege;
	}

	@Transactional
	private Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {

		Role role = roleService.findByName(name);
		if (role == null) {
			role = new Role(name);
			role.setPrivileges(privileges);
			roleService.save(role);
		}
		return role;
	}
}