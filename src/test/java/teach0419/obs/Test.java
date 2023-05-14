package teach0419.obs;

import java.util.ArrayList;
import java.util.List;

public class Test {
    MySubject m = new MySubject();
    private int id;
    private void xx(MySubject n){}

    private void xxxx(){
        System.out.println("dfsfsdf");
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("c");
        list.add("a");
        list.add("b");
        System.out.println(list.indexOf("d"));
    }
}



class A implements MyObserver {
    @Override
    public void update(String str) {
        System.out.println("a " + str);
    }
}

class B implements MyObserver {
    @Override
    public void update(String str) {
        System.out.println("b " + str);
    }
}
