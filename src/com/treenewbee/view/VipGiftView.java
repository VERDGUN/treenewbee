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
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;

import com.treenewbee.dialog.GiftAdddialog;
import com.treenewbee.dialog.GiftUpdateDialog;
import com.treenewbee.util.JdbcTool;

public class VipGiftView extends ViewPart {

	public static final String ID = "com.shxt.supersystem.view.VipGiftView"; //$NON-NLS-1$
	private Table table;
	public VipGiftView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundImage(SWTResourceManager.getImage(VipGiftView.class, "/com/shxt/supersystem/picture/\u84DD\u8272_\u526F\u672C.jpg"));
		container.setBackgroundMode(SWT.INHERIT_FORCE);
		
		Composite composite = new Composite(container, SWT.BORDER);
		composite.setBounds(0, 46, 1017, 42);
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
				GiftAdddialog giftAddDialog = new GiftAdddialog(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
				giftAddDialog.open();
				
				IWorkbenchPage page = getSite().getPage();
				page.hideView(page.findView(VipGiftView.ID));
				try {
					page.showView(VipGiftView.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		btnNewButton.setBounds(0, 0, 111, 38);
		btnNewButton.setText("\u589E\u52A0");
		
		Button button = new Button(composite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
				MessageBox box = new MessageBox(shell);
				
				//获取用户选取的哪一行
				int index = table.getSelectionIndex();
				
				if(index < 0) {
					box.setMessage("请先选择您所需要修改的信息行");
					box.open();
					return;
				}
				
				//获取行对象
				TableItem row = table.getItem(index);
				
				
				GiftUpdateDialog giftUpdateDialog = new GiftUpdateDialog(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
				giftUpdateDialog.open(row.getText(1));
				
				IWorkbenchPage page = getSite().getPage();
				page.hideView(page.findView(VipGiftView.ID));
				try {
					page.showView(VipGiftView.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
			}
		});
		button.setText("\u4FEE\u6539");
		button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		button.setBounds(108, 0, 128, 38);
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
				MessageBox box = new MessageBox(shell);
				
				//获取用户选取的哪一行
				int index = table.getSelectionIndex();
				
				if(index < 0) {
					box.setMessage("请先查询您所需要删除的会员信息");
					box.open();
					return;
				}
				//获取行对象
				TableItem row = table.getItem(index);
				
				JdbcTool jt = new JdbcTool();
				String sql = "DELETE FROM privilege WHERE vip_consume = " + row.getText(1) + "";
				jt.update(sql);
				
				
				box.setMessage("修改成功！");
				box.open();
				
				IWorkbenchPage page = getSite().getPage();
				page.hideView(page.findView(VipGiftView.ID));
				try {
					page.showView(VipGiftView.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		button_1.setText("\u5220\u9664");
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		button_1.setBounds(234, 0, 128, 38);
		
		Button button_2 = new Button(composite, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IWorkbenchPage page = getSite().getPage();
				page.hideView(page.findView(VipGiftView.ID));
				try {
					page.showView(VipView.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
			}
		});
		button_2.setText("\u8FD4\u56DE\u4E0A\u7EA7\u83DC\u5355");
		button_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		button_2.setBounds(887, 0, 128, 38);
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 20, SWT.NORMAL));
		lblNewLabel.setBounds(464, 5, 211, 40);
		lblNewLabel.setText("\u4F1A\u5458\u4F18\u60E0");
		
		Composite composite_1 = new Composite(container, SWT.NONE);
		composite_1.setBounds(0, 94, 1116, 592);
		
		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		table.setBounds(0, 0, 1016, 582);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(114);
		tableColumn.setText("\u884C\u53F7");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(126);
		tableColumn_3.setText("\u4F1A\u5458\u6D88\u8D39\u91D1\u989D");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(179);
		tableColumn_1.setText("\u793C\u54C1\u7F16\u7801");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(174);
		tableColumn_2.setText("\u793C\u54C1\u540D\u79F0");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(134);
		tableColumn_4.setText("\u4F1A\u5458\u7B49\u7EA7");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(139);
		tableColumn_5.setText("\u4F1A\u5458\u6298\u6263");
		
		TableColumn tableColumn_10 = new TableColumn(table, SWT.NONE);
		tableColumn_10.setWidth(146);
		tableColumn_10.setText("\u5907\u6CE8");
		
		JdbcTool jt = new JdbcTool();
		String sql = "SELECT vip_consume,good_id, good_name, vip_level, vip_discount, privilege_comment FROM privilege ORDER BY vip_consume";
		List<List<String>> list = jt.query(sql);
		int flag = 1;
		for(List<String> row : list) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(0, "" + flag);
			tableItem.setText(1, row.get(0));
			tableItem.setText(2, row.get(1));
			tableItem.setText(3, row.get(2));
			tableItem.setText(4, row.get(3));
			tableItem.setText(5, row.get(4));
			tableItem.setText(6, row.get(5));
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
