package teach0419;

public class Abs implements A {
    @Override
    public void ooo() {
        System.out.println();
    }

    private String xxx() {
        return "";
    }

    private int aaa() {
        return 4;
    }
    // class：一定要實作
    // abstract：可以實作，也可以不實作
    // interface：不可以實作(jdk7)
//    public void xxx() {
//    }
//
////    public abstract void ooo();
//    public void ooo(){}
}


interface A {
    void ooo();
}
