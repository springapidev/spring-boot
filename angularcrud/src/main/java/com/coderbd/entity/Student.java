package com.coderbd.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "stu")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Enter Your Name")
    @Column(name = "student_name", nullable = false)
    private String studentName;
    @NotEmpty(message = "Enter Your Email")
    @Column(name = "email_address", nullable = false)
    private String emailAddress;
    @NotEmpty(message = "Enter Your Mobile")
    @Column(name = "mobile_no", nullable = false)
    private String mobileNo;

    private char gender;

    private String round;

    private String[] completedCourse;

    private String msg;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "regi_date")
    private Date regiDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String[] getCompletedCourse() {
        return completedCourse;
    }

    public void setCompletedCourse(String[] completedCourse) {
        this.completedCourse = completedCourse;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getRegiDate() {
        return regiDate;
    }

    public void setRegiDate(Date regiDate) {
        this.regiDate = regiDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return getGender() == student.getGender() &&
                Objects.equals(getId(), student.getId()) &&
                Objects.equals(getStudentName(), student.getStudentName()) &&
                Objects.equals(getEmailAddress(), student.getEmailAddress()) &&
                Objects.equals(getMobileNo(), student.getMobileNo()) &&
                Objects.equals(getRound(), student.getRound()) &&
                Arrays.equals(getCompletedCourse(), student.getCompletedCourse()) &&
                Objects.equals(getMsg(), student.getMsg()) &&
                Objects.equals(getRegiDate(), student.getRegiDate());
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(getId(), getStudentName(), getEmailAddress(), getMobileNo(), getGender(), getRound(), getMsg(), getRegiDate());
        result = 31 * result + Arrays.hashCode(getCompletedCourse());
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", mobileNo=" + mobileNo +
                ", gender=" + gender +
                ", round='" + round + '\'' +
                ", completedCourse=" + Arrays.toString(completedCourse) +
                ", msg='" + msg + '\'' +
                ", regiDate=" + regiDate +
                '}';
    }
}
