# ⚡ Electricity Billing System (Java - Swing + SQLite)

This repository contains the **complete Java source code** for an Electricity Billing System built using Java Swing for the GUI and SQLite for data storage. The project allows users to calculate electricity bills, view billing history, search records, and prepare for PDF export.

## 📌 Features
- 💡 User-friendly GUI built with Swing
- 🔢 Bill calculation based on units input
- 🧾 History of previous bills stored in SQLite
- 🔍 Search functionality (by date, units, or amount)
- 📄 PDF Export support using iText (integrated in GUI)

## 🧰 Technologies Used
- Java 17
- Swing (GUI)
- SQLite (via JDBC)
- iText (for PDF generation)

## 📁 Project Structure
ElectricityBillingSystem/
├── src/ # Java source files (MainPage.java, BillCalculator.java, etc.)
├── ElectricityBillingSystem.db # SQLite database file (auto-created if not found)
├── icon/ # Optional icons (if used)
└── lib/ # External libraries like sqlite-jdbc and itextpdf (if not using Maven)

markdown
Copy
Edit

## 🚀 How to Run
1. Open the project in **IntelliJ IDEA** or any Java IDE.
2. Make sure the required `.jar` libraries (`sqlite-jdbc`, `itextpdf`) are added to your project.
3. Run `MainPage.java` to launch the app.
4. The database file will be created automatically in the project directory.

## 📦 Dependencies
- [`sqlite-jdbc`](https://github.com/xerial/sqlite-jdbc)
- [`iText (v2.x)`](https://github.com/itext/itextpdf) (for PDF export)

> ⚠️ Note: This repo contains **only source code**. The `.exe` file is not included.

---

👨‍💻 Author: [Rohan S](#)  
📅 Created: August 2025  
📫 Feel free to fork and modify the project!
