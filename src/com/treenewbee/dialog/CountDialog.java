package com.treenewbee.dialog;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Composite;

import com.treenewbee.view.CashierView;

import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Text;

public class CountDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private String paid;
	private Text text;
	private Text text_1;
	private Label lblVipMoney;
	private String vipMoney;
	private float sum;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public CountDialog(Shell parent, int style) {
		super(parent, style);
		setText("");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open(String money, String vipMoney, Float sum) {
		this.sum = sum;
		this.vipMoney = vipMoney;
		paid = money;
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
		shell = new Shell(getParent(), SWT.NONE);
		shell.setModified(true);
		shell.setSize(352, 327);

		Composite composite_1 = new Composite(shell, SWT.BORDER);
		composite_1.setBounds(10, 10, 331, 183);

		lblVipMoney = new Label(composite_1, SWT.NONE);
		lblVipMoney.setBounds(153, 120, 164, 46);
		lblVipMoney.setFont(SWTResourceManager.getFont("微软雅黑", 21, SWT.BOLD));
		lblVipMoney.setText(vipMoney);

		Label label_7 = new Label(composite_1, SWT.NONE);
		label_7.setBounds(40, 120, 105, 46);
		label_7.setText("\u4F1A\u5458\u4EF7\uFF1A");
		label_7.setFont(SWTResourceManager.getFont("微软雅黑", 20, SWT.BOLD));

		Label label = new Label(composite_1, SWT.NONE);
		label.setBounds(40, 59, 105, 46);
		label.setText("\u5E94   \u4ED8\uFF1A");
		label.setFont(SWTResourceManager.getFont("微软雅黑", 21, SWT.BOLD));

		Label label_4 = new Label(composite_1, SWT.NONE);
		label_4.setBounds(153, 60, 156, 34);
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 21, SWT.BOLD));
		label_4.setText("" + sum);

		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setBounds(40, 0, 105, 46);
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 21, SWT.BOLD));
		lblNewLabel.setText("\u5408   \u8BA1\uFF1A");

		Label label_3 = new Label(composite_1, SWT.NONE);
		label_3.setBounds(153, 1, 156, 35);
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 21, SWT.BOLD));
		label_3.setText("" + sum);

		Composite composite = new Composite(shell, SWT.BORDER);
		composite.setBounds(10, 199, 331, 116);

		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setBounds(38, 66, 105, 36);
		label_2.setText("\u627E   \u96F6\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 21, SWT.BOLD));

		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setBounds(38, 10, 105, 46);
		label_1.setText("\u5DF2   \u4ED8\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 21, SWT.BOLD));

		text_1 = new Text(composite, SWT.BORDER);
		text_1.setFont(SWTResourceManager.getFont("微软雅黑", 21, SWT.BOLD));
		text_1.setBounds(149, 10, 168, 46);
		text_1.setFocus();
		text_1.setText(paid);

		text_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 13) {

					float rest = Float.parseFloat(text_1.getText())
							- Float.parseFloat(lblVipMoney.getText());
					rest = (float) (((int) (rest * 10)) / 10.0);
					text.setText("" + rest);
					text.setFocus();
				}
			}
		});

		text = new Text(composite, SWT.BORDER);
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				shell.close();
			}
		});
		text.setFont(SWTResourceManager.getFont("微软雅黑", 21, SWT.BOLD));
		text.setBounds(149, 66, 168, 46);
		float rest = Float.parseFloat(text_1.getText())
				- Float.parseFloat(lblVipMoney.getText());
		rest = (float) (((int) (rest * 10)) / 10.0);
		text.setText("" + rest);

	}
}
