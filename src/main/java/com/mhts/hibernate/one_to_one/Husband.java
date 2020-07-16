package com.mhts.hibernate.one_to_one;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="t_husband")
public class Husband {

    @Id
    @GenericGenerator(name="myForeignKey",strategy="foreign",parameters={
    		@Parameter(name="property",value="wife")
    })// 自定义主键生成策略，让t_husband表的主键去参考t_wife表的主键
    @GeneratedValue(generator="myForeignKey")
    private String id;
    
    private String name;

    @OneToOne(mappedBy="husband") // 由t_wife表来维护外键
    @PrimaryKeyJoinColumn
    private Wife wife;

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

    public Wife getWife() {
        return wife;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }

}