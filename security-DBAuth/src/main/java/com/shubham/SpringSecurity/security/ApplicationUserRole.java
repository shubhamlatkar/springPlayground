package com.shubham.SpringSecurity.security;

import static com.shubham.SpringSecurity.security.ApplicationUserPermission.COURSE_READ;
import static com.shubham.SpringSecurity.security.ApplicationUserPermission.COURSE_WRITE;
import static com.shubham.SpringSecurity.security.ApplicationUserPermission.STUDENT_READ;
import static com.shubham.SpringSecurity.security.ApplicationUserPermission.STUDENT_WRITE;

import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()), ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE)),
    ADMINTRAINEE(Sets.newHashSet(COURSE_READ, STUDENT_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }

}