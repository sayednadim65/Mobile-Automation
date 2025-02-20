package pageobjects;
import java.io.FileWriter;
import java.io.IOException;

public class TableLogger {
    private StringBuilder table = new StringBuilder();
    
    public TableLogger() {
        // Add the header for the HTML table
        table.append("<html><body><table border='1'>")
             .append("<tr><th>Test Case Name</th><th>Status</th><th>Time Taken (ms)</th></tr>");
    }
    
    // Method to log the test results
    public void logTestResult(String testCaseName, String status, long timeTaken) {
        table.append("<tr>")
             .append("<td>").append(testCaseName).append("</td>")
             .append("<td>").append(status).append("</td>")
             .append("<td>").append(timeTaken).append("</td>")
             .append("</tr>");
    }
    
    // Method to save the table to an HTML file
    public void saveToFile(String filename) throws IOException {
        table.append("</table></body></html>");
        FileWriter writer = new FileWriter(filename);
        writer.write(table.toString());
        writer.close();
    }
}
