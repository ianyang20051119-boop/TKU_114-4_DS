import java.util.ArrayDeque;
import java.util.Deque;

public class BrowserBackStack {

    private Deque<String> history = new ArrayDeque<>();

    public void visit(String url) {
        if (url != null && !url.isEmpty()) {
            history.push(url);
        }
    }

    public String back() {
        if (!history.isEmpty()) {
            history.pop();
        }

        return current();
    }

    public String current() {
        if (history.isEmpty()) {
            return null;
        }

        return history.peek();
    }

    public static void main(String[] args) {
        BrowserBackStack browser = new BrowserBackStack();

        browser.visit("https://google.com");
        System.out.println("目前頁面：" + browser.current());

        browser.visit("https://youtube.com");
        System.out.println("目前頁面：" + browser.current());

        browser.visit("https://github.com");
        System.out.println("目前頁面：" + browser.current());

        System.out.println("返回後：" + browser.back());
        System.out.println("返回後：" + browser.back());

        System.out.println("目前頁面：" + browser.current());

        System.out.println("返回後：" + browser.back());
        System.out.println("再次返回：" + browser.back());
    }
}