package com.treenewbee.dialog;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.treenewbee.util.JdbcTool;

public class WorkerAddDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Button btnMan;
	private Button btnWomen;
	private Label label_8;
	private Text text_6;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public WorkerAddDialog(Shell parent, int style) {
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
		shell = new Shell(getParent(), getStyle());
		shell.setSize(337, 402);
		shell.setText("\u5458\u5DE5\u4FE1\u606F\u6DFB\u52A0");
		
		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label.setBounds(38, 20, 69, 20);
		label.setText("\u5DE5      \u53F7\uFF1A");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u5BC6      \u7801\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_1.setBounds(38, 59, 69, 20);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("\u59D3      \u540D\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_2.setBounds(38, 98, 69, 20);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setText("\u6027      \u522B\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_3.setBounds(38, 136, 69, 20);
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setText("\u5E74      \u9F84\uFF1A");
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_4.setBounds(38, 174, 69, 20);
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setText("\u8054\u7CFB\u7535\u8BDD\uFF1A");
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_5.setBounds(38, 262, 75, 20);
		
		Label label_6 = new Label(shell, SWT.NONE);
		label_6.setText("\u5DE5      \u9F84\uFF1A");
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_6.setBounds(38, 220, 69, 20);
		
		text = new Text(shell, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text.setBounds(113, 20, 123, 23);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_1.setBounds(113, 56, 123, 23);
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_2.setBounds(113, 98, 123, 23);
		
		text_3 = new Text(shell, SWT.BORDER);
		text_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_3.setBounds(113, 174, 123, 23);
		
		text_4 = new Text(shell, SWT.BORDER);
		text_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_4.setBounds(113, 259, 123, 23);
		
		text_5 = new Text(shell, SWT.BORDER);
		text_5.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_5.setBounds(113, 217, 123, 23);
		
		btnMan = new Button(shell, SWT.RADIO);
		btnMan.setSelection(true);
		btnMan.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		btnMan.setBounds(113, 136, 49, 17);
		btnMan.setText("\u7537");
		
		btnWomen = new Button(shell, SWT.RADIO);
		btnWomen.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		btnWomen.setText("\u5973");
		btnWomen.setBounds(187, 136, 49, 17);
		
		Label label_7 = new Label(shell, SWT.NONE);
		label_7.setText("\uFF08\u552F\u4E00\uFF09");
		label_7.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_7.setBounds(242, 20, 69, 20);
		
		Button button_2 = new Button(shell, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageBox box = new MessageBox(shell);
				
				String workerID = text.getText().trim();
				String workerPassword = text_1.getText().trim();
				String workerName = text_2.getText().trim();
				String workerAge = text_3.getText().trim();
				String workerTel = text_4.getText().trim();
				String workAge = text_5.getText().trim();
				String isManager = text_6.getText().trim();
				
				if("".equals(workerID)) {
					box.setMessage("员工工号不能为空");
					box.open();
					return;
				}else if("".equals(workerPassword)) {
					box.setMessage("员工密码不能为空");
					box.open();
					return;
				}else if("".equals(workerName)) {
					box.setMessage("员工姓名不能为空");
					box.open();
					return;
				}else if("".equals(workerAge)) {
					box.setMessage("员工年龄不能为空");
					box.open();
					return;
				}else if("".equals(workerTel)) {
					box.setMessage("员工电话不能为空");
					box.open();
					return;
				}else if("".equals(workAge)) {
					box.setMessage("员工工龄不能为空");
					box.open();
					return;
				}else if("".equals(isManager)) {
					box.setMessage("员工权限不能为空");
					box.open();
					return;
				}
					
				JdbcTool jt = new JdbcTool();
				String sql;
				if(btnMan.getSelection()) {
					
					sql = "INSERT INTO cashier VALUES ('" + workerID + "','" + workerPassword + "', '" + isManager + "', '" + workerName + "', '男', '" + workerAge + "', '" + workerTel + "', '" + workAge + "')";
					jt.update(sql);
					
					
				}
				if(btnWomen.getSelection()) {
				
					sql = "INSERT INTO cashier VALUES ('" + workerID + "','" + workerPassword + "', '" + isManager + "', '" + workerName + "', '女', '" + workerAge + "', '" + workerTel + "', '" + workAge + "')";
					jt.update(sql);
					
				}
				
				box.setMessage("添加成功！");
				if(box.open() == SWT.OK) {
					shell.close();
				}
			}
		});
		button_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button_2.setBounds(81, 338, 140, 36);
		button_2.setText("\u786E\u8BA4\u6DFB\u52A0");
		
		label_8 = new Label(shell, SWT.NONE);
		label_8.setText("\u6743        \u9650\uFF1A");
		label_8.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_8.setBounds(38, 300, 75, 20);
		
		text_6 = new Text(shell, SWT.BORDER);
		text_6.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_6.setBounds(113, 297, 123, 23);

	}
}
