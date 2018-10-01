package me.chill

import khttp.get

class Shibe(animal: Animal) {
	private val endPoint = StringBuilder("http://shibe.online/api/${animal.name.toLowerCase()}s?")
	private var count = 1
	private var urls = true
	private var https = true

	fun setCount(count: Int): Shibe {
		this.count = count
		return this
	}

	fun setUrl(enabled: Boolean): Shibe {
		this.urls = enabled
		return this
	}

	fun setHttps(enabled: Boolean): Shibe {
		this.https = enabled
		return this
	}

	fun retrieve(): Array<String> {
		endPoint.append("count=$count&urls=$urls&httpsUrls=$https")

		val data = get(
			endPoint.toString(),
			headers = mapOf(
				"Content-Type" to "application/json",
				"User-Agent" to "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36"
			)
		)

		if (data.statusCode >= 400) throw ShibeException("Status Code Error: ${data.statusCode}")

		return data.jsonArray.map { it.toString() }.toTypedArray()
	}

}