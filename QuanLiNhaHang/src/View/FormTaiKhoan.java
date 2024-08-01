package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Controller.sqlconnect;
import Model.DangNhap;
import Model.NhanVien;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.sql.rowset.CachedRowSet;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Font;

public class FormTaiKhoan extends JFrame {

	private static final long serialVersionUID = 1L;
	DefaultTableModel defaultTableModel;
	private JPanel contentPane;
	private JTable tableTaiKhoan;
	private JTextField editUserName;
	private JTextField editPassword;
	private JComboBox cbQuyenTruyCap,cbMaNhanVien;
	ArrayList<DangNhap>listTaiKhoan;
	private sqlconnect con;
	private Connection connection;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormTaiKhoan frame = new FormTaiKhoan();
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
	public FormTaiKhoan() {
		con = new sqlconnect("jdbc:oracle:thin:@localhost:1521:orcldk", "C##QL_NhaHang", "123");
	    connection = con.getCon();
	    initialize();
	}
	public void initialize()
	{
		setTitle("Form Tai Khoan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 753, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255,255, 255));
		panel.setBorder(new LineBorder(null, 0));
		panel.setBounds(32, 47, 394, 169);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout());
		defaultTableModel =new DefaultTableModel();
		tableTaiKhoan = new JTable(defaultTableModel);
		tableTaiKhoan.setBackground(new Color(255,255,255));
		panel.setLayout(new BorderLayout());
		JScrollPane jScrollPane=new JScrollPane(tableTaiKhoan,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.add(jScrollPane,BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Th\u00F4ng tin t\u00E0i kho\u1EA3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(459, 25, 226, 194);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UserName: ");
		lblNewLabel.setBounds(21, 23, 81, 20);
		panel_1.add(lblNewLabel);
		
		JLabel lblQuynTruyCp = new JLabel("Quyền truy cập:");
		lblQuynTruyCp.setBounds(21, 110, 97, 20);
		panel_1.add(lblQuynTruyCp);
		
		JLabel lblNhnVin = new JLabel("Nhân viên:");
		lblNhnVin.setBounds(21, 151, 81, 20);
		panel_1.add(lblNhnVin);
		
		cbQuyenTruyCap = new JComboBox();
		cbQuyenTruyCap.setBounds(135, 110, 81, 21);
		panel_1.add(cbQuyenTruyCap);
		
		cbMaNhanVien = new JComboBox();
		cbMaNhanVien.setBounds(135, 151, 81, 21);
		panel_1.add(cbMaNhanVien);
		
		editUserName = new JTextField();
		editUserName.setBounds(111, 24, 105, 19);
		panel_1.add(editUserName);
		editUserName.setColumns(10);
		
		editPassword = new JTextField();
		editPassword.setBounds(111, 67, 105, 19);
		panel_1.add(editPassword);
		editPassword.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Password: ");
		lblNewLabel_2_1.setBounds(21, 66, 81, 20);
		panel_1.add(lblNewLabel_2_1);
		
		JButton btnThemTaiKhoan = new JButton("Thêm tài khoản");
		btnThemTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnThemTaiKhoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themTaiKhoan();
			}
		});
		btnThemTaiKhoan.setBounds(32, 226, 140, 30);
		contentPane.add(btnThemTaiKhoan);
		
