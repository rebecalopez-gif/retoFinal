package modelo;

import interfaces.NamedObject;

public class Objectos implements NamedObject{
	protected int cod_object;
	protected String objectName;
 
	public Objectos() {
		this.cod_object=0;
		this.objectName="";
	}
	public Objectos(int cod_object,String objectName) {
		this.cod_object=cod_object;
		this.objectName=objectName;
	}
	
	public Objectos(String objectName) {
		this.objectName=objectName;
	}

	public int getCod_object() {
		return cod_object;
	}

	public void setCod_object(int cod_object) {
		this.cod_object = cod_object;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	@Override
	public String toString() {
		return cod_object+" - " + objectName ; 
	}
	@Override
	public String getName() {
		return objectName;
	}
	
	

 
}
