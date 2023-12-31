
public class CsvFormat extends Format {
	private char quote = '"';
	private char quoteEscape = '"';
	private String delimiter = ",";
	private Character charToEscapeQuoteEscaping = null;

	/**
	 * Returns the character used for escaping values where the field delimiter is part of the value. Defaults to '"'
	 *
	 * @return the quote character
	 */
	public char getQuote() {
		return quote;
	}

	/**
	 * Defines the character used for escaping values where the field delimiter is part of the value. Defaults to '"'
	 *
	 * @param quote the quote character
	 */
	public void setQuote(char quote) {
		this.quote = quote;
	}

	/**
	 * Identifies whether or not a given character is used for escaping values where the field delimiter is part of the value
	 *
	 * @param ch the character to be verified
	 *
	 * @return true if the given character is the character used for escaping values, false otherwise
	 */
	public boolean isQuote(char ch) {
		return this.quote == ch;
	}

	/**
	 * Returns the character used for escaping quotes inside an already quoted value. Defaults to '"'
	 *
	 * @return the quote escape character
	 */
	public char getQuoteEscape() {
		return quoteEscape;
	}

	/**
	 * Defines the character used for escaping quotes inside an already quoted value. Defaults to '"'
	 *
	 * @param quoteEscape the quote escape character
	 */
	public void setQuoteEscape(char quoteEscape) {
		this.quoteEscape = quoteEscape;
	}

	/**
	 * Identifies whether or not a given character is used for escaping quotes inside an already quoted value.
	 *
	 * @param ch the character to be verified
	 *
	 * @return true if the given character is the quote escape character, false otherwise
	 */
	public boolean isQuoteEscape(char ch) {
		return this.quoteEscape == ch;
	}

	/**
	 * Returns the field delimiter character. Defaults to ','
	 *
	 * @return the field delimiter character
	 */
	public char getDelimiter() {
		if (delimiter.length() > 1) {
			throw new UnsupportedOperationException("Delimiter '" + delimiter + "' has more than one character. Use method getDelimiterString()");
		}
		return delimiter.charAt(0);
	}

	/**
	 * Returns the field delimiter sequence.
	 *
	 * @return the field delimiter as a {@code String}.
	 */
	public String getDelimiterString() {
		return delimiter;
	}

	/**
	 * Defines the field delimiter character. Defaults to ','
	 *
	 * @param delimiter the field delimiter character
	 */
	public void setDelimiter(char delimiter) {
		this.delimiter = String.valueOf(delimiter);
	}

	/**
	 * Defines the field delimiter as a sequence of characters. Defaults to ','
	 *
	 * @param delimiter the field delimiter sequence.
	 */
	public void setDelimiter(String delimiter) {
		if (delimiter == null) {
			throw new IllegalArgumentException("Delimiter cannot be null");
		}
		if (delimiter.isEmpty()) {
			throw new IllegalArgumentException("Delimiter cannot be empty");
		}
		this.delimiter = delimiter;
	}

	/**
	 * Identifies whether or not a given character represents a field delimiter
	 *
	 * @param ch the character to be verified
	 *
	 * @return true if the given character is the field delimiter character, false otherwise
	 */
	public boolean isDelimiter(char ch) {
		if (delimiter.length() > 1) {
			throw new UnsupportedOperationException("Delimiter '" + delimiter + "' has more than one character. Use method isDelimiter(String)");
		}
		return this.delimiter.charAt(0) == ch;
	}

	/**
	 * Identifies whether or not a given character represents a field delimiter
	 *
	 * @param sequence the character sequence to be verified
	 *
	 * @return true if the given sequence is the field delimiter character sequence, false otherwise
	 */
	public boolean isDelimiter(String sequence) {
		return this.delimiter.equals(sequence);
	}

	/**
	 * Returns the character used to escape the character used for escaping quotes defined by {@link #getQuoteEscape()}.
	 * For example, if the quote escape is set to '\', and the quoted value ends with: \", as in the following example:
	 *
	 * <p>
	 * [ " a\\", b ]
	 * </p>
	 *
	 * Then:
	 * <ul>
	 * <li>If the character to escape the '\' is undefined, the record won't be parsed. The parser will read characters: [a],[\],["],[,],[ ],[b] and throw an error because it cannot find a closing quote</li>
	 * <li>If the character to escape the '\' is defined as '\', the record will be read with 2 values: [a\] and [b]</li>
	 * </ul>
	 * Defaults to '\0' (undefined)
	 *
	 * @return the character to escape the character used for escaping quotes defined
	 */
	public final char getCharToEscapeQuoteEscaping() {
		if (charToEscapeQuoteEscaping == null) { //not provided by the user
			if (quote == quoteEscape) {
				return '\0'; //not required
			} else {
				return quoteEscape;
			}
		}
		return charToEscapeQuoteEscaping;
	}

	/**
	 * Defines the character used to escape the character used for escaping quotes defined by {@link #getQuoteEscape()}.
	 * For example, if the quote escape is set to '\', and the quoted value ends with: \", as in the following example:
	 *
	 * <p>
	 * [ " a\\", b ]
	 * </p>
	 *
	 * Then:
	 * <ul>
	 * <li>If the character to escape the '\' is undefined, the record won't be parsed. The parser will read characters: [a],[\],["],[,],[ ],[b] and throw an error because it cannot find a closing quote</li>
	 * <li>If the character to escape the '\' is defined as '\', the record will be read with 2 values: [a\] and [b]</li>
	 * </ul>
	 * Defaults to '\0' (undefined)
	 *
	 * @param charToEscapeQuoteEscaping the character to escape the character used for escaping quotes defined
	 */
	public final void setCharToEscapeQuoteEscaping(char charToEscapeQuoteEscaping) {
		this.charToEscapeQuoteEscaping = charToEscapeQuoteEscaping;
	}

	
	public final boolean isCharToEscapeQuoteEscaping(char ch) {
		char current = getCharToEscapeQuoteEscaping();
		return current != '\0' && current == ch;
	}

	@Override
	protected TreeMap<String, Object> getConfiguration() {
		TreeMap<String, Object> out = new TreeMap<String, Object>();
		out.put("Quote character", quote);
		out.put("Quote escape character", quoteEscape);
		out.put("Quote escape escape character", charToEscapeQuoteEscaping);
		out.put("Field delimiter", delimiter);
		return out;
	}

	@Override
	public final CsvFormat clone() {
		return (CsvFormat) super.clone();
	}
}
