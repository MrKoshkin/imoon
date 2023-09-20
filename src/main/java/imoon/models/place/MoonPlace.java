package imoon.models.place;

import imoon.models.common.Contact;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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

    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }

    public void setSpecialSchedule(List<SpecialSchedule> specialSchedule) {
        this.specialSchedule = specialSchedule;
    }

    public List<MoonEvent> getEvents() {
        return events;
    }

    public void setEvents(List<MoonEvent> events) {
        this.events = events;
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


    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setImages(List<MoonImage> images) {
        this.images = images;
    }

    public void setTags(List<MoonTag> tags) {
        this.tags = tags;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public PlaceCategory getCategory() {
        return category;
    }

    public void setCategory(PlaceCategory category) {
        this.category = category;
    }

    public Boolean getMorning() {
        return morning;
    }

    public void setMorning(Boolean morning) {
        this.morning = morning;
    }

    public Boolean getDay() {
        return day;
    }

    public void setDay(Boolean day) {
        this.day = day;
    }

    public Boolean getEvening() {
        return evening;
    }

    public void setEvening(Boolean evening) {
        this.evening = evening;
    }

    public Boolean getNight() {
        return night;
    }

    public void setNight(Boolean night) {
        this.night = night;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
