package com.treenewbee.dialog;

import java.util.List;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

import com.treenewbee.util.JdbcTool;

import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CashierReturnDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Table table;
	private Table table_1;
	private List<List<String>> list;
	private Text txtSum;
	private int flag = 1;
	private float sum = 0;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public CashierReturnDialog(Shell parent, int style) {
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
		shell.setSize(592, 663);
		shell.setText("\u9000\u8D27\u6E05\u5355");
		
		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		label.setBounds(10, 10, 114, 25);
		label.setText("\u5C0F\u7968\u6D41\u6C34\u53F7\uFF1A");
		
		text = new Text(shell, SWT.BORDER);
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.keyCode == 13) {
					String waterNum = text.getText();
					JdbcTool jt = new JdbcTool();
					String sql = "SELECT s.sale_date, s.good_id,g.good_name,s.sale_num,s.sale_money FROM sale s JOIN good g ON s.good_id = g.good_id WHERE s.sale_id = '" + waterNum + "'";
					list = jt.query(sql);
					if(list.size() == 0) {
						MessageBox box = new MessageBox(shell);
						box.setMessage("输入错误，无此购买记录！");
						box.open();
						return;
					}
					
					
					for(List<String> row : list) {
						TableItem tableItem = new TableItem(table, SWT.NONE);
						tableItem.setText(0, row.get(0));
						tableItem.setText(1, row.get(1));
						tableItem.setText(2, row.get(2));
						tableItem.setText(3, row.get(3));
						tableItem.setText(4, row.get(4));
					}
					
					
				}
			}
		});
		text.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		text.setBounds(141, 10, 277, 25);
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(0, 41, 586, 252);
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(0, 0, 586, 252);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(118);
		tableColumn.setText("\u65F6\u95F4");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(129);
		tableColumn_1.setText("\u5546\u54C1\u7F16\u7801");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(128);
		tableColumn_2.setText("\u5546\u54C1\u540D\u79F0");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("\u8D2D\u4E70\u6570\u91CF");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("\u5C0F\u8BA1\u91D1\u989D");
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//获取用户选取的哪一行
				int index = table.getSelectionIndex();
				//获取行对象
				TableItem row = table.getItem(index);
				
				String saleDate = row.getText(0);
				String goodID = row.getText(1);
				String goodName = row.getText(2);
				String goodNum = row.getText(3);
				String goodMoney = row.getText(4);
				
				ReturnReasonDialog returnReasonDialog = new ReturnReasonDialog(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
				returnReasonDialog.open(goodID, goodName, goodNum, saleDate);
				
				TableItem tableItem = new TableItem(table_1, SWT.NONE);
				tableItem.setText(0, "" + flag);
				tableItem.setText(1, goodID);
				tableItem.setText(2, goodName);
				tableItem.setText(3, "" + ReturnReasonDialog.reNum);
				tableItem.setText(4, ReturnReasonDialog.reason);
				flag = flag + 1;
				
				sum = (Float.parseFloat(goodMoney)) * ReturnReasonDialog.reNum / (Float.parseFloat(goodNum)) + sum;
				txtSum.setText("" + sum);
				
			}
		});
		menuItem.setText("\u9000\u8D27");
		
		
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u9000\u8D27\u6E05\u5355\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		label_1.setBounds(10, 299, 114, 25);
		
		table_1 = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		table_1.setBounds(0, 330, 586, 252);
		
		TableColumn tableColumn_5 = new TableColumn(table_1, SWT.NONE);
		tableColumn_5.setWidth(69);
		tableColumn_5.setText("\u884C\u53F7");
		
		TableColumn tableColumn_6 = new TableColumn(table_1, SWT.NONE);
		tableColumn_6.setWidth(122);
		tableColumn_6.setText("\u5546\u54C1\u7F16\u7801");
		
		TableColumn tableColumn_7 = new TableColumn(table_1, SWT.NONE);
		tableColumn_7.setWidth(113);
		tableColumn_7.setText("\u5546\u54C1\u540D\u79F0");
		
		TableColumn tableColumn_8 = new TableColumn(table_1, SWT.NONE);
		tableColumn_8.setWidth(79);
		tableColumn_8.setText("\u9000\u8D27\u6570\u91CF");
		
		TableColumn tableColumn_9 = new TableColumn(table_1, SWT.NONE);
		tableColumn_9.setWidth(194);
		tableColumn_9.setText("\u9000\u8D27\u539F\u56E0");
		
		Button button = new Button(shell, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("微软雅黑", 20, SWT.BOLD));
		button.setBounds(0, 583, 586, 52);
		button.setText("\u9000   \u8D27   \u5B8C   \u6210");
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("\u603B\u8BA1\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		label_2.setBounds(366, 299, 66, 25);
		
		txtSum = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		txtSum.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		txtSum.setBounds(438, 299, 114, 25);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setText("\u5143");
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		label_3.setBounds(555, 299, 31, 25);

	}
}
