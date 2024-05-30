import functools

@lru_cache
def cached_function():
    print('a')

@lru_cache(maxsize=30)
def cached_function():
    print('b')