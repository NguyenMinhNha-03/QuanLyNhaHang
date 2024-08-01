package View;

import java.awt.EventQueue;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.sqlconnect;
import Model.BangChamCong;
import Model.DangNhap;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class FormBangChamCong extends JFrame {

	private static final long serialVersionUID = 1L;
	DefaultTableModel defaultTableModel;
	private JPanel contentPane;
	ArrayList<BangChamCong>listBangChamCong;
	private sqlconnect con;
	private Connection connection;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormBangChamCong frame = new FormBangChamCong();
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
	public FormBangChamCong() {
		setTitle("Form Cham Cong");
		con = new sqlconnect("jdbc:oracle:thin:@localhost:1521:orcldk", "C##QL_NhaHang", "123");
	    connection = con.getCon();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(null, 0));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 71, 394, 171);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane jScrollPane = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		jScrollPane.setBounds(0, 0, 394, 169);
		panel.add(jScrollPane);
		
		table = new JTable((TableModel) null);
		table.setBackground(Color.WHITE);
		jScrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Cập nhật chấm công");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateSoGioLam();
			}
		});
		btnNewButton.setBounds(243, 24, 161, 21);
		contentPane.add(btnNewButton);
		listBangChamCong=getListBangChamCong();
		defaultTableModel=(DefaultTableModel)table.getModel();
		defaultTableModel.setColumnIdentifiers(new Object[] {
				"Tên nhân viên","Chức vụ","Tổng số giờ làm"
		});
		showTableChamCong();
	}
	public ArrayList<BangChamCong>getListBangChamCong()
	{
		ArrayList<BangChamCong>listBCC=new ArrayList<BangChamCong>();
		try {
			CallableStatement preparedStatement=connection.prepareCall("{Call showALLBangChamCong(?)}");
			preparedStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
            preparedStatement.execute();
            ResultSet resultSet = (ResultSet)preparedStatement.getObject(1);
			while(resultSet.next())
			{
				BangChamCong bangChamCong=new BangChamCong();
				bangChamCong.setMaChamCong(resultSet.getInt("machamcong"));
				bangChamCong.setCaLam(resultSet.getString("calam"));
				bangChamCong.setMaNhanVien(resultSet.getString("manhanvien"));;
				bangChamCong.setGioBatDauLam(resultSet.getTimestamp("giobatdaulam"));
				bangChamCong.setGioKetThucLam(resultSet.getTimestamp("gioketthuclam"));
				bangChamCong.setSoGioLam(resultSet.getFloat("sogiolam"));
	
				listBCC.add(bangChamCong);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listBCC;
	}
	public void showTableChamCong()
	{
		defaultTableModel.setRowCount(0);
		CallableStatement callableStatement=null;
		ResultSet resultSet=null;
		String sql="{Call hien_thi_bang_cham_cong(?)}";
		try {
			callableStatement=connection.prepareCall(sql);
			callableStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
			callableStatement.execute();
			resultSet=(ResultSet)callableStatement.getObject(1);
			
			while(resultSet.next())
			{
				String tenNV=resultSet.getString("tennv");
				String chucVu=resultSet.getString("chucvu");

				String sogiolam=resultSet.getString("sogiolam");
				defaultTableModel.addRow(new Object[] {tenNV,chucVu,sogiolam});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi khi hiển thị bảng chấm công"+e.getMessage());
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
	private void updateSoGioLam() {
        CallableStatement callableStatement = null;
        try {
            String sql = "{CALL update_sogiolam}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.execute();
            JOptionPane.showMessageDialog(this, "Cập nhật giờ làm  thành công!");
            showTableChamCong();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật giờ làm: " + e.getMessage());
        } finally {
            try {
                if (callableStatement != null) callableStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
