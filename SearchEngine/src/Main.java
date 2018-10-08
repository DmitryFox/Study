import core.Engine;
import data.DocumentReader;
import holder.Document;
import holder.TermFrequency;
import utils.FileUtil;

import java.io.File;
import java.util.*;

/**
 * @author Dmitry [ ______ ]
 * @version 07.10.2018 19:31
 */
public class Main {
	private static final String HELP_MESSAGE = "Available commands:" + "\r\n"
			+ "-help [To show help message]" + "\r\n"
			+ "-index ./directory [Indexing files in directory]" + "\r\n"
			+ "-load ./directory [Load indexed files]" + "\r\n"
			+ "-find query [Search message in documents]" + "\r\n"
			+ "-exit [Terminate program]" + "\r\n";

	public static void main(String[] args) {
		log("Start.");
		log("Enter --help to show available commands.");

		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] command = line.split(" ");
			switch (command[0]) {
				case "-help":
					log(HELP_MESSAGE);
					break;
				case "-index":
					indexedFileCommand(command[1]);
					break;
				case "-load":
					loadIndexedFileCommand(command[1]);
					break;
				case "-find":
					String query = line.substring(command[0].length() + 1);
					searchInIndexCommand(query);
					break;
				case "-exit":
					System.exit(0);
					break;

			}
		}

	    log("Program exit...");
	}

	private static void loadIndexedFileCommand(String directory) {

	}

	private static void indexedFileCommand(String directory) {
		log("Start indexing *.txt files...");
		Engine engine = Engine.getInstance();

		long timeStart = System.currentTimeMillis();
		int documentId = 1;
		for (File file : FileUtil.getFileList(new File(directory), ".txt", true)) {
			Document document = new Document(documentId, file.getName());
			new DocumentReader(file, document);

			engine.addDocument(documentId, document);
			engine.calculateTermFrequency(document);
			++documentId;
		}
		long timeEnd = System.currentTimeMillis();

		log("Indexing time: " + (timeEnd - timeStart) + "ms.");
	}

	private static void searchInIndexCommand(String query) {
		long timeStart = System.nanoTime();
		Engine engine = Engine.getInstance();

		int termId = engine.getTermId(query);
		if (termId != -1) {
			Map<Integer, TermFrequency> findDocumentMap = new TreeMap<>(Comparator.reverseOrder());
			Map<Integer, TermFrequency> termFrequency = engine.getTermFrequency(termId);
			if (termFrequency != null) {
				for (Map.Entry<Integer, TermFrequency> fileIndex : termFrequency.entrySet()) {
					findDocumentMap.put(fileIndex.getValue().getCount(), fileIndex.getValue());
				}
			}

			for (Map.Entry<Integer, TermFrequency> findDocument : findDocumentMap.entrySet()) {
				log(findDocument.getValue().getCount() + "\t" + engine.getDocument(findDocument.getValue().getDocumentId()).getName());
			}
		}
		long timeEnd = System.nanoTime();

		log("Time spent: " + (timeEnd - timeStart) / 1000 + "µs.");
	}

	private static void log(Object msg) {
		System.out.println(msg);
	}
}
