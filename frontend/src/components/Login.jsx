
export default function Login({ onLogin }) {
	const handleSubmit = (event) => {
		event.preventDefault();
		onLogin();
	};

	return (
		<main>
			<h1>Login</h1>
			<form onSubmit={handleSubmit}>
				<div>
					<label htmlFor="email">E-Mail-Adresse</label>
					<input
						id="email"
						name="email"
						type="email"
						autoComplete="email"
						required
					/>
				</div>
				<div>
					<label htmlFor="password">Passwort</label>
					<input
						id="password"
						name="password"
						type="password"
						autoComplete="current-password"
						required
					/>
				</div>
				<button type="submit">Anmelden</button>
			</form>
		</main>
	);
}
