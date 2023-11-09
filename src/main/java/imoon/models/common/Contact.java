package imoon.models.common;

import imoon.models.place.MoonPlace;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
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

}
