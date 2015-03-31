package com.esc.CustomerQuestionAndAnswer;

public class QuestionAndAnswer {
	
	String questionTitle;
	String answerContent;
	
	public QuestionAndAnswer(  String questionTitle,  String answerContent ) {

		this.questionTitle = questionTitle;
		this.answerContent = answerContent;
	
	}
	
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public String getQuestionTitle() {
		return questionTitle;
	}
	public String getAnswerContent() {
		return answerContent;
	}

}
