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
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.TableItem;

import com.treenewbee.util.JdbcTool;

public class VipUpdateView extends ViewPart {

	public static final String ID = "com.shxt.supersystem.view.VipUpdateView"; //$NON-NLS-1$
	private Table table;
	private Table table_1;
	private Table table_2;
	
	public VipUpdateView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundImage(SWTResourceManager.getImage(VipUpdateView.class, "/com/shxt/supersystem/picture/\u84DD\u8272_\u526F\u672C.jpg"));
		container.setBackgroundMode(SWT.INHERIT_FORCE);
		{
			Composite composite = new Composite(container, SWT.BORDER);
			composite.setBounds(0, -1, 1015, 39);
			{
				Button button = new Button(composite, SWT.NONE);
				button.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						IWorkbenchPage page = getSite().getPage();
						page.hideView(page.findView(VipUpdateView.ID));
						try {
							page.showView(VipUpdateView.ID);
						} catch (PartInitException e1) {
							e1.printStackTrace();
						}
					}
				});
				button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
				button.setBounds(0, -1, 95, 39);
				button.setText("\u5237\u65B0");
			}
			{
				Button button = new Button(composite, SWT.NONE);
				button.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						IWorkbenchPage page = getSite().getPage();
						page.hideView(page.findView(VipUpdateView.ID));
						try {
							page.showView(VipView.ID);
						} catch (PartInitException e1) {
							e1.printStackTrace();
						}
					}
				});
				button.setText("\u8FD4\u56DE\u4E0A\u7EA7\u83DC\u5355");
				button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
				button.setBounds(889, -1, 124, 39);
			}
		}
		{
			Composite composite = new Composite(container, SWT.BORDER);
			composite.setBounds(0, 39, 1165, 317);
			{
				table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
				table.setLinesVisible(true);
				table.setHeaderVisible(true);
				table.setBounds(0, 0, 1014, 313);
				{
					TableColumn tableColumn = new TableColumn(table, SWT.NONE);
					tableColumn.setWidth(66);
					tableColumn.setText("\u884C\u53F7");
				}
				{
					TableColumn tableColumn = new TableColumn(table, SWT.NONE);
					tableColumn.setWidth(75);
					tableColumn.setText("\u4F1A\u5458\u5361\u53F7");
				}
				{
					TableColumn tableColumn = new TableColumn(table, SWT.NONE);
					tableColumn.setWidth(86);
					tableColumn.setText("\u4F1A\u5458\u59D3\u540D");
				}
				{
					TableColumn tableColumn = new TableColumn(table, SWT.NONE);
					tableColumn.setWidth(54);
					tableColumn.setText("\u6027\u522B");
				}
				{
					TableColumn tableColumn = new TableColumn(table, SWT.NONE);
					tableColumn.setWidth(53);
					tableColumn.setText("\u5E74\u9F84");
				}
				{
					TableColumn tableColumn = new TableColumn(table, SWT.NONE);
					tableColumn.setWidth(56);
					tableColumn.setText("\u4F4F\u5740");
				}
				{
					TableColumn tableColumn = new TableColumn(table, SWT.NONE);
					tableColumn.setWidth(123);
					tableColumn.setText("\u90AE\u7BB1");
				}
				{
					TableColumn tableColumn = new TableColumn(table, SWT.NONE);
					tableColumn.setWidth(124);
					tableColumn.setText("\u8054\u7CFB\u7535\u8BDD");
				}
				{
					TableColumn tableColumn = new TableColumn(table, SWT.NONE);
					tableColumn.setWidth(75);
					tableColumn.setText("\u6D88\u8D39\u91D1\u989D");
				}
				{
					TableColumn tableColumn = new TableColumn(table, SWT.NONE);
					tableColumn.setWidth(61);
					tableColumn.setText("\u4F1A\u5458\u7B49\u7EA7");
				}
				{
					TableColumn tableColumn = new TableColumn(table, SWT.NONE);
					tableColumn.setWidth(62);
					tableColumn.setText("\u4F18\u60E0\u6298\u6263");
				}
				{
					TableColumn tableColumn = new TableColumn(table, SWT.NONE);
					tableColumn.setMoveable(true);
					tableColumn.setWidth(165);
					tableColumn.setText("\u5907\u6CE8");
				}
				{
					JdbcTool jt = new JdbcTool();
					String sql;
					
					//会员消费升级表
					sql = "SELECT vip_consume, vip_level, vip_discount FROM privilege WHERE good_id = '' ORDER BY vip_consume";
					List<List<String>> levelList = jt.query(sql);
					
					//会员礼品表
					sql = "SELECT vip_consume, good_id, good_name FROM privilege WHERE good_id LIKE '_%'";
					List<List<String>> giftList = jt.query(sql);
					
					//会员礼品赠送表
					sql = "SELECT vip_id, good_id FROM gift";
					List<List<String>> giftOutList = jt.query(sql);
					
					//获得会员消费金额
					sql = "SELECT vip_id,vip_consume, vip_level FROM vip";
					List<List<String>> vipConsumeList = jt.query(sql);
					
					for(List<String> row : vipConsumeList) {
						float vipConsume = Float.parseFloat(row.get(1));
						for(int i = 0; i < (levelList.size()-1); i++) {
							if(vipConsume >= Float.parseFloat(levelList.get(i).get(0)) && vipConsume < Float.parseFloat(levelList.get(i+1).get(0))) {
								if(!row.get(2).equals(levelList.get(i).get(1))) {
									sql = "UPDATE vip SET vip_update = '会员需要升级' WHERE vip_id = '" + row.get(0) + "'";
									jt.update(sql);
								}
							}
						}
						if(vipConsume >= Float.parseFloat(levelList.get(levelList.size()-1).get(0))) {
							if(!row.get(2).equals(levelList.get(levelList.size()-1).get(1))) {
								sql = "UPDATE vip SET vip_update = '会员需要升级' WHERE vip_id = '" + row.get(0) + "'";
								jt.update(sql);
							}
						}
						
						
						for(int i = 0; i < (giftList.size() - 1); i++) {
							if(vipConsume >= Float.parseFloat(giftList.get(i).get(0)) && vipConsume < Float.parseFloat(giftList.get(i+1).get(0))) {
								int n = 0;
								for(int j = 0; j < giftOutList.size(); j++) {
									if(row.get(0).equals(giftOutList.get(j).get(0)) && giftList.get(i).get(1).equals(giftOutList.get(j).get(1))) {
										n = n + 1;
									}
								}
								if(n == 0) {
									sql = "UPDATE vip SET vip_gift = '向会员赠送礼品' WHERE vip_id = '" + row.get(0) + "'";
									jt.update(sql);
								}
							}
						}
						
						if(vipConsume >= Float.parseFloat(giftList.get(giftList.size()-1).get(0))) {
							int n = 0;
							for(int j = 0; j < giftOutList.size(); j++) {
								if(row.get(0).equals(giftOutList.get(j).get(0)) && giftList.get(giftList.size()-1).get(1).equals(giftOutList.get(j).get(1))) {
									n = n + 1;
								}
							}
							if(n == 0) {
								sql = "UPDATE vip SET vip_gift = '向会员赠送礼品' WHERE vip_id = '" + row.get(0) + "'";
								jt.update(sql);
							}
						}
						
					}
					
					int flag = 1;
					sql = "SELECT * FROM vip";
					List<List<String>> list = jt.query(sql);
					for(List<String> row : list) {
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
						tableItem.setText(9, row.get(8));
						tableItem.setText(10, row.get(9));
						if("".equals(row.get(11))) {
							tableItem.setText(11, row.get(12));
						}else if("".equals(row.get(12))) {
							tableItem.setText(11, row.get(11));
						}else {
							tableItem.setText(11, row.get(11) + "，" + row.get(12));
						}
						
						
						flag = flag + 1;
					}
					
				}
			}
		}
		
		TabFolder tabFolder = new TabFolder(container, SWT.NONE);
		tabFolder.setBounds(0, 362, 1013, 275);
		
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("\u4F1A\u5458\u5347\u7EA7");
		{
			Composite composite = new Composite(tabFolder, SWT.NONE);
			tabItem.setControl(composite);
			{
				table_1 = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
				table_1.setBounds(0, 0, 662, 294);
				table_1.setHeaderVisible(true);
				table_1.setLinesVisible(true);
				{
					TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
					tableColumn.setWidth(66);
					tableColumn.setText("\u884C\u53F7");
				}
				{
					TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
					tableColumn.setWidth(84);
					tableColumn.setText("\u4F1A\u5458\u5361\u53F7");
				}
				{
					TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
					tableColumn.setWidth(92);
					tableColumn.setText("\u4F1A\u5458\u59D3\u540D");
				}
				{
					TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
					tableColumn.setWidth(100);
					tableColumn.setText("\u6D88\u8D39\u91D1\u989D");
				}
				{
					TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
					tableColumn.setWidth(78);
					tableColumn.setText("\u4F1A\u5458\u7B49\u7EA7");
				}
				{
					TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
					tableColumn.setWidth(85);
					tableColumn.setText("\u4F18\u60E0\u6298\u6263");
				}
				{
					TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
					tableColumn.setWidth(139);
					tableColumn.setText("\u5907\u6CE8");
				}
				{
					JdbcTool jt = new JdbcTool();
					String sql;
					
					//会员消费升级表
					sql = "SELECT vip_consume, vip_level, vip_discount FROM privilege WHERE good_id = '' ORDER BY vip_consume";
					List<List<String>> levelList = jt.query(sql);
					
					
					//获得会员消费金额
					sql = "SELECT vip_id,vip_consume, vip_level FROM vip";
					List<List<String>> vipConsumeList = jt.query(sql);
					
					for(List<String> row : vipConsumeList) {
						float vipConsume = Float.parseFloat(row.get(1));
						for(int i = 0; i < (levelList.size()-1); i++) {
							if(vipConsume >= Float.parseFloat(levelList.get(i).get(0)) && vipConsume < Float.parseFloat(levelList.get(i+1).get(0))) {
								if(!row.get(2).equals(levelList.get(i).get(1))) {
									sql = "UPDATE vip SET vip_update = '会员需要升" + levelList.get(i).get(1) + "级' WHERE vip_id = '" + row.get(0) + "'";
									jt.update(sql);
								}
							}
						}
						if(vipConsume >= Float.parseFloat(levelList.get(levelList.size()-1).get(0))) {
							if(!row.get(2).equals(levelList.get(levelList.size()-1).get(1))) {
								sql = "UPDATE vip SET vip_update = '会员需要升" + levelList.get(levelList.size()-1).get(1) + "级' WHERE vip_id = '" + row.get(0) + "'";
								jt.update(sql);
							}
						}
					}
					
					int flag = 1;
					sql = "SELECT * FROM vip WHERE vip_update LIKE '_%'";
					List<List<String>> list = jt.query(sql);
					for(List<String> row : list) {
						TableItem tableItem = new TableItem(table_1, SWT.NONE);
						tableItem.setText(0, "" + flag);
						tableItem.setText(1, row.get(0));
						tableItem.setText(2, row.get(1));
						tableItem.setText(3, row.get(7));
						tableItem.setText(4, row.get(8));
						tableItem.setText(5, row.get(9));
						tableItem.setText(6, row.get(11));
						flag = flag + 1;
					}
				
					
				}
			}
				
			{
				Button button = new Button(composite, SWT.NONE);
				button.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						JdbcTool jt = new JdbcTool();
						String sql;
						
						//会员消费升级表
						sql = "SELECT vip_consume, vip_level, vip_discount FROM privilege WHERE good_id = '' ORDER BY vip_consume";
						List<List<String>> levelList = jt.query(sql);
						
						
						//获得会员消费金额
						sql = "SELECT vip_id,vip_consume, vip_level FROM vip";
						List<List<String>> vipConsumeList = jt.query(sql);
						
						for(List<String> row : vipConsumeList) {
							float vipConsume = Float.parseFloat(row.get(1));
							for(int i = 0; i < (levelList.size()-1); i++) {
								if(vipConsume >= Float.parseFloat(levelList.get(i).get(0)) && vipConsume < Float.parseFloat(levelList.get(i+1).get(0))) {
									if(!row.get(2).equals(levelList.get(i).get(1))) {
										sql = "UPDATE vip SET vip_level = '" + levelList.get(i).get(1) + "', vip_update = '' WHERE vip_id = '" + row.get(0) + "'";
										jt.update(sql);
									}
								}
							}
							if(vipConsume >= Float.parseFloat(levelList.get(levelList.size()-1).get(0))) {
								if(!row.get(2).equals(levelList.get(levelList.size()-1).get(1))) {
									sql = "UPDATE vip SET vip_level = '" + levelList.get(levelList.size()-1).get(1) + "', vip_update = '' WHERE vip_id = '" + row.get(0) + "'";
									jt.update(sql);
								}
							}
						}
						
						
						Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
						MessageBox box = new MessageBox(shell);
						box.setMessage("升级成功！");
						box.open();
						
					}
				});
				button.setFont(SWTResourceManager.getFont("微软雅黑", 33, SWT.BOLD));
				button.setBounds(668, 0, 340, 245);
				button.setText("\u4E00\u952E\u5347\u7EA7");
			}
		}
		
		TabItem tabItem_1 = new TabItem(tabFolder, SWT.NONE);
		tabItem_1.setText("\u4F1A\u5458\u8D60\u54C1");
		{
			Composite composite = new Composite(tabFolder, SWT.NONE);
			tabItem_1.setControl(composite);
			{
				table_2 = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
				table_2.setLinesVisible(true);
				table_2.setHeaderVisible(true);
				table_2.setBounds(0, 0, 671, 245);
				{
					TableColumn tableColumn = new TableColumn(table_2, SWT.NONE);
					tableColumn.setWidth(66);
					tableColumn.setText("\u884C\u53F7");
				}
				{
					TableColumn tableColumn = new TableColumn(table_2, SWT.NONE);
					tableColumn.setWidth(121);
					tableColumn.setText("\u4F1A\u5458\u5361\u53F7");
				}
				{
					TableColumn tableColumn = new TableColumn(table_2, SWT.NONE);
					tableColumn.setWidth(107);
					tableColumn.setText("\u4F1A\u5458\u59D3\u540D");
				}
				{
					TableColumn tableColumn = new TableColumn(table_2, SWT.NONE);
					tableColumn.setWidth(86);
					tableColumn.setText("\u6D88\u8D39\u91D1\u989D");
				}
				{
					TableColumn tableColumn = new TableColumn(table_2, SWT.NONE);
					tableColumn.setWidth(277);
					tableColumn.setText("\u5907\u6CE8");
				}
				{
					JdbcTool jt = new JdbcTool();
					String sql;
					
					//会员礼品表
					sql = "SELECT vip_consume, good_id, good_name FROM privilege WHERE good_id LIKE '_%'";
					List<List<String>> giftList = jt.query(sql);
					
					//会员礼品赠送表
					sql = "SELECT vip_id, good_id FROM gift";
					List<List<String>> giftOutList = jt.query(sql);
					
					
					//获得会员消费金额
					sql = "SELECT vip_id,vip_consume, vip_level FROM vip";
					List<List<String>> vipConsumeList = jt.query(sql);
					
					for(List<String> row : vipConsumeList) {
						float vipConsume = Float.parseFloat(row.get(1));
						for(int i = 0; i < (giftList.size() - 1); i++) {
							if(vipConsume >= Float.parseFloat(giftList.get(i).get(0)) && vipConsume < Float.parseFloat(giftList.get(i+1).get(0))) {
								int n = 0;
								for(int j = 0; j < giftOutList.size(); j++) {
									if(row.get(0).equals(giftOutList.get(j).get(0)) && giftList.get(i).get(1).equals(giftOutList.get(j).get(1))) {
										n = n + 1;
									}
								}
								if(n == 0) {
									sql = "UPDATE vip SET vip_gift = '向会员赠送礼品" + giftList.get(i).get(2) + "(编码：" + giftList.get(i).get(1) + ")' WHERE vip_id = '" + row.get(0) + "'";
									jt.update(sql);
								}
							}
						}
						
						
						if(vipConsume >= Float.parseFloat(giftList.get(giftList.size()-1).get(0))) {
							int n = 0;
							for(int j = 0; j < giftOutList.size(); j++) {
								if(row.get(0).equals(giftOutList.get(j).get(0)) && giftList.get(giftList.size()-1).get(1).equals(giftOutList.get(j).get(1))) {
									n = n + 1;
								}
							}
							if(n == 0) {
								sql = "UPDATE vip SET vip_gift = '向会员赠送礼品" + giftList.get(giftList.size()-1).get(2) + "(编码：" + giftList.get(giftList.size()-1).get(1) + ")' WHERE vip_id = '" + row.get(0) + "'";
								jt.update(sql);
							}
						}
					}
					
					int flag = 1;
					sql = "SELECT * FROM vip WHERE vip_gift LIKE '_%'";
					List<List<String>> list = jt.query(sql);
					for(List<String> row : list) {
						TableItem tableItem = new TableItem(table_2, SWT.NONE);
						tableItem.setText(0, "" + flag);
						tableItem.setText(1, row.get(0));
						tableItem.setText(2, row.get(1));
						tableItem.setText(3, row.get(7));
						tableItem.setText(4, row.get(12));
						flag = flag + 1;
					}
				}
			}
			{
				Button button = new Button(composite, SWT.NONE);
				button.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						JdbcTool jt = new JdbcTool();
						String sql;
						
						//会员礼品表
						sql = "SELECT vip_consume, good_id, good_name FROM privilege WHERE good_id LIKE '_%'";
						List<List<String>> giftList = jt.query(sql);
						
						//会员礼品赠送表
						sql = "SELECT vip_id, good_id FROM gift";
						List<List<String>> giftOutList = jt.query(sql);
						
						
						//获得会员消费金额
						sql = "SELECT vip_id,vip_consume, vip_level FROM vip";
						List<List<String>> vipConsumeList = jt.query(sql);
						
						
						
						for(List<String> row : vipConsumeList) {
							Date now = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
							String datetime = sdf.format(now);
							
							float vipConsume = Float.parseFloat(row.get(1));
							for(int i = 0; i < (giftList.size() - 1); i++) {
								if(vipConsume >= Float.parseFloat(giftList.get(i).get(0)) && vipConsume < Float.parseFloat(giftList.get(i+1).get(0))) {
									int n = 0;
									for(int j = 0; j < giftOutList.size(); j++) {
										if(row.get(0).equals(giftOutList.get(j).get(0)) && giftList.get(i).get(1).equals(giftOutList.get(j).get(1))) {
											n = n + 1;
										}
									}
									if(n == 0) {
										sql = "UPDATE vip SET vip_gift = '' WHERE vip_id = '" + row.get(0) + "'";
										jt.update(sql);
										sql = "INSERT INTO gift VALUES ('" + datetime + "', '" + row.get(0) + "', '" + giftList.get(i).get(1) + "')";
										jt.update(sql);
										
										sql = "SELECT storage_num, shelve_num, import_id FROM import_good WHERE good_id = '" + giftList.get(i).get(1) + "'AND storage_num > 0";
										List<List<String>> list = jt.query(sql);
										
										for(List<String> row1 : list) {
											Float storeNum = Float.parseFloat(row1.get(0));
											Float shelveNum = Float.parseFloat(row1.get(1));
											
											storeNum = storeNum - 1;
											sql = "UPDATE import_good SET shelve_num = " + shelveNum + " WHERE import_id = '" + row1.get(2) + "'";
											jt.update(sql);
											break;
										}
									}
								}
							}
							
							
							if(vipConsume >= Float.parseFloat(giftList.get(giftList.size()-1).get(0))) {
								
								int n = 0;
								for(int j = 0; j < giftOutList.size(); j++) {
									if(row.get(0).equals(giftOutList.get(j).get(0)) && giftList.get(giftList.size()-1).get(1).equals(giftOutList.get(j).get(1))) {
										n = n + 1;
									}
								}
								if(n == 0) {
									sql = "UPDATE vip SET vip_gift = '' WHERE vip_id = '" + row.get(0) + "'";
									jt.update(sql);
									sql = "INSERT INTO gift VALUES ('" + datetime + "', '" + row.get(0) + "', '" + giftList.get(giftList.size()-1).get(1) + "')";
									jt.update(sql);
								}
							}
						}
						
						Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
						MessageBox box = new MessageBox(shell);
						box.setMessage("发送成功！");
						box.open();
						
					}
				});
				button.setText("\u4E00\u952E\u53D1\u9001");
				button.setFont(SWTResourceManager.getFont("微软雅黑", 33, SWT.BOLD));
				button.setBounds(677, 0, 333, 245);
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
