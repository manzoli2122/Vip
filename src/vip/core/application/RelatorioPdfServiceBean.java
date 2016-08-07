package vip.core.application;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import vip.core.domain.AdvanceMoney;
import vip.core.domain.Salary;
import vip.secretariat.domain.Attendance;
import vip.secretariat.domain.EmployeeAttendance;
import vip.secretariat.domain.Payment;


@Stateless
public class RelatorioPdfServiceBean implements RelatorioPdfService {

	
	private static final long serialVersionUID = 1L;
	
	@Override
	public String RelatorioSalario(Salary salary) {
		
		String path = Thread.currentThread().getContextClassLoader().getResource("/vip/core/relatorio/salario").getPath();
		path += "salario_"+ salary.getFuncionario().getName().replace(" ", "_")+"_"+ salary.getCreateDate().getTime().toLocaleString().substring(0,10).replace("/","-")+".pdf";
		System.out.println(path);
		
		Document documento = new Document(); 
		documento.setMargins(50f, 50f, 50f, 50f);
		try {
			PdfWriter.getInstance(documento, new FileOutputStream(path));
			documento.open();
			
					
			
			
			/* CABEÇALHO */
			Paragraph p1 = new Paragraph("Relatório De Salário");
			p1.setAlignment(Element.ALIGN_CENTER);
			p1.setSpacingAfter(20);
			documento.add(p1);
			
			p1 = new Paragraph("Funcionário : "+salary.getFuncionario().getName());
			p1.setAlignment(Element.ALIGN_CENTER);
			p1.setSpacingAfter(20);
			documento.add(p1);
			
			p1 = new Paragraph("Data : " + salary.getCreateDate().getTime().toGMTString().substring(0,11));
			p1.setAlignment(Element.ALIGN_CENTER);
			p1.setSpacingAfter(20);
			documento.add(p1);
			
			
			
			
			
			
			
			/* TABELA SERVIÇOS */
			PdfPTable table = new PdfPTable(new float[] { 0.15f, 0.4f, 0.3f , 0.15f });
			table.setWidthPercentage(100);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			p1 = new Paragraph(" Serviços ");
			
			PdfPCell header = new PdfPCell(p1);
			header.setBackgroundColor(new BaseColor(150, 255,150));
			header.setColspan(4);
			header.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(header);
			
			PdfPCell cell ;
			p1 = new Paragraph("Data");
			cell = new PdfPCell(p1);
			cell.setBackgroundColor(new BaseColor(200, 255,200));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			p1 = new Paragraph("Cliente");
			cell = new PdfPCell(p1);
			cell.setBackgroundColor(new BaseColor(200, 255,200));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			p1 = new Paragraph("Serviço");
			cell = new PdfPCell(p1);
			cell.setBackgroundColor(new BaseColor(200, 255,200));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			p1 = new Paragraph("Comissão");
			cell = new PdfPCell(p1);
			cell.setBackgroundColor(new BaseColor(200, 255,200));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
						
			Iterator<EmployeeAttendance> ite = salary.getServicos().iterator();
			EmployeeAttendance serv;
			Double valor = 0.0;
			while(ite.hasNext()){
				serv = ite.next();
				valor += serv.getComissao();
				table.addCell(serv.getAtendance().getCreateDate().getTime().toLocaleString().substring(0,10));
				table.addCell(serv.getAtendance().getCliente().getName());
				table.addCell(serv.getTask().getName());
				table.addCell( NumberFormat.getCurrencyInstance().format( serv.getComissao()));
				
			}
			table.addCell("Total");
			table.addCell("");
			table.addCell("");
			cell = new PdfPCell( new Paragraph(NumberFormat.getCurrencyInstance().format(valor)));
			cell.setBackgroundColor(new BaseColor(100, 255,100));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			documento.add(table);

			
			
			
			
			documento.add(new Paragraph("  "));
			
			
			
			
			
			
			/* TABELA VALE */
			table = new PdfPTable(new float[] { 0.15f, 0.7f, 0.15f  });
			table.setWidthPercentage(100);
			
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			
			p1 = new Paragraph(" Vales ");
			header = new PdfPCell(p1);
			header.setBackgroundColor(new BaseColor(150, 255,150));
			header.setColspan(3);
			header.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(header);
			
			
			
			p1 = new Paragraph("Data");
			cell = new PdfPCell(p1);
			cell.setBackgroundColor(new BaseColor(200, 255,200));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			p1 = new Paragraph("Descrição");
			cell = new PdfPCell(p1);
			cell.setBackgroundColor(new BaseColor(200, 255,200));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			p1 = new Paragraph("Valor");
			cell = new PdfPCell(p1);
			cell.setBackgroundColor(new BaseColor(200, 255,200));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			Iterator<AdvanceMoney> ite1 = salary.getVales().iterator();
			AdvanceMoney val;
			valor = 0.0;
			while(ite1.hasNext()){
				val = ite1.next();
				valor += val.getValor();	
				table.addCell(val.getCreateDate().getTime().toLocaleString().substring(0,10));
				table.addCell(val.getDescricao());
				table.addCell(  NumberFormat.getCurrencyInstance().format(val.getValor()));
			}
			table.addCell("Total");
			table.addCell("");
			cell = new PdfPCell( new Paragraph( NumberFormat.getCurrencyInstance().format(valor)));
			cell.setBackgroundColor(new BaseColor(100, 255,100));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			documento.add(table);
			
			
			
			
			
			/* TOTAL DO SALARIO */
			documento.add(new Paragraph("  "));
			
			p1 =  new Paragraph(" Salario total =  "+ NumberFormat.getCurrencyInstance().format(salary.getValor()));
			
			documento.add(new Paragraph(p1));
			
			
			
			
			documento.add(new Paragraph("  "));
			
			p1 =  new Paragraph(" Resposável  =  "+ salary.getCreateRegister().getName());
			
			documento.add(new Paragraph(p1));
			
			
		} catch (DocumentException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		documento.close();
		
		return path;
		
	}
	
	
	
	
	
	
	@Override
	public String RelatorioDiario(List<Attendance> lista) {
		
		String path = Thread.currentThread().getContextClassLoader().getResource("/vip/core/relatorio/diario").getPath();
		if( (lista == null)  || lista.isEmpty() )
			return null;
		
		path += "relatorio_Diario"+ lista.get(0).getCreateDate().getTime().toLocaleString().substring(0,10).replace("/","-")+".pdf";
				
		System.out.println(path);
		
		Document documento = new Document(); 
		documento.setMargins(50f, 50f, 50f, 50f);
		
		try {
			PdfWriter.getInstance(documento, new FileOutputStream(path));
			documento.open();
					
			/* CABEÇALHO */
			Paragraph p1 = new Paragraph("Relatório Diário");
			p1.setAlignment(Element.ALIGN_CENTER);
			p1.setSpacingAfter(20);
			documento.add(p1);
			
			p1 = new Paragraph("Data : " + lista.get(0).getCreateDate().getTime().toGMTString().substring(0,11));
			p1.setAlignment(Element.ALIGN_CENTER);
			p1.setSpacingAfter(20);
			documento.add(p1);
			
			
			/* TABELA SERVIÇOS */
			Iterator<Attendance> atendimentos = lista.iterator();
			
			Attendance atendimento;
			
			while(atendimentos.hasNext()){
				
				atendimento = atendimentos.next();
				PdfPTable table = new PdfPTable(new float[] { 0.4f, 0.4f, 0.2f });
				table.setWidthPercentage(100);
				table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				p1 = new Paragraph(atendimento.getCliente().getName());
				
				PdfPCell header = new PdfPCell(p1);
				header.setBackgroundColor(new BaseColor(150, 255,150));
				header.setColspan(3);
				header.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(header);
				
				PdfPCell cell ;
				p1 = new Paragraph("Serviço");
				cell = new PdfPCell(p1);
				cell.setBackgroundColor(new BaseColor(200, 255,200));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				p1 = new Paragraph("Funcionário");
				cell = new PdfPCell(p1);
				cell.setBackgroundColor(new BaseColor(200, 255,200));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				
				p1 = new Paragraph("Valor");
				cell = new PdfPCell(p1);
				cell.setBackgroundColor(new BaseColor(200, 255,200));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				
				
				
				Iterator<EmployeeAttendance> servicos = atendimento.getEmployeeAttendances().iterator();
				EmployeeAttendance servico;
				
				while(servicos.hasNext()){
					servico = servicos.next();
					table.addCell(servico.getTask().getName());
					table.addCell(servico.getEmployee().getName());
					table.addCell( NumberFormat.getCurrencyInstance().format( servico.getValorComDesconto()));
				}
				
				
				
				
				p1 = new Paragraph(" ****** ");
				header = new PdfPCell(p1);
				header.setBackgroundColor(new BaseColor(200, 200,200));
				header.setColspan(3);
				header.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(header);
				
				/*p1 = new Paragraph("Pagamento");
				header = new PdfPCell(p1);
				header.setBackgroundColor(new BaseColor(150, 255,150));
				header.setColspan(3);
				header.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(header);
				*/
				
				
				Iterator<Payment> pagamentos = atendimento.getPayments().iterator();
				Payment pagamento;
				
				while(pagamentos.hasNext()){
					
					pagamento = pagamentos.next();
					
					cell = new PdfPCell( new Paragraph("Pagamento"));
					cell.setBackgroundColor(new BaseColor(150, 150,255));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
					
					cell = new PdfPCell( new Paragraph(pagamento.getFormOfPayment().getLabel()));
					cell.setBackgroundColor(new BaseColor(150, 150,255));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
					
					cell = new PdfPCell( new Paragraph(NumberFormat.getCurrencyInstance().format(pagamento.getValor())));
					cell.setBackgroundColor(new BaseColor(150, 150,255));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
					
				}
				
				
				
				
				documento.add(table);
				documento.add(new Paragraph("  "));
			}
			
			
		} catch (DocumentException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		documento.close();
		
		return path;
		
	}



}
