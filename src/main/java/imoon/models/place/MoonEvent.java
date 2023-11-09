package imoon.models.place;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
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

    public List<MoonImage> getImages() {
        if (images == null) {
            images = new ArrayList<>();
        }
        return images;
    }

}

