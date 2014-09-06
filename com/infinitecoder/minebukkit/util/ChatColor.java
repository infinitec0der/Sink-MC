package com.infinitecoder.minebukkit.util;

public enum ChatColor {
	BLACK('0'),
	DARK_BLUE('1'),
	DARK_GREEN('2'),
	DARK_AQUA('3'),
	DARK_RED('4'),
	DARK_PURPLE('5'),
	GOLD('6'),
	GRAY('7'),
	DARK_GRAY('8'),
	BLUE('9'),
	GREEN('a'),
	AQUA('b'),
	RED('c'),
	LIGHT_PURPLE('d'),
	YELLOW('e'),
	WHITE('f'),
	MAGIC('k'),
	BOLD('l'),
	STRIKETHROUGH('m'),
	UNDERLINE('n'),
	ITALIC('o'),
	RESET('r');
	
	private char colorChar;
	private char colorCode;
	private String asString;
	
	private ChatColor(char colorCode) {
		this.colorCode = colorCode;
		this.colorChar = '§';
		this.asString = new String(new char[] {colorChar, colorCode});
	}
	
	public char getColorCode() {
		return colorCode;
	}
	
	public char getColorChar() {
		return colorChar;
	}
	
	public static String translateAlternateColorCodes(char symbol, String s) {
		for(ChatColor c : values()) {
			s = s.replaceAll("" + symbol + c.getColorCode(), "" + c.getColorChar() + c.getColorCode());
		}
		return s;
	}
	
	public static String stripColor(char symbol, String s) {
		for(ChatColor c : values()) {
			s = s.replaceAll("" + symbol + c.getColorCode(), "");
		}
		return s;
	}
	
	public static String stripColor(String s) {
		for(ChatColor c : values()) {
			s = s.replaceAll("" + c.getColorChar() + c.getColorCode(), "");
		}
		return s;
	}
	
	@Override
	public String toString() {
		return asString;
	}
	
}
