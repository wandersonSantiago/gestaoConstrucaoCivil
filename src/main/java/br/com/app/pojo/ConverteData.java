package br.com.app.pojo;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public class ConverteData {

	
	private Date data;
	
	public ConverteData(Date data)
	{
		this.data  = data;
	}
	public String getString()
	{
		return converterParaString();
	}
	public LocalDate getLocalDate()
	{
		return converterParaLocalDate();
	}
	
	private String converterParaString(){
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(this.data);
    }
	private LocalDate converterParaLocalDate()
	{
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Instant instant = data.toInstant();
		
		return instant.atZone(defaultZoneId).toLocalDate();
	}
	
	
}
