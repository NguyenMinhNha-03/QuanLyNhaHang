package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Controller.sqlconnect;
import Model.HoaDon;
import Model.PhieuNhap;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormHoaDon extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	ArrayList<Model.HoaDon>listHoaDon;
	private sqlconnect con;
	private Connection connection;
	DefaultTableModel defaultTableModel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormHoaDon frame = new FormHoaDon();
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
	public FormHoaDon() {
		setTitle("Form Hoa Don");
		con = new sqlconnect("jdbc:oracle:thin:@localhost:1521:orcldk", "C##QL_NhaHang", "123");
	    connection = con.getCon();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 466, 355);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 41, 442, 267);
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout());
		
		JScrollPane jScrollPane = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel_1.add(jScrollPane, BorderLayout.CENTER);
		
		table = new JTable((TableModel) null);
		table.setBackground(Color.WHITE);
		jScrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Cập Nhật Tổng Tiền");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTongTien();
			}
		});
		btnNewButton.setBounds(291, 10, 161, 21);
		contentPane.add(btnNewButton);
		listHoaDon=getListHD();
		defaultTableModel=(DefaultTableModel)table.getModel();
		defaultTableModel.setColumnIdentifiers(new Object[] {
				
				"Mã hóa đơn","Ngày nhập","Tổng tiền"
		});
		
		showTableHD();
	}
	public ArrayList<HoaDon>getListHD()
	{
		ArrayList<HoaDon>listHD=new ArrayList<HoaDon>();
		try {
			CallableStatement preparedStatement=connection.prepareCall("{Call getListHD(?)}");
			preparedStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
            preparedStatement.execute();
            ResultSet resultSet = (ResultSet)preparedStatement.getObject(1);
			while(resultSet.next())
			{
				HoaDon hoaDon=new HoaDon();
				hoaDon.setMaHD(resultSet.getInt("mahoadon"));
				hoaDon.setNgayLapHD(resultSet.getDate("ngaylaphoadon"));
				hoaDon.setTongTien(resultSet.getInt("tongtienhoadon"));
			
	
				listHD.add(hoaDon);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listHD;
	}
	public void showTableHD()
	{
		defaultTableModel.setRowCount(0);
		CallableStatement callableStatement=null;
		ResultSet resultSet=null;
		String sql="{Call hien_thi_hd(?)}";
		try {
			callableStatement=connection.prepareCall(sql);
			callableStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
			callableStatement.execute();
			resultSet=(ResultSet)callableStatement.getObject(1);
			
			while(resultSet.next())
			{
				String maHDString=resultSet.getString("mahoadon");
				String ngayNhap=resultSet.getString("ngaylaphoadon");
				String tongtien=resultSet.getString("tongtienhoadon");
				defaultTableModel.addRow(new Object[] {maHDString,ngayNhap,tongtien});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi khi hiển thị phiếu nhập"+e.getMessage());
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
	  public void updateTongTien()
	    {
	    	CallableStatement callableStatement = null;
	        try {
	            String sql = "{CALL updateTongTien}";
	            callableStatement = connection.prepareCall(sql);
	            callableStatement.execute();
	            JOptionPane.showMessageDialog(this, "Cập nhật tổng tiền thành công!");
	            showTableHD();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật tổng tiền: " + e.getMessage());
	        } finally {
	            try {
	                if (callableStatement != null) callableStatement.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
}
