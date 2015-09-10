package com.treenewbee.view;

import java.util.List;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.treenewbee.util.JdbcTool;

public class TestView extends ViewPart {

	public static final String ID = "com.shxt.paging.view.SelBookView"; //$NON-NLS-1$
	private Table table;
	
	/**当前查看的页数,初始是第一页*/
	private int index = 1;
	/**总页数*/
	private int pages;
	/**每页显示的条数*/
	private final int size = 1;
	
	
	/**显示第几页*/
	private Label lblIndex;
	/**显示总页数*/
	private Label lblAll;
	
	private Button btnFirst;
	
	private Button btnPer;
	
	private Button btnNext;
	
	private Button btnEnd;
	private Text txtAuthor;
	private Text txtPress;

	public TestView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(69, 84, 515, 289);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnId = new TableColumn(table, SWT.NONE);
		tblclmnId.setWidth(100);
		tblclmnId.setText("ID");
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("\u4E66\u540D");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("\u4F5C\u8005");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("\u51FA\u7248\u793E");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("\u51FA\u7248\u65E5\u671F");
		
		btnPer = new Button(container, SWT.NONE);
		btnPer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				index--;
				query();
			}
		});
		btnPer.setBounds(183, 410, 80, 27);
		btnPer.setText("\u4E0A\u4E00\u9875");
		
		btnNext = new Button(container, SWT.NONE);
		btnNext.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				index++;
				
				query();
			}
		});
		btnNext.setBounds(397, 410, 80, 27);
		btnNext.setText("\u4E0B\u4E00\u9875");
		
		btnFirst = new Button(container, SWT.NONE);
		btnFirst.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				index = 1;
				query();
			}
		});
		btnFirst.setBounds(77, 410, 80, 27);
		btnFirst.setText("\u9996\u9875");
		
		btnEnd = new Button(container, SWT.NONE);
		btnEnd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				index = pages;
				query();
			}
		});
		btnEnd.setBounds(504, 410, 80, 27);
		btnEnd.setText("\u5C3E\u9875");
		
		lblIndex = new Label(container, SWT.NONE);
		lblIndex.setBounds(286, 415, 22, 17);
		lblIndex.setText(String.valueOf(index));
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setBounds(314, 415, 22, 17);
		label_1.setText("/");
		
		lblAll = new Label(container, SWT.NONE);
		lblAll.setBounds(339, 415, 28, 17);
		lblAll.setText("6");
		
		txtAuthor = new Text(container, SWT.BORDER);
		txtAuthor.setBounds(132, 32, 73, 23);
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setBounds(51, 35, 61, 17);
		lblNewLabel.setText("\u4F5C\u8005");
		
		Label label = new Label(container, SWT.NONE);
		label.setBounds(306, 35, 61, 17);
		label.setText("\u51FA\u7248\u793E");
		
		txtPress = new Text(container, SWT.BORDER);
		txtPress.setBounds(397, 32, 112, 23);
		
		Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				index = 1;
				query();
			}
		});
		button.setBounds(514, 32, 80, 27);
		button.setText("\u67E5\u8BE2");
		
		
		
		
		
		query();
		
		
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
	
	
	
	public void query() {
		table.removeAll();
		//第几条
		int first = (index - 1) * size;
		String sql = "SELECT  b.id,b.book_name,b.author,b.press,b.publish_date FROM book b  WHERE 1=1 ";
		String sqlCount = "SELECT  COUNT(*) FROM book b  WHERE 1=1 ";
		
		//判断作者
		String author = txtAuthor.getText();
		if(!"".equals(author = author.trim())) {
			sql = sql + " AND b.author LIKE '%" + author + "%'";
			sqlCount = sqlCount + " AND b.author LIKE '%" + author + "%'";
		}
		
		String press = txtPress.getText();
		if(!"".equals(press = press.trim())) {
			sql = sql + " AND b.press LIKE '%" + press + "%'";
			sqlCount = sqlCount + " AND b.press LIKE '%" + press + "%'";
		}
		
		
		sql = sql + " LIMIT " + first + "," + size;
		
		JdbcTool jt = new JdbcTool();
		List<List<String>>	tableList = jt.query(sql);
		
		for(List<String> row : tableList) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			for (int i = 0; i < row.size(); i++) {
				tableItem.setText(i,row.get(i));
			}
			
		}
		
		tableList = jt.query(sqlCount);
		String rowsStr = tableList.get(0).get(0);
		
		//查询的总行数
		int rows = 0;
		if(rowsStr != null) {
			rows = Integer.parseInt(rowsStr);
		}
		
		// 3 = 15 /5;
		//     18 / 5;
//		pages =  rows / size;
//		if(rows % size != 0) {
//			pages++;
//		}
		
		pages =  rows / size + (rows % size != 0 ? 1 : 0);
		
		if(pages == 0) {
			pages = 1;
		}
		
		//改变当前显示的第几页
		lblIndex.setText(String.valueOf(index));
		//设置总页数
		lblAll.setText(String.valueOf(pages));
		
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
