package com.example.SpringBoot.service;

import com.example.SpringBoot.model.QuestionPaper;
import com.example.SpringBoot.repository.QuestionPaperRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionPaperService {

    private final QuestionPaperRepository repository;

    public QuestionPaperService(QuestionPaperRepository repository) {
        this.repository = repository;
    }

    // ✅ Get all papers
    public List<QuestionPaper> getAllPapers() {
        return repository.findAll();
    }

    // ✅ Delete paper
    public void deletePaper(Long id) {
        QuestionPaper paper = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paper not found with ID: " + id));

        repository.delete(paper);
    }

    // ✅ Get by Department + Study Year + Academic Year (NO semester)
    public List<QuestionPaper> getByDepartmentAndStudyYearAndAcademicYear(
            String department,
            int studyYear,
            int academicYear) {

        return repository.findByDepartmentAndStudyYearAndAcademicYear(
                department,
                studyYear,
                academicYear
        );
    }

    // ✅ Get by Department + Study Year + Academic Year + Semester
    public List<QuestionPaper> getByDepartmentStudyYearYearAndSemester(
            String department,
            int studyYear,
            int academicYear,
            int semester) {

        return repository.findByDepartmentAndStudyYearAndAcademicYearAndSemester(
                department,
                studyYear,
                academicYear,
                semester
        );
    }

    // ✅ Save with duplicate check
    public QuestionPaper saveIfNotExists(QuestionPaper paper) {

        List<QuestionPaper> existingPapers =
                repository.findByDepartmentAndStudyYearAndAcademicYearAndSemester(
                        paper.getDepartment(),
                        paper.getStudyYear(),
                        paper.getAcademicYear(),
                        paper.getSemester()
                );

        boolean exists = existingPapers.stream()
                .anyMatch(p ->
                        p.getSubject().equalsIgnoreCase(paper.getSubject())
                );

        if (exists) {
            throw new RuntimeException(
                    "Paper already exists for this Department, Study Year, Academic Year, Subject and Semester!"
            );
        }

        return repository.save(paper);
    }
}