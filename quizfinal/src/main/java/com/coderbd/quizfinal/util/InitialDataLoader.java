package com.coderbd.quizfinal.util;

import com.coderbd.quizfinal.entity.Privilege;
import com.coderbd.quizfinal.entity.Role;
import com.coderbd.quizfinal.entity.User;
import com.coderbd.quizfinal.repo.PrivilegeRepo;
import com.coderbd.quizfinal.repo.RoleRepo;
import com.coderbd.quizfinal.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class InitialDataLoader {/*implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private RoleRepo roleRepository;

    @Autowired
    private PrivilegeRepo privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        Privilege readPrivilege
                = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

        Optional<Role> adminRole = roleRepository.findByName("ROLE_ADMIN");
        Role role = new Role(adminRole.get().getName());
        User user = new User();
        user.setUsername("admin");
        user.setFirstName("Mr.");
        user.setLastName("Admin");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setEmail("test@test.com");
        user.setRoles(Arrays.asList(role));
        user.setEnabled(true);
        userRepository.save(user);

        alreadySetup = true;
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {

        Optional<Role> role = roleRepository.findByName(name);
        Role r=new Role(role.get().getName());
        if (r == null) {
            r = new Role(name);
            r.setPrivileges(privileges);
            roleRepository.save(r);
        }
        return r;
    }*/
}