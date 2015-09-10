package com.treenewbee.view;

import java.util.List;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.treenewbee.util.JdbcTool;

public class ExpenseStaticsView extends ViewPart {

	public static final String ID = "com.shxt.supersystem.view.ExpenseStaticsView"; //$NON-NLS-1$
	private Table table;
	private Table table_1;
	private Table table_2;
	private DateTime dateTime;
	
	private Label lblAllCost;
	private Label lblImportCost;
	private Label lblLossCost;
	private Label lblGiftCost;

	public ExpenseStaticsView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundMode(SWT.INHERIT_FORCE);
		container.setBackgroundImage(SWTResourceManager.getImage(ExpenseStaticsView.class, "/com/shxt/supersystem/picture/\u84DD\u8272_\u526F\u672C.jpg"));
		
		Label label = new Label(container, SWT.CENTER);
		label.setText("\u652F\u51FA\u65E5\u5386");
		label.setFont(SWTResourceManager.getFont("微软雅黑", 22, SWT.BOLD));
		label.setBounds(0, 0, 218, 42);
		
		dateTime = new DateTime(container, SWT.CALENDAR);
		dateTime.setBounds(0, 48, 218, 159);
		
		/**当月查询*/
		Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int year = dateTime.getYear();
				int month = dateTime.getMonth() + 1;
				
				JdbcTool jt = new JdbcTool();
				String sql = "SELECT i.import_id,i.import_date,i.good_id,g.good_name,g.good_cost,i.import_num, (g.good_cost * i.import_num) FROM import_good i JOIN good g ON g.good_id = i.good_id WHERE i.import_date BETWEEN '" + year + "-" + month + "-01 00:00:00' AND '" + year + "-" + month + "-31 23:59:59'";
				List<List<String>> list = jt.query(sql);
				
				Float importSum = 0f;
				int flag = 1;
				table.removeAll();
				for(List<String> row : list) {
					TableItem tableItem = new TableItem(table, SWT.NONE);
					tableItem.setText(0, "" + flag);
					tableItem.setText(1, row.get(0));
					tableItem.setText(2, row.get(1));
					tableItem.setText(3, row.get(2));
					tableItem.setText(4, row.get(3));
					tableItem.setText(5, row.get(4));
					tableItem.setText(6, row.get(5));
					tableItem.setText(7, row.get(6));
					flag = flag + 1;
					importSum = importSum + Float.parseFloat(row.get(6));
				}
				
				lblImportCost.setText("" + importSum);
				
				
				sql = "SELECT l.loss_date,l.good_id, g.good_name,g.good_cost,l.loss_num, (g.good_cost * l.loss_num), l.loss_comment FROM loss l JOIN good g ON g.good_id = l.good_id WHERE l.loss_date BETWEEN '" + year + "-" + month + "-01 00:00:00' AND '" + year + "-" + month + "-31 23:59:59'";
				list = jt.query(sql);
				Float lossSum = 0f;
				flag = 1;
				table_1.removeAll();
				for(List<String> row : list) {
					TableItem tableItem = new TableItem(table_1, SWT.NONE);
					tableItem.setText(0, "" + flag);
					tableItem.setText(1, row.get(0));
					tableItem.setText(2, row.get(1));
					tableItem.setText(3, row.get(2));
					tableItem.setText(4, row.get(3));
					tableItem.setText(5, row.get(4));
					tableItem.setText(6, row.get(5));
					tableItem.setText(7, row.get(6));
					flag = flag + 1;
					lossSum = lossSum + Float.parseFloat(row.get(5));
				}
				
				lblLossCost.setText("" + lossSum);
				
