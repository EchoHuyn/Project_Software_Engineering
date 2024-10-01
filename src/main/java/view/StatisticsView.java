/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import entity.Bill;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StatisticsView extends JFrame {
    private List<Bill> bills; // Giả sử bạn có một danh sách bills

    private JTable table;
    private JComboBox<String> timeFilterComboBox;

    public StatisticsView(List<Bill> bills) {
        this.bills = bills;

        initComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        // Tạo panel chứa các thành phần
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

               datePanel.add(new JLabel("Day:"));

               SpinnerDateModel spinnerDateModel = new SpinnerDateModel();
        JSpinner dateSpinner = new JSpinner(spinnerDateModel);

               JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
               dateSpinner.setEditor(dateEditor);

               datePanel.add(dateSpinner);

               panel.add(datePanel);
        // Thêm combobox cho lựa chọn thời gian
       
       
        
         JButton calculateButton1 = new JButton("Sort By Day");
        calculateButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date selectedDate = (Date) dateSpinner.getValue();
        
                    // Chuyển đổi từ Date sang LocalDate (nếu bạn đã thay đổi Bill để sử dụng LocalDate)
                    LocalDate selectedLocalDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                    // Xử lý thống kê theo ngày được chọn
                    calculateStatisticsByDate(selectedLocalDate);
            }
        });
        panel.add(calculateButton1);
        
        // Thêm nút để thực hiện thống kê
        JButton calculateButton = new JButton("All");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateStatistics();
            }
        });
        panel.add(calculateButton);
        
            JButton calculateButton2 = new JButton("Return");
        calculateButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                        dispose();

                MenuView menuView = new MenuView(); // Assuming you have a MenuView class
                menuView.setVisible(true); // Show the MenuView
            }
        });
        panel.add(calculateButton2);

        add(panel, BorderLayout.NORTH);

        // Tạo bảng để hiển thị kết quả thống kê
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
private void calculateStatisticsByDate(LocalDate selectedDate) {
    // Tính toán thống kê dựa trên ngày được chọn
    List<Bill> filteredBills = filterBillsByDate(selectedDate);
    int totalBills = filteredBills.size();
    int totalRevenue = calculateTotalRevenue(filteredBills);
        int totalProd = calculateTotalCus(filteredBills);

    // Hiển thị kết quả trong bảng
    displayStatistics(totalBills, totalRevenue,totalProd);
}

private List<Bill> filterBillsByDate(LocalDate selectedDate) {
    List<Bill> filteredBills = new ArrayList<>();

    for (Bill bill : bills) {
        LocalDate billDate = bill.getDay();

        // Kiểm tra nếu ngày của hóa đơn trùng khớp với ngày được chọn
        if (billDate.isEqual(selectedDate)) {
            filteredBills.add(bill);
        }
    }

    return filteredBills;
}
    private void calculateStatistics() {
        // Lấy giá trị được chọn từ combobox
        String selectedTimeFilter = "All";

        // Tính toán thống kê dựa trên thời gian được chọn
        List<Bill> filteredBills = filterBillsByTime(selectedTimeFilter);
        int totalBills = filteredBills.size();
        int totalRevenue = calculateTotalRevenue(filteredBills);
        int totalProd = calculateTotalCus(filteredBills);
        // Hiển thị kết quả trong bảng
        displayStatistics(totalBills, totalRevenue,totalProd);
    }

    private List<Bill> filterBillsByTime(String timeFilter) {
        // Thực hiện lọc danh sách bills theo thời gian được chọn
        // Ở đây, giả sử bạn có phương thức để lọc theo ngày (LocalDate)
        // Nếu bạn có cách lưu trữ ngày tháng năm trong đối tượng Bill, bạn có thể sử dụng nó
        LocalDate today = LocalDate.now();
        List<Bill> filteredBills = new ArrayList<>();

        for (Bill bill : bills) {
            LocalDate billDate =(bill.getDay());
            switch (timeFilter) {
               
                case "All":
                    filteredBills.add(bill);
                    break;
                // Thêm các trường hợp lọc khác nếu cần
            }
        }

        return filteredBills;
    }

    private int calculateTotalRevenue(List<Bill> bills) {
        // Thực hiện tính tổng doanh thu từ danh sách bills
        int totalRevenue = 0;
        for (Bill bill : bills) {
            totalRevenue += bill.getPrice();
        }
        return totalRevenue;
    }
    
    private int calculateTotalCus(List<Bill> bills){
        int totalRevenue = 0;
        for (Bill bill : bills) {
            totalRevenue += bill.getQuality();
        }
        return totalRevenue;
    }

    private void displayStatistics(int totalBills, int totalRevenue, int totalProduct) {
        // Hiển thị kết quả trong bảng
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Statistical Information");
        model.addColumn("Value");
        model.addRow(new Object[]{"Total Bills", totalBills});
        model.addRow(new Object[]{"Total Revenue", totalRevenue});
        model.addRow(new Object[]{"Total Product", totalProduct});

        table.setModel(model);
    }

   
}
