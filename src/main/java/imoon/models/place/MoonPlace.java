package imoon.models.place;

import imoon.models.common.Contact;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class MoonPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String title;

    @Column(columnDefinition = "VARCHAR(500)")
    private String shortDescription;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "DOUBLE PRECISION", nullable = false)
    private Double lat;

    @Column(columnDefinition = "DOUBLE PRECISION", nullable = false)
    private Double lng;

    @Column(columnDefinition = "NUMERIC(2) default 0")
    private Integer rating = 0;

    @Column(columnDefinition = "NUMERIC(5) default 0")
    private Integer followers = 0;

    @Column(columnDefinition = "VARCHAR(500)", nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private PlaceCategory category;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contact> contacts;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MoonTag> tags;

    @OrderBy("dayOfWeek ASC")
    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Schedule> schedule;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SpecialSchedule> specialSchedule;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MoonEvent> events;

    @Column(columnDefinition = "BOOLEAN default False")
    private Boolean morning = false;

    @Column(columnDefinition = "BOOLEAN default False")
    private Boolean day = false;

    @Column(columnDefinition = "BOOLEAN default False")
    private Boolean evening = false;

    @Column(columnDefinition = "BOOLEAN default False")
    private Boolean night = false;

    @Column(columnDefinition = "VARCHAR(500) default '/public/img/cover_default.png'")
    private String cover;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("sortOrder ASC")
    private List<MoonImage> images;

    @CreationTimestamp
    private Long timestamp;

    public List<Contact> getContacts() {
        if (contacts == null) {
            contacts = new ArrayList<>();
        }
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<MoonTag> getTags() {
        if (tags == null) {
            tags = new ArrayList<>();
        }
        return tags;
    }

    public List<MoonImage> getImages() {
        if (images == null) {
            images = new ArrayList<>();
        }
        return images;
    }

    public List<Schedule> getSchedule() {
        if (schedule == null) {
            schedule = new ArrayList<>();
        }
        return schedule;
    }

    public List<SpecialSchedule> getSpecialSchedule() {
        if (specialSchedule == null) {
            specialSchedule = new ArrayList<>();
        }
        return specialSchedule;
    }

    public Schedule getScheduleByIndex(int index) {
        if (schedule != null) {
            for (Schedule sc : schedule) {
                if (index == sc.getDayOfWeek()) {
                    return sc;
                }
            }
        }
        return null;
    }

    public String getNameOfDayOfWeek(int dayOfWeek) {
        if (schedule!=null) {
            switch (dayOfWeek) {
                case 1:
                    return "Понедельник";
                case 2:
                    return "Вторник";
                case 3:
                    return "Среда";
                case 4:
                    return "Четверг";
                case 5:
                    return "Пятница";
                case 6:
                    return "Суббота";
                case 7:
                    return "Воскресенье";
                default:
                    return "";
            }
        }
        return null;
    }

}
