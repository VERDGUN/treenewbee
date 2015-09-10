package com.treenewbee.dialog;

import java.util.List;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.ui.PlatformUI;

import com.treenewbee.util.JdbcTool;

public class ShelveNewDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Label goodName;
	private Label goodNum;
	private float number;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ShelveNewDialog(Shell parent, int style) {
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
		shell.setSize(414, 276);
		shell.setText("\u65B0\u5546\u54C1\u4E0A\u67B6");
		
		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label.setBounds(49, 53, 75, 30);
		label.setText("\u5546\u54C1\u7F16\u7801\uFF1A");
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setText("\u4E0A\u67B6\u6570\u91CF\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_3.setBounds(49, 134, 75, 30);
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				JdbcTool jt = new JdbcTool();
				
				String sql = "SELECT good_name FROM good WHERE good_id = '" + text.getText() + "'";
				List<List<String>> list = jt.query(sql);
				if(list.size() == 0) {
					MessageBox box = new MessageBox(ShelveNewDialog.this.shell);
					box.setMessage("无此商品，请重新输入！");
					box.open();
					return;
				}
				goodName.setText(list.get(0).get(0));
				
				sql = "SELECT SUM(storage_num) FROM import_good where good_id = '" + text.getText() + "'";
				list = jt.query(sql);
				goodNum.setText(list.get(0).get(0));
				number = Float.parseFloat(list.get(0).get(0));
				
				
				
			}
		});
		button.setBounds(49, 89, 69, 27);
		button.setText("\u67E5\u770B\u5E93\u5B58");
		
		text = new Text(shell, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text.setBounds(130, 50, 158, 30);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_1.setBounds(130, 131, 158, 30);
		
		goodName = new Label(shell, SWT.NONE);
		goodName.setBounds(130, 89, 89, 27);
		
		goodNum = new Label(shell, SWT.NONE);
		goodNum.setBounds(225, 89, 89, 27);
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Float inputNum = Float.parseFloat(text_1.getText());
				
				if(inputNum > number) {
					Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
					MessageBox box = new MessageBox(shell);
					box.setMessage("库存不足，请重新输入！");
					box.open();
					return;
				}
				
				JdbcTool jt = new JdbcTool();
				String sql = "SELECT storage_num, shelve_num, import_id FROM import_good WHERE good_id = '" + text.getText() + "'AND storage_num > 0";
				List<List<String>> list = jt.query(sql);
				
				for(List<String> row : list) {
					Float storeNum = Float.parseFloat(row.get(0));
					Float shelveNum = Float.parseFloat(row.get(1));
					
					if(storeNum >= inputNum) {
						storeNum = storeNum - inputNum;
						shelveNum = shelveNum + inputNum;
						sql = "UPDATE import_good SET shelve_num = " + shelveNum + ", storage_num = " + storeNum + " WHERE import_id = '" + row.get(2) + "'";
						jt.update(sql);
						break;
					}else {
						
						shelveNum = shelveNum + storeNum;
						storeNum = 0f;
						sql = "UPDATE import_good SET shelve_num = " + shelveNum + ", storage_num = " + storeNum + " WHERE import_id = '" + row.get(2) + "'";
						jt.update(sql);
					}
				}
				
				shell.close();
				
			}
		});
		button_1.setText("\u786E\u5B9A");
		button_1.setBounds(97, 187, 168, 38);

	}
}
