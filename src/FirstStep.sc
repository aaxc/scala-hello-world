object FunctionsAndADTsHomework {
  import FunctionsAndADTsHomework.Car._
  import FunctionsAndADTsHomework.Csv._
  import FunctionsAndADTsHomework.ParsingFailure._
  import FunctionsAndADTsHomework.SortCriteria._

  /** CSV data consisting of individual lines.
   *
   * @param lines CSV data lines, do not contain header or footer lines
   */
  final case class Csv(lines: List[Line])

  object Csv {

    /** Zero-based CSV data line number. */
    final case class LineNumber(value: Int) extends AnyVal

    /** CSV data line. */
    final case class Line(value: String) extends AnyVal
  }

  /** Sort criteria.
   *
   * @param field field to sort according to
   * @param order sort order to use, string values are compared lexicographically
   */
  final case class SortCriteria(
                                 field: Field,
                                 order: Order,
                               )

  object SortCriteria {

    sealed trait Field

    object Field {
      final case object ByManufacturer extends Field

      final case object ByModel extends Field

      final case object ByProductionYear extends Field

      final case object ByLicensePlateNumber extends Field

      final case object ByEngineType extends Field
    }

    sealed trait Order

    object Order {
      final case object Ascending extends Order

      final case object Descending extends Order
    }
  }

  final case class Car(
                        manufacturer: Manufacturer,
                        model: Model,
                        productionYear: ProductionYear,
                        licensePlateNumber: LicensePlateNumber,
                        engineType: EngineType,
                      )

  object Car {

    /** Car manufacturer. */
    final case class Manufacturer(value: String) extends AnyVal

    object Manufacturer {

      /** Constructs [[Manufacturer]] from the string.
       * <p/>
       * The string must be between 2 and 100 characters, must not be blank.
       */
      def fromString(s: String): Option[Manufacturer] = ???
    }

    /** Car model. */
    final case class Model(value: String) extends AnyVal

    object Model {

      /** Constructs [[Model]] from the string.
       * <p/>
       * The string must be between 1 and 200 characters, must not be blank.
       */
      def fromString(s: String): Option[Model] = ???
    }

    /** Car production year. */
    final case class ProductionYear(value: Int) extends AnyVal

    object ProductionYear {

      /** Constructs [[ProductionYear]] from the string.
       * <p/>
       * The string must contain a number between 1886 and 2100.
       */
      def fromString(s: String): Option[ProductionYear] = ???
    }

    /** Car license plate number. */
    final case class LicensePlateNumber(value: String) extends AnyVal

    object LicensePlateNumber {

      /** Constructs [[LicensePlateNumber]] from the string.
       * <p/>
       * The string must contain 3 to 10 upper case letters and numbers.
       */
      def fromString(s: String): Option[LicensePlateNumber] = ???
    }

    /** Car engine type. */
    sealed trait EngineType {
      def value: String
    }

    object EngineType {
      final case object Petrol extends EngineType {
        override val value: String = "P"
      }

      final case object Diesel extends EngineType {
        override val value: String = "D"
      }

      final case object Hybrid extends EngineType {
        override val value: String = "H"
      }

      final case object Electric extends EngineType {
        override val value: String = "E"
      }

      /** Constructs [[EngineType]] from the string.
       * <p/>
       * The string must be one of the predefined values:
       * <ul>
       * <li>"p" or "P" for [[Petrol]];</li>
       * <li>"d" or "D" for [[Diesel]];</li>
       * <li>"h" or "H" for [[Hybrid]];</li>
       * <li>"e" or "E" for [[Electric]].</li>
       * </ul>
       */
      def fromString(s: String): Option[EngineType] = ???
    }
  }

  final case class ParsingFailure(lineNumber: LineNumber, reason: Reason)

  object ParsingFailure {

    /** Free-form reason for the parsing failure, must not be blank. */
    final case class Reason(value: String) extends AnyVal
  }

  /** Parsing result. Can be either a [[ParsingFailure]] or some success value. */
  type ParsingResult[A] = Either[ParsingFailure, A]

  object CarParser {

    /** Tries to parse one CSV data line into a [[Car]]. */
    def parse(line: Line, lineNumber: LineNumber): ParsingResult[Car] = {
      val bmw = Car(
        manufacturer = Manufacturer("BMW"),
        model = Model("i3"),
        productionYear = ProductionYear(2019),
        licensePlateNumber = LicensePlateNumber("AB1234"),
        engineType = EngineType.Electric,
      )

      val a: ParsingResult[Car] = Right(bmw)
      a
    }

    /** Tries to parse all CSV data sequentially into a list of [[Car Cars]]. */
//    def parseCsv(csv: Csv): List[ParsingResult[Car]] = {
    def parseCsv(csv: Csv) = {
      val k = 0
      val res: List[ParsingResult[Car]] = for {
        line <- csv.lines
        loop = k + 1
      } parse(line, LineNumber(loop))

      res
    }
  }

  object CarSorter {

    /** Sorts the list of [[Car Cars]] according to the [[SortCriteria]]. */
    def sort(cars: List[Car], sortCriteria: SortCriteria): List[Car] = ???
  }

  object CarProcessor {

    /** Tries to parse CSV data into a list of [[Car Cars]] and sort them.
     * <p/>
     * If there are [[ParsingFailure ParsingFailures]], combines all of them into a list and returns it.
     * In this case the list should be sorted by [[LineNumber]] in ascending order.
     * <p/>
     * If there are no [[ParsingFailure ParsingFailures]], returns back the list of [[Car Cars]], sorted
     * according to the [[SortCriteria]].
     */
    //    def process(sortCriteria: SortCriteria)(csv: Csv): Either[List[ParsingFailure], List[Car]] = {
    def process(sortCriteria: SortCriteria)(csv: Csv) = {
      val carParser = CarParser.parseCsv(csv)
    }
  }
}

import FunctionsAndADTsHomework.Car._
import FunctionsAndADTsHomework.Csv._
import FunctionsAndADTsHomework.ParsingFailure._
import FunctionsAndADTsHomework.{CarProcessor, Csv, ParsingResult, SortCriteria}
import FunctionsAndADTsHomework.SortCriteria._


val file = new Csv(List(
  Line("BMW,i3,2019,AB1234,E"),
  Line("BMW,i3,2019,AB1234"),
  Line("Lada,2011,,DE5432,Z"),
  Line("Volkswagen,Beetle,2011,DE5432,P"),
  Line("Volkswagen,Beetle,1,DE5432,X"),
))

val sort = SortCriteria(Field.ByManufacturer, Order.Descending)
val proc = CarProcessor.process(sort)(file)
