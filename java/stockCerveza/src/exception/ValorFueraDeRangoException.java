package exception;

public class ValorFueraDeRangoException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7010415991158573379L;
	private String atributo;
	public ValorFueraDeRangoException(String atributo){
		super();
		this.atributo = atributo;
	}
	
	@Override
	public String getMessage(){
		return "El atributo: "+ this.atributo + " tiene un valor fuera de rango";
		
	}
	
}
