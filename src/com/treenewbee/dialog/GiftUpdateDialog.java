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

import com.treenewbee.util.JdbcTool;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class GiftUpdateDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private String consume;
	
	private List<List<String>> giftList = new ArrayList<List<String>>();

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public GiftUpdateDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(String consume) {
		this.consume = consume;
		
		JdbcTool jt = new JdbcTool();
		String sql = "SELECT * FROM privilege WHERE vip_consume = '" + consume + "'";
		giftList = jt.query(sql);
		
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
		shell.setSize(399, 485);
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("\u6D88\u8D39\u91D1\u989D\uFF1A");
		label.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label.setBounds(56, 32, 75, 20);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u793C\u54C1\u7F16\u7801\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_1.setBounds(56, 72, 75, 20);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("\u793C\u54C1\u540D\u79F0\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_2.setBounds(56, 117, 75, 20);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setText("\u4F1A\u5458\u7B49\u7EA7\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_3.setBounds(56, 162, 75, 20);
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setText("\u4F1A\u5458\u6298\u6263\uFF1A");
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_4.setBounds(56, 211, 75, 20);
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setText("\u5907        \u6CE8\uFF1A");
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_5.setBounds(56, 263, 75, 20);
		
		text = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text.setBounds(137, 29, 90, 23);
		text.setText(giftList.get(0).get(2));
		
		Label label_6 = new Label(shell, SWT.NONE);
		label_6.setText("\u5143");
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_6.setBounds(233, 32, 40, 20);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(137, 69, 132, 23);
		text_1.setText(giftList.get(0).get(0));
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(137, 114, 132, 23);
		text_2.setText(giftList.get(0).get(1));
		
		text_3 = new Text(shell, SWT.BORDER);
		text_3.setBounds(137, 159, 90, 23);
		text_3.setText(giftList.get(0).get(3));
		
		Label label_7 = new Label(shell, SWT.NONE);
		label_7.setText("\u7EA7");
		label_7.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_7.setBounds(233, 162, 40, 20);
		
		text_4 = new Text(shell, SWT.BORDER);
		text_4.setBounds(137, 208, 90, 23);
		text_4.setText(giftList.get(0).get(4));
		
		Label label_8 = new Label(shell, SWT.NONE);
		label_8.setText("\u6298");
		label_8.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_8.setBounds(233, 211, 40, 20);
		
		text_5 = new Text(shell, SWT.BORDER | SWT.WRAP);
		text_5.setBounds(137, 263, 188, 94);
		text_5.setText(giftList.get(0).get(5));
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				JdbcTool jt = new JdbcTool();
				String sql = "UPDATE privilege SET good_id = '" + text_1.getText() + "', good_name = '" + text_2.getText() + "',vip_level = '" + text_3.getText() + "', vip_discount = '" + text_4.getText() + "', privilege_comment = '" + text_5.getText() + "' WHERE vip_consume = " + consume + "";
				jt.update(sql);
				
				MessageBox box = new MessageBox(shell);
				box.setMessage("修改成功！");
				if(box.open() == SWT.OK) {
					shell.close();
				}
			}
		});
		button.setText("\u786E\u8BA4\u4FEE\u6539");
		button.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button.setBounds(108, 392, 132, 27);

	}

}
