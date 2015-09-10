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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class StorageManagerView extends ViewPart {

	public static final String ID = "com.shxt.supersystem.view.StorageManagerView"; //$NON-NLS-1$

	public StorageManagerView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundImage(SWTResourceManager.getImage(StorageManagerView.class, "/com/shxt/supersystem/picture/1374565556177_\u526F\u672C.jpg"));
		container.setBackgroundMode(SWT.INHERIT_FORCE);
		
		Link link = new Link(container, SWT.NONE);
		link.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IWorkbenchPage page = getSite().getPage();
				page.hideView(page.findView(StorageManagerView.ID));
				try {
					page.showView(StorageView.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
			}
		});
		link.setFont(SWTResourceManager.getFont("微软雅黑", 20, SWT.NORMAL));
		link.setBounds(182, 138, 150, 64);
		link.setText("<a>\u4ED3\u5E93\u7BA1\u7406</a>");
		
		Label label = new Label(container, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 18, SWT.NORMAL));
		label.setBounds(86, 320, 76, 37);
		label.setText("\u8FDB\u8D27");
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setText("\u9000\u8D27");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 18, SWT.NORMAL));
		label_1.setBounds(351, 320, 50, 37);
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setImage(SWTResourceManager.getImage(StorageManagerView.class, "/com/shxt/supersystem/picture/28_\u526F\u672C.png"));
		label_2.setBounds(200, 57, 112, 75);
		
		Label label_3 = new Label(container, SWT.NONE);
		label_3.setImage(SWTResourceManager.getImage(StorageManagerView.class, "/com/shxt/supersystem/picture/\u56FE\u72472_\u526F\u672C.png"));
		label_3.setBounds(231, 171, 131, 142);
		
		Label label_4 = new Label(container, SWT.NONE);
		label_4.setImage(SWTResourceManager.getImage(StorageManagerView.class, "/com/shxt/supersystem/picture/\u56FE\u72472_\u526F\u672C1.png"));
		label_4.setBounds(76, 171, 131, 142);

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
