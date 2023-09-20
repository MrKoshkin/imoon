package imoon.models.place;

import imoon.models.common.Tag;
import jakarta.persistence.*;

@Entity
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tag getParent() {
        return parent;
    }

    public void setParent(Tag parent) {
        this.parent = parent;
    }

    public MoonPlace getPlace() {
        return place;
    }

    public void setPlace(MoonPlace place) {
        this.place = place;
    }

    public MoonEvent getEvent() {
        return event;
    }

    public void setEvent(MoonEvent event) {
        this.event = event;
    }

    public Boolean getMain() {
        return main;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }

    public Boolean getShowIcon() {
        return showIcon;
    }

    public void setShowIcon(Boolean showIcon) {
        this.showIcon = showIcon;
    }
}
