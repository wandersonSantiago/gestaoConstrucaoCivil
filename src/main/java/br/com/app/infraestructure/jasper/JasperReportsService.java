package br.com.app.infraestructure.jasper;

import java.io.ByteArrayOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.app.infraestructure.exception.NotFoundException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

@Service
public class JasperReportsService {
	
	
	public byte[] generateReport(List<?> lista, String arquivoJasper,HashMap<String, Object> hashMap) throws JRException {
		if (lista == null || lista.isEmpty()) {
			throw new NotFoundException("Relátorio não pode ser vazio");
		}

			
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream(arquivoJasper));
		JRDataSource datasource = new JRBeanCollectionDataSource(lista, true);
		
		jasperPrint = JasperFillManager.fillReport(jasperReport, hashMap, datasource);

		JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
		
		return baos.toByteArray();
	}
	
	public void compileReport(String jasperFile, Map<String, Object> params, Collection<?> collection){
		
		try {
			
			JasperReport report = JasperCompileManager.compileReport(jasperFile);
			
			JasperPrint print = JasperFillManager.fillReport(report, params, new JRBeanCollectionDataSource(collection));
			
			JasperViewer viewer = new JasperViewer(print);
			viewer.setVisible(true);
			
		} catch (JRException e) {		
			e.printStackTrace();
		}		
		
		
	}
	public void openReportByHTML(String jasperFile, String OutFile, Map<String, Object> params, Collection<?> collection){
		
		try {
			JasperPrint print = JasperFillManager.fillReport(jasperFile, params, new JRBeanCollectionDataSource(collection));
		
			JasperExportManager.exportReportToHtmlFile(print, OutFile);
		} catch (JRException e) {
			
			e.printStackTrace();
		}
				
	}
	
	public void openReportByPDF(String jasperFile, String OutFile, Map<String, Object> params, Collection<?> collection){
		
		try {
			JasperPrint print = JasperFillManager.fillReport(jasperFile, params, new JRBeanCollectionDataSource(collection));
		
			JasperExportManager.exportReportToPdfFile(print, OutFile);
		} catch (JRException e) {
			
			e.printStackTrace();
		}
				
	}
	
	public void openReportByJasper(String jasperFile, Map<String, Object> params, Collection<?> collection ){
		
		try {
			JasperPrint print = JasperFillManager.fillReport(jasperFile, params, new JRBeanCollectionDataSource(collection));
			
			JasperViewer viewer = new JasperViewer(print);
			viewer.setVisible(true);
			
		} catch (JRException e) {		
			e.printStackTrace();
		}		
		
	}

}
