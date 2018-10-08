package data;

import core.Engine;
import holder.Document;
import holder.TermFrequency;

import java.io.*;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Dmitry [ _____ ]
 * @version 07.10.2018 19:54
 */
public class DocumentReader {
	private static final Pattern TOKEN_PATTERN = Pattern.compile("\\b([А-Яа-яA-Za-z]+)\\b");

	public DocumentReader(File file, Document document) {
		int documentId = document.getId();
		Engine engine = Engine.getInstance();

		int termCount = 1;
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
			String line;
			while ((line = reader.readLine()) != null) {
				Matcher matcher = TOKEN_PATTERN.matcher(line);
				while (matcher.find()) {
					String term = matcher.group(1).toLowerCase();
					int termId = engine.getNewTermId(term);

					Map<Integer, TermFrequency> termFrequencyMap = engine.getTermFrequency(termId);
					if (termFrequencyMap.get(documentId) == null)
						termFrequencyMap.put(documentId, new TermFrequency(termId, 1, document.getId()));
					else
						termFrequencyMap.get(documentId).increaseCounter();

					++termCount;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		document.setTermCount(termCount);
	}
}
