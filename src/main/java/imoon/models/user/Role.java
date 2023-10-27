package imoon.models.user;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Role {

    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(255) default ''", nullable = false)
    private String name;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "role_user_group", joinColumns = @JoinColumn(name = "role_id"))
    @Column(name = "user_group")
    private Set<UserGroup> userGroups = new HashSet<>();

    @OneToMany(mappedBy = "role")
    private Set<UserRole> userRoles = new HashSet<>();

    public Set<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void addUserGroup(UserGroup userGroup) {
        userGroups.add(userGroup);
    }

    public void removeUserGroup(UserGroup userGroup) {
        userGroups.remove(userGroup);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void addUserRole(UserRole userRole) {
        userRoles.add(userRole);
        userRole.setRole(this);
    }

    public void removeUserRole(UserRole userRole) {
        userRoles.remove(userRole);
        userRole.setRole(null);
    }
}

