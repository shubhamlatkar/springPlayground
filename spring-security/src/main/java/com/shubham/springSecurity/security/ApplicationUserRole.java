package com.shubham.springSecurity.security;

import java.util.Set;
import com.google.common.collect.Sets;
import static com.shubham.springSecurity.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(STUDENT_READ, STUDENT_WRITE, COURSE_READ, COURSE_WRITE));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions= permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    
}