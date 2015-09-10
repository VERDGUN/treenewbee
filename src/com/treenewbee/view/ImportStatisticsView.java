package com.treenewbee.view;

import java.util.List;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.treenewbee.util.JdbcTool;

import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Combo;

public class ImportStatisticsView extends ViewPart {

	public static final String ID = "com.shxt.supersystem.view.ImportStatisticsView"; //$NON-NLS-1$
	private Table table;
	private DateTime dateTime;
	private Label lblSum;
	private Button btnMonth;
	private Button btnDay; 
	
	/**当前查看的页数，初始页是第一页*/
	private int index = 1;
	/**总页数*/
	private int pages;
	/**每页显示的条数*/
	private int size = 3;
	/**显示第几页*/
	private Label lblIndex;
	/**现实总页数*/
	private Label lblAll;
	
	private String sqlPart;
	private int flag;
	
	private Button btnFirst;
	private Button btnPer;
	private Button btnNext;
	private Button btnEnd;
	private Combo combo;

	public ImportStatisticsView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundImage(SWTResourceManager.getImage(ImportStatisticsView.class, "/com/shxt/supersystem/picture/\u84DD\u8272_\u526F\u672C.jpg"));
		container.setBackgroundMode(SWT.INHERIT_FORCE);
		
		dateTime = new DateTime(container, SWT.CALENDAR);
		dateTime.setBounds(0, 48, 218, 159);
		
		Composite composite = new Composite(container, SWT.NONE);
		composite.setBounds(225, 48, 940, 578);
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
		table.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		table.setBounds(0, 0, 787, 515);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(78);
		tableColumn.setText("\u884C\u53F7");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(94);
		tableColumn_1.setText("\u8FDB\u8D27\u6D41\u6C34\u53F7");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(115);
		tableColumn_6.setText("\u8FDB\u8D27\u65F6\u95F4");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(120);
		tableColumn_2.setText("\u5546\u54C1\u7F16\u53F7");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(127);
		tableColumn_3.setText("\u5546\u54C1\u540D\u79F0");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(77);
		tableColumn_4.setText("\u6210\u672C");
		
		TableColumn tableColumn_7 = new TableColumn(table, SWT.NONE);
		tableColumn_7.setWidth(84);
		tableColumn_7.setText("\u8FDB\u8D27\u6570\u91CF");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(89);
		tableColumn_5.setText("\u8FDB\u8D27\u82B1\u8D39");
		
		btnFirst = new Button(composite, SWT.NONE);
		btnFirst.setEnabled(false);
		btnFirst.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				index = 1;
				query();
			}
		});
		btnFirst.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		btnFirst.setText("\u9996\u9875");
//		btnFirst.setEnabled(true);
		btnFirst.setBounds(176, 537, 89, 29);
		
		btnPer = new Button(composite, SWT.NONE);
		btnPer.setEnabled(false);
		btnPer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				index--;
				query();
			}
		});
		btnPer.setText("\u4E0A\u4E00\u9875");
		btnPer.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
//		btnPer.setEnabled(true);
		btnPer.setBounds(292, 537, 89, 29);
		
		btnNext = new Button(composite, SWT.NONE);
		btnNext.setEnabled(false);
		btnNext.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				index++;
				query();
			}
		});
		btnNext.setText("\u4E0B\u4E00\u9875");
		btnNext.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
//		btnNext.setEnabled(true);
		btnNext.setBounds(570, 537, 89, 29);
		
		btnEnd = new Button(composite, SWT.NONE);
		btnEnd.setEnabled(false);
		btnEnd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				index = pages;
				query();	
			}
		});
		btnEnd.setText("\u5C3E\u9875");
		btnEnd.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
