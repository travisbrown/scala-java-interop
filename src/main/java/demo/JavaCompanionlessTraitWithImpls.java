package demo;

public class JavaCompanionlessTraitWithImpls
  implements CompanionlessTraitWithImpls {

  /**
   * We have to provide an implementation even though it's implemented in the
   * trait.
   */
  public String bar() {
    /**
     * If we want consistency with the trait, we have to call the appropriate
     * static method on the "...$class" class.
     */
    return CompanionlessTraitWithImpls$class.bar(this);
  }

  public String baz() {
    return "baz";
  }
}
