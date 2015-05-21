package demo;

import com.twitter.util.Function;
import static com.twitter.util.javainterop.Scala.*;
import scala.collection.JavaConversions;
import scala.collection.Seq;
import scala.collection.Traversable;

public class UsingScala {
  /**
   * For Scala objects without companions, referring to members is easy.
   */
  String someString = CompanionlessObject.someString();

  /**
   * Scala objects with class companions are the same.
   */
  int someInt1 = CompanionedClass.someInt();

  /**
   * Scala objects with trait companions aren't.
   */
  int someInt2 = CompanionedTrait$.MODULE$.someInt();

  /**
   * Methods with default arguments in Scala require all arguments to be
   * provided explicitly.
   */
  String twice = DefaultArguments.multiplyString(2, "s");

  /**
   * If we want the default, we have to rely on an undocumented synthetic
   * method (don't do this).
   */
  String twiceWithDefault = DefaultArguments.multiplyString(
    2,
    DefaultArguments.multiplyString$default$2()
  );

  /**
   *
   */
  String thrice = MultipleParamSections.multiplyString(3, "s");

  /**
   * In Scala this would be `"foo" :: Nil`, but we have to mangle the operator
   * name. Also note that we can't use the `scala.List` type alias.
   */
  scala.collection.immutable.List<String> strings =
    scala.collection.immutable.Nil.<String>$colon$colon("foo");

  /**
   * In Scala this would be `"foo" :: Nil`, but we have to mangle the operator
   * name. Also note that we can't use the `scala.List` type alias.
   */
  scala.collection.immutable.List<String> stringList =
    scala.collection.immutable.Nil.<String>$colon$colon("foo");

  /**
   * Ugh, implicits. In Scala this would be `strings.flatten`.
   */
  public static scala.collection.GenTraversableOnce<String> flattenSeq(Seq<Seq<String>> strings) {
    return strings.<String>flatten(
      scala.Predef.<Seq<String>>conforms().andThen(
        new Function<
          Seq<String>,
          scala.collection.GenTraversableOnce<String>
        >() {
          public scala.collection.GenTraversableOnce<String> apply(Seq<String> seq) {
            return seq;
          }
        }
      )
    );
  }

  /**
   * Java can't tell that `String => List[String]` is a subtype of
   * `String => Seq[String]`.
   */
  scala.Function1<String, Seq<String>> fromListFunc(
    scala.Function1<String, scala.collection.immutable.List<String>> values
  ) {
    //return values;
    return null;
  }

  /**
   * More generally, Java just doesn't get contravariance.
   */
  ContravariantClass<Dog> fromAnimal(ContravariantClass<Animal> cca) {
    return null;
    //return cca;
  }

  /**
   * Or covariance.
   */
  CovariantClass<Animal> fromDog(CovariantClass<Dog> ccd) {
    return null;
    //return ccd;
  }

  /**
   * This doesn't work!
   */
  /*public Traversable<String> moreThanThreeChars(Seq<String> xs) {
    return xs.filter(
      new com.twitter.util.Function<String, scala.Boolean>() {
        public scala.Boolean apply(String x) {
          return scala.Predef.Boolean2boolean(x.length() > 3);
        }
      }
    );
  }*/

  /**
   * Instead we have to use `Object` for the primitive.
   */
  public Traversable<String> moreThanThreeChars(Seq<String> xs) {
    return xs.filter(
      new com.twitter.util.Function<String, Object>() {
        public Object apply(String x) {
          return x.length() > 3;
        }
      }
    );
  }

  /**
   * Converting from Scala to Java.
   */
  java.util.List<String> fromScalaList(Seq<String> strings) {
    return JavaConversions.seqAsJavaList(strings);
  }

  /**
   * Converting from Java to a Scala mutable collection.
   */
  scala.collection.mutable.Buffer<String> toScalaBuffer(java.util.List<String> strings) {
    return JavaConversions.asScalaBuffer(strings);
  }

  /**
   * Converting from Java to a Scala mutable collection.
   */
  Seq<String> toScalaImmutableSeq(java.util.List<String> strings) {
    return asImmutableSeq(strings);
  }

  /**
   * Yay! Runtime exceptions!
   */
  public static ImplicitClasses.RichInt rich4() { return new ImplicitClasses.RichInt(4); }
}