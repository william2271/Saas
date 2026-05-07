package com.resumeai.backend.model;

import java.util.List;

public class AnalysisResponse {

    private String summary;
    private List<String> improvements;
    private List<String> strengths;

    public AnalysisResponse() {}

    public AnalysisResponse(String summary, List<String> improvements, List<String> strengths) {
        this.summary = summary;
        this.improvements = improvements;
        this.strengths = strengths;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<String> getImprovements() {
        return improvements;
    }

    public void setImprovements(List<String> improvements) {
        this.improvements = improvements;
    }

    public List<String> getStrengths() {
        return strengths;
    }

    public void setStrengths(List<String> strengths) {
        this.strengths = strengths;
    }
}