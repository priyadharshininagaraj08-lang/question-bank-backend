package com.example.SpringBoot.controller;

import com.example.SpringBoot.model.QuestionPaper;
import com.example.SpringBoot.service.QuestionPaperService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/papers")
@CrossOrigin(origins = "http://localhost:5173")
public class QuestionPaperController {

    private final QuestionPaperService service;

    public QuestionPaperController(QuestionPaperService service) {
        this.service = service;
    }

    // 🔹 Get all papers
    @GetMapping("/{department}/{studyYear}/{academicYear}")
    public List<QuestionPaper> getByYear(
            @PathVariable String department,
            @PathVariable int studyYear,
            @PathVariable int academicYear,
            @RequestParam(required = false) Integer semester) {

        // ✅ If semester selected
        if (semester != null) {
            return service.getByDepartmentStudyYearYearAndSemester(
                    department,
                    studyYear,
                    academicYear,
                    semester
            );
        }

        // ✅ If semester not selected
        return service.getByDepartmentAndStudyYearAndAcademicYear(
                department,
                studyYear,
                academicYear
        );
    }
}