package com.treenewbee.dialog;

import java.util.ArrayList;
import java.util.List;

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

public class WorkUpdateDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private String workerID;
	private List<List<String>> workerList = new ArrayList<List<String>>();
	
	private Button btnMan;
	private Button btnWomen;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public WorkUpdateDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(String workerID) {
		this.workerID = workerID;
		JdbcTool jt = new JdbcTool();
		String sql = "SELECT * FROM cashier WHERE cashier_id = '" + workerID + "'";
		workerList = jt.query(sql);
		
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
		shell.setSize(342, 425);
		shell.setText("\u5458\u5DE5\u4FE1\u606F\u4FEE\u6539");
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("\u5DE5      \u53F7\uFF1A");
		label.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label.setBounds(47, 22, 69, 20);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u5BC6      \u7801\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_1.setBounds(47, 60, 69, 20);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("\u59D3      \u540D\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_2.setBounds(47, 99, 69, 20);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setText("\u6027      \u522B\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_3.setBounds(47, 140, 69, 20);
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setText("\u5E74      \u9F84\uFF1A");
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_4.setBounds(47, 180, 69, 20);
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setText("\u5DE5      \u9F84\uFF1A");
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_5.setBounds(47, 220, 69, 20);
		
		Label label_6 = new Label(shell, SWT.NONE);
		label_6.setText("\u8054\u7CFB\u7535\u8BDD\uFF1A");
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_6.setBounds(47, 265, 75, 20);
		
		Label label_7 = new Label(shell, SWT.NONE);
		label_7.setText("\u6743        \u9650\uFF1A");
		label_7.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_7.setBounds(47, 310, 75, 20);
		
		text = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text.setBounds(122, 19, 123, 23);
		text.setText(workerList.get(0).get(0));
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_1.setBounds(122, 57, 123, 23);
		text_1.setText(workerList.get(0).get(1));
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_2.setBounds(122, 96, 123, 23);
		text_2.setText(workerList.get(0).get(3));
		
		btnMan = new Button(shell, SWT.RADIO);
		btnMan.setText("\u7537");
		btnMan.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		btnMan.setBounds(122, 142, 49, 17);
		if("男".equals(workerList.get(0).get(4))) {
			btnMan.setSelection(true);
		}
		
		btnWomen = new Button(shell, SWT.RADIO);
		btnWomen.setText("\u5973");
		btnWomen.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		btnWomen.setBounds(196, 143, 49, 17);
		if("女".equals(workerList.get(0).get(4))) {
			btnWomen.setSelection(true);
		}
		
		text_3 = new Text(shell, SWT.BORDER);
		text_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_3.setBounds(122, 177, 123, 23);
		text_3.setText(workerList.get(0).get(5));
		
		text_4 = new Text(shell, SWT.BORDER);
		text_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_4.setBounds(122, 217, 123, 23);
		text_4.setText(workerList.get(0).get(7));
		
		text_5 = new Text(shell, SWT.BORDER);
		text_5.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_5.setBounds(122, 262, 123, 23);
		text_5.setText(workerList.get(0).get(6));
		
		text_6 = new Text(shell, SWT.BORDER);
		text_6.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_6.setBounds(122, 307, 123, 23);
		text_6.setText(workerList.get(0).get(2));
		
		Button button_2 = new Button(shell, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				JdbcTool jt = new JdbcTool();
				String sql;
				if(btnMan.getSelection()) {
					sql = "UPDATE cashier SET cashier_password = '" + text_1.getText() + "', is_maneger = '" + text_6.getText() + "',cashier_name = '" + text_2.getText() + "', cashier_sex = '男', cashier_age = '" + text_3.getText() + "',cashier_tel = '" + text_5.getText() + "',cashier_workage = '" + text_4.getText() + "' WHERE cashier_id = " + workerID + "";
					jt.update(sql);
				}
				if(btnWomen.getSelection()) {
					sql = "UPDATE cashier SET cashier_password = '" + text_1.getText() + "', is_maneger = '" + text_6.getText() + "',cashier_name = '" + text_2.getText() + "', cashier_sex = '女', cashier_age = '" + text_3.getText() + "',cashier_tel = '" + text_5.getText() + "',cashier_workage = '" + text_4.getText() + "' WHERE cashier_id = " + workerID + "";
					jt.update(sql);
				}
				
				MessageBox box = new MessageBox(shell);
				box.setMessage("修改成功！");
				if(box.open() == SWT.OK) {
					shell.close();
				}
			}
		});
		button_2.setText("\u786E\u8BA4\u4FEE\u6539");
		button_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button_2.setBounds(99, 351, 140, 36);

	}

}
