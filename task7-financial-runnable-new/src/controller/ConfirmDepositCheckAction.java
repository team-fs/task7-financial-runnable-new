package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.CustomerBean;
import databeans.FundBean;
import databeans.PositionBean;
import databeans.PositionOfUser;
import databeans.TransactionBean;
import model.CustomerDAO;
import model.Model;
import model.PositionDAO;
import model.PriceDAO;
import model.TransactionDAO;
import model.FundDAO;
import formbeans.DepositCheckForm;
import formbeans.SellForm;

public class ConfirmDepositCheckAction extends Action {
	private FormBeanFactory<DepositCheckForm> formBeanFactory = FormBeanFactory.getInstance(DepositCheckForm.class);
	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;
	private FundDAO fundDAO;
	private PositionDAO positionDAO;
	private PriceDAO priceDAO;
	public ConfirmDepositCheckAction(Model model) {
		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
		positionDAO = model.getPositionDAO();
		priceDAO = model.getPriceDAO();
	}
	
	public String getName() {
		return "confirmDepositCheck.do";
	}
	
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		try {
			DepositCheckForm form  = formBeanFactory.create(request);
			request.setAttribute("form", form);
			TransactionBean transaction = new TransactionBean();
			transaction.setCustomer_id(form.getIdAsInt());
			transaction.setTransaction_type(3);
			transaction.setFund_id(-1);
			long amount = form.getAmountAsLong();
			request.setAttribute("customerList", customerDAO.getCustomer());
			if(amount <= 0) {
				errors.add("Amount should be a positive number");
				return "depositCheck.jsp";
			}
			transaction.setAmount(amount);
			transactionDAO.createDepChkTransaction(transaction);
			request.setAttribute("success", "SUCCESS");
		} catch(FormBeanException e) {
			errors.add(e.getMessage());
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "depositCheck.jsp";
		
	}
	
}