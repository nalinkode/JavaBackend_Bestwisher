package com.bestwisher.dto;

public class MessageResponse<T> {

	private String message;

	private T data;

	public MessageResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MessageResponse(String message, T data) {
		super();
		this.message = message;
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "MessageResponse [message=" + message + ", data=" + data + "]";
	}

}
