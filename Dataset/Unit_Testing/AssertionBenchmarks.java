public class AssertionBenchmarks {

	@Benchmark
	public void junit4_assertTrue_boolean() {
		Assert.assertTrue(true);
	}

	@Benchmark
	public void junitJupiter_assertTrue_boolean() {
		Assertions.assertTrue(true);
	}

	@Benchmark
	public void junit4_assertTrue_String_boolean() {
		Assert.assertTrue("message", true);
	}

	@Benchmark
	public void junitJupiter_assertTrue_boolean_String() {
		Assertions.assertTrue(true, "message");
	}

	@Benchmark
	public void junitJupiter_assertTrue_boolean_Supplier() {
		Assertions.assertTrue(true, () -> "message");
	}

	@Benchmark
	public void junitJupiter_assertTrue_BooleanSupplier_String() {
		Assertions.assertTrue(() -> true, "message");
	}

	@Benchmark
	public void junitJupiter_assertTrue_BooleanSupplier_Supplier() {
		Assertions.assertTrue(() -> true, () -> "message");
	}

}