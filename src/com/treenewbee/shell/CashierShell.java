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

import com.treenewbee.util.JdbcTool;

import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class CashierShell extends Shell {
	private Text txtID;
	private Text txtPassword;
	private Button btnLogin;
	private Button btnClear;
	public static boolean isCashierOpen;
	public static boolean openSuccess;
	public static String id;
	private Button button;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void start() {
		isCashierOpen = true;
		openSuccess = false;
		try {
			Display display = Display.getDefault();
			CashierShell shell = new CashierShell(display);
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
	public CashierShell(Display display) {
		super(display, SWT.NONE);
		setBackgroundMode(SWT.INHERIT_FORCE);
		setBackgroundImage(SWTResourceManager.getImage(CashierShell.class, "/com/shxt/supersystem/picture/1374565585914.jpg"));
		setImage(SWTResourceManager.getImage(CashierShell.class, "/com/shxt/supersystem/picture/cashier.png"));
		
		int screenWidth=CashierShell.this.getMonitor().getClientArea().width;
		int screenHeight=CashierShell.this.getMonitor().getClientArea().height;
		int x=CashierShell.this.getSize().x;
		int y=CashierShell.this.getSize().y;
		if(x>screenWidth){
			CashierShell.this.getSize().x=screenWidth;
		}
		if(y>=screenHeight){
			CashierShell.this.getSize().y=screenHeight;
		}
		CashierShell.this.setLocation((screenWidth-x)/2,(screenHeight-y)/2);

		
		Label lblid = new Label(this, SWT.NONE);
		lblid.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		lblid.setBounds(78, 72, 81, 27);
		lblid.setText("\u5DE5       \u53F7\uFF1A");
		
		Label label = new Label(this, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label.setBounds(78, 115, 81, 27);
		label.setText("\u5BC6       \u7801\uFF1A");
		
		txtID = new Text(this, SWT.BORDER);
		txtID.setText("104");
		txtID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.keyCode == 16777218) {
					txtPassword.setFocus();
				}
			}
		});
		txtID.setBounds(165, 72, 118, 23);
		
		txtPassword = new Text(this, SWT.BORDER | SWT.PASSWORD);
		txtPassword.setText("456789");
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
		txtPassword.setBounds(165, 115, 118, 23);
		
		btnLogin = new Button(this, SWT.NONE);
		btnLogin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				login();
			}
		});
		btnLogin.setBounds(79, 175, 80, 27);
		btnLogin.setText("\u767B\u5F55");
		
		btnClear = new Button(this, SWT.NONE);
		btnClear.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
//				openSuccess = true;
//				CashierShell.this.close();
//				isCashierOpen = false;
				
				txtID.setText("");
				txtPassword.setText("");
			}
		});
		btnClear.setBounds(203, 175, 80, 27);
		btnClear.setText("\u91CD\u7F6E");
		
		button = new Button(this, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageBox box = new MessageBox(CashierShell.this, SWT.OK | SWT.CANCEL);
				box.setMessage("确认退出？");
				if(box.open() == SWT.OK) {
					close();
				}
			}
		});
		button.setToolTipText("\u9000\u51FA\u7CFB\u7EDF");
		button.setImage(SWTResourceManager.getImage(CashierShell.class, "/com/shxt/supersystem/picture/\u9000\u51FA.jpg"));
		button.setBounds(374, 245, 42, 42);
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("\u6536\u94F6\u5458\u767B\u9646");
		setSize(418, 289);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	
	private void login() {
		MessageBox box = new MessageBox(CashierShell.this);
		
		id = txtID.getText();
		String password = txtPassword.getText();
		
		if("".equals(id = id.trim())) {
			box.setMessage("请输入工号：");
			box.open();
			return;
		}else if("".equals(password = password.trim())) {
			box.setMessage("请输入密码");
			box.open();
			return;
		}
		
		String sql = "SELECT cashier_password FROM cashier WHERE cashier_id = '" + id + "' AND is_manager = '收银员'";
		
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
		CashierShell.this.close();
	}
}
