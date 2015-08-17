package demo;

public abstract class JavaAccess {
  abstract String secretString();
  protected abstract String lessSecretString();
  public abstract String nonsecretString();
}

class JavaAccessImpl extends JavaAccess {
	public String secretString() {
		return "foo";
	}

	public String lessSecretString() {
		return "bar";
	}

	public String nonsecretString() {
		return "baz";
	}
}
