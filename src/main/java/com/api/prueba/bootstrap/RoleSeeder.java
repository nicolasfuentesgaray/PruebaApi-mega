package com.api.prueba.bootstrap;

import com.api.prueba.entities.Rol;
import com.api.prueba.entities.RolEnum;
import com.api.prueba.repositories.RolRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RolRepository rolRepository;


    public RoleSeeder(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.cargaRoles();
    }

    private void cargaRoles() {
        RolEnum[] nombreRoles = new RolEnum[] { RolEnum.USER, RolEnum.ADMIN, RolEnum.SUPER_ADMIN };
        Map<RolEnum, String> rolDescripcionMap = Map.of(
            RolEnum.USER, "Rol por defecto",
            RolEnum.ADMIN, "Administrador",
            RolEnum.SUPER_ADMIN, "Super Usuario"
        );

        Arrays.stream(nombreRoles).forEach((rolNombre) -> {
            Optional<Rol> optionalRol = rolRepository.findByNombre(rolNombre);

            optionalRol.ifPresentOrElse(System.out::println, () -> {
                Rol roleToCreate = new Rol();

                roleToCreate.setNombre(rolNombre)
                        .setDescripcion(rolDescripcionMap.get(rolNombre));

                rolRepository.save(roleToCreate);
            });
        });
    }
}