				sql = "SELECT i.gift_date,i.vip_id, v.vip_consume,i.good_id,g.good_name,g.good_cost FROM gift i JOIN good g ON g.good_id = i.good_id JOIN vip v ON v.vip_id = i.vip_id WHERE i.gift_date BETWEEN '" + year + "-" + month + "-01 00:00:00' AND '" + year + "-" + month + "-31 23:59:59'";
				list = jt.query(sql);
				Float giftSum = 0f;
				flag = 1;
				table_2.removeAll();
				for(List<String> row : list) {
					TableItem tableItem = new TableItem(table_2, SWT.NONE);
					tableItem.setText(0, "" + flag);
					tableItem.setText(1, row.get(0));
					tableItem.setText(2, row.get(1));
					tableItem.setText(3, row.get(2));
					tableItem.setText(4, row.get(3));
					tableItem.setText(5, row.get(4));
					tableItem.setText(6, row.get(5));
					flag = flag + 1;
					giftSum = giftSum + Float.parseFloat(row.get(5));
				}
				
				lblGiftCost.setText("" + giftSum);
				
				Float sum = importSum + lossSum + giftSum;
				lblAllCost.setText("" + sum);
			}
		});
		button.setText("\u5F53\u6708\u67E5\u8BE2");
		button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		button.setBounds(58, 241, 90, 51);
		
		/**当月查询*/
		Button button_1 = new Button(container, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int year = dateTime.getYear();
				int month = dateTime.getMonth() + 1;
				int day = dateTime.getDay();
				
				JdbcTool jt = new JdbcTool();
				String sql = "SELECT i.import_id,i.import_date,i.good_id,g.good_name,g.good_cost,i.import_num, (g.good_cost * i.import_num) FROM import_good i JOIN good g ON g.good_id = i.good_id WHERE i.import_date BETWEEN '" + year + "-" + month + "-" + day + " 00:00:00' AND '" + year + "-" + month + "-" + day + " 23:59:59'";
				List<List<String>> list = jt.query(sql);
				
				Float importSum = 0f;
				int flag = 1;
				table.removeAll();
				for(List<String> row : list) {
					TableItem tableItem = new TableItem(table, SWT.NONE);
					tableItem.setText(0, "" + flag);
					tableItem.setText(1, row.get(0));
					tableItem.setText(2, row.get(1));
					tableItem.setText(3, row.get(2));
					tableItem.setText(4, row.get(3));
					tableItem.setText(5, row.get(4));
					tableItem.setText(6, row.get(5));
					tableItem.setText(7, row.get(6));
					flag = flag + 1;
					importSum = importSum + Float.parseFloat(row.get(6));
				}
				
				lblImportCost.setText("" + importSum);
				
				
				sql = "SELECT l.loss_date,l.good_id, g.good_name,g.good_cost,l.loss_num, (g.good_cost * l.loss_num), l.loss_comment FROM loss l JOIN good g ON g.good_id = l.good_id WHERE l.loss_date BETWEEN '" + year + "-" + month + "-" + day + " 00:00:00' AND '" + year + "-" + month + "-" + day + " 23:59:59'";
				list = jt.query(sql);
				Float lossSum = 0f;
				flag = 1;
				table_1.removeAll();
				for(List<String> row : list) {
					TableItem tableItem = new TableItem(table_1, SWT.NONE);
					tableItem.setText(0, "" + flag);
					tableItem.setText(1, row.get(0));
					tableItem.setText(2, row.get(1));
					tableItem.setText(3, row.get(2));
					tableItem.setText(4, row.get(3));
					tableItem.setText(5, row.get(4));
					tableItem.setText(6, row.get(5));
					tableItem.setText(7, row.get(6));
					flag = flag + 1;
					lossSum = lossSum + Float.parseFloat(row.get(5));
				}
				
				lblLossCost.setText("" + lossSum);
				
				sql = "SELECT i.gift_date,i.vip_id, v.vip_consume,i.good_id,g.good_name,g.good_cost FROM gift i JOIN good g ON g.good_id = i.good_id JOIN vip v ON v.vip_id = i.vip_id WHERE i.gift_date BETWEEN '" + year + "-" + month + "-" + day + " 00:00:00' AND '" + year + "-" + month + "-" + day + " 23:59:59'";
				list = jt.query(sql);
				Float giftSum = 0f;
				flag = 1;
				table_2.removeAll();
				for(List<String> row : list) {
					TableItem tableItem = new TableItem(table_2, SWT.NONE);
					tableItem.setText(0, "" + flag);
					tableItem.setText(1, row.get(0));
					tableItem.setText(2, row.get(1));
					tableItem.setText(3, row.get(2));
					tableItem.setText(4, row.get(3));
					tableItem.setText(5, row.get(4));
					tableItem.setText(6, row.get(5));
					flag = flag + 1;
					giftSum = giftSum + Float.parseFloat(row.get(5));
				}
				
				lblGiftCost.setText("" + giftSum);
				
				Float sum = importSum + lossSum + giftSum;
				lblAllCost.setText("" + sum);
			}
		});
		button_1.setText("\u5F53\u65E5\u67E5\u8BE2");
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		button_1.setBounds(58, 324, 90, 51);
		
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(224, 48, 805, 147);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(78);
		tableColumn.setText("\u884C\u53F7");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(105);
		tableColumn_1.setText("\u8FDB\u8D27\u65F6\u95F4");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(133);
		tableColumn_2.setText("\u8FDB\u8D27\u6D41\u6C34\u53F7");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(163);
		tableColumn_3.setText("\u5546\u54C1\u7F16\u53F7");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(133);
		tableColumn_4.setText("\u5546\u54C1\u540D\u79F0");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(75);
		tableColumn_5.setText("\u6210\u672C");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(93);
		tableColumn_6.setText("\u8FDB\u8D27\u82B1\u8D39");
		
		Label label_1 = new Label(container, SWT.CENTER);
		label_1.setText("\u652F\u51FA\u7EDF\u8BA1");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 22, SWT.BOLD));
		label_1.setBounds(574, 0, 218, 42);
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setText("\u8FDB\u8D27\u652F\u51FA\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
		label_2.setBounds(224, 208, 105, 28);
		
		lblImportCost = new Label(container, SWT.BORDER);
		lblImportCost.setFont(SWTResourceManager.getFont("微软雅黑", 24, SWT.NORMAL));
		lblImportCost.setBounds(335, 196, 148, 42);
		
		Label label_4 = new Label(container, SWT.NONE);
		label_4.setText("\u5143");
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 18, SWT.NORMAL));
		label_4.setBounds(489, 198, 46, 28);
		
		Label label_5 = new Label(container, SWT.NONE);
		label_5.setText("\u635F\u8017\u652F\u51FA\uFF1A");
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
		label_5.setBounds(224, 402, 105, 28);
		
		Label label_8 = new Label(container, SWT.NONE);
		label_8.setText("\u793C\u54C1\u652F\u51FA\uFF1A");
		label_8.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
		label_8.setBounds(224, 601, 105, 28);
		
		lblAllCost = new Label(container, SWT.BORDER);
		lblAllCost.setFont(SWTResourceManager.getFont("微软雅黑", 24, SWT.NORMAL));
		lblAllCost.setBounds(20, 467, 148, 42);
		
		Label label_10 = new Label(container, SWT.NONE);
		label_10.setText("\u5143");
		label_10.setFont(SWTResourceManager.getFont("微软雅黑", 18, SWT.NORMAL));
		label_10.setBounds(174, 476, 46, 28);
		
		table_1 = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		table_1.setBounds(224, 241, 806, 147);
		
		TableColumn tableColumn_7 = new TableColumn(table_1, SWT.NONE);
		tableColumn_7.setWidth(78);
		tableColumn_7.setText("\u884C\u53F7");
		
		TableColumn tableColumn_8 = new TableColumn(table_1, SWT.NONE);
		tableColumn_8.setWidth(108);
		tableColumn_8.setText("\u635F\u8017\u65F6\u95F4");
		
		TableColumn tableColumn_10 = new TableColumn(table_1, SWT.NONE);
		tableColumn_10.setWidth(130);
		tableColumn_10.setText("\u5546\u54C1\u7F16\u53F7");
		
		TableColumn tableColumn_11 = new TableColumn(table_1, SWT.NONE);
		tableColumn_11.setWidth(124);
		tableColumn_11.setText("\u5546\u54C1\u540D\u79F0");
		
		TableColumn tableColumn_12 = new TableColumn(table_1, SWT.NONE);
		tableColumn_12.setWidth(68);
		tableColumn_12.setText("\u6210\u672C");
		
		TableColumn tableColumn_13 = new TableColumn(table_1, SWT.NONE);
		tableColumn_13.setWidth(67);
		tableColumn_13.setText("\u8017\u635F\u6570\u91CF");
		
		TableColumn tableColumn_9 = new TableColumn(table_1, SWT.NONE);
		tableColumn_9.setWidth(72);
		tableColumn_9.setText("\u8017\u635F\u91D1\u989D");
		
		TableColumn tableColumn_21 = new TableColumn(table_1, SWT.NONE);
		tableColumn_21.setWidth(139);
		tableColumn_21.setText("\u8017\u635F\u539F\u56E0");
		
		table_2 = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		table_2.setLinesVisible(true);
		table_2.setHeaderVisible(true);
		table_2.setBounds(224, 446, 806, 147);
		
		TableColumn tableColumn_14 = new TableColumn(table_2, SWT.NONE);
		tableColumn_14.setWidth(78);
		tableColumn_14.setText("\u884C\u53F7");
		
		TableColumn tableColumn_15 = new TableColumn(table_2, SWT.NONE);
		tableColumn_15.setWidth(118);
		tableColumn_15.setText("\u65F6\u95F4");
		
		TableColumn tableColumn_16 = new TableColumn(table_2, SWT.NONE);
		tableColumn_16.setWidth(82);
		tableColumn_16.setText("\u4F1A\u5458\u5361\u53F7");
		
		TableColumn tableColumn_17 = new TableColumn(table_2, SWT.NONE);
		tableColumn_17.setWidth(95);
		tableColumn_17.setText("\u4F1A\u5458\u6D88\u8D39\u91D1\u989D");
		
		TableColumn tableColumn_18 = new TableColumn(table_2, SWT.NONE);
		tableColumn_18.setWidth(132);
		tableColumn_18.setText("\u5546\u54C1\u7F16\u7801");
		
		TableColumn tableColumn_19 = new TableColumn(table_2, SWT.NONE);
		tableColumn_19.setWidth(115);
		tableColumn_19.setText("\u5546\u54C1\u540D\u79F0");
		
		TableColumn tableColumn_20 = new TableColumn(table_2, SWT.NONE);
		tableColumn_20.setWidth(63);
		tableColumn_20.setText("\u8D60\u9001\u6570\u91CF");
		
		TableColumn tableColumn_22 = new TableColumn(table_2, SWT.NONE);
		tableColumn_22.setWidth(102);
		tableColumn_22.setText("\u82B1\u8D39");
		
		lblLossCost = new Label(container, SWT.BORDER);
		lblLossCost.setFont(SWTResourceManager.getFont("微软雅黑", 24, SWT.NORMAL));
		lblLossCost.setBounds(335, 397, 148, 43);
		
		Label label_12 = new Label(container, SWT.NONE);
		label_12.setText("\u5143");
		label_12.setFont(SWTResourceManager.getFont("微软雅黑", 18, SWT.NORMAL));
		label_12.setBounds(489, 399, 46, 28);
		
		lblGiftCost = new Label(container, SWT.BORDER);
		lblGiftCost.setFont(SWTResourceManager.getFont("微软雅黑", 24, SWT.NORMAL));
		lblGiftCost.setBounds(335, 596, 148, 42);
		
		Label label_14 = new Label(container, SWT.NONE);
		label_14.setText("\u5143");
		label_14.setFont(SWTResourceManager.getFont("微软雅黑", 18, SWT.NORMAL));
		label_14.setBounds(489, 601, 46, 28);
		
		Label label_6 = new Label(container, SWT.NONE);
		label_6.setText("\u603B\u652F\u51FA\uFF1A");
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
		label_6.setBounds(10, 436, 105, 28);

		createActions();
		initializeToolBar();
		initializeMenu();
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
}
