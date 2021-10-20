package main;

import java.util.Set;

import aktion.Automat;
import zustand.Zustand;

public class Main {
  
  public static void main(String... s) {
	Zustand s1 = new Zustand();
	Zustand s2 = new Zustand();
	Zustand s3 = new Zustand();
	Zustand s4 = new Zustand();
	
	s1.uebergangHinzufuegen('a', s2);
	s1.uebergangHinzufuegen('b', s4);

	s2.uebergangHinzufuegen('a', s3);
	s2.uebergangHinzufuegen('b', s4);
	
	s3.uebergangHinzufuegen('b', s3);
	
	s4.uebergangHinzufuegen('a', s4);
	s4.uebergangHinzufuegen('b', s4);
	
    Automat automat = new Automat(s1, Set.of(s3));
    boolean ok = true;
    System.out.print("zu untersuchender String: ");
    String eingabe = Eingabe.leseString();
    for( int i = 0; i < eingabe.length() && ok; i++) {
      ok = ok && automat.schritt(eingabe.charAt(i));
    }
    if (ok && automat.imEndzustand()) {
      System.out.println("Eingabe akzeptiert");
    } else {
      System.out.println("Eingabe nicht akzeptiert");
    }
  }
}