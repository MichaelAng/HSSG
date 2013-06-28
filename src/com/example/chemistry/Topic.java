package com.example.chemistry;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class Topic implements Parcelable {

	private List<Question> question = new ArrayList<Question>();
	private int topicId = 0;
	private String topic = null;

	public List<Question> getQuestion() {
		return question;
	}

	public int getTopicId() {
		return topicId;
	}

	public String getTopic() {
		return topic;
	}

	public void setQuestion(List<Question> question) {
		this.question = question;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	Topic() {
		// initialization
		question = new ArrayList<Question>();
	}
	
	public Topic(List<Question> question){
		this.question = question;
	}

	public Topic(Parcel in) {
		topicId = in.readInt();
		topic = in.readString();
		in.readTypedList(question, Question.CREATOR);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel outParcel, int flags) {
		outParcel.writeInt(topicId);
		outParcel.writeString(topic);
		outParcel.writeTypedList(question);
	}

	public static final Parcelable.Creator<Topic> CREATOR = new Parcelable.Creator<Topic>() {

		@Override
		public Topic createFromParcel(Parcel in) {
			return new Topic(in);
		}

		@Override
		public Topic[] newArray(int size) {
			return new Topic[size];
		}
	};

}
// public void setTopic(String topic) {
// this.topic = topic;
// }
//
// public void setTopicId(int topicId) {
// this.topicId = topicId;
// }
//
// public void setQuestion(List<Question> question) {
// this.question = question;
// }
//
// public String getTopic() {
// return topic;
// }
//
// public int getTopicId() {
// return topicId;
// }
//
// public List<Question> getQuestion() {
// return question;
// }
//
// public static Parcelable.Creator<Topic> getCreator() {
// return CREATOR;
// }
//
// public Topic() {
// question = new ArrayList<Question>();
// }
//
// public Topic(Parcel in) {
// this();
// readFromParcel(in);
// }
//
// @Override
// public void writeToParcel(Parcel dest, int flags) {
// dest.writeString(topic);
// dest.writeInt(topicId);
// dest.writeList(question);
// }
//
// public static final Parcelable.Creator<Topic> CREATOR = new
// Parcelable.Creator<Topic>() {
//
// public Topic createFromParcel(Parcel source) {
// return new Topic(source);
// }
//
// public Topic[] newArray(int size) {
// return new Topic[size];
// }
//
// };
//
// private void readFromParcel(Parcel in) {
// this.topic = in.readString();
// this.topicId = in.readInt();
// in.readTypedList(question, Question.CREATOR); /* NULLPOINTER HERE */
// }
//
// @Override
// public int describeContents() {
// // TODO Auto-generated method stub
// return 0;
// }
//
//
//
// }
