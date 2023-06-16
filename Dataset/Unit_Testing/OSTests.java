class OSTests {
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = { " ", "\t" })
	void blankOsNameYieldsNull(String name) {
		assertNull(OS.parse(name));
	}

	@ParameterizedTest
	@ValueSource(strings = { "!", "?", "✨", "MINIX" })
	void unknownOsNameYieldsOTHER(String name) {
		assertEquals(OS.OTHER, OS.parse(name));
	}

	@Nested
	class ValidNames {
		@ParameterizedTest
		@ValueSource(strings = { "AIX", "Aix", "LaIxOS" })
		void aix(String name) {
			assertEquals(OS.AIX, OS.parse(name));
		}

		@ParameterizedTest
		@ValueSource(strings = { "FREEBSD", "FreeBSD" })
		void freebsd(String name) {
			assertEquals(OS.FREEBSD, OS.parse(name));
		}

		@ParameterizedTest
		@ValueSource(strings = { "LINUX", "Linux" })
		void linux(String name) {
			assertEquals(OS.LINUX, OS.parse(name));
		}

		@ParameterizedTest
		@ValueSource(strings = { "MAC", "mac" })
		void mac(String name) {
			assertEquals(OS.MAC, OS.parse(name));
		}

		@ParameterizedTest
		@ValueSource(strings = { "OPENBSD", "OpenBSD" })
		void openbsd(String name) {
			assertEquals(OS.OPENBSD, OS.parse(name));
		}

		@ParameterizedTest
		@ValueSource(strings = { "SOLARIS", "SunOS" })
		void solaris(String name) {
			assertEquals(OS.SOLARIS, OS.parse(name));
		}

		@ParameterizedTest
		@ValueSource(strings = { "WINDOW", "Microsoft Windows [Version 10.?]" })
		void windows(String name) {
			assertEquals(OS.WINDOWS, OS.parse(name));
		}
	}
}