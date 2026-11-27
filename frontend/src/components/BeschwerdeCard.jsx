import { useState } from "react";

function BeschwerdeCard() {
    const [submitted, setSubmitted] = useState(false);
    const [selectedImages, setSelectedImages] = useState([]);

    function handleSubmit(event) {
        event.preventDefault();
        setSubmitted(true);
        event.currentTarget.reset();
        setSelectedImages([]);
    }

    return (
        <section className="complaint-card" aria-labelledby="complaint-form-title">
            <h2 id="complaint-form-title">Neue Beschwerde</h2>
            <p className="complaint-card-intro">
                Beschreibe dein Anliegen. Die mit * markierten Felder sind erforderlich.
            </p>

            {submitted && (
                <p className="complaint-success" role="status">
                    Danke! Deine Beschwerde wurde erfasst.
                </p>
            )}

            <form onSubmit={handleSubmit} onChange={() => setSubmitted(false)}>
                <div className="complaint-form-field">
                    <label htmlFor="complaint-title">Titel *</label>
                    <input
                        id="complaint-title"
                        name="title"
                        type="text"
                        placeholder="Worum geht es?"
                        maxLength={120}
                        required
                    />
                </div>

                <div className="complaint-form-field">
                    <label htmlFor="complaint-category">Kategorie *</label>
                    <select id="complaint-category" name="category" defaultValue="" required>
                        <option value="" disabled>Bitte auswählen</option>
                        <option value="strassen">Straßen und Wege</option>
                        <option value="verkehr">Verkehr und Mobilität</option>
                        <option value="sauberkeit">Sauberkeit und Abfall</option>
                        <option value="beleuchtung">Beleuchtung</option>
                        <option value="sonstiges">Sonstiges</option>
                    </select>
                </div>

                <div className="complaint-form-field">
                    <label htmlFor="complaint-location">Ort</label>
                    <input
                        id="complaint-location"
                        name="location"
                        type="text"
                        placeholder="Straße, Platz oder Stadtteil"
                        maxLength={160}
                    />
                </div>

                <div className="complaint-form-field">
                    <label htmlFor="complaint-description">Beschreibung *</label>
                    <textarea
                        id="complaint-description"
                        name="description"
                        rows="5"
                        placeholder="Beschreibe dein Anliegen möglichst genau …"
                        maxLength={2000}
                        required
                    />
                </div>

                <div className="complaint-form-field">
                    <label htmlFor="complaint-images">Bilder hinzufügen</label>
                    <input
                        id="complaint-images"
                        name="images"
                        type="file"
                        accept="image/*"
                        multiple
                        onChange={(event) => setSelectedImages(Array.from(event.target.files ?? []))}
                    />
                    <span className="complaint-field-hint">
                        Du kannst mehrere Bilddateien auswählen.
                    </span>
                    {selectedImages.length > 0 && (
                        <ul className="complaint-image-list" aria-label="Ausgewählte Bilder">
                            {selectedImages.map((image) => (
                                <li key={`${image.name}-${image.lastModified}`}>
                                    {image.name}
                                </li>
                            ))}
                        </ul>
                    )}
                </div>

                <button className="complaint-submit-button" type="submit">
                    Beschwerde abschicken
                </button>
            </form>
        </section>
    );
}

export default BeschwerdeCard;