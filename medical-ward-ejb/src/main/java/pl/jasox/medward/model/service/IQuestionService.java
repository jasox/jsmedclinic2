package pl.jasox.medward.model.service;

import java.util.List;

import pl.jasox.medward.model.domainobject.MedProcedure;

public interface IQuestionService {
	List<MedProcedure> findAll();
}
