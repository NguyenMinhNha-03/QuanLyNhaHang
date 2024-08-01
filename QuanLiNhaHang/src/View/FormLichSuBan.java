package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Controller.sqlconnect;
import Model.ChiTietHoaDon;
import Model.ChiTietPhieuNhap;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class FormLichSuBan extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	ArrayList<ChiTietHoaDon> listCTHD;
    DefaultTableModel defaultTableModel;
    private sqlconnect con;
    private Connection connection;
    private JTextField editTimKiemCTHD;
    private JTable tableCTHD;
    private JLabel lbNhanVien;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormLichSuBan frame = new FormLichSuBan("Default User");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormLichSuBan(String employeeName) {
		 con = new sqlconnect("jdbc:oracle:thin:@localhost:1521:orcldk", "C##QL_NhaHang", "123");
	     connection = con.getCon();
	     initialize();
	     lbNhanVien.setText(employeeName);
	     
	     JButton btnTongTien = new JButton("Cập nhật tổng tiền");
	     btnTongTien.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		new FormHoaDon().setVisible(true);
	     		setVisible(false);
	     	}
	     });
	     btnTongTien.setFont(new Font("Tahoma", Font.PLAIN, 13));
	     btnTongTien.setBounds(900, 67, 156, 32);
	     getContentPane().add(btnTongTien);
	}
	public void initialize()
	{
		setTitle("Form Chi Tiet Hoa Don");
        getContentPane().setBackground(new Color(255, 255, 255));
        setBounds(256, 0, 1124, 731);
        getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(10, 110, 1046, 582);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JScrollPane jScrollPane = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane.setBounds(10, 10, 1034, 570);
        panel.add(jScrollPane);
        
        tableCTHD = new JTable((TableModel) null);
        tableCTHD.setBackground(Color.WHITE);
        jScrollPane.setViewportView(tableCTHD);
        
        editTimKiemCTHD = new JTextField();
        editTimKiemCTHD.setColumns(10);
        editTimKiemCTHD.setBounds(10, 69, 218, 31);
        getContentPane().add(editTimKiemCTHD);
        
        JButton btnTimKiemCTHD = new JButton("Search");
        btnTimKiemCTHD.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		timKiemTheoNgay(editTimKiemCTHD.getText().trim());
        	}
        });
        btnTimKiemCTHD.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnTimKiemCTHD.setBounds(261, 68, 108, 32);
        getContentPane().add(btnTimKiemCTHD);
        
        JLabel lblNewLabel_1_2 = new JLabel("Nhân Viên");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_1_2.setBounds(10, 10, 144, 38);
        getContentPane().add(lblNewLabel_1_2);
        
        JLabel lblNewLabel_2_2 = new JLabel("New label");
        lblNewLabel_2_2.setIcon(new ImageIcon(FormLichSuBan.class.getResource("/Icon/username.png")));
        lblNewLabel_2_2.setBounds(854, 12, 36, 38);
        getContentPane().add(lblNewLabel_2_2);
        
        lbNhanVien = new JLabel("<dynamic>");
        lbNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbNhanVien.setBounds(900, 10, 156, 38);
        getContentPane().add(lbNhanVien);
        listCTHD = getListCTHD();
        defaultTableModel = (DefaultTableModel) tableCTHD.getModel();
        defaultTableModel.setColumnIdentifiers(new Object[] { "Hóa đơn", "Tên món ăn", "Số lượng",
                "Thành tiền", "Ngày hóa đơn" });
        
        showTableCTPN();
	}
	 public ArrayList<ChiTietHoaDon> getListCTHD() {
	        ArrayList<ChiTietHoaDon> listHD = new ArrayList<ChiTietHoaDon>();
	        try {
	        	CallableStatement preparedStatement=connection.prepareCall("{Call getListCTHD(?)}");
				preparedStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
	            preparedStatement.execute();
	            ResultSet resultSet = (ResultSet)preparedStatement.getObject(1);
	            while (resultSet.next()) {
	                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
	                chiTietHoaDon.setMaCTHD(resultSet.getString("machitiethd"));
	                chiTietHoaDon.setMaHD(resultSet.getInt("mahd"));
	                chiTietHoaDon.setMaBan(resultSet.getInt("maban"));
	                chiTietHoaDon.setMaMA(resultSet.getString("mama"));
	                chiTietHoaDon.setMaNVString(resultSet.getString("manv"));
	                chiTietHoaDon.setSoLuong(resultSet.getInt("soluonghd"));
	                chiTietHoaDon.setThanhTien(resultSet.getInt("thanhtienhd"));
	                listHD.add(chiTietHoaDon);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return listHD;
	    }

	    public void showTableCTPN() {
	        defaultTableModel.setRowCount(0);
	        CallableStatement callableStatement = null;
	        ResultSet resultSet = null;
	        String sql = "{Call hien_thi_chi_tiet_hd(?)}";
	        try {
	            callableStatement = connection.prepareCall(sql);
	            callableStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
	            callableStatement.execute();
	            resultSet = (ResultSet) callableStatement.getObject(1);
	            while (resultSet.next()) {
	                int mahd = resultSet.getInt("mahoadon");
	                String tenmonan = resultSet.getString("tenmonan");
	                
	                int soluong = resultSet.getInt("soluonghd");
	                int thanhtien = resultSet.getInt("thanhtienhd");
	                Date ngayhoadon = resultSet.getDate("ngaylaphoadon");

	                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	                String ngayhoadonStr = dateFormat.format(ngayhoadon);

	                defaultTableModel.addRow(new Object[] { mahd, tenmonan, soluong, thanhtien, ngayhoadonStr });
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Lỗi khi hiển thị chi tiết hóa đơn" + e.getMessage());
	        } finally {
	            try {
	                if (resultSet != null)
	                    resultSet.close();
	                if (callableStatement != null)
	                    callableStatement.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    public String convertDateString(String dateStr) {
	        String[] possibleDateFormats = { "dd-MM-yyyy", "yyyy-MM-dd", "MM/dd/yyyy", "dd/MM/yyyy" };

	        for (String format : possibleDateFormats) {
	            try {
	                SimpleDateFormat fromUser = new SimpleDateFormat(format);
	                fromUser.setLenient(false);
	                Date date = fromUser.parse(dateStr);
	                SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
	                return myFormat.format(date);
	            } catch (ParseException e) {
	             
	            }
	        }

	        return null;
	    }

	    private void timKiemTheoNgay(String ngayNhap) {
	        defaultTableModel.setRowCount(0);
	        CallableStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        try {
	        	preparedStatement=connection.prepareCall("{Call timKiemTheoNgay(?,?)}");
	            preparedStatement.setString(1, ngayNhap);
	            preparedStatement.registerOutParameter(2, java.sql.Types.REF_CURSOR);
	            preparedStatement.execute();
	            resultSet = (ResultSet)preparedStatement.getObject(2);

	            while (resultSet.next()) {
	            	int mahd = resultSet.getInt("mahoadon");
	                String tenmonan = resultSet.getString("tenmonan"); 
	                int soluong = resultSet.getInt("soluonghd");
	                int thanhtien = resultSet.getInt("thanhtienhd");
	                Date ngayhoadon = resultSet.getDate("ngaylaphoadon");
	                defaultTableModel.addRow(new Object[] { mahd, tenmonan, soluong, thanhtien, ngayhoadon });
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm chi tiết hóa đơn: " + e.getMessage());
	        } finally {
	            try {
	                if (resultSet != null)
	                    resultSet.close();
	                if (preparedStatement != null)
	                    preparedStatement.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	  
}
