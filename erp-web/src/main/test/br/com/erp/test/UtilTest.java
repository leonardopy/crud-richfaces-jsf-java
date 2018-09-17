package br.com.erp.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import br.com.erp.ejb.entidade.Usuario;
import br.com.erp.ejb.expection.DALException;
import br.com.erp.ejb.expection.ErpException;
import br.com.erp.ejb.util.Utils;

public class UtilTest {

	
	@Test
	public void testarconvertStringToMD5Retornare10adc3949ba59abbe56e057f20f883e() throws ErpException, DALException {
		Usuario usuario = new Usuario();
		usuario.setSenha("123456");
		Assert.assertEquals("e10adc3949ba59abbe56e057f20f883e", Utils.convertStringToMD5(usuario.getSenha()));
	}
	
	@Test
	public void testarToListDeveRetornareTrue()  {
		Set<Object> s = new HashSet<>();
		Assert.assertEquals(true, Utils.toList(s) instanceof List);
	}
	
	@Test
	public void testarValidarPeriodoDeveRetornarJavaxFacesConverterDateTimeConverterDATE()  {
		String periodoDe = "11022";
		String periodoAte = null;
		
		Assert.assertEquals("javax.faces.converter.DateTimeConverter.DATE", Utils.validarPeriodo(periodoDe , periodoAte ));
	}
}
