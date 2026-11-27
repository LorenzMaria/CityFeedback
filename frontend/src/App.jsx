import { useState } from "react";
import Footer from "./components/Footer";
import Header from "./components/Header";
import Login from "./components/Login";
import BeschwerdeSeite from "./pages/BeschwerdeSeite";
import "./App.css";

function App() {
    const [currentPage, setCurrentPage] = useState("login");

    return (
        <div className="app-shell">
            <Header />
            {currentPage === "login" ? (
                <Login onLogin={() => setCurrentPage("complaints")} />
            ) : (
                <BeschwerdeSeite onLogout={() => setCurrentPage("login")} />
            )}
            <Footer />
        </div>
    );
}

export default App;
