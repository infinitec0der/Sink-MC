package com.infinitecoder.sink.event;

public class CancellableEvent extends Event {
	
	private boolean cancelled = false;
	
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	public boolean isCancelled() {
		return cancelled;
	}
	
}
