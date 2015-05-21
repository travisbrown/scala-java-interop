package demo;

public abstract class JavaInnerClasses {
  protected static class InsideThingy {
    String foo() {
      return "whatever";
    }
  }

  protected abstract InsideThingy getThingy();
}