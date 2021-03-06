import java.util.ArrayList;

public class Question {
	private int yesAnswers;
	private int noAnswers;
	private String question;

	public Question(String question) {
		this.question = question;
	}

	public void answer(boolean jn) {
		if (jn) {
			yesAnswers++;
		} else {
			noAnswers++;
		}
	}

	public boolean answer(String jn) {
		switch (jn.toLowerCase()) {
		case "j":
		case "ja":
		case "y":
		case "yes":
			answer(true);
			return true;
		case "n":
		case "nein":
		case "no":
			answer(false);
			return true;
		default:
			return false;
		}
	}

	public String ask() {
		return question;
	}

	public int getYesNum() {

		return yesAnswers;
	}

	public int getNoNum() {
		int noN = 0;
		return noAnswers;
	}

	public double getYesPercent() {
		return ((double) yesAnswers / (yesAnswers + noAnswers)) * 100.0;
	}

	public double getNoPercent() {
		return 100.0 - this.getYesPercent();
	}
}
