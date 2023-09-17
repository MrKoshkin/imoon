package imoon.models.user;

import jakarta.persistence.*;

import java.util.*;
import java.util.stream.Collectors;

@Entity
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelegramCode() {
        return telegramCode;
    }

    public void setTelegramCode(String telegramCode) {
        this.telegramCode = telegramCode;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoonUser moonUser = (MoonUser) o;
        return Objects.equals(username, moonUser.username) && Objects.equals(password, moonUser.password) && Objects.equals(email, moonUser.email) && Objects.equals(telegramCode, moonUser.telegramCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, telegramCode);
    }
}