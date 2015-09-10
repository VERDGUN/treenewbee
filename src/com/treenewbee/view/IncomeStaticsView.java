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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.treenewbee.util.JdbcTool;

import org.eclipse.swt.widgets.Combo;

public class IncomeStaticsView extends ViewPart {

	public static final String ID = "com.shxt.supersystem.view.IncomeStaticsView"; //$NON-NLS-1$
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


	public IncomeStaticsView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundImage(SWTResourceManager.getImage(IncomeStaticsView.class, "/com/shxt/supersystem/picture/\u84DD\u8272_\u526F\u672C.jpg"));
		container.setBackgroundMode(SWT.INHERIT_FORCE);
		{
			Label label = new Label(container, SWT.CENTER);
			label.setText("\u5165\u8D26\u65E5\u5386");
			label.setFont(SWTResourceManager.getFont("微软雅黑", 22, SWT.BOLD));
			label.setBounds(0, 0, 218, 42);
		}
		{
			dateTime = new DateTime(container, SWT.CALENDAR);
			dateTime.setBounds(0, 48, 218, 159);
		}
		{
			table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
			table.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
			table.setLinesVisible(true);
			table.setHeaderVisible(true);
			table.setBounds(225, 48, 795, 518);
			{
				TableColumn tableColumn = new TableColumn(table, SWT.NONE);
				tableColumn.setWidth(54);
				tableColumn.setText("\u884C\u53F7");
			}
			{
				TableColumn tableColumn = new TableColumn(table, SWT.NONE);
				tableColumn.setWidth(109);
				tableColumn.setText("\u552E\u51FA\u65F6\u95F4");
			}
			{
				TableColumn tableColumn = new TableColumn(table, SWT.NONE);
				tableColumn.setWidth(98);
				tableColumn.setText("\u6536\u94F6\u5458\u5DE5\u53F7");
			}
			{
				TableColumn tableColumn = new TableColumn(table, SWT.NONE);
				tableColumn.setWidth(140);
				tableColumn.setText("\u5546\u54C1\u7F16\u53F7");
			}
			{
				TableColumn tableColumn = new TableColumn(table, SWT.NONE);
				tableColumn.setWidth(139);
				tableColumn.setText("\u5546\u54C1\u540D\u79F0");
			}
			{
				TableColumn tableColumn = new TableColumn(table, SWT.NONE);
				tableColumn.setWidth(58);
				tableColumn.setText("\u6570\u91CF");
			}
			{
				TableColumn tableColumn = new TableColumn(table, SWT.NONE);
				tableColumn.setWidth(83);
				tableColumn.setText("\u6536\u6B3E");
			}
			{
				TableColumn tableColumn = new TableColumn(table, SWT.NONE);
				tableColumn.setWidth(104);
				tableColumn.setText("\u5907\u6CE8");
			}
		}
		{
			Label label = new Label(container, SWT.CENTER);
			label.setText("\u5165\u8D26\u7EDF\u8BA1");
			label.setFont(SWTResourceManager.getFont("微软雅黑", 22, SWT.BOLD));
			label.setBounds(571, 0, 218, 42);
		}
		{
			btnMonth = new Button(container, SWT.NONE);
			btnMonth.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					index = 1;
					flag = 1;
					int year = dateTime.getYear();
					int month = dateTime.getMonth() + 1;
					sqlPart = " AND s.sale_date BETWEEN '" + year + "-" + month + "-01 00:00:00' AND '" + year + "-" + month + "-31 23:59:59'";
					query();
					
					JdbcTool jt = new JdbcTool();
					String sql = "SELECT SUM(s.sale_money) FROM sale s JOIN good g ON s.good_id = g.good_id  WHERE 1 = 1 " + sqlPart;
					List<List<String>> list = jt.query(sql);
					if(list.get(0).get(0) == null) {
						lblSum.setText("0.00");
					}else {
						lblSum.setText("" + Float.parseFloat(list.get(0).get(0)));
					}
				}
			});
			btnMonth.setText("\u5F53\u6708\u67E5\u8BE2");
			btnMonth.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
			btnMonth.setBounds(62, 224, 90, 51);
		}
		{
			btnDay = new Button(container, SWT.NONE);
			btnDay.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					index = 1;
					flag = 1;
					int year = dateTime.getYear();
					int month = dateTime.getMonth() + 1;
					int day = dateTime.getDay();
					sqlPart = " AND s.sale_date BETWEEN '" + year + "-" + month + "-" + day + " 00:00:00' AND '" + year + "-" + month + "-" + day + " 23:59:59'";
					query();
					
					JdbcTool jt = new JdbcTool();
					String sql = "SELECT SUM(s.sale_money) FROM sale s JOIN good g ON s.good_id = g.good_id  WHERE 1 = 1 " + sqlPart;
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
			btnDay.setBounds(62, 293, 90, 51);
		}
		{
			Label label = new Label(container, SWT.NONE);
			label.setText("\u8FDB\u8D27\u603B\u989D\uFF1A");
			label.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
			label.setBounds(10, 365, 105, 28);
		}
		{
			lblSum = new Label(container, SWT.BORDER);
			lblSum.setFont(SWTResourceManager.getFont("微软雅黑", 24, SWT.NORMAL));
			lblSum.setBounds(20, 399, 148, 42);
		}
		{
			Label label = new Label(container, SWT.NONE);
			label.setText("\u5143");
			label.setFont(SWTResourceManager.getFont("微软雅黑", 18, SWT.NORMAL));
			label.setBounds(172, 408, 46, 28);
		}
		{
			btnFirst = new Button(container, SWT.NONE);
			btnFirst.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					index = 1;
					query();
				}
			});
			btnFirst.setText("\u9996\u9875");
			btnFirst.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
			btnFirst.setEnabled(false);
			btnFirst.setBounds(403, 572, 89, 29);
		}
		{
			btnPer = new Button(container, SWT.NONE);
			btnPer.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					index--;
					query();
				}
			});
			btnPer.setText("\u4E0A\u4E00\u9875");
			btnPer.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
			btnPer.setEnabled(false);
			btnPer.setBounds(537, 572, 89, 29);
		}
		{
			lblIndex = new Label(container, SWT.NONE);
			lblIndex.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
			lblIndex.setBounds(681, 576, 24, 29);
		}
		{
			Label label = new Label(container, SWT.NONE);
			label.setText("/");
			label.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
			label.setBounds(711, 576, 22, 29);
		}
		{
			lblAll = new Label(container, SWT.NONE);
			lblAll.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
			lblAll.setBounds(744, 576, 24, 29);
		}
		{
			btnNext = new Button(container, SWT.NONE);
			btnNext.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					index++;
					query();
				}
			});
			btnNext.setText("\u4E0B\u4E00\u9875");
			btnNext.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
			btnNext.setEnabled(false);
			btnNext.setBounds(814, 572, 89, 29);
		}
		{
			btnEnd = new Button(container, SWT.NONE);
			btnEnd.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					index = pages;
					query();
				}
			});
			btnEnd.setText("\u5C3E\u9875");
			btnEnd.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
			btnEnd.setEnabled(false);
			btnEnd.setBounds(974, 576, 89, 29);
		}
		{
			Label label = new Label(container, SWT.NONE);
			label.setText("\u4E00\u9875\u663E\u793A");
			label.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
			label.setBounds(225, 576, 60, 25);
		}
		{
			combo = new Combo(container, SWT.READ_ONLY);
			combo.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
			combo.setBounds(291, 573, 43, 28);
			for(int i = 0; i < 19; i++) {
				combo.add("" + (i+1));
			}
		}
		{
			Label label = new Label(container, SWT.NONE);
			label.setText("\u6761");
			label.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
			label.setBounds(338, 576, 34, 25);
		}

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
		IToolBarManager toolbarManager = getViewSite().getActionBars().getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars().getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
	
	/**分页显示*/
	public void query() {
		table.removeAll();
		size = Integer.parseInt(combo.getText());
		
		/**第几条*/
		int first = (index - 1) * size;
		
		JdbcTool jt = new JdbcTool();
		String sql = "SELECT s.sale_date,s.cashier_id,s.good_id,g.good_name, s.sale_num,s.sale_money, s.vip_id FROM sale s JOIN good g ON s.good_id = g.good_id  WHERE 1 = 1 ";
		String sqlCount = "SELECT COUNT(*) FROM sale s JOIN good g ON s.good_id = g.good_id  WHERE 1 = 1 ";
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
			if(row.get(6) != null) {
				tableItem.setText(7, "会员消费");
			}
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
