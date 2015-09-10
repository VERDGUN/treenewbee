package com.treenewbee.dialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.treenewbee.shell.CashierShell;
import com.treenewbee.util.JdbcTool;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class CashierDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Label lblWater;
	private Table table;
	private TableColumn tableColumn;
	private TableColumn tableColumn_1;
	private TableColumn tableColumn_2;
	private TableColumn tableColumn_3;
	private TableColumn tableColumn_4;
	private TableColumn tableColumn_5;
	private TableColumn tableColumn_6;
	private Text txtVipID;
	private Text txtGoodID;
	private Text txtGoodNum;
	private Text txtMoney;
	private Label lblVipName;
	private Label lblVipConsume;
	private Label lblVipLevel;
	private Label lblVipDiscount;
	private Label lblVipMoney;
	private Label lblAllNum;
	private Label lblPriMoney;
	private Label lblRealMoney;

	private int flag = 1;
	private ArrayList<ArrayList<String>> sale = new ArrayList<ArrayList<String>>();
	private float sum = 0f;
	private float number = 0f;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public CashierDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(787, 624);
		shell.setText("\u8D85\u5E02\u6536\u94F6");

		Label lblCashierID = new Label(shell, SWT.NONE);
		lblCashierID
				.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		lblCashierID.setBounds(0, -2, 114, 30);
		lblCashierID.setText("收银员：" + CashierShell.id);

		Label label = new Label(shell, SWT.NONE);
		label.setText("\u6D41\u6C34\u53F7\uFF1A");
		label.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		label.setBounds(144, 0, 68, 28);

		lblWater = new Label(shell, SWT.NONE);
		lblWater.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		lblWater.setBounds(223, 0, 176, 28);
		Date now1 = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		String nowtime1 = sdf1.format(now1);
		lblWater.setText(nowtime1);

		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(0, 34, 781, 269);

		tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(66);
		tableColumn.setText("\u5E8F\u53F7");

		tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(169);
		tableColumn_1.setText("\u5546\u54C1\u540D\u79F0");

		tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(180);
		tableColumn_2.setText("\u5546\u54C1\u7F16\u7801");

		tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(84);
		tableColumn_3.setText("\u5355\u4EF7");

		tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(77);
		tableColumn_4.setText("\u6298\u6263\u4EF7");

		tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(88);
		tableColumn_5.setText("\u6570\u91CF");

		tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(107);
		tableColumn_6.setText("\u5C0F\u8BA1\u91D1\u989D");

		Menu menu = new Menu(table);
		table.setMenu(menu);

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

		Composite composite = new Composite(shell, SWT.BORDER);
		composite.setBounds(0, 309, 355, 287);

		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText("\u4F1A\u5458\u4FE1\u606F\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_1.setBounds(10, 10, 80, 25);

		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setText("\u4F1A\u5458\u5361\u53F7\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_2.setBounds(30, 41, 65, 19);

		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setText("\u59D3      \u540D\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_3.setBounds(30, 70, 60, 19);

		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setText("\u6D88\u8D39\u91D1\u989D\uFF1A");
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_4.setBounds(30, 102, 61, 25);

		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setText("\u4F1A\u5458\u7B49\u7EA7\uFF1A");
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_5.setBounds(30, 133, 61, 17);

		lblVipName = new Label(composite, SWT.NONE);
		lblVipName.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		lblVipName.setBounds(111, 70, 102, 19);

		lblVipConsume = new Label(composite, SWT.NONE);
		lblVipConsume.setText("0");
		lblVipConsume.setFont(SWTResourceManager
				.getFont("微软雅黑", 10, SWT.NORMAL));
		lblVipConsume.setBounds(111, 102, 80, 19);

		lblVipLevel = new Label(composite, SWT.NONE);
		lblVipLevel.setText("0");
		lblVipLevel.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		lblVipLevel.setBounds(111, 131, 80, 19);

		Label label_9 = new Label(composite, SWT.NONE);
		label_9.setText("\u4F1A\u5458\u4F18\u60E0\uFF1A");
		label_9.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_9.setBounds(30, 164, 80, 25);

		lblVipDiscount = new Label(composite, SWT.NONE);
		lblVipDiscount.setText("1.00");
		lblVipDiscount.setFont(SWTResourceManager.getFont("微软雅黑", 12,
				SWT.NORMAL));
		lblVipDiscount.setBounds(111, 164, 60, 25);

		Label label_11 = new Label(composite, SWT.NONE);
		label_11.setText("\u7EA7");
		label_11.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_11.setBounds(197, 131, 32, 17);

		Label label_12 = new Label(composite, SWT.NONE);
		label_12.setText("\u5143");
		label_12.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_12.setBounds(197, 102, 32, 17);

		Label label_13 = new Label(composite, SWT.NONE);
		label_13.setText("\u6298");
		label_13.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_13.setBounds(197, 164, 40, 23);

		Label label_14 = new Label(composite, SWT.NONE);
		label_14.setText("\u4F1A\u5458\u4EF7\uFF1A");
		label_14.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.BOLD));
		label_14.setBounds(30, 195, 89, 34);

		lblVipMoney = new Label(composite, SWT.NONE);
		lblVipMoney.setText("0.00");
		lblVipMoney.setFont(SWTResourceManager.getFont("微软雅黑", 38, SWT.BOLD));
		lblVipMoney.setBounds(125, 217, 145, 56);

		Label label_16 = new Label(composite, SWT.NONE);
		label_16.setText("\u5143\u4EBA\u6C11\u5E01");
		label_16.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.BOLD));
		label_16.setBounds(276, 257, 65, 16);

		txtVipID = new Text(composite, SWT.BORDER);
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
						sql = "SELECT vip_name, vip_consume, vip_level,vip_discount FROM vip WHERE vip_id = '"
								+ txtVipID.getText() + "'";
						List<List<String>> list = jt.query(sql);
						lblVipName.setText(list.get(0).get(0));
						lblVipConsume.setText(list.get(0).get(1));
						lblVipLevel.setText(list.get(0).get(2));
						lblVipDiscount.setText(list.get(0).get(3));
						float dismoney = sum
								* Float.parseFloat(list.get(0).get(3));
						lblVipMoney.setText("" + dismoney);

						float vipUpdate = Float.parseFloat(list.get(0).get(1))
								+ dismoney;
						sql = "UPDATE vip SET vip_consume = " + vipUpdate
								+ " WHERE vip_id = " + txtVipID.getText() + "";
						jt.update(sql);

						for (ArrayList<String> sl : sale) {
							sl.set(3, txtVipID.getText());
							sl.set(5,
									""
											+ Float.parseFloat(sl.get(5))
											* Float.parseFloat(list.get(0).get(
													3)));
						}

						lblPriMoney.setText(""
								+ (Float.parseFloat(lblRealMoney.getText()) - Float
										.parseFloat(lblVipMoney.getText())));
					}

					txtMoney.setFocus();
				}
			}
		});
		txtVipID.setText("0");
		txtVipID.setBounds(110, 40, 108, 23);

		Label label_17 = new Label(shell, SWT.BORDER);
		label_17.setText("\u8BF7\u8F93\u5165\u5546\u54C1\u6761\u7801\uFF1A");
		label_17.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		label_17.setBounds(361, 474, 154, 35);

		txtGoodID = new Text(shell, SWT.BORDER);
		txtGoodID.setFocus();
		txtGoodID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == 16777218) {
					txtGoodNum.setFocus();
					txtGoodNum.setText("");
				}
				if (e.keyCode == 27) {
					shell.close();
				}
				if (e.keyCode == 16777227) {
					CheckImportDialog checkImportDialog = new CheckImportDialog(
							shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
					checkImportDialog.open();
				}

				if (e.keyCode == 16777219) {
					txtVipID.setFocus();
					txtVipID.setText("");
				}

				if (e.keyCode == 13) {
					txtGoodNum.setText("1.00");
					JdbcTool jt = new JdbcTool();
					String sql = "SELECT good_name, good_id,good_price, good_disprice FROM good WHERE good_id = '"
							+ txtGoodID.getText() + "'";

					List<List<String>> listGood = jt.query(sql);
					TableItem tableItem = new TableItem(table, SWT.NONE);
					tableItem.setText(0, "" + flag);
					tableItem.setText(1, listGood.get(0).get(0));
					tableItem.setText(2, listGood.get(0).get(1));
					tableItem.setText(3, listGood.get(0).get(2));
					tableItem.setText(4, listGood.get(0).get(3));
					tableItem.setText(5, txtGoodNum.getText());
					tableItem.setText(
							6,
							"" + Float.parseFloat(txtGoodNum.getText())
									* Float.parseFloat(listGood.get(0).get(3)));

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
					saleList.add("" + Float.parseFloat(txtGoodNum.getText())
							* Float.parseFloat(listGood.get(0).get(3)));
					saleList.add(lblWater.getText());
					sale.add(saleList);

					number = number + Float.parseFloat(txtGoodNum.getText());
					sum = sum + Float.parseFloat(txtGoodNum.getText())
							* Float.parseFloat(listGood.get(0).get(3));
					txtGoodID.setText("");
					flag = flag + 1;
					lblRealMoney.setText("" + sum);
					lblVipMoney.setText("" + sum);
					lblAllNum.setText("" + number);

				}
			}
		});
		txtGoodID.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		txtGoodID.setBounds(521, 472, 250, 35);

		Label label_18 = new Label(shell, SWT.NONE);
		label_18.setText("\u6570\u91CF\uFF1A");
		label_18.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		label_18.setBounds(362, 518, 57, 30);

		txtGoodNum = new Text(shell, SWT.BORDER | SWT.RIGHT);
		txtGoodNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == 16777218) {
					txtMoney.setFocus();
				}
				if (e.keyCode == 16777217) {
					txtGoodID.setFocus();
				}
				if (e.keyCode == 27) {
					shell.close();
				}
				if (e.keyCode == 16777227) {
					CheckImportDialog checkImportDialog = new CheckImportDialog(
							shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
					checkImportDialog.open();
				}
				if (e.keyCode == 13) {
					JdbcTool jt = new JdbcTool();
					String sql = "SELECT good_name, good_id,good_price, good_disprice FROM good WHERE good_id = '"
							+ txtGoodID.getText() + "'";

					List<List<String>> listGood = jt.query(sql);
					TableItem tableItem = new TableItem(table, SWT.NONE);
					tableItem.setText(0, "" + flag);
					tableItem.setText(1, listGood.get(0).get(0));
					tableItem.setText(2, listGood.get(0).get(1));
					tableItem.setText(3, listGood.get(0).get(2));
					tableItem.setText(4, listGood.get(0).get(3));
					tableItem.setText(5, txtGoodNum.getText());
					tableItem.setText(
							6,
							"" + Float.parseFloat(txtGoodNum.getText())
									* Float.parseFloat(listGood.get(0).get(3)));

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
					saleList.add("" + Float.parseFloat(txtGoodNum.getText())
							* Float.parseFloat(listGood.get(0).get(3)));
					saleList.add(lblWater.getText());
					sale.add(saleList);

					number = number + Float.parseFloat(txtGoodNum.getText());
					sum = sum + Float.parseFloat(txtGoodNum.getText())
							* Float.parseFloat(listGood.get(0).get(3));

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
		txtGoodNum.setBounds(436, 515, 335, 31);

		Label label_19 = new Label(shell, SWT.NONE);
		label_19.setText("\u6536  \u6B3E\uFF1A");
		label_19.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.BOLD));
		label_19.setBounds(361, 554, 74, 32);

		txtMoney = new Text(shell, SWT.BORDER | SWT.RIGHT);
		txtMoney.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == 16777217) {
					txtGoodNum.setFocus();
				}
				if (e.keyCode == 27) {
					shell.close();
				}
				if (e.keyCode == 16777219) {
					txtVipID.setFocus();
					txtVipID.setText("");
				}
				if (e.keyCode == 16777227) {
					CheckImportDialog checkImportDialog = new CheckImportDialog(
							shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
					checkImportDialog.open();
				}
				if (e.keyCode == 13) {

					JdbcTool jt = new JdbcTool();
					String sql;
					if (!"0".equals(txtVipID.getText())) {

						for (ArrayList<String> sl : sale) {
							sql = "INSERT INTO sale (cashier_id, sale_date, good_id, vip_id, sale_num, sale_money,sale_id) VALUES ('"
									+ sl.get(0)
									+ "', '"
									+ sl.get(1)
									+ "', '"
									+ sl.get(2)
									+ "', '"
									+ sl.get(3)
									+ "', '"
									+ sl.get(4)
									+ "', '"
									+ sl.get(5)
									+ "', '"
									+ sl.get(6) + "')";
							jt.update(sql);
						}
					} else {
						for (ArrayList<String> sl : sale) {
							sql = "INSERT INTO sale (cashier_id, sale_date, good_id, sale_num, sale_money,sale_id) VALUES ('"
									+ sl.get(0)
									+ "', '"
									+ sl.get(1)
									+ "', '"
									+ sl.get(2)
									+ "', '"
									+ sl.get(4)
									+ "', '"
									+ sl.get(5) + "', '" + sl.get(6) + "')";
							jt.update(sql);
						}
					}

					for (ArrayList<String> s : sale) {
						Float goodNum = Float.parseFloat(s.get(4));

						sql = "SELECT shelve_num, import_id FROM import_good WHERE good_id = '"
								+ s.get(2) + "'AND shelve_num > 0";
						List<List<String>> list = jt.query(sql);

						for (List<String> row : list) {
							Float shelveNum = Float.parseFloat(row.get(0));

							if (shelveNum >= goodNum) {
								shelveNum = shelveNum - goodNum;
								sql = "UPDATE import_good SET shelve_num = "
										+ shelveNum + " WHERE import_id = '"
										+ row.get(1) + "'";
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

					CountDialog countDialog = new CountDialog(shell,
							SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
					countDialog.open(txtMoney.getText(), lblVipMoney.getText(),
							sum);

					txtGoodID.setFocus();
					table.removeAll();

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
		txtMoney.setBounds(436, 551, 335, 32);

		Composite composite_1 = new Composite(shell, SWT.BORDER);
		composite_1.setBounds(361, 309, 420, 159);

		Label label_22 = new Label(composite_1, SWT.NONE);
		label_22.setText("\u603B \u91CF\uFF1A");
		label_22.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		label_22.setBounds(10, 10, 56, 29);

		Label label_23 = new Label(composite_1, SWT.NONE);
		label_23.setText("\u4F18 \u60E0\uFF1A");
		label_23.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		label_23.setBounds(194, 10, 56, 32);

		lblAllNum = new Label(composite_1, SWT.NONE);
		lblAllNum.setText("0.00");
		lblAllNum.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		lblAllNum.setBounds(72, 10, 50, 29);

		lblPriMoney = new Label(composite_1, SWT.NONE);
		lblPriMoney.setText("0.00");
		lblPriMoney.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		lblPriMoney.setBounds(256, 10, 50, 29);

		Label label_26 = new Label(composite_1, SWT.NONE);
		label_26.setText("\u91D1  \u989D\uFF1A");
		label_26.setFont(SWTResourceManager.getFont("微软雅黑", 20, SWT.BOLD));
		label_26.setBounds(10, 45, 97, 45);

		lblRealMoney = new Label(composite_1, SWT.NONE);
		lblRealMoney.setText("0.00");
		lblRealMoney.setFont(SWTResourceManager.getFont("微软雅黑", 50, SWT.BOLD));
		lblRealMoney.setBounds(132, 63, 198, 82);

		Label label_28 = new Label(composite_1, SWT.NONE);
		label_28.setText("\u5143\u4EBA\u6C11\u5E01");
		label_28.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.BOLD));
		label_28.setBounds(351, 119, 60, 16);

		Label label_29 = new Label(composite_1, SWT.NONE);
		label_29.setText("\u5143");
		label_29.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		label_29.setBounds(311, 10, 45, 29);

		Button btnesc = new Button(shell, SWT.NONE);
		btnesc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btnesc.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		btnesc.setBounds(694, 1, 87, 34);
		btnesc.setText("\u8FD4\u56DE(Esc)");

		Button btnf = new Button(shell, SWT.NONE);
		btnf.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CheckImportDialog checkImportDialog = new CheckImportDialog(
						shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
				checkImportDialog.open();
			}
		});
		btnf.setText("\u67E5\u770B\u5E93\u5B58(F2)");
		btnf.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		btnf.setBounds(614, 1, 81, 34);

	}
}
