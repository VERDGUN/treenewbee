package com.treenewbee.view;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ShelveManagerView extends ViewPart {

	public static final String ID = "com.shxt.supersystem.view.ShelveManagerView"; //$NON-NLS-1$

	public ShelveManagerView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundImage(SWTResourceManager.getImage(ShelveManagerView.class, "/com/shxt/supersystem/picture/1374565556177_\u526F\u672C.jpg"));
		container.setBackgroundMode(SWT.INHERIT_FORCE);
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
		lblNewLabel.setBounds(116, 333, 88, 45);
		lblNewLabel.setText("\u4E0A\u67B6");
		
		Link link = new Link(container, SWT.NONE);
		link.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IWorkbenchPage page = getSite().getPage();
				page.hideView(page.findView(ShelveManagerView.ID));
				try {
					page.showView(ShelveView.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
			}
		});
		link.setFont(SWTResourceManager.getFont("微软雅黑", 19, SWT.NORMAL));
		link.setBounds(209, 150, 112, 45);
		link.setText("<a>\u4E0A\u67B6\u7BA1\u7406</a>");
		
		Label label = new Label(container, SWT.NONE);
		label.setText("\u4E0B\u67B6");
		label.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
		label.setBounds(344, 333, 88, 45);
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setImage(SWTResourceManager.getImage(ShelveManagerView.class, "/com/shxt/supersystem/picture/6_\u526F\u672C.png"));
		label_1.setBounds(222, 68, 82, 69);
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setImage(SWTResourceManager.getImage(ShelveManagerView.class, "/com/shxt/supersystem/picture/\u56FE\u72472_\u526F\u672C1.png"));
		label_2.setBounds(106, 176, 131, 151);
		
		Label label_3 = new Label(container, SWT.NONE);
		label_3.setImage(SWTResourceManager.getImage(ShelveManagerView.class, "/com/shxt/supersystem/picture/\u56FE\u72472_\u526F\u672C.png"));
		label_3.setBounds(255, 176, 131, 142);

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
