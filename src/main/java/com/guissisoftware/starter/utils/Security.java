package com.guissisoftware.starter.utils;

import java.util.List;

/**
 * @param enabled Enable Security. Possible values true/false
 * @param token Token Value
 * @param roles Available roles
 */
public record Security(boolean enabled, String token, List<String> roles) {

	@Override
	public String toString() {
		return "Security{" +
				"enabled=" + enabled +
				", token='" + token + '\'' +
				", roles=" + roles +
				'}';
	}
}
