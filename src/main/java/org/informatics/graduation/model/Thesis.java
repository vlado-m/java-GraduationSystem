package org.informatics.graduation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
public class Thesis {
    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @NotBlank(message = "Fill out a title.")
    @Size(max = 200)
    private String title;

    @NotBlank(message = "Fill out the text.")
    @Column(columnDefinition = "TEXT")
    private String text;

    @PastOrPresent
    private LocalDate uploadDate = LocalDate.now();

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Application application;

    private String fileName;

    public Thesis() {}
}
