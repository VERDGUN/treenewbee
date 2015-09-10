package com.treenewbee.dialog;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.treenewbee.util.JdbcTool;

import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class VipRegistreDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text txtVipName;
	private Text txtVipAge;
	private Text txtVipAddr;
	private Text txtVipMail;
	private Text txtVipTel;
	private Text txtVipComment;
	private Button btnVipMan;
	private Button btnVipWomen;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public VipRegistreDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MIN | SWT.MAX);
		shell.setSize(417, 406);
		
		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label.setBounds(74, 25, 61, 17);
		label.setText("\u59D3    \u540D\uFF1A");
		
		txtVipName = new Text(shell, SWT.BORDER);
		txtVipName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.keyCode == 16777218) {
					btnVipMan.setFocus();
				}
			}
		});
		txtVipName.setBounds(156, 25, 104, 23);
		txtVipName.setFocus();
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_1.setBounds(74, 61, 61, 17);
		label_1.setText("\u6027    \u522B\uFF1A");
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_2.setBounds(74, 97, 61, 17);
		label_2.setText("\u5E74    \u9F84\uFF1A");
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_3.setBounds(74, 135, 61, 23);
		label_3.setText("\u5730    \u5740\uFF1A");
		
		Label lblMail = new Label(shell, SWT.NONE);
		lblMail.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		lblMail.setBounds(74, 173, 75, 23);
		lblMail.setText("\u7535\u5B50\u90AE\u4EF6\uFF1A");
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_4.setBounds(74, 210, 75, 23);
		label_4.setText("\u8054\u7CFB\u7535\u8BDD\uFF1A");
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_5.setBounds(74, 250, 61, 23);
		label_5.setText("\u5907    \u6CE8\uFF1A");
		
		txtVipAge = new Text(shell, SWT.BORDER);
		txtVipAge.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.keyCode == 16777218) {
					txtVipAddr.setFocus();
				}
				if(e.keyCode == 16777217) {
					txtVipName.setFocus();
				}
			}
		});
		txtVipAge.setBounds(156, 91, 104, 23);
		
		txtVipAddr = new Text(shell, SWT.BORDER);
		txtVipAddr.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.keyCode == 16777218) {
					txtVipMail.setFocus();
				}
				if(e.keyCode == 16777217) {
					txtVipAge.setFocus();
				}
			}
		});
		txtVipAddr.setBounds(156, 135, 142, 23);
		
		txtVipMail = new Text(shell, SWT.BORDER);
		txtVipMail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.keyCode == 16777218) {
					txtVipTel.setFocus();
				}
				if(e.keyCode == 16777217) {
					txtVipAddr.setFocus();
				}
			}
		});
		txtVipMail.setBounds(156, 173, 142, 23);
		
		txtVipTel = new Text(shell, SWT.BORDER);
		txtVipTel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.keyCode == 16777218) {
					txtVipComment.setFocus();
				}
				if(e.keyCode == 16777217) {
					txtVipMail.setFocus();
				}
			}
		});
		txtVipTel.setBounds(156, 210, 142, 23);
		
		txtVipComment = new Text(shell, SWT.BORDER | SWT.WRAP);
		txtVipComment.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.keyCode == 16777217) {
					txtVipTel.setFocus();
				}
			}
		});
		txtVipComment.setBounds(156, 250, 197, 54);
		
		btnVipMan = new Button(shell, SWT.RADIO);
		btnVipMan.setBounds(156, 61, 49, 17);
		btnVipMan.setText("\u7537");
		
		btnVipWomen = new Button(shell, SWT.RADIO);
		btnVipWomen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.keyCode == 16777218) {
					txtVipAge.setFocus();
				}
				
			}
		});
		btnVipWomen.setBounds(216, 61, 49, 17);
		btnVipWomen.setText("\u5973");
		
		Button button_2 = new Button(shell, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageBox box = new MessageBox(shell);
				
				String vipName = txtVipName.getText().trim();
				String vipMail = txtVipMail.getText().trim();
				String vipTel = txtVipTel.getText().trim();
				
				if("".equals(vipName)) {
					box.setMessage("会员姓名不能为空");
					box.open();
					return;
				}else if("".equals(vipMail)) {
					box.setMessage("会员邮箱不能为空");
					box.open();
					return;
				}else if("".equals(vipTel)) {
					box.setMessage("会员电话不能为空");
					box.open();
					return;
				}else if("".equals(txtVipAge.getText().trim())) {
					box.setMessage("会员年龄不能为空");
					box.open();
					return;
				}
					
				JdbcTool jt = new JdbcTool();
				String sql;
				if(btnVipMan.getSelection()) {
					sql = "INSERT INTO vip (vip_name, vip_sex,vip_age,vip_addr,vip_mail,vip_tel,vip_consume,vip_level,vip_discount,vip_comment) VALUES ('" + vipName + "','" + btnVipMan.getText() + "', '" + txtVipAge.getText() + "', '" + txtVipAddr.getText() + "', '" + vipMail + "', '" + vipTel + "', 0, 1, 0.9, '')";
					jt.update(sql);
				}
				if(btnVipWomen.getSelection()) {
					sql = "INSERT INTO vip (vip_name, vip_sex,vip_age,vip_addr,vip_mail,vip_tel,vip_consume,vip_level,vip_discount,vip_comment) VALUES ('" + vipName + "','" + btnVipWomen.getText() + "', '" + txtVipAge.getText() + "', '" + txtVipAddr.getText() + "', '" + vipMail + "', '" + vipTel + "', 0, 1, 0.9, '')";
					jt.update(sql);
				}
				
				box.setMessage("添加成功！");
				if(box.open() == SWT.OK) {
					shell.close();
				}
				
			}
		});
		button_2.setBounds(164, 319, 96, 27);
		button_2.setText("\u786E\u8BA4\u6DFB\u52A0");
		
		Label label_6 = new Label(shell, SWT.NONE);
		label_6.setBounds(280, 31, 61, 17);
		label_6.setText("\uFF08\u5FC5\u586B\uFF09");
		
		Label label_7 = new Label(shell, SWT.NONE);
		label_7.setText("\uFF08\u5FC5\u586B\uFF09");
		label_7.setBounds(317, 216, 61, 17);
		
		Label label_8 = new Label(shell, SWT.NONE);
		label_8.setText("\uFF08\u5FC5\u586B\uFF09");
		label_8.setBounds(317, 179, 61, 17);
		
		Label label_9 = new Label(shell, SWT.NONE);
		label_9.setText("\uFF08\u5FC5\u586B\uFF09");
		label_9.setBounds(280, 97, 61, 17);

	}
}
