# SOW
Wrapper written in Kotlin for [https://shibe.online/](https://shibe.online/) to learn about the process of writing 
wrappers.

## Usage
Calls can be chained and if there are overlaps, the latest method will override every other same method call in the chain.

```kotlin
val shibes = Shibe().setCount(5).retrieve()
shibes.forEach { println(it) }
```

### Properties
#### setAnimal(Animal)
Sets the animal to retrieve, defaults to **Shibe** if nothing is chosen. Animal chosen from the **Animal** enumeration:

1. Shibe
2. Cat
3. Bird

#### setCount(Int)
Sets the number of entries to retrieve, defaults to **1** if nothing is chosen. Must be greater than **0** and caps at **100**.

#### setUrl(Boolean)
Sets the format that the Strings in the returned array will be formatted in, defaults to **true** if nothing is chosen.

* **true** - Full urls will be returned
* **false** - Only the image id of each image retrieved will be returned

#### setHttps(Boolean)
Sets the type of Url returned, determining if it will be using the HTTPS protocol or HTTP protocol, defaults to HTTPS is nothing is chosen.

* **true** - HTTPS urls
* **false** - HTTP url

## Examples
### Untouched Retrieve
```kotlin
val shibe = Shibe().retrieve()
println(shibe[0]) // Retrieves 1 Shibe's full URL in HTTPS
```

### Retrieving 100 Cats
```kotlin
val cats = Shibe().setAnimal(Animal.Cat).setCount(100).retrieve()
cats.forEach { println(it) }
```

### Retrieving Only Ids
```kotlin
val birdIds = Shibe().setAnimal(Animal.Bird).setCount(40).setUrl(false).retrieve()
birdIds.forEach { println(it) }
```
