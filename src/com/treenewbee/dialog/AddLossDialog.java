package com.treenewbee.dialog;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.eclipse.ui.PlatformUI;

import com.treenewbee.util.JdbcTool;

import org.eclipse.swt.widgets.Combo;

public class AddLossDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Combo combo;
	private boolean shelve;
	private boolean storage;
	

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public AddLossDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(boolean shelve,boolean storage) {
		this.shelve = shelve;
		this.storage = storage;
		
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
		shell.setSize(365, 256);
		shell.setText("\u6DFB\u52A0\u635F\u8017");
		
		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label.setBounds(53, 20, 105, 25);
		label.setText("\u635F\u8017\u5546\u54C1\u7F16\u7801\uFF1A");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u635F\u8017\u5546\u54C1\u6570\u76EE\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_1.setBounds(53, 59, 105, 25);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("\u635F\u8017\u539F\u56E0\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_2.setBounds(53, 102, 79, 25);
		
		text = new Text(shell, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text.setBounds(164, 20, 135, 23);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_1.setBounds(164, 61, 135, 23);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageBox box = new MessageBox(shell);
				
				String goodID = text.getText().trim();
				String goodLossNum = text_1.getText().trim();
				String goodLossReason = combo.getText().trim();
				
				if ("".equals(goodID)) {
					box.setMessage("请输入商品编号");
					box.open();
					return;
				} else if ("".equals(goodLossNum)) {
					box.setMessage("请输入损耗数目");
					box.open();
					return;
				} else if ("".equals(goodLossReason)) {
					box.setMessage("请输入损耗原因");
					box.open();
					return;
				}
				
				JdbcTool jt = new JdbcTool();
				String sql = "SELECT i.good_id,SUM(i.storage_num) FROM import_good i GROUP BY i.good_id HAVING i.good_id = '" + goodID + "'";
				List<List<String>> list = jt.query(sql);
				if(list.size() == 0) {
					box.setMessage("无此商品，请重新输入！");
					box.open();
					return;
				}
				if(Float.parseFloat(goodLossNum) > Float.parseFloat(list.get(0).get(1))) {
					box.setMessage("输入有误，库存" + list.get(0).get(1) + "，请重新输入！");
					box.open();
					return;
				}
				
				
				
				Date now = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String nowdate = sdf.format(now);
				
				sql = "INSERT INTO loss VALUES ('" + nowdate + "', '" + text.getText() + "', '" + text_1.getText() + "', '" + combo.getText() + "')";
				jt.update(sql);
				
				
				if(shelve) {
					
					
					sql = "SELECT storage_num, shelve_num, import_id FROM import_good WHERE good_id = '" + goodID + "'AND storage_num > 0";
					
				}else if(storage) {
					sql = "SELECT storage_num, shelve_num, import_id FROM import_good WHERE good_id = '" + goodID + "'AND storage_num > 0 ORDER BY storage_num DESC";
				}
				
				Float lossNum = Float.parseFloat(goodLossNum);
				list = jt.query(sql);
				
				for(List<String> row : list) {
					Float storeNum = Float.parseFloat(row.get(0));
					
					if(storeNum >= lossNum) {
						storeNum = storeNum - lossNum;
						sql = "UPDATE import_good SET storage_num = " + storeNum + " WHERE import_id = '" + row.get(2) + "'";
						jt.update(sql);
						break;
					}else {
						lossNum = lossNum - storeNum;
						storeNum = 0f;
						sql = "UPDATE import_good SET storage_num = " + storeNum + " WHERE import_id = '" + row.get(2) + "'";
						jt.update(sql);
					}
				}
				
				box.setMessage("修改成功！");
				if(box.open() == SWT.OK) {
					shell.close();
				}
				
			}
		});
		btnNewButton.setBounds(105, 156, 114, 35);
		btnNewButton.setText("\u786E\u8BA4\u6DFB\u52A0");
		
		combo = new Combo(shell, SWT.NONE);
		combo.setBounds(160, 102, 199, 25);
		combo.add("商品过期");
		combo.add("商品包装损坏");
		combo.add("商品缺胳膊少腿");

	}
}
