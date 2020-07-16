package com.mhts.hibernate.one_to_one;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_wife")
public class Wife {

    @Id
    @GenericGenerator(name="myuuid",strategy="uuid")
    @GeneratedValue(generator="myuuid")
    private String id;
    private String name;

    @OneToOne
    @PrimaryKeyJoinColumn
    @Cascade(CascadeType.SAVE_UPDATE)
    private Husband husband;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Husband getHusband() {
        return husband;
    }

    public void setHusband(Husband husband) {
        this.husband = husband;
    }

}