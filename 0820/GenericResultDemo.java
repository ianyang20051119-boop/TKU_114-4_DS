class Result<T> {
    private boolean success;
    private String message;
    private T data;

    public Result(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}

public class GenericResultDemo {
    public static void main(String[] args) {

        // Result<String>
        Result<String> stringResult =
                new Result<>(true, "取得字串資料成功", "Hello Java");

        // 取出資料時不需要 cast
        String text = stringResult.getData();

        System.out.println("success: " + stringResult.isSuccess());
        System.out.println("message: " + stringResult.getMessage());
        System.out.println("data: " + text);

        System.out.println("--------------------");

        // Result<Integer>
        Result<Integer> integerResult =
                new Result<>(true, "取得整數資料成功", 100);

        // 取出資料時不需要 cast
        Integer number = integerResult.getData();

        System.out.println("success: " + integerResult.isSuccess());
        System.out.println("message: " + integerResult.getMessage());
        System.out.println("data: " + number);

        System.out.println("--------------------");

        // 失敗情況，data 為 null
        Result<String> failedResult =
                new Result<>(false, "資料讀取失敗", null);

        System.out.println("success: " + failedResult.isSuccess());
        System.out.println("message: " + failedResult.getMessage());

        // 正確處理 data == null
        if (failedResult.getData() == null) {
            System.out.println("data: 無資料");
        } else {
            System.out.println("data: " + failedResult.getData());
        }
    }
}