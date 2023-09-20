package imoon.models.place;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class SpecialSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private MoonPlace place;

    @Column(columnDefinition = "Date")
    private LocalDate date;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalTime openTime) {
        this.openTime = openTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
    }

    public LocalTime getBreakStartTime() {
        return breakStartTime;
    }

    public void setBreakStartTime(LocalTime breakStartTime) {
        this.breakStartTime = breakStartTime;
    }

    public LocalTime getBreakEndTime() {
        return breakEndTime;
    }

    public void setBreakEndTime(LocalTime breakEndTime) {
        this.breakEndTime = breakEndTime;
    }

    public boolean getIsWork() {
        return isWork;
    }

    public void setIsWork(boolean work) {
        isWork = work;
    }
}
