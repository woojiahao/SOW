package me.chill

import khttp.get
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ShibeTest {
	@Test(expected = ShibeException::class)
	fun count_below_1_throws_exception() {
		Shibe().setCount(1000)
	}

	@Test(expected = ShibeException::class)
	fun count_above_100_throws_exception() {
		Shibe().setAnimal(Animal.Cat).setCount(-1)
	}

	@Test fun api_should_retrieve_right_amount() {
		assertEquals(5, Shibe().setAnimal(Animal.Bird).setCount(5).retrieve().size)
	}

	@Test fun disabling_urls_should_return_image_id_only() {
		Shibe()
			.setAnimal(Animal.Bird)
			.setCount(10)
			.setUrl(false)
			.retrieve()
			.map { "https://cdn.shibe.online/birds/$it.jpg" }
			.forEach { assertTrue(get(it).statusCode < 400) }
	}

	@Test fun disabling_https_should_return_http_urls_only() {
		Shibe()
			.setAnimal(Animal.Cat)
			.setCount(15)
			.setHttps(false)
			.retrieve()
			.forEach { assertFalse(it.startsWith("https")) }
	}
}