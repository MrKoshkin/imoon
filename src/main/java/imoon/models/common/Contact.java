package imoon.models.common;

import imoon.models.place.MoonPlace;
import jakarta.persistence.*;

@Entity
public class Contact {

    public enum Type {
        PHONE("", ""), INSTAGRAM("", ""), TELEGRAM("", ""), WHATSAPP("", ""), VIBER("", ""), EMAIL("", ""), OTHER("", "");
        private String label;
        private String icon;

        Type(String label, String icon) {
            this.label = label;
            this.icon = icon;
        }

        public String getLabel() {
            return label;
        }

        public String getIcon() {
            return icon;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private MoonPlace place;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(8)")
    private Type type = Type.PHONE;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MoonPlace getPlace() {
        return place;
    }

    public void setPlace(MoonPlace place) {
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
