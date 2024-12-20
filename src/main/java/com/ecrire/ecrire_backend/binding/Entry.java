package com.ecrire.ecrire_backend.binding;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eid;
    @ManyToOne
    @JoinColumn
    @Nonnull
    private User user;

    private String title;
    private String entry;
    private String mood;
    private Date insertdate;
    private Date updateddate;

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    @Nonnull
    public User getUser() {
        return user;
    }

    public void setUser(@Nonnull User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public Date getInsertdate() {
        return insertdate;
    }

    public void setInsertdate(Date insertdate) {
        this.insertdate = insertdate;
    }

    public Date getUpdateddate() {
        return updateddate;
    }

    public void setUpdateddate(Date updateddate) {
        this.updateddate = updateddate;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "eid=" + eid +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", entry='" + entry + '\'' +
                ", mood='" + mood + '\'' +
                ", insertDate=" + insertdate +
                ", updatedDate=" + updateddate +
                '}';
    }
}
