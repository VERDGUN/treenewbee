package com.treenewbee.view;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.MouseEvent;

public class ManagerView extends ViewPart {

	public static final String ID = "com.shxt.supersystem.view.ManagerView"; //$NON-NLS-1$
	private Button btnStorage;
	private Button btnGoodInfo;
	private Button btnShelve;
	private Button btnVIP;
	private Button btnMoney;
	private Button btnWorker;
	private Button btnReturn;

	public ManagerView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundMode(SWT.INHERIT_FORCE);
		container.setToolTipText("\u5E2E\u52A9");
		container.setBackgroundImage(SWTResourceManager.getImage(ManagerView.class, "/com/shxt/supersystem/picture/\u84DD\u8272_\u526F\u672C.jpg"));
		container.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		container.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		container.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		btnGoodInfo = new Button(container, SWT.NONE);
		btnGoodInfo.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseExit(MouseEvent e) {
				btnGoodInfo.setBounds(63, 22, 226, 50);
			}
			@Override
			public void mouseEnter(MouseEvent e) {
				btnGoodInfo.setBounds(63, 10, 252, 73);
			}
		});
		btnGoodInfo.setImage(SWTResourceManager.getImage(ManagerView.class, "/com/shxt/supersystem/picture/4 (1)_\u526F\u672C.png"));
		btnGoodInfo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnGoodInfo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IWorkbenchPage page = getSite().getPage();
				try {
					page.showView(BaseView.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
				page.hideView(page.findView(WelcomeView.ID));
			}
		});
		btnGoodInfo.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.BOLD));
		btnGoodInfo.setBounds(63, 22, 226, 50);
		btnGoodInfo.setText("  \u57FA\u7840\u8BBE\u65BD");
		
		btnStorage = new Button(container, SWT.NONE);
		btnStorage.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent e) {
				btnStorage.setBounds(0, 98, 251, 70);
			}
			@Override
			public void mouseExit(MouseEvent e) {
				btnStorage.setBounds(25, 108, 226, 50);
			}
		});
		btnStorage.setImage(SWTResourceManager.getImage(ManagerView.class, "/com/shxt/supersystem/picture/dsga_ (4)_\u526F\u672C.png"));
		btnStorage.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IWorkbenchPage page = getSite().getPage();
				try {
					page.showView(StorageManagerView.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
				page.hideView(page.findView(WelcomeView.ID));
			}
		});
		btnStorage.setText("   \u4ED3\u5E93\u7BA1\u7406");
		btnStorage.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.BOLD));
		btnStorage.setBounds(25, 108, 226, 50);
		
		btnShelve = new Button(container, SWT.NONE);
		btnShelve.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseExit(MouseEvent e) {
				btnShelve.setBounds(73, 190, 211, 50);
			}
			@Override
			public void mouseEnter(MouseEvent e) {
				btnShelve.setBounds(63, 174, 239, 82);
			}
		});
		btnShelve.setImage(SWTResourceManager.getImage(ManagerView.class, "/com/shxt/supersystem/picture/shopping_cart_\u526F\u672C.png"));
		btnShelve.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IWorkbenchPage page = getSite().getPage();
				try {
					page.showView(ShelveManagerView.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
				page.hideView(page.findView(WelcomeView.ID));
			}
		});
		btnShelve.setText("  \u4E0A\u67B6\u7BA1\u7406");
		btnShelve.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.BOLD));
		btnShelve.setBounds(73, 190, 211, 50);
		
		btnVIP = new Button(container, SWT.NONE);
		btnVIP.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseExit(MouseEvent e) {
				btnVIP.setBounds(25, 285, 211, 50);
			}
			@Override
			public void mouseEnter(MouseEvent e) {
				btnVIP.setBounds(0, 278, 260, 68);
			}
		});
		btnVIP.setImage(SWTResourceManager.getImage(ManagerView.class, "/com/shxt/supersystem/picture/4_\u526F\u672C.png"));
		btnVIP.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IWorkbenchPage page = getSite().getPage();
				try {
					page.showView(VipView.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
				page.hideView(page.findView(WelcomeView.ID));
			}
		});
		btnVIP.setText("   VIP\u7BA1\u7406");
		btnVIP.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.BOLD));
		btnVIP.setBounds(25, 285, 211, 50);
		
		btnMoney = new Button(container, SWT.NONE);
		btnMoney.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent e) {
				btnMoney.setBounds(42, 373, 273, 74);
			}
			@Override
			public void mouseExit(MouseEvent e) {
				btnMoney.setBounds(63, 380, 226, 50);
			}
		});
		btnMoney.setImage(SWTResourceManager.getImage(ManagerView.class, "/com/shxt/supersystem/picture/chart_\u526F\u672C.png"));
		btnMoney.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IWorkbenchPage page = getSite().getPage();
				try {
					page.showView(FinancialView.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
				page.hideView(page.findView(WelcomeView.ID));
			}
		});
		btnMoney.setText("  \u8D22\u52A1\u7EDF\u8BA1");
		btnMoney.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.BOLD));
		btnMoney.setBounds(63, 380, 226, 50);
		
		btnWorker = new Button(container, SWT.NONE);
		btnWorker.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent e) {
				btnWorker.setBounds(10, 465, 241, 72);
			}
			@Override
			public void mouseExit(MouseEvent e) {
				btnWorker.setBounds(25, 475, 226, 50);
			}
		});
		btnWorker.setImage(SWTResourceManager.getImage(ManagerView.class, "/com/shxt/supersystem/picture/chinaz_PNG08_\u526F\u672C.png"));
		btnWorker.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IWorkbenchPage page = getSite().getPage();
				try {
					page.showView(WorkerView.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
				page.hideView(page.findView(WelcomeView.ID));
				
			}
		});
		btnWorker.setText("\u5458\u5DE5\u7BA1\u7406");
		btnWorker.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.BOLD));
		btnWorker.setBounds(25, 475, 226, 50);
		
		btnReturn = new Button(container, SWT.RIGHT);
		btnReturn.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent e) {
				btnReturn.setBounds(73, 551, 242, 70);
			}
			@Override
			public void mouseExit(MouseEvent e) {
				btnReturn.setBounds(73, 561, 211, 50);
			}
		});
		btnReturn.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnReturn.setImage(SWTResourceManager.getImage(ManagerView.class, "/com/shxt/supersystem/picture/1_\u526F\u672C.png"));
		btnReturn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PlatformUI.getWorkbench().restart();
			}
		});
		btnReturn.setText("  \u9000     \u51FA");
		btnReturn.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.BOLD));
		btnReturn.setBounds(73, 561, 211, 50);
		

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
