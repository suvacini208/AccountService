package com.suva.account.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "AccountTransaction")
public class AccountTransaction {
	
	@Transient
	public static final String DB_SEQ = "AccountTransaction";
	
	@Id
    private long id;
	private String accountNumber;
	private BigDecimal amount;
	private LocalDateTime transactionTs;
	public enum Type {
		WITHDRAW, DEPOSIT
	}
	private Type type;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public LocalDateTime getTransactionTs() {
		return transactionTs;
	}
	public void setTransactionTs(LocalDateTime transactionTs) {
		this.transactionTs = transactionTs;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "AccountTransaction [accountId=" + accountNumber + ", amount=" + amount + ", localDate=" + transactionTs
				+ ", type=" + type + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((transactionTs == null) ? 0 : transactionTs.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountTransaction other = (AccountTransaction) obj;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (transactionTs == null) {
			if (other.transactionTs != null)
				return false;
		} else if (!transactionTs.equals(other.transactionTs))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
}
