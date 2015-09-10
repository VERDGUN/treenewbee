package com.treenewbee.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.treenewbee.dialog.AddLossDialog;
import com.treenewbee.dialog.GoodAddDialog;
import com.treenewbee.dialog.GoodImportDialog;
import com.treenewbee.dialog.GoodStoreDialog;
import com.treenewbee.util.JdbcTool;

import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class StorageView extends ViewPart {

	public static final String ID = "com.shxt.supersystem.view.StorageView"; //$NON-NLS-1$
	private Text text;
	private Table table;
	private Table table_1;
	private Table table_2;

	public StorageView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundImage(SWTResourceManager.getImage(StorageView.class, "/com/shxt/supersystem/picture/\u84DD\u8272_\u526F\u672C.jpg"));
		container.setBackgroundMode(SWT.INHERIT_FORCE);
		{
			Composite composite = new Composite(container, SWT.BORDER);
			composite.setBounds(0, 0, 1017, 42);
			{
				Button button = new Button(composite, SWT.NONE);
				button.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
						GoodAddDialog goodAddDialog = new GoodAddDialog(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
						goodAddDialog.open();
					}
				});
				button.setText("\u6DFB\u52A0\u65B0\u5546\u54C1");
				button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
				button.setBounds(0, -1, 95, 39);
			}
			{
				Button button = new Button(composite, SWT.NONE);
				button.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						IWorkbenchPage page = getSite().getPage();
						page.hideView(page.findView(StorageView.ID));
						try {
							page.showView(StorageManagerView.ID);
						} catch (PartInitException e1) {
							e1.printStackTrace();
						}
					}
				});
				button.setText("\u8FD4\u56DE\u4E0A\u7EA7\u83DC\u5355");
				button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
				button.setBounds(870, -1, 145, 39);
			}
			{
				Button button = new Button(composite, SWT.NONE);
				button.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
						AddLossDialog addLossDialog = new AddLossDialog(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
						addLossDialog.open(false,true);
						
					}
				});
				button.setText("\u6DFB\u52A0\u635F\u8017");
				button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
				button.setBounds(94, -1, 95, 39);
			}
			{
				Button button = new Button(composite, SWT.NONE);
				button.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						IWorkbenchPage page = getSite().getPage();
						page.hideView(page.findView(StorageView.ID));
						try {
							page.showView(StorageView.ID);
						} catch (PartInitException e1) {
							e1.printStackTrace();
						}
					}
				});
				button.setText("\u5237\u65B0");
				button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
				button.setBounds(188, -1, 95, 39);
			}
		}
		{
			Composite composite = new Composite(container, SWT.BORDER);
			composite.setBounds(0, 41, 1017, 37);
			{
				Label label = new Label(composite, SWT.NONE);
				label.setText("\u8BF7\u8F93\u5165\u5546\u54C1\u7F16\u7801\u67E5\u8BE2");
				label.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
				label.setBounds(38, 8, 141, 25);
			}
			{
				text = new Text(composite, SWT.BORDER);
				text.setBounds(182, 8, 154, 23);
			}
			{
				Button button = new Button(composite, SWT.NONE);
				button.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						String s1 = text.getText().trim();
						
						Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
						MessageBox box = new MessageBox(shell);
						if("".equals(s1)) {
							box.setMessage("请输入商品编码！");
							box.open();
							return;
						}
						
						JdbcTool jt = new JdbcTool();
						String sql = "SELECT i.good_id,g.good_name,SUM(i.storage_num) FROM import_good i JOIN good g ON g.good_id = i.good_id GROUP BY i.good_id HAVING i.good_id = '" + s1 + "'";
						List<List<String>> list = jt.query(sql);
						if(list.size() == 0) {
							box.setMessage("此商品之前未进货，请重新输入");
							box.open();
							text.setText("");
							return;
						}
						GoodStoreDialog goodStoreDialog = new GoodStoreDialog(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
						goodStoreDialog.open(s1);
						
					}
				});
				button.setText("\u786E\u5B9A");
				button.setBounds(342, 6, 57, 27);
			}
		}
		{
			table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
			table.setLinesVisible(true);
			table.setHeaderVisible(true);
			table.setBounds(0, 79, 1017, 317);
			{
				TableColumn tableColumn = new TableColumn(table, SWT.NONE);
				tableColumn.setWidth(66);
				tableColumn.setText("\u884C\u53F7");
			}
			{
				TableColumn tableColumn = new TableColumn(table, SWT.NONE);
				tableColumn.setWidth(148);
				tableColumn.setText("\u5546\u54C1\u7F16\u7801");
			}
			{
				TableColumn tableColumn = new TableColumn(table, SWT.NONE);
				tableColumn.setWidth(181);
				tableColumn.setText("\u5546\u54C1\u540D\u79F0");
			}
			{
				TableColumn tableColumn = new TableColumn(table, SWT.NONE);
				tableColumn.setWidth(152);
				tableColumn.setText("\u5546\u54C1\u7C7B\u522B");
			}
			{
				TableColumn tableColumn = new TableColumn(table, SWT.NONE);
				tableColumn.setWidth(128);
				tableColumn.setText("\u5E93\u5B58");
			}
			{
				TableColumn tableColumn = new TableColumn(table, SWT.NONE);
				tableColumn.setWidth(220);
				tableColumn.setText("\u5907\u6CE8");
			}
			
			JdbcTool jt = new JdbcTool();
			String sql = "SELECT i.good_id, g.good_name,g.good_type,SUM(i.storage_num) FROM import_good i JOIN good g ON g.good_id = i.good_id WHERE g.good_comment = '' GROUP BY i.good_id";
			List<List<String>> list = jt.query(sql);
			
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String nowdate = sdf.format(now);
			
			int flag = 1;
			for(List<String> row : list) {
				TableItem tableItem = new TableItem(table, SWT.NONE);
				tableItem.setText(0, "" + flag);
				tableItem.setText(1, row.get(0));
				tableItem.setText(2, row.get(1));
				tableItem.setText(3, row.get(2));
				tableItem.setText(4, row.get(3));
				
				sql = "SELECT storage_num,good_out_date FROM import_good WHERE good_id = " + row.get(0) + "";
				List<List<String>> dateList = jt.query(sql);
				
				int n = 0;
				for(List<String> r : dateList) {
					
					if(Float.parseFloat(r.get(0)) != 0 && r.get(1).compareTo(nowdate) <= 0) {
						n = n + 1;
					}
				}
				if(Float.parseFloat(row.get(3)) <= 100 && n > 0) {
					tableItem.setText(5, "库存不足且有部分商品已过期");
				}else if(Float.parseFloat(row.get(3)) <= 100) {
					tableItem.setText(5, "库存不足");
				}else if(n > 0){
					tableItem.setText(5, "有部分商品已过期");
				}
				
				flag = flag + 1;
			}
			
		}
		
		TabFolder tabFolder = new TabFolder(container, SWT.NONE);
		tabFolder.setBounds(0, 402, 1017, 228);
		
		TabItem tabItem_1 = new TabItem(tabFolder, SWT.NONE);
		tabItem_1.setText("\u9000\u8D27");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tabItem_1.setControl(composite_1);
		
		table_2 = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table_2.setBounds(0, 0, 704, 204);
		table_2.setHeaderVisible(true);
		table_2.setLinesVisible(true);
		
		TableColumn tableColumn_5 = new TableColumn(table_2, SWT.NONE);
		tableColumn_5.setWidth(66);
		tableColumn_5.setText("\u884C\u53F7");
		
		TableColumn tableColumn_6 = new TableColumn(table_2, SWT.NONE);
		tableColumn_6.setWidth(130);
		tableColumn_6.setText("\u5546\u54C1\u7F16\u7801");
		
		TableColumn tableColumn_7 = new TableColumn(table_2, SWT.NONE);
		tableColumn_7.setWidth(157);
		tableColumn_7.setText("\u5546\u54C1\u540D\u79F0");
		
		TableColumn tableColumn_8 = new TableColumn(table_2, SWT.NONE);
		tableColumn_8.setWidth(107);
		tableColumn_8.setText("\u8FC7\u671F\u65E5\u671F");
		
		TableColumn tableColumn_9 = new TableColumn(table_2, SWT.NONE);
		tableColumn_9.setWidth(92);
		tableColumn_9.setText("\u8FC7\u671F\u6570\u91CF");
		
		TableColumn tableColumn_10 = new TableColumn(table_2, SWT.NONE);
		tableColumn_10.setWidth(123);
		tableColumn_10.setText("\u5907\u6CE8");
		
		
	
		
		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				JdbcTool jt = new JdbcTool();
				String sql = "SELECT import_id, good_id, storage_num, good_out_date FROM import_good";
				List<List<String>> list = jt.query(sql);
				
				Date now = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String nowdate = sdf.format(now);
				
				for(List<String> row : list) {
					if(Float.parseFloat(row.get(2)) != 0 && row.get(3).compareTo(nowdate) <= 0) {
						sql = "UPDATE import_good SET storage_num = 0 WHERE import_id = '" + row.get(0) + "'";
						jt.update(sql);
						
						sql = "INSERT INTO loss VALUES ('" + nowdate + "', '" + row.get(1) + "', '" + row.get(2) + "', '商品过期')";
						jt.update(sql);
					}
				}
				
				Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
				MessageBox box = new MessageBox(shell);
				box.setMessage("退货成功！");
				box.open();
			}
		});
		btnNewButton.setFont(SWTResourceManager.getFont("微软雅黑", 26, SWT.BOLD));
		btnNewButton.setBounds(710, 0, 299, 204);
		btnNewButton.setText("\u4E00\u952E\u9000\u8D27");
		
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("\u8FDB\u8D27");
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tabItem.setControl(composite);
		
		table_1 = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setBounds(0, 0, 1009, 206);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
		tableColumn.setWidth(63);
		tableColumn.setText("\u884C\u53F7");
		
		TableColumn tableColumn_1 = new TableColumn(table_1, SWT.NONE);
		tableColumn_1.setWidth(199);
		tableColumn_1.setText("\u5546\u54C1\u7F16\u53F7");
		
		TableColumn tableColumn_2 = new TableColumn(table_1, SWT.NONE);
		tableColumn_2.setWidth(233);
		tableColumn_2.setText("\u5546\u54C1\u540D\u79F0");
		
		TableColumn tableColumn_3 = new TableColumn(table_1, SWT.NONE);
		tableColumn_3.setWidth(169);
		tableColumn_3.setText("\u5E93\u5B58");
		
		TableColumn tableColumn_4 = new TableColumn(table_1, SWT.NONE);
		tableColumn_4.setWidth(277);
		tableColumn_4.setText("\u5907\u6CE8");
		
		Menu menu = new Menu(table_1);
		table_1.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//获取用户选取的哪一行
				int index = table_1.getSelectionIndex();
				//获取行对象
				TableItem row = table_1.getItem(index);
				
				Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
				GoodImportDialog goodImportDialog = new GoodImportDialog(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
				goodImportDialog.open(row.getText(1), row.getText(2));
				
				IWorkbenchPage page = getSite().getPage();
				page.hideView(page.findView(StorageView.ID));
				try {
					page.showView(StorageView.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
			}
		});
		menuItem.setText("\u8FDB\u8D27");
		
		JdbcTool jt = new JdbcTool();
		String sql = "SELECT i.good_id, g.good_name,g.good_type,SUM(i.storage_num) FROM import_good i JOIN good g ON g.good_id = i.good_id WHERE g.good_comment = '' GROUP BY i.good_id";
		List<List<String>> list = jt.query(sql);
		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowdate = sdf.format(now);
		
		int flag = 1;
		for(List<String> row : list) {
			
			if(Float.parseFloat(row.get(3)) <= 100) {
				TableItem tableItem = new TableItem(table_1, SWT.NONE);
				tableItem.setText(0, "" + flag);
				tableItem.setText(1, row.get(0));
				tableItem.setText(2, row.get(1));
				tableItem.setText(3, row.get(3));
				tableItem.setText(4, "库存不足");
			}
			flag = flag + 1;
		}
		
		sql = "SELECT i.good_id, g.good_name, i.storage_num, i.good_out_date FROM import_good i JOIN good g ON i.good_id = g.good_id";
		list = jt.query(sql);
		
		flag = 1;
		for(List<String> row : list) {
			if(Float.parseFloat(row.get(2)) != 0 && row.get(3).compareTo(nowdate) <= 0) {
				TableItem tableItem = new TableItem(table_2, SWT.NONE);
				tableItem.setText(0, "" + flag);
				tableItem.setText(1, row.get(0));
				tableItem.setText(2, row.get(1));
				tableItem.setText(3, row.get(3));
				tableItem.setText(4, row.get(2));
				tableItem.setText(5, "此批商品已过期");
				}
			flag = flag + 1;
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
