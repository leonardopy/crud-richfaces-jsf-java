package br.com.erp.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.erp.web.util.Utils;

@FacesConverter("moedaConverter")
public class MoedaConverter implements Converter {

	/**
	 * Obtem o valor do controle da tela para o componente - retira mascara
	 */
	@Override
	public Object getAsObject(final FacesContext context, final UIComponent component, final String value) {
		
		if (value!=null && !value.equals("")){
			return Long.valueOf(Utils.retiraFormatacaoMoeda(value));
		}
		return null;
	}

	/**
	 * Obtem o valor do componente para o controle da tela - aplica mascara
	 */
	@Override
	public String getAsString(final FacesContext context, final UIComponent component, final Object value) {
		return String.valueOf(value);
	}

}
