package com.infinitecoder.sink.event;

public class Event {
	
	private boolean async;
	
	public Event() {
		if(getClass().getSimpleName().toLowerCase().startsWith("async")) {
			async = true;
		}
	}
	
	public boolean isAsynchronous() {
		return async;
	}
	
	protected void setAsynchronous(boolean async) {
		this.async = async;
	}
	
}
