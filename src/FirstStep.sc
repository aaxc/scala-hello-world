import java.io.File

class Director(val firstName: String,
               val lastName: String,
               val yearOfBirth: Int) {

  def name: String =
    s"$firstName $lastName"

  def copy(firstName: String = this.firstName,
           lastName: String = this.lastName,
           yearOfBirth: Int = this.yearOfBirth): Director =
    new Director(firstName, lastName, yearOfBirth)
}

class Film(val name: String,
           val yearOfRelease: Int,
           val imdbRating: Double,
           val director: Director) {

  def directorsAge: Int =
    yearOfRelease - director.yearOfBirth

  def isDirectedBy(director: Director) =
    this.director == director

  def copy(name: String = this.name,
           yearOfRelease: Int = this.yearOfRelease,
           imdbRating: Double = this.imdbRating,
           director: Director = this.director): Film =
    new Film(name, yearOfRelease, imdbRating, director)
}

object Director {
  def apply(firstName: String,
            lastName: String,
            yearOfBirth: Int): Director = {
    new Director(firstName, lastName, yearOfBirth)
  }

  def older(director1: Director,
            director2: Director
           ): Director = {
    if (director1.yearOfBirth - director2.yearOfBirth > 0) director1 else director2
  }
}

object Film {
  def apply(name: String,
            yearOfRelease: Int,
            imdbRating: Double,
            director: Director): Film = {
    new Film(name, yearOfRelease, imdbRating, director)
  }

  def highestRating(film1: Film, film2: Film) = {
    if (film1.imdbRating > film2.imdbRating) film1 else film2
  }

  def oldestDirectorAtTheTime(film1: Film, film2: Film): Director = {
    if (film1.directorsAge > film2.directorsAge) film1.director else film2.director
  }
}

