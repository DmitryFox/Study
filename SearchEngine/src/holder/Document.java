package holder;

/**
 * @author Dmitry [ ______ ]
 * @version 07.10.2018 20:17
 */
public class Document {
	private final int id;
	private final String name;
	private int termCount = 0;

	public Document(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getTermCount() {
		return termCount;
	}

	public void setTermCount(int termCount) {
		this.termCount = termCount;
	}
}
