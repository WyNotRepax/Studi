
public class Produkt {
	String name;
	int productnum;
	double price;

	Produkt(String name, int productnum, double price) {
		this.name = name;
		this.productnum = productnum;
		this.price = price;
	}

	Produkt(String name, int productnum) {
		this.name = name;
		this.productnum = productnum;
	}

	Produkt(String name, double price) {
		this.name = name;
		this.price = price;
	}

	Produkt(int productnum, double price) {
		this.productnum = productnum;
		this.price = price;
	}

	Produkt(String name) {
		this.name = name;
	}

	Produkt(int productnum) {
		this.productnum = productnum;
	}

	Produkt(double price) {
		this.price = price;
	}

	Produkt() {

	}

	public static void main(String[] args) {
		Produkt p1 = new Produkt("Test", 1, 1.0);
		Produkt p2 = new Produkt("Test", 1);
		Produkt p3 = new Produkt("Test", 1.0);
		Produkt p4 = new Produkt(1, 1.0);
		Produkt p5 = new Produkt("Test");
		Produkt p6 = new Produkt(1);
		Produkt p7 = new Produkt(1.0);
		Produkt p8 = new Produkt();
	}
}
