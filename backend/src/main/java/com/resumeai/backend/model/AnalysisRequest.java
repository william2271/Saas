package com.resumeai.backend.model;

import jakarta.validation.constraints.NotBlank;

public class AnalysisRequest {

    @NotBlank(message = "Resume text is required")
    private String resume;

    private String jobDescription;

    public AnalysisRequest() {}

    public AnalysisRequest(String resume, String jobDescription) {
        this.resume = resume;
        this.jobDescription = jobDescription;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
}