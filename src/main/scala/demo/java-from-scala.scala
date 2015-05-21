package demo

/**
 * It's common to rename Java classes in imports for convenience.
 */
import java.util.{List => jList}

/**
 * Support for easy Java getter and setter definition.
 */
import scala.beans.BeanProperty

/**
 * Note that we prefer `JavaConverters` over `JavaConversions` when working from
 * Scala.
 */
import scala.collection.JavaConverters._

/**
 * Note that we use "extends" even though we're implementing an interface.
 */
class JavaInterface1 extends JavaInterface {
  /**
   * Syntax is different for varargs, but otherwise it works just fine.
   */
  def foo(x: Int, strings: String*): Unit = {
    strings.foreach(println)
  }

  /**
   * Scala has keywords that Java doesn't, but you can escape them with
   * backticks.
   */
  def `forSome`(a: Int): String = "foo"
}

/**
 * Note that we can extend a Java class with a trait.
 */
trait JavaClass2 extends JavaClass {
  protected def someStuff: jList[_] = Nil.asJava
}

/**
 * Static field and methods aren't in scope automatically.
 */
class JavaStatics1 extends JavaStatics {
  def scalaInstanceFoo = JavaStatics.staticFoo
}

/**
 * Partial implementation.
 */
trait JavaInterface2 extends JavaInterface {
  def foo(x: Int, strings: String*): Unit = {
    strings.foreach(println)
  }
}

class JavaClass1 extends JavaClass {
  /**
   * We can implement a method with a val.
   */
  val someStrings: jList[String] = Seq("foo", "bar").asJava

  /**
   * Scala doesn't have raw generic types, so we use an existential type.
   */
  protected def someStuff: jList[_] = Seq("foo", "bar").asJava
}

/**
 * Not even for Scala objects.
 */
object JavaStatics2 extends JavaStatics {
  def scalaObjectFoo = JavaStatics.staticFoo
}

/**
 * Getters and setter will be defined automatically (in addition to the Scala
 * ones).
 */
class JavaBean1(
  @BeanProperty var name: String,
  @BeanProperty val age: Int
) extends JavaBean

/**
 * We can extends a generic type with an upper bound.
 */
class JavaGenerics1 extends JavaGenerics[JavaClass1] {
  def getJavaClass = new JavaClass1
}

/**
 * This was broken in Scala for a long time, but now it works.
 */
class JavaInnerClasses1 extends JavaInnerClasses {
  import JavaInnerClasses.InsideThingy

  protected def getThingy: InsideThingy = new InsideThingy();
}
