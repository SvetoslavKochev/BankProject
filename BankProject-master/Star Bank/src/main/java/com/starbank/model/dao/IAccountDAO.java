package com.starbank.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.starbank.exceptions.AccountException;
import com.starbank.exceptions.DateTimeException;
import com.starbank.exceptions.IbanException;
import com.starbank.exceptions.IdException;
import com.starbank.exceptions.InterestException;
import com.starbank.exceptions.InvalidEmailException;
import com.starbank.exceptions.InvalidStringException;
import com.starbank.exceptions.UserException;
import com.starbank.model.entity.Account;

@Component
public interface IAccountDAO {
	
	static final String SELECT_CURRENT_ACCOUNTS_SQL = "SELECT ca.account_id, a.net_avlb_balance, a.current_balance, a.iban, a.user_id, "
			+ "a.currency FROM e_banking.accounts a JOIN e_banking.current_accounts ca on (ca.account_id = a.account_id) WHERE a.user_id = ?;";
	final String SELECT_DEPOSIT_ACCOUNTS_SQL = "SELECT a.account_id, a.net_avlb_balance, a.current_balance, a.iban, a.user_id, "
			+ "a.currency FROM e_banking.accounts a JOIN e_banking.deposits da on (da.account_id = a.account_id)"
			+ " WHERE a.user_id = ?;";
	final String SELECT_CREDIT_ACCOUNTS_SQL = "SELECT a.account_id, a.net_avlb_balance, a.current_balance, a.iban, a.user_id, "
			+ "a.currency FROM e_banking.accounts a JOIN e_banking.credits ca on (ca.account_id = a.account_id)"
			+ " WHERE a.user_id = ?;";
	final String SELECT_USER_ACCOUNT_SQL = "SELECT * FROM Accounts WHERE account_id = ?";
	final String SELECT_USER_ACCOUNT_BY_IBAN_SQL = "SELECT * FROM Accounts WHERE iban = ?";
	final String SELECT_RECEIVING_ACCOUNT_SQL = "SELECT account_id FROM Accounts WHERE iban = ?";
	static final String UPDATE_RECIPIENT_ACCOUNT_ID = "UPDATE Accounts SET recipient_account_id = ? WHERE account_id = ?";
	static final String UPDATE_ACCOUNT_SQL = "UPDATE Accounts SET net_avlb_balance = ?, blocked_amount = ? WHERE account_id = ?";
	static final String SELECT_ACCOUNT_ID = "SELECT account_id FROM Accounts WHERE iban = ?";
	static final String SELECT_ACCOUNT_ID_BY_USER_ID = "SELECT account_id FROM Accounts WHERE user_id = ?";
	static final String INSERT_ACCOUNT_SQL = "INSERT INTO Accounts VALUES (null, ?, ?, ?, ?, ?, ?, ?)";

	public List<Account> showUserAccounts(int userId) throws UserException, AccountException, IbanException,
			InvalidStringException, IdException, DateTimeException, InterestException;

	public boolean transferMoneyToOtherAccount(String senderIban, double moneyToTransfer, String recipientIban)
			throws IbanException, AccountException;

	public boolean transferMoneyToMyAccount(String senderIban, String recipientIban, double moneyToTransfer)
			throws IbanException, AccountException;
	
	public void updateRecipientAccount(Account senderAccount, int recipientAccountId) throws SQLException;
	
	public void updateCurrentAccount(Account senderAccount, double moneyToTransfer, double availableBalance,
			double blockedAmount) throws SQLException;
	
	public int getAccountId(String iban);
	
	public boolean openAccount(String email, String type, double amount, String iban, int validation, String cardNumber) throws InvalidEmailException, InvalidStringException, AccountException;

}
