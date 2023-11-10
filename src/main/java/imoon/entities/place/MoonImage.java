package imoon.entities.place;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
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

}
