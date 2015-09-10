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
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TableColumn;

import com.treenewbee.dialog.GoodAddDialog;
import com.treenewbee.dialog.GoodUpdateDialog;
import com.treenewbee.util.JdbcTool;

import org.eclipse.swt.widgets.TableItem;

public class GoodInfoView extends ViewPart {

	public static final String ID = "com.shxt.supersystem.view.GoodInfoView"; //$NON-NLS-1$
	private Text text;
	private Table table;
	private Table table_1;
	private Combo combo;
	private Button btnLike;
	private Button btnSure;

	public GoodInfoView() {
	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundMode(SWT.INHERIT_FORCE);
		container.setBackgroundImage(SWTResourceManager.getImage(GoodInfoView.class, "/com/shxt/supersystem/picture/\u84DD\u8272_\u526F\u672C.jpg"));

		Composite composite = new Composite(container, SWT.BORDER);
		composite.setBounds(0, 0, 1004, 56);
		{
			Button button = new Button(composite, SWT.NONE);
			button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					IWorkbenchPage page = getSite().getPage();
					page.hideView(page.findView(GoodInfoView.ID));
					try {
						page.showView(BaseView.ID);
					} catch (PartInitException e1) {
						e1.printStackTrace();
					}
				}
			});
			button.setText("\u8FD4\u56DE\u4E0A\u7EA7\u83DC\u5355");
			button.setBounds(875, 0, 125, 52);
		}
		{
			Button button = new Button(composite, SWT.NONE);
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				/**属性修改*/
				public void widgetSelected(SelectionEvent e) {
					Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
					MessageBox box = new MessageBox(shell);

					// 获取用户选取的哪一行
					int index = table_1.getSelectionIndex();

					if (index < 0) {
						box.setMessage("请先查询您所需要修改的商品信息");
						box.open();
						return;
					}

					// 获取行对象
					TableItem row = table_1.getItem(index);

					GoodUpdateDialog goodUpdateDialog = new GoodUpdateDialog(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
					goodUpdateDialog.open(row.getText(1));

					JdbcTool jt = new JdbcTool();
					String sql = null;
					if (btnSure.getSelection()) {
						sql = "SELECT * FROM good WHERE " + combo.getText().trim() + " = '" + text.getText().trim() + "'";
					}
					if (btnLike.getSelection()) {
						sql = "SELECT * FROM good WHERE " + combo.getText().trim() + " like '%" + text.getText().trim() + "%'";
					}

					table_1.removeAll();
					int flag = 1;
					List<List<String>> list = jt.query(sql);
					for (List<String> row1 : list) {
						TableItem tableItem = new TableItem(table_1, SWT.NONE);
						tableItem.setText(0, "" + flag);
						tableItem.setText(1, row1.get(0));
						tableItem.setText(2, row1.get(1));
						tableItem.setText(3, row1.get(2));
						tableItem.setText(4, row1.get(3));
						tableItem.setText(5, row1.get(4));
						tableItem.setText(6, row1.get(5));
						tableItem.setText(7, row1.get(6));
						tableItem.setText(8, row1.get(7));
						flag = flag + 1;
					}
				}
			});
			button.setText("\u5C5E\u6027\u4FEE\u6539");
			button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
			button.setBounds(0, 0, 159, 52);
		}
		{
			Button button = new Button(composite, SWT.NONE);
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				/**商品淘汰*/
				public void widgetSelected(SelectionEvent e) {
					Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
					MessageBox box = new MessageBox(shell);

					// 获取用户选取的哪一行
					int index = table_1.getSelectionIndex();

					if (index < 0) {
						box.setMessage("请先查询您所需要删除的商品信息");
						box.open();
						return;
					}
					// 获取行对象
					TableItem row = table_1.getItem(index);

					/**商品备注淘汰*/
					JdbcTool jt = new JdbcTool();
					String sql = "UPDATE good SET good_comment = '淘汰' WHERE good_id = '" + row.getText(1) + "'";
					jt.update(sql);
					
					/**加入损耗*/
					Date now = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String nowdate = sdf.format(now);
					sql = "SELECT SUM(storage_num + shelve_num) FROM import_good WHERE good_id = '" + row.getText(1) + "'";
					List<List<String>> list = jt.query(sql);
					sql = "INSERT INTO loss VALUES ('" + nowdate + "','" + row.getText(1) + "','" + list.get(0).get(0) + "', '商品淘汰')";
					jt.update(sql);
					
					/**仓库上架都置零*/
					sql = "UPDATE import_good SET shelve_num = 0, storage_num = 0 WHERE good_id = '" + row.getText(1) + "'";
					jt.update(sql);
					
					
					box.setMessage("删除成功！");
					box.open();

//					table_1.remove(index);
				}
			});
			button.setText("\u5546\u54C1\u6DD8\u6C70");
			button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
			button.setBounds(159, 0, 159, 52);
		}
		{
			Button button = new Button(composite, SWT.NONE);
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				/**刷新*/
				public void widgetSelected(SelectionEvent e) {
					IWorkbenchPage page = getSite().getPage();
					page.hideView(page.findView(GoodInfoView.ID));

					try {
						page.showView(GoodInfoView.ID);
					} catch (PartInitException e1) {
						e1.printStackTrace();
					}
				}
			});
			button.setText("\u5237\u65B0");
			button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
			button.setBounds(318, 0, 159, 52);
		}
		{
			Composite composite_1 = new Composite(container, SWT.BORDER);
			composite_1.setBounds(0, 53, 1004, 37);
			{
				Label label = new Label(composite_1, SWT.NONE);
				label.setText("\u8BF7\u9009\u62E9");
				label.setFont(SWTResourceManager
						.getFont("微软雅黑", 11, SWT.NORMAL));
				label.setBounds(0, 8, 45, 25);
			}
			{
				combo = new Combo(composite_1, SWT.READ_ONLY);
				combo.setBounds(51, 8, 88, 25);
				combo.add("good_id");
				combo.add("good_name");
				combo.add("good_type");
				combo.add("good_comment");
			}
			{
				Label label = new Label(composite_1, SWT.NONE);
				label.setText("\u67E5\u8BE2");
				label.setFont(SWTResourceManager
						.getFont("微软雅黑", 11, SWT.NORMAL));
				label.setBounds(145, 8, 37, 25);
			}
			{
				text = new Text(composite_1, SWT.BORDER);
				text.setBounds(182, 8, 154, 23);
			}
			{
				Button button = new Button(composite_1, SWT.NONE);
				button.addSelectionListener(new SelectionAdapter() {
					@Override
					/**条件查询*/
					public void widgetSelected(SelectionEvent e) {
						JdbcTool jt = new JdbcTool();
						String sql = null;
						String s1 = combo.getText().trim();
						String s2 = text.getText().trim();

						Shell shell = PlatformUI.getWorkbench()
								.getActiveWorkbenchWindow().getShell();
						MessageBox box = new MessageBox(shell);
						if ("".equals(s1)) {
							box.setMessage("请选择查询方式");
							box.open();
							return;
						} else if ("".equals(s2)) {
							box.setMessage("请输入查询字段");
							box.open();
							return;
						}

						if (btnSure.getSelection()) {
							sql = "SELECT * FROM good WHERE " + s1 + " = '" + s2 + "'";
						}
						if (btnLike.getSelection()) {
							sql = "SELECT * FROM good WHERE " + s1 + " like '%" + s2 + "%'";
						}

						table_1.removeAll();
						int flag = 1;
						List<List<String>> list = jt.query(sql);

						if (list.size() == 0) {
							box.setMessage("此商品不存在，请重新输入");
							box.open();
							text.setText("");
							return;
						}
						for (List<String> row : list) {
							TableItem tableItem = new TableItem(table_1, SWT.NONE);
							tableItem.setText(0, "" + flag);
							tableItem.setText(1, row.get(0));
							tableItem.setText(2, row.get(1));
							tableItem.setText(3, row.get(2));
							tableItem.setText(4, row.get(3));
							tableItem.setText(5, row.get(4));
							tableItem.setText(6, row.get(5));
							tableItem.setText(7, row.get(6));
							tableItem.setText(8, row.get(7));
							flag = flag + 1;
						}
					}
				});
				button.setText("\u786E\u5B9A");
				button.setBounds(342, 6, 57, 27);
			}
			{
				btnLike = new Button(composite_1, SWT.RADIO);
				btnLike.setText("\u6A21\u7CCA\u67E5\u8BE2");
				btnLike.setSelection(true);
				btnLike.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
				btnLike.setBounds(424, 8, 97, 17);
			}
			{
				btnSure = new Button(composite_1, SWT.RADIO);
				btnSure.setText("\u7CBE\u786E\u67E5\u8BE2");
				btnSure.setFont(SWTResourceManager.getFont("微软雅黑", 11,
						SWT.NORMAL));
				btnSure.setBounds(527, 8, 97, 17);
			}
		}
		{
			Composite composite_1 = new Composite(container, SWT.NONE);
			composite_1.setBounds(0, 92, 1004, 317);
			{
				table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
				table.setLinesVisible(true);
				table.setHeaderVisible(true);
				table.setBounds(0, 0, 1004, 317);
				{
					TableColumn tableColumn = new TableColumn(table, SWT.NONE);
					tableColumn.setWidth(66);
					tableColumn.setText("\u884C\u53F7");
				}
				{
					TableColumn tblclmngoodid = new TableColumn(table, SWT.NONE);
					tblclmngoodid.setWidth(145);
					tblclmngoodid.setText("\u5546\u54C1\u7F16\u7801(good_id)");
				}
				{
					TableColumn tblclmngoodname = new TableColumn(table, SWT.NONE);
					tblclmngoodname.setWidth(147);
					tblclmngoodname.setText("\u5546\u54C1\u540D\u79F0(good_name)");
				}
				{
					TableColumn tblclmngoodtype = new TableColumn(table, SWT.NONE);
					tblclmngoodtype.setWidth(135);
					tblclmngoodtype.setText("\u5546\u54C1\u7C7B\u522B(good_type)");
				}
				{
					TableColumn tableColumn = new TableColumn(table, SWT.NONE);
					tableColumn.setWidth(79);
					tableColumn.setText("\u6210\u672C\u4EF7");
				}
				{
					TableColumn tableColumn = new TableColumn(table, SWT.NONE);
					tableColumn.setWidth(80);
					tableColumn.setText("\u9500\u552E\u4EF7");
				}
				{
					TableColumn tableColumn = new TableColumn(table, SWT.NONE);
					tableColumn.setWidth(73);
					tableColumn.setText("\u6298\u6263\u4EF7");
				}
				{
					TableColumn tableColumn = new TableColumn(table, SWT.NONE);
					tableColumn.setWidth(83);
					tableColumn.setText("\u5229\u6DA6");
				}
				{
					TableColumn tblclmngoodcomment = new TableColumn(table, SWT.NONE);
					tblclmngoodcomment.setWidth(187);
					tblclmngoodcomment.setText("\u5907\u6CE8(good_comment)");
				}
				{
					JdbcTool jt = new JdbcTool();
					String sql;
					int flag = 1;
					sql = "SELECT * FROM good";
					List<List<String>> list = jt.query(sql);
					for (List<String> row : list) {
						TableItem tableItem = new TableItem(table, SWT.NONE);
						tableItem.setText(0, "" + flag);
						tableItem.setText(1, row.get(0));
						tableItem.setText(2, row.get(1));
						tableItem.setText(3, row.get(2));
						tableItem.setText(4, row.get(3));
						tableItem.setText(5, row.get(4));
						tableItem.setText(6, row.get(5));
						tableItem.setText(7, row.get(6));
						tableItem.setText(8, row.get(7));

						flag = flag + 1;
					}
				}

			}
		}
		{
			Label label = new Label(container, SWT.NONE);
			label.setText("\u67E5\u8BE2\u663E\u793A\uFF1A");
			label.setFont(SWTResourceManager.getFont("微软雅黑", 19, SWT.BOLD));
			label.setBounds(0, 408, 144, 37);
		}
		{
			table_1 = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
			table_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
			table_1.setHeaderVisible(true);
			table_1.setBounds(0, 451, 1004, 175);
			{
				TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
				tableColumn.setWidth(66);
				tableColumn.setText("\u884C\u53F7");
			}
			{
				TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
				tableColumn.setWidth(148);
				tableColumn.setText("\u5546\u54C1\u7F16\u7801");
			}
			{
				TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
				tableColumn.setWidth(139);
				tableColumn.setText("\u5546\u54C1\u540D\u79F0");
			}
			{
				TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
				tableColumn.setWidth(139);
				tableColumn.setText("\u5546\u54C1\u7C7B\u522B");
			}
			{
				TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
				tableColumn.setWidth(85);
				tableColumn.setText("\u6210\u672C\u4EF7");
			}
			{
				TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
				tableColumn.setWidth(84);
				tableColumn.setText("\u6298\u6263\u4EF7");
			}
			{
				TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
				tableColumn.setWidth(81);
				tableColumn.setText("\u9500\u552E\u4EF7");
			}
			{
				TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
				tableColumn.setWidth(76);
				tableColumn.setText("\u5229\u6DA6");
			}
			{
				TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
				tableColumn.setWidth(179);
				tableColumn.setText("\u5907\u6CE8");
			}
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
