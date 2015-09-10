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

public class GiftAdddialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public GiftAdddialog(Shell parent, int style) {
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
		shell.setSize(408, 458);
		
		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label.setBounds(50, 21, 75, 20);
		label.setText("\u6D88\u8D39\u91D1\u989D\uFF1A");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u793C\u54C1\u7F16\u7801\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_1.setBounds(50, 58, 75, 20);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("\u793C\u54C1\u540D\u79F0\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_2.setBounds(50, 102, 75, 20);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setText("\u4F1A\u5458\u7B49\u7EA7\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_3.setBounds(50, 144, 75, 20);
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setText("\u4F1A\u5458\u6298\u6263\uFF1A");
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_4.setBounds(50, 191, 75, 20);
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setText("\u5907        \u6CE8\uFF1A");
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_5.setBounds(50, 237, 75, 20);
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(149, 21, 90, 23);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(149, 58, 132, 23);
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(149, 102, 132, 23);
		
		text_3 = new Text(shell, SWT.BORDER);
		text_3.setBounds(149, 144, 90, 23);
		
		text_4 = new Text(shell, SWT.BORDER);
		text_4.setBounds(149, 191, 90, 23);
		
		text_5 = new Text(shell, SWT.BORDER | SWT.WRAP);
		text_5.setBounds(149, 237, 188, 94);
		
		Label label_6 = new Label(shell, SWT.NONE);
		label_6.setText("\u5143");
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_6.setBounds(245, 21, 40, 20);
		
		Label label_7 = new Label(shell, SWT.NONE);
		label_7.setText("\u7EA7");
		label_7.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_7.setBounds(245, 144, 25, 20);
		
		Label label_8 = new Label(shell, SWT.NONE);
		label_8.setText("\u6298");
		label_8.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_8.setBounds(245, 191, 25, 20);
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageBox box = new MessageBox(shell);
				
				String consume = text.getText().trim();
				String giftid = text_1.getText().trim();
				String geftName = text_2.getText().trim();
				String vipLevel = text_3.getText().trim();
				String vipdisCount = text_4.getText().trim();
				String comment = text_5.getText().trim();
				
				
				if("".equals(consume)) {
					box.setMessage("消费金额不能为空！");
					box.open();
					return;
				}else if("".equals(vipLevel)) {
					box.setMessage("会员等级不能为空");
					box.open();
					return;
				}else if("".equals(vipdisCount)) {
					box.setMessage("优惠折扣不能为空");
					box.open();
					return;
				}
					
				JdbcTool jt = new JdbcTool();
				String sql = "INSERT INTO privilege VALUES ('" + giftid + "','" + geftName + "', " + consume + ", " + vipLevel + ", " + vipdisCount + ",'" + comment + "')";
				jt.update(sql);
				
				box.setMessage("添加成功！");
				if(box.open() == SWT.OK) {
					shell.close();
				}
			}
		});
		button.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button.setBounds(132, 371, 132, 27);
		button.setText("\u786E\u8BA4\u6DFB\u52A0");
		
		Label label_9 = new Label(shell, SWT.NONE);
		label_9.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_9.setBounds(276, 144, 61, 20);
		label_9.setText("\uFF08\u5FC5\u586B\uFF09");
		
		Label label_10 = new Label(shell, SWT.NONE);
		label_10.setText("\uFF08\u5FC5\u586B\uFF09");
		label_10.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_10.setBounds(276, 191, 61, 20);
		
		Label label_11 = new Label(shell, SWT.NONE);
		label_11.setText("\uFF08\u5FC5\u586B\uFF09");
		label_11.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_11.setBounds(286, 21, 61, 20);

	}
}
