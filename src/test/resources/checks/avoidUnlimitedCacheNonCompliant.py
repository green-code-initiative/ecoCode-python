import functools

class A:
    @cache  # Noncompliant {{Do not set cache size to unlimited}}
    def cached_method_a():
        print('a')

    @lru_cache(maxsize=None)  # Noncompliant {{Do not set cache size to unlimited}}
    def cached_method_b():
        print('b')

@cache  # Noncompliant {{Do not set cache size to unlimited}}
def cached_function():
    print('a')
