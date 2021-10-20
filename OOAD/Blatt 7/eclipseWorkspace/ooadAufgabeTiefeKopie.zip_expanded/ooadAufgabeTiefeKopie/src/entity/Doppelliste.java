package entity;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Doppelliste implements Cloneable {

	private List<Punkt> liste1;
	private List<Punkt> liste2;

	public Doppelliste() {
		this.liste1 = new ArrayList<>();
		this.liste2 = new ArrayList<>();
	}

	public Doppelliste(List<Punkt> liste1, List<Punkt> liste2) {
		super();
		this.liste1 = liste1;
		this.liste2 = liste2;
	}

	@Override
	public Doppelliste clone() {
		List<Punkt> liste1Clone = null;
		List<Punkt> liste2Clone = null;
		HashMap<Punkt, Punkt> lookupTable = new HashMap<Punkt, Punkt>();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		XMLEncoder encoder = new XMLEncoder(out);
		encoder.writeObject(this);
		encoder.flush();
		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		XMLDecoder decoder = new XMLDecoder(in);
		Doppelliste clone = (Doppelliste)decoder.readObject();
		decoder.close();
		encoder.close();
		try {
			in.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clone;
	}

	public List<Punkt> getListe1() {
		return liste1;
	}

	public void setListe1(List<Punkt> liste1) {
		this.liste1 = liste1;
	}

	public List<Punkt> getListe2() {
		return liste2;
	}

	public void setListe2(List<Punkt> liste2) {
		this.liste2 = liste2;
	}

	@Override
	public int hashCode() {
		return Objects.hash(liste1, liste2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Doppelliste other = (Doppelliste) obj;
		return Objects.equals(liste1, other.liste1) && Objects.equals(liste2, other.liste2);
	}

	@Override
	public String toString() {
		return "Doppelliste [liste1=" + liste1 + ", liste2=" + liste2 + "]";
	}

}