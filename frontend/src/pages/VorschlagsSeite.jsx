import VorschlagCard from "../components/VorschlagCard";

function VorschlagsSeite({ onBack, onLogout }) {
    return (
        <main className="suggestion-page">
            <div className="complaint-page-heading">
                <div>
                    <h1>Vorschläge</h1>
                    <p>Gemeinsam machen wir CityFeedback noch besser.</p>
                </div>
                <div className="page-actions">
                    <button className="logout-button" type="button" onClick={onBack}>
                        Zurück zu Beschwerden
                    </button>
                    <button className="logout-button" type="button" onClick={onLogout}>
                        Logout
                    </button>
                </div>
            </div>

            <VorschlagCard />
        </main>
    );
}

export default VorschlagsSeite;
