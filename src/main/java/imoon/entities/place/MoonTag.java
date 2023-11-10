package imoon.entities.place;

import imoon.entities.common.Tag;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MoonTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Tag parent;

    @ManyToOne
    private MoonPlace place;

    @ManyToOne
    private MoonEvent event;

    @Column(columnDefinition = "BOOLEAN default False")
    private Boolean main = false;

    @Column(columnDefinition = "BOOLEAN default True")
    private Boolean showIcon = true;

}
