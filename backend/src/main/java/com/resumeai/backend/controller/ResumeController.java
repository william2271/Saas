package com.resumeai.backend.controller;

import com.resumeai.backend.model.AnalysisRequest;
import com.resumeai.backend.model.AnalysisResponse;
import com.resumeai.backend.service.AiAnalysisService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Configure appropriately for production
public class ResumeController {

    private static final Logger logger = LoggerFactory.getLogger(ResumeController.class);

    @Autowired
    private AiAnalysisService aiAnalysisService;

    @PostMapping("/analyze")
    public ResponseEntity<AnalysisResponse> analyzeResume(@Valid @RequestBody AnalysisRequest request) {
        logger.info("Received resume analysis request");

        try {
            AnalysisResponse response = aiAnalysisService.analyzeResume(request);
            logger.info("Resume analysis completed successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error analyzing resume", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}