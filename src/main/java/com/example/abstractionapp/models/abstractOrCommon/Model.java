package com.example.abstractionapp.models.abstractOrCommon;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@ToString(callSuper = true)
public class Model extends Audit<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String uuid;

    private long createdBy;

    private boolean deleted;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deletedAt;

    @PrePersist
    public void onPrePersist() {

        this.createdAt = this.updatedAt = new Date();
        this.deleted = false;

        if (StringUtils.isBlank(this.uuid)) {
            this.uuid = UUID.randomUUID().toString();
        }

    }//onPrePersist

    @PreUpdate
    public void onPreUpdate() {

        this.updatedAt = new Date();

    }//onPreUpdate
}
