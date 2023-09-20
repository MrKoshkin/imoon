package imoon.models.place;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MoonEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String title;

    @Column(columnDefinition = "VARCHAR(500)")
    private String shortDescription;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TIME default '00:00'")
    private LocalTime startTime;

    @Column(columnDefinition = "TIME default '00:00'")
    private LocalTime endTime;

    @Column(columnDefinition = "Date")
    private LocalDate startDate;

    @Column(columnDefinition = "Date")
    private LocalDate endDate;

    @Column(columnDefinition = "VARCHAR(500) default '/public/img/cover_default.png'")
    private String cover;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("sortOrder ASC")
    private List<MoonImage> images;

    @ManyToOne
    private MoonPlace place;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MoonTag> tags;

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

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public List<MoonImage> getImages() {
        if (images == null) {
            images = new ArrayList<>();
        }
        return images;
    }

    public void setImages(List<MoonImage> images) {
        this.images = images;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public MoonPlace getPlace() {
        return place;
    }

    public void setPlace(MoonPlace place) {
        this.place = place;
    }

    public List<MoonTag> getTags() {
        return tags;
    }

    public void setTags(List<MoonTag> tags) {
        this.tags = tags;
    }
}

