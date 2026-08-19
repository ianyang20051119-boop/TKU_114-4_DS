interface Exportable {
    void export(String fileName);
}

interface Compressible {
    void compress();
}

class BackupDocument implements Exportable, Compressible {

    @Override
    public void export(String fileName) {
        System.out.println("匯出文件：" + fileName);
    }

    @Override
    public void compress() {
        System.out.println("壓縮文件完成");
    }
}

public class DocumentCapabilityDemo {

    public static void main(String[] args) {

        BackupDocument document = new BackupDocument();

        Exportable exportable = document;
        Compressible compressible = document;

        exportable.export("backup.pdf");
        compressible.compress();

        System.out.println("兩個 reference 指向同一個物件");
        System.out.println("Exportable reference 可見 export()");
        System.out.println("Compressible reference 可見 compress()");
    }
}