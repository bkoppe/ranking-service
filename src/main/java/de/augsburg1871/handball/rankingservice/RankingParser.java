package de.augsburg1871.handball.rankingservice;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import de.augsburg1871.handball.rankingservice.model.Rank;
import de.augsburg1871.handball.rankingservice.parser.ValueExtractor;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.Map;

import static de.augsburg1871.handball.rankingservice.parser.ValueExtractor.getAsInt;
import static de.augsburg1871.handball.rankingservice.parser.ValueExtractor.getAsString;

public class RankingParser {

    private static final String RANG = "Rang";
    private static final String MANNSCHAFT = "Mannschaft";
    private static final String BEGEGNUNGEN = "Begegnungen";
    private static final String SIEGE = "S";
    private static final String UNENTSCHIEDEN = "U";
    private static final String NIEDERLAGEN = "N";
    private static final String TORE = "Tore";
    private static final String TOR_DIFFERENZ = "+/-";
    private static final String PUNKTE = "Punkte";

    public static List<Rank> parse(final Element tabelle)
    {
        final Map<String, Integer> indicies = Maps.newHashMap();
        final Elements rows = tabelle.select("tr");
        final Elements headline = rows.first().select("th");

        for (int i = 0; i < headline.size(); i++) {
            indicies.put(headline.get(i).text().trim(), i);
        }

        final List<Rank> ranks = Lists.newArrayList();
        // zeilenweise lesen
        for (int i = 1; i < rows.size(); i++) {
            // spaltenweise lesen
            final Elements columns = rows.get(i).select("td");

            if (columns.size() != 5 && columns.size() != 10) {
                continue;
            }

            final Rank rank = new Rank();
            // wenn wir zehn spalten haben gehen wir von einem regulären eintrag aus
            if (columns.size() == 10) {
                rank.setRang(getAsInt(columns, indicies.get(RANG)));
                rank.setMannschaft(getAsString(columns, indicies.get(MANNSCHAFT)));
                rank.setBegegnungen(getAsInt(columns, indicies.get(BEGEGNUNGEN)));
                rank.setSiege(getAsInt(columns, indicies.get(SIEGE)));
                rank.setUnentschieden(getAsInt(columns, indicies.get(UNENTSCHIEDEN)));
                rank.setNiederlagen(getAsInt(columns, indicies.get(NIEDERLAGEN)));
                rank.setTore(getAsString(columns, indicies.get(TORE)));
                rank.setTorDifferenz(Integer.valueOf(getAsInt(columns, indicies.get(TOR_DIFFERENZ))));
                rank.setPunkte(getAsString(columns, indicies.get(PUNKTE)));
            }

            // wenn wir fünf spalten haben versuchen wir den hinweis zu extrahieren
            if (columns.size() == 5) {
                rank.setRang(getAsInt(columns, indicies.get(RANG)));
                rank.setMannschaft(getAsString(columns, indicies.get(MANNSCHAFT)));
                rank.setHinweis(columns.get(4).text());
            }

            ranks.add(rank);
        }

        return ranks;
    }

}
