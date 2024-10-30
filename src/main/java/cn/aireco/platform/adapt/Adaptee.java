package cn.aireco.platform.adapt;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Adaptee {


    public Adaptee() {
        this.name = "111111";
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name = "Adaptee";

    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        System.out.println("Adaptee name: " + adaptee.name);
    }

    public void specificRequest() {
        System.out.println("Adaptee specific request");
    }
}
