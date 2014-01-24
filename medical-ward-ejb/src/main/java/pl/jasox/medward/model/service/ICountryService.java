package pl.jasox.medward.model.service;

//--------------------------------------------------------------------
import java.util.List;

import pl.jasox.medward.model.domainobject.MedProcedureCat;

//--------------------------------------------------------------------

public interface ICountryService {
  
	List<MedProcedureCat> findAll();

}
//--------------------------------------------------------------------
