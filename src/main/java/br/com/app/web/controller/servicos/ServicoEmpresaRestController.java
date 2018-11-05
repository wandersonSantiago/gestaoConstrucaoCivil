package br.com.app.web.controller.servicos;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.app.entity.Imagens;
import br.com.app.entity.servicos.OcorrenciaServico;
import br.com.app.entity.servicos.ServicoEmpresa;
import br.com.app.service.servicos.ServicoEmpresaService;

@RestController
@RequestMapping("/rest/servicos/vincular")
public class ServicoEmpresaRestController {

	@Autowired
	private ServicoEmpresaService servicoService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/prestadora/{id}/pagamentos/liberado")
	public Collection<ServicoEmpresa> buscarServicosPagamentoLiberadoDaPrestadora(@PathVariable Long id) {
		return servicoService.buscarServicosPagamentoLiberadoDaPrestadora(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void insert(@RequestBody ServicoEmpresa servico) {
		servicoService.insert(servico);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping
	public void update(@RequestBody ServicoEmpresa servico) {
		servicoService.update(servico);

	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/prestadora/{id}/pagamentos/efetuar")
	public void efetuarPagamento(@PathVariable Long id) {
		servicoService.efetuarPagamento(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/prestadora/{id}/pagamentos")
	public Collection<ServicoEmpresa> buscarServicosDaPrestadora(@PathVariable Long id) {
		return servicoService.buscarServicosDaPrestadora(id);
		 
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}")
	public ServicoEmpresa findById(@PathVariable Long id) {
		return servicoService.findById(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/vistoria")
	public void alteraVistoria(@RequestBody ServicoEmpresa servico) {
		servicoService.salvarOuEditarVistoria(servico);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/vistoria/ocorrencia")
	public void insert(@RequestPart(value="file", required = false )  List<MultipartFile> files, @RequestPart("servicoEmpresa")  ServicoEmpresa obj, @RequestPart("ocorrencia")  OcorrenciaServico ocorrencia){
	
			files.forEach(file ->{
				String base64;
				try {
					System.out.println(file.getContentType());
					byte[]  bit = resizeImage(file.getBytes(), 1280 , 720 , "png");
					System.out.println(bit.length);
					base64 = Base64.encodeBase64String(bit);
					Imagens img = new Imagens();
					img.setDescricao(file.getOriginalFilename());
					img.setImagemBase64(base64);
					img.setOcorrencia(ocorrencia);
					ocorrencia.getImagens().add(img);
					ocorrencia.setArquivo(base64);
					obj.getOcorrencias().add(ocorrencia);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			});	
		 servicoService.salvarOuEditarVistoria(obj);
	}
	private static byte[] resizeImage(byte[] imageByte, int width, int height, String type) throws IOException {
	    try (InputStream inputImage = new ByteArrayInputStream(imageByte);) {
	        BufferedImage image = ImageIO.read(inputImage);
	        BufferedImage imgResized = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	        imgResized.getGraphics().drawImage(image, 0, 0, width, height, null);
	        return imageToByte(imgResized, type);
	    }
	}
	private static byte[] imageToByte(BufferedImage img, String type) throws IOException {
		 try (ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
		        ImageIO.write(img, type, baos);
		        baos.flush();
		        return baos.toByteArray();
		    }
	}
	
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/pagamento/salvar")
	public void salvarPagamento(@RequestBody ServicoEmpresa servico) {

		servicoService.salvarPagamento(servico);
	}
	
	@GetMapping(value = "/buscar")
	public ResponseEntity<Page<ServicoEmpresa>> findByDescricao(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="dataCadastro") String orderBy, 
			@RequestParam(value="direction", defaultValue="DESC") String direction,
			@RequestParam(value="descricao", required = false , defaultValue="")String descricao) {

		Page<ServicoEmpresa> list = null;
		
		if(descricao.isEmpty() || descricao.equalsIgnoreCase("")) {
			list = servicoService.findAll(PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}else {
			list = servicoService.findByDescricaoIgnoreCase(descricao, PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}
		
		return ResponseEntity.ok().body(list);
	}

}
