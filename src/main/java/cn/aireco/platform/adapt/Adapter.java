package cn.aireco.platform.adapt;

public class Adapter implements Target{

    private final Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request(Object obj) {
        if (obj instanceof Demo) {
            adaptee.specificRequest();
        }else {
            System.out.println("demo1");
        }
    }
}
