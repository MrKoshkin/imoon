package imoon.models.user;


import jakarta.persistence.*;

@Entity
public class UserRole {

    public static final String ADMIN = "ADMIN";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private MoonUser user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public MoonUser getUser() {
        return user;
    }

    public void setUser(MoonUser user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @PreRemove
    public void preRemove() {
        if (user != null) {
            user.removeUserRole(this);
        }

        if (role != null) {
            role.removeUserRole(this);
        }
    }
}

