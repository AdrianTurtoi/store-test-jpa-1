package com.msys.web.view;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.vaadin.ui.Upload;
import com.msys.entity.Article;
import com.msys.entity.CommonOrder;
import com.msys.entity.OrderItem;
import com.msys.entity.Supplier;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

public class ImportView extends CustomComponent implements View, Upload.Receiver {

	private static final long serialVersionUID = 1L;
	public static final String NAME = "import";
	private final Grid grid = new Grid();
	private final Grid grid1 = new Grid();

	final Upload upload = new Upload("Upload the file here", this);

	public ImportView(/* CommonOrderRepository commonOrderRepo */) {
		// this.commonOrderRepo = commonOrderRepo;

		VerticalLayout mainLayout = new VerticalLayout(upload, grid, grid1);
		mainLayout.setSizeFull();
		mainLayout.setComponentAlignment(upload, Alignment.TOP_CENTER);
		mainLayout.setComponentAlignment(grid, Alignment.MIDDLE_CENTER);
		mainLayout.setComponentAlignment(grid1, Alignment.MIDDLE_CENTER);

		CssLayout csLay = new CssLayout(mainLayout);
		csLay.setSizeFull();
		setCompositionRoot(csLay);
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}

	@Override
	public OutputStream receiveUpload(String filename, String mimeType) {
		PipedOutputStream ps = null;
		PipedInputStream is = null;

		String[] orderItems;
		String[] orderDetails;
		List<OrderItem> listOI = new ArrayList<>();
		List<CommonOrder> commonOrderList = new ArrayList<>();

		try {
			DataInputStream dis = new DataInputStream(
					new FileInputStream("D:\\Programe\\GitHub\\store-test-jpa-1\\" + filename));
			String input;

			ps = new PipedOutputStream();
			is = new PipedInputStream(ps);
			PrintStream os = new PrintStream(ps);

			while ((input = dis.readLine()) != null) {

				if (input.contains("oc;")) {
					orderDetails = input.substring(3, input.indexOf("[")).split(";");

					Matcher m = Pattern.compile("(?<=\\[)(.+?)(?=\\])").matcher(input);
					while (m.find()) {
						orderItems = m.group().split(";");
						listOI.add(new OrderItem(new Article(Integer.parseInt(orderItems[0])),
								Integer.parseInt(orderItems[1]), new Supplier(Integer.parseInt(orderItems[2]))));

					}

					SimpleDateFormat formatter = new SimpleDateFormat("dd.mm.yyyy");
					CommonOrder commonOrder = new CommonOrder(1L, formatter.parse(orderDetails[0]), listOI,
							formatter.parse(orderDetails[1]), formatter.parse(orderDetails[2]));

					commonOrderList.add(new CommonOrder(1L, formatter.parse(orderDetails[0]), listOI,
							formatter.parse(orderDetails[1]), formatter.parse(orderDetails[2])));

					// listOI.clear();

					grid.setHeight(300, Unit.PIXELS);
					grid.setWidth(70, Unit.PERCENTAGE);
					grid.setColumns("id", "deliveryDate", "validFrom", "validTo");
					grid.setContainerDataSource(new BeanItemContainer<CommonOrder>(CommonOrder.class, commonOrderList));

					grid.addSelectionListener(e -> {

						if (e.getSelected().isEmpty()) {
						} else {
							CommonOrder co = (CommonOrder) e.getSelected().iterator().next();
							final boolean persisted = co.getOrderItem() != null;
							if (persisted) {
								listOrderItems(co.getOrderItem());
							}
						}
					});

				} else if (input.contains("on;")) {

				} else if (input.contains("os;")) {

				} else if (input.contains("ol;")) {

				}
			}
			os.close();
		} catch (Exception e) {
			System.out.println("StringUtils reverse: " + e);
		}
		return ps;
	}

	private void listOrderItems(List<OrderItem> orderItemsList) {
		grid1.setHeight(30, Unit.PERCENTAGE);
		grid1.setWidth(100, Unit.PERCENTAGE);
		final BeanItemContainer<OrderItem> ds = new BeanItemContainer<OrderItem>(OrderItem.class, orderItemsList);
		ds.addNestedContainerBean("article");
		ds.addNestedContainerBean("supplier");
		grid1.setColumns("article.articleNo", "article.articleName", "quantity", "supplier.supplierNo",
				"supplier.supplierName");
		grid1.setContainerDataSource(ds);
		grid1.markAsDirty();
	}
}
