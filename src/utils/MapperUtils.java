package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapperUtils {
	public static List<String> obtemBigramasDeLinha(String linha) {
	    linha = linha.replaceAll("[^A-Za-z0-9'\"\\s]", "");
	    linha = linha.replaceAll("\\s{2,}", " ").trim();
	    List<String> lista = Arrays.asList(linha.split(" "));
	    List<String> listaBigramas = new ArrayList<>();
	
		for (int i = 0; i<lista.size();i++) {
			if (i==0) {
				listaBigramas.add("# "+ lista.get(i));
			} else if (i == lista.size()-1) {
				listaBigramas.add(lista.get(i-1) + " " + lista.get(i));
				listaBigramas.add(lista.get(i) + " #");
			} else {
				listaBigramas.add(lista.get(i-1) + " " + lista.get(i));
			}
		}
	  
		return listaBigramas;
	}
}
