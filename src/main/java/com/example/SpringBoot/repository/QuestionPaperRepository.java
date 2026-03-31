package com.example.SpringBoot.repository;

import com.example.SpringBoot.model.QuestionPaper;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuestionPaperRepository extends JpaRepository<QuestionPaper, Long> {

    // 🔹 Get papers by Department + Study Year + Academic Year (NO semester)
    List<QuestionPaper> findByDepartmentAndStudyYearAndAcademicYear(
            String department,
            int studyYear,
            int academicYear
    );

    // 🔹 Get papers by Department + Study Year + Academic Year + Semester
    List<QuestionPaper> findByDepartmentAndStudyYearAndAcademicYearAndSemester(
            String department,
            int studyYear,
            int academicYear,
            int semester
    );

    // 🔹 Get papers by Department + Study Year
    List<QuestionPaper> findByDepartmentAndStudyYear(
            String department,
            int studyYear
    );

    // 🔹 Get papers by Department only
    List<QuestionPaper> findByDepartment(String department);
}