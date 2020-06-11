package Quiz;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class QuizController implements Initializable {
	Connection conn;
	ObservableList<Quiz> list = FXCollections.observableArrayList();
	@FXML
	Button btnOk, btnClose;
	@FXML
	Label quiz, select1, select2, select3;
	@FXML
	ToggleGroup group;

	ObservableList<Quiz> alist, blist;
	int nextNum = 1;
	int nextAnum = 0;
	String match;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");

		} catch (Exception e) {
			e.printStackTrace();
		}
		alist = getQuizList();
		blist = FXCollections.observableArrayList();

		int[] intAry = new int[alist.size()];
		int aryLength = intAry.length;

		for (int i = 0; i < aryLength;) {
			int temp = (int) (Math.random() * aryLength);
			intAry[i] = temp;
			int j = i;
			for (; j > 0;) {
				if (i != 0) {
					if (intAry[i] == intAry[j - 1]) {
						break;
					}
					j--;
				}
			}
			if (j != 0)
				continue;
			i++;
		}
		for (int i = 0; i < alist.size(); i++) {
			blist.add(alist.get(intAry[i]));
		}
		getAnsList(blist.get(0));
		btnOk.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				handleBtnOkAction(event);
			}
		});
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldVal, Toggle newVal) {
				if (newVal != null) {
					if (newVal.getUserData().equals(select1.getUserData())) {
						if (select1.getText().equals(blist.get(nextAnum).getSelect1())) {
							match = " 정답입니다.";
						} else if (select1.getText().equals(blist.get(nextAnum).getSelect2())) {
							match = "오답입니다.";
						} else if (select1.getText().equals(blist.get(nextAnum).getSelect3())) {
							match = "오답입니다.";

						}

					} else if (newVal.getUserData().equals(select2.getUserData())) {
						if (select2.getText().equals(blist.get(nextAnum).getSelect1())) {
							match = " 정답입니다.";
						} else if (select2.getText().equals(blist.get(nextAnum).getSelect2())) {
							match = "오답입니다.";
						} else if (select2.getText().equals(blist.get(nextAnum).getSelect3())) {
							match = "오답입니다.";
						}

					} else if (newVal.getUserData().equals(select3.getUserData())) {
						if (select3.getText().equals(blist.get(nextAnum).getSelect1())) {
							match = " 정답입니다.";
						} else if (select3.getText().equals(blist.get(nextAnum).getSelect2())) {
							match = "오답입니다.";
						} else if (select3.getText().equals(blist.get(nextAnum).getSelect3())) {
							match = "오답입니다.";
						}
					}			
				}
			}
		});
	}

	public ObservableList<Quiz> getQuizList() {
		String sql = "select * from question";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Quiz board = new Quiz(rs.getInt("num"), rs.getString("quiz"), rs.getInt("r1"), rs.getString("select1"),
						rs.getInt("r2"), rs.getString("select2"), rs.getInt("r3"), rs.getString("select3"),
						rs.getInt("answer"), rs.getString("answerlist"));
				list.add(board);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public void getAnsList(Quiz quizAns) {
		String[] quz = new String[3];
		quz[0] = quizAns.getSelect1();
		quz[1] = quizAns.getSelect2();
		quz[2] = quizAns.getSelect3();

		Random random = new Random();
		int num1 = random.nextInt(3);
		int num2 = random.nextInt(3);
		int num3 = random.nextInt(3);

		if (num1 == num2) {
			while (true) {
				num2 = random.nextInt(3);
				if (num1 != num2) {
					break;
				}
			}
		}
		if (num3 == num1 || num3 == num2) {
			while (true) {
				num3 = random.nextInt(3);
				if (num3 != num1 && num3 != num2) {
					break;
				}
			}
		}
		quiz.setText(quizAns.getQuiz());
		select1.setText(quz[num1]);
		select2.setText(quz[num2]);
		select3.setText(quz[num3]);
	}

	public void handleBtnOkAction(ActionEvent ae) {
		Stage addStage = new Stage(StageStyle.UTILITY);
		addStage.initModality(Modality.WINDOW_MODAL);
		addStage.initOwner(btnOk.getScene().getWindow());

		try {
			Parent parent = FXMLLoader.load(getClass().getResource("Answer.fxml"));
			Scene scene = new Scene(parent);
			addStage.setScene(scene);
			addStage.setResizable(false);
			addStage.show();

			Label answerLabel = (Label) parent.lookup("#answerLabel");
			answerLabel.setText(match);

			Label answerList = (Label) parent.lookup("#answerList");
			answerList.setText(blist.get(nextAnum++).getAnswerlist());

			Button btnNext = (Button) parent.lookup("#btnNext");
			btnNext.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

					addStage.close();
					getAnsList(blist.get(nextNum++));
				}

			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void handleBtnCancelAction(ActionEvent e) {

	}
}
