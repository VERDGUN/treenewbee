package com.treenewbee.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.treenewbee.dialog.VipRegistreDialog;
import com.treenewbee.dialog.VipUpdateDialog;
import com.treenewbee.util.JdbcTool;

import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class VipInfoView extends ViewPart {

	public static final String ID = "com.shxt.supersystem.view.VipInfoView"; //$NON-NLS-1$
	private Table table;
	private Text text;
	private Table table_1;
	private Combo combo;
	private Button button_4;
	private Button button_5;
	private List<List<String>> list = new ArrayList<List<String>>();
	
	public VipInfoView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		
		
		
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundImage(SWTResourceManager.getImage(VipInfoView.class, "/com/shxt/supersystem/picture/\u84DD\u8272_\u526F\u672C.jpg"));
		container.setBackgroundMode(SWT.INHERIT_FORCE);
		
		Composite composite = new Composite(container, SWT.BORDER);
		composite.setBounds(0, 0, 1012, 56);
		
		Button button = new Button(composite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
				VipRegistreDialog vipRegistreDialog = new VipRegistreDialog(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
				vipRegistreDialog.open();
			}
		});
		button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		button.setBounds(0, 0, 159, 52);
		button.setText("\u4F1A\u5458\u7533\u8BF7");
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
				MessageBox box = new MessageBox(shell);
				
				//获取用户选取的哪一行
				int index = table_1.getSelectionIndex();
				
				if(index < 0) {
					box.setMessage("请先查询您所需要修改的会员信息");
					box.open();
					return;
				}
				
				//获取行对象
				TableItem row = table_1.getItem(index);
				
				
				VipUpdateDialog vipUpdateDialog = new VipUpdateDialog(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
				vipUpdateDialog.open(row.getText(1));
				
				JdbcTool jt = new JdbcTool();
				String sql = null;
				if(button_5.getSelection()) {
					sql = "SELECT * FROM vip WHERE " + combo.getText().trim() + " = '" + text.getText().trim() + "'";
				}
				if(button_4.getSelection()) {
					sql = "SELECT * FROM vip WHERE " + combo.getText().trim() + " like '%" + text.getText().trim() + "%'";
				}
				
				table_1.removeAll();
				int flag = 1;
				list = jt.query(sql);
				for(List<String> row1 : list) {
					TableItem tableItem = new TableItem(table_1, SWT.NONE);
					tableItem.setText(0,"" + flag);
					tableItem.setText(1, row1.get(0));
					tableItem.setText(2, row1.get(1));
					tableItem.setText(3, row1.get(2));
					tableItem.setText(4, row1.get(3));
					tableItem.setText(5, row1.get(4));
					tableItem.setText(6, row1.get(5));
					tableItem.setText(7, row1.get(6));
					tableItem.setText(8, row1.get(7));
					tableItem.setText(9, row1.get(8));
					tableItem.setText(10, row1.get(9));
					tableItem.setText(11, row1.get(10));
					flag = flag + 1;
				}
			}
		});
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		button_1.setText("\u4F1A\u5458\u4FEE\u6539");
		button_1.setBounds(159, 0, 159, 52);
		
		Button button_2 = new Button(composite, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
				MessageBox box = new MessageBox(shell);
				
				//获取用户选取的哪一行
				int index = table_1.getSelectionIndex();
				
				if(index < 0) {
					box.setMessage("请先查询您所需要删除的会员信息");
					box.open();
					return;
				}
				//获取行对象
				TableItem row = table_1.getItem(index);
				
				JdbcTool jt = new JdbcTool();
				String sql = "DELETE FROM vip WHERE vip_id = " + row.getText(1) + "";
				jt.update(sql);
				
				
				box.setMessage("删除成功！");
				box.open();
				
				table_1.remove(index);
				
			}
		});
		button_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		button_2.setText("\u4F1A\u5458\u5220\u9664");
		button_2.setBounds(317, 0, 159, 52);
		
		Button button_3 = new Button(composite, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IWorkbenchPage page = getSite().getPage();
				page.hideView(page.findView(VipInfoView.ID));
				try {
					page.showView(VipView.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
			}
		});
		button_3.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		button_3.setText("\u8FD4\u56DE\u4E0A\u7EA7\u83DC\u5355");
		button_3.setBounds(850, 0, 159, 52);
		
		Button button_6 = new Button(composite, SWT.NONE);
		button_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IWorkbenchPage page = getSite().getPage();
				page.hideView(page.findView(VipInfoView.ID));
				try {
					page.showView(VipInfoView.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
			}
		});
		button_6.setText("\u5237\u65B0");
		button_6.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		button_6.setBounds(475, 0, 159, 52);
		
		Composite composite_1 = new Composite(container, SWT.BORDER);
		composite_1.setBounds(0, 55, 1011, 39);
		
		Label label = new Label(composite_1, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label.setBounds(0, 10, 45, 25);
		label.setText("\u8BF7\u9009\u62E9");
		
		combo = new Combo(composite_1, SWT.READ_ONLY);
		combo.setBounds(52, 10, 88, 25);
		combo.add("vip_id");
		combo.add("vip_name");
		combo.add("vip_sex");
		combo.add("vip_age");
		combo.add("vip_addr");
		combo.add("vip_consume");
		combo.add("vip_level");
		combo.add("vip_discount");
		combo.add("vip_comment");
		
		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setText("\u67E5\u8BE2");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_1.setBounds(146, 10, 37, 25);
		
		text = new Text(composite_1, SWT.BORDER);
		text.setBounds(186, 10, 154, 23);
		
		button_4 = new Button(composite_1, SWT.RADIO);
		button_4.setSelection(true);
		button_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button_4.setBounds(440, 12, 97, 17);
		button_4.setText("\u6A21\u7CCA\u67E5\u8BE2");
		
		button_5 = new Button(composite_1, SWT.RADIO);
		button_5.setText("\u7CBE\u786E\u67E5\u8BE2");
		button_5.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button_5.setBounds(543, 12, 97, 17);
		
		Button button_7 = new Button(composite_1, SWT.NONE);
		button_7.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				JdbcTool jt = new JdbcTool();
				String sql = null;
				String s1 = combo.getText().trim();
				String s2 = text.getText().trim();
				
				Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
				MessageBox box = new MessageBox(shell);
				if("".equals(s1)) {
					box.setMessage("请选择查询方式");
					box.open();
					return;
				}else if("".equals(s2)) {
					box.setMessage("请输入查询字段");
					box.open();
					return;
				}
				
				if(button_5.getSelection()) {
					sql = "SELECT * FROM vip WHERE " + s1 + " = '" + s2 + "'";
				}
				if(button_4.getSelection()) {
					sql = "SELECT * FROM vip WHERE " + s1 + " like '%" + s2 + "%'";
				}
				
				table_1.removeAll();
				int flag = 1;
				list = jt.query(sql);
				
				if(list.size() == 0) {
					box.setMessage("此会员不存在");
					box.open();
					text.setText("");
					return;
				}
				
				for(List<String> row : list) {
					TableItem tableItem = new TableItem(table_1, SWT.NONE);
					tableItem.setText(0,"" + flag);
					tableItem.setText(1, row.get(0));
					tableItem.setText(2, row.get(1));
					tableItem.setText(3, row.get(2));
					tableItem.setText(4, row.get(3));
					tableItem.setText(5, row.get(4));
					tableItem.setText(6, row.get(5));
					tableItem.setText(7, row.get(6));
					tableItem.setText(8, row.get(7));
					tableItem.setText(9, row.get(8));
					tableItem.setText(10, row.get(9));
					tableItem.setText(11, row.get(10));
					flag = flag + 1;
				}
				
			}
		});
		button_7.setBounds(354, 8, 57, 27);
		button_7.setText("\u786E\u5B9A");
		
		Composite composite_2 = new Composite(container, SWT.BORDER);
		composite_2.setBounds(0, 94, 1119, 317);
		
		table = new Table(composite_2, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(0, 0, 1009, 313);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(66);
		tableColumn.setText("\u884C\u53F7");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(71);
		tableColumn_1.setText("\u4F1A\u5458\u5361\u53F7");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(68);
		tableColumn_2.setText("\u4F1A\u5458\u59D3\u540D");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(45);
		tableColumn_3.setText("\u6027\u522B");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(43);
		tableColumn_4.setText("\u5E74\u9F84");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(134);
		tableColumn_5.setText("\u4F4F\u5740");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(115);
		tableColumn_6.setText("\u90AE\u7BB1");
		
		TableColumn tableColumn_7 = new TableColumn(table, SWT.NONE);
		tableColumn_7.setWidth(102);
		tableColumn_7.setText("\u8054\u7CFB\u7535\u8BDD");
		
		TableColumn tableColumn_8 = new TableColumn(table, SWT.NONE);
		tableColumn_8.setWidth(80);
		tableColumn_8.setText("\u6D88\u8D39\u91D1\u989D");
		
		TableColumn tableColumn_9 = new TableColumn(table, SWT.NONE);
		tableColumn_9.setWidth(61);
		tableColumn_9.setText("\u4F1A\u5458\u7B49\u7EA7");
		
		TableColumn tableColumn_10 = new TableColumn(table, SWT.NONE);
		tableColumn_10.setWidth(62);
		tableColumn_10.setText("\u4F18\u60E0\u6298\u6263");
		
		TableColumn tableColumn_11 = new TableColumn(table, SWT.NONE);
		tableColumn_11.setWidth(152);
		tableColumn_11.setText("\u5907\u6CE8");
		
		
		JdbcTool jt = new JdbcTool();
		String sql;
		int flag = 1;
		sql = "SELECT * FROM vip";
		List<List<String>> list = jt.query(sql);
		for(List<String> row : list) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(0,"" + flag);
			tableItem.setText(1, row.get(0));
			tableItem.setText(2, row.get(1));
			tableItem.setText(3, row.get(2));
			tableItem.setText(4, row.get(3));
			tableItem.setText(5, row.get(4));
			tableItem.setText(6, row.get(5));
			tableItem.setText(7, row.get(6));
			tableItem.setText(8, row.get(7));
			tableItem.setText(9, row.get(8));
			tableItem.setText(10, row.get(9));
			tableItem.setText(11, row.get(10));
			flag = flag + 1;
		}
		
		
		Composite composite_3 = new Composite(container, SWT.BORDER);
		composite_3.setBounds(0, 412, 1010, 256);
		
		table_1 = new Table(composite_3, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
		table_1.setHeaderVisible(true);
		table_1.setBounds(0, 37, 1115, 186);
		
		
		TableColumn tableColumn_12 = new TableColumn(table_1, SWT.NONE);
		tableColumn_12.setWidth(66);
		tableColumn_12.setText("\u884C\u53F7");
		
		TableColumn tableColumn_13 = new TableColumn(table_1, SWT.NONE);
		tableColumn_13.setWidth(74);
		tableColumn_13.setText("\u4F1A\u5458\u5361\u53F7");
		
		TableColumn tableColumn_14 = new TableColumn(table_1, SWT.NONE);
		tableColumn_14.setWidth(74);
		tableColumn_14.setText("\u4F1A\u5458\u59D3\u540D");
		
		TableColumn tableColumn_15 = new TableColumn(table_1, SWT.NONE);
		tableColumn_15.setWidth(46);
		tableColumn_15.setText("\u6027\u522B");
		
		TableColumn tableColumn_16 = new TableColumn(table_1, SWT.NONE);
		tableColumn_16.setWidth(42);
		tableColumn_16.setText("\u5E74\u9F84");
		
		TableColumn tableColumn_17 = new TableColumn(table_1, SWT.NONE);
		tableColumn_17.setWidth(128);
		tableColumn_17.setText("\u4F4F\u5740");
		
		TableColumn tableColumn_18 = new TableColumn(table_1, SWT.NONE);
		tableColumn_18.setWidth(115);
		tableColumn_18.setText("\u90AE\u7BB1");
		
		TableColumn tableColumn_19 = new TableColumn(table_1, SWT.NONE);
		tableColumn_19.setWidth(93);
		tableColumn_19.setText("\u8054\u7CFB\u7535\u8BDD");
		
		TableColumn tableColumn_20 = new TableColumn(table_1, SWT.NONE);
		tableColumn_20.setWidth(72);
		tableColumn_20.setText("\u6D88\u8D39\u91D1\u989D");
		
		TableColumn tableColumn_21 = new TableColumn(table_1, SWT.NONE);
		tableColumn_21.setWidth(73);
		tableColumn_21.setText("\u4F1A\u5458\u7B49\u7EA7");
		
		TableColumn tableColumn_22 = new TableColumn(table_1, SWT.NONE);
		tableColumn_22.setWidth(69);
		tableColumn_22.setText("\u4F18\u60E0\u6298\u6263");
		
		TableColumn tableColumn_23 = new TableColumn(table_1, SWT.NONE);
		tableColumn_23.setWidth(152);
		tableColumn_23.setText("\u5907\u6CE8");
		
		
		
		Label label_2 = new Label(composite_3, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 19, SWT.BOLD));
		label_2.setBounds(0, 0, 144, 40);
		label_2.setText("\u67E5\u8BE2\u663E\u793A\uFF1A");

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
