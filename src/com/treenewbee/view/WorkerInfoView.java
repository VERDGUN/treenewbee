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
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.treenewbee.dialog.WorkUpdateDialog;
import com.treenewbee.dialog.WorkerAddDialog;
import com.treenewbee.util.JdbcTool;

import org.eclipse.swt.widgets.TableItem;

public class WorkerInfoView extends ViewPart {

	public static final String ID = "com.shxt.supersystem.view.WorkerInfoView"; //$NON-NLS-1$
	private Text text;
	private Table table;
	private Table table_1;
	private Combo combo;
	private Button btnLike;
	private Button btnSure;

	public WorkerInfoView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundImage(SWTResourceManager.getImage(WorkerInfoView.class, "/com/shxt/supersystem/picture/\u84DD\u8272_\u526F\u672C.jpg"));
		container.setBackgroundMode(SWT.INHERIT_FORCE);
		{
			Composite composite = new Composite(container, SWT.BORDER);
			composite.setBounds(0, 0, 1019, 56);
			{
				Button button = new Button(composite, SWT.NONE);
				button.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						IWorkbenchPage page = getSite().getPage();
						page.hideView(page.findView(WorkerInfoView.ID));
						try {
							page.showView(WorkerView.ID);
						} catch (PartInitException e1) {
							e1.printStackTrace();
						}
					}
				});
				button.setText("\u8FD4\u56DE\u4E0A\u7EA7\u83DC\u5355");
				button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
				button.setBounds(890, 0, 125, 52);
			}
			{
				Button button = new Button(composite, SWT.NONE);
				button.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
						WorkerAddDialog workerAddDialog = new WorkerAddDialog(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
						workerAddDialog.open();
					}
				});
				button.setText("\u6DFB\u52A0\u5458\u5DE5");
				button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
				button.setBounds(0, 0, 159, 52);
			}
			{
				Button button = new Button(composite, SWT.NONE);
				button.addSelectionListener(new SelectionAdapter() {
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
						
						
						WorkUpdateDialog workUpdateDialog = new WorkUpdateDialog(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
						workUpdateDialog.open(row.getText(1));
						
						JdbcTool jt = new JdbcTool();
						String sql = null;
						if(btnSure.getSelection()) {
							sql = "SELECT * FROM cashier WHERE " + combo.getText().trim() + " = '" + text.getText().trim() + "'";
						}
						if(btnLike.getSelection()) {
							sql = "SELECT * FROM cashier WHERE " + combo.getText().trim() + " like '%" + text.getText().trim() + "%'";
						}
						
						table_1.removeAll();
						int flag = 1;
						List<List<String>> list = jt.query(sql);
						for(List<String> row1 : list) {
							TableItem tableItem = new TableItem(table_1, SWT.NONE);
							tableItem.setText(0,"" + flag);
							tableItem.setText(1, row1.get(0));
							tableItem.setText(2, row1.get(3));
							tableItem.setText(3, row1.get(4));
							tableItem.setText(4, row1.get(5));
							tableItem.setText(5, row1.get(7));
							tableItem.setText(6, row1.get(6));
							tableItem.setText(7, row1.get(2));
							flag = flag + 1;
						}
						
					}
				});
				button.setText("\u8D44\u6599\u4FEE\u6539");
				button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
				button.setBounds(157, 0, 159, 52);
			}
			{
				Button button = new Button(composite, SWT.NONE);
				button.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
						MessageBox box = new MessageBox(shell);
						
						//获取用户选取的哪一行
						int index = table_1.getSelectionIndex();
						
						if(index < 0) {
							box.setMessage("请先查询您所需要删除的员工信息");
							box.open();
							return;
						}
						//获取行对象
						TableItem row = table_1.getItem(index);
						
						JdbcTool jt = new JdbcTool();
						String sql = "DELETE FROM cashier WHERE cashier_id = " + row.getText(1) + "";
						jt.update(sql);
						
						
						box.setMessage("删除成功！");
						box.open();
						
						table_1.remove(index);
					}
				});
				button.setText("\u5458\u5DE5\u5220\u9664");
				button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
				button.setBounds(314, 0, 159, 52);
			}
			{
				Button button = new Button(composite, SWT.NONE);
				button.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						IWorkbenchPage page = getSite().getPage();
						page.hideView(page.findView(WorkerInfoView.ID));
						try {
							page.showView(WorkerInfoView.ID);
						} catch (PartInitException e1) {
							e1.printStackTrace();
						}
					}
				});
				button.setText("\u5237\u65B0");
				button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
				button.setBounds(471, 0, 159, 52);
			}
		}
		{
			Composite composite = new Composite(container, SWT.BORDER);
			composite.setBounds(0, 53, 1019, 37);
			{
				Label label = new Label(composite, SWT.NONE);
				label.setText("\u8BF7\u9009\u62E9");
				label.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
				label.setBounds(0, 8, 45, 25);
			}
			{
				combo = new Combo(composite, SWT.READ_ONLY);
				combo.setBounds(51, 8, 107, 25);
				combo.add("cashier_id");
				combo.add("is_maneger");
				combo.add("cashier_name");
			}
			{
				Label label = new Label(composite, SWT.NONE);
				label.setText("\u67E5\u8BE2");
				label.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
				label.setBounds(165, 8, 30, 25);
			}
			{
				text = new Text(composite, SWT.BORDER);
				text.setBounds(201, 8, 154, 23);
			}
			{
				Button button = new Button(composite, SWT.NONE);
				button.addSelectionListener(new SelectionAdapter() {
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
						
						if(btnSure.getSelection()) {
							sql = "SELECT * FROM cashier WHERE " + s1 + " = '" + s2 + "'";
						}
						if(btnLike.getSelection()) {
							sql = "SELECT * FROM cashier WHERE " + s1 + " like '%" + s2 + "%'";
						}
						
						table_1.removeAll();
						int flag = 1;
						List<List<String>> list = jt.query(sql);
						
						if(list.size() == 0) {
							box.setMessage("此员工不存在！请重新输入");
							box.open();
							text.setText("");
							return;
						}
						for(List<String> row : list) {
							TableItem tableItem = new TableItem(table_1, SWT.NONE);
							tableItem.setText(0,"" + flag);
							tableItem.setText(1, row.get(0));
							tableItem.setText(2, row.get(3));
							tableItem.setText(3, row.get(4));
							tableItem.setText(4, row.get(5));
							tableItem.setText(5, row.get(7));
							tableItem.setText(6, row.get(6));
							tableItem.setText(7, row.get(2));
							flag = flag + 1;
						}
					}
				});
				button.setText("\u786E\u5B9A");
				button.setBounds(361, 6, 57, 27);
			}
			{
				btnLike = new Button(composite, SWT.RADIO);
				btnLike.setText("\u6A21\u7CCA\u67E5\u8BE2");
				btnLike.setSelection(true);
				btnLike.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
				btnLike.setBounds(424, 8, 97, 17);
			}
			{
				btnSure = new Button(composite, SWT.RADIO);
				btnSure.setText("\u7CBE\u786E\u67E5\u8BE2");
				btnSure.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
				btnSure.setBounds(527, 8, 97, 17);
			}
		}
		{
			table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
			table.setLinesVisible(true);
			table.setHeaderVisible(true);
			table.setBounds(0, 87, 1019, 317);
			{
				TableColumn tableColumn = new TableColumn(table, SWT.NONE);
				tableColumn.setWidth(76);
				tableColumn.setText("\u884C\u53F7");
			}
			{
				TableColumn tableColumn = new TableColumn(table, SWT.NONE);
				tableColumn.setWidth(148);
				tableColumn.setText("\u5DE5\u53F7");
			}
			{
				TableColumn tableColumn = new TableColumn(table, SWT.NONE);
				tableColumn.setWidth(139);
				tableColumn.setText("\u5458\u5DE5\u59D3\u540D");
			}
			{
				TableColumn tableColumn = new TableColumn(table, SWT.NONE);
				tableColumn.setWidth(115);
				tableColumn.setText("\u6027\u522B");
			}
			{
				TableColumn tableColumn = new TableColumn(table, SWT.NONE);
				tableColumn.setWidth(123);
				tableColumn.setText("\u5E74\u9F84");
			}
			{
				TableColumn tableColumn = new TableColumn(table, SWT.NONE);
				tableColumn.setWidth(138);
				tableColumn.setText("\u5DE5\u9F84");
			}
			{
				TableColumn tableColumn = new TableColumn(table, SWT.NONE);
				tableColumn.setWidth(119);
				tableColumn.setText("\u8054\u7CFB\u7535\u8BDD");
			}
			{
				TableColumn tableColumn = new TableColumn(table, SWT.NONE);
				tableColumn.setWidth(147);
				tableColumn.setText("\u6743\u9650");
			}
			{

				JdbcTool jt = new JdbcTool();
				String sql;
				int flag = 1;
				sql = "SELECT * FROM cashier";
				List<List<String>> list = jt.query(sql);
				for(List<String> row : list) {
					TableItem tableItem = new TableItem(table, SWT.NONE);
					tableItem.setText(0,"" + flag);
					tableItem.setText(1, row.get(0));
					tableItem.setText(2, row.get(3));
					tableItem.setText(3, row.get(4));
					tableItem.setText(4, row.get(5));
					tableItem.setText(5, row.get(7));
					tableItem.setText(6, row.get(6));
					tableItem.setText(7, row.get(2));
					flag = flag + 1;
				}
			}
		}
		{
			table_1 = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
			table_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
			table_1.setHeaderVisible(true);
			table_1.setBounds(0, 447, 1019, 238);
			{
				TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
				tableColumn.setWidth(66);
				tableColumn.setText("\u884C\u53F7");
			}
			{
				TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
				tableColumn.setWidth(124);
				tableColumn.setText("\u5DE5\u53F7");
			}
			{
				TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
				tableColumn.setWidth(139);
				tableColumn.setText("\u5458\u5DE5\u59D3\u540D");
			}
			{
				TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
				tableColumn.setWidth(115);
				tableColumn.setText("\u6027\u522B");
			}
			{
				TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
				tableColumn.setWidth(97);
				tableColumn.setText("\u5E74\u9F84");
			}
			{
				TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
				tableColumn.setWidth(119);
				tableColumn.setText("\u5DE5\u9F84");
			}
			{
				TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
				tableColumn.setWidth(160);
				tableColumn.setText("\u8054\u7CFB\u7535\u8BDD");
			}
			{
				TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
				tableColumn.setWidth(155);
				tableColumn.setText("\u6743\u9650");
			}
		}
		{
			Label label = new Label(container, SWT.NONE);
			label.setText("\u67E5\u8BE2\u663E\u793A\uFF1A");
			label.setFont(SWTResourceManager.getFont("微软雅黑", 19, SWT.BOLD));
			label.setBounds(0, 410, 144, 37);
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
