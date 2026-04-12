package gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import dao.TaiKhoan_DAO;
import entity.TaiKhoan;

public class DangNhap_GUI extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private JTextField txtTenDN;
    private JPasswordField txtMatKhau;
    private JButton btnDangNhap;
    public static TaiKhoan taiKhoanDangNhap;

    // Bảng màu Pastel & Modern
    private final Color COL_PRIMARY = new Color(52, 152, 219); // Sky Blue (#3498DB)
    private final Color COL_ACCENT = new Color(241, 196, 15);   // Sun Yellow (#F1C40F)
    private final Color COL_BG_LIGHT = new Color(247, 249, 249); // Ultra Light Gray
    private final Color COL_CARD_BG = Color.WHITE;
    private final Color COL_TEXT_SOFT = new Color(127, 140, 141);

    public DangNhap_GUI() {
        setTitle("Smart Restaurant OS - Đăng nhập");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 550);
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(true); // Tạo giao diện không viền hiện đại

        // Panel nền chứa ảnh mờ (Background)
        JPanel backgroundPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon bgIcon = getIcon("restaurant_bg.png", getWidth(), getHeight());
                if (bgIcon != null) {
                    g.drawImage(bgIcon.getImage(), 0, 0, null);
                    // Lớp phủ trắng mờ
                    g.setColor(new Color(255, 255, 255, 180));
                    g.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    g.setColor(COL_BG_LIGHT);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());
        setContentPane(backgroundPanel);

        // --- LOGIN CARD (Bo góc & Đổ bóng) ---
        JPanel loginCard = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Vẽ bóng đổ (Drop Shadow)
                g2.setColor(new Color(0, 0, 0, 30));
                g2.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 40, 40);
                // Vẽ nền Card
                g2.setColor(COL_CARD_BG);
                g2.fillRoundRect(0, 0, getWidth() - 10, getHeight() - 10, 40, 40);
                g2.dispose();
            }
        };
        loginCard.setPreferredSize(new Dimension(400, 480));
        loginCard.setOpaque(false);
        loginCard.setLayout(new BoxLayout(loginCard, BoxLayout.Y_AXIS));
        loginCard.setBorder(new EmptyBorder(30, 40, 30, 50));

        // Logo/Icon
        JLabel lblLogo = new JLabel("🔔"); // Có thể thay bằng icon getIcon("logo_bell.png", 50, 50)
        lblLogo.setFont(new Font("Segoe UI", Font.PLAIN, 40));
        lblLogo.setForeground(COL_ACCENT);
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Tiêu đề
        JLabel lblTitle = new JLabel("Smart Restaurant OS");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(new Color(44, 62, 80));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblWelcome = new JLabel("Vui lòng đăng nhập để tiếp tục");
        lblWelcome.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblWelcome.setForeground(COL_TEXT_SOFT);
        lblWelcome.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Inputs
        txtTenDN = new JTextField();
        styleNeumorphicField(txtTenDN, "Tên đăng nhập");
        
        txtMatKhau = new JPasswordField();
        styleNeumorphicField(txtMatKhau, "Mật khẩu");

        // Button Đăng nhập
        btnDangNhap = new JButton("LOGIN");
        btnDangNhap.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnDangNhap.setBackground(COL_PRIMARY);
        btnDangNhap.setForeground(Color.WHITE);
        btnDangNhap.setFocusPainted(false);
        btnDangNhap.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0));
        btnDangNhap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDangNhap.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btnDangNhap.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Quên mật khẩu
        JLabel lblForgot = new JLabel("Quên mật khẩu?");
        lblForgot.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblForgot.setForeground(new Color(230, 126, 34)); // Yellow-orange
        lblForgot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblForgot.setAlignmentX(Component.RIGHT_ALIGNMENT);
        lblForgot.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { new QuenMatKhau_GUI(); }
        });

        // Exit button (Góc phải)
        JButton btnClose = new JButton("✕");
        btnClose.setBorderPainted(false);
        btnClose.setContentAreaFilled(false);
        btnClose.setForeground(COL_TEXT_SOFT);
        btnClose.addActionListener(e -> System.exit(0));

        // Thêm vào Card
        loginCard.add(lblLogo);
        loginCard.add(Box.createVerticalStrut(10));
        loginCard.add(lblTitle);
        loginCard.add(lblWelcome);
        loginCard.add(Box.createVerticalStrut(40));
        loginCard.add(txtTenDN);
        loginCard.add(Box.createVerticalStrut(20));
        loginCard.add(txtMatKhau);
        loginCard.add(Box.createVerticalStrut(10));
        loginCard.add(lblForgot);
        loginCard.add(Box.createVerticalStrut(30));
        loginCard.add(btnDangNhap);

        backgroundPanel.add(loginCard);

        // Event
        btnDangNhap.addActionListener(this);
    }

    private void styleNeumorphicField(JTextField field, String title) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        field.setBackground(new Color(240, 243, 244)); // Pastel background
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        field.setToolTipText(title);
    }

    private ImageIcon getIcon(String path, int width, int height) {
        try {
            URL imgURL = getClass().getResource("/image/" + path);
            if (imgURL != null) {
                ImageIcon icon = new ImageIcon(imgURL);
                Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                return new ImageIcon(img);
            }
        } catch (Exception e) {}
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDangNhap) {
            // Giữ nguyên logic xử lý DB của bạn
            TaiKhoan_DAO dao = new TaiKhoan_DAO();
            String userInput = txtTenDN.getText();
            String passInput = new String(txtMatKhau.getPassword());

            if (userInput.trim().isEmpty() || passInput.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }

            TaiKhoan tk = dao.dangNhap(userInput, passInput);
            if (tk != null) {
                DangNhap_GUI.taiKhoanDangNhap = tk;
                new TrangChinh_Form().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Sai tên đăng nhập hoặc mật khẩu!");
            }
        }
    }
}