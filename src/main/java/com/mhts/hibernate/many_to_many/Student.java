package com.mhts.hibernate.many_to_many;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="t_student")
public class Student {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany(targetEntity=Teacher.class)
    // @JoinTable��ʹ��@JoinTable�������м���������м���������Student��Teacher��ӳ���ϵ
    // joinColumns��������������Student���м���ӳ���ϵ
    // inverseJoinColumns��������������Teacher���м���ӳ���ϵ
    @JoinTable(name="t_student_teacher", joinColumns={@JoinColumn(name="student_id",referencedColumnName="id")}, inverseJoinColumns={@JoinColumn(name="teacher_id")})
    @Cascade(CascadeType.SAVE_UPDATE)
    private Set<Teacher> teachers = new HashSet<Teacher>();

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Set<Teacher> getTeachers() {
        return teachers;
    }
    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

}