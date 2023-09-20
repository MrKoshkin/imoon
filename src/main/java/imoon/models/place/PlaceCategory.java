package imoon.models.place;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class PlaceCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PlaceCategory parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlaceCategory> categories;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MoonPlace> places;

    @Column(columnDefinition = "VARCHAR(255) default 'Category name'", nullable = false)
    private String name;

    @Column(columnDefinition = "VARCHAR(255) default 'fa-solid fa-martini-glass'", nullable = false)
    private String icon;

    @Column(columnDefinition = "VARCHAR(500)")
    private String description;

    @CreationTimestamp
    private Long timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlaceCategory getParent() {
        return parent;
    }

    public void setParent(PlaceCategory parent) {
        this.parent = parent;
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

    public List<PlaceCategory> getCategories() {
        if (categories == null) {
            categories = new ArrayList<>();
        }
        return categories;
    }

    public void setCategories(List<PlaceCategory> categories) {
        this.categories = categories;
    }

    public List<MoonPlace> getPlaces() {
        if (places == null) {
            places = new ArrayList<>();
        }
        return places;
    }

    public void setPlaces(List<MoonPlace> places) {
        this.places = places;
    }
}

