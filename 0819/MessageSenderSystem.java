interface MessageSender {
    void send(String receiver, String message);
}

class EmailSender implements MessageSender {

    @Override
    public void send(String receiver, String message) {
        if (receiver == null || receiver.trim().isEmpty()) {
            System.out.println("Email 發送失敗：receiver 不可為空白");
            return;
        }

        if (message == null || message.trim().isEmpty()) {
            System.out.println("Email 發送失敗：message 不可為空白");
            return;
        }

        System.out.println("Email 發送給：" + receiver);
        System.out.println("內容：" + message);
    }
}

class SmsSender implements MessageSender {

    @Override
    public void send(String receiver, String message) {
        if (receiver == null || receiver.trim().isEmpty()) {
            System.out.println("SMS 發送失敗：receiver 不可為空白");
            return;
        }

        if (message == null || message.trim().isEmpty()) {
            System.out.println("SMS 發送失敗：message 不可為空白");
            return;
        }

        System.out.println("SMS 發送給：" + receiver);
        System.out.println("內容：" + message);
    }
}

class ConsoleSender implements MessageSender {

    @Override
    public void send(String receiver, String message) {
        if (receiver == null || receiver.trim().isEmpty()) {
            System.out.println("Console 發送失敗：receiver 不可為空白");
            return;
        }

        if (message == null || message.trim().isEmpty()) {
            System.out.println("Console 發送失敗：message 不可為空白");
            return;
        }

        System.out.println("Console 發送給：" + receiver);
        System.out.println("內容：" + message);
    }
}

public class MessageSenderSystem {

    public static void notify(
            MessageSender sender,
            String receiver,
            String message) {

        if (sender == null) {
            System.out.println("發送失敗：sender 不可為 null");
            return;
        }

        sender.send(receiver, message);
    }

    public static void main(String[] args) {

        MessageSender email = new EmailSender();
        MessageSender sms = new SmsSender();
        MessageSender console = new ConsoleSender();

        notify(email, "user@gmail.com", "您好，這是 Email 訊息。");
        notify(sms, "0912345678", "您好，這是 SMS 訊息。");
        notify(console, "使用者", "您好，這是 Console 訊息。");

        notify(email, "   ", "測試訊息");
        notify(sms, "0912345678", "   ");

        MessageSender anotherSender = new ConsoleSender();
        notify(anotherSender, "TestUser", "新增 Sender 測試");
    }
}