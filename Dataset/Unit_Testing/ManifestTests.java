class ManifestTests {

	@ParameterizedTest
	@MethodSource("platform.tooling.support.Helper#loadModuleDirectoryNames")
	void manifestEntriesAdhereToConventions(String module) throws Exception {
		var version = Helper.version(module);
		var jarFile = MavenRepo.jar(module).toFile();
		try (var jar = new Jar(jarFile)) {
			var manifest = jar.getManifest();
			var attributes = manifest.getMainAttributes();
			assertValue(attributes, "Built-By", "JUnit Team");
			assertValue(attributes, "Specification-Title", module);
			assertValue(attributes, "Specification-Version", specificationVersion(version));
			assertValue(attributes, "Specification-Vendor", "junit.org");
			assertValue(attributes, "Implementation-Title", module);
			assertValue(attributes, "Implementation-Version", version);
			assertValue(attributes, "Implementation-Vendor", "junit.org");
			assertValue(attributes, "Automatic-Module-Name", null);
			assertValue(attributes, "Bundle-ManifestVersion", "2");
			assertValue(attributes, "Bundle-SymbolicName", module);
			assertValue(attributes, "Bundle-Version",
				MavenVersion.parseMavenString(version).getOSGiVersion().toString());
			switch (module) {
				case "junit-platform-commons" -> assertValue(attributes, "Multi-Release", "true");
				case "junit-platform-console" ->
					assertValue(attributes, "Main-Class", "org.junit.platform.console.ConsoleLauncher");
			}
			var domain = Domain.domain(manifest);
			domain.getExportPackage().forEach((pkg, attrs) -> {
				final var stringVersion = attrs.get(VERSION_ATTRIBUTE);
				assertNotNull(stringVersion);
				assertDoesNotThrow(() -> new Version(stringVersion));
			});
			domain.getImportPackage().forEach((pkg, attrs) -> {
				final var stringVersionRange = attrs.get(VERSION_ATTRIBUTE);
				if (stringVersionRange == null) {
					return;
				}
				assertDoesNotThrow(() -> new VersionRange(stringVersionRange));
			});
		}
	}

	private static String specificationVersion(String version) {
		var dash = version.indexOf('-');
		if (dash < 0) {
			return version;
		}
		return version.substring(0, dash);
	}

	private static void assertValue(Attributes attributes, String name, String expected) {
		var actual = attributes.getValue(name);
		assertEquals(expected, actual,
			String.format("Manifest attribute %s expected to be %s, but is: %s", name, expected, actual));
	}
}