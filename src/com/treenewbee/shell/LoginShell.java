package com.treenewbee.shell;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;

import com.treenewbee.core.Activator;

import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.MouseEvent;




public class LoginShell extends Shell {
	
	public static boolean shellOpen;
	public static boolean openSuccess;
	private Button btnManager;
	private Button btnCashier;
	private Label label_1;
	private Label label_2;
	private Button button;
	

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void start() {
		openSuccess = false;
		try {
			Display display = Display.getDefault();
			LoginShell shell = new LoginShell(display);
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
	public LoginShell(Display display) {
		super(display, SWT.NONE);
		setImage(SWTResourceManager.getImage(LoginShell.class, "/com/shxt/supersystem/picture/manager.png"));
		setBackground(SWTResourceManager.getColor(135, 206, 250));
		setBackgroundMode(SWT.INHERIT_FORCE);
		
		int screenWidth=LoginShell.this.getMonitor().getClientArea().width;
		int screenHeight=LoginShell.this.getMonitor().getClientArea().height;
		int x=LoginShell.this.getSize().x;
		int y=LoginShell.this.getSize().y;
		if(x>screenWidth){
			LoginShell.this.getSize().x=screenWidth;
		}
		if(y>=screenHeight){
			LoginShell.this.getSize().y=screenHeight;
		}
		LoginShell.this.setLocation((screenWidth-x)/2,(screenHeight-y)/2);
		
		Label label = new Label(this, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("方正彩云简体", 28, SWT.BOLD));
		label.setBounds(72, 10, 410, 46);
		label.setText("\u6B22\u8FCE\u8FDB\u5165\u8D85\u5E02\u6536\u94F6\u7CFB\u7EDF");
		
		btnManager = new Button(this, SWT.NONE);
		btnManager.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent e) {
				label_1.setVisible(true);
			}
			@Override
			public void mouseExit(MouseEvent e) {
				label_1.setVisible(false);
			}
		});
		btnManager.setImage(SWTResourceManager.getImage(LoginShell.class, "/com/shxt/supersystem/picture/manager.png"));
		btnManager.setImage(SWTResourceManager.getImage(LoginShell.class, "/com/shxt/supersystem/picture/manager.png"));
//		btnNewButton.setBackgroundImage(SWTResourceManager.getImage(LoginShell.class, "/com/shxt/supersystem/picture/maru.png"));

		btnManager.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shellOpen = true;
				openSuccess = true;
				LoginShell.this.close();
			}
		});
		btnManager.setFont(SWTResourceManager.getFont("方正彩云简体", 18, SWT.BOLD));
		btnManager.setBounds(45, 62, 229, 228);
		
		btnCashier = new Button(this, SWT.NONE);
		btnCashier.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent e) {
				label_2.setVisible(true);
			}
			@Override
			public void mouseExit(MouseEvent e) {
				label_2.setVisible(false);
			}
		});
		btnCashier.setImage(SWTResourceManager.getImage(LoginShell.class, "/com/shxt/supersystem/picture/cashier.png"));
		btnCashier.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shellOpen = false;
				openSuccess = true;
				LoginShell.this.close();
			}
		});
		btnCashier.setFont(SWTResourceManager.getFont("方正彩云简体", 16, SWT.BOLD));
		btnCashier.setBounds(280, 62, 229, 228);
		
		label_1 = new Label(this, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("方正彩云简体", 19, SWT.BOLD));
		label_1.setBounds(108, 296, 105, 30);
		label_1.setText("\u7BA1 \u7406 \u5458");
		label_1.setVisible(false);
		
		label_2 = new Label(this, SWT.NONE);
		label_2.setText("\u6536 \u94F6 \u5458");
		label_2.setFont(SWTResourceManager.getFont("方正彩云简体", 19, SWT.BOLD));
		label_2.setBounds(345, 296, 105, 30);
		label_2.setVisible(false);
		
		button = new Button(this, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageBox box = new MessageBox(LoginShell.this, SWT.OK | SWT.CANCEL);
				box.setMessage("确认退出？");
				if(box.open() == SWT.OK) {
					close();
				}
			}
		});
		button.setToolTipText("\u9000\u51FA\u7CFB\u7EDF");
		button.setImage(SWTResourceManager.getImage(LoginShell.class, "/com/shxt/supersystem/picture/612_\u526F\u672C.png"));
		button.setBounds(515, 306, 50, 49);
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("\u767B\u5F55\u754C\u9762");
		setSize(567, 357);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
