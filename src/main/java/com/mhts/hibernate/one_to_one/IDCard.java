package com.mhts.hibernate.one_to_one;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_id_card")
public class IDCard {

    @Id
    @GenericGenerator(strategy="uuid",name="myuuid")
    @GeneratedValue(generator="myuuid")
    private String id;
    
    private String cardNum;

    @OneToOne(targetEntity=User.class)
    @JoinColumn(name="user_id")
    @Cascade(CascadeType.SAVE_UPDATE)
    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
