
public class TestClass {
	public static void main(String[] args) {
		// a)
		{
			Menge m = new Menge();
			if (m == null) {
				System.out.println("Fehler a): Menge wird \"null\"!");
			} else {
				System.out.println("a) ?berpr?ft keinen Fehler gefunden!");
			}
		}
		// b
		{
			Menge m1 = new Menge();
			Menge m2 = new Menge();
			Menge m3 = new Menge();
			m1.addPerson(new Person("Eine", "Person"));
			m2.addPerson(new Person("Eine", "Person"));
			m3.addPerson(new Person("Andere", "Person"));

			if (!m1.equals(m2)) {
				System.out.println("Fehler in b): Falsch negativ!");
			} else if (m1.equals(m3)) {
				System.out.println("Fehler in b): Falsch positiv!");
			} else {
				System.out.println("b) ?berpr?ft keinen Fehler gefunden!");
			}
		}
		// c)
		{
			Menge m1 = new Menge();
			Person p = new Person("Eine", "Person");
			m1.addPerson(p);
			Menge m2 = new Menge(m1);
			if (!m2.equals(m1)) {
				System.out.println("Fehler in b): nicht alle Personen werden Kopiert!");
			} else {
				System.out.println("b) ?berpr?ft keinen Fehler gefunden!");
			}
		}
		// d)
		{
			Menge m = new Menge();
			Person p = new Person("Eine", "Person");
			m.addPerson(p);
			if (!m.contains(p)) {
				System.out.println("Fehler in d): Personen wird ncht Hinzugef?gt!");
			} else {
				System.out.println("d) ?berpr?ft keinen Fehler gefunden!");
			}
		}
		// e)
		{
			Menge m = new Menge();
			Person p = new Person("Eine", "Person");
			m.addPerson(p);
			m.removePerson(p);
			if (m.contains(p)) {
				System.out.println("Fehler in e): Person wird nicht entfernt!");
			} else {
				System.out.println("e) ?berpr?ft keinen Fehler gefunden!");
			}
		}
		// f)
		{
			Menge m = new Menge();
			Person p1 = new Person("Person", "Eins");
			m.addPerson(p1);
			if (!m.contains("Person", "Eins")) {
				System.out.println("Fehler in f): Falsch negativ!");
			} else if (m.contains("Andere", "Person")) {
				System.out.println("Fehler in f): Falsch positiv!");
			} else {
				System.out.println("f) ?berpruft keinen Fehler gefunden!");
			}
		}
		// g)
		{
			Menge m1 = new Menge();
			Menge m2 = new Menge();
			Menge m3 = new Menge();
			Person p1 = new Person("Person", "Eins");
			Person p2 = new Person("Person", "Zwei");
			Person p3 = new Person("Person", "Drei");
			m1.addPerson(p1);
			m1.addPerson(p2);
			m2.addPerson(p2);
			m2.addPerson(p3);
			m3.addPerson(p2);

			if (!m3.equals(m1.intersect(m2))) {
				System.out.println("Fehler in g) Schnittmenge fehlerhaft!");
			} else {
				System.out.println("g) ?berpr?ft keinen Fehler gefunden!");
			}
		}
		// h)
		{

			Menge m1 = new Menge();
			Menge m2 = new Menge();
			Menge m3 = new Menge();
			Person p1 = new Person("Person", "Eins");
			Person p2 = new Person("Person", "Zwei");
			m1.addPerson(p1);
			m2.addPerson(p2);
			m3.addPerson(p1);
			m3.addPerson(p2);
			if (!m3.equals(m1.union(m2))) {
				System.out.println("Fehler in h) Vereinigung fehlerhaft!");
			} else {
				System.out.println("h) ?berpr?ft keinen Fehler gefunden!");
			}
		}
		{
			Menge m1 = new Menge();
			Menge m2 = new Menge();
			Menge m3 = new Menge();
			Person p1 = new Person("Person", "Eins");
			Person p2 = new Person("Person", "Zwei");
			Person p3 = new Person("Person", "Drei");
			m1.addPerson(p1);
			m1.addPerson(p2);
			m2.addPerson(p2);
			m2.addPerson(p3);
			m3.addPerson(p1);
			if (!m3.equals(m1.removed(m2))) {
				System.out.println("Fehler in i) Differenzbildun fehlerhaft!");
			} else {
				System.out.println("i) ?berpr?ft keinen Fehler gefunden!");
			}
		}
		
	}
}
