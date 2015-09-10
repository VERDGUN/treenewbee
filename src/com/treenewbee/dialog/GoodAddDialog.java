package com.treenewbee.dialog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.treenewbee.util.JdbcTool;

import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Combo;

public class GoodAddDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private Combo combo;
	private Text text_2;
	private Text text_8;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public GoodAddDialog(Shell parent, int style) {
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
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MIN | SWT.MAX);
		shell.setSize(381, 581);
		shell.setText("\u5546\u54C1\u6DFB\u52A0");
		
		Group group = new Group(shell, SWT.NONE);
		group.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		group.setText("\u57FA\u672C\u5C5E\u6027");
		group.setBounds(10, 10, 355, 126);
		
		Label label = new Label(group, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label.setBounds(27, 29, 65, 25);
		label.setText("\u5546\u54C1\u7F16\u7801\uFF1A");
		
		Label label_1 = new Label(group, SWT.NONE);
		label_1.setText("\u5546\u54C1\u540D\u79F0\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_1.setBounds(27, 61, 65, 25);
		
		Label label_2 = new Label(group, SWT.NONE);
		label_2.setText("\u5546\u54C1\u7C7B\u522B\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_2.setBounds(27, 92, 65, 25);
		
		text = new Text(group, SWT.BORDER);
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.keyCode == 13) {
					MessageBox box = new MessageBox(shell);
					if("".equals(text.getText().trim())) {
						box.setMessage("商品编码不能为空！");
						box.open();
						return;
					}else {
						text_1.setFocus();
					}
				}
			}
		});
		text.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		text.setBounds(98, 29, 128, 23);
		text.setFocus();
		
		text_1 = new Text(group, SWT.BORDER);
		text_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.keyCode == 13) {
					MessageBox box = new MessageBox(shell);
					if("".equals(text_1.getText().trim())) {
						box.setMessage("商品名称不能为空！");
						box.open();
						return;
					}else {
//						text_2.setFocus();
					}
				}
			}
		});
		text_1.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		text_1.setBounds(98, 63, 128, 23);
		
		combo = new Combo(group, SWT.NONE);
		combo.setBounds(98, 91, 128, 25);
		/**遍历所有的商品类别*/
		JdbcTool jt = new JdbcTool();
		String sql = "SELECT DISTINCT good_type FROM good";
		List<List<String>> list = jt.query(sql);
		for(List<String> row : list) {
			combo.add(row.get(0));
		}
		
		Group group_1 = new Group(shell, SWT.NONE);
		group_1.setText("\u4EF7\u683C\u5C5E\u6027");
		group_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		group_1.setBounds(10, 142, 355, 152);
		
		Label label_3 = new Label(group_1, SWT.NONE);
		label_3.setText("\u5546\u54C1\u6210\u672C\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_3.setBounds(31, 33, 65, 25);
		
		Label label_4 = new Label(group_1, SWT.NONE);
		label_4.setText("\u9500\u552E\u4EF7\uFF1A");
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_4.setBounds(31, 73, 52, 25);
		
		Label label_5 = new Label(group_1, SWT.NONE);
		label_5.setText("\u6298\u6263\u4EF7\uFF1A");
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_5.setBounds(31, 117, 65, 25);
		
		text_3 = new Text(group_1, SWT.BORDER);
		text_3.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		text_3.setBounds(96, 33, 73, 23);
		
		text_4 = new Text(group_1, SWT.BORDER);
		text_4.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		text_4.setBounds(96, 70, 73, 23);
		
		text_5 = new Text(group_1, SWT.BORDER);
		text_5.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		text_5.setBounds(96, 114, 73, 23);
		
		
		Label label_7 = new Label(group_1, SWT.NONE);
		label_7.setText("\u5143");
		label_7.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_7.setBounds(172, 33, 13, 25);
		
		Label label_8 = new Label(group_1, SWT.NONE);
		label_8.setText("\u5143");
		label_8.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_8.setBounds(172, 70, 13, 25);
		
		Label label_9 = new Label(group_1, SWT.NONE);
		label_9.setText("\u5143");
		label_9.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_9.setBounds(173, 117, 13, 25);
		
		Group group_2 = new Group(shell, SWT.NONE);
		group_2.setText("\u4F9B\u5E94\u5546\u4FE1\u606F");
		group_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		group_2.setBounds(10, 300, 355, 98);
		
		Label label_6 = new Label(group_2, SWT.NONE);
		label_6.setText("\u4F9B\u5E94\u5546\u5BB6\uFF1A");
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_6.setBounds(32, 29, 65, 25);
		
		Label label_10 = new Label(group_2, SWT.NONE);
		label_10.setText("\u8054\u7CFB\u7535\u8BDD\uFF1A");
		label_10.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_10.setBounds(32, 63, 65, 25);
		
		text_6 = new Text(group_2, SWT.BORDER);
		text_6.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		text_6.setBounds(103, 29, 219, 23);
		
		text_7 = new Text(group_2, SWT.BORDER);
		text_7.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		text_7.setBounds(103, 65, 219, 23);
		
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageBox box = new MessageBox(shell);
				
				String goodID = text.getText().trim();
				String goodName = text_1.getText().trim();
				String goodType = combo.getText().trim();
				String goodCost = text_3.getText().trim();
				String goodPrice = text_4.getText().trim();
				String goodDisprice = text_5.getText().trim();
				String goodProvider = text_6.getText().trim();
				String goodProviderTel = text_7.getText().trim();
				String goodImportNum = text_2.getText().trim();
				String goodOutDate = text_8.getText().trim();;
				
				
				if("".equals(goodID)) {
					box.setMessage("商品编码不能为空！");
					box.open();
					return;
				}else if("".equals(goodName)) {
					box.setMessage("商品名称不能为空");
					box.open();
					return;
				}else if("".equals(goodType)) {
					box.setMessage("商品类别不能为空");
					box.open();
					return;
				}else if("".equals(goodCost)) {
					box.setMessage("商品成本不能为空");
					box.open();
					return;
				}else if("".equals(goodPrice)) {
					box.setMessage("销售价不能为空");
					box.open();
					return;
				}else if("".equals(goodDisprice)) {
					box.setMessage("折扣价不能为空");
					box.open();
					return;
				}else if("".equals(goodProvider)) {
					box.setMessage("供应商家不能为空");
					box.open();
					return;
				}else if("".equals(goodProviderTel)) {
					box.setMessage("供应商家联系电话不能为空");
					box.open();
					return;
				}else if("".equals(goodImportNum)) {
					box.setMessage("进货数量不能为空");
					box.open();
					return;
				}else if("".equals(goodOutDate)) {
					box.setMessage("过期时间不能为空");
					box.open();
					return;
				}
			
					
				JdbcTool jt = new JdbcTool();
				String sql = "INSERT INTO good VALUES ('" + goodID + "','" + goodName + "', '" + goodType + "', " + goodCost + ", " + goodPrice + "," + goodDisprice + ", " + (Float.parseFloat(goodDisprice) - Float.parseFloat(goodCost)) + ", '', '" + goodProvider + "', '" + goodProviderTel + "')";
				jt.update(sql);
				
				Date now = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String nowtime = sdf.format(now);
				
				sql = "INSERT INTO import_good (import_date, good_id, import_num, shelve_num, storage_num, good_out_date) VALUES ('" + nowtime + "', '" + goodID + "', '" + goodImportNum + "', 0, '" + goodImportNum + "', '" + goodOutDate + "')";
				jt.update(sql);
				
				
				box.setMessage("添加成功！");
				if(box.open() == SWT.OK) {
					shell.close();
				}
			}
		});
		btnNewButton.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.BOLD));
		btnNewButton.setBounds(0, 503, 375, 51);
		btnNewButton.setText("\u786E\u8BA4\u6DFB\u52A0");
		
		Group group_3 = new Group(shell, SWT.NONE);
		group_3.setText("\u8FDB\u8D27\u4FE1\u606F");
		group_3.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		group_3.setBounds(10, 404, 355, 98);
		
		Label label_11 = new Label(group_3, SWT.NONE);
		label_11.setText("\u8FDB\u8D27\u6570\u91CF\uFF1A");
		label_11.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_11.setBounds(32, 29, 65, 25);
		
		Label label_12 = new Label(group_3, SWT.NONE);
		label_12.setText("\u8FC7\u671F\u65E5\u671F\uFF1A");
		label_12.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_12.setBounds(32, 63, 65, 25);
		
		text_2 = new Text(group_3, SWT.BORDER);
		text_2.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		text_2.setBounds(103, 29, 111, 23);
		
		text_8 = new Text(group_3, SWT.BORDER);
		text_8.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		text_8.setBounds(103, 65, 111, 23);
		
		Label label_13 = new Label(group_3, SWT.NONE);
		label_13.setText("(\u683C\u5F0F:2013-01-01)");
		label_13.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_13.setBounds(220, 65, 118, 23);

	}
}
