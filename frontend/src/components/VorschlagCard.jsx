import { useState } from "react";

function VorschlagCard() {
    const [submitted, setSubmitted] = useState(false);

    function handleSubmit(event) {
        event.preventDefault();
        setSubmitted(true);
        event.currentTarget.reset();
    }

    return (
        <section className="complaint-card suggestion-card" aria-labelledby="suggestion-form-title">
            <h2 id="suggestion-form-title">Neuen Vorschlag einreichen</h2>
            <p className="complaint-card-intro">
                Deine Ideen helfen dabei, unsere Stadt gemeinsam weiterzuentwickeln.
            </p>

            {submitted && (
                <p className="complaint-success" role="status">
                    Danke! Dein Vorschlag wurde erfasst.
                </p>
            )}

            <form onSubmit={handleSubmit} onChange={() => setSubmitted(false)}>
                <div className="complaint-form-field">
                    <label htmlFor="suggestion-title">Titel *</label>
                    <input
                        id="suggestion-title"
                        name="title"
                        type="text"
                        placeholder="Fasse deine Idee kurz zusammen"
                        maxLength={120}
                        required
                    />
                </div>

                <div className="complaint-form-field">
                    <label htmlFor="suggestion-category">Themenbereich *</label>
                    <select id="suggestion-category" name="category" defaultValue="" required>
                        <option value="" disabled>Bitte auswählen</option>
                        <option value="mobilitaet">Mobilität und Verkehr</option>
                        <option value="umwelt">Umwelt und Grünflächen</option>
                        <option value="freizeit">Freizeit und Kultur</option>
                        <option value="digitales">Digitales und Verwaltung</option>
                        <option value="sonstiges">Sonstiges</option>
                    </select>
                </div>

                <div className="complaint-form-field">
                    <label htmlFor="suggestion-location">Betroffener Ort</label>
                    <input
                        id="suggestion-location"
                        name="location"
                        type="text"
                        placeholder="Stadtteil, Straße oder Platz (optional)"
                        maxLength={160}
                    />
                </div>

                <div className="complaint-form-field">
                    <label htmlFor="suggestion-description">Beschreibung *</label>
                    <textarea
                        id="suggestion-description"
                        name="description"
                        rows="5"
                        placeholder="Wie könnte deine Idee umgesetzt werden?"
                        maxLength={2000}
                        required
                    />
                </div>

                <button className="complaint-submit-button" type="submit">
                    Vorschlag einreichen
                </button>
            </form>
        </section>
    );
}

export default VorschlagCard;
