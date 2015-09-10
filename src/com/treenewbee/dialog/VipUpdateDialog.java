package com.treenewbee.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Group;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

import com.treenewbee.util.JdbcTool;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class VipUpdateDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text txtVipId;
	private Text txtVipName;
	private Text txtVipAge;
	private Text txtVipAddr;
	private Text txtVipMail;
	private Text txtVipTel;
	private Text txtVipConsume;
	private Text txtVipLevel;
	private Text txtVipDiscount;
	private Text txtVipComment;
	private Button btnVipMan;
	private Button btnVipWomen;
	
	private String vip_id;
	private List<List<String>> vipList = new ArrayList<List<String>>();

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public VipUpdateDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(String vip_id) {
		this.vip_id = vip_id;
		
		JdbcTool jt = new JdbcTool();
		String sql = "SELECT * FROM vip WHERE vip_id = '" + vip_id + "'";
		vipList = jt.query(sql);
		
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
		shell.setSize(420, 606);
		shell.setText("\u4F1A\u5458\u8D44\u6599\u4FEE\u6539");
		
		Group group = new Group(shell, SWT.NONE);
		group.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		group.setText("\u57FA\u672C\u8D44\u6599");
		group.setBounds(0, 10, 415, 212);
		
		Label label = new Label(group, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label.setBounds(22, 37, 39, 19);
		label.setText("\u5361\u53F7\uFF1A");
		
		txtVipId = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		txtVipId.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		txtVipId.setBounds(67, 37, 124, 23);
		txtVipId.setText(vipList.get(0).get(0));
		
		Label label_1 = new Label(group, SWT.NONE);
		label_1.setText("\u59D3\u540D\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_1.setBounds(218, 37, 39, 19);
		
		txtVipName = new Text(group, SWT.BORDER);
		txtVipName.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		txtVipName.setBounds(264, 37, 107, 23);
		txtVipName.setText(vipList.get(0).get(1));
		
		Label label_2 = new Label(group, SWT.NONE);
		label_2.setText("\u6027\u522B\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_2.setBounds(22, 76, 39, 19);
		
		btnVipMan = new Button(group, SWT.RADIO);
		btnVipMan.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		btnVipMan.setBounds(77, 77, 44, 17);
		btnVipMan.setText("\u7537");
		if("男".equals(vipList.get(0).get(2))) {
			btnVipMan.setSelection(true);
		}
		
		btnVipWomen = new Button(group, SWT.RADIO);
		btnVipWomen.setText("\u5973");
		btnVipWomen.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		btnVipWomen.setBounds(136, 76, 44, 17);
		if("女".equals(vipList.get(0).get(2))) {
			btnVipWomen.setSelection(true);
		}
		
		Label label_3 = new Label(group, SWT.NONE);
		label_3.setText("\u5E74\u9F84\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_3.setBounds(218, 76, 39, 19);
		
		txtVipAge = new Text(group, SWT.BORDER);
		txtVipAge.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		txtVipAge.setBounds(264, 72, 73, 23);
		txtVipAge.setText(vipList.get(0).get(3));
		
		Label label_4 = new Label(group, SWT.NONE);
		label_4.setText("\u5C81");
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_4.setBounds(342, 76, 39, 19);
		
		Label label_5 = new Label(group, SWT.NONE);
		label_5.setText("\u5730\u5740\uFF1A");
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_5.setBounds(22, 168, 39, 19);
		
		txtVipAddr = new Text(group, SWT.BORDER);
		txtVipAddr.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		txtVipAddr.setBounds(67, 165, 304, 23);
		txtVipAddr.setText(vipList.get(0).get(4));
		
		Label label_6 = new Label(group, SWT.NONE);
		label_6.setText("\u90AE\u7BB1\uFF1A");
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_6.setBounds(22, 121, 39, 19);
		
		txtVipMail = new Text(group, SWT.BORDER);
		txtVipMail.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		txtVipMail.setBounds(67, 118, 124, 23);
		txtVipMail.setText(vipList.get(0).get(5));
		
		Label label_7 = new Label(group, SWT.NONE);
		label_7.setText("\u7535\u8BDD\uFF1A");
		label_7.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_7.setBounds(218, 121, 39, 19);
		
		txtVipTel = new Text(group, SWT.BORDER);
		txtVipTel.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		txtVipTel.setBounds(264, 121, 107, 23);
		txtVipTel.setText(vipList.get(0).get(6));
		
		Group group_1 = new Group(shell, SWT.NONE);
		group_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		group_1.setText("\u6D88\u8D39\u60C5\u51B5");
		group_1.setBounds(0, 230, 415, 172);
		
		Label label_8 = new Label(group_1, SWT.NONE);
		label_8.setText("\u6D88\u8D39\u91D1\u989D\uFF1A");
		label_8.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_8.setBounds(24, 46, 65, 19);
		
		txtVipConsume = new Text(group_1, SWT.BORDER);
		txtVipConsume.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		txtVipConsume.setBounds(96, 46, 124, 23);
		txtVipConsume.setText(vipList.get(0).get(7));
		
		Label label_9 = new Label(group_1, SWT.NONE);
		label_9.setText("\u5143");
		label_9.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_9.setBounds(226, 46, 65, 19);
		
		Label label_10 = new Label(group_1, SWT.NONE);
		label_10.setText("\u4F1A\u5458\u7B49\u7EA7\uFF1A");
		label_10.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_10.setBounds(24, 87, 65, 19);
		
		txtVipLevel = new Text(group_1, SWT.BORDER);
		txtVipLevel.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		txtVipLevel.setBounds(96, 87, 73, 23);
		txtVipLevel.setText(vipList.get(0).get(8));
		
		Label label_11 = new Label(group_1, SWT.NONE);
		label_11.setText("\u7EA7");
		label_11.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_11.setBounds(175, 87, 65, 19);
		
		Label label_12 = new Label(group_1, SWT.NONE);
		label_12.setText("\u4F1A\u5458\u6298\u6263\uFF1A");
		label_12.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_12.setBounds(24, 129, 65, 19);
		
		txtVipDiscount = new Text(group_1, SWT.BORDER);
		txtVipDiscount.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		txtVipDiscount.setBounds(96, 125, 73, 23);
		txtVipDiscount.setText(vipList.get(0).get(9));
		
		Label label_13 = new Label(group_1, SWT.NONE);
		label_13.setText("\u6298");
		label_13.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_13.setBounds(175, 129, 65, 19);
		
		Group group_2 = new Group(shell, SWT.NONE);
		group_2.setText("\u5907\u6CE8");
		group_2.setBounds(0, 408, 415, 113);
		
		txtVipComment = new Text(group_2, SWT.BORDER | SWT.WRAP);
		txtVipComment.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		txtVipComment.setBounds(10, 28, 395, 75);
		txtVipComment.setText(vipList.get(0).get(10));
		
		Button button_2 = new Button(shell, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				JdbcTool jt = new JdbcTool();
				String sql;
				if(btnVipMan.getSelection()) {
					sql = "UPDATE vip SET vip_name = '" + txtVipName.getText() + "', vip_sex = '" + btnVipMan.getText() + "',vip_age = '" + txtVipAge.getText() + "', vip_addr = '" + txtVipAddr.getText() + "', vip_mail = '" + txtVipMail.getText() + "',vip_tel = '" + txtVipTel.getText() + "',vip_consume = '" + txtVipConsume.getText() + "',vip_level = '" + txtVipLevel.getText() + "',vip_discount = '" + txtVipDiscount.getText() + "',vip_comment = '" + txtVipComment.getText() + "' WHERE vip_id = " + txtVipId.getText() + "";
					jt.update(sql);
				}
				if(btnVipWomen.getSelection()) {
					sql = "UPDATE vip SET vip_name = '" + txtVipName.getText() + "', vip_sex = '" + btnVipWomen.getText() + "',vip_age = '" + txtVipAge.getText() + "', vip_addr = '" + txtVipAddr.getText() + "', vip_mail = '" + txtVipMail.getText() + "',vip_tel = '" + txtVipTel.getText() + "',vip_consume = '" + txtVipConsume.getText() + "',vip_level = '" + txtVipLevel.getText() + "',vip_discount = '" + txtVipDiscount.getText() + "',vip_comment = '" + txtVipComment.getText() + "' WHERE vip_id = " + txtVipId.getText() + "";
					jt.update(sql);
				}
				
				MessageBox box = new MessageBox(shell);
				box.setMessage("修改成功！");
				if(box.open() == SWT.OK) {
					shell.close();
				}
			}
		});
		button_2.setFont(SWTResourceManager.getFont("微软雅黑", 20, SWT.BOLD));
		button_2.setBounds(0, 520, 415, 58);
		button_2.setText("\u786E     \u5B9A");

	}
}
