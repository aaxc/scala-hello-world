import java.io.File

case class Director(firstName: String, lastName: String, yearOfBirth: Int)

object Director {
  def older(director1: Director, director2: Director): Director = {
    if (director1.yearOfBirth - director2.yearOfBirth > 0) director1 else director2
  }
}

case class Film(name: String, yearOfRelease: Int, imdbRating: Double, director: Director) {
  def directorsAge: Int = yearOfRelease - director.yearOfBirth
  def isDirectedBy(director: Director) = this.director == director
}

object Film {
  def highestRating(film1: Film, film2: Film) = {
    if (film1.imdbRating > film2.imdbRating) film1 else film2
  }

  def oldestDirectorAtTheTime(film1: Film, film2: Film): Director = {
    if (film1.directorsAge > film2.directorsAge) film1.director else film2.director
  }
}

case class Counter(count: Int = 0) {
  def dec = copy(count = count - 1)
  def inc = copy(count = count + 1)
}

Counter(0) // construct objects without `new`
// res16: Counter = Counter(0)

Counter().inc // printout shows the value of `count`
// res17: Counter = Counter(1)

Counter().inc.dec == Counter().dec.inc // semantic equality check
// res18: Boolean = true

case class Person(firstName: String, lastName: String) {
  def name = firstName + " " + lastName
}

object Person {
  def apply(name: String): Person = {
    val parts = name.split(" ")
    apply(parts(0), parts(1))
  }
}
