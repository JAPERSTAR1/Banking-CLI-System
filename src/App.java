public class App {
    static Bank access = Control.askForAccess();
    public static void main(String[] args) {
        if (access instanceof Employee) {
            if (Login.login()) {
                Control.Checker();

            }

        } else {
            if (Login.login()) {
                Control.Checker();

            }
        }

    }
}
