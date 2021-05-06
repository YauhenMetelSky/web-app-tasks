package by.metelski.webtask.entity;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Map;

import by.metelski.webtask.command.ParameterAndAttribute;

public class ProcedureBuilder {
	private final static ProcedureBuilder instance = new ProcedureBuilder();
	private ProcedureBuilder() {
		
	}
	 public static ProcedureBuilder getInstance() {
		 return instance;
	 }
	 
	 public Procedure build(Map<String, String> procedureData) {
		 Procedure procedure = new Procedure();
		 procedure.setName(procedureData.get(ParameterAndAttribute.PROCEDURE_NAME));
		 procedure.setImageName(procedureData.get(ParameterAndAttribute.PROCEDURE_IMAGE));
		 procedure.setPrice(new BigDecimal(procedureData.get(ParameterAndAttribute.PROCEDURE_PRICE)));
		 procedure.setDescription(procedureData.get(ParameterAndAttribute.DESCRIPTION));
		 Duration duration = Duration.ofMinutes(Long.parseLong(procedureData.get(ParameterAndAttribute.DURATION)));
		 procedure.setDuration(duration);
		 return procedure;
	 }
}
