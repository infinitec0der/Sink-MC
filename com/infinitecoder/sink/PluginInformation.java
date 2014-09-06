package com.infinitecoder.sink;

public class PluginInformation {
	
	private String name;
	private String version;
	private String[] authors;
	
	public PluginInformation(String name, String version, String[] authors) {
		this.name = name;
		this.version = version;
		this.authors = authors;
	}
	
	public PluginInformation(String name, String version, String author) {
		this(name, version, new String[]{author});
	}

	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}
	
	public String getPrimaryAuthor() {
		return getAuthors().length == 0 ? "None" : getAuthors()[0];
	}
	
	public String[] getAuthors() {
		return authors;
	}
	
}
