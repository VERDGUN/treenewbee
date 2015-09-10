package com.treenewbee.view;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Link;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;

public class FinancialView extends ViewPart {

	public static final String ID = "com.shxt.supersystem.view.FinancialView"; //$NON-NLS-1$

	public FinancialView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundImage(SWTResourceManager.getImage(FinancialView.class, "/com/shxt/supersystem/picture/1374565556177_\u526F\u672C.jpg"));
		container.setBackgroundMode(SWT.INHERIT_FORCE);
		
		Link link = new Link(container, SWT.NONE);
		link.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IWorkbenchPage page = getSite().getPage();
				try {
					page.showView(ImportStatisticsView.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
			}
		});
		link.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
		link.setBounds(326, 82, 101, 35);
		link.setText("<a>\u8FDB\u8D27\u660E\u7EC6</a>");
		
		Link link_1 = new Link(container, 0);
		link_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IWorkbenchPage page = getSite().getPage();
				try {
					page.showView(IncomeStaticsView.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
			}
		});
		link_1.setText("<a>\u5165\u8D26\u7EDF\u8BA1</a>");
		link_1.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
		link_1.setBounds(326, 171, 101, 35);
		
		Link link_2 = new Link(container, 0);
		link_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IWorkbenchPage page = getSite().getPage();
				try {
					page.showView(ExpenseStaticsView.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
			}
		});
		link_2.setText("<a>\u652F\u51FA\u7EDF\u8BA1</a>");
		link_2.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
		link_2.setBounds(326, 261, 101, 35);
		
		Link link_3 = new Link(container, 0);
		link_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IWorkbenchPage page = getSite().getPage();
				try {
					page.showView(ProfitStaticsView.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
			}
		});
		link_3.setText("<a>\u5229\u6DA6\u7EDF\u8BA1</a>");
		link_3.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
		link_3.setBounds(326, 358, 101, 35);
		
		Link link_4 = new Link(container, 0);
		link_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IWorkbenchPage page = getSite().getPage();
				try {
					page.showView(LossView.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
			}
		});
		link_4.setText("<a>\u635F\u8017\u7EDF\u8BA1</a>");
		link_4.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
		link_4.setBounds(326, 448, 101, 35);
		
		Label label = new Label(container, SWT.NONE);
		label.setImage(SWTResourceManager.getImage(FinancialView.class, "/com/shxt/supersystem/picture/2_\u526F\u672C.png"));
		label.setBounds(242, 68, 60, 60);
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setImage(SWTResourceManager.getImage(FinancialView.class, "/com/shxt/supersystem/picture/2_\u526F\u672C.png"));
		label_1.setBounds(242, 157, 60, 60);
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setImage(SWTResourceManager.getImage(FinancialView.class, "/com/shxt/supersystem/picture/2_\u526F\u672C.png"));
		label_2.setBounds(242, 249, 60, 60);
		
		Label label_3 = new Label(container, SWT.NONE);
		label_3.setImage(SWTResourceManager.getImage(FinancialView.class, "/com/shxt/supersystem/picture/2_\u526F\u672C.png"));
		label_3.setBounds(242, 345, 60, 60);
		
		Label label_4 = new Label(container, SWT.NONE);
		label_4.setImage(SWTResourceManager.getImage(FinancialView.class, "/com/shxt/supersystem/picture/2_\u526F\u672C.png"));
		label_4.setBounds(242, 430, 60, 60);

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
