package pl.jasox.medward.model.service.impl;

//--------------------------------------------------------------------
// Java
import java.util.List;

import pl.jasox.medward.model.dao.IKasachDao;
import pl.jasox.medward.model.dao.factory.ADaoFactory;
import pl.jasox.medward.model.domainobject.Kasach;
import pl.jasox.medward.model.domainobject.MedProcedureCat;
import pl.jasox.medward.model.service.ICountryService;
// Isrp

//--------------------------------------------------------------------

/** */
public class CountryServiceImpl implements ICountryService {

	@Override
	public List<MedProcedureCat> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
    /*
	@SuppressWarnings("unchecked")
	@Override
	public List<Kasach> findAll() {
		IKasachDao cDao = DaoFactory.getInstance().getKasachDao();
		return cDao.findAll();
	}
    */
}
//--------------------------------------------------------------------