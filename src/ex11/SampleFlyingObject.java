package ex11;

public class SampleFlyingObject {
    public static void main(String[] args) {
        FlyingObject aPlane, aBird;
        Bird a = new Bird();

        aPlane = new Boeing787();
        aBird = new Swallow();

        aPlane.fly();
        aBird.fly();
        a.pick();
    }
}