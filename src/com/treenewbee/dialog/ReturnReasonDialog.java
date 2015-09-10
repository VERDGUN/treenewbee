package com.treenewbee.dialog;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.treenewbee.util.JdbcTool;

public class ReturnReasonDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text txtID;
	private Text txtName;
	private Text txtReNum;
	private String goodID;
	private String goodName;
	private String goodNum;
	private String saleDate;
	private Label lblNum;
	private Combo combo;
	public static Float reNum;
	public static String reason;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ReturnReasonDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(String goodID, String goodName, String goodNum, String saleDate) {
		this.goodID = goodID;
		this.goodName = goodName;
		this.goodNum = goodNum;
		this.saleDate = saleDate;
		
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
		shell = new Shell(getParent(), SWT.BORDER | SWT.CLOSE);
		shell.setSize(367, 318);
		
		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		label.setBounds(31, 43, 92, 30);
		label.setText("\u5546\u54C1\u7F16\u53F7\uFF1A");
		
		txtID = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		txtID.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		txtID.setBounds(134, 43, 172, 30);
		txtID.setText(goodID);
		
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u5546\u54C1\u540D\u79F0\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		label_1.setBounds(31, 96, 92, 30);
		
		txtName = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		txtName.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		txtName.setBounds(134, 96, 172, 30);
		txtName.setText(goodName);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("\u9000\u8D27\u6570\u91CF\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		label_2.setBounds(31, 148, 92, 30);
		
		txtReNum = new Text(shell, SWT.BORDER);
		txtReNum.setFocus();
		txtReNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.keyCode == 13) {
					if("".equals(txtReNum.getText().trim())) {
						MessageBox box = new MessageBox(shell);
						box.setMessage("请输入退货数量！");
						box.open();
						return;
					}
					reNum = Float.parseFloat(txtReNum.getText().trim());
					Float num = Float.parseFloat(goodNum);
					if(reNum > num) {
						MessageBox box = new MessageBox(shell);
						box.setMessage("输入不在范围之内");
						box.open();
						return;	
					}else {
						combo.setFocus();
					}
				}
			}
		});
		txtReNum.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		txtReNum.setBounds(134, 148, 92, 30);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setText("\u9000\u8D27\u539F\u56E0\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		label_3.setBounds(31, 195, 92, 30);
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setText("\uFF081~");
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		label_4.setBounds(232, 148, 51, 30);
		
		lblNum = new Label(shell, SWT.NONE);
		lblNum.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		lblNum.setBounds(282, 148, 48, 30);
		lblNum.setText(goodNum);
		
		Label label_6 = new Label(shell, SWT.NONE);
		label_6.setText("\uFF09");
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		label_6.setBounds(336, 148, 19, 30);
		
		combo = new Combo(shell, SWT.NONE);
		combo.setBounds(134, 200, 221, 25);
		combo.add("商品过期");
		combo.add("商品包装损坏");
		combo.add("商品缺胳膊少腿");
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if("".equals(combo.getText().trim())) {
					MessageBox box = new MessageBox(shell);
					box.setMessage("请输入退货原因！");
					box.open();
					return;
				}
				
				
				if("".equals(txtReNum.getText().trim())) {
					MessageBox box = new MessageBox(shell);
					box.setMessage("请输入退货数量！");
					box.open();
					return;
				}
				reNum = Float.parseFloat(txtReNum.getText().trim());
				Float num = Float.parseFloat(goodNum);
				if(reNum > num) {
					MessageBox box = new MessageBox(shell);
					box.setMessage("输入不在范围之内");
					box.open();
					return;
				}
				Float restNum = num - reNum;
				
				JdbcTool jt = new JdbcTool();
				String sql = "UPDATE sale SET sale_num = " + restNum + ", sale_money = (sale_money * " + restNum + " / " + num + ") WHERE sale_date = '" + saleDate + "' AND good_id = '" + goodID + "'";
				jt.update(sql);
				
				
				Date now = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String nowdate = sdf.format(now);
				sql = "INSERT INTO loss VALUES ('" + nowdate + "', '" + goodID + "', '" + reNum + "', '" + combo.getText() + "')";
				jt.update(sql);
				reason = combo.getText();
				
				MessageBox box = new MessageBox(shell);
				box.setMessage("退货成功！");
				if(box.open() == SWT.OK) {
					shell.close();
				}
			}
		});
		button.setBounds(75, 248, 208, 37);
		button.setText("\u786E\u5B9A\u9000\u8D27");

	}
}
