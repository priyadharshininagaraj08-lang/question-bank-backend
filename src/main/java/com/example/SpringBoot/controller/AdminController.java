package com.example.SpringBoot.controller;

import com.example.SpringBoot.model.QuestionPaper;
import com.example.SpringBoot.service.QuestionPaperService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    private final QuestionPaperService service;

    public AdminController(QuestionPaperService service) {
        this.service = service;
    }

    // 🔹 Upload PDF
    @PostMapping("/upload")
    public ResponseEntity<?> uploadPaper(
            @RequestParam("department") String department,
            @RequestParam("studyYear") int studyYear,
            @RequestParam("academicYear") int academicYear,
            @RequestParam("subject") String subject,
            @RequestParam("semester") int semester,
            @RequestParam("file") MultipartFile file) {

        try {

            // ✅ Validate file
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Please upload a PDF file");
            }

            if (!file.getContentType().equals("application/pdf")) {
                return ResponseEntity.badRequest().body("Only PDF files are allowed");
            }

            // 🔹 Decide folder based on study year
            String yearFolder = "";

            if (studyYear == 1) yearFolder = "I_year";
            else if (studyYear == 2) yearFolder = "II_year";
            else if (studyYear == 3) yearFolder = "III_year";

            // 🔹 Folder structure
            String uploadDir = "uploads/" + department + "/" + yearFolder;

            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 🔹 Unique filename
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Path filePath = Paths.get(uploadDir, fileName);

            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // 🔹 Save file path to DB
            String fileUrl = department + "/" + yearFolder + "/" + fileName;

            QuestionPaper paper = new QuestionPaper(
                    department,
                    studyYear,
                    academicYear,
                    subject,
                    semester,
                    fileUrl
            );

            QuestionPaper saved = service.saveIfNotExists(paper);

            return ResponseEntity.ok(saved);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 🔹 Delete paper
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePaper(@PathVariable Long id) {
        service.deletePaper(id);
        return ResponseEntity.ok("Paper deleted successfully");
    }
}