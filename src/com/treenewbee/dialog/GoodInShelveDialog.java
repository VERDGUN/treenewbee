package com.treenewbee.dialog;

import java.util.List;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

import com.treenewbee.util.JdbcTool;

import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class GoodInShelveDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private String goodID;
	private String goodName;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public GoodInShelveDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(String goodID, String goodName) {
		this.goodID = goodID;
		this.goodName = goodName;
		
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
		shell.setSize(461, 279);
		shell.setText("\u5546\u54C1\u4E0A\u67B6");
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("\u5546\u54C1\u7F16\u7801\uFF1A");
		label.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label.setBounds(68, 35, 75, 27);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u5546\u54C1\u540D\u79F0\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_1.setBounds(68, 75, 75, 27);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("\u5E93\u5B58\u6570\u91CF\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_2.setBounds(68, 118, 75, 27);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setText("\u4E0A\u67B6\u6570\u91CF\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_3.setBounds(68, 160, 75, 27);
		
		text = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text.setBounds(158, 35, 133, 27);
		text.setText(goodID);
		
		text_1 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_1.setBounds(158, 75, 133, 27);
		text_1.setText(goodName);
		
		text_2 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_2.setBounds(158, 118, 133, 27);
		JdbcTool jt = new JdbcTool();
		String sql = "SELECT SUM(storage_num) FROM import_good where good_id = '" + goodID + "'";
		List<List<String>> list = jt.query(sql);
		text_2.setText(list.get(0).get(0));
		
		text_3 = new Text(shell, SWT.BORDER);
		text_3.setFocus();
		text_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.keyCode == 13) {
					Float inputNum = Float.parseFloat(text_3.getText());
					
					JdbcTool jt = new JdbcTool();
					String sql = "SELECT storage_num, shelve_num, import_id FROM import_good WHERE good_id = '" + goodID + "'AND storage_num > 0";
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
				
			}
		});
		text_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_3.setBounds(158, 160, 133, 27);
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Float inputNum = Float.parseFloat(text_3.getText());
				
				JdbcTool jt = new JdbcTool();
				String sql = "SELECT storage_num, shelve_num, import_id FROM import_good WHERE good_id = '" + goodID + "'AND storage_num > 0";
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
						inputNum = inputNum - storeNum;
						shelveNum = shelveNum + storeNum;
						storeNum = 0f;
						sql = "UPDATE import_good SET shelve_num = " + shelveNum + ", storage_num = " + storeNum + " WHERE import_id = '" + row.get(2) + "'";
						jt.update(sql);
					}
				}
				
				shell.close();
			}
		});
		button.setText("\u786E\u5B9A");
		button.setBounds(124, 204, 130, 36);

	}

}
