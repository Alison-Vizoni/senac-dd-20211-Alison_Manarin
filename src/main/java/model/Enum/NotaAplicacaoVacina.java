package model.Enum;

public enum NotaAplicacaoVacina {
	PESSIMO
	, RUIM
	, REGULAR
	, BOM
	, OTIMO;
	
	public static NotaAplicacaoVacina getNotaAplicacaoVacina(String notaAplicacaoVacina){
        for(NotaAplicacaoVacina nota:NotaAplicacaoVacina.values()){
            if(nota.toString().equals(notaAplicacaoVacina.toUpperCase())){
                return nota;
            }
        }
        return null;
    }
}
