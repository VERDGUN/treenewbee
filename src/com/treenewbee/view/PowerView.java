package com.treenewbee.view;

import java.util.List;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.treenewbee.util.JdbcTool;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class PowerView extends ViewPart {

	public static final String ID = "com.shxt.supersystem.view.PowerView"; //$NON-NLS-1$
	private Table table;
	private Combo combo;

	public PowerView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundImage(SWTResourceManager.getImage(PowerView.class, "/com/shxt/supersystem/picture/\u84DD\u8272_\u526F\u672C.jpg"));
		container.setBackgroundMode(SWT.INHERIT_FORCE);
		
		Composite composite = new Composite(container, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_FORCE);
		composite.setBounds(0, 0, 1018, 41);
		
		Label label = new Label(composite, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.NORMAL));
		label.setBounds(0, 5, 47, 31);
		label.setText("\u67E5\u770B");
		
		combo = new Combo(composite, SWT.READ_ONLY);
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				table.removeAll();
				JdbcTool jt = new JdbcTool();
				String sql = null;
				if("所有人".equals(combo.getText())) {
					sql = "SELECT cashier_id, cashier_name, is_maneger FROM cashier";
				}else if("管理员".equals(combo.getText())) {
					sql = "SELECT cashier_id, cashier_name, is_maneger FROM cashier WHERE is_maneger = '管理员'";
				}else {
					sql = "SELECT cashier_id, cashier_name, is_maneger FROM cashier WHERE is_maneger = '收银员'";
				}
				
				List<List<String>> list = jt.query(sql);
				int flag = 1;
				for(List<String> row : list) {
					TableItem tableItem = new TableItem(table, SWT.NONE);
					tableItem.setText(0, "" + flag);
					tableItem.setText(1, row.get(0));
					tableItem.setText(2, row.get(1));
					tableItem.setText(3, row.get(2));
					flag = flag + 1;
				}
			}
		});
		combo.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		combo.setBounds(47, 5, 77, 29);
		combo.add("所有人");
		combo.add("收银员");
		combo.add("管理员");
		combo.select(0);
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IWorkbenchPage page = getSite().getPage();
				page.hideView(page.findView(PowerView.ID));
				try {
					page.showView(WorkerView.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		btnNewButton.setBounds(880, 1, 135, 41);
		btnNewButton.setText("\u8FD4\u56DE\u4E0A\u7EA7\u83DC\u5355");
		
		Composite composite_1 = new Composite(container, SWT.NONE);
		composite_1.setBounds(0, 41, 1117, 519);
		
		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		table.setBounds(0, 0, 1017, 519);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("\u884C\u53F7");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(171);
		tableColumn_1.setText("\u5DE5\u53F7");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(239);
		tableColumn_2.setText("\u59D3\u540D");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(184);
		tableColumn_3.setText("\u6743\u9650");
		
		Button button = new Button(container, SWT.NONE);
		button.setBounds(0, 566, 508, 56);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
				MessageBox box = new MessageBox(shell);
				
				//获取用户选取的哪一行
				int index = table.getSelectionIndex();
				
				if(index < 0) {
					box.setMessage("请先查询您所需要修改的员工信息");
					box.open();
					return;
				}
				
				//获取行对象
				TableItem row = table.getItem(index);
				
				JdbcTool jt = new JdbcTool();
				String sql = "UPDATE cashier SET is_maneger = '管理员' WHERE cashier_id = '" + row.getText(1) + "'";
				jt.update(sql);
				
				table.removeAll();
				if("所有人".equals(combo.getText())) {
					sql = "SELECT cashier_id, cashier_name, is_maneger FROM cashier";
				}else if("管理员".equals(combo.getText())) {
					sql = "SELECT cashier_id, cashier_name, is_maneger FROM cashier WHERE is_maneger = '管理员'";
				}else {
					sql = "SELECT cashier_id, cashier_name, is_maneger FROM cashier WHERE is_maneger = '收银员'";
				}
				
				List<List<String>> list = jt.query(sql);
				int flag = 1;
				for(List<String> row1 : list) {
					TableItem tableItem = new TableItem(table, SWT.NONE);
					tableItem.setText(0, "" + flag);
					tableItem.setText(1, row1.get(0));
					tableItem.setText(2, row1.get(1));
					tableItem.setText(3, row1.get(2));
					flag = flag + 1;
				}
				
			}
		});
		button.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.NORMAL));
		button.setText("\u9009\u62E9\u6536\u94F6\u5458\u5347\u7EA7\u4E3A\u7BA1\u7406\u5458");
		
		Button button_1 = new Button(container, SWT.NONE);
		button_1.setBounds(509, 566, 508, 56);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
				MessageBox box = new MessageBox(shell);
				
				//获取用户选取的哪一行
				int index = table.getSelectionIndex();
				
				if(index < 0) {
					box.setMessage("请先查询您所需要修改的员工信息");
					box.open();
					return;
				}
				
				//获取行对象
				TableItem row = table.getItem(index);
				
				JdbcTool jt = new JdbcTool();
				String sql = "UPDATE cashier SET is_maneger = '收银员' WHERE cashier_id = '" + row.getText(1) + "'";
				jt.update(sql);
				
				table.removeAll();
				if("所有人".equals(combo.getText())) {
					sql = "SELECT cashier_id, cashier_name, is_maneger FROM cashier";
				}else if("管理员".equals(combo.getText())) {
					sql = "SELECT cashier_id, cashier_name, is_maneger FROM cashier WHERE is_maneger = '管理员'";
				}else {
					sql = "SELECT cashier_id, cashier_name, is_maneger FROM cashier WHERE is_maneger = '收银员'";
				}
				
				List<List<String>> list = jt.query(sql);
				int flag = 1;
				for(List<String> row1 : list) {
					TableItem tableItem = new TableItem(table, SWT.NONE);
					tableItem.setText(0, "" + flag);
					tableItem.setText(1, row1.get(0));
					tableItem.setText(2, row1.get(1));
					tableItem.setText(3, row1.get(2));
					flag = flag + 1;
				}
			}
		});
		button_1.setText("\u9009\u62E9\u7BA1\u7406\u5458\u964D\u7EA7\u4E3A\u6536\u94F6\u5458");
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.NORMAL));
		
		JdbcTool jt = new JdbcTool();
		String sql = "SELECT cashier_id, cashier_name, is_maneger FROM cashier";
		List<List<String>> list = jt.query(sql);
		int flag = 1;
		for(List<String> row : list) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(0, "" + flag);
			tableItem.setText(1, row.get(0));
			tableItem.setText(2, row.get(1));
			tableItem.setText(3, row.get(2));
			
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
