package br.edu.ifpb.pweb.tsi.validator;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("dataValidador")
public class DataValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    	if (value == null) {
            return; 
        }
    	
    	UIInput dataRecebidaComponent =	(UIInput)component.getAttributes().get("dataRecebida");
    	
    	if (!dataRecebidaComponent.isValid()) {
            return;
        }
    	
    	Date today = new Date();
    	String data = (String) dataRecebidaComponent.getSubmittedValue();
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    	Date dataRecebida ;
		try {
			dataRecebida = (Date) formatter.parse(data);
			 if (dataRecebida.before(today)) { // A data tem que ser posterior a data do dia. O metodo after, significa anterior a data do dia. 
		        	dataRecebidaComponent.setValid(false);
		            throw new ValidatorException(new FacesMessage(
		                FacesMessage.SEVERITY_ERROR, "A data não pode ser posterior a data de hoje.", null));
		        }
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}