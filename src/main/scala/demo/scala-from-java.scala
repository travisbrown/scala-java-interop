package demo

trait CompanionlessTrait {
  def foo: String
}

trait CompanionlessTraitWithImpls {
  println("We're in the CompanionlessTraitWithImpls constructor!")
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

object ObjectAsInstance extends CompanionedTrait {
  def foo: String = "foo"
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

/**
 * We can create Java-friendly overloads for vararg methods with the @varargs
 * annotation.
 */
object ScalaJavaVarargs {
  @scala.annotation.varargs
  def countStrings(strings: String*) = strings.size
}

/**
 * Java doesn't support multiple parameter sections, so what does this compile
 * to?
 */
object MultiVarargs {
  @scala.annotation.varargs
  def countStrings(strings: String*)(xs: Int*) = strings.size
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