
public class BetterNullPointerReports {
  public static void main(String[] args) {
    C[] ca = {
      new C(new B(new A(null))),
      new C(new B(null)),
      new C(null),
    };
    for(C c: ca) {
      try {
        System.out.println(c.b.a.s);
      } catch(NullPointerException npe) {
        System.out.println(npe);
      }
    }
  }
}
