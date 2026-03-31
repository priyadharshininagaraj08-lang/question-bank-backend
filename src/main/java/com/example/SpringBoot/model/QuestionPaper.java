package com.example.SpringBoot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "question_papers")
public class QuestionPaper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Department (BSC_CS, BCA, IT)
    @Column(nullable = false)
    private String department;

    // I Year = 1, II Year = 2, III Year = 3
    @Column(nullable = false)
    private int studyYear;

    // 2023, 2024, 2025
    @Column(nullable = false)
    private int academicYear;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private int semester;

    @Column(nullable = false)
    private String fileUrl;

    public QuestionPaper() {}

    public QuestionPaper(String department,int studyYear,int academicYear,String subject,int semester,String fileUrl) {
        this.department = department;
        this.studyYear = studyYear;
        this.academicYear = academicYear;
        this.subject = subject;
        this.semester = semester;
        this.fileUrl = fileUrl;
    }

    public Long getId() {
        return id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }

    public int getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(int academicYear) {
        this.academicYear = academicYear;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}