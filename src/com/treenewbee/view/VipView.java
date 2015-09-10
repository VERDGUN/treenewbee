package com.treenewbee.view;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Link;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TabFolder;

public class VipView extends ViewPart {

	public static final String ID = "com.shxt.supersystem.view.VipView"; //$NON-NLS-1$

	public VipView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundImage(SWTResourceManager.getImage(VipView.class, "/com/shxt/supersystem/picture/1374565556177_\u526F\u672C.jpg"));
		
		container.setBackgroundMode(SWT.INHERIT_FORCE);
		Link link = new Link(container, SWT.NONE);
		link.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IWorkbenchPage page = getSite().getPage();
				
				try {
					page.showView(VipInfoView.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
				page.hideView(page.findView(VipView.ID));
			}
		});
		link.setFont(SWTResourceManager.getFont("微软雅黑", 20, SWT.NORMAL));
		link.setBounds(319, 216, 142, 36);
		link.setText("<a>\u4F1A\u5458\u8D44\u6599</a>");
		
		Label label = new Label(container, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(376, 150, 2, 60);
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
		label_1.setBounds(155, 97, 85, 36);
		label_1.setText("\u4F1A\u5458\u7533\u8BF7");
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setText("\u4FEE\u6539\u8D44\u6599");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
		label_2.setBounds(335, 97, 85, 36);
		
		Label label_3 = new Label(container, SWT.NONE);
		label_3.setText("\u4F1A\u5458\u5220\u9664");
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
		label_3.setBounds(529, 97, 85, 36);
		
		Label label_4 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_4.setBounds(198, 150, 373, 2);
		
		Label label_5 = new Label(container, SWT.SEPARATOR | SWT.VERTICAL);
		label_5.setBounds(198, 124, 2, 25);
		
		Label label_6 = new Label(container, SWT.SEPARATOR);
		label_6.setBounds(376, 124, 2, 25);
		
		Label label_7 = new Label(container, SWT.SEPARATOR);
		label_7.setBounds(569, 124, 2, 25);
		
		Link link_1 = new Link(container, 0);
		link_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IWorkbenchPage page = getSite().getPage();
				try {
					page.showView(VipGiftView.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
				page.hideView(page.findView(VipView.ID));
			}
		});
		link_1.setText("<a>\u4F1A\u5458\u4F18\u60E0</a>");
		link_1.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
		link_1.setBounds(155, 322, 94, 36);
		
		Link link_2 = new Link(container, 0);
		link_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IWorkbenchPage page = getSite().getPage();
				try {
					page.showView(VipUpdateView.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
				page.hideView(page.findView(VipView.ID));
			}
		});
		link_2.setText("<a>\u4F1A\u5458\u7EF4\u62A4</a>");
		link_2.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
		link_2.setBounds(339, 322, 106, 36);
		
		Link link_3 = new Link(container, 0);
		link_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IWorkbenchPage page = getSite().getPage();
				try {
					page.showView(VipConsumeView.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
				page.hideView(page.findView(VipView.ID));
			}
		});
		link_3.setText("<a>\u4F1A\u5458\u6D88\u8D39\u67E5\u8BE2</a>");
		link_3.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
		link_3.setBounds(529, 322, 128, 36);
		
		Label label_8 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_8.setBounds(198, 291, 373, 2);
		
		Label label_9 = new Label(container, SWT.SEPARATOR);
		label_9.setBounds(376, 256, 2, 60);
		
		Label label_10 = new Label(container, SWT.SEPARATOR);
		label_10.setBounds(198, 291, 2, 25);
		
		Label label_11 = new Label(container, SWT.SEPARATOR);
		label_11.setBounds(569, 291, 2, 25);
		
		Label label_12 = new Label(container, SWT.NONE);
		label_12.setImage(SWTResourceManager.getImage(VipView.class, "/com/shxt/supersystem/picture/10_\u526F\u672C.png"));
		label_12.setBounds(263, 216, 50, 50);
		
		Label label_13 = new Label(container, SWT.NONE);
		label_13.setImage(SWTResourceManager.getImage(VipView.class, "/com/shxt/supersystem/picture/12_\u526F\u672C.png"));
		label_13.setBounds(99, 308, 50, 50);
		
		Label label_14 = new Label(container, SWT.NONE);
		label_14.setImage(SWTResourceManager.getImage(VipView.class, "/com/shxt/supersystem/picture/26_\u526F\u672C.png"));
		label_14.setBounds(283, 308, 50, 50);
		
		Label label_15 = new Label(container, SWT.NONE);
		label_15.setImage(SWTResourceManager.getImage(VipView.class, "/com/shxt/supersystem/picture/24 (1)_\u526F\u672C.png"));
		label_15.setBounds(478, 308, 50, 50);
		
		Label label_16 = new Label(container, SWT.NONE);
		label_16.setImage(SWTResourceManager.getImage(VipView.class, "/com/shxt/supersystem/picture/18_\u526F\u672C.png"));
		label_16.setBounds(176, 48, 50, 50);
		
		Label label_17 = new Label(container, SWT.NONE);
		label_17.setImage(SWTResourceManager.getImage(VipView.class, "/com/shxt/supersystem/picture/18_\u526F\u672C.png"));
		label_17.setBounds(359, 48, 50, 50);
		
		Label label_18 = new Label(container, SWT.NONE);
		label_18.setImage(SWTResourceManager.getImage(VipView.class, "/com/shxt/supersystem/picture/18_\u526F\u672C.png"));
		label_18.setBounds(551, 48, 50, 50);

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
