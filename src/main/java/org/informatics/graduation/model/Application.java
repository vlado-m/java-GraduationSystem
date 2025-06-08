package org.informatics.graduation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Fill out a topic.")
    @Size(max = 200, message = "Topic can't exceed 200 characters.")
    private String topic;

    @NotBlank(message = "Fill out an objective.")
    @Size(max = 2000)
    private String objective;

    @NotBlank(message = "Fill out a task description.")
    @Column(length = 2000)
    private String tasks;

    @NotBlank(message = "Fill out technologies.")
    @Column(length = 1000)
    private String technologies;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Student student;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Teacher supervisor;
    private boolean approved = false;
    private LocalDate approvedOn;
    private LocalDate submittedOn;

    public Application(){}

    public Long getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public String getObjective() {
        return objective;
    }

    public String getTasks() {
        return tasks;
    }

    public String getTechnologies() {
        return technologies;
    }

    public Student getStudent() {
        return student;
    }

    public Teacher getSupervisor() {
        return supervisor;
    }

    public boolean isApproved() {
        return approved;
    }

    public LocalDate getApprovedOn() {
        return approvedOn;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public void setTasks(String tasks) {
        this.tasks = tasks;
    }

    public void setTechnologies(String technologies) {
        this.technologies = technologies;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setSupervisor(Teacher supervisor) {
        this.supervisor = supervisor;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
        // Update approval date if we set something to approved
        if ( approved && this.approvedOn == null ){
            this.approvedOn = LocalDate.now();
        }
    }

    public void setApprovedOn(LocalDate approvedOn) {
        this.approvedOn = approvedOn;
    }

    public LocalDate getSubmittedOn() {
        return submittedOn;
    }

    public void setSubmittedOn(LocalDate submittedOn) {
        this.submittedOn = submittedOn;
    }

}
