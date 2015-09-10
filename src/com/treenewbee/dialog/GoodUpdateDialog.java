package com.treenewbee.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

import com.treenewbee.util.JdbcTool;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;

public class GoodUpdateDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private List<List<String>> goodList = new ArrayList<List<String>>();
	private Text text_6;
	private Text text_7;
	private Combo combo;
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public GoodUpdateDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(String goodID) {
		
		JdbcTool jt = new JdbcTool();
		String sql = "SELECT * FROM good WHERE good_id = '" + goodID + "'";
		goodList = jt.query(sql);
		
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
		shell.setSize(381, 497);
		shell.setText("\u5C5E\u6027\u4FEE\u6539");
		
		Group group = new Group(shell, SWT.NONE);
		group.setText("\u57FA\u672C\u5C5E\u6027");
		group.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		group.setBounds(10, 10, 355, 126);
		
		Label label = new Label(group, SWT.NONE);
		label.setText("\u5546\u54C1\u7F16\u7801\uFF1A");
		label.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label.setBounds(27, 29, 65, 25);
		
		Label label_1 = new Label(group, SWT.NONE);
		label_1.setText("\u5546\u54C1\u540D\u79F0\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_1.setBounds(27, 61, 65, 25);
		
		Label label_2 = new Label(group, SWT.NONE);
		label_2.setText("\u5546\u54C1\u7C7B\u522B\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_2.setBounds(27, 92, 65, 25);
		
		text = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		text.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		text.setBounds(98, 29, 128, 23);
		text.setText(goodList.get(0).get(0));
		
		text_1 = new Text(group, SWT.BORDER);
		text_1.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		text_1.setBounds(98, 63, 128, 23);
		text_1.setText(goodList.get(0).get(1));
		
		combo = new Combo(group, SWT.NONE);
		combo.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		combo.setBounds(98, 92, 128, 27);
		/**遍历所有的商品类别*/
		JdbcTool jt = new JdbcTool();
		String sql = "SELECT DISTINCT good_type FROM good";
		List<List<String>> list = jt.query(sql);
		for(List<String> row : list) {
			combo.add(row.get(0));
		}
		combo.setText(goodList.get(0).get(2));
		
		Group group_1 = new Group(shell, SWT.NONE);
		group_1.setText("\u4EF7\u683C\u5C5E\u6027");
		group_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		group_1.setBounds(10, 142, 355, 166);
		
		Label label_3 = new Label(group_1, SWT.NONE);
		label_3.setText("\u5546\u54C1\u6210\u672C\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_3.setBounds(31, 33, 65, 25);
		
		Label label_4 = new Label(group_1, SWT.NONE);
		label_4.setText("\u9500\u552E\u4EF7\uFF1A");
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_4.setBounds(31, 74, 52, 25);
		
		Label label_5 = new Label(group_1, SWT.NONE);
		label_5.setText("\u6298\u6263\u4EF7\uFF1A");
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_5.setBounds(31, 117, 65, 25);
		
		text_3 = new Text(group_1, SWT.BORDER);
		text_3.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		text_3.setBounds(96, 33, 73, 23);
		text_3.setText(goodList.get(0).get(3));
		
		text_4 = new Text(group_1, SWT.BORDER);
		text_4.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		text_4.setBounds(96, 71, 73, 23);
		text_4.setText(goodList.get(0).get(4));
		
		text_5 = new Text(group_1, SWT.BORDER);
		text_5.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		text_5.setBounds(96, 114, 73, 23);
		text_5.setText(goodList.get(0).get(5));
		
		Label label_7 = new Label(group_1, SWT.NONE);
		label_7.setText("\u5143");
		label_7.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_7.setBounds(172, 33, 13, 25);
		
		Label label_8 = new Label(group_1, SWT.NONE);
		label_8.setText("\u5143");
		label_8.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_8.setBounds(175, 74, 13, 25);
		
		Label label_9 = new Label(group_1, SWT.NONE);
		label_9.setText("\u5143");
		label_9.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_9.setBounds(172, 117, 13, 25);
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				JdbcTool jt = new JdbcTool();
				String sql = "UPDATE good SET good_name = '" + text_1.getText() + "', good_type = '" + combo.getText() + "',good_cost = '" + text_3.getText() + "', good_price = '" + text_4.getText() + "', good_disprice = '" + text_5.getText() + "',good_profit = '" + (Float.parseFloat(text_5.getText()) - Float.parseFloat(text_4.getText())) + "',good_provider = '" + text_6.getText() + "',good_providertel = '" + text_7.getText() + "' WHERE good_id = " + text.getText() + "";
				jt.update(sql);
				
				MessageBox box = new MessageBox(shell);
				box.setMessage("添加成功！");
				if(box.open() == SWT.OK) {
					shell.close();
				}
			}
		});
		button.setText("\u786E\u8BA4\u4FEE\u6539");
		button.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.BOLD));
		button.setBounds(0, 418, 375, 51);
		
		Group group_2 = new Group(shell, SWT.NONE);
		group_2.setText("\u4F9B\u5E94\u5546\u4FE1\u606F");
		group_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		group_2.setBounds(10, 314, 355, 98);
		
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
		text_6.setText(goodList.get(0).get(8));
		
		
		text_7 = new Text(group_2, SWT.BORDER);
		text_7.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		text_7.setBounds(103, 65, 219, 23);
		text_7.setText(goodList.get(0).get(9));

	}

}
