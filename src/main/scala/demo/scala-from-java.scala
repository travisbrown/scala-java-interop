package demo

trait CompanionlessTrait {
  def foo: String
}

trait CompanionlessTraitWithImpls {
  def bar: String = "bar"
  def baz: String
}

trait CompanionedTrait {
  def foo: String
  def qux: String = "qux"
}

object CompanionedTrait {
  def someInt: Int = 12345
}

object CompanionlessObject {
  def someString: String = "nothing"
}

abstract class CompanionedClass {
  def foo: String
  def qux: String = "qux"
}

object CompanionedClass {
  def someInt: Int = 12345
}

object DefaultArguments {
  def multiplyString(n: Int, s: String = "s"): String = s * n
}

object MultipleParamSections {
  def multiplyString(n: Int)(s: String): String = s * n
}

class Animal
class Dog extends Animal

class CovariantClass[+A]
class ContravariantClass[-A]

object OutOfLuck {
  def const = "Can't use from Java"
}

object ImplicitClasses {
  /**
   * Allows us to write `1.times(println("foo"))` with no runtime overhead.
   */
  implicit class RichInt(val i: Int) extends AnyVal {
    def times(f: => Unit) = (1 to i).foreach(_ => f)
  }
}