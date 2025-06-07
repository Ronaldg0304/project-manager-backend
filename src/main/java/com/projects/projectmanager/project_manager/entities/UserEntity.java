package com.projects.projectmanager.project_manager.entities;

import com.fasterxml.jackson.annotation.*;
import com.projects.projectmanager.project_manager.entities.audit.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "users")
public class UserEntity extends Auditable implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "document_id", unique = true, nullable = false, length = 20)
    private String documentId;

    @Column(nullable = false, length = 20)
    private String firstName;

    @Column(nullable = false, length = 20)
    private String secondName;

    @Column(nullable = false, length = 20)
    private String firstLastName;

    @Column(nullable = false, length = 20)
    private String secondLastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "company_id")

    private Company company;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Project> projects;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Report> reports;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Role.class)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    // Retorna la contraseña desde tu entidad
    @Override
    public String getPassword() {
        return password;
    }

    // Retorna el nombre de usuario desde tu entidad
    @Override
    public String getUsername() {
        return username;
    }

    // Otros métodos de control de acceso
    @Override
    public boolean isAccountNonExpired() {
        return true; // Aquí puedes usar una propiedad como user.isAccountExpired()
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Similar, podrías usar user.isLocked()
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true; // O por ejemplo: return user.isActive()
    }

    // Autoridades (roles o permisos)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (roles == null) {
            System.out.println("Roles vacios");
            return Collections.emptyList();
        }

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().name())) // ← Aquí usamos el Enum
                .collect(Collectors.toSet());
    }

    // También puedes exponer el usuario completo si lo necesitas
    public UserEntity getUserEntity() {
        return getUserEntity();
    }
}
