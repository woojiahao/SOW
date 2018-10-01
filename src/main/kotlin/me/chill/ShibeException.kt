package me.chill

import java.lang.Exception

class ShibeException(err: String) : Exception(
	"\n\n\tException Occurred:\n" +
		"\tReason: $err\n"
)