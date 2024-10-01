package view;

import dao.UserDao;
import entity.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class LoginView extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JTextField Username;
    private JPasswordField Password;
    private JButton signInButton; // Nút Đăng Nhập
    private JButton closeButton; // Nút Đóng

    public LoginView() {
        initComponents();
        setLocationRelativeTo(null);
        // Thiết lập nút SIGN IN làm nút mặc định khi nhấn Enter
        getRootPane().setDefaultButton(signInButton);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        JPanel logo_panel = new JPanel();
        JLabel logo_parking = new JLabel();
        JLabel title_parking1 = new JLabel();
        JLabel title_parking2 = new JLabel();
        JPanel form_panel = new JPanel();
        JPanel titlePanel = new JPanel();
        JLabel title_form = new JLabel();
        Username = new JTextField();
        Password = new JPasswordField();
        JLabel username_reminder = new JLabel();
        JLabel password_reminder = new JLabel();
        JSeparator jSeparator1 = new JSeparator();
        JSeparator jSeparator2 = new JSeparator();
        JLabel username_text = new JLabel();
        JLabel password_text = new JLabel();

        // Thanh công cụ ở dưới cùng
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        
        // Khai báo nút minimize
        JButton minimizeButton = new JButton("_");
        minimizeButton.setForeground(Color.WHITE);
        minimizeButton.setBackground(Color.BLACK);
        minimizeButton.setBorderPainted(false);
        minimizeButton.setFocusPainted(false);
        minimizeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        minimizeButton.addActionListener(e -> setState(JFrame.ICONIFIED));

        // Khai báo nút close
        closeButton = new JButton("X");
        closeButton.setForeground(Color.WHITE);
        closeButton.setBackground(Color.BLACK);
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeButton.addActionListener(e -> System.exit(0));

        bottomPanel.add(minimizeButton);
        bottomPanel.add(closeButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setUndecorated(false); // Bỏ chế độ không có viền

        logo_panel.setBackground(new Color(75, 86, 210));

        // Cập nhật cách load hình ảnh từ thư mục resources
        URL imageUrl = getClass().getClassLoader().getResource("images/login.jpg");
        if (imageUrl != null) {
            logo_parking.setIcon(new ImageIcon(imageUrl));
        } else {
            System.out.println("Image not found!");
        }

        title_parking1.setFont(new Font("sansserif", Font.BOLD, 24));
        title_parking1.setForeground(Color.WHITE);
        title_parking1.setText("SNEAKER MANAGEMENT");
        title_parking2.setFont(new Font("sansserif", Font.BOLD, 24));
        title_parking2.setForeground(Color.WHITE);
        title_parking2.setHorizontalAlignment(SwingConstants.CENTER);
        title_parking2.setText("SYSTEM");

        GroupLayout logo_panelLayout = new GroupLayout(logo_panel);
        logo_panel.setLayout(logo_panelLayout);
        logo_panelLayout.setHorizontalGroup(
            logo_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(logo_panelLayout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(logo_parking)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, logo_panelLayout.createSequentialGroup()
                .addGap(0, 35, Short.MAX_VALUE)
                .addComponent(title_parking1)
                .addGap(32, 32, 32))
            .addGroup(GroupLayout.Alignment.TRAILING, logo_panelLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(title_parking2)
                .addGap(126, 126, 126))
        );
        logo_panelLayout.setVerticalGroup(
            logo_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(logo_panelLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logo_parking)
                .addGap(32, 32, 32)
                .addComponent(title_parking1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(title_parking2)
                .addGap(92, 92, 92))
        );

        form_panel.setBackground(Color.WHITE);
        form_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        title_form.setFont(new Font("sansserif", Font.PLAIN, 24));
        title_form.setForeground(Color.BLACK);
        title_form.setHorizontalAlignment(SwingConstants.CENTER);
        title_form.setText("SIGN IN ACCOUNT");
        form_panel.add(title_form, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 220, 60));

        Username.setBackground(Color.WHITE);
        Username.setFont(new Font("sansserif", Font.ITALIC, 14));
        Username.setForeground(new Color(149, 149, 149));
        Username.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        form_panel.add(Username, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 280, 30));

        Password.setBackground(Color.WHITE);
        Password.setFont(new Font("sansserif", Font.ITALIC, 14));
        Password.setForeground(new Color(149, 149, 149));
        Password.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        form_panel.add(Password, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 280, 30));

        // Tạo JButton cho SIGN IN
        signInButton = new JButton("SIGN IN");
        signInButton.setBackground(new Color(75, 86, 210));
        signInButton.setForeground(Color.WHITE);
        signInButton.setFont(new Font("sansserif", Font.BOLD, 14));
        signInButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Xử lý sự kiện khi bấm vào nút SIGN IN
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                handleLogin();
            }
        });

        form_panel.add(signInButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 280, 40));

        username_reminder.setForeground(Color.RED);
        form_panel.add(username_reminder, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 280, -1));

        password_reminder.setForeground(Color.RED);
        form_panel.add(password_reminder, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 280, 10));

        jSeparator1.setBackground(new Color(102, 102, 102));
        form_panel.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 280, 10));

        jSeparator2.setBackground(new Color(102, 102, 102));
        form_panel.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 280, 10));

        username_text.setForeground(Color.BLACK);
        username_text.setText("Username");
        form_panel.add(username_text, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, 20));

        password_text.setForeground(Color.BLACK);
        password_text.setText("Password");
        form_panel.add(password_text, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, 20));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(logo_panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(form_panel, GroupLayout.PREFERRED_SIZE, 337, GroupLayout.PREFERRED_SIZE))
            .addComponent(bottomPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Thêm bottomPanel
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(form_panel, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                .addComponent(bottomPanel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)) // Thêm bottomPanel
            .addComponent(logo_panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    // Phương thức xử lý đăng nhập
    private void handleLogin() {
        User user = this.getUser();
        UserDao daoUser = new UserDao();
        if (daoUser.checkUser(user)) {
            MenuView menuview = new MenuView();
            menuview.setVisible(true);
            this.setVisible(false);
        } else {
            this.showMessage("Tên đăng nhập hoặc mật khẩu không đúng.");
        }
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public User getUser() {
        return new User(Username.getText(), String.valueOf(Password.getPassword()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Xử lý sự kiện khác nếu cần
    }

  
}
