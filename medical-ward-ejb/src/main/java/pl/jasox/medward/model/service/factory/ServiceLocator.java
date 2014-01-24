package pl.jasox.medward.model.service.factory;


import java.util.logging.Logger;
import pl.jasox.medward.model.dao.factory.ADaoFactory;
import pl.jasox.medward.model.dao.hibernate.factory.HibernateDaoFactory;

//------------------------------------------------------------------------------

/**
 * Implementacja interfejsu lokalizatora usług 
 */
public class ServiceLocator implements IServiceLocator {
	
	//private IPaperService    paperService;
	//private IUserService     userService;
	//private IReviewService   reviewService;
	//private ICountryService  countryService;
	//private IQuestionService questionService;

	private Logger logger = Logger.getLogger(this.getClass().getName());

	public ServiceLocator() {		
		// ustawienie DaoFactory
		// defaultowa implementacja - z Hibernate
		ADaoFactory.setInstance( HibernateDaoFactory.getInstance() );	
		this.logger.info("Dao factory bean is set to " + 
		                  ADaoFactory.getInstance().toString());		
		//this.paperService    = new PaperServiceImpl();
		//this.userService     = new UserServiceImpl();
		//this.reviewService   = new ReviewServiceImpl();
		//this.countryService  = new CountryServiceImpl();
		//this.questionService = new QuestionServiceImpl();
		this.logger.info("Default service locator bean is initialized");
	}
	
	public ServiceLocator(ADaoFactory daoFactory) {		
		// implementacja z dowolną DaoFactory
		ADaoFactory.setInstance( daoFactory );
		this.logger.info("Dao factory bean is set to " + 
		                  ADaoFactory.getInstance().toString());		
		//this.paperService    = new PaperServiceImpl();
		//this.userService     = new UserServiceImpl();
		//this.reviewService   = new ReviewServiceImpl();
		//this.countryService  = new CountryServiceImpl();
		//this.questionService = new QuestionServiceImpl();
		this.logger.info("Service locator bean is initialized");
	}
	
    /*
	@Override
	public IPaperService getPaperService() {
		return paperService;
	}

	@Override
	public IUserService getUserService() {
		return userService;
	}

	@Override
	public IReviewService getReviewService() {
		return reviewService;
	}

	@Override
	public ICountryService getCountryService() {
		return countryService;
	}

	@Override
	public IQuestionService getQuestionService() {
		return questionService;
	}
	*/
}
