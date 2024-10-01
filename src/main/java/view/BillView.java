package view;

import dao.BillDao;
import dao.ProductDao;
import entity.Bill;
import entity.Product;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class BillView extends JFrame implements ActionListener {
   private JTextField customerNameField;
    private JComboBox<String> productComboBox;
    private JTextField quantityField;
    private JTextField totalField;
    private JButton submitButton;
    private JButton backButton;
    private JSpinner dateSpinner;
    
    private String[] columnNames = new String[]{
        "ID", "CustomerName", "ProductName", "Quality", "Price","Day"
    };

    private JTable billTable;
    private JScrollPane scrollPane;

    public BillView() {
        setTitle("Create Bill");
        setSize(600, 400); // Adjusted size to accommodate the table
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initComponents();
        initTable();
    }

    private void initComponents() {
        // Using BorderLayout for better arrangement
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Customer Name Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Customer Name:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        customerNameField = new JTextField();
        customerNameField.setColumns(20);
        formPanel.add(customerNameField, gbc);

        // Product Combo Box
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Product:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        productComboBox = new JComboBox<>();
        formPanel.add(productComboBox, gbc);

        // Quantity Field
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Quantity:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        quantityField = new JTextField();
        quantityField.setColumns(20);
        formPanel.add(quantityField, gbc);

        // Date Spinner
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Đặt gridwidth để nó trải rộng qua cả hai cột
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        datePanel.add(new JLabel("Day:"));
        dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);
        datePanel.add(dateSpinner);
        formPanel.add(datePanel, gbc);

        // Nút Submit và Nút Back
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1; // Reset gridwidth để không ảnh hưởng đến các thành phần sau
        submitButton = new JButton("Create bill");
        submitButton.addActionListener(this);
        formPanel.add(submitButton, gbc);

        gbc.gridx = 1;
        backButton = new JButton("Return");
        backButton.addActionListener(this);
        formPanel.add(backButton, gbc);

        add(formPanel, BorderLayout.NORTH);
    }

    private void initTable() {
        billTable = new JTable();
        scrollPane = new JScrollPane(billTable);
        add(scrollPane, BorderLayout.CENTER); // Add scroll pane with table to the center
    }

    public void showListBills(java.util.List<Bill> list) {
        int size = list.size();
        Object[][] bills = new Object[size][6];
        for (int i = 0; i < size; i++) {
            bills[i][0] = list.get(i).getId();
            bills[i][1] = list.get(i).getCustomerName();
            bills[i][2] = list.get(i).getProductName();
            bills[i][3] = list.get(i).getQuality();
            bills[i][4] = list.get(i).getPrice();
            bills[i][5] = list.get(i).getDay();
        }
        billTable.setModel(new DefaultTableModel(bills, columnNames));
    }
    
    public void setListProducts(java.util.List<Product> list) {
        productComboBox.removeAllItems(); // Remove previous items if you want to reset the combo box
        for (Product product : list) {
            productComboBox.addItem(product.getName()); // Assuming Product has a getName() method
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            Bill billAdd = new Bill();
            billAdd.setCustomerName(customerNameField.getText());
            String selectedProduct = (String) productComboBox.getSelectedItem();
            billAdd.setProductName(selectedProduct);      
            ProductDao productDao = new ProductDao();
            java.util.List<Product> productList = productDao.getListProducts();
            int price = 0;
               for (Product product : productList) {
                   if (product.getName().equals(selectedProduct)) {
                       price = product.getPrice();
                   }
               }
            
            try {
                int quantity = Integer.parseInt(quantityField.getText());
                int total = price * quantity;

                billAdd.setQuality(quantity);
                billAdd.setPrice(total);
                Date date = (Date) dateSpinner.getValue();
                Instant instant = date.toInstant();
                ZoneId zoneId = ZoneId.systemDefault();
                LocalDate localDate = instant.atZone(zoneId).toLocalDate();
                billAdd.setDay(localDate);
            } catch (NumberFormatException ex) {
                // Handle parsing error
                JOptionPane.showMessageDialog(this, "Số lượng và giá tiền phải là dạng số", 
                                              "Input Error", JOptionPane.ERROR_MESSAGE);
                return; // Exit the method without adding the bill
            }
            
            BillDao billDao = new BillDao();
            billDao.add(billAdd);
            refreshTable();

        } else if (e.getSource() == backButton) {
            backToMenu();
        }
    }

    private void backToMenu() {
        this.setVisible(false); // Hide the current view
        MenuView menuView = new MenuView(); // Assuming you have a MenuView class
        menuView.setVisible(true); // Show the MenuView
    }
    
    private void refreshTable() {
        BillDao billDao = new BillDao();
        java.util.List<Bill> allBills = billDao.getListBills(); // Assuming this method exists

        DefaultTableModel model = (DefaultTableModel) billTable.getModel();
        model.setRowCount(0); // Clear existing data

        // Populate table with updated list of bills
        for (Bill bill : allBills) {
            model.addRow(new Object[] { bill.getId(), bill.getCustomerName(), 
                                        bill.getProductName(), bill.getQuality(), 
                                        bill.getPrice(), bill.getDay()});
        }
    }
}
