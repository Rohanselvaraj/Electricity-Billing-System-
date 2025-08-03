import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class MainPage {

    public static void main(String[] args) {
        // ✅ Ensure SQLite table is created
        DBHandler.initializeDatabase();

        JFrame frame = new JFrame("Electricity Billing System");
        frame.setSize(400, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(8, 1, 10, 10)); // Increased rows to 8

        JLabel unitsLabel = new JLabel("Enter Units Consumed:");
        JTextField unitsField = new JTextField();

        JButton calculateButton = new JButton("Calculate Bill");
        JButton saveButton = new JButton("Save Bill");
        JButton viewHistoryButton = new JButton("View History");
        JButton chartButton = new JButton("Show Usage Chart");
        JButton exportPdfButton = new JButton("Export History to PDF"); // ✅ Added export button

        JLabel resultLabel = new JLabel("Bill Amount: ₹ 0.0");

        frame.add(unitsLabel);
        frame.add(unitsField);
        frame.add(calculateButton);
        frame.add(saveButton);
        frame.add(viewHistoryButton);
        frame.add(chartButton);
        frame.add(exportPdfButton); // ✅ Added export button to frame
        frame.add(resultLabel);

        // 💡 Calculate Bill
        calculateButton.addActionListener(e -> {
            try {
                int units = Integer.parseInt(unitsField.getText());
                double amount = BillCalculator.calculateBill(units);
                resultLabel.setText("Bill Amount: ₹ " + amount);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid units!");
            }
        });

        // 💡 Save Bill (to SQLite)
        saveButton.addActionListener(e -> {
            try {
                int units = Integer.parseInt(unitsField.getText());
                double amount = BillCalculator.calculateBill(units);
                DBHandler.saveBill(units, amount); // ✅ Using SQLite
                JOptionPane.showMessageDialog(frame, "Bill saved successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error saving bill: " + ex.getMessage());
            }
        });

        // 💡 View History (from SQLite)
        viewHistoryButton.addActionListener(e -> {
            String history = DBHandler.getHistory(); // ✅ Using SQLite
            JTextArea textArea = new JTextArea(history);
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(400, 200));
            JOptionPane.showMessageDialog(frame, scrollPane, "Billing History", JOptionPane.INFORMATION_MESSAGE);
        });

        // 💡 Show Usage Chart
        chartButton.addActionListener(e -> {
            Map<String, Integer> usageData = DBHandler.getUsageData(); // ✅ Using SQLite
            UsageChart.showUsageChart(usageData);
        });

        // 💡 Export to PDF
        exportPdfButton.addActionListener(e -> {
            try {
                PDFExporter.exportHistoryToPDF(DBHandler.getHistory());
                JOptionPane.showMessageDialog(frame, "PDF exported successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Failed to export PDF: " + ex.getMessage());
            }
        });

        frame.setVisible(true);
    }
}
