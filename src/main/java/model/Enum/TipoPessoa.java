package model.Enum;

public enum TipoPessoa {
	
	PESQUISADOR
	, PACIENTE;
	
	public static TipoPessoa getTipoPessoa(String tipoPessoa){
        for(TipoPessoa tipo:TipoPessoa.values()){
            if(tipo.toString().equals(tipoPessoa.toUpperCase())){
                return tipo;
            }
        }
        return null;
    }
}
