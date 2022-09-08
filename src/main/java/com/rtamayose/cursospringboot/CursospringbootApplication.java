package com.rtamayose.cursospringboot;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rtamayose.cursospringboot.domain.Categoria;
import com.rtamayose.cursospringboot.domain.Cidade;
import com.rtamayose.cursospringboot.domain.Cliente;
import com.rtamayose.cursospringboot.domain.Endereco;
import com.rtamayose.cursospringboot.domain.Estado;
import com.rtamayose.cursospringboot.domain.Produto;
import com.rtamayose.cursospringboot.domain.enums.TipoCliente;
import com.rtamayose.cursospringboot.repositories.CategoriaRepository;
import com.rtamayose.cursospringboot.repositories.CidadeRepository;
import com.rtamayose.cursospringboot.repositories.ClienteRepository;
import com.rtamayose.cursospringboot.repositories.EnderecoRepository;
import com.rtamayose.cursospringboot.repositories.EstadoRepository;
import com.rtamayose.cursospringboot.repositories.ProdutoRepository;

@SpringBootApplication
public class CursospringbootApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursospringbootApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception{
		
		Categoria cat1  = new Categoria(null, "Informática");
		Categoria cat2  = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "computador", 200.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado est1 = new Estado(null, "SP");
		Estado est2 = new Estado(null, "RJ"); 
		
		Cidade cid1 = new Cidade(null, "São paulo", est1);
		Cidade cid2 = new Cidade(null, "Rio de Janeiro", est2);
		Cidade cid3 = new Cidade(null, "Limeira", est1);
		
		est1.getCidades().addAll(Arrays.asList(cid1,cid3));
		est2.getCidades().addAll(Arrays.asList(cid2));
		 
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "28082034330", TipoCliente.PESSSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("964915878","997696217"));
		
		Endereco end1 = new Endereco(null, "Av. Paulista","280","Predio Roxo","Paulista","02472110", cli1, cid1);
		Endereco end2 = new Endereco(null, "Av. Flores","300","Predio Azul","Flores","02472200", cli1, cid2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1,end2));
		
	}

}
