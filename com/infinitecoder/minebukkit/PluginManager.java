package com.infinitecoder.minebukkit;

import java.lang.reflect.Method;
import java.util.ArrayList;

import com.infinitecoder.minebukkit.event.CancellableEvent;
import com.infinitecoder.minebukkit.event.Event;
import com.infinitecoder.minebukkit.event.EventHandler;
import com.infinitecoder.minebukkit.event.Listener;

public class PluginManager {
	private static ArrayList<Listener> handlers = new ArrayList<Listener>();

	public void registerEvents(Listener listener) {
		handlers.add(listener);
	}

	public Event raiseEvent(final Event event) {
		synchronized(this) {
			return raise(event);
		}
	}

	private Event raise(final Event event) {
		for(Listener listener : handlers) {
			Class handler = listener.getClass();
			Method[] methods = handler.getMethods();

			for (int i = 0; i < methods.length; ++i) {
				EventHandler eventHandler = methods[i]
						.getAnnotation(EventHandler.class);
				if (eventHandler != null) {
					Class[] methodParams = methods[i].getParameterTypes();

					if (methodParams.length != 1)
						continue;

					if (!event.getClass().getSimpleName()
							.equals(methodParams[0].getSimpleName()))
						continue;

					if(event instanceof CancellableEvent) {
						if(((CancellableEvent)event).isCancelled()) {
							return event;
						}
					}
					
					try {
						methods[i].invoke(listener, event);
					} catch (Exception e) {
						System.err.println(e);
					}
				}
			}
		}
		return event;
	}
}
