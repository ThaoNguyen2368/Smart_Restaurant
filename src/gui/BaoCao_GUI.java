// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package gui;

import com.toedter.calendar.JDateChooser;
import dao.BaoCao_DAO;
import entity.MonAn;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class BaoCao_GUI extends JPanel {
   private static final long serialVersionUID = 1L;
   private JTable tblTopMon;
   private DefaultTableModel modelTopMon;
   private JLabel lblTongDoanhThu;
   private JLabel lblDonDatBan;
   private JLabel lblDoanhThuMon;
   private JLabel lblSoLuongHD;
   private JPanel pnlChartContainer;
   private JDateChooser txtTuNgay;
   private JDateChooser txtDenNgay;
   private JComboBox<String> cboThongKeTheo;
   private JButton btnLoc;
   private ChartPanel chartPanel = null;
   private final Color PRIMARY_BG = new Color(255, 204, 153);
   private final Color CONTENT_BG;
   private final Color ACCENT_COLOR;
   private final BaoCao_DAO baoCaoDAO;

   public BaoCao_GUI() {
      this.CONTENT_BG = Color.WHITE;
      this.ACCENT_COLOR = new Color(51, 153, 255);
      this.baoCaoDAO = new BaoCao_DAO();
      this.setLayout(new BorderLayout(10, 10));
      this.setBackground(this.PRIMARY_BG);
      this.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
      JPanel pnlNorth = new JPanel();
      pnlNorth.setLayout(new BoxLayout(pnlNorth, 1));
      pnlNorth.setBackground(this.PRIMARY_BG);
      JLabel lblTitle = new JLabel("BÁO CÁO THỐNG KÊ", 0);
      lblTitle.setFont(new Font("Arial", 1, 36));
      lblTitle.setForeground(new Color(139, 69, 19));
      lblTitle.setAlignmentX(0.5F);
      pnlNorth.add(lblTitle);
      pnlNorth.add(Box.createVerticalStrut(20));
      JPanel pnlFilter = new JPanel(new FlowLayout(1, 15, 10));
      pnlFilter.setBackground(this.PRIMARY_BG);
      pnlFilter.setAlignmentX(0.5F);
      this.txtTuNgay = new JDateChooser(Date.from(LocalDate.now().minusMonths(1L).atStartOfDay(ZoneId.systemDefault()).toInstant()));
      this.txtTuNgay.setDateFormatString("dd/MM/yyyy");
      this.txtTuNgay.setPreferredSize(new Dimension(140, 30));
      this.txtDenNgay = new JDateChooser(new Date());
      this.txtDenNgay.setDateFormatString("dd/MM/yyyy");
      this.txtDenNgay.setPreferredSize(new Dimension(140, 30));
      this.cboThongKeTheo = new JComboBox(new String[]{"Ngày", "Tháng", "Năm"});
      this.cboThongKeTheo.setPreferredSize(new Dimension(100, 30));
      this.btnLoc = new JButton("Lọc");
      this.btnLoc.setBackground(this.ACCENT_COLOR);
      this.btnLoc.setForeground(Color.WHITE);
      this.btnLoc.setFocusPainted(false);
      this.btnLoc.setFont(new Font("Segoe UI", 1, 14));
      this.btnLoc.setPreferredSize(new Dimension(80, 30));
      pnlFilter.add(new JLabel("Từ ngày:"));
      pnlFilter.add(this.txtTuNgay);
      pnlFilter.add(new JLabel("Đến ngày:"));
      pnlFilter.add(this.txtDenNgay);
      pnlFilter.add(new JLabel("Thống kê theo:"));
      pnlFilter.add(this.cboThongKeTheo);
      pnlFilter.add(this.btnLoc);
      pnlNorth.add(pnlFilter);
      pnlNorth.add(Box.createVerticalStrut(40));
      this.add(pnlNorth, "North");
      JPanel pnlCenter = new JPanel();
      pnlCenter.setLayout(new BoxLayout(pnlCenter, 1));
      pnlCenter.setBackground(this.PRIMARY_BG);
      pnlCenter.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
      JPanel pnlSummary = new JPanel();
      pnlSummary.setBackground(this.PRIMARY_BG);
      pnlSummary.setLayout(new BoxLayout(pnlSummary, 0));
      Dimension summaryMax = new Dimension(1100, 140);
      pnlSummary.setMaximumSize(summaryMax);
      pnlSummary.setAlignmentX(0.5F);
      this.lblTongDoanhThu = new JLabel("0₫", 0);
      this.lblDonDatBan = new JLabel("0₫", 0);
      this.lblDoanhThuMon = new JLabel("0₫", 0);
      this.lblSoLuongHD = new JLabel("0", 0);
      pnlSummary.add(Box.createHorizontalGlue());
      pnlSummary.add(this.createSummaryBox("Tổng doanh thu", this.lblTongDoanhThu, new Color(66, 133, 244)));
      pnlSummary.add(Box.createRigidArea(new Dimension(25, 0)));
      pnlSummary.add(this.createSummaryBox("Đơn đặt bàn", this.lblDonDatBan, new Color(52, 168, 83)));
      pnlSummary.add(Box.createRigidArea(new Dimension(25, 0)));
      pnlSummary.add(this.createSummaryBox("Doanh thu món ăn", this.lblDoanhThuMon, new Color(251, 188, 5)));
      pnlSummary.add(Box.createRigidArea(new Dimension(25, 0)));
      pnlSummary.add(this.createSummaryBox("Số lượng hóa đơn", this.lblSoLuongHD, new Color(234, 67, 53)));
      pnlSummary.add(Box.createHorizontalGlue());
      pnlCenter.add(pnlSummary);
      pnlCenter.add(Box.createVerticalStrut(25));
      this.add(pnlCenter, "Center");
      JPanel pnlSouth = new JPanel(new GridLayout(1, 2, 25, 0));
      pnlSouth.setBackground(this.PRIMARY_BG);
      pnlSouth.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
      this.pnlChartContainer = new JPanel(new BorderLayout());
      this.pnlChartContainer.setBackground(this.CONTENT_BG);
      this.pnlChartContainer.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
      JPanel pnlChartTitle = new JPanel(new BorderLayout());
      pnlChartTitle.setBackground(this.CONTENT_BG);
      JLabel lblChartTitle = new JLabel("Biểu đồ doanh thu theo ngày", 0);
      lblChartTitle.setFont(new Font("Segoe UI", 1, 15));
      lblChartTitle.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
      pnlChartTitle.add(lblChartTitle, "North");
      this.createChartPanel(new DefaultCategoryDataset(), "Ngày");
      this.pnlChartContainer.add(pnlChartTitle, "North");
      this.pnlChartContainer.add(this.chartPanel, "Center");
      pnlSouth.add(this.pnlChartContainer);
      JPanel pnlTop = new JPanel(new BorderLayout());
      pnlTop.setBackground(this.PRIMARY_BG);
      pnlTop.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
      JLabel lblTop = new JLabel("Top 5 món bán chạy", 0);
      lblTop.setFont(new Font("Segoe UI", 1, 15));
      lblTop.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
      pnlTop.add(lblTop, "North");
      String[] cols = new String[]{"Hạng", "Tên món ăn", "Số lượng"};
      this.modelTopMon = new DefaultTableModel(cols, 0);
      this.tblTopMon = new JTable(this.modelTopMon);
      this.tblTopMon.setRowHeight(30);
      this.tblTopMon.setFont(new Font("Segoe UI", 0, 14));
      this.tblTopMon.getTableHeader().setFont(new Font("Segoe UI", 1, 14));
      this.tblTopMon.getTableHeader().setBackground(new Color(220, 220, 220));
      JScrollPane scroll = new JScrollPane(this.tblTopMon);
      scroll.setBackground(this.CONTENT_BG);
      scroll.getViewport().setBackground(this.CONTENT_BG);
      pnlTop.add(scroll, "Center");
      pnlSouth.add(pnlTop);
      this.add(pnlSouth, "South");
      this.btnLoc.addActionListener((e) -> this.handleLocAction());
      SwingUtilities.invokeLater(this::handleLocAction);
   }

   private void handleLocAction() {
      Date tuNgayUtil = this.txtTuNgay.getDate();
      Date denNgayUtil = this.txtDenNgay.getDate();
      if (tuNgayUtil != null && denNgayUtil != null) {
         LocalDate tuNgay = tuNgayUtil.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
         LocalDate denNgay = denNgayUtil.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
         if (tuNgay.isAfter(denNgay)) {
            JOptionPane.showMessageDialog(this, "'Từ ngày' phải nhỏ hơn hoặc bằng 'Đến ngày'!", "Lỗi chọn ngày", 0);
         } else {
            String thongKeTheo = (String)this.cboThongKeTheo.getSelectedItem();
            this.updateSummaryBoxes(tuNgay, denNgay);
            this.updateTopMonTable(tuNgay, denNgay, 5);
            this.updateChartData(tuNgay, denNgay, thongKeTheo);
         }
      } else {
         JOptionPane.showMessageDialog(this, "Vui lòng chọn đầy đủ 'Từ ngày' và 'Đến ngày'!", "Thiếu thông tin", 2);
      }
   }

   private void updateSummaryBoxes(LocalDate tuNgay, LocalDate denNgay) {
      double tongDoanhThu = this.baoCaoDAO.tinhTongDoanhThu(tuNgay, denNgay);
      double tongTienDatBan = this.baoCaoDAO.tinhTongTienDatBan(tuNgay, denNgay);
      int soLuongHD = this.baoCaoDAO.tinhTongSoLuongHoaDon(tuNgay, denNgay);
      double doanhThuMon = tongDoanhThu - tongTienDatBan;
      if (doanhThuMon < (double)0.0F) {
         doanhThuMon = tongDoanhThu;
      }

      this.lblTongDoanhThu.setText(String.format("%,.0f₫", tongDoanhThu));
      this.lblDonDatBan.setText(String.format("%,.0f₫", tongTienDatBan));
      this.lblDoanhThuMon.setText(String.format("%,.0f₫", doanhThuMon));
      this.lblSoLuongHD.setText(String.valueOf(soLuongHD));
   }

   private void updateTopMonTable(LocalDate tuNgay, LocalDate denNgay, int limit) {
      this.modelTopMon.setRowCount(0);
      Map<MonAn, Integer> topMon = this.baoCaoDAO.getTopMonAnBanChay(tuNgay, denNgay, limit);
      if (topMon.isEmpty()) {
         this.modelTopMon.addRow(new Object[]{"", "Không có dữ liệu trong kỳ.", ""});
      } else {
         int hang = 1;

         for(Map.Entry<MonAn, Integer> entry : topMon.entrySet()) {
            this.modelTopMon.addRow(new Object[]{String.valueOf(hang++), ((MonAn)entry.getKey()).getTenMonAn(), entry.getValue()});
         }

      }
   }

   private void updateChartData(LocalDate tuNgay, LocalDate denNgay, String thongKeTheo) {
      DefaultCategoryDataset newDataset = new DefaultCategoryDataset();
      String title;
      String categoryLabel;
      if (thongKeTheo.equals("Ngày")) {
         title = "Biểu đồ doanh thu theo ngày";
         categoryLabel = "Ngày";
      } else if (thongKeTheo.equals("Tháng")) {
         title = "Biểu đồ doanh thu theo tháng";
         categoryLabel = "Tháng";
      } else {
         title = "Biểu đồ doanh thu theo năm";
         categoryLabel = "Năm";
      }

      Map<String, Double> duLieuDoanhThu = this.baoCaoDAO.getDoanhThuTheoNhom(tuNgay, denNgay, thongKeTheo);
      if (duLieuDoanhThu.isEmpty()) {
         newDataset.addValue((double)0.0F, "Doanh thu", "Không có dữ liệu");
      } else {
         for(Map.Entry<String, Double> entry : duLieuDoanhThu.entrySet()) {
            newDataset.addValue((Number)entry.getValue(), "Doanh thu", (Comparable)entry.getKey());
         }
      }

      this.replaceChartPanel(newDataset, categoryLabel);
      Component[] components = this.pnlChartContainer.getComponents();

      label39:
      for(Component comp : components) {
         if (comp instanceof JPanel) {
            Component[] subComponents = ((JPanel)comp).getComponents();

            for(Component subComp : subComponents) {
               if (subComp instanceof JLabel) {
                  ((JLabel)subComp).setText(title);
                  break label39;
               }
            }
            break;
         }
      }

      this.pnlChartContainer.revalidate();
      this.pnlChartContainer.repaint();
   }

   private void replaceChartPanel(DefaultCategoryDataset dataset, String categoryLabel) {
      JFreeChart chart = ChartFactory.createBarChart("", categoryLabel, "Doanh thu (₫)", dataset, PlotOrientation.VERTICAL, true, true, false);
      chart.getTitle().setFont(new Font("Segoe UI", 1, 15));
      chart.setBackgroundPaint(this.CONTENT_BG);
      CategoryPlot plot = chart.getCategoryPlot();
      plot.setBackgroundPaint(this.CONTENT_BG);
      plot.setOutlineVisible(false);
      NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
      rangeAxis.setNumberFormatOverride(new DecimalFormat("#,###"));
      BarRenderer renderer = (BarRenderer)plot.getRenderer();
      renderer.setSeriesPaint(0, new Color(51, 153, 255));
      renderer.setItemMargin(0.05);
      chart.removeLegend();
      ChartPanel newChartPanel = new ChartPanel(chart);
      newChartPanel.setPreferredSize(new Dimension(450, 400));
      if (this.chartPanel != null) {
         this.pnlChartContainer.remove(this.chartPanel);
      }

      this.chartPanel = newChartPanel;
      this.pnlChartContainer.add(this.chartPanel, "Center");
   }

   private void createChartPanel(DefaultCategoryDataset dataset, String categoryLabel) {
      dataset = new DefaultCategoryDataset();
      dataset.addValue((double)5000000.0F, "Doanh thu", LocalDate.now().minusDays(2L).toString());
      dataset.addValue((double)8000000.0F, "Doanh thu", LocalDate.now().minusDays(1L).toString());
      dataset.addValue((double)1.2E7F, "Doanh thu", LocalDate.now().toString());
      JFreeChart chart = ChartFactory.createBarChart("", categoryLabel, "Doanh thu (₫)", dataset, PlotOrientation.VERTICAL, true, true, false);
      chart.getTitle().setFont(new Font("Segoe UI", 1, 15));
      chart.setBackgroundPaint(this.CONTENT_BG);
      CategoryPlot plot = chart.getCategoryPlot();
      plot.setBackgroundPaint(this.CONTENT_BG);
      plot.setOutlineVisible(false);
      NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
      rangeAxis.setNumberFormatOverride(new DecimalFormat("#,###"));
      BarRenderer renderer = (BarRenderer)plot.getRenderer();
      renderer.setSeriesPaint(0, new Color(51, 153, 255));
      renderer.setItemMargin(0.05);
      chart.removeLegend();
      this.chartPanel = new ChartPanel(chart);
      this.chartPanel.setPreferredSize(new Dimension(450, 400));
   }

   private JPanel createSummaryBox(String title, JLabel valueLabel, Color color) {
      JPanel panel = new JPanel(new BorderLayout());
      panel.setBackground(color);
      panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(color.darker(), 1), BorderFactory.createEmptyBorder(20, 20, 20, 20)));
      JLabel lblTitle = new JLabel(title, 0);
      lblTitle.setForeground(Color.WHITE);
      lblTitle.setFont(new Font("Segoe UI", 1, 16));
      valueLabel.setForeground(Color.WHITE);
      valueLabel.setFont(new Font("Segoe UI", 1, 28));
      panel.add(lblTitle, "North");
      panel.add(valueLabel, "Center");
      Dimension boxSize = new Dimension(240, 140);
      panel.setPreferredSize(boxSize);
      panel.setMaximumSize(boxSize);
      panel.setOpaque(true);
      return panel;
   }
}

