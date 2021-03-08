package Aerei;

import java.util.*;
import java.util.stream.Collectors;

public class Aeroporto {
	
	private String code;
	
	private List<Volo> Arrivi = new LinkedList<Volo>();
	private List<Volo> Partenze = new LinkedList<Volo>();
	
	public Aeroporto(String code){
		this.code = code;
	}

	public String getCode(){
		return this.code;
	}
	
	public void addArrivo(Volo v) {
		this.Arrivi.add(v);
	}
	
	public void addPartenza(Volo v) {
		this.Partenze.add(v);
	}
	
	public void deleteArrivo(Volo v) {
		this.Arrivi.remove(v);
	}
	
	public void deletePartenza(Volo v) {
		this.Partenze.remove(v);
	}

	public List<Volo> getArrivi(){
		return this.Arrivi.stream().sorted((a, b) -> a.getFCode().compareTo(b.getFCode())).collect(Collectors.toList());
	}
	
	public List<Volo> getPartenze(){
		return this.Partenze.stream().sorted((a, b) -> a.getFCode().compareTo(b.getFCode())).collect(Collectors.toList());
	}
}