		JButton btnXoaTaiKhoan = new JButton("Xóa tài khoản");
		btnXoaTaiKhoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaTaiKhoan();
			}
		});
		btnXoaTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnXoaTaiKhoan.setBounds(286, 226, 140, 30);
		contentPane.add(btnXoaTaiKhoan);
		
		JButton btnCapNhatTaiKhoan = new JButton("Cập nhật tài khoản");
		btnCapNhatTaiKhoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				capNhatTaiKhoan();
			}
		});
		btnCapNhatTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCapNhatTaiKhoan.setBounds(134, 266, 189, 30);
		contentPane.add(btnCapNhatTaiKhoan);
		
		listTaiKhoan=getListTaiKhoan();
		defaultTableModel=(DefaultTableModel)tableTaiKhoan.getModel();
		defaultTableModel.setColumnIdentifiers(new Object[] {
				"User Name","Password","Quyen Truy Cap"
		});
		tableTaiKhoan.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(tableTaiKhoan.getSelectedRow() >=0)
				{
					DangNhap dangNhap = listTaiKhoan.get(tableTaiKhoan.getSelectedRow());
		            editUserName.setText(dangNhap.getUserName());
		            editPassword.setText(dangNhap.getPassword());
		            cbQuyenTruyCap.setSelectedItem(dangNhap.getQuyenTruyCap());
		            cbMaNhanVien.setSelectedItem(dangNhap.getMaNhanVien());	           
				}
			}
		});
		showTableTaiKhoan();
		showCbQuyenTruyCap();
		showCbNhanVien();
	}

	public ArrayList<DangNhap>getListTaiKhoan()
	{
		ArrayList<DangNhap>listDN=new ArrayList<DangNhap>();
		try {
			CallableStatement preparedStatement=connection.prepareCall("{Call showAllAccount(?)}");
			preparedStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
            preparedStatement.execute();
            ResultSet resultSet = (ResultSet)preparedStatement.getObject(1);
			while(resultSet.next())
			{
				DangNhap dangNhap=new DangNhap();
				dangNhap.setUserName(resultSet.getString("username"));
				dangNhap.setPassword(resultSet.getString("password"));
				dangNhap.setQuyenTruyCap(resultSet.getInt("quyentruycap"));
				dangNhap.setMaNhanVien(resultSet.getString("manv"));;
	
				listDN.add(dangNhap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listDN;
	}
	public void showTableTaiKhoan()
	{
		defaultTableModel.setRowCount(0);
		CallableStatement callableStatement=null;
		ResultSet resultSet=null;
		String sql="{Call hien_thi_tai_khoan(?)}";
		try {
			callableStatement=connection.prepareCall(sql);
			callableStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
			callableStatement.execute();
			resultSet=(ResultSet)callableStatement.getObject(1);
			
			while(resultSet.next())
			{
				String usernameString=resultSet.getString("username");
				String passwordString=resultSet.getString("password");
				String quyentruycapString=resultSet.getString("quyentruycap");
				defaultTableModel.addRow(new Object[] {usernameString,passwordString,quyentruycapString});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi khi hiển thị tài khoản"+e.getMessage());
		} finally
		{
			try {
				if(resultSet !=null) resultSet.close();
				if(callableStatement !=null)
				callableStatement.close();
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		}
	}
	public void showCbQuyenTruyCap()
	{
		try {
			CallableStatement preparedStatement=connection.prepareCall("{Call showQuyenTruyCap(?)}");
			preparedStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
            preparedStatement.execute();
            ResultSet resultSet = (ResultSet)preparedStatement.getObject(1);
			while(resultSet.next())
			{
				cbQuyenTruyCap.addItem(resultSet.getString("quyentruycap"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void showCbNhanVien()
	{
		try {
			CallableStatement preparedStatement=connection.prepareCall("{Call showMaNhanVien(?)}");
			preparedStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
            preparedStatement.execute();
            ResultSet resultSet = (ResultSet)preparedStatement.getObject(1);
			while(resultSet.next())
			{
				cbMaNhanVien.addItem(resultSet.getString("manhanvien"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void themTaiKhoan()
	{
		String usernameString=editUserName.getText().trim();
		String passwordString=editPassword.getText().trim();
		String quyentruycapString=(String)cbQuyenTruyCap.getSelectedItem();
		String manhanvienString=(String)cbMaNhanVien.getSelectedItem();
		if(usernameString.isEmpty() || passwordString.isEmpty() || quyentruycapString == null || manhanvienString == null)
		{
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin! ", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String sqlString="{Call themtaikhoan(?,?,?,?)}";
		try {
			CallableStatement callableStatement=connection.prepareCall(sqlString);
			callableStatement.setString(1, usernameString);
			callableStatement.setString(2, passwordString);
			callableStatement.setString(3, quyentruycapString);
			callableStatement.setString(4, manhanvienString);
			callableStatement.execute();
			JOptionPane.showMessageDialog(this, "Thêm tài khoản thành công!");
			listTaiKhoan.add(new DangNhap(usernameString,passwordString,Integer.parseInt(quyentruycapString),manhanvienString));
			defaultTableModel.addRow(new Object[] {usernameString,passwordString,quyentruycapString});
			editUserName.setText("");
			editPassword.setText("");
			cbQuyenTruyCap.setSelectedItem("");
			cbMaNhanVien.setSelectedItem("");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi khi thêm tài khoản!", "Lỗi", JOptionPane.ERROR_MESSAGE);

		}		
	}
	public void xoaTaiKhoan() {
	    int selectedRow = tableTaiKhoan.getSelectedRow();
	    if (selectedRow >= 0) {
	        String usernameString = listTaiKhoan.get(selectedRow).getUserName();
	        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa nhân viên này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
	        if (confirm == JOptionPane.YES_OPTION) {
	            try (CallableStatement callableStatement = connection.prepareCall("{Call xoataikhoan(?)}")) {
	                callableStatement.setString(1, usernameString);
	                callableStatement.execute();

	                JOptionPane.showMessageDialog(this, "Xóa tài khoản thành công!");
	                listTaiKhoan.remove(selectedRow);
	                defaultTableModel.removeRow(selectedRow);
	                editUserName.setText("");
	                editPassword.setText("");
	                cbQuyenTruyCap.setSelectedItem("");
	                cbMaNhanVien.setSelectedItem("");
	            } catch (SQLException e) {
	                if (e.getErrorCode() == 20001) { 
	                    JOptionPane.showMessageDialog(this, "Không tìm thấy tài khoản để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	                } else {
	                    JOptionPane.showMessageDialog(this, "Lỗi khi xóa tài khoản!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	                }
	                e.printStackTrace();
	            }
	        }
	    } else {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn một tài khoản để xóa.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	    }
	}
	public void capNhatTaiKhoan()
	{
		String userNameString=editUserName.getText().trim();
		String passwordString=editPassword.getText().trim();
		String quyenTruyCapString=(String)cbQuyenTruyCap.getSelectedItem();
		if(userNameString.isEmpty() || quyenTruyCapString==null)
		{
			 JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		     return;
		}
		try(CallableStatement callableStatement=connection.prepareCall("{Call capnhattaikhoan(?,?)}")) {
			callableStatement.setString(1, quyenTruyCapString);
			callableStatement.setString(2, userNameString);
			callableStatement.executeUpdate();
			JOptionPane.showMessageDialog(this, "Sửa tài khoản thành công!");
			
			int selectRow= tableTaiKhoan.getSelectedRow();
			if(selectRow >=0)
			{
				listTaiKhoan.get(selectRow).setQuyenTruyCap(Integer.parseInt(quyenTruyCapString));
				listTaiKhoan.get(selectRow).setUserName(userNameString);
				defaultTableModel.setValueAt(userNameString, selectRow, 0);
				defaultTableModel.setValueAt(passwordString, selectRow, 1);
				defaultTableModel.setValueAt(quyenTruyCapString, selectRow, 2);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi khi sửa tài khoản!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

}
