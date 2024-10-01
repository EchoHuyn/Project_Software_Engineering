package view;

import controller.BillController;
import controller.ProductController;
import dao.BillDao;
import entity.Bill;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class MenuView extends JFrame {

    public MenuView() {
        setTitle("Main Menu");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Color.BLUE);

        // Load logo image from resources
        JLabel logoLabel = new JLabel();
        URL logoUrl = getClass().getClassLoader().getResource("images/logo.jpg");
        if (logoUrl != null) {
            logoLabel = new JLabel(new ImageIcon(logoUrl));
        } else {
            System.out.println("Logo not found!");
        }

        JLabel titleLabel = new JLabel("SNEAKER MANAGEMENT SYSTEM");
        titleLabel.setForeground(Color.WHITE);
        logoPanel.add(logoLabel);
        logoPanel.add(titleLabel);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(3, 1));

        JButton statisticsButton = createMenuButton("Statistics", "images/thongke.png");
        statisticsButton.addActionListener(e -> {
            dispose();
            BillDao billDao = new BillDao();
            java.util.List<Bill> bills = billDao.getListBills();
            StatisticsView statis = new StatisticsView(bills);
            statis.setVisible(true);
        });

        JButton productManagementButton = createMenuButton("Product Management", "images/product.jpg");
        JButton paymentManagementButton = createMenuButton("Payment Management", "images/hoadon.jpg");

        productManagementButton.addActionListener(e -> {
            dispose();
            ProductView productView = new ProductView();
            ProductController productController = new ProductController(productView);
            productController.showProductView();
        });

        paymentManagementButton.addActionListener(e -> {
            dispose();
            BillView billView = new BillView();
            BillController billController = new BillController(billView);
            billController.showBillView();
        });

        menuPanel.add(statisticsButton);
        menuPanel.add(productManagementButton);
        menuPanel.add(paymentManagementButton);

        add(logoPanel, BorderLayout.NORTH);
        add(menuPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createMenuButton(String buttonText, String imagePath) {
        JButton button = new JButton();
        button.setText(buttonText);

        // Load image from resources
        URL imageUrl = getClass().getClassLoader().getResource(imagePath);
        if (imageUrl != null) {
            ImageIcon origin = new ImageIcon(imageUrl);
            Image originImage = origin.getImage();
            Image scaledImage = originImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaledImage);
            button.setIcon(icon);
        } else {
            System.out.println("Image not found: " + imagePath);
        }

        styleButton(button);
        return button;
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLUE);
        // Thêm bất kỳ tùy chỉnh style nào khác ở đây
    }
}
