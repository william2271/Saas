# Resume AI Backend

Spring Boot backend service for AI-powered resume analysis.

## Features

- REST API for resume analysis
- Integration with Claude or ChatGPT APIs
- Input validation
- CORS support
- Actuator endpoints for monitoring

## Prerequisites

- Java 17 or higher
- Maven 3.6+

## Configuration

Create environment variables or update `application.properties`:

```bash
# For Claude (Anthropic)
export AI_API_KEY=your_claude_api_key
export AI_API_URL=https://api.anthropic.com/v1/messages
export AI_MODEL=claude-3-sonnet-20240229

# For ChatGPT (OpenAI)
export AI_API_KEY=your_openai_api_key
export AI_API_URL=https://api.openai.com/v1/chat/completions
export AI_MODEL=gpt-4
```

## Running the Application

```bash
# Clone and navigate to backend directory
cd backend

# Install dependencies
mvn clean install

# Run the application
mvn spring-boot:run
```

The server will start on `http://localhost:8080`

## API Endpoints

### POST /api/analyze

Analyzes a resume and provides improvement suggestions.

**Request Body:**
```json
{
  "resume": "Your resume text here...",
  "jobDescription": "Optional job description for tailored analysis"
}
```

**Response:**
```json
{
  "summary": "Overall analysis summary",
  "strengths": ["Strength 1", "Strength 2"],
  "improvements": ["Improvement 1", "Improvement 2"]
}
```

## Project Structure

```
src/main/java/com/resumeai/backend/
├── ResumeAiBackendApplication.java    # Main application class
├── controller/
│   └── ResumeController.java          # REST API endpoints
├── model/
│   ├── AnalysisRequest.java           # Request DTO
│   └── AnalysisResponse.java          # Response DTO
└── service/
    └── AiAnalysisService.java         # AI integration service
```

## Development

The service currently returns mock data. To integrate with real AI:

1. Update `AiAnalysisService.java` to call the actual AI API
2. Configure API keys in environment variables
3. Test with real AI responses

## Testing

```bash
mvn test
```

## Health Check

Visit `http://localhost:8080/actuator/health` for application health status.