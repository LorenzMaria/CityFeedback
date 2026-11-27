import { useState } from "react";
import BeschwerdeCard from "../components/BeschwerdeCard";
import VorschlagCard from "../components/VorschlagCard";

function BeschwerdeSeite({ onLogout }) {
    const [activeCard, setActiveCard] = useState("complaint");

    return (
        <main className="complaint-page">
            <div className="complaint-page-heading">
                <div>
                    <h1>
                        {activeCard === "complaint" ? "Beschwerdemanagement" : "Vorschläge"}
                    </h1>
                    <p>
                        {activeCard === "complaint"
                            ? "Teile uns mit, was in deiner Stadt verbessert werden kann."
                            : "Gemeinsam machen wir CityFeedback noch besser."}
                    </p>
                </div>
                <div className="page-actions">
                    <button className="logout-button" type="button" onClick={onLogout}>
                        Logout
                    </button>
                </div>
            </div>

            <div className="submission-tabs" role="tablist" aria-label="Einreichungsart auswählen">
                <button
                    id="complaint-tab"
                    className={activeCard === "complaint" ? "submission-tab active" : "submission-tab"}
                    type="button"
                    role="tab"
                    aria-selected={activeCard === "complaint"}
                    aria-controls="submission-panel"
                    onClick={() => setActiveCard("complaint")}
                >
                    Beschwerde einreichen
                </button>
                <button
                    id="suggestion-tab"
                    className={activeCard === "suggestion" ? "submission-tab active" : "submission-tab"}
                    type="button"
                    role="tab"
                    aria-selected={activeCard === "suggestion"}
                    aria-controls="submission-panel"
                    onClick={() => setActiveCard("suggestion")}
                >
                    Vorschlag einreichen
                </button>
            </div>

            <div
                id="submission-panel"
                role="tabpanel"
                aria-labelledby={activeCard === "complaint" ? "complaint-tab" : "suggestion-tab"}
            >
                {activeCard === "complaint" ? <BeschwerdeCard /> : <VorschlagCard />}
            </div>
        </main>
    );
}

export default BeschwerdeSeite;