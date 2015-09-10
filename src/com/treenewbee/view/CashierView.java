package com.treenewbee.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchPage;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Label;

import com.treenewbee.dialog.CashierDialog;
import com.treenewbee.dialog.CashierReturnDialog;
import com.treenewbee.dialog.CheckImportDialog;
import com.treenewbee.dialog.CountDialog;
import com.treenewbee.dialog.GoodStoreDialog;
import com.treenewbee.dialog.VipInputDialog;
import com.treenewbee.shell.CashierShell;
import com.treenewbee.util.JdbcTool;

import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class CashierView extends ViewPart {

	public static final String ID = "com.treenewbee.view.CashierView"; //$NON-NLS-1$
	/**商品列表*/
	private Table table;
	/**商品编码*/
	private Text txtGoodID;
	/**付款*/
	private Text txtMoney;
	/**商品数量*/
	private Text txtGoodNum;
	private int flag = 1;
	private Label lblNewLabel_3;
	/**金额*/
	private Label lblRealMoney;
	/**总数量*/
	private Label lblAllNum;
	/**会员名称*/
	private Label lblVipName;
	/**会员消费金额*/
	private Label lblVipConsume;
	/**会员等级*/
	private Label lblVipLevel;
	/**会员折扣*/
	private Label lblVipDiscount;
	/**会员付款*/
	private Label lblVipMoney;
	/**流水号*/
	private Label lblWater;
	/**优惠价格*/
	private Label lblPriMoney;
	private ArrayList<ArrayList<String>> sale = new ArrayList<ArrayList<String>>();
	/**动态时间*/
	private Label lblTime;
	/**总金额*/
	private float sum = 0f;
	/**总量*/
	private float number = 0f;
	/**会员卡号*/
	private Text txtVipID;

	public CashierView() {
	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {

		Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundImage(SWTResourceManager.getImage(CashierView.class, "/com/shxt/supersystem/picture/\u84DD\u8272_\u526F\u672C.jpg"));
		container.setBackgroundMode(SWT.INHERIT_FORCE);
		container.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		{
			final Composite composite = new Composite(container, SWT.BORDER);
			composite.setBounds(0, 0, 1364, 35);
			{
				Label label = new Label(composite, SWT.NONE);
				label.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
				label.setBounds(10, 1, 114, 30);
				label.setText("收银员：" + CashierShell.id);
			}
			{
				Label label = new Label(composite, SWT.NONE);
				label.setFont(SWTResourceManager.getFont("微软雅黑", 17, SWT.NORMAL));
				label.setBounds(550, 1, 194, 30);
				label.setText("\u9EA6 \u515C \u515C \u8D85 \u5E02");
			}

			/**动态时间显示*/
			lblTime = new Label(composite, SWT.NONE);
			lblTime.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
			lblTime.setBounds(1156, 1, 194, 30);
			/**线程*/
			Thread thread = new Thread() {
				public void run() {
					while (true) {
						composite.getDisplay();
						Display.getDefault().syncExec(new Runnable() {
							public void run() {
								lblTime.setText(new Date().toLocaleString());
							}
						});
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			};
			thread.start();
		}
		{
			Composite composite = new Composite(container, SWT.BORDER);
			composite.setBounds(10, 41, 1354, 415);
			
			/**商品列表*/
			table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.H_SCROLL);
			table.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
			table.setBounds(0, 0, 1340, 411);
			table.setHeaderVisible(true);
			table.setLinesVisible(true);

			TableColumn tableColumn = new TableColumn(table, SWT.NONE);
			tableColumn.setWidth(136);
			tableColumn.setText("\u5E8F\u53F7");

			TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
			tableColumn_1.setWidth(178);
			tableColumn_1.setText("\u5546\u54C1\u540D\u79F0");

			TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
			tableColumn_2.setWidth(191);
			tableColumn_2.setText("\u5546\u54C1\u7F16\u7801");

			TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
			tableColumn_3.setWidth(177);
			tableColumn_3.setText("\u5355\u4EF7");

			TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
			tableColumn_4.setWidth(178);
			tableColumn_4.setText("\u6298\u6263\u4EF7");

			TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
			tableColumn_5.setWidth(188);
			tableColumn_5.setText("\u6570\u91CF");

			TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
			tableColumn_6.setWidth(283);
			tableColumn_6.setText("\u5C0F\u8BA1\u91D1\u989D");

			Menu menu = new Menu(table);
			table.setMenu(menu);
			
			/**撤销商品右击菜单*/
			MenuItem menuItem = new MenuItem(menu, SWT.NONE);
			menuItem.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					// 获取用户选取的哪一行
					int index = table.getSelectionIndex();
					// 获取行对象
					TableItem row = table.getItem(index);

					sale.remove(index);

					sum = sum - Float.parseFloat(row.getText(6));
					number = number - Float.parseFloat(row.getText(5));

					table.remove(index);

					lblRealMoney.setText("" + sum);
					lblVipMoney.setText("" + sum);
					lblAllNum.setText("" + number);

				}
			});
			menuItem.setText("\u64A4\u9500");

		}

		Composite composite = new Composite(container, SWT.BORDER);
		composite.setBounds(873, 472, 481, 35);

		Label label_11 = new Label(composite, SWT.BORDER);
		label_11.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		label_11.setBounds(0, 0, 154, 35);
		label_11.setText("\u8BF7\u8F93\u5165\u5546\u54C1\u6761\u7801\uFF1A");
		
		/**条形码录入商品*/
		txtGoodID = new Text(composite, SWT.BORDER);
		txtGoodID.setFocus();
		txtGoodID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				/**光标右移*/
				if (e.keyCode == 16777218) {
					txtGoodNum.setFocus();
					txtGoodNum.setText("");
				}
				/**退出*/
				if (e.keyCode == 27) {
					PlatformUI.getWorkbench().restart();
				}
				/**查看库存*/
				if (e.keyCode == 16777227) {
					Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
					CheckImportDialog checkImportDialog = new CheckImportDialog(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
					checkImportDialog.open();
				}
				/**退货*/
				if (e.keyCode == 16777226) {
					Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
					CashierReturnDialog cashierReturnDialog = new CashierReturnDialog(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
					cashierReturnDialog.open();
				}
				/**挂单*/
				if (e.keyCode == 16777228) {
					Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
					CashierDialog cashierDialog = new CashierDialog(shell,SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
					cashierDialog.open();
				}
				/**光标左移*/
				if (e.keyCode == 16777219) {
					txtVipID.setFocus();
					txtVipID.setText("");
				}
				/**商品录入*/
				if (e.keyCode == 13) {
					txtGoodNum.setText("1.00");
					lblNewLabel_3.setText("销售中....按←键输入会员卡号");
					JdbcTool jt = new JdbcTool();
					String sql = "SELECT good_name, good_id,good_price, good_disprice FROM good WHERE good_id = '" + txtGoodID.getText() + "'";
					
					/**列表显示*/
					List<List<String>> listGood = jt.query(sql);
					TableItem tableItem = new TableItem(table, SWT.NONE);
					tableItem.setText(0, "" + flag);
					tableItem.setText(1, listGood.get(0).get(0));
					tableItem.setText(2, listGood.get(0).get(1));
					tableItem.setText(3, listGood.get(0).get(2));
					tableItem.setText(4, listGood.get(0).get(3));
					tableItem.setText(5, txtGoodNum.getText());
					tableItem.setText(6, "" + Float.parseFloat(txtGoodNum.getText()) * Float.parseFloat(listGood.get(0).get(3)));

					Date now = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String time = sdf.format(now);
					
					/**将买的商品暂时存进salelist里*/
					ArrayList<String> saleList = new ArrayList<String>();
					saleList.add("" + CashierShell.id);
					saleList.add(time);
					saleList.add(txtGoodID.getText());
					saleList.add("");
					saleList.add(txtGoodNum.getText());
					saleList.add("" + Float.parseFloat(txtGoodNum.getText()) * Float.parseFloat(listGood.get(0).get(3)));
					saleList.add(lblWater.getText());
					sale.add(saleList);
					
					/**相关数据的变化*/
					number = number + Float.parseFloat(txtGoodNum.getText());
					sum = sum + Float.parseFloat(txtGoodNum.getText()) * Float.parseFloat(listGood.get(0).get(3));
					txtGoodID.setText("");
					flag = flag + 1;
					lblRealMoney.setText("" + sum);
					lblVipMoney.setText("" + sum);
					lblAllNum.setText("" + number);

				}
			}
		});
		txtGoodID.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		txtGoodID.setBounds(160, 0, 145, 35);

		Label label_20 = new Label(composite, SWT.NONE);
		label_20.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		label_20.setBounds(311, 0, 57, 35);
		label_20.setText("\u6570\u91CF\uFF1A");
		
		/**商品数量*/
		txtGoodNum = new Text(composite, SWT.BORDER);
		txtGoodNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				/**光标下移*/
				if (e.keyCode == 16777218) {
					txtMoney.setFocus();
				}
				/**左移*/
				if (e.keyCode == 16777217) {
					txtGoodID.setFocus();
				}
				/**退出*/
				if (e.keyCode == 27) {
					PlatformUI.getWorkbench().restart();
				}
				/**库存*/
				if (e.keyCode == 16777227) {
					Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
					CheckImportDialog checkImportDialog = new CheckImportDialog(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
					checkImportDialog.open();
				}
				/**退货*/
				if (e.keyCode == 16777226) {
					Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
					CashierReturnDialog cashierReturnDialog = new CashierReturnDialog(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
					cashierReturnDialog.open();
				}
				if (e.keyCode == 16777228) {
					Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
					CashierDialog cashierDialog = new CashierDialog(shell,SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
					cashierDialog.open();
				}
				/**商品录取，数量有变化*/
				if (e.keyCode == 13) {
					JdbcTool jt = new JdbcTool();
					String sql = "SELECT good_name, good_id,good_price, good_disprice FROM good WHERE good_id = '" + txtGoodID.getText() + "'";

					List<List<String>> listGood = jt.query(sql);
					TableItem tableItem = new TableItem(table, SWT.NONE);
					tableItem.setText(0, "" + flag);
					tableItem.setText(1, listGood.get(0).get(0));
					tableItem.setText(2, listGood.get(0).get(1));
					tableItem.setText(3, listGood.get(0).get(2));
					tableItem.setText(4, listGood.get(0).get(3));
					tableItem.setText(5, txtGoodNum.getText());
					tableItem.setText(6, "" + Float.parseFloat(txtGoodNum.getText()) * Float.parseFloat(listGood.get(0).get(3)));

					Date now = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					String time = sdf.format(now);

					ArrayList<String> saleList = new ArrayList<String>();

					saleList.add("" + CashierShell.id);
					saleList.add(time);
					saleList.add(txtGoodID.getText());
					saleList.add("");
					saleList.add(txtGoodNum.getText());
					saleList.add("" + Float.parseFloat(txtGoodNum.getText()) * Float.parseFloat(listGood.get(0).get(3)));
					saleList.add(lblWater.getText());
					sale.add(saleList);

					number = number + Float.parseFloat(txtGoodNum.getText());
					sum = sum + Float.parseFloat(txtGoodNum.getText()) * Float.parseFloat(listGood.get(0).get(3));

					txtGoodNum.setText("1.00");
					txtGoodID.setText("");
					flag = flag + 1;
					lblRealMoney.setText("" + sum);
					lblVipMoney.setText("" + sum);
					lblAllNum.setText("" + number);
					txtGoodID.setFocus();
				}
			}
		});
		txtGoodNum.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		txtGoodNum.setBounds(374, 0, 93, 31);

		Group group = new Group(container, SWT.NONE);
		group.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		group.setText("\u5DE5\u4F5C\u72B6\u6001");
		group.setBounds(10, 462, 216, 172);

		Label label = new Label(group, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label.setBounds(23, 65, 63, 19);
		label.setText("\u673A    \u53F7\uFF1A");

		Label label_1 = new Label(group, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_1.setBounds(92, 65, 44, 17);
		label_1.setText("01");

		Label label_2 = new Label(group, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_2.setBounds(23, 100, 61, 17);
		label_2.setText("\u6536\u94F6\u5458\uFF1A");

		Label lblNewLabel_1 = new Label(group, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager
				.getFont("微软雅黑", 10, SWT.NORMAL));
		lblNewLabel_1.setBounds(92, 100, 73, 17);
		lblNewLabel_1.setText(CashierShell.id);

		Label label_3 = new Label(group, SWT.NONE);
		label_3.setBounds(23, 135, 61, 17);
		label_3.setText("\u65E5    \u671F\uFF1A");

		Label label_6 = new Label(group, SWT.NONE);
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_6.setBounds(92, 135, 97, 17);
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowtime = sdf.format(now);
		label_6.setText(nowtime);

		Label label_9 = new Label(group, SWT.NONE);
		label_9.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_9.setBounds(23, 31, 48, 17);
		label_9.setText("\u6D41\u6C34\u53F7\uFF1A");

		lblWater = new Label(group, SWT.NONE);
		lblWater.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		lblWater.setBounds(77, 31, 117, 17);
		Date now1 = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		String nowtime1 = sdf1.format(now1);
		lblWater.setText(nowtime1);

		Composite composite_1 = new Composite(container, SWT.BORDER);
		composite_1.setBounds(232, 472, 635, 172);

		Label label_22 = new Label(composite_1, SWT.NONE);
		label_22.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_22.setBounds(10, 10, 80, 25);
		label_22.setText("\u4F1A\u5458\u4FE1\u606F\uFF1A");

		Label label_23 = new Label(composite_1, SWT.NONE);
		label_23.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_23.setBounds(30, 41, 65, 19);
		label_23.setText("\u4F1A\u5458\u5361\u53F7\uFF1A");

		Label label_24 = new Label(composite_1, SWT.NONE);
		label_24.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_24.setBounds(30, 70, 60, 19);
		label_24.setText("\u59D3      \u540D\uFF1A");

		Label label_25 = new Label(composite_1, SWT.NONE);
		label_25.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_25.setBounds(30, 102, 61, 25);
		label_25.setText("\u6D88\u8D39\u91D1\u989D\uFF1A");

		Label label_26 = new Label(composite_1, SWT.NONE);
		label_26.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_26.setBounds(30, 133, 61, 17);
		label_26.setText("\u4F1A\u5458\u7B49\u7EA7\uFF1A");

		lblVipName = new Label(composite_1, SWT.NONE);
		lblVipName.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		lblVipName.setBounds(111, 70, 102, 19);

		lblVipConsume = new Label(composite_1, SWT.NONE);
		lblVipConsume.setText("0");
		lblVipConsume.setFont(SWTResourceManager
				.getFont("微软雅黑", 10, SWT.NORMAL));
		lblVipConsume.setBounds(111, 102, 80, 19);

		lblVipLevel = new Label(composite_1, SWT.NONE);
		lblVipLevel.setText("0");
		lblVipLevel.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		lblVipLevel.setBounds(111, 131, 80, 19);

		Label label_30 = new Label(composite_1, SWT.NONE);
		label_30.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_30.setBounds(280, 10, 80, 25);
		label_30.setText("\u4F1A\u5458\u4F18\u60E0\uFF1A");

		lblVipDiscount = new Label(composite_1, SWT.NONE);
		lblVipDiscount.setFont(SWTResourceManager.getFont("微软雅黑", 12,SWT.NORMAL));
		lblVipDiscount.setBounds(366, 10, 88, 25);
		lblVipDiscount.setText("1.00");

		Label label_31 = new Label(composite_1, SWT.NONE);
		label_31.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_31.setBounds(197, 131, 32, 17);
		label_31.setText("\u7EA7");

		Label label_32 = new Label(composite_1, SWT.NONE);
		label_32.setText("\u5143");
		label_32.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_32.setBounds(197, 102, 32, 17);

		Label label_33 = new Label(composite_1, SWT.NONE);
		label_33.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_33.setBounds(460, 10, 40, 23);
		label_33.setText("\u6298");

		Label label_36 = new Label(composite_1, SWT.NONE);
		label_36.setText("\u4F1A\u5458\u4EF7\uFF1A");
		label_36.setFont(SWTResourceManager.getFont("微软雅黑", 20, SWT.BOLD));
		label_36.setBounds(280, 41, 102, 48);

		lblVipMoney = new Label(composite_1, SWT.NONE);
		lblVipMoney.setText("0.00");
		lblVipMoney.setFont(SWTResourceManager.getFont("微软雅黑", 50, SWT.BOLD));
		lblVipMoney.setBounds(370, 78, 184, 90);

		Label label_39 = new Label(composite_1, SWT.NONE);
		label_39.setText("\u5143\u4EBA\u6C11\u5E01");
		label_39.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.BOLD));
		label_39.setBounds(559, 152, 72, 16);

		/**根据会员卡号，得到会员信息*/
		txtVipID = new Text(composite_1, SWT.BORDER);
		txtVipID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == 16777218) {
					txtGoodID.setFocus();
				}
				if (e.keyCode == 13) {
					JdbcTool jt = new JdbcTool();
					String sql;
					if (!"0".equals(txtVipID.getText())) {
						sql = "SELECT vip_name, vip_consume, vip_level,vip_discount FROM vip WHERE vip_id = '" + txtVipID.getText() + "'";
						List<List<String>> list = jt.query(sql);
						lblVipName.setText(list.get(0).get(0));
						lblVipConsume.setText(list.get(0).get(1));
						lblVipLevel.setText(list.get(0).get(2));
						lblVipDiscount.setText(list.get(0).get(3));
						float dismoney = sum * Float.parseFloat(list.get(0).get(3));
						lblVipMoney.setText("" + dismoney);
						
						/**更新会员信息*/
						float vipUpdate = Float.parseFloat(list.get(0).get(1)) + dismoney;
						sql = "UPDATE vip SET vip_consume = " + vipUpdate + " WHERE vip_id = " + txtVipID.getText() + "";
						jt.update(sql);

						for (ArrayList<String> sl : sale) {
							sl.set(3, txtVipID.getText());
							sl.set(5,"" + Float.parseFloat(sl.get(5)) * Float.parseFloat(list.get(0).get(3)));
						}

						lblPriMoney.setText("" + (Float.parseFloat(lblRealMoney.getText()) - Float.parseFloat(lblVipMoney.getText())));
					}

					txtMoney.setFocus();
				}
			}
		});
		txtVipID.setText("0");
		txtVipID.setBounds(110, 40, 108, 23);

		Composite composite_2 = new Composite(container, SWT.BORDER);
		composite_2.setBounds(873, 516, 481, 128);

		Label label_12 = new Label(composite_2, SWT.NONE);
		label_12.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_12.setBounds(10, 0, 45, 20);
		label_12.setText("\u5408\u8BA1\uFF1A");

		Label label_13 = new Label(composite_2, SWT.NONE);
		label_13.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		label_13.setBounds(10, 38, 56, 29);
		label_13.setText("\u603B \u91CF\uFF1A");

		Label label_14 = new Label(composite_2, SWT.NONE);
		label_14.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		label_14.setBounds(10, 81, 56, 32);
		label_14.setText("\u4F18 \u60E0\uFF1A");

		lblAllNum = new Label(composite_2, SWT.NONE);
		lblAllNum.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		lblAllNum.setBounds(72, 38, 50, 29);
		lblAllNum.setText("0.00");

		lblPriMoney = new Label(composite_2, SWT.NONE);
		lblPriMoney.setText("0.00");
		lblPriMoney.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		lblPriMoney.setBounds(72, 81, 50, 29);

		Label label_18 = new Label(composite_2, SWT.NONE);
		label_18.setFont(SWTResourceManager.getFont("微软雅黑", 20, SWT.BOLD));
		label_18.setBounds(166, 10, 97, 45);
		label_18.setText("\u91D1  \u989D\uFF1A");

		lblRealMoney = new Label(composite_2, SWT.NONE);
		lblRealMoney.setFont(SWTResourceManager.getFont("微软雅黑", 50, SWT.BOLD));
		lblRealMoney.setBounds(269, 10, 198, 82);
		lblRealMoney.setText("0.00");

		Label label_41 = new Label(composite_2, SWT.NONE);
		label_41.setText("\u5143\u4EBA\u6C11\u5E01");
		label_41.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.BOLD));
		label_41.setBounds(407, 98, 60, 16);

		Label label_44 = new Label(composite_2, SWT.NONE);
		label_44.setText("\u5143");
		label_44.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		label_44.setBounds(127, 84, 45, 29);

		Composite composite_3 = new Composite(container, SWT.BORDER);
		composite_3.setBounds(873, 650, 481, 46);

		Label label_19 = new Label(composite_3, SWT.NONE);
		label_19.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.BOLD));
		label_19.setBounds(10, 10, 74, 32);
		label_19.setText("\u6536  \u6B3E\uFF1A");

		/**输入收款金额，打开找银界面*/
		txtMoney = new Text(composite_3, SWT.BORDER | SWT.RIGHT);
		txtMoney.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == 16777217) {
					txtGoodNum.setFocus();
				}
				if (e.keyCode == 27) {
					PlatformUI.getWorkbench().restart();
				}
				if (e.keyCode == 16777219) {
					txtVipID.setFocus();
					txtVipID.setText("");
				}
				if (e.keyCode == 16777227) {
					Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
					CheckImportDialog checkImportDialog = new CheckImportDialog(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
					checkImportDialog.open();
				}
				if (e.keyCode == 16777226) {
					Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
					CashierReturnDialog cashierReturnDialog = new CashierReturnDialog(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
					cashierReturnDialog.open();
				}
				if (e.keyCode == 16777228) {
					Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
					CashierDialog cashierDialog = new CashierDialog(shell,SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
					cashierDialog.open();
				}
				/**收款*/
				if (e.keyCode == 13) {
					lblNewLabel_3.setText("收款。。。");
					
					/**向售出表中添加记录*/
					JdbcTool jt = new JdbcTool();
					String sql;
					if (!"0".equals(txtVipID.getText())) {

						for (ArrayList<String> sl : sale) {
							sql = "INSERT INTO sale (cashier_id, sale_date, good_id, vip_id, sale_num, sale_money,sale_id) VALUES ('" + sl.get(0) + "', '" + sl.get(1) + "', '" + sl.get(2) + "', '" + sl.get(3) + "', '" + sl.get(4) + "', '" + sl.get(5) + "', '" + sl.get(6) + "')";
							jt.update(sql);
						}
					} else {
						for (ArrayList<String> sl : sale) {
							sql = "INSERT INTO sale (cashier_id, sale_date, good_id, sale_num, sale_money,sale_id) VALUES ('" + sl.get(0) + "', '" + sl.get(1) + "', '" + sl.get(2) + "', '" + sl.get(4) + "', '" + sl.get(5) + "', '" + sl.get(6) + "')";
							jt.update(sql);
						}
					}
					/**从货架上减去卖出商品数量*/
					for (ArrayList<String> s : sale) {
						Float goodNum = Float.parseFloat(s.get(4));

						sql = "SELECT shelve_num, import_id FROM import_good WHERE good_id = '" + s.get(2) + "'AND shelve_num > 0";
						List<List<String>> list = jt.query(sql);

						for (List<String> row : list) {
							Float shelveNum = Float.parseFloat(row.get(0));
							
							if (shelveNum >= goodNum) {
								shelveNum = shelveNum - goodNum;
								sql = "UPDATE import_good SET shelve_num = " + shelveNum + " WHERE import_id = '" + row.get(1) + "'";
								jt.update(sql);
								break;
							} else {
								goodNum = goodNum - shelveNum;
								shelveNum = 0f;
								sql = "UPDATE import_good SET shelve_num = "
										+ shelveNum + " WHERE import_id = '"
										+ row.get(1) + "'";
								jt.update(sql);
							}
						}
					}


					/**打开找零界面*/
					Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
					CountDialog countDialog = new CountDialog(shell,SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
					countDialog.open(txtMoney.getText(), lblVipMoney.getText(),sum);

					txtGoodID.setFocus();
					table.removeAll();

					/**交易完成，数据清空*/
					sum = 0f;
					number = 0f;
					lblRealMoney.setText("0.00");

					txtVipID.setText("0");
					lblVipName.setText("");
					lblVipConsume.setText("0");
					lblVipLevel.setText("0");
					lblVipDiscount.setText("1.0");
					lblVipMoney.setText("0.00");
					txtMoney.setText("");
					lblPriMoney.setText("0.00");
					lblNewLabel_3.setText("按↑、↓键选择文本框输入");
					sale.clear();
					lblAllNum.setText("0.00");
					flag = 1;

					Date now1 = new Date();
					SimpleDateFormat sdf1 = new SimpleDateFormat(
							"yyyyMMddHHmmss");
					String nowtime1 = sdf1.format(now1);
					lblWater.setText(nowtime1);
				}
			}
		});
		txtMoney.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.NORMAL));
		txtMoney.setBounds(90, 7, 315, 32);

		Label label_16 = new Label(composite_3, SWT.NONE);
		label_16.setText("\u5143\u4EBA\u6C11\u5E01");
		label_16.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.BOLD));
		label_16.setBounds(405, 22, 72, 16);

		Composite composite_4 = new Composite(container, SWT.BORDER);
		composite_4.setBounds(232, 649, 635, 45);

		Label label_10 = new Label(composite_4, SWT.NONE);
		label_10.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		label_10.setBounds(10, 8, 101, 23);
		label_10.setText("\u63D0\u793A\u4FE1\u606F\uFF1A");

		lblNewLabel_3 = new Label(composite_4, SWT.NONE);
		lblNewLabel_3.setFont(SWTResourceManager
				.getFont("微软雅黑", 13, SWT.NORMAL));
		lblNewLabel_3.setBounds(117, 8, 510, 23);
		lblNewLabel_3.setText("按↑、↓键选择文本框输入,←键输入会员卡号");

		Button btnf = new Button(container, SWT.NONE);
		btnf.setBounds(119, 640, 107, 29);
		btnf.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Shell shell = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getShell();
				CheckImportDialog checkImportDialog = new CheckImportDialog(
						shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
				checkImportDialog.open();

			}
		});
		btnf.setText("\u67E5\u770B\u5E93\u5B58(F2)");

		Button btnf_1 = new Button(container, SWT.NONE);
		btnf_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Shell shell = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getShell();
				CashierReturnDialog cashierReturnDialog = new CashierReturnDialog(
						shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
				cashierReturnDialog.open();
			}
		});
		btnf_1.setText("\u9000\u8D27(F1)");
		btnf_1.setBounds(10, 640, 107, 29);

		Button btnf_2 = new Button(container, SWT.NONE);
		btnf_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Shell shell = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getShell();
				CashierDialog cashierDialog = new CashierDialog(shell,
						SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
				cashierDialog.open();
			}
		});
		btnf_2.setText("\u6302\u5355(F3)");
		btnf_2.setBounds(10, 670, 107, 29);

		{
			Button btnNewButton = new Button(container, SWT.NONE);
			btnNewButton.setBounds(119, 670, 107, 29);
			btnNewButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					PlatformUI.getWorkbench().restart();
				}
			});
			btnNewButton.setText("\u9000\u51FA\u767B\u5F55(Esc)");
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
