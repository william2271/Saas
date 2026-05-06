import { useState, type ChangeEvent, type FormEvent } from 'react';

type AnalysisResult = {
  summary: string;
  improvements: string[];
  strengths: string[];
};

function App() {
  const [resumeText, setResumeText] = useState('');
  const [jobDescription, setJobDescription] = useState('');
  const [result, setResult] = useState<AnalysisResult | null>(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const handleFileChange = async (event: ChangeEvent<HTMLInputElement>) => {
    const file = event.target.files?.[0];
    if (!file) return;
    const text = await file.text();
    setResumeText(text);
  };

  const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    setError('');
    setResult(null);

    if (!resumeText.trim()) {
      setError('Please paste your resume or upload a text file.');
      return;
    }

    setLoading(true);
    try {
      const response = await fetch('/api/analyze', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ resume: resumeText, jobDescription })
      });

      if (!response.ok) {
        throw new Error('Failed to analyze resume.');
      }

      const data = await response.json();
      setResult(data as AnalysisResult);
    } catch (fetchError) {
      setError(String(fetchError));
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="app-shell">
      <header className="hero-panel">
        <div>
          <span className="badge">AI Resume Analyzer</span>
          <h1>Improve your resume with intelligent guidance</h1>
          <p>
            Drag or paste your resume, add a target job description, and get tailored improvement tips.
            Your Spring Boot backend will connect to Claude or ChatGPT for AI-powered analysis.
          </p>
        </div>
      </header>

      <main className="layout-grid">
        <section className="form-card">
          <h2>Submit your resume</h2>
          <form onSubmit={handleSubmit}>
            <label>
              Resume text
              <textarea
                rows={12}
                value={resumeText}
                onChange={(event) => setResumeText(event.target.value)}
                placeholder="Paste your resume content here..."
              />
            </label>

            <label>
              Optional job description
              <textarea
                rows={6}
                value={jobDescription}
                onChange={(event) => setJobDescription(event.target.value)}
                placeholder="Paste the job posting or target role description..."
              />
            </label>

            <label className="file-input-label">
              Upload resume text file
              <input type="file" accept=".txt" onChange={handleFileChange} />
            </label>

            {error && <p className="error-message">{error}</p>}

            <button type="submit" disabled={loading}>
              {loading ? 'Analyzing...' : 'Analyze resume'}
            </button>
          </form>

          <div className="note-card">
            <h3>Backend integration</h3>
            <p>
              This frontend expects a POST endpoint at <code>/api/analyze</code>. The backend should
              forward the request to Claude or ChatGPT and return JSON with <code>summary</code>,
              <code>improvements</code>, and <code>strengths</code>.
            </p>
          </div>
        </section>

        <section className="result-card">
          <h2>Analysis output</h2>

          {!result && !loading && (
            <div className="placeholder-box">
              Paste a resume and press <strong>Analyze resume</strong> to see improvement guidance.
            </div>
          )}

          {result && (
            <div className="result-content">
              <article>
                <h3>Executive summary</h3>
                <p>{result.summary}</p>
              </article>

              <article>
                <h3>Key strengths</h3>
                <ul>
                  {result.strengths.map((item, index) => (
                    <li key={index}>{item}</li>
                  ))}
                </ul>
              </article>

              <article>
                <h3>Improvement guidelines</h3>
                <ol>
                  {result.improvements.map((item, index) => (
                    <li key={index}>{item}</li>
                  ))}
                </ol>
              </article>
            </div>
          )}
        </section>
      </main>
    </div>
  );
}

export default App;
