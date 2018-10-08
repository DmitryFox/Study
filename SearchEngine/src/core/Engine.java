package core;

import holder.Document;
import holder.TermFrequency;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Dmitry [ ______ ]
 * @version 07.10.2018 19:53
 */
public class Engine {
	/**
	 * List of all documents.
	 */
	private static final Map<Integer, Document> documentMap = new HashMap<>();
	/**
	 * A list of all the terms that are in all documents.
	 */
	private static final Map<String, Integer> termIdMap = new TreeMap<>();

	/**
	 * The list of frequencies of all terms along with documents.
	 */
	private static final Map<Integer, Map<Integer, TermFrequency>> termFrequencyMap = new TreeMap<>();

	public Document getDocument(int documentId) {
		return documentMap.get(documentId);
	}

	public void addDocument(int id, Document document) {
		documentMap.put(id, document);
	}

	public int getTermId(String term) {
		return hasTerm(term) ? termIdMap.get(term) : -1;
	}

	public int getNewTermId(String term) {
		if (hasTerm(term)) {
			return termIdMap.get(term);
		} else {
			int newId = termIdMap.size() + 1;
			addTerm(newId, term);
			return newId;
		}
	}

	public boolean hasTerm(String term) {
		return termIdMap.containsKey(term);
	}

	public void addTerm(int id, String term) {
		termIdMap.put(term, id);
	}

	public int getTermListSize() {
		return termIdMap.size();
	}

	public Map<Integer, TermFrequency> getTermFrequency(int id) {
		if (termFrequencyMap.get(id) == null) {
			termFrequencyMap.put(id, new TreeMap<>());
		}

		return termFrequencyMap.get(id);
	}

	public boolean hasTermFrequency(int id) {
		return termFrequencyMap.containsKey(id);
	}

	public void addTermFrequency(int id, Map<Integer, TermFrequency> termFrequency) {
		termFrequencyMap.put(id, termFrequency);
	}

	public void calculateTermFrequency(Document document) {
		for (Map<Integer, TermFrequency> termDocuments : termFrequencyMap.values()) {
			TermFrequency termFrequency = termDocuments.get(document.getId());
			if (termFrequency != null)
				termFrequency.setFrequency((double) termFrequency.getCount() / (double) document.getTermCount());
		}
	}

	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder {
		private static final Engine INSTANCE = new Engine();
	}

	public static Engine getInstance() {
		return SingletonHolder.INSTANCE;
	}
}
