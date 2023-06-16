
public class Csv {


	/**
	 * Provides a basic CSV configuration that allows parsing CSV files produced by Microsoft Excel.
	 *
	 * @return a pre-configured {@link CsvParserSettings} object with suggested settings
	 * for parsing CSV files produced by Microsoft Excel.
	 */
	public static CsvParserSettings parseExcel() {
		CsvParserSettings settings = new CsvParserSettings();
		settings.getFormat().setLineSeparator("\r\n");
		settings.getFormat().setComment('\0');
		settings.setParseUnescapedQuotes(false);

		settings.setSkipEmptyLines(false);
		settings.trimValues(false);

		return settings;
	}

	/**
	 * Provides a basic CSV configuration for parsing CSV files in accordance with the
	 * rules established by <a href="http://tools.ietf.org/html/rfc4180">RFC 4180</a>
	 *
	 * @return a pre-configured {@link CsvParserSettings} object with suggested settings for parsing
	 * CSV using the <a href="http://tools.ietf.org/html/rfc4180">RFC 4180</a> rules.
	 */
	public static CsvParserSettings parseRfc4180() {
		CsvParserSettings settings = parseExcel();
		settings.setNormalizeLineEndingsWithinQuotes(false);
		return settings;
	}

	/**
	 * Provides a basic CSV configuration that allows writing CSV files that can be read by Microsoft Excel.
	 *
	 * @return a pre-configured {@link CsvWriterSettings} object with suggested settings for generating
	 * CSV files that can be read by Microsoft Excel.
	 */
	public static CsvWriterSettings writeExcel() {
		CsvWriterSettings settings = new CsvWriterSettings();

		settings.getFormat().setLineSeparator("\r\n");
		settings.getFormat().setComment('\0');
		settings.setEmptyValue(null);

		settings.setSkipEmptyLines(false);
		settings.trimValues(false);

		return settings;
	}

	/**
	 * Provides a basic CSV configuration for writing CSV files in accordance with the
	 * rules established by <a href="http://tools.ietf.org/html/rfc4180">RFC 4180</a>
	 *
	 * @return a pre-configured {@link CsvWriterSettings} object with the settings required to generate
	 * CSV files in accordance with the rules established by <a href="http://tools.ietf.org/html/rfc4180">RFC 4180</a>
	 */
	public static CsvWriterSettings writeRfc4180() {
		CsvWriterSettings settings = writeExcel();

		settings.setNormalizeLineEndingsWithinQuotes(false);
		settings.setQuoteEscapingEnabled(true);

		return settings;
	}
}
