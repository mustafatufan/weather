package com.oneot.weather.model;

public enum TimeOfDay {
	DAY("day"), NIGHT("night");

	private String value;

	private TimeOfDay(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static TimeOfDay fromString(String value) {
		for (TimeOfDay time : TimeOfDay.values()) {
			if (time.value.equalsIgnoreCase(value)) {
				return time;
			}
		}
		return null;
	}
}
