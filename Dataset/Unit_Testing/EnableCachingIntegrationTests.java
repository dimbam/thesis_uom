class EnableCachingIntegrationTests {

	@Test
	void repositoryIsClassBasedCacheProxy() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(Config.class, ProxyTargetClassCachingConfig.class);
		ctx.refresh();

		assertCacheProxying(ctx);
		assertThat(AopUtils.isCglibProxy(ctx.getBean(FooRepository.class))).isTrue();
	}

	@Test
	void repositoryUsesAspectJAdviceMode() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(Config.class, AspectJCacheConfig.class);
		// this test is a bit fragile, but gets the job done, proving that an
		// attempt was made to look up the AJ aspect. It's due to classpath issues
		// in integration-tests that it's not found.
		assertThatException().isThrownBy(ctx::refresh)
			.withMessageContaining("AspectJCachingConfiguration");
	}


	private void assertCacheProxying(AnnotationConfigApplicationContext ctx) {
		FooRepository repo = ctx.getBean(FooRepository.class);
		assertThat(isCacheProxy(repo)).isTrue();
	}

	private boolean isCacheProxy(FooRepository repo) {
		if (AopUtils.isAopProxy(repo)) {
			for (Advisor advisor : ((Advised)repo).getAdvisors()) {
				if (advisor instanceof BeanFactoryCacheOperationSourceAdvisor) {
					return true;
				}
			}
		}
		return false;
	}


	@Configuration
	@EnableCaching(proxyTargetClass=true)
	static class ProxyTargetClassCachingConfig {

		@Bean
		CacheManager mgr() {
			return new NoOpCacheManager();
		}
	}


	@Configuration
	static class Config {

		@Bean
		FooRepository fooRepository() {
			return new DummyFooRepository();
		}
	}


	@Configuration
	@EnableCaching(mode=AdviceMode.ASPECTJ)
	static class AspectJCacheConfig {

		@Bean
		CacheManager cacheManager() {
			return new NoOpCacheManager();
		}
	}


	interface FooRepository {

		List<Object> findAll();
	}


	@Repository
	static class DummyFooRepository implements FooRepository {

		@Override
		@Cacheable("primary")
		public List<Object> findAll() {
			return Collections.emptyList();
		}
	}

}