//		btnEnd.setEnabled(true);
		btnEnd.setBounds(682, 537, 89, 29);
		
		lblIndex = new Label(composite, SWT.NONE);
		lblIndex.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		lblIndex.setBounds(428, 541, 24, 29);
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_4.setText("/");
		label_4.setBounds(458, 541, 22, 29);
		
		lblAll = new Label(composite, SWT.NONE);
		lblAll.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		lblAll.setBounds(481, 541, 24, 29);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_2.setBounds(10, 541, 60, 25);
		label_2.setText("\u4E00\u9875\u663E\u793A");
		
		combo = new Combo(composite, SWT.READ_ONLY);
		combo.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		combo.setBounds(76, 541, 43, 28);
		for(int i = 0; i < 19; i++) {
			combo.add("" + (i+1));
		}
		
		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setText("\u6761");
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_5.setBounds(125, 541, 34, 25);
		
		
		
		Label lblNewLabel = new Label(container, SWT.CENTER);
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 22, SWT.BOLD));
		lblNewLabel.setBounds(0, 0, 218, 42);
		lblNewLabel.setText("\u8FDB\u8D27\u65E5\u5386");
		
		btnMonth = new Button(container, SWT.NONE);
		btnMonth.addSelectionListener(new SelectionAdapter() {
			@Override
			/**当月查询*/
			public void widgetSelected(SelectionEvent e) {
				index = 1;
				flag = 1;
				int year = dateTime.getYear();
				int month = dateTime.getMonth() + 1;
				sqlPart = " AND i.import_date BETWEEN '" + year + "-" + month + "-01 00:00:00' AND '" + year + "-" + month + "-31 23:59:59'";
				query();
				
				
				JdbcTool jt = new JdbcTool();
				String sql = "SELECT SUM(g.good_cost*i.import_num) FROM import_good i JOIN good g ON i.good_id = g.good_id WHERE 1 = 1 " + sqlPart;
				List<List<String>> list = jt.query(sql);
				if(list.get(0).get(0) == null) {
					lblSum.setText("0.00");
				}else {
					lblSum.setText("" + Float.parseFloat(list.get(0).get(0)));
				}
				
			}
		});
		btnMonth.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		btnMonth.setBounds(69, 224, 90, 51);
		btnMonth.setText("\u5F53\u6708\u67E5\u8BE2");
		
		btnDay = new Button(container, SWT.NONE);
		btnDay.addSelectionListener(new SelectionAdapter() {
			@Override
			/**当日查询*/
			public void widgetSelected(SelectionEvent e) {
				index = 1;
				flag = 1;
				int year = dateTime.getYear();
				int month = dateTime.getMonth() + 1;
				int day = dateTime.getDay();
				sqlPart = " AND i.import_date BETWEEN '" + year + "-" + month + "-" + day + " 00:00:00' AND '" + year + "-" + month + "-" + day + " 23:59:59'";
				query();
				
				JdbcTool jt = new JdbcTool();
				String sql = "SELECT SUM(g.good_cost*i.import_num) FROM import_good i JOIN good g ON i.good_id = g.good_id WHERE 1 = 1 " + sqlPart;
				List<List<String>> list = jt.query(sql);
				if(list.get(0).get(0) == null) {
					lblSum.setText("0.00");
				}else {
					lblSum.setText("" + Float.parseFloat(list.get(0).get(0)));
				}
			}
		});
		btnDay.setText("\u5F53\u65E5\u67E5\u8BE2");
		btnDay.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		btnDay.setBounds(69, 291, 90, 51);
		
		Label label = new Label(container, SWT.CENTER);
		label.setText("\u8FDB\u8D27\u660E\u7EC6");
		label.setFont(SWTResourceManager.getFont("微软雅黑", 22, SWT.BOLD));
		label.setBounds(549, 0, 218, 42);
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
		label_1.setBounds(11, 373, 105, 28);
		label_1.setText("\u8FDB\u8D27\u603B\u989D\uFF1A");
		
		lblSum = new Label(container, SWT.BORDER);
		lblSum.setFont(SWTResourceManager.getFont("微软雅黑", 24, SWT.NORMAL));
		lblSum.setBounds(0, 407, 148, 42);
		
		Label label_3 = new Label(container, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 18, SWT.NORMAL));
		label_3.setBounds(154, 416, 46, 28);
		label_3.setText("\u5143");

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
	
	/**分页显示*/
	public void query() {
		size = Integer.parseInt(combo.getText());
		table.removeAll();
		
		/**第几条*/
		int first = (index - 1) * size;
		
		JdbcTool jt = new JdbcTool();
		String sql = "SELECT i.import_id,i.import_date,i.good_id,g.good_name, g.good_cost,i.import_num, (g.good_cost*i.import_num) FROM import_good i JOIN good g ON i.good_id = g.good_id WHERE 1 = 1 ";
		String sqlCount = "SELECT COUNT(*) FROM import_good i JOIN good g ON i.good_id = g.good_id WHERE 1 = 1 ";
		sql = sql + sqlPart + " LIMIT " + first + "," + size;
		sqlCount = sqlCount + sqlPart;
		
		List<List<String>> list = jt.query(sql);
		
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
		}
		
		
		list = jt.query(sqlCount);
		String rowsStr = list.get(0).get(0);
		
		/**查询总行数*/
		int rows = 0;
		if(rowsStr != null) {
			rows = Integer.parseInt(rowsStr);
		}
		
		pages =  rows / size + (rows % size != 0 ? 1 : 0);
		
		if(pages == 0) {
			pages = 1;
		}
		
		/**改变当前显示的第几页*/
		lblIndex.setText("" + index);
		/**设置总页数*/
		lblAll.setText("" + pages);
		
		btnFirst.setEnabled(true);
		btnPer.setEnabled(true);
		btnNext.setEnabled(true);
		btnEnd.setEnabled(true);
		
		if(index == 1) {
			btnFirst.setEnabled(false);
			btnPer.setEnabled(false);
		}
		
		if(index == pages) {
			btnNext.setEnabled(false);
			btnEnd.setEnabled(false);
		}
		
	}
}
