package com.treenewbee.shell;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

import com.treenewbee.util.JdbcTool;

import org.eclipse.swt.custom.CBanner;

public class ManagerShell extends Shell {
	private Text txtID;
	private Text txtPassword;
	private Button btnLogin;
	private Button btnClear;
	private Button button;
	
	public static boolean isManagerOpen;
	public static boolean openSuccess;
	public static String ID;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void start() {
		openSuccess = false;
		isManagerOpen = true;
		try {
			Display display = Display.getDefault();
			ManagerShell shell = new ManagerShell(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * @param display
	 */
	public ManagerShell(Display display) {
		super(display, SWT.NONE);
		setBackgroundImage(SWTResourceManager.getImage(ManagerShell.class, "/com/shxt/supersystem/picture/1374565585914.jpg"));
		setBackgroundMode(SWT.INHERIT_FORCE);
		
		int screenWidth=ManagerShell.this.getMonitor().getClientArea().width;
		int screenHeight=ManagerShell.this.getMonitor().getClientArea().height;
		int x=ManagerShell.this.getSize().x;
		int y=ManagerShell.this.getSize().y;
		if(x>screenWidth){
			ManagerShell.this.getSize().x=screenWidth;
		}
		if(y>=screenHeight){
			ManagerShell.this.getSize().y=screenHeight;
		}
		ManagerShell.this.setLocation((screenWidth-x)/2,(screenHeight-y)/2);

		
		Label lblid = new Label(this, SWT.NONE);
		lblid.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		lblid.setBounds(70, 67, 86, 29);
		lblid.setText("\u5DE5       \u53F7\uFF1A");
		
		txtID = new Text(this, SWT.BORDER);
		txtID.setText("101");
		txtID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.keyCode == 16777218) {
					txtPassword.setFocus();
				}
			}
		});
		
		txtID.setBounds(162, 68, 129, 23);
		
		Label label = new Label(this, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label.setBounds(70, 119, 86, 29);
		label.setText("\u5BC6        \u7801:");
		
		txtPassword = new Text(this, SWT.BORDER | SWT.PASSWORD);
		txtPassword.setText("123456");
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.keyCode == 16777217) {
					txtID.setFocus();
				}
				if(e.keyCode == 13) {
					btnLogin.setFocus();
				}
			}
		});
		txtPassword.setBounds(162, 120, 129, 23);
		
		btnLogin = new Button(this, SWT.NONE);

		btnLogin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				login();
			}
		});
		btnLogin.setBounds(85, 186, 80, 27);
		btnLogin.setText("\u767B\u5F55");
		
		btnClear = new Button(this, SWT.NONE);
		btnClear.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
//				openSuccess = true;
//				ManagerShell.this.close();
//				isManagerOpen = false;
				
				txtID.setText("");
				txtPassword.setText("");
				
			}
		});
		btnClear.setBounds(202, 186, 80, 27);
		btnClear.setText("\u91CD\u7F6E");
		
		button = new Button(this, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageBox box = new MessageBox(ManagerShell.this, SWT.OK | SWT.CANCEL);
				box.setMessage("确认退出？");
				if(box.open() == SWT.OK) {
					close();
				}
			}
		});
		button.setToolTipText("\u9000\u51FA\u7CFB\u7EDF");
		button.setImage(SWTResourceManager.getImage(CashierShell.class, "/com/shxt/supersystem/picture/\u9000\u51FA.jpg"));
		button.setBounds(376, 245, 42, 42);
		
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("\u7BA1\u7406\u5458\u767B\u9646");
		setSize(420, 289);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	private void login() {
		MessageBox box = new MessageBox(ManagerShell.this);
		
		ID = txtID.getText();
		String password = txtPassword.getText();
		
		if("".equals(ID = ID.trim())) {
			box.setMessage("请输入用户名");
			box.open();
			return;
		}else if("".equals(password = password.trim())) {
			box.setMessage("请输入密码");
			box.open();
			return;
		}
		
		String sql = "SELECT cashier_password FROM cashier WHERE cashier_id = '" + ID + "' AND is_manager = '管理员'";
		
		JdbcTool jt = new JdbcTool();
		List<List<String>> list = jt.query(sql);
		
		if(list.size() == 0) {
			box.setMessage("您的ID错误，请重新输入");
			box.open();
			return;
		}else if(!password.equals(list.get(0).get(0))) {
			box.setMessage("您的ID或者密码错误，请重新输入");
			box.open();
			return;
		}
		openSuccess = true;
		ManagerShell.this.close();
	}
}
