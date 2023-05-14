package teach0419.obs;

import java.util.ArrayList;

public class MySubject {
//    MyObserver[] m = new MyObserver[10];
    ArrayList<MyObserver> list = new ArrayList<>();

    public void register(MyObserver mo) {
        list.add(mo);
        // System.out.println(list);
    }

    public void remove(MyObserver mo) {
        list.remove(mo);
    }

    public void notify(String str) {
        for (int i = 0; i < list.size(); i++) {
            MyObserver mo = list.get(i);
            mo.update(str);
        }

//        for (MyObserver mo : list) {
//            mo.update(str);
//        }

//        list.forEach(c -> c.update(str));
    }
}
