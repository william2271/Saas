# Resume AI Analyzer Frontend

This is a React + Vite frontend for an AI resume analysis SaaS.

## Features
- Paste resume text or upload a `.txt` resume file
- Optionally include a target job description
- Sends analysis requests to a backend at `/api/analyze`
- Displays summary, strengths, and improvement guidelines

## Project structure
- `src/App.tsx` - main UI and form handling
- `src/styles.css` - page styling
- `vite.config.ts` - Vite development config
- `package.json` - dependencies and scripts

## Backend contract
The frontend expects a POST endpoint at `/api/analyze`.

Request JSON:
```json
{
  "resume": "...resume text...",
  "jobDescription": "...optional job description..."
}
```

Response JSON:
```json
{
  "summary": "AI analysis summary",
  "strengths": ["..."],
  "improvements": ["...", "..."]
}
```

## Setup
1. Install Node.js and npm.
2. Run `npm install` from the project root.
3. Run `npm run dev` to start the app.

## Notes
- The backend will be implemented separately in Java Spring Boot.
- The frontend does not include AI integration directly; it delegates to the backend.
