package com.resumeai.backend.service;

import com.resumeai.backend.model.AnalysisRequest;
import com.resumeai.backend.model.AnalysisResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Service
public class AiAnalysisService {

    private static final Logger logger = LoggerFactory.getLogger(AiAnalysisService.class);

    private final WebClient webClient;

    @Value("${ai.api.key:}")
    private String apiKey;

    @Value("${ai.api.url:}")
    private String apiUrl;

    @Value("${ai.model:claude-3-sonnet-20240229}")
    private String model;

    public AiAnalysisService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public AnalysisResponse analyzeResume(AnalysisRequest request) {
        logger.info("Analyzing resume with AI service");

        // For now, return mock data. Replace with actual AI integration
        if (apiKey == null || apiKey.isEmpty()) {
            logger.warn("No AI API key configured, returning mock response");
            return getMockAnalysis(request);
        }

        // TODO: Implement actual AI integration with Claude or ChatGPT
        // This would involve:
        // 1. Constructing the prompt with resume and job description
        // 2. Making HTTP request to AI API
        // 3. Parsing the response
        // 4. Returning structured analysis

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

    // TODO: Implement actual AI integration methods
    private Mono<String> callClaudeApi(String prompt) {
        // Implementation for Claude API
        return webClient.post()
                .uri(apiUrl)
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(createClaudeRequest(prompt))
                .retrieve()
                .bodyToMono(String.class);
    }

    private Mono<String> callChatGptApi(String prompt) {
        // Implementation for ChatGPT API
        return webClient.post()
                .uri(apiUrl)
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(createChatGptRequest(prompt))
                .retrieve()
                .bodyToMono(String.class);
    }

    private String createClaudeRequest(String prompt) {
        // Create Claude API request body
        return String.format("""
            {
                "model": "%s",
                "max_tokens": 2000,
                "messages": [
                    {
                        "role": "user",
                        "content": "%s"
                    }
                ]
            }
            """, model, prompt.replace("\"", "\\\""));
    }

    private String createChatGptRequest(String prompt) {
        // Create ChatGPT API request body
        return String.format("""
            {
                "model": "%s",
                "messages": [
                    {
                        "role": "user",
                        "content": "%s"
                    }
                ],
                "max_tokens": 2000
            }
            """, model, prompt.replace("\"", "\\\""));
    }
}