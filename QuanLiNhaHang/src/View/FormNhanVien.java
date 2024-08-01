package View;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
//import org.eclipse.wb.swing.FocusTraversalOnArray;

import Controller.sqlconnect;
import Model.NhanVien;

import java.awt.Component;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.Canvas;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JEditorPane;
import javax.swing.JTable;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JRadioButton;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

public class FormNhanVien extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	JDesktopPane desktopPane;
	private JTable tableNhanVien;
	private JTextField editTimNhanVien;
	DefaultTableModel defaultTableModel;
	private JTextField editMaNV;
	private JTextField editTenNV;
	private JTextField editNgaySinh;
	private JTextField editSDT;
	private JComboBox cbChucVu;
	private JRadioButton rdNam,rdNu;
	ArrayList<NhanVien>listNhanVien;
	private sqlconnect con;
	private Connection connection;
	private JLabel lbNhanVien;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormNhanVien frame = new FormNhanVien("Default User");
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
	public FormNhanVien(String employeeName) {
		  con = new sqlconnect("jdbc:oracle:thin:@localhost:1521:orcldk", "C##QL_NhaHang", "123");
	      connection = con.getCon();
	      initialize();
	      lbNhanVien.setText(employeeName);
	      
	     
	      
	      
	}
	private void initialize() 
	{
		setTitle("Form Nhan Vien");
		JLabel lblNewLabel_1 = new JLabel("Nhân Viên");
	    lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
	    lblNewLabel_1.setBounds(54, 10, 144, 38);
	    getContentPane().add(lblNewLabel_1);
	    
	    ImageIcon editIcon = new ImageIcon(FormMenu.class.getResource("/Icon/shutdown.png"));
	    Image editImage = editIcon.getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH);
	    ImageIcon clearEditIcon = new ImageIcon(editImage);
	      
	    JLabel lblNewLabel_2 = new JLabel("New label");
	    lblNewLabel_2.setIcon(new ImageIcon(FormNhanVien.class.getResource("/Icon/username.png")));
	    lblNewLabel_2.setBounds(837, 12, 36, 38);
	    getContentPane().add(lblNewLabel_2);
		setTitle("Form Nhan Vien");
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(327, 0, 1069, 734);
		getContentPane().setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(690, 143, 341, 466);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã nhân viên: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(24, 44, 140, 13);
		panel.add(lblNewLabel);
		
		editMaNV = new JTextField();
		editMaNV.setBounds(155, 43, 167, 19);
		panel.add(editMaNV);
		editMaNV.setColumns(10);
		
		JLabel lblTnNhnVin = new JLabel("Tên nhân viên:");
		lblTnNhnVin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTnNhnVin.setBounds(24, 82, 140, 13);
		panel.add(lblTnNhnVin);
		
		editTenNV = new JTextField();
		editTenNV.setColumns(10);
		editTenNV.setBounds(155, 81, 167, 19);
		panel.add(editTenNV);
		
		editNgaySinh = new JTextField();
		editNgaySinh.setColumns(10);
		editNgaySinh.setBounds(155, 120, 167, 19);
		panel.add(editNgaySinh);
		
		 rdNam = new JRadioButton("Nam");
		buttonGroup.add(rdNam);
		rdNam.setBounds(189, 164, 62, 21);
		panel.add(rdNam);
		
		 rdNu = new JRadioButton("Nữ");
		buttonGroup.add(rdNu);
		rdNu.setBounds(253, 164, 51, 21);
		panel.add(rdNu);
		
		editSDT = new JTextField();
		editSDT.setColumns(10);
		editSDT.setBounds(155, 203, 167, 19);
		panel.add(editSDT);
		
		JLabel lblNewLabel_1_1 = new JLabel("Ngày sinh:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(24, 123, 140, 13);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Giới tính:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(24, 168, 140, 19);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Số điện thoại:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_3.setBounds(24, 206, 140, 19);
		panel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Chức vụ:");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_4.setBounds(24, 245, 140, 21);
		panel.add(lblNewLabel_1_4);
		
		JButton btnThemNV = new JButton("Thêm nhân viên");
		btnThemNV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnThemNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themNhanVien();
			}
		});
		btnThemNV.setBounds(24, 302, 133, 36);
		panel.add(btnThemNV);
		
		JButton btnXoaNV = new JButton("Xóa nhân viên");
		btnXoaNV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnXoaNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaNhanVien();
				
			}
		});
		btnXoaNV.setBounds(189, 302, 121, 36);
		panel.add(btnXoaNV);
		
		JButton btnSuaNV = new JButton("Cập nhật nhân viên");
		btnSuaNV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSuaNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suaNhanVien();
		
			}
		});
		btnSuaNV.setBounds(105, 360, 146, 36);
		panel.add(btnSuaNV);
		
		cbChucVu = new JComboBox();
		cbChucVu.setBounds(155, 247, 167, 21);
		panel.add(cbChucVu);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBorder(new LineBorder(null, 0));
		panel_1.setBounds(54, 184, 608, 419);
		getContentPane().add(panel_1);
		panel_1.setLayout(new BorderLayout());
		defaultTableModel = new DefaultTableModel();
		tableNhanVien = new JTable(defaultTableModel);
		tableNhanVien.setBackground(new Color(255, 255, 255));
		panel_1.setLayout(new BorderLayout());
		JScrollPane jScrollPane = new JScrollPane(tableNhanVien,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel_1.add(jScrollPane,BorderLayout.NORTH);
		
		editTimNhanVien = new JTextField();
		editTimNhanVien.setBounds(54, 143, 244, 31);
		getContentPane().add(editTimNhanVien);
		editTimNhanVien.setColumns(10);
		
		listNhanVien=getListNhanVien();
		defaultTableModel=(DefaultTableModel)tableNhanVien.getModel();
		defaultTableModel.setColumnIdentifiers(new Object[] {
				"Tên nhân viên","Số điện thoại","Chức vụ"
		});
		tableNhanVien.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				 if (tableNhanVien.getSelectedRow() >= 0) {
			            NhanVien nhanVien = listNhanVien.get(tableNhanVien.getSelectedRow());
			            editMaNV.setText(nhanVien.getMaNhanVien());
			            editTenNV.setText(nhanVien.getTenNhanVien());
			            editNgaySinh.setText(new SimpleDateFormat("dd/MM/yyyy").format(nhanVien.getNgaySinh()));
			            if (nhanVien.getGioiTinh().equalsIgnoreCase("Nam")) {
			                rdNam.setSelected(true);
			            } else {
			                rdNu.setSelected(true);
			            }
			            editSDT.setText(String.valueOf(nhanVien.getSoDienThoai()));
			            cbChucVu.setSelectedItem(nhanVien.getChucVu());
			        }
			}
		});
		showTableNhanVien();
		JButton btnTimKiem = new JButton("Search");
		btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnTimKiem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String tenNVString = editTimNhanVien.getText().toString().trim();
		        if (!tenNVString.isEmpty()) {
		            defaultTableModel.setRowCount(0);
		            String callProcedure = "{CALL timNhanVien(?, ?)}";
		            CallableStatement callableStatement;
		            try {
		                callableStatement = connection.prepareCall(callProcedure);
		                callableStatement.setString(1, tenNVString);
		                callableStatement.registerOutParameter(2, java.sql.Types.REF_CURSOR);
		                callableStatement.execute();
		                
		                ResultSet resultSet = (ResultSet) callableStatement.getObject(2);
		                while (resultSet.next()) {
		                    String ten = resultSet.getString("TenNV");
		                    int sdt = resultSet.getInt("SDT");
		                    String chucVuString = resultSet.getString("ChucVu");
		                    defaultTableModel.addRow(new Object[]{ten, sdt, chucVuString});
		                }
		                
		                resultSet.close();
		                callableStatement.close();
		            } catch (SQLException e1) {
		                JOptionPane.showMessageDialog(btnTimKiem, "Không tìm thấy nhân viên yêu cầu!");
		                e1.printStackTrace();
		            }
		        } else {
		            JOptionPane.showMessageDialog(btnTimKiem, "Vui lòng nhập tên nhân viên để tìm kiếm!");
		        }
		    }
		});

		btnTimKiem.setBounds(308, 142, 123, 32);
		getContentPane().add(btnTimKiem);
		
		JButton btnTaiKhoan = new JButton("Cấp phát tài khoản");
		btnTaiKhoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormTaiKhoan taiKhoan=new FormTaiKhoan();
				taiKhoan.setVisible(true);
			}
		});
		btnTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnTaiKhoan.setBounds(867, 93, 164, 32);
		getContentPane().add(btnTaiKhoan);
		
		JButton btnBangChamCong = new JButton("Chấm công");
		btnBangChamCong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormBangChamCong bangChamCong=new FormBangChamCong();
				bangChamCong.setVisible(true);
			}
		});
		btnBangChamCong.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBangChamCong.setBounds(539, 142, 123, 32);
		getContentPane().add(btnBangChamCong);
		
		 lbNhanVien = new JLabel("New label");
		 lbNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbNhanVien.setBounds(873, 10, 158, 38);
		getContentPane().add(lbNhanVien);
		showCombobox();
	}
	public void showTableNhanVien() {
	    defaultTableModel.setRowCount(0);
	    CallableStatement callableStatement = null;
	    ResultSet resultSet = null;

	    try {
	        String sql = "{call hien_thi_nhanvien(?)}";
	        callableStatement = connection.prepareCall(sql);
	        callableStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);

	        callableStatement.execute();

	        resultSet = (ResultSet) callableStatement.getObject(1);

	        while (resultSet.next()) {
	            String tenNV = resultSet.getString("tennv");
	            String sdt = resultSet.getString("sdt");
	            String chucVu = resultSet.getString("chucvu");
	            defaultTableModel.addRow(new Object[] {tenNV, sdt, chucVu});
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Lỗi khi hiển thị nhân viên: " + e.getMessage());
	    } finally {
	        try {
	            if (resultSet != null) resultSet.close();
	            if (callableStatement != null) callableStatement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


	public ArrayList<NhanVien>getListNhanVien()
	{
		ArrayList<NhanVien>listNV=new ArrayList<NhanVien>();
		try {
			CallableStatement preparedStatement=connection.prepareCall("{Call showALLNhanVien(?)}");
			preparedStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
            preparedStatement.execute();
            ResultSet resultSet = (ResultSet)preparedStatement.getObject(1);
			while(resultSet.next())
			{
				NhanVien nVien=new NhanVien();
				nVien.setMaNhanVien(resultSet.getString("MaNhanVien"));
				nVien.setTenNhanVien(resultSet.getString("TenNV"));
				nVien.setNgaySinh(resultSet.getDate("NgaySinh"));
				nVien.setGioiTinh(resultSet.getString("GioiTinh"));
				nVien.setSoDienThoai(resultSet.getInt("SDT"));
				nVien.setChucVu(resultSet.getString("ChucVu"));
				listNV.add(nVien);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listNV;
	}
	public void showCombobox()
	{
		try {
			CallableStatement preparedStatement=connection.prepareCall("{Call showChucVuNhanVien(?)}");
			preparedStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
            preparedStatement.execute();
            ResultSet resultSet = (ResultSet)preparedStatement.getObject(1);
			while(resultSet.next())
			{
				cbChucVu.addItem(resultSet.getString("chucvu"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void themNhanVien() {
	    String maNV = editMaNV.getText().trim();
	    String tenNV = editTenNV.getText().trim();
	    String ngaySinh = editNgaySinh.getText().trim();
	    String gioiTinh = rdNam.isSelected() ? "Nam" : "Nu";
	    String sdt = editSDT.getText().trim();
	    String chucVu = (String) cbChucVu.getSelectedItem();

	    if (maNV.isEmpty() || tenNV.isEmpty() || ngaySinh.isEmpty() || sdt.isEmpty() || chucVu == null) {
	        JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    try {
	        java.text.SimpleDateFormat inputFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
	        java.text.SimpleDateFormat outputFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date date = inputFormat.parse(ngaySinh);
	        ngaySinh = outputFormat.format(date);
	    } catch (java.text.ParseException e) {
	        JOptionPane.showMessageDialog(this, "Ngày sinh không đúng định dạng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    String sql = "{CALL themnhanvien(?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?)}";
	    try (CallableStatement callableStatement = connection.prepareCall(sql)) {
	        callableStatement.setString(1, maNV);
	        callableStatement.setString(2, tenNV);
	        callableStatement.setString(3, ngaySinh);
	        callableStatement.setString(4, gioiTinh);
	        callableStatement.setString(5, sdt);
	        callableStatement.setString(6, chucVu);

	        callableStatement.execute();
	        JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
	        listNhanVien.add(new NhanVien(maNV, tenNV, Date.valueOf(ngaySinh), gioiTinh, Integer.parseInt(sdt), chucVu));
	        defaultTableModel.addRow(new Object[]{tenNV, sdt, chucVu});
	        editMaNV.setText("");
            editNgaySinh.setText("");
            editTenNV.setText("");
            editSDT.setText("");
            cbChucVu.setSelectedItem("");
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Lỗi khi thêm nhân viên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	    }
	}


	public void xoaNhanVien() {
	    int selectedRow = tableNhanVien.getSelectedRow();
	    if (selectedRow >= 0) {
	        String maNV = listNhanVien.get(selectedRow).getMaNhanVien();

	        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa nhân viên này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
	        if (confirm == JOptionPane.YES_OPTION) {
	            try (CallableStatement callableStatement = connection.prepareCall("{call xoaNhanVien(?)}")) {
	                callableStatement.setString(1, maNV);
	                callableStatement.execute();
	                System.out.println("Xóa nhân viên thành công.");
	                int rowsDeleted = callableStatement.executeUpdate();
	                if (rowsDeleted > 0) {
	                    JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công!");
	                    listNhanVien.remove(selectedRow);
	                    defaultTableModel.removeRow(selectedRow);
	                    editMaNV.setText("");
	                    editNgaySinh.setText("");
	                    editTenNV.setText("");
	                    editSDT.setText("");
	                    cbChucVu.setSelectedItem("");
	                } else {
	                    JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	                JOptionPane.showMessageDialog(this, "Lỗi khi xóa nhân viên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    } else {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên để xóa!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	    }
	}
	

	public void suaNhanVien() {
	    String maNV = editMaNV.getText().trim();
	    String tenNV = editTenNV.getText().trim();
	    String ngaySinh = editNgaySinh.getText().trim();
	    String gioiTinh = rdNam.isSelected() ? "Nam" : "Nu";
	    String sdt = editSDT.getText().trim();
	    String chucVu = (String) cbChucVu.getSelectedItem();

	    if (tenNV.isEmpty() || ngaySinh.isEmpty() || sdt.isEmpty() || chucVu == null) {
	        JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    java.sql.Date sqlDate = null;
	    try {
	        java.text.SimpleDateFormat inputFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
	        java.util.Date date = inputFormat.parse(ngaySinh);
	        sqlDate = new java.sql.Date(date.getTime());
	    } catch (java.text.ParseException e) {
	        JOptionPane.showMessageDialog(this, "Ngày sinh không đúng định dạng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    try (CallableStatement callableStatement = connection.prepareCall("{call sua_nhanvien(?, ?, ?, ?, ?, ?)}")) {
	        callableStatement.setString(1, tenNV);
	        callableStatement.setDate(2, sqlDate);
	        callableStatement.setString(3, gioiTinh);
	        callableStatement.setString(4, sdt);
	        callableStatement.setString(5, chucVu);
	        callableStatement.setString(6, maNV);

	        callableStatement.executeUpdate();
	        JOptionPane.showMessageDialog(this, "Sửa nhân viên thành công!");
	        
	        int selectedRow = tableNhanVien.getSelectedRow();
	        if (selectedRow >= 0) {
	            listNhanVien.get(selectedRow).setTenNhanVien(tenNV);
	            listNhanVien.get(selectedRow).setNgaySinh(sqlDate);
	            listNhanVien.get(selectedRow).setGioiTinh(gioiTinh);
	            listNhanVien.get(selectedRow).setSoDienThoai(Integer.parseInt(sdt));
	            listNhanVien.get(selectedRow).setChucVu(chucVu);
	            defaultTableModel.setValueAt(tenNV, selectedRow, 0);
	            defaultTableModel.setValueAt(sdt, selectedRow, 1);
	            defaultTableModel.setValueAt(chucVu, selectedRow, 2);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Lỗi khi sửa nhân viên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	    }
	}
}

