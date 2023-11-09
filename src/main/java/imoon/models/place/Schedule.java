package imoon.models.place;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Entity
@Data
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private MoonPlace place;

    @Column(columnDefinition = "SMALLINT", nullable = false)
    private short dayOfWeek;

    @Column(columnDefinition = "TIME default '00:00'")
    private LocalTime openTime;

    @Column(columnDefinition = "TIME default '00:00'")
    private LocalTime closeTime;

    @Column(columnDefinition = "TIME default '00:00'")
    private LocalTime breakStartTime;

    @Column(columnDefinition = "TIME default '00:00'")
    private LocalTime breakEndTime;

    @Column(columnDefinition = "BOOLEAN default False")
    private boolean isWork;

}
