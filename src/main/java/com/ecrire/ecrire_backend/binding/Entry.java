package com.ecrire.ecrire_backend.binding;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
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

//    @ManyToMany
//    @JoinColumn(name = "user.userId")
//    private User user;


    private String title;
    private String entry;
    private String mood;
    private Date insertDate;
    private Date updatedDate;

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
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

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "eid=" + eid +
                ", title='" + title + '\'' +
                ", entry='" + entry + '\'' +
                ", mood='" + mood + '\'' +
                ", insertDate=" + insertDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
