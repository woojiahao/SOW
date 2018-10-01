package me.chill

import khttp.get

class Shibe {
	private var animal = Animal.Shibe
	private var count = 1
	private var urls = true
	private var https = true

	/**
	 * Set the animal to retrieve
	 *
	 * Defaults to Shibe
	 */
	fun setAnimal(animal: Animal): Shibe {
		this.animal = animal
		return this
	}

	/**
	 * Set the number of images you wish to retrieve
	 *
	 * Restrictions: Cannot fall below 1 or above 100
	 *
	 * Defaults to 1
	 */
	fun setCount(count: Int): Shibe {
		if (count < 1 || count > 100) throw ShibeException("Count cannot be less than 1 or exceed 100")
		this.count = count
		return this
	}

	/**
	 * Set whether the data returned will be a full URL or just the image ID
	 *
	 * Defaults to displaying full URLs
	 */
	fun setUrl(enabled: Boolean): Shibe {
		this.urls = enabled
		return this
	}

	/**
	 * Sets whether the url returned will be a HTTPS url or HTTP url
	 *
	 * Defaults to HTTPS
	 */
	fun setHttps(enabled: Boolean): Shibe {
		this.https = enabled
		return this
	}

	/**
	 * Returns an array of Strings corresponding to their requested parameters
	 */
	fun retrieve(): Array<String> {
		val endPoint = "http://shibe.online/api/${animal.name.toLowerCase()}s?count=$count&urls=$urls&httpsUrls=$https"

		val data = get(
			endPoint,
			headers = mapOf(
				"Content-Type" to "application/json",
				"User-Agent" to "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36"
			)
		)

		if (data.statusCode >= 400) throw ShibeException("Status Code Error: ${data.statusCode}")

		return data.jsonArray.map { it.toString() }.toTypedArray()
	}

}