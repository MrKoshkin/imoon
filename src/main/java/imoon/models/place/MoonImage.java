package imoon.models.place;

import jakarta.persistence.*;


@Entity
public class MoonImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private MoonPlace place;

    @ManyToOne(fetch = FetchType.LAZY)
    private MoonEvent event;

    @Column(columnDefinition = "VARCHAR(500) default ''")
    private String path;

    @Column(columnDefinition = "VARCHAR(500) default ''")
    private String originalPath;

    @Column(columnDefinition = "VARCHAR(500) default ''")
    private String previewPath;

    @Column(columnDefinition = "NUMERIC(5) default 99999")
    private Integer sortOrder = 99999;

    @Column(columnDefinition = "VARCHAR(255) default ''")
    private String title;

    @Column(columnDefinition = "VARCHAR(255) default ''")
    private String metaDescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getOriginalPath() {
        return originalPath;
    }

    public void setOriginalPath(String originalPath) {
        this.originalPath = originalPath;
    }

    public String getPreviewPath() {
        return previewPath;
    }

    public void setPreviewPath(String previewPath) {
        this.previewPath = previewPath;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
}
