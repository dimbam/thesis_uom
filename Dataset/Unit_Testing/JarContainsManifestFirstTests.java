class JarContainsManifestFirstTests {

	@ParameterizedTest
	@MethodSource("platform.tooling.support.Helper#loadModuleDirectoryNames")
	void manifestFirst(String module) throws Exception {
		var modulePath = MavenRepo.jar(module);

		if (Files.notExists(modulePath)) {
			fail("No such file: " + modulePath);
		}

		// JarInputStream expects the META-INF/MANIFEST.MF to be at the start of the JAR archive
		try (final JarInputStream jarInputStream = new JarInputStream(new FileInputStream(modulePath.toFile()))) {
			assertNotNull(jarInputStream.getManifest(), "MANIFEST.MF should be available via JarInputStream");
		}
	}
}