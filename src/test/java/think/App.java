package think;

//: initialization/App.java
// Calling constructors with "this"
public class App {
    public App() {
        this(9);
    }

    public App(int i) {
        System.out.println(i);
    }

    public static void main(String[] args){
        new App();
    }
}






















