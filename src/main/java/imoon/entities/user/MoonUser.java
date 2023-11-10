package imoon.entities.user;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Data
public class MoonUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(30)", nullable = false)
    private String username;

    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    private String password;

    @Column(columnDefinition = "VARCHAR(255) default 'email'")
    private String email;

    @Column(columnDefinition = "VARCHAR(30)")
    private String telegramCode;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<UserGroup> userGroups = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<UserRole> userRoles = new HashSet<>();

    @Column(columnDefinition = "VARCHAR(50)")
    private String role;

    @Column(columnDefinition = "BOOLEAN default True")
    private Boolean hidden = true;

    private Long timestamp;

    public void setUserGroups(Set<UserGroup> userGroups) {
        this.userGroups = Optional.ofNullable(userGroups).orElse(new HashSet<>());
    }

    public void addUserGroups(UserGroup... values) {
        userGroups.addAll(Arrays.asList(values));
    }

    public Set<UserGroup> getUserGroups() {
        if (userGroups == null) {
            userGroups = new HashSet<>();
        }
        return userGroups;
    }

    public void resetUserGroups(Set<UserGroup> userGroups) {
        getUserGroups().clear();
        for (UserGroup userGroup : userGroups) {
            getUserGroups().add(userGroup);
        }
    }

    public void addRole(Role role) {
        UserRole userRole = new UserRole();
        userRole.setUser(this);  // Устанавливаем текущего пользователя
        userRole.setRole(role);  // Устанавливаем роль
        userRoles.add(userRole);  // Добавляем в коллекцию userRoles
        role.getUserRoles().add(userRole);  // Добавляем в коллекцию роли
    }

    public void removeUserRole(UserRole userRole) {
        userRoles.remove(userRole);
        userRole.setUser(null);
    }

    public void removeUserRole(Role role) {
        // Находим связь UserRole, которую нужно удалить
        UserRole userRoleToRemove = userRoles.stream()
                .filter(userRole -> userRole.getRole().equals(role))
                .findFirst()
                .orElse(null);

        if (userRoleToRemove != null) {
            userRoles.remove(userRoleToRemove);
            userRoleToRemove.setRole(null);
        }
    }

    public Set<Role> getRoles() {
        return userRoles.stream().map(UserRole::getRole).collect(Collectors.toSet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoonUser moonUser = (MoonUser) o;
        return Objects.equals(username, moonUser.username) && Objects.equals(password, moonUser.password) && Objects.equals(email, moonUser.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, telegramCode);
    }
}
