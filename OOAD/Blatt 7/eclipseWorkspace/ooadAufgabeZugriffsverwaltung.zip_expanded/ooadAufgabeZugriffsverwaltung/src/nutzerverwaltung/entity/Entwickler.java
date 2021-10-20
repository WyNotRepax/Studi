/*
 * Author: Benno Steinkamp
 * Datum: 30.11.2020
 * */

package nutzerverwaltung.entity;

public class Entwickler extends Nutzer {
	public Entwickler() {
		super();
	}
	public Entwickler(String login, String passwort) {
		super(login,passwort);
	}
	@Override
	public boolean darfDatenBearbeiten() {
		return true;
	}
	@Override
	public boolean darfTabellenBearbeiten() {
		return false;
	}
	@Override
	public boolean darfNutzerAnlegen() {
		return false;
	}
}
