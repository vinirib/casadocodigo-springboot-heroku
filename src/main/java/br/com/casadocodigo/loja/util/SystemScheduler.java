package br.com.casadocodigo.loja.util;

import br.com.casadocodigo.loja.builders.ProdutoBuilder;
import br.com.casadocodigo.loja.domain.Produto;
import br.com.casadocodigo.loja.domain.Usuario;
import br.com.casadocodigo.loja.repository.ProdutoRepository;
import br.com.casadocodigo.loja.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Component
public class SystemScheduler {

    private final static Logger logger = Logger.getLogger(SystemScheduler.class.getName());

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private UsuarioRepository userRepository;

    @Scheduled(fixedRate = 5 * 60 * 1000)// five minutes
    @CacheEvict(value = {"produtosHome", "collections"}, allEntries = true)
    public void removeProdutosNew() {
        logger.info("Verifyng database to get new Produtos " + dateFormat.format(new Date()));
        List<Produto> newProdutos = repository.findByIsNew(true);
        if (!newProdutos.isEmpty()) {
            logger.info("Excluding new Produtos of database");
            newProdutos.forEach(repository::delete);
        }
        logger.info("Verifyng database to get new Users " + dateFormat.format(new Date()));
        List<Usuario> newUsers = userRepository.findByUsernameNot("admin@casadocodigo.com.br");

        if (!newUsers.isEmpty()) {
            logger.info("Excluding new Usuarios of database");
            newUsers.forEach(userRepository::delete);
        }
    }

    @Scheduled(fixedRate = 100 * 60 * 1000, initialDelay = 100 * 60 * 1000)// 100 minutes
    @CacheEvict(value = {"produtosHome", "collections"}, allEntries = true)
    public void rebaseProdutos() {
        logger.info("Deleting all Produtos on database to rebuilding " + dateFormat.format(new Date()));
        repository.deleteAll();
        logger.info("Creating default Produtos " + dateFormat.format(new Date()));
        List<Produto> defaultProdutos = ProdutoBuilder.createDefaultTemplateProdutos();
        repository.saveAll(defaultProdutos);
    }
}
