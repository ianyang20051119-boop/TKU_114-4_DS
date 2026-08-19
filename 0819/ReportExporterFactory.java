interface ReportExporter {
    void exportReport(String title, int[] values);
}

class CsvExporter implements ReportExporter {

    @Override
    public void exportReport(String title, int[] values) {
        System.out.println("CSV");
        System.out.println("Title," + title);

        if (values == null) {
            System.out.println("Values,");
            return;
        }

        System.out.print("Values");
        for (int value : values) {
            System.out.print("," + value);
        }
        System.out.println();
    }
}

class JsonExporter implements ReportExporter {

    @Override
    public void exportReport(String title, int[] values) {
        System.out.println("JSON");

        System.out.print("{\"title\":\"" + title + "\",\"values\":[");

        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                if (i > 0) {
                    System.out.print(",");
                }
                System.out.print(values[i]);
            }
        }

        System.out.println("]}");
    }
}

class TextExporter implements ReportExporter {

    @Override
    public void exportReport(String title, int[] values) {
        System.out.println("TEXT");
        System.out.println("Title: " + title);

        if (values == null) {
            System.out.println("Values: ");
            return;
        }

        System.out.print("Values: ");

        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(values[i]);
        }

        System.out.println();
    }
}

public class ReportExporterFactory {

    public static ReportExporter createExporter(String format) {
        if (format == null) {
            return new TextExporter();
        }

        switch (format.toLowerCase()) {
            case "csv":
                return new CsvExporter();

            case "json":
                return new JsonExporter();

            case "text":
                return new TextExporter();

            default:
                return new TextExporter();
        }
    }

    public static void exportReport(
            ReportExporter exporter,
            String title,
            int[] values) {

        if (exporter == null) {
            return;
        }

        exporter.exportReport(title, values);
    }

    public static void main(String[] args) {

        int[] values = {10, 20, 30, 40};

        ReportExporter csv = createExporter("csv");
        ReportExporter json = createExporter("json");
        ReportExporter text = createExporter("text");
        ReportExporter unknown = createExporter("xml");

        exportReport(csv, "Sales Report", values);
        System.out.println();

        exportReport(json, "Sales Report", values);
        System.out.println();

        exportReport(text, "Sales Report", values);
        System.out.println();

        exportReport(unknown, "Unknown Format", values);
        System.out.println();

        exportReport(createExporter("csv"), "Null Test", null);
    }
}