package com.resumeai.backend.service;

import com.resumeai.backend.model.AnalysisRequest;
import com.resumeai.backend.model.AnalysisResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AiAnalysisService {

    private static final Logger logger = LoggerFactory.getLogger(AiAnalysisService.class);

    public AnalysisResponse analyzeResume(AnalysisRequest request) {
        logger.info("Analyzing resume with AI service");

        // For now, return mock data. Replace with actual AI integration
        return getMockAnalysis(request);
    }

    private AnalysisResponse getMockAnalysis(AnalysisRequest request) {
        // Mock response for development/testing
        String summary = "Your resume demonstrates solid technical skills and relevant experience. " +
                "The structure is clear and professional, with good use of action verbs.";

        List<String> strengths = Arrays.asList(
            "Strong technical background with relevant programming languages",
            "Clear career progression with increasing responsibilities",
            "Good use of quantifiable achievements",
            "Professional formatting and consistent styling"
        );

        List<String> improvements = Arrays.asList(
            "Add more specific metrics to quantify your achievements",
            "Tailor your resume more closely to the target job description",
            "Include relevant certifications or recent training",
            "Consider adding a professional summary at the top"
        );

        return new AnalysisResponse(summary, improvements, strengths);
    }
}