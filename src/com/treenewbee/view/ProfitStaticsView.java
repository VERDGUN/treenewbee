package com.treenewbee.view;

import java.util.List;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.treenewbee.util.JdbcTool;

import org.eclipse.swt.widgets.ProgressBar;

public class ProfitStaticsView extends ViewPart {

	public static final String ID = "com.shxt.supersystem.view.ProfitStaticsView"; //$NON-NLS-1$
	
	private Label lblCost;
	private Label lblIncome;
	private Label lblProfit;
	private DateTime dateTime;
	private Label lblYearIncome;
	private Label lblYearCost;
	private Label lblYearProfit;
	
	private ProgressBar[] progressBar = new ProgressBar[12];
	private ProgressBar[] progressBar_1 = new ProgressBar[12];
	private ProgressBar[] progressBar_2 = new ProgressBar[12];


	public ProfitStaticsView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundMode(SWT.INHERIT_FORCE);
		container.setBackgroundImage(SWTResourceManager.getImage(ProfitStaticsView.class, "/com/shxt/supersystem/picture/\u84DD\u8272_\u526F\u672C.jpg"));
		{
			Label label = new Label(container, SWT.CENTER);
			label.setText("\u5229\u6DA6\u65E5\u5386");
			label.setFont(SWTResourceManager.getFont("微软雅黑", 22, SWT.BOLD));
			label.setBounds(0, 0, 218, 42);
		}
		{
			dateTime = new DateTime(container, SWT.CALENDAR);
			dateTime.setBounds(0, 48, 218, 159);
		}
		{
			Button button = new Button(container, SWT.NONE);
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					int year = dateTime.getYear();
					int month = dateTime.getMonth() + 1;
					
					JdbcTool jt = new JdbcTool();
					String sql = "SELECT sale_money FROM sale WHERE sale_date BETWEEN '" + year + "-" + month + "-01 00:00:00' AND '" + year + "-" + month + "-31 23:59:59'";
					List<List<String>> list = jt.query(sql);
					
					Float income = 0f;
					if(list.size() != 0) {
						for(List<String> row : list) {
							income = income + Float.parseFloat(row.get(0));
						}
					}
					
					lblIncome.setText("" + income);
					
					sql = "SELECT SUM(g.good_cost * i.import_num) FROM import_good i JOIN good g ON g.good_id = i.good_id WHERE i.import_date BETWEEN '" + year + "-" + month + "-01 00:00:00' AND '" + year + "-" + month + "-31 23:59:59'";
					list = jt.query(sql);
					Float importMoney = 0f;
					if(list.get(0).get(0) != null) {
						importMoney = Float.parseFloat(list.get(0).get(0));
					}
					
					sql = "SELECT SUM(l.loss_num * g.good_cost) FROM loss l JOIN good g ON g.good_id = l.good_id WHERE l.loss_date BETWEEN '" + year + "-" + month + "-01 00:00:00' AND '" + year + "-" + month + "-31 23:59:59'";
					list = jt.query(sql);
					Float lossMoney = 0f;
					if(list.get(0).get(0) != null) {
						lossMoney = Float.parseFloat(list.get(0).get(0));
					}
					
					sql = "SELECT SUM(g.good_cost) FROM gift i JOIN good g ON g.good_id = i.good_id WHERE i.gift_date BETWEEN '" + year + "-" + month + "-01 00:00:00' AND '" + year + "-" + month + "-31 23:59:59'";
					list = jt.query(sql);
					Float giftMoney = 0f;
					if(list.get(0).get(0) != null) {
						giftMoney = Float.parseFloat(list.get(0).get(0));
					}
					
					Float cost = importMoney + lossMoney + giftMoney;
					lblCost.setText("" + cost);
					
					Float profit = income - cost;
					lblProfit.setText("" + profit);
					
					
					sql = "SELECT sale_money FROM sale WHERE sale_date BETWEEN '" + year + "-00-01 00:00:00' AND '" + year + "-12-31 23:59:59'";
					list = jt.query(sql);
					
					Float yearIncome = 0f;
					if(list.size() != 0) {
						for(List<String> row : list) {
							yearIncome = yearIncome + Float.parseFloat(row.get(0));
						}
					}
					
					lblYearIncome.setText("" + yearIncome);
					
					sql = "SELECT SUM(g.good_cost * i.import_num) FROM import_good i JOIN good g ON g.good_id = i.good_id WHERE i.import_date BETWEEN '" + year + "-00-01 00:00:00' AND '" + year + "-12-31 23:59:59'";
					list = jt.query(sql);
					Float importYearMoney = 0f;
					if(list.get(0).get(0) != null) {
						importYearMoney = Float.parseFloat(list.get(0).get(0));
					}
					
					sql = "SELECT SUM(l.loss_num * g.good_cost) FROM loss l JOIN good g ON g.good_id = l.good_id WHERE l.loss_date BETWEEN '" + year + "-00-01 00:00:00' AND '" + year + "-12-31 23:59:59'";
					list = jt.query(sql);
					Float lossYearMoney = 0f;
					if(list.get(0).get(0) != null) {
						lossYearMoney = Float.parseFloat(list.get(0).get(0));
					}
					
					sql = "SELECT SUM(g.good_cost) FROM gift i JOIN good g ON g.good_id = i.good_id WHERE i.gift_date BETWEEN '" + year + "-00-01 00:00:00' AND '" + year + "-12-31 23:59:59'";
					list = jt.query(sql);
					Float giftYearMoney = 0f;
					if(list.get(0).get(0) != null) {
						giftYearMoney = Float.parseFloat(list.get(0).get(0));
					}
					
					Float yearCost = importYearMoney + lossYearMoney + giftYearMoney;
					lblYearCost.setText("" + yearCost);
					
					Float yearProfit = yearIncome - yearCost;
					lblYearProfit.setText("" + yearProfit);
					
					
					Float monthIncome = 0f;
					Float monthImport = 0f;
					Float monthLoss = 0f;
					Float monthGift = 0f;
					int value = 0;
					for (int i = 1; i < 13; i++) {
						value = 0;
						monthIncome = 0f;
						monthImport = 0f;
						monthLoss = 0f;
						monthGift = 0f;
						
						sql = "SELECT sale_money FROM sale WHERE sale_date BETWEEN '" + year + "-" + i + "-01 00:00:00' AND '" + year + "-" + i + "-31 23:59:59'";
						list = jt.query(sql);
						
						
						if(list.size() != 0) {
							for(List<String> row : list) {
								monthIncome = monthIncome + Float.parseFloat(row.get(0));
							}
						}
						value = (int) (monthIncome / yearIncome * 100);
						progressBar[i-1].setSelection(value);
						
						sql = "SELECT SUM(g.good_cost * i.import_num) FROM import_good i JOIN good g ON g.good_id = i.good_id WHERE i.import_date BETWEEN '" + year + "-" + i + "-01 00:00:00' AND '" + year + "-" + i + "-31 23:59:59'";
						list = jt.query(sql);
						if(list.get(0).get(0) != null) {
							monthImport = Float.parseFloat(list.get(0).get(0));
						}
						
						sql = "SELECT SUM(l.loss_num * g.good_cost) FROM loss l JOIN good g ON g.good_id = l.good_id WHERE l.loss_date BETWEEN '" + year + "-" + i + "-01 00:00:00' AND '" + year + "-" + i + "-31 23:59:59'";
						list = jt.query(sql);
						if(list.get(0).get(0) != null) {
							monthLoss = Float.parseFloat(list.get(0).get(0));
						}
						
						sql = "SELECT SUM(g.good_cost) FROM gift i JOIN good g ON g.good_id = i.good_id WHERE i.gift_date BETWEEN '" + year + "-" + i + "-01 00:00:00' AND '" + year + "-" + i + "-31 23:59:59'";
						list = jt.query(sql);
						if(list.get(0).get(0) != null) {
							monthGift = Float.parseFloat(list.get(0).get(0));
						}
						
						value = 0;
						Float monthCost = monthImport + monthLoss + monthGift;
						value = (int)(monthCost / yearCost * 100);
						progressBar_1[i-1].setSelection(value);
						
						value = 0;
						Float monthProfit = monthIncome - monthCost;
						value = (int)(monthProfit / yearProfit * 100);
						if(monthProfit < 0) {
							progressBar_2[i-1].setState(SWT.ERROR);
						}
						progressBar_2[i-1].setSelection(value);
						
					}
					
				}
			});
			button.setText("\u5F53\u6708\u67E5\u8BE2");
			button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
			button.setBounds(52, 242, 90, 51);
		}
		{
			Button button = new Button(container, SWT.NONE);
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					int year = dateTime.getYear();
					int month = dateTime.getMonth() + 1;
					int day = dateTime.getDay();
					
					JdbcTool jt = new JdbcTool();
					String sql = "SELECT sale_money FROM sale WHERE sale_date BETWEEN '" + year + "-" + month + "-" + day + " 00:00:00' AND '" + year + "-" + month + "-" + day + " 23:59:59'";
					List<List<String>> list = jt.query(sql);
					
					Float income = 0f;
					if(list.size() != 0) {
						for(List<String> row : list) {
							income = income + Float.parseFloat(row.get(0));
						}
					}
					lblIncome.setText("" + income);
					
					sql = "SELECT SUM(g.good_cost * i.import_num) FROM import_good i JOIN good g ON g.good_id = i.good_id WHERE i.import_date BETWEEN '" + year + "-" + month + "-" + day + " 00:00:00' AND '" + year + "-" + month + "-" + day + " 23:59:59'";
					list = jt.query(sql);
					Float importMoney = 0f;
					
					if(list.get(0).get(0) != null) {
						importMoney = Float.parseFloat(list.get(0).get(0));
					}
					
					sql = "SELECT SUM(l.loss_num * g.good_cost) FROM loss l JOIN good g ON g.good_id = l.good_id WHERE l.loss_date BETWEEN '" + year + "-" + month + "-" + day + " 00:00:00' AND '" + year + "-" + month + "-" + day + " 23:59:59'";
					list = jt.query(sql);
					Float lossMoney = 0f;
					if(list.get(0).get(0) != null) {
						lossMoney = Float.parseFloat(list.get(0).get(0));
					}
					
					sql = "SELECT SUM(g.good_cost) FROM gift i JOIN good g ON g.good_id = i.good_id WHERE i.gift_date BETWEEN '" + year + "-" + month + "-" + day + " 00:00:00' AND '" + year + "-" + month + "-" + day + " 23:59:59'";
					list = jt.query(sql);
					Float giftMoney = 0f;
					if(list.get(0).get(0) != null) {
						giftMoney = Float.parseFloat(list.get(0).get(0));
					}
					
					Float cost = importMoney + lossMoney + giftMoney;
					lblCost.setText("" + cost);
					
					Float profit = income - cost;
					lblProfit.setText("" + profit);
				}
			});
			button.setText("\u5F53\u65E5\u67E5\u8BE2");
			button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
			button.setBounds(52, 318, 90, 51);
		}
		{
			Label label = new Label(container, SWT.CENTER);
			label.setText("\u8BE5\u5E74\u5EA6\u5229\u6DA6\u5206\u6790\u5206\u6790");
			label.setFont(SWTResourceManager.getFont("微软雅黑", 22, SWT.BOLD));
			label.setBounds(284, 0, 218, 42);
		}
		{
			Label label = new Label(container, SWT.NONE);
			label.setText("\u652F\u51FA\u603B\u989D\uFF1A");
			label.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
			label.setBounds(0, 390, 105, 28);
		}
		{
			Label label = new Label(container, SWT.NONE);
			label.setText("\u5165\u8D26\u603B\u989D\uFF1A");
			label.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
			label.setBounds(0, 475, 105, 28);
		}
		{
			Label label = new Label(container, SWT.NONE);
			label.setText("\u5229\u6DA6\u603B\u989D\uFF1A");
			label.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
			label.setBounds(0, 557, 105, 28);
		}
		{
			lblCost = new Label(container, SWT.BORDER);
			lblCost.setFont(SWTResourceManager.getFont("微软雅黑", 24, SWT.NORMAL));
			lblCost.setBounds(0, 424, 200, 42);
		}
		{
			lblIncome = new Label(container, SWT.BORDER);
			lblIncome.setFont(SWTResourceManager.getFont("微软雅黑", 24, SWT.NORMAL));
			lblIncome.setBounds(0, 509, 200, 42);
		}
		{
			lblProfit = new Label(container, SWT.BORDER);
			lblProfit.setFont(SWTResourceManager.getFont("微软雅黑", 24, SWT.NORMAL));
			lblProfit.setBounds(0, 591, 200, 42);
		}
		{
			Label label = new Label(container, SWT.NONE);
			label.setText("\u5143");
			label.setFont(SWTResourceManager.getFont("微软雅黑", 18, SWT.NORMAL));
			label.setBounds(206, 436, 46, 28);
		}
		{
			Label label = new Label(container, SWT.NONE);
			label.setText("\u5143");
			label.setFont(SWTResourceManager.getFont("微软雅黑", 18, SWT.NORMAL));
			label.setBounds(206, 523, 46, 28);
		}
		{
			Label label = new Label(container, SWT.NONE);
			label.setText("\u5143");
			label.setFont(SWTResourceManager.getFont("微软雅黑", 18, SWT.NORMAL));
			label.setBounds(206, 600, 46, 28);
		}
		
		Composite composite = new Composite(container, SWT.BORDER);
		composite.setBounds(294, 48, 719, 601);
		
		Label label = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(20, 164, 450, 2);
		
		Label label_1 = new Label(composite, SWT.SEPARATOR | SWT.VERTICAL);
		label_1.setBounds(53, 10, 2, 176);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_2.setBounds(63, 169, 14, 28);
		label_2.setText("1");
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setText("2");
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_3.setBounds(93, 169, 14, 28);
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setText("3");
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_4.setBounds(125, 169, 14, 28);
		
		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setText("4");
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_5.setBounds(156, 169, 14, 28);
		
		Label label_6 = new Label(composite, SWT.NONE);
		label_6.setText("5");
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_6.setBounds(186, 169, 14, 28);
		
		Label label_7 = new Label(composite, SWT.NONE);
		label_7.setText("6");
		label_7.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_7.setBounds(220, 169, 14, 28);
		
		Label label_8 = new Label(composite, SWT.NONE);
		label_8.setText("7");
		label_8.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_8.setBounds(256, 169, 14, 28);
		
		Label label_9 = new Label(composite, SWT.NONE);
		label_9.setText("8");
		label_9.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_9.setBounds(290, 169, 14, 28);
		
		Label label_10 = new Label(composite, SWT.NONE);
		label_10.setText("9");
		label_10.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_10.setBounds(325, 169, 25, 28);
		
		Label label_11 = new Label(composite, SWT.NONE);
		label_11.setText("10");
		label_11.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_11.setBounds(356, 169, 25, 28);
		
		Label label_12 = new Label(composite, SWT.NONE);
		label_12.setText("11");
		label_12.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_12.setBounds(395, 169, 23, 28);
		
		Label label_13 = new Label(composite, SWT.NONE);
		label_13.setText("12");
		label_13.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_13.setBounds(437, 169, 33, 28);
		
		progressBar[0] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar[0].setBounds(61, 10, 14, 156);
		
		progressBar[1] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar[1].setBounds(93, 10, 14, 156);
		
		progressBar[2] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar[2].setBounds(125, 10, 14, 156);
		
		progressBar[3] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar[3].setBounds(156, 10, 14, 156);
		
		progressBar[4] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar[4].setBounds(186, 10, 14, 156);
		
		progressBar[5] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar[5].setBounds(220, 10, 14, 156);
		
		progressBar[6] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar[6].setBounds(256, 10, 14, 156);
		
		progressBar[7] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar[7].setBounds(290, 10, 14, 156);
		
		progressBar[8] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar[8].setBounds(325, 10, 14, 156);
		
		progressBar[9] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar[9].setBounds(356, 10, 14, 156);
		
		progressBar[10] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar[10].setBounds(395, 10, 14, 156);
		
		progressBar[11] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar[11].setBounds(437, 10, 14, 156);
		
		Label label_14 = new Label(composite, SWT.NONE);
		label_14.setText("\u6708\u4EFD");
		label_14.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_14.setBounds(470, 150, 33, 28);
		
		Label label_15 = new Label(composite, SWT.NONE);
		label_15.setText("\u767E\u5206\u6BD4");
		label_15.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_15.setBounds(10, 0, 45, 28);
		
		Label label_16 = new Label(composite, SWT.NONE);
		label_16.setText("\u5E74\u5EA6\u6536\u5165\u603B\u989D\uFF1A");
		label_16.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
		label_16.setBounds(470, 48, 147, 28);
		
		lblYearIncome = new Label(composite, SWT.BORDER);
		lblYearIncome.setFont(SWTResourceManager.getFont("微软雅黑", 24, SWT.NORMAL));
		lblYearIncome.setBounds(505, 82, 200, 42);
		
		Label label_17 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_17.setBounds(20, 359, 450, 2);
		
		Label label_18 = new Label(composite, SWT.SEPARATOR);
		label_18.setBounds(53, 212, 2, 176);
		
		Label label_19 = new Label(composite, SWT.NONE);
		label_19.setText("\u767E\u5206\u6BD4");
		label_19.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_19.setBounds(10, 212, 45, 28);
		
		Label label_20 = new Label(composite, SWT.NONE);
		label_20.setText("\u6708\u4EFD");
		label_20.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_20.setBounds(470, 347, 33, 28);
		
		Label label_21 = new Label(composite, SWT.NONE);
		label_21.setText("1");
		label_21.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_21.setBounds(63, 360, 14, 28);
		
		Label label_22 = new Label(composite, SWT.NONE);
		label_22.setText("2");
		label_22.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_22.setBounds(93, 359, 14, 28);
		
		Label label_23 = new Label(composite, SWT.NONE);
		label_23.setText("3");
		label_23.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_23.setBounds(125, 359, 14, 28);
		
		Label label_24 = new Label(composite, SWT.NONE);
		label_24.setText("4");
		label_24.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_24.setBounds(156, 360, 14, 28);
		
		Label label_25 = new Label(composite, SWT.NONE);
		label_25.setText("5");
		label_25.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_25.setBounds(186, 359, 14, 28);
		
		Label label_26 = new Label(composite, SWT.NONE);
		label_26.setText("6");
		label_26.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_26.setBounds(220, 359, 14, 28);
		
		Label label_27 = new Label(composite, SWT.NONE);
		label_27.setText("7");
		label_27.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_27.setBounds(256, 359, 14, 28);
		
		Label label_28 = new Label(composite, SWT.NONE);
		label_28.setText("8");
		label_28.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_28.setBounds(290, 360, 14, 28);
		
		Label label_29 = new Label(composite, SWT.NONE);
		label_29.setText("9");
		label_29.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_29.setBounds(325, 359, 25, 28);
		
		Label label_30 = new Label(composite, SWT.NONE);
		label_30.setText("10");
		label_30.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_30.setBounds(356, 359, 25, 28);
		
		Label label_31 = new Label(composite, SWT.NONE);
		label_31.setText("11");
		label_31.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_31.setBounds(395, 359, 23, 28);
		
		Label label_32 = new Label(composite, SWT.NONE);
		label_32.setText("12");
		label_32.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_32.setBounds(437, 359, 33, 28);
		
		Label label_33 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_33.setBounds(20, 564, 450, 2);
		
		Label label_34 = new Label(composite, SWT.SEPARATOR);
		label_34.setBounds(53, 411, 2, 176);
		
		Label label_35 = new Label(composite, SWT.NONE);
		label_35.setText("\u767E\u5206\u6BD4");
		label_35.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_35.setBounds(10, 409, 45, 28);
		
		Label label_36 = new Label(composite, SWT.NONE);
		label_36.setText("\u6708\u4EFD");
		label_36.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_36.setBounds(470, 546, 33, 28);
		
		Label label_37 = new Label(composite, SWT.NONE);
		label_37.setText("1");
		label_37.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_37.setBounds(63, 564, 14, 28);
		
		Label label_38 = new Label(composite, SWT.NONE);
		label_38.setText("2");
		label_38.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_38.setBounds(93, 564, 14, 28);
		
		Label label_39 = new Label(composite, SWT.NONE);
		label_39.setText("3");
		label_39.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_39.setBounds(125, 564, 14, 28);
		
		Label label_40 = new Label(composite, SWT.NONE);
		label_40.setText("4");
		label_40.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_40.setBounds(156, 564, 14, 28);
		
		Label label_41 = new Label(composite, SWT.NONE);
		label_41.setText("5");
		label_41.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_41.setBounds(186, 564, 14, 28);
		
		Label label_42 = new Label(composite, SWT.NONE);
		label_42.setText("6");
		label_42.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_42.setBounds(220, 564, 14, 28);
		
		Label label_43 = new Label(composite, SWT.NONE);
		label_43.setText("7");
		label_43.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_43.setBounds(256, 564, 14, 28);
		
		Label label_44 = new Label(composite, SWT.NONE);
		label_44.setText("8");
		label_44.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_44.setBounds(290, 564, 14, 28);
		
		Label label_45 = new Label(composite, SWT.NONE);
		label_45.setText("9");
		label_45.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_45.setBounds(325, 564, 25, 28);
		
		Label label_46 = new Label(composite, SWT.NONE);
		label_46.setText("10");
		label_46.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_46.setBounds(356, 564, 25, 28);
		
		Label label_47 = new Label(composite, SWT.NONE);
		label_47.setText("11");
		label_47.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_47.setBounds(395, 564, 23, 28);
		
		Label label_48 = new Label(composite, SWT.NONE);
		label_48.setText("12");
		label_48.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_48.setBounds(437, 564, 33, 28);
		
		Label label_49 = new Label(composite, SWT.NONE);
		label_49.setText("\u5E74\u5EA6\u652F\u51FA\u603B\u989D\uFF1A");
		label_49.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
		label_49.setBounds(470, 274, 147, 28);
		
		lblYearCost = new Label(composite, SWT.BORDER);
		lblYearCost.setFont(SWTResourceManager.getFont("微软雅黑", 24, SWT.NORMAL));
		lblYearCost.setBounds(505, 308, 200, 42);
		
		Label label_51 = new Label(composite, SWT.NONE);
		label_51.setText("\u5E74\u5EA6\u5229\u6DA6\u603B\u989D\uFF1A");
		label_51.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
		label_51.setBounds(470, 460, 147, 28);
		
		lblYearProfit = new Label(composite, SWT.BORDER);
		lblYearProfit.setFont(SWTResourceManager.getFont("微软雅黑", 24, SWT.NORMAL));
		lblYearProfit.setBounds(505, 494, 200, 42);
		
		progressBar_1[0] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar_1[0].setBounds(61, 203, 14, 156);
		
		progressBar_1[1] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar_1[1].setBounds(93, 203, 14, 156);
		
		progressBar_1[2] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar_1[2].setBounds(125, 203, 14, 156);
		
		progressBar_1[3] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar_1[3].setBounds(156, 203, 14, 156);
		
		progressBar_1[4] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar_1[4].setBounds(186, 203, 14, 156);
		
		progressBar_1[5] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar_1[5].setBounds(220, 203, 14, 156);
		
		progressBar_1[6] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar_1[6].setBounds(256, 203, 14, 156);
		
		progressBar_1[7] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar_1[7].setBounds(290, 203, 14, 156);
		
		progressBar_1[8] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar_1[8].setBounds(325, 203, 14, 156);
		
		progressBar_1[9] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar_1[9].setBounds(356, 203, 14, 156);
		
		progressBar_1[10] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar_1[10].setBounds(395, 203, 14, 156);
		
		progressBar_1[11] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar_1[11].setBounds(437, 203, 14, 156);
		
		
		progressBar_2[0] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar_2[0].setBounds(61, 411, 14, 156);
		
		progressBar_2[1] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar_2[1].setBounds(93, 411, 14, 156);
		
		progressBar_2[2] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar_2[2].setBounds(125, 411, 14, 156);
		
		progressBar_2[3] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar_2[3].setBounds(156, 411, 14, 156);
		
		progressBar_2[4] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar_2[4].setBounds(186, 411, 14, 156);
		
		progressBar_2[5] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar_2[5].setBounds(220, 411, 14, 156);
		
		progressBar_2[6] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar_2[6].setBounds(256, 411, 14, 156);
		
		progressBar_2[7] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar_2[7].setBounds(290, 411, 14, 156);
		
		progressBar_2[8] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar_2[8].setBounds(325, 411, 14, 156);
		
		progressBar_2[9] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar_2[9].setBounds(356, 411, 14, 156);
		
		progressBar_2[10] = new ProgressBar(composite, SWT.VERTICAL);
		progressBar_2[10].setBounds(395, 411, 14, 156);
		
		progressBar_2[11] = new ProgressBar(composite, SWT.VERTICAL);
  		progressBar_2[11].setBounds(437, 411, 14, 156);
	
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
