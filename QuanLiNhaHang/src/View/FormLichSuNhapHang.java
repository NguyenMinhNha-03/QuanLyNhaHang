package View;

import java.awt.BorderLayout;
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
import java.util.HashMap;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Controller.sqlconnect;
import Model.ChiTietPhieuNhap;
import Model.DangNhap;
import Model.NhanVien;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

public class FormLichSuNhapHang extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    ArrayList<ChiTietPhieuNhap> listChiTietPN;
    DefaultTableModel defaultTableModel;
    private sqlconnect con;
    private Connection connection;
    private JTextField editTimKiemCTPN;
    private JTable tableChiTietPN;
    private JTextField editMaCTPN;
    private JTextField editHSD;
    private JTextField editSL;
    private JTextField editDonGia;
    private JTextField editThanhTien;
    private JComboBox cbPN,cbNL;
    private JLabel lbNhanVien;
    String manvString;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FormLichSuNhapHang frame = new FormLichSuNhapHang("Default User");
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
    public FormLichSuNhapHang(String employeeName) {
        con = new sqlconnect("jdbc:oracle:thin:@localhost:1521:orcldk", "C##QL_NhaHang", "123");
        connection = con.getCon();
        initialize();
        lbNhanVien.setText(employeeName);
        manvString=getMaNV(employeeName);
    }

    public void initialize() {
        setTitle("Form Chi Tiet Phieu Nhap");
        getContentPane().setBackground(new Color(255, 255, 255));
        setBounds(256, 0, 1104, 731);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(10, 121, 1042, 278);
        getContentPane().add(panel);
        panel.setLayout(new BorderLayout());
        defaultTableModel = new DefaultTableModel();
        tableChiTietPN = new JTable(defaultTableModel);
        tableChiTietPN.setBackground(new Color(255, 255, 255));
        panel.setLayout(new BorderLayout());
        JScrollPane jScrollPane = new JScrollPane(tableChiTietPN, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel.add(jScrollPane, BorderLayout.CENTER);

        editTimKiemCTPN = new JTextField();
        editTimKiemCTPN.setBounds(10, 80, 218, 31);
        getContentPane().add(editTimKiemCTPN);
        editTimKiemCTPN.setColumns(10);

        JButton btnPhieuNhap = new JButton("Cập nhật phiếu nhập");
        btnPhieuNhap.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		FormPhieuNhap phieuNhap=new FormPhieuNhap();
        		phieuNhap.setVisible(true);
        	}
        });
        btnPhieuNhap.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnPhieuNhap.setBounds(876, 78, 176, 32);
        getContentPane().add(btnPhieuNhap);

        JButton btnTimKiemCTPN = new JButton("Search");
        btnTimKiemCTPN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ngayNhapString = editTimKiemCTPN.getText().trim();
                String formattedDate = convertDateString(ngayNhapString);
                if (formattedDate != null) {
                    searchCTPNByNgayNhap(formattedDate);
                } else {
                    JOptionPane.showMessageDialog(null, "Lỗi sai định dạng format");
                }
            }
        });
        btnTimKiemCTPN.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnTimKiemCTPN.setBounds(261, 79, 108, 32);
        getContentPane().add(btnTimKiemCTPN);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "Th\u00F4ng tin chi ti\u1EBFt phi\u1EBFu nh\u1EADp", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1.setBounds(10, 423, 1042, 258);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Mã CTPN:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel.setBounds(28, 34, 111, 13);
        panel_1.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Mã NL:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel_1.setBounds(28, 85, 111, 13);
        panel_1.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("MaPN:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel_2.setBounds(28, 137, 111, 13);
        panel_1.add(lblNewLabel_2);
        
        JLabel lblHnSDng = new JLabel("Hạn sử dụng:");
        lblHnSDng.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblHnSDng.setBounds(442, 34, 127, 13);
        panel_1.add(lblHnSDng);
        
        JLabel lblNewLabel_1_1 = new JLabel("Số lượng:");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel_1_1.setBounds(442, 85, 111, 13);
        panel_1.add(lblNewLabel_1_1);
        
        JLabel lblNewLabel_2_1 = new JLabel("Đơn giá:");
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel_2_1.setBounds(442, 137, 111, 13);
        panel_1.add(lblNewLabel_2_1);
        
        JLabel lblNewLabel_3_1 = new JLabel("Thành tiền:");
        lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel_3_1.setBounds(28, 187, 111, 13);
        panel_1.add(lblNewLabel_3_1);
        
        editMaCTPN = new JTextField();
        editMaCTPN.setBounds(126, 31, 213, 19);
        panel_1.add(editMaCTPN);
        editMaCTPN.setColumns(10);
        
        editHSD = new JTextField();
        editHSD.setColumns(10);
        editHSD.setBounds(560, 32, 165, 19);
        panel_1.add(editHSD);
        
        editSL = new JTextField();
        editSL.setColumns(10);
        editSL.setBounds(563, 83, 165, 19);
        panel_1.add(editSL);
        
        editDonGia = new JTextField();
        editDonGia.setColumns(10);
        editDonGia.setBounds(563, 135, 165, 19);
        panel_1.add(editDonGia);
        
        editThanhTien = new JTextField();
        editThanhTien.setColumns(10);
        editThanhTien.setBounds(126, 185, 213, 19);
        panel_1.add(editThanhTien);
        
         cbNL = new JComboBox();
        cbNL.setBounds(126, 81, 213, 21);
        panel_1.add(cbNL);
        
         cbPN = new JComboBox();
        cbPN.setBounds(126, 133, 213, 21);
        panel_1.add(cbPN);
        
        JButton btnThemCTON = new JButton("Thêm CTPN");
        btnThemCTON.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		themChiTietPhieuNhap();
        	}
        });
        btnThemCTON.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnThemCTON.setBounds(806, 85, 176, 32);
        panel_1.add(btnThemCTON);
        
        JButton btnCapNhapThanhTien = new JButton("Cập nhật thành tiền");
        btnCapNhapThanhTien.setBounds(806, 137, 176, 32);
        panel_1.add(btnCapNhapThanhTien);
        btnCapNhapThanhTien.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		updateThanhTienNhap();
        	}
        });
        btnCapNhapThanhTien.setFont(new Font("Tahoma", Font.PLAIN, 13));
        
        JLabel lblNewLabel_1_2 = new JLabel("Nhân Viên");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_1_2.setBounds(10, 10, 144, 38);
        getContentPane().add(lblNewLabel_1_2);
        
        JLabel lblNewLabel_2_2 = new JLabel("New label");
        lblNewLabel_2_2.setIcon(new ImageIcon(FormLichSuNhapHang.class.getResource("/Icon/username.png")));
        lblNewLabel_2_2.setBounds(836, 12, 36, 38);
        getContentPane().add(lblNewLabel_2_2);
        
        lbNhanVien = new JLabel("<dynamic>");
        lbNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbNhanVien.setBounds(876, 10, 176, 38);
        getContentPane().add(lbNhanVien);

        listChiTietPN = getListCTPN();
        defaultTableModel = (DefaultTableModel) tableChiTietPN.getModel();
        defaultTableModel.setColumnIdentifiers(new Object[] { "Phiếu nhập", "Tên nguyên liệu", "Hạn sử dụng", "Số lượng",
                "Thành tiền", "Ngày hóa đơn" });
        tableChiTietPN.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				 if (tableChiTietPN.getSelectedRow() >= 0) {
			            ChiTietPhieuNhap chiTietPhieuNhap = listChiTietPN.get(tableChiTietPN.getSelectedRow());
			            editMaCTPN.setText(chiTietPhieuNhap.getMaCTPN());
			            editSL.setText(String.valueOf(chiTietPhieuNhap.getSoLuongNhap()));
			            editHSD.setText(new SimpleDateFormat("dd/MM/yyyy").format(chiTietPhieuNhap.getHanSuDung()));
			            editThanhTien.setText(String.valueOf(chiTietPhieuNhap.getThanhTienNhap()));
			            editDonGia.setText(String.valueOf(chiTietPhieuNhap.getDonGiaNhap()));
			            cbNL.setSelectedItem(chiTietPhieuNhap.getMaNL());
			            cbPN.setSelectedItem(chiTietPhieuNhap.getMaPN());
			   
			        }
			}
		});
        showTableCTPN();
        showCbNL();
        ShowCbPN();
    }

    public ArrayList<ChiTietPhieuNhap> getListCTPN() {
        ArrayList<ChiTietPhieuNhap> listPN = new ArrayList<ChiTietPhieuNhap>();
        try {
        	CallableStatement preparedStatement=connection.prepareCall("{Call showALLCTPhieuNhap(?)}");
			preparedStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
            preparedStatement.execute();
            ResultSet resultSet = (ResultSet)preparedStatement.getObject(1);
            while (resultSet.next()) {
                ChiTietPhieuNhap chiTietPhieuNhap = new ChiTietPhieuNhap();
                chiTietPhieuNhap.setMaCTPN(resultSet.getString("mactpn"));
                chiTietPhieuNhap.setMaNL(resultSet.getString("manl"));
                chiTietPhieuNhap.setMaPN(resultSet.getString("mapn"));
                chiTietPhieuNhap.setMaNV(resultSet.getString("manv"));
                chiTietPhieuNhap.setHanSuDung(resultSet.getDate("hansudung"));
                chiTietPhieuNhap.setSoLuongNhap(resultSet.getInt("soluongnhap"));
                chiTietPhieuNhap.setDonGiaNhap(resultSet.getInt("dongianhap"));
                chiTietPhieuNhap.setThanhTienNhap(resultSet.getInt("thanhtiennhap"));
                listPN.add(chiTietPhieuNhap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPN;
    }

    public void showTableCTPN() {
        defaultTableModel.setRowCount(0);
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        String sql = "{Call hien_thi_chi_tiet_pn(?)}";
        try {
            callableStatement = connection.prepareCall(sql);
            callableStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet) callableStatement.getObject(1);
            while (resultSet.next()) {
                String mapn = resultSet.getString("mapn");
                String tennl = resultSet.getString("tennguyenlieu");
                Date hansudung = resultSet.getDate("hansudung");
                int soluong = resultSet.getInt("soluongnhap");
                int thanhtien = resultSet.getInt("thanhtiennhap");
                Date ngayhoadon = resultSet.getDate("ngaynhap");

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String hansudungStr = dateFormat.format(hansudung);
                String ngayhoadonStr = dateFormat.format(ngayhoadon);

                defaultTableModel.addRow(new Object[] { mapn, tennl, hansudungStr, soluong, thanhtien, ngayhoadonStr });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi hiển thị chi tiết phiếu nhập" + e.getMessage());
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

        System.err.println("Unparseable date: " + dateStr);
        return null;
    }

    private void searchCTPNByNgayNhap(String ngayNhap) {
        defaultTableModel.setRowCount(0);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "SELECT mapn, nguyenlieu.tennguyenlieu, hansudung, soluongnhap, thanhtiennhap, phieunhap.ngaynhap "
                   + "FROM chitietphieunhap "
                   + "JOIN nguyenlieu ON chitietphieunhap.manl = nguyenlieu.manguyenlieu "
                   + "JOIN phieunhap ON chitietphieunhap.mapn = phieunhap.maphieunhap "
                   + "WHERE phieunhap.ngaynhap = TO_DATE(?, 'YYYY-MM-DD')";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ngayNhap);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String mapn = resultSet.getString("mapn");
                String tennl = resultSet.getString("tennguyenlieu");
                String hansudung = resultSet.getString("hansudung");
                String soluong = resultSet.getString("soluongnhap");
                String thanhtien = resultSet.getString("thanhtiennhap");
                String ngayhoadon = resultSet.getString("ngaynhap");
                defaultTableModel.addRow(new Object[] { mapn, tennl, hansudung, soluong, thanhtien, ngayhoadon });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm chi tiết phiếu nhập: " + e.getMessage());
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
    private void updateThanhTienNhap() {
        CallableStatement callableStatement = null;
        try {
            String sql = "{CALL update_thanhtiennhap}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.execute();
            JOptionPane.showMessageDialog(this, "Cập nhật thành tiền thành công!");
            showTableCTPN();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật thành tiền: " + e.getMessage());
        } finally {
            try {
                if (callableStatement != null) callableStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void showCbNL()
	{
		try {
			CallableStatement preparedStatement=connection.prepareCall("{Call showCbNL(?)}");
			preparedStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
            preparedStatement.execute();
            ResultSet resultSet = (ResultSet)preparedStatement.getObject(1);
			while(resultSet.next())
			{
				cbNL.addItem(resultSet.getString("manguyenlieu"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
    public void ShowCbPN() {
        try {
        	CallableStatement preparedStatement=connection.prepareCall("{Call ShowCbPN(?)}");
			preparedStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
            preparedStatement.execute();
            ResultSet resultSet = (ResultSet)preparedStatement.getObject(1);
            while (resultSet.next()) {
                cbPN.addItem(resultSet.getString("maphieunhap"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void themChiTietPhieuNhap() {
        String maCTPN = editMaCTPN.getText().trim();
        String maNL = (String) cbNL.getSelectedItem();
        String maPN = (String) cbPN.getSelectedItem(); 
        String HSD = editHSD.getText().trim();
        String soLuong = editSL.getText().trim();
        String donGia = editDonGia.getText().trim();
        String thanhTien = editThanhTien.getText().trim();

        if (maCTPN.isEmpty() || HSD.isEmpty() || soLuong.isEmpty() || donGia.isEmpty() || maNL == null || maPN == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = inputFormat.parse(HSD);
            HSD = outputFormat.format(date);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Hạn sử dụng không đúng định dạng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        CallableStatement callableStatement = null;
        try {
            String sql = "{CALL themchitietphieunhap(?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setString(1, maCTPN);
            callableStatement.setString(2, maNL);
            callableStatement.setString(3, maPN);
            callableStatement.setString(4, manvString);
            callableStatement.setString(5, HSD);
            callableStatement.setInt(6, Integer.parseInt(soLuong));
            callableStatement.setInt(7, Integer.parseInt(donGia));
            callableStatement.setInt(8, Integer.parseInt(thanhTien));
            callableStatement.execute();
            JOptionPane.showMessageDialog(this, "Thêm chi tiết phiếu nhập thành công!");
            listChiTietPN.add(new ChiTietPhieuNhap(maCTPN, maNL, maPN, manvString, java.sql.Date.valueOf(HSD), Integer.parseInt(soLuong), Integer.parseInt(donGia), Integer.parseInt(thanhTien)));
            showTableCTPN();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm chi tiết phiếu nhập: " + e.getMessage());
        } finally {
            try {
                if (callableStatement != null) callableStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private String getMaNV(String employeeName) {
        String maNhanVien = null;
        String query = "SELECT manhanvien FROM nhanvien WHERE tennv = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, employeeName);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    maNhanVien = rs.getString("manhanvien");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching employee ID: " + e.getMessage());
        }
        return maNhanVien;
    }

}
