package imoon.models.common;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag {

    public enum Type {
        ONLY_TEXT("Только текст"), ONLY_ICON("Только иконка"), ICON_TEXT("Иконка и текст");
        private String label;

        Type(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Tag parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Tag> tags;

    @Column(columnDefinition = "VARCHAR(50) default 'Category name'", nullable = false)
    private String name;

    @Column(columnDefinition = "VARCHAR(6)")
    private String color = "FFFFFF";

    @Column(columnDefinition = "VARCHAR(6)")
    private String iconColor = "000000";

    @Column(columnDefinition = "VARCHAR(255) default 'fa-solid fa-martini-glass'", nullable = false)
    private String icon;

    @Column(columnDefinition = "VARCHAR(255)")
    private String description;

    @Column(columnDefinition = "VARCHAR(255)")
    private String image;

    @Column(columnDefinition = "VARCHAR(10)")
    @Enumerated(EnumType.STRING)
    private Type type = Type.ONLY_TEXT;

    public Tag getParent() {
        return parent;
    }

    public void setParent(Tag parent) {
        this.parent = parent;
    }

    public List<Tag> getTags() {
        if (tags == null) {
            tags = new ArrayList<>();
        }
        return tags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIconColor() {
        return iconColor;
    }

    public void setIconColor(String iconColor) {
        this.iconColor = iconColor;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
