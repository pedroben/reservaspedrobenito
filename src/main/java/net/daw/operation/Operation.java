package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.helper.Contexto;


public abstract class Operation {
	public abstract Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
