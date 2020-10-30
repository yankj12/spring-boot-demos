

https://www.cnblogs.com/wlandwl/p/async.html



    默认线程池的弊端

    在线程池应用中，参考阿里巴巴java开发规范：线程池不允许使用Executors去创建，不允许使用系统默认的线程池，推荐通过ThreadPoolExecutor的方式，这样的处理方式让开发的工程师更加明确线程池的运行规则，规避资源耗尽的风险。Executors各个方法的弊端：

    newFixedThreadPool和newSingleThreadExecutor：主要问题是堆积的请求处理队列可能会耗费非常大的内存，甚至OOM。
    newCachedThreadPool和newScheduledThreadPool：要问题是线程数最大数是Integer.MAX_VALUE，可能会创建数量非常多的线程，甚至OOM。

    @Async默认异步配置使用的是SimpleAsyncTaskExecutor，该线程池默认来一个任务创建一个线程，若系统中不断的创建线程，最终会导致系统占用内存过高，引发OutOfMemoryError错误。针对线程创建问题，SimpleAsyncTaskExecutor提供了限流机制，通过concurrencyLimit属性来控制开关，当concurrencyLimit>=0时开启限流机制，默认关闭限流机制即concurrencyLimit=-1，当关闭情况下，会不断创建新的线程来处理任务。基于默认配置，SimpleAsyncTaskExecutor并不是严格意义的线程池，达不到线程复用的功能。
    