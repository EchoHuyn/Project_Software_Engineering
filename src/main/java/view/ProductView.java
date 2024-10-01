package view;

import entity.Product;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ProductView extends JFrame implements ActionListener, ListSelectionListener {
    private static final long serialVersionUID = 1L;
    private JButton addProductBtn;
    private JButton editProductBtn;
    private JButton deleteProductBtn;
    private JButton clearBtn;
    private JButton sortProductPriceBtn, sortProductQualityBtn;
    private JButton sortProductNameBtn;
    private JScrollPane jScrollPaneProductTable;
    private JScrollPane jScrollPaneAddress;
    private JTable productTable;

    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel priceLable;
    private JLabel imageLabel;
    private JLabel qualityLabel;
    private JLabel searchLabel;

    private JLabel logoLabel;

    private JTextField idField;
    private JTextField nameField;
    private JTextField priceField;
    private JTextArea imageTa;
    private JTextField qualityField;
    private JTextField searchField;
    private JButton backButton;

    private String[] columnNames = new String[]{
        "ID", "Name", "Price", "Image", "Quantity"};

    private Object data = new Object[][]{};

    public ProductView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addProductBtn = new JButton("Add");
        editProductBtn = new JButton("Edit");
        deleteProductBtn = new JButton("Delete");
        clearBtn = new JButton("Clear");
        backButton = new JButton("Return");
        sortProductPriceBtn = new JButton("Sort By Price");
        sortProductQualityBtn = new JButton("Sort By Quantity");
        sortProductNameBtn = new JButton("Sort By Name");

        jScrollPaneProductTable = new JScrollPane();
        productTable = new JTable();

        idLabel = new JLabel("Id");
        nameLabel = new JLabel("Name");
        priceLable = new JLabel("Price");
        imageLabel = new JLabel("Image");
        qualityLabel = new JLabel("Quantity");
        searchLabel = new JLabel("Search by Name");

        searchField = new JTextField(15);
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }

            private void search() {
                String searchTerm = searchField.getText().trim();
                if (!searchTerm.isEmpty()) {
                    searchByName(searchTerm);
                } else {
                    resetSearch();
                }
            }
        });

        idField = new JTextField(6);
        idField.setEditable(false);
        nameField = new JTextField(15);
        priceField = new JTextField(6);
        imageTa = new JTextArea();
        imageTa.setColumns(15);
        imageTa.setRows(5);

        jScrollPaneAddress = new JScrollPane();
        jScrollPaneAddress.setViewportView(imageTa);
        qualityField = new JTextField(6);

        productTable.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        jScrollPaneProductTable.setViewportView(productTable);
        jScrollPaneProductTable.setPreferredSize(new Dimension(480, 300));

        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();

        panel.add(searchLabel);
        panel.add(searchField);

        logoLabel = new JLabel();
        updateLogoLabel("");
        panel.add(logoLabel);

        panel.setSize(800, 420);
        panel.setLayout(layout);
        panel.add(jScrollPaneProductTable);

        panel.add(addProductBtn);
        panel.add(editProductBtn);
        panel.add(deleteProductBtn);
        panel.add(clearBtn);

        panel.add(backButton);
        panel.add(sortProductPriceBtn);
        panel.add(sortProductNameBtn);
        panel.add(sortProductQualityBtn);

        panel.add(idLabel);
        panel.add(nameLabel);
        panel.add(priceLable);
        panel.add(imageLabel);
        panel.add(qualityLabel);

        panel.add(idField);
        panel.add(nameField);
        panel.add(priceField);
        panel.add(jScrollPaneAddress);
        panel.add(qualityField);

        layout.putConstraint(SpringLayout.WEST, logoLabel, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, logoLabel, 270, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, idLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idLabel, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nameLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameLabel, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, priceLable, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, priceLable, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, imageLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, imageLabel, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, qualityLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, qualityLabel, 200, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, idField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idField, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nameField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameField, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, priceField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, priceField, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, jScrollPaneAddress, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneAddress, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, qualityField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, qualityField, 200, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, jScrollPaneProductTable, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneProductTable, 10, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, addProductBtn, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addProductBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, editProductBtn, 60, SpringLayout.WEST, addProductBtn);
        layout.putConstraint(SpringLayout.NORTH, editProductBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, deleteProductBtn, 60, SpringLayout.WEST, editProductBtn);

        layout.putConstraint(SpringLayout.NORTH, clearBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 80, SpringLayout.WEST, deleteProductBtn);

        layout.putConstraint(SpringLayout.NORTH, deleteProductBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sortProductPriceBtn, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sortProductPriceBtn, 340, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sortProductNameBtn, 115, SpringLayout.WEST, sortProductPriceBtn);
        layout.putConstraint(SpringLayout.NORTH, sortProductNameBtn, 340, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, sortProductQualityBtn, 235, SpringLayout.WEST, sortProductPriceBtn);
        layout.putConstraint(SpringLayout.NORTH, sortProductQualityBtn, 340, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, backButton, 360, SpringLayout.WEST, sortProductPriceBtn);
        layout.putConstraint(SpringLayout.NORTH, backButton, 340, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, searchLabel, 100, SpringLayout.WEST, sortProductPriceBtn);
        layout.putConstraint(SpringLayout.NORTH, searchLabel, 310, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, searchField, 200, SpringLayout.WEST, sortProductPriceBtn);
        layout.putConstraint(SpringLayout.NORTH, searchField, 310, SpringLayout.NORTH, panel);

        this.add(panel);
        this.pack();
        this.setTitle("Product Information");
        this.setSize(800, 420);

        editProductBtn.setEnabled(false);
        deleteProductBtn.setEnabled(false);
        addProductBtn.setEnabled(true);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void showListProducts(List<Product> list) {
        int size = list.size();
        Object[][] products = new Object[size][5];
        for (int i = 0; i < size; i++) {
            products[i][0] = list.get(i).getId();
            products[i][1] = list.get(i).getName();
            products[i][2] = list.get(i).getPrice();
            products[i][3] = list.get(i).getImage();
            products[i][4] = list.get(i).getQuantity();
        }
        productTable.setModel(new DefaultTableModel(products, columnNames));
    }

    public void fillProductFromSelectedRow() {
        int row = productTable.getSelectedRow();
        if (row >= 0) {
            idField.setText(productTable.getModel().getValueAt(row, 0).toString());
            nameField.setText(productTable.getModel().getValueAt(row, 1).toString());
            priceField.setText(productTable.getModel().getValueAt(row, 2).toString());
            imageTa.setText(productTable.getModel().getValueAt(row, 3).toString());
            qualityField.setText(productTable.getModel().getValueAt(row, 4).toString());
            updateLogoLabel(imageTa.getText().trim());

            editProductBtn.setEnabled(true);
            deleteProductBtn.setEnabled(true);
            addProductBtn.setEnabled(false);
        }
    }

    private void updateLogoLabel(String imagePath) {
        URL imageUrl = getClass().getClassLoader().getResource("images/" + imagePath);
        if (imageUrl != null) {
            ImageIcon origin = new ImageIcon(imageUrl);
            Image originImage = origin.getImage();
            Image scaledImage = originImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon logoIcon = new ImageIcon(scaledImage);
            logoLabel.setIcon(logoIcon);
        } else {
            logoLabel.setIcon(null);
            System.out.println("Image not found: " + imagePath);
        }
    }

    private void searchByName(String searchTerm) {
        DefaultTableModel model = (DefaultTableModel) productTable.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        productTable.setRowSorter(sorter);

        RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)" + searchTerm, 1);
        sorter.setRowFilter(rowFilter);
    }

    private void resetSearch() {
        DefaultTableModel model = (DefaultTableModel) productTable.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        productTable.setRowSorter(sorter);
        sorter.setRowFilter(null);
    }

    public void clearProductInfo() {
        idField.setText("");
        nameField.setText("");
        priceField.setText("");
        imageTa.setText("");
        qualityField.setText("");
        updateLogoLabel(imageTa.getText().trim());
        editProductBtn.setEnabled(false);
        deleteProductBtn.setEnabled(false);
        addProductBtn.setEnabled(true);
    }

    public void showProduct(Product product) {
        idField.setText("" + product.getId());
        nameField.setText(product.getName());
        priceField.setText("" + product.getPrice());
        imageTa.setText(product.getImage());
        qualityField.setText("" + product.getQuantity());
        editProductBtn.setEnabled(true);
        deleteProductBtn.setEnabled(true);
        addProductBtn.setEnabled(false);
    }

    public Product getProductInfo() {
        if (!validateName() || !validatePrice() || !validateAddress() || !validateQuality()) {
            return null;
        }
        try {
            Product product = new Product();
            if (idField.getText() != null && !"".equals(idField.getText())) {
                product.setId(Integer.parseInt(idField.getText()));
            }
            product.setName(nameField.getText().trim());
            product.setPrice(Integer.parseInt(priceField.getText().trim()));
            product.setImage(imageTa.getText().trim());
            product.setQuality(Integer.parseInt(qualityField.getText().trim()));
            return product;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }

    private boolean validateName() {
        String name = nameField.getText();
        if (name == null || "".equals(name.trim())) {
            nameField.requestFocus();
            showMessage("Name cannot be blank.");
            return false;
        }
        return true;
    }

    private boolean validateAddress() {
        String address = imageTa.getText();
        if (address == null || "".equals(address.trim())) {
            imageTa.requestFocus();
            showMessage("Image cannot be blank.");
            return false;
        }
        return true;
    }

    private boolean validatePrice() {
        try {
            int price = Integer.parseInt(priceField.getText().trim());
            if (price < 1) {
                priceField.requestFocus();
                showMessage("Invalid Price!, Price > 0.");
                return false;
            }
        } catch (Exception e) {
            priceField.requestFocus();
            showMessage("Invalid Price!");
            return false;
        }
        return true;
    }

    private boolean validateQuality() {
        try {
            int gpa = Integer.parseInt(qualityField.getText().trim());
            if (gpa < 1 || gpa > 10000) {
                qualityField.requestFocus();
                showMessage("Invalid Quantity!, Quantity should be between 1 to 10000.");
                return false;
            }
        } catch (Exception e) {
            qualityField.requestFocus();
            showMessage("Invalid Quantity!");
            return false;
        }
        return true;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            backToMenu();
        }
    }

    private void backToMenu() {
        this.dispose();
        new MenuView().setVisible(true);
    }

    public void valueChanged(ListSelectionEvent e) {
    }

    public void addAddProductListener(ActionListener listener) {
        addProductBtn.addActionListener(listener);
    }

    public void addEdiProductListener(ActionListener listener) {
        editProductBtn.addActionListener(listener);
    }

    public void addDeleteProductListener(ActionListener listener) {
        deleteProductBtn.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        clearBtn.addActionListener(listener);
    }

    public void addSortProductQualityListener(ActionListener listener) {
        sortProductQualityBtn.addActionListener(listener);
    }

    public void addSorbackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    public void addSortProductPriceListener(ActionListener listener) {
        sortProductPriceBtn.addActionListener(listener);
    }

    public void addSortProductNameListener(ActionListener listener) {
        sortProductNameBtn.addActionListener(listener);
    }

    public void addListProductSelectionListener(ListSelectionListener listener) {
        productTable.getSelectionModel().addListSelectionListener(listener);
    }
}
