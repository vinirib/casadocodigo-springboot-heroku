package br.com.casadocodigo.loja.builders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import com.google.common.collect.Lists;

import br.com.casadocodigo.loja.domain.Categoria;
import br.com.casadocodigo.loja.domain.Preco;
import br.com.casadocodigo.loja.domain.Produto;
import br.com.casadocodigo.loja.domain.TipoPreco;

public class ProdutoBuilder {

    private List<Produto> produtos = new ArrayList<>();

    private ProdutoBuilder(Produto produto) {
        produtos.add(produto);

    }

    public static ProdutoBuilder newProduto(TipoPreco tipoPreco, BigDecimal valor) {
        Produto livro = create("livro 1", tipoPreco, valor);
        return new ProdutoBuilder(livro);
    }

    public static ProdutoBuilder newProduto() {
        Produto livro = create("livro 1", TipoPreco.COMBO, BigDecimal.TEN);
        return new ProdutoBuilder(livro);
    }

    private static Produto create(String nomeLivro, TipoPreco tipoPreco, BigDecimal valor) {
        Produto livro = new Produto();
        livro.setTitulo(nomeLivro);
        livro.setDataLancamento(Calendar.getInstance());
        livro.setPaginas(150);
        livro.setDescricao("Livro top sobre testes");
        livro.setSumarioPath("http://google.com.br");
        Preco preco = new Preco();
        preco.setPreco(tipoPreco);
        preco.setValor(valor);
        livro.getTipoPrecos().add(preco);
        livro.setCategorias(Lists.newArrayList(Categoria.values()));
        return livro;
    }

    public ProdutoBuilder more(int number) {
        Produto base = produtos.get(0);
        Preco preco = base.getTipoPrecos().get(0);
        for (int i = 0; i < number; i++) {
            produtos.add(create("Book " + i, preco.getPreco(), preco.getValor()));
        }
        return this;
    }

    public Produto buildOne() {
        return produtos.get(0);
    }

