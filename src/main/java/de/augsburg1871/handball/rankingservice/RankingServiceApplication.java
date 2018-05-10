package de.augsburg1871.handball.rankingservice;

import com.google.common.collect.Lists;
import de.augsburg1871.handball.rankingservice.model.Rank;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RankingServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RankingServiceApplication.class, args);
	}

	private static final String TABELLE = "Tabelle";

	@Override
	public void run(String... args) throws Exception {
        ClassPathResource classPathResource = new ClassPathResource("test-tabelle.html");

        Document document = Jsoup.parse(classPathResource.getFile(), StandardCharsets.UTF_8.name());
		Elements h2Tags = document.getElementsByTag("h2");

		for (Element headline : h2Tags) {
			System.out.println(headline);

			if (StringUtils.equalsIgnoreCase(headline.text(), TABELLE)) {
				System.out.println("found:" + headline);
				final Element tabelle = headline.nextElementSibling();
                List<Rank> ranking = Lists.newArrayList(RankingParser.parse(tabelle));
                System.out.println(ranking);
			}
		}
    }

}
