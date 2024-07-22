package WCTool;

public class DefaultSystemExit implements SystemExit {
    public void exit(int status) {
        System.exit(status);
    }
}
