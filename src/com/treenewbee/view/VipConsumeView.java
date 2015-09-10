package com.treenewbee.view;

import java.util.List;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import com.treenewbee.util.JdbcTool;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class VipConsumeView extends ViewPart {

	public static final String ID = "com.shxt.supersystem.view.VipConsumeView"; //$NON-NLS-1$
	private Table table;

	public VipConsumeView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundImage(SWTResourceManager.getImage(VipConsumeView.class, "/com/shxt/supersystem/picture/\u84DD\u8272_\u526F\u672C.jpg"));
		container.setBackgroundMode(SWT.INHERIT_FORCE);
		
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
		table.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		table.setBounds(0, 33, 1015, 523);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("\u884C\u53F7");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(156);
		tableColumn_1.setText("\u4F1A\u5458\u5361\u53F7");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(188);
		tableColumn_2.setText("\u4F1A\u5458\u59D3\u540D");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(247);
		tableColumn_3.setText("\u6D88\u8D39\u91D1\u989D");
		
		JdbcTool jt = new JdbcTool();
		String sql = "SELECT vip_id, vip_name, vip_consume FROM vip";
		List<List<String>> list = jt.query(sql);
		int flag = 1;
		for(List<String> row : list) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(0, "" + flag);
			tableItem.setText(1, row.get(0));
			tableItem.setText(2, row.get(1));
			tableItem.setText(3, row.get(2));
			
		}
		
		
		Label label = new Label(container, SWT.BORDER);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 24, SWT.NORMAL));
		label.setBounds(0, 562, 260, 38);
		label.setText("\u603B\u8BA1\u6D88\u8D39\u91D1\u989D\uFF1A");
		
		
		Label lblNewLabel = new Label(container, SWT.BORDER);
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 24, SWT.NORMAL));
		lblNewLabel.setBounds(266, 562, 268, 38);
		sql = "SELECT SUM(vip_consume) FROM vip";
		jt.query(sql);
		list = jt.query(sql);
		lblNewLabel.setText(list.get(0).get(0));
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setText("\u5143");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 24, SWT.NORMAL));
		label_1.setBounds(540, 562, 46, 38);
		
		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.NORMAL));
		lblNewLabel_1.setBounds(0, 3, 105, 27);
		lblNewLabel_1.setText("\u6309\u6D88\u8D39\u91D1\u989D");
		
		Button btnRadioButton = new Button(container, SWT.RADIO);
		btnRadioButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				table.removeAll();
				
				JdbcTool jt = new JdbcTool();
				String sql = "SELECT vip_id, vip_name, vip_consume FROM vip ORDER BY vip_consume ASC";
				List<List<String>> list = jt.query(sql);
				int flag = 1;
				for(List<String> row : list) {
					TableItem tableItem = new TableItem(table, SWT.NONE);
					tableItem.setText(0, "" + flag);
					tableItem.setText(1, row.get(0));
					tableItem.setText(2, row.get(1));
					tableItem.setText(3, row.get(2));
					
				}
			}
		});
		btnRadioButton.setSelection(true);
		btnRadioButton.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		btnRadioButton.setBounds(108, 3, 63, 24);
		btnRadioButton.setText("\u5347\u5E8F");
		
		Button button = new Button(container, SWT.RADIO);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				table.removeAll();
				JdbcTool jt = new JdbcTool();
				String sql = "SELECT vip_id, vip_name, vip_consume FROM vip ORDER BY vip_consume DESC";
				List<List<String>> list = jt.query(sql);
				int flag = 1;
				for(List<String> row : list) {
					TableItem tableItem = new TableItem(table, SWT.NONE);
					tableItem.setText(0, "" + flag);
					tableItem.setText(1, row.get(0));
					tableItem.setText(2, row.get(1));
					tableItem.setText(3, row.get(2));
					
				}
			}
		});
		button.setText("\u964D\u5E8F");
		button.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		button.setBounds(171, 3, 63, 24);
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setText("\u6392\u5217");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.NORMAL));
		label_2.setBounds(240, 3, 105, 27);
		
		Button button_1 = new Button(container, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IWorkbenchPage page = getSite().getPage();
				page.hideView(page.findView(VipConsumeView.ID));
				try {
					page.showView(VipView.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
			}
		});
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button_1.setBounds(910, 5, 105, 27);
		button_1.setText("\u8FD4\u56DE\u4E0A\u7EA7\u83DC\u5355");
		

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
