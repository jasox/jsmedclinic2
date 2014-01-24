package pl.jasox.medward.services.loginservice;

public interface IAccountRepository {
	
	IAccount find(String accountId);
	
}
