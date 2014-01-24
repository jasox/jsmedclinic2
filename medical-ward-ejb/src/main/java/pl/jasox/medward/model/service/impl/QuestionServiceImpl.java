package pl.jasox.medward.model.service.impl;

//--------------------------------------------------------------------
// Java
import java.util.List;

import pl.jasox.medward.model.dao.IMedProcedureDao;
import pl.jasox.medward.model.dao.factory.ADaoFactory;
import pl.jasox.medward.model.domainobject.MedProcedure;
import pl.jasox.medward.model.service.IQuestionService;

//--------------------------------------------------------------------

/** Implementacja obsługi zapytań dla recenzenta, dla oceny pracy */
public class QuestionServiceImpl implements IQuestionService {

	public List<MedProcedure> findAll() {
		IMedProcedureDao qDao = ADaoFactory.getInstance().getMedProcedureDao();
		return  qDao.getAll();
	}

}
//--------------------------------------------------------------------
