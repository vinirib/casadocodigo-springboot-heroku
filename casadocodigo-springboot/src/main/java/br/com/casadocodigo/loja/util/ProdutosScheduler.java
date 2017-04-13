package br.com.casadocodigo.loja.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.casadocodigo.loja.domain.Produto;
import br.com.casadocodigo.loja.repository.ProdutoRepository;

@Component
public class ProdutosScheduler {

	private final static Logger logger =  Logger.getLogger(ProdutosScheduler.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private ProdutoRepository repository;
	
	@Scheduled(fixedRate=5*60*1000)// five minutes
	@CacheEvict(value="produtosHome", allEntries=true)
	public void removeProdutosNew(){
		logger.info("Verifyng database to get new Produtos "+ dateFormat.format(new Date()));
		List<Produto> newProdutos = repository.findByIsNew(true);
		if (!newProdutos.isEmpty()) {
			logger.info("Excluding new Produtos of database");
			newProdutos.forEach(repository::delete);
		}
	}
}
