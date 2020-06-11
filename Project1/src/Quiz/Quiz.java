package Quiz;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Quiz {
	
	private SimpleIntegerProperty num;
	private SimpleStringProperty quiz;
	private SimpleIntegerProperty r1;
	private SimpleStringProperty select1;
	private SimpleIntegerProperty r2;
	private SimpleStringProperty select2;
	private SimpleIntegerProperty r3;
	private SimpleStringProperty select3;
	private SimpleIntegerProperty answer;
	private SimpleStringProperty answerlist;

	public Quiz(int num, String quiz, int r1, String select1, int r2, String select2, int r3, String select3,
			int answer, String answerlist) {
		super();
		this.num = new SimpleIntegerProperty(num);
		this.quiz = new SimpleStringProperty(quiz);
		this.r1 = new SimpleIntegerProperty(r1);
		this.select1 = new SimpleStringProperty(select1);
		this.r2 = new SimpleIntegerProperty(r2);
		this.select2 = new SimpleStringProperty(select2);
		this.r3 = new SimpleIntegerProperty(r3);
		this.select3 = new SimpleStringProperty(select3);
		this.answer = new SimpleIntegerProperty(answer);
		this.answerlist = new SimpleStringProperty(answerlist);
	} // num

	public void setNum(int num) {
		this.num.set(num);
	}

	public int getNum() {
		return this.num.get();
	}

	public SimpleIntegerProperty numProperty() {
		return this.num;

	} // quiz

	public void setQuiz(String quiz) {
		this.quiz.set(quiz);
	}

	public String getQuiz() {
		return this.quiz.get();
	}

	public SimpleStringProperty quizProperty() {
		return this.quiz;
	}

	// r1
	public void setR1(int r1) {
		this.num.set(r1);
	}

	public int getR1() {
		return this.r1.get();
	}

	public SimpleIntegerProperty r1Property() {
		return this.r1;

	}

	// select1
	public void setSelect1(String select1) {
		this.select1.set(select1);
	}

	public String getSelect1() {
		return this.select1.get();
	}

	public SimpleStringProperty select1Property() {
		return this.select1;
	}

	// r2
	public void setR2(int r2) {
		this.num.set(r2);
	}

	public int getR2() {
		return this.r2.get();
	}

	public SimpleIntegerProperty r2Property() {
		return this.r2;

	}

	// select2
	public void setSelect2(String select2) {
		this.select2.set(select2);
	}

	public String getSelect2() {
		return this.select2.get();
	}

	public SimpleStringProperty select2Property() {
		return this.select2;

		
	}// r3

	public void setR3(int r3) {
		this.num.set(r3);
	}

	public int getR3() {
		return this.r3.get();
	}

	public SimpleIntegerProperty r3Property() {
		return this.r3;

	} 
	// select3

	public void setSelect3(String select3) {
		this.select3.set(select3);
	}

	public String getSelect3() {
		return this.select3.get();
	}

	public SimpleStringProperty select3Property() {
		return this.select3;
	}
	//answer
	public void setAnswer(int answer) {
		this.answer.set(answer);
	}

	public int getAnswer() {
		return this.answer.get();
	}

	public SimpleIntegerProperty answerProperty() {
		return this.answer;
	}
	// answerlist
	public void setAnswerlist(String answerlist) {
		this.answerlist.set(answerlist);
	}

	public String getAnswerlist() {
		return this.answerlist.get();
	}

	public SimpleStringProperty answerlistProperty() {
		return this.answerlist;
	}
}