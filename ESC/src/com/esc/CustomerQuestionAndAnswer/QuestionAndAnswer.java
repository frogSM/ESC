package com.esc.CustomerQuestionAndAnswer;

public class QuestionAndAnswer {
	
	String questionImage;
	String questionTitle;
	String answerImage;
	String answerContent;
	
	public QuestionAndAnswer( ) {

		this.questionImage = "";
		this.questionTitle = "";
		this.answerImage = "";
		this.answerContent = "";

	}
	
	public QuestionAndAnswer( String qustionImage , String questionTitle, String answerImage, String answerContent ) {
		this.questionImage = qustionImage;
		this.questionTitle = questionTitle;
		this.answerImage = answerImage;
		this.answerContent = answerContent;
	}
	
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	
	public void setAnswerImage(String answerImage) {
		this.answerImage = answerImage;
	}
	
	public void setQuestionImage(String questionImage) {
		this.questionImage = questionImage;
	}
	
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	
	public String getQuestionImage() {
		return questionImage;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}
	
	public String getAnswerImage() {
		return answerImage;
	}
	
	public String getAnswerContent() {
		return answerContent;
	}

}