    public List<Produto> buildAll() {
        return produtos;
    }
    
    
    public static List<Produto> createDefaultTemplateProdutos(){
    	Produto algoritmosEmJava = new Produto();
		algoritmosEmJava.setTitulo("Algoritmos em Java");
		algoritmosEmJava.setDescricao(
				" Em nosso dia a dia, realizamos uma série de buscas e ordenações que nos são tão naturais que nem percebemos como sua "
						+ "presença é ubíqua e facilita nossa vida. Quando pesquisamos produtos por preço em uma loja, ou queremos buscar uma pessoa em uma "
						+ "lista, ou mesmo quando organizamos as cartas de baralho para algum jogo, estamos usando algoritmos."
						+ "Neste livro, Guilherme Silveira mostra que, na computação, não é diferente. Muitos dos problemas complexos de lógica com que "
						+ "desenvolvedores lidam todos os dias envolvem conceitos básicos de busca e ordenação, alguns dos quais priorizam economia de tempo "
						+ "ou de memória. Você verá como funcionam os algoritmos e como implementá-los, por meio do estudo de soluções que já usamos no cotidiano. "
						+ "Entendê-los a ponto de sermos capazes de recriá-los nos traz um conhecimento valioso para todo programador: o pensamento lógico e a "
						+ "quebra de problemas em partes menores que podem ser resolvidas com algoritmos. ");

		algoritmosEmJava.setSumarioPath(
				"//cdn.shopify.com/s/files/1/0155/7645/products/AlgoritmosemJAVA_Amazon_large.jpg?v=1631300171");
		setPrecosOnProdutos(algoritmosEmJava);
		algoritmosEmJava.setDataLancamento(Calendar.getInstance());
		algoritmosEmJava.setPaginas(440);
		algoritmosEmJava.setCategorias(Arrays.asList(Categoria.JAVA));

		Produto jpaEficaz = new Produto();
		jpaEficaz.setTitulo("JPA Eficaz");
		jpaEficaz.setDescricao(
				"  Entender o básico da JPA pode ser simples e rápido, mas com o uso do dia a dia, ela se torna uma ferramenta que demanda muito cuidado. "
						+ "É preciso conhecer bem seus detalhes para não cair em armadilhas."
						+ "Nesse livro, Hebert Coelho explica em tópicos curtos, e direto ao ponto, técnicas para usar a JPA da melhor forma possível, com atenção aos equívocos "
						+ "mais comuns. Você vai aprender a evitar o problema de N+1 consultas, usar corretamente o controle transacional, fazer consultas que envolvam relacionamentos, "
						+ "controlar quando usar EAGER ou LAZY nos relacionamentos, evitar a famigerada LazyInitializationException e muito mais.");

		jpaEficaz.setSumarioPath(
				"//cdn.shopify.com/s/files/1/0155/7645/products/jpa-eficaz-featured_large.png?v=1411490333");
		setPrecosOnProdutos(jpaEficaz);
		jpaEficaz.setDataLancamento(Calendar.getInstance());
		jpaEficaz.setPaginas(167);
		jpaEficaz.setCategorias(Arrays.asList(Categoria.JAVA));

		Produto jquery = new Produto();
		jquery.setTitulo("Dominando JavaScript com jQuery");
		jquery.setDescricao(
				" Esqueça os livros que mais parecem uma documentação da API! Aqui você encontra jQuery apresentado de maneira incremental, substituindo código JavaScript"
						+ " longo e complicado.Truques de animação, componentes visuais, e manipulação do DOM através de jQuery, jQueryUI e jQuery mobile. Refatore uma aplicação JavaScript e "
						+ "finalmente entenda callbacks, seletores e AJAX sem ficar preso ao copy and paste. Tudo isso com a versão 2.x, a mais recente do framework.");

		jquery.setSumarioPath(
				"//cdn.shopify.com/s/files/1/0155/7645/products/cover_07b67aad-28bd-47fd-ae2c-968432b697c1_large.jpg?v=1631670404");
		setPrecosOnProdutos(jquery);
		jquery.setDataLancamento(Calendar.getInstance());
		jquery.setPaginas(193);
		jquery.setCategorias(Arrays.asList(Categoria.FRONT_END));

		Produto css = new Produto();
		css.setTitulo("CSS Eficiente");
		css.setDescricao(
				" Quando aprendemos a trabalhar com CSS, frequentemente nos pegamos perdidos em detalhes fundamentais que não nos são explicados. "
						+ "Por vezes, alguns desses detalhes passam despercebidos até pelo desenvolvedor front-end mais experiente. Mas como ir além do conhecimento básico do CSS e preparar o caminho"
						+ " para explorar tópicos mais avançados?Neste livro, Tárcio Zemel ensina como organizar seu estilo, entender especificidade, como usar diferentes seletores, trabalhar orientado a "
						+ " objetos com CSS e várias outras técnicas que farão diferença no dia a dia do trabalho com os estilos e abrirão novas possibilidades para você explorar ainda mais o CSS. ");

		css.setSumarioPath(
				"//cdn.shopify.com/s/files/1/0155/7645/products/cover_01987b09-b64e-45c2-8575-728cce314abf_large.jpg?v=1631658704");
		setPrecosOnProdutos(css);
		css.setDataLancamento(Calendar.getInstance());
		css.setPaginas(131);
		css.setCategorias(Arrays.asList(Categoria.FRONT_END));

		Produto spring = new Produto();
		spring.setTitulo("Vire o jogo com Spring Framework ");
		spring.setDescricao(
				" Criado para simplificar o desenvolvimento de aplicações Java, o Spring se tornou um dos frameworks de mais destaque dentro desse grande ambiente."
						+ "Aprenda muito mais que o básico do Spring, desde o tradicional Container de Inversão de Controle e Injeção de Dependências, passando pelos robustos módulos de segurança,"
						+ " transações, programação orientada a aspectos e também o fantástico módulo MVC, o SpringMVC.");

		spring.setSumarioPath(
				"//cdn.shopify.com/s/files/1/0155/7645/products/VireojogocomSpringFramework_ebook_large.jpg?v=1631647617");
		setPrecosOnProdutos(spring);
		spring.setDataLancamento(Calendar.getInstance());
		spring.setPaginas(296);
		spring.setCategorias(Arrays.asList(Categoria.JAVA));

		Produto linux = new Produto();
		linux.setTitulo("Começando com o Linux");
		linux.setDescricao(
				" Conhecer e utilizar Linux é essencial. Não apenas para um administrador de sistemas, mas também para "
						+ "o desenvolvedor web, para o administrador de banco de dados ou o usuário mais engajado de qualquer sistema vindo"
						+ " do Unix, como o Mac OSX.Neste livro, Daniel Romero parte desde a instalação do Ubuntu, utilização de comandos"
						+ " básicos, conhecimento do sistema de diretórios para depois atacar processos, configuração de pacotes como "
						+ "Apache, PHP, Java e MySQL, para depois chegar na criação de seus próprios scripts.Em um linguajar fácil e passo "
						+ "a passo, você vai perder o medo de encarar a linha de comando e os terminais, para tirar o máximo de proveito"
						+ " desse onipresente sistema operacional. ");

		linux.setSumarioPath("//cdn.shopify.com/s/files/1/0155/7645/products/cover_7c9b974b-b420-4e17-b334-d53e9c2ecc6b_large.jpg?v=1631591679");
		setPrecosOnProdutos(linux);
		linux.setDataLancamento(Calendar.getInstance());
		linux.setPaginas(150);
		linux.setCategorias(Arrays.asList(Categoria.OUTROS));

		Produto agile = new Produto();
		agile.setTitulo("Agile");
		agile.setDescricao(
				" As diversas metodologias ágeis que formam o tão falado \"Agile\" são hoje uma das maneiras mais "
						+ "eficientes de guiar um projeto do ínicio ao fim, sem complicações e mantendo o tempo inteiro o foco na "
						+ "entrega de valor para o cliente.Neste livro, André Faria Gomes, renomado coach e líder de equipes, explica "
						+ "como os diferentes sabores de Agile podem fazer um projeto de sucesso. Aprenda como o Kanban, XP e Scrum podem "
						+ "ser usados em conjunto e onde cada um dos métodos se complementa, além de conhecer dicas para adotar as "
						+ "metodologias no seu dia a dia de trabalho. ");

		agile.setSumarioPath("//cdn.shopify.com/s/files/1/0155/7645/products/agile-featured_large.png?v=1411485880");
		setPrecosOnProdutos(agile);
		agile.setDataLancamento(Calendar.getInstance());
		agile.setPaginas(150);
		agile.setCategorias(Arrays.asList(Categoria.AGILE));

		Produto node = new Produto();
		node.setTitulo("Node.js");
		node.setDescricao(
				"Node.js é uma poderosa plataforma. Ele permite escrever aplicações JavaScript no server-side, "
						+ "tirando proveito da sintaxe e familiaridade da linguagem para escrever aplicações web escaláveis."
						+ "Como o Node.js usa um modelo orientado a eventos, focado em I/O não bloqueante, desenvolver nele pode "
						+ "ser diferente para quem está acostumado às aplicações web tradicionais. Neste livro, Caio Ribeiro Pereira "
						+ "quebra essa enorme barreira, mostrando claramente essa mudança de paradigma, além de focar em tópicos "
						+ "importantes, as APIs principais e frameworks como o Express e o Socket.IO.");

		node.setSumarioPath("//cdn.shopify.com/s/files/1/0155/7645/products/nodejs-featured_large.png?v=1411486494");
		setPrecosOnProdutos(node);
		node.setDataLancamento(Calendar.getInstance());
		node.setPaginas(161);
		node.setCategorias(Arrays.asList(Categoria.FRONT_END, Categoria.WEB, Categoria.OUTROS));

		Produto androidJogos = new Produto();
		androidJogos.setTitulo("Desenvolvimento de Jogos para Android");
		androidJogos
				.setDescricao(" Crie histórias e jogos da forma que sempre imaginou! Se você já conhece um pouco de "
						+ "desenvolvimento Android, vai se surpreender com este livro. De forma didática e prática, conceitos são"
						+ " apresentados sempre com aplicações diretas no jogo que é desenvolvido.Construa um jogo do início ao fim, "
						+ "sem esquecer a importância do enredo, distribuição, arte e como prender a atenção do jogador. Lógica,"
						+ " matemática e física são apresentados sem traumas. Também conheceremos muitos dos benefícios do framework "
						+ "Cocos2D, utilizado na versão definitiva do nosso game. ");

		androidJogos.setSumarioPath(
				"//cdn.shopify.com/s/files/1/0155/7645/products/jogos-android-featured_large.png?v=1411488637");
		setPrecosOnProdutos(androidJogos);
		androidJogos.setDataLancamento(Calendar.getInstance());
		androidJogos.setPaginas(189);
		androidJogos.setCategorias(Arrays.asList(Categoria.GAMES, Categoria.OUTROS));

		Produto startup = new Produto();
		startup.setTitulo("Guia da Startup");
		startup.setDescricao(
				"Aprenda as melhores técnicas para criar o seu produto web e faça ele render dinheiro o mais "
						+ "rápido possível com o Guia da Startup. Da mesma maneira que diversas empresas de sucesso fizeram, como a "
						+ "Caelum e a Locaweb, invista em suas ideias.");

		startup.setSumarioPath(
				"//cdn.shopify.com/s/files/1/0155/7645/products/cover_f0c223aa-023e-415b-8389-a942213aae39_large.jpg?v=1486151929");
		setPrecosOnProdutos(startup);
		startup.setDataLancamento(Calendar.getInstance());
		startup.setPaginas(388);
		startup.setCategorias(Arrays.asList(Categoria.AGILE, Categoria.OUTROS));

		return Arrays.asList(algoritmosEmJava, jpaEficaz, jquery, css, spring, linux, agile, node,
				androidJogos, startup);
	}

	private static void setPrecosOnProdutos(Produto produto) {
		Preco precoEbook = new Preco();
		precoEbook.setPreco(TipoPreco.EBOOK);
		precoEbook.setValor(new BigDecimal(30));

		Preco precoImpresso = new Preco();
		precoImpresso.setPreco(TipoPreco.IMPRESSO);
		precoImpresso.setValor(new BigDecimal(50));

		Preco precoCombo = new Preco();
		precoCombo.setPreco(TipoPreco.COMBO);
		precoCombo.setValor(new BigDecimal(70));

		produto.setTipoPrecos(Arrays.asList(precoEbook, precoImpresso, precoCombo));
	}
}
