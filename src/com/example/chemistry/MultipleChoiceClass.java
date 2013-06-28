package com.example.chemistry;

import android.os.Parcel;
import android.os.Parcelable;

public class MultipleChoiceClass implements Parcelable {
	/**
	 * I need to create a question Id, create a answer array, explanation
	 * array, choices array
	 */
	private String topic;
	private String[] questionTitle;
	private String[] question;
	private String[] answer;
	private String[] explanation;
	private String[] choiceA;
	private String[] choiceB;
	private String[] choiceC;
	private String[] choiceD;

	public MultipleChoiceClass() {
	}

	public MultipleChoiceClass(String topic, String[] questionTitleArray,
			String[] question, String[] answer, String[] explanation,
			String[] choiceA, String[] choiceB, String[] choiceC,
			String[] choiceD) {
		super();
		this.topic = topic;
		this.questionTitle = questionTitleArray;
		this.question = question;
		this.answer = answer;
		this.explanation = explanation;
		this.choiceA = choiceA;
		this.choiceB = choiceB;
		this.choiceC = choiceC;
		this.choiceD = choiceD;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public void setQuestionTitleArray(String[] question) {
		this.questionTitle = question;
	}

	public void setQuestion(String[] question) {
		this.question = question;
	}

	public void setAnswer(String[] answer) {
		this.answer = answer;
	}

	public void setExplanation(String[] explanation) {
		this.explanation = explanation;
	}

	public void setChoiceA(String[] choiceA) {
		this.choiceA = choiceA;
	}

	public void setChoiceB(String[] choiceB) {
		this.choiceB = choiceB;
	}

	public void setChoiceC(String[] choiceC) {
		this.choiceC = choiceC;
	}

	public void setChoiceD(String[] choiceD) {
		this.choiceD = choiceD;
	}

	public String getTopic() {
		return topic;
	}

	public String[] getQuestionTitleArray() {
		return questionTitle;
	}

	public String[] getQuestion() {
		return question;
	}

	public String[] getAnswer() {
		return answer;
	}

	public String[] getExplanation() {
		return explanation;
	}

	public String[] getChoiceA() {
		return choiceA;
	}

	public String[] getChoiceB() {
		return choiceB;
	}

	public String[] getChoiceC() {
		return choiceC;
	}

	public String[] getChoiceD() {
		return choiceD;
	}

	public static Parcelable.Creator<MultipleChoiceClass> getCreator() {
		return CREATOR;
	}

	protected MultipleChoiceClass(Parcel in) {
		topic = in.readString();
		questionTitle = in.createStringArray();
		question = in.createStringArray();
		answer = in.createStringArray();
		explanation = in.createStringArray();
		choiceA = in.createStringArray();
		choiceB = in.createStringArray();
		choiceC = in.createStringArray();
		choiceD = in.createStringArray();
	}

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(topic);
		dest.writeStringArray(questionTitle);
		dest.writeStringArray(question);
		dest.writeStringArray(answer);
		dest.writeStringArray(explanation);
		dest.writeStringArray(choiceA);
		dest.writeStringArray(choiceB);
		dest.writeStringArray(choiceC);
		dest.writeStringArray(choiceD);
	}

	public static final Parcelable.Creator<MultipleChoiceClass> CREATOR = new Parcelable.Creator<MultipleChoiceClass>() {
		public MultipleChoiceClass createFromParcel(Parcel in) {
			return new MultipleChoiceClass(in);
		}

		public MultipleChoiceClass[] newArray(int size) {
			return new MultipleChoiceClass[size];
		}
	};

}